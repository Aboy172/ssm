package com.cym.crowdfundingadmincomponent.config;

import com.cym.crowdfundingcommonutil.utils.CrowdConstant;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * program: ssm Date: 2022-04-07  22:00 Author: cym Description: Security配置类
 *
 * @author cym
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {


  @Autowired
  private UserDetailsService userDetailsService;


  @Bean
  public BCryptPasswordEncoder getBcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder builder) throws Exception {
    builder.userDetailsService(userDetailsService).passwordEncoder(getBcryptPasswordEncoder());

  }

    @SuppressWarnings("AlibabaAvoidCommentBehindStatement")
    @Override
    protected void configure (HttpSecurity security) throws Exception {
        security
                .authorizeRequests() //给请求授权
                //给请求页面进行授权，所有人都可以访问
                .antMatchers("/admin-login.html")
                .permitAll()
                .antMatchers("/jquery/**")
                .permitAll()
                .antMatchers("/js/**")
                .permitAll()
                .antMatchers("/layer/**")
                .permitAll()
                .antMatchers("/jquery-validation/**")
                .permitAll()
                .antMatchers("/bootstrap/**")
                .permitAll()
                .antMatchers("/css/**")
                .permitAll()
                .antMatchers("/fonts/**")
                .permitAll()
                .antMatchers("/ztree/**")
                .permitAll()
                .antMatchers("/error")
                .permitAll()
                // 给所有请求授权
                .anyRequest()
                //需要表单登录
                .authenticated()
                .and()
                .formLogin()
                //指定登录的请求，不使用security自带的login页面
                .loginPage("/admin-login.html")
                //指定登录处理的请求、
                .loginProcessingUrl("/security/do/login.html")
                //登录请求携带的参数
                .usernameParameter("loginAcct")
                .passwordParameter("userPswd")
                //登录成功以后跳转的页面
                .defaultSuccessUrl("/admin-main.html")
                //登录失败处理器
                .failureHandler((request,response,exception) -> {
                    if (Strings.isEmpty(request.getParameter(CrowdConstant.ATTR_LOGINACCT)) || Strings.isEmpty(request.getParameter(CrowdConstant.ATTR_USERPSWD))) {
                        request.setAttribute(CrowdConstant.ATTR_LOGIN_ERROR,CrowdConstant.ATTR_USN_OR_PSW_NULL);
                    }
                    if (Strings.isNotBlank(request.getParameter(CrowdConstant.ATTR_LOGINACCT)) && Strings.isNotBlank(request.getParameter(CrowdConstant.ATTR_USERPSWD))){
                        request.setAttribute(CrowdConstant.ATTR_LOGIN_ERROR,CrowdConstant.ATTR_USN_OR_PSW_ERROR);
                    }
                    request.getRequestDispatcher("/admin-login.html").forward(request,response);
                })
                .and()
                .csrf()
                .disable()
                .logout()
                // 退出请求的地址
                .logoutUrl("/security/admin/do/loginOut.html")
                //成功以后重定向的页面
                .logoutSuccessUrl("/admin-login.html")
                ;


  }
}
