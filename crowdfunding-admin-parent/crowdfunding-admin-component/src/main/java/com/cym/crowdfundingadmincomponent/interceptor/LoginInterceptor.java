package com.cym.crowdfundingadmincomponent.interceptor;

import com.cym.crowdfundingadmincomponent.exception.AdminLoginException;
import com.cym.crowdfundingadmincomponent.exception.GlobalException;
import com.cym.crowdfundingadminentity.entity.pojo.Admin;
import com.cym.crowdfundingcommonutil.constant.CrowdConstant;
import com.cym.crowdfundingcommonutil.utils.CrowdUtils;
import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * program: ssm Date: 2022-03-31  19:26 Author: cym Description:
 *
 * @author 86152
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    HttpSession session = request.getSession();
    Admin admin = ((Admin) session.getAttribute(CrowdConstant.ATTR_ADMIN));
    boolean requestType = CrowdUtils.judgeRequestType(request);
    //普通请求未登录，转发到异常页面
    if (admin == null) {
      log.info("请登陆后访问");
      throw new AdminLoginException(RespBeanEnum.ADMIN_IS_NOT_LOGIN);
    }
    //如果是ajax请求,并且未登录响应自定义信息给浏览器端
    if (requestType && admin == null) {
      log.info("请登陆后访问");
      throw new GlobalException(RespBeanEnum.REQUEST_ERROR);
    }
    return true;

  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }
}
