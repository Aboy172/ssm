package pro01.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * program: ssm
 * Date: 2022-04-07  22:00
 * Author: cym
 * Description: Security配置类
 */
@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyUserDetailServiceImpl myUserDetailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Bean
    public BCryptPasswordEncoder getPasswordEncoder ( ) {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure (AuthenticationManagerBuilder builder) throws Exception {
        builder
                //.inMemoryAuthentication()  //内存中检查账号密码
                //.withUser("cym").password("123456")    //指定账号名和密码
                //.roles("ADMIN","学徒")             //设置角色
                //.and()
                //.withUser("cc").password("123456")    //指定另一个账号名和密码
                //.authorities("INSERT","EDIT","内门弟子");     //设置权限

                .userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder);  //自定义登录账号和密码

    }

    @Override
    protected void configure (HttpSecurity security) throws Exception {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        security.
                authorizeRequests()  //对请求进行授权
                .antMatchers("/index.jsp")  //针对/index.jsp请求进行授权
                .permitAll()   //所有人都可以访问
                .antMatchers("/layui/**") //放开layui的静态资源
                .permitAll()       //所有人都可以访问
                .antMatchers("/level1/**")
                .hasRole("ADMIN")
                .antMatchers("/level2/**")
                .hasAuthority("UPDATE")
                .and()
                .authorizeRequests()  //对请求进行授权
                .anyRequest()        //对所有请求进行授权
                .authenticated()    //需要登录才能访问
                .and()
                .formLogin()   //表单形式提交
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure (HttpServletRequest request,HttpServletResponse response,AuthenticationException e) throws IOException, ServletException {
                        request.setAttribute("loginError","账号或密码错误");
                        request.getRequestDispatcher("/index.jsp").forward(request,response);
                    }
                })
                .loginPage("/index.jsp")  //自定义去到登录页面的请求，不使用security自带的
                .loginProcessingUrl("/do/login.html")  //点击登录执行的请求
                //.permitAll()                   //所有人都可以访问
                .usernameParameter("loginAcct") //指定登录请求的账号参数
                .passwordParameter("userPswd")  //指定登录请求密码参数
                .defaultSuccessUrl("/main.html")//登录成功默认跳转的页面
                .and()
                .logout()
                .logoutUrl("/do/logout.html")
                .logoutSuccessUrl("/index.jsp")
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request,response,e) -> {
                    request.setAttribute("message","抱歉，您当前不具备访问此资源的权限");
                    request.getRequestDispatcher("/WEB-INF/views/no_auth.jsp").forward(request,response);
                })
                .and()
                .rememberMe()   //记住我，在页面上要设置按钮的name为remember-me
                .tokenRepository(repository)  //记住我数据库版
        ;
    }
}
