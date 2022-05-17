package com.cym.crowdfundingadmincomponent.config;


import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.*;


/**
 * program: ssm
 * Date: 2022-03-29  19:34
 * Author: cym
 * Description:
 * @author cym
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * 不拦截的url请求
     */
    private final String[] notLoginInterceptPaths = {
                "/jquery/**","/js/**","/layer/**","/jquery-validation/**","/bootstrap/**","/css/**","/fonts/**",
            "/ztree/**","/error","/admin/do/doLogin.html","/admin-login.html","/admin-main.html","/admin/do/LoginOut.html"};

    @Override
    public void addViewControllers (ViewControllerRegistry registry) {
        //这里必须注意，只有发送get请求并且返回的是视图才加入view-controller中
        // 异常捕捉只能捕捉handler中的请求，无法捕捉view-controller中的请求
        //registry.addViewController("/admin-login.html").setViewName("admin-login");


    }

    @Override
    public void addResourceHandlers (ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * 注册拦截器
     *
     * @param registry
     */
    //@Override
    //public void addInterceptors (InterceptorRegistry registry) {
    //    registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(notLoginInterceptPaths);
    //
    //}

    /**
     * 开启对静态资源的访问，默认就是开启的，无需配
     *
     * @param configurer
     */
    //@Override
    //public void configureDefaultServletHandling (DefaultServletHandlerConfigurer configurer) {
    //    configurer.enable("default-servlet");
    //}

    /**
     * 配置dispatchServlet，只处理*.json和*.html的请求，配了之后静态资源404，还没有找到解决方法
     * @return
     */
    //@Bean
    //public ServletRegistrationBean restServlet ( ) {
    //    //注解扫描上下文
    //    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
    //    //base package
    //    applicationContext.scan("com.cym.atcrowdfunding03admincomponent.handler");
    //    //通过构造函数指定dispatcherServlet的上下文
    //    DispatcherServlet rest_dispatcherServlet = new DispatcherServlet(applicationContext);
    //
    //    //用ServletRegistrationBean包装servlet
    //    ServletRegistrationBean registrationBean
    //            = new ServletRegistrationBean(rest_dispatcherServlet);
    //    registrationBean.setLoadOnStartup(1);
    //    //指定urlmapping
    //    registrationBean.addUrlMappings("*.html","*.json","*.css", "*.js", "*.ttf", "*.png ", "*.jpg", "*.gif ", "*.woff", "*.woff2");
    //    //指定name，如果不指定默认为dispatcherServlet,暂时不需要多个DispatcherServlet，所以直接覆盖掉
    //    //registrationBean.setName("noRest");
    //    return registrationBean;
    //}
}
