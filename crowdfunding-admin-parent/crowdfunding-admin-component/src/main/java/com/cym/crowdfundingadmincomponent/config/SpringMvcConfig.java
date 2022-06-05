package com.cym.crowdfundingadmincomponent.config;



import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * program: ssm Date: 2022-03-29  19:34 Author: cym Description:
 *
 * @author cym
 */
@Configuration(proxyBeanMethods = false)
public class SpringMvcConfig implements WebMvcConfigurer {

  /**
   * 不拦截的url请求
   */
  private final String[] notLoginInterceptPaths = {
      "/jquery/**", "/js/**", "/layer/**", "/jquery-validation/**", "/bootstrap/**", "/css/**",
      "/fonts/**",
      "/ztree/**", "/error", "/admin/do/doLogin.html", "/admin-login.html", "/admin-main.html",
      "/admin/do/LoginOut.html"};

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {

    //registry.addViewController("/admin-login.html").setViewName("admin-login");

  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
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



}
