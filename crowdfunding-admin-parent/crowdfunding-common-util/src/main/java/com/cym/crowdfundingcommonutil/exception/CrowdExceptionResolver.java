package com.cym.crowdfundingcommonutil.exception;

import cn.hutool.json.JSONUtil;
import com.cym.crowdfundingcommonutil.constant.CrowdConstant;
import com.cym.crowdfundingcommonutil.utils.CrowdUtils;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * program: ssm Date: 2022-03-30  10:34 Author: cym Description: 当前类是一个异常处理器类
 *
 * @author 86152
 */
@ControllerAdvice
public class CrowdExceptionResolver {




  /**
   * 全局ajax请求处理，直接返回ResBean格式
   */
  @ExceptionHandler(value = GlobalException.class)
  @ResponseBody
  public RespBean resolverGlobalException(
      GlobalException ex) {
    return  RespBean.error(ex.getRespBeanEnum());
  }


  /**
   * 通用处理异常返回视图方法
   *
   * @param viewName
   * @param exception
   * @param request
   * @param response
   * @return
   * @throws IOException
   */
  public ModelAndView commonResolver(String viewName, Exception exception,
      HttpServletRequest request, HttpServletResponse response, RespBeanEnum respBeanEnum)
      throws IOException {
    boolean judgeRequestType = CrowdUtils.judgeRequestType(request);
    ModelAndView modelAndView = new ModelAndView();
    //如果是ajax请求
    if (judgeRequestType) {
      //返回异常的错误信息
      RespBean error = RespBean.error(respBeanEnum, exception);
      //将异常信息转换为json字符串
      String errorJson = JSONUtil.toJsonStr(error);
      //响应给浏览器
      response.getWriter().write(errorJson);
      return null;
    }
    //向请求域共享数据
    modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);
    //设置视图对应的名称
    modelAndView.setViewName(viewName);
    return modelAndView;
  }


}
