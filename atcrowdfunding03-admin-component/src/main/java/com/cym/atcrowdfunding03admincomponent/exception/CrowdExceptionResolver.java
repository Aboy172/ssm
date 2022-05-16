package com.cym.atcrowdfunding03admincomponent.exception;

import com.alibaba.fastjson.JSON;
import com.cym.atcrowdfunding05commonutil.utils.CrowdConstant;
import com.cym.atcrowdfunding05commonutil.utils.CrowdUtils;
import com.cym.atcrowdfunding05commonutil.vo.RespBean;
import com.cym.atcrowdfunding05commonutil.vo.RespBeanEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * program: ssm
 * Date: 2022-03-30  10:34
 * Author: cym
 * Description: 当前类是一个异常处理器类
 *
 * @author 86152
 */
@ControllerAdvice
public class CrowdExceptionResolver {


    /**
     * 处理空指针异常，并跳转到相应的错误页面
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolverNullException (NullPointerException exception,HttpServletRequest request,HttpServletResponse
            response) throws IOException {
        String viewName = CrowdConstant.ATTR_NAME_EXCEPTION;
        return commonResolver(viewName,exception,request,response,RespBeanEnum.NULL_EXCEPTION);
    }

    @ExceptionHandler(value = AdminExitsAddException.class)
    public ModelAndView resolverAdminExitsAddException (AdminExitsAddException exception,HttpServletRequest request,HttpServletResponse
            response) throws IOException {
        String viewName = CrowdConstant.ATTR_ADMIN_ADD;
        return commonResolver(viewName,exception,request,response,exception.getRespBeanEnum());
    }

    @ExceptionHandler(value = AdminExitsUpdateException.class)
    public ModelAndView resolverAdminExitsUpdateException (AdminExitsUpdateException exception,HttpServletRequest request,HttpServletResponse
            response) throws IOException {
        String viewName = CrowdConstant.ATTR_NAME_EXCEPTION;
        return commonResolver(viewName,exception,request,response,exception.getRespBeanEnum());
    }

    @ExceptionHandler(value = AdminLoginException.class)
    public ModelAndView resolverAdminLoginException (AdminLoginException exception,HttpServletRequest request,HttpServletResponse
            response) throws IOException {
        String viewName = CrowdConstant.ATTR_NAME_EXCEPTION;

        return commonResolver(viewName,exception,request,response,exception.getRespBeanEnum());
    }

    /**
     * 捕获异常security异常
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public ModelAndView resolverUnAccessAuthException (Exception exception,HttpServletRequest request,HttpServletResponse
            response) throws IOException {
        String viewName = CrowdConstant.ATTR_NO_AUTH;
        request.setAttribute(CrowdConstant.ATTR_AUTH_EXCEPTION,"不允许访问此资源");
        return commonResolver(viewName,exception,request,response,RespBeanEnum.AUTH_IS_NOT_EXITS);
    }

    /**
     *
     * 全局ajax请求处理，直接返回ResBean格式
     */
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public RespBean resolverGlobalException (GlobalException ex) {
        return RespBean.error(ex.getRespBeanEnum());
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
    public ModelAndView commonResolver (String viewName,Exception exception,HttpServletRequest request,HttpServletResponse response,RespBeanEnum respBeanEnum) throws IOException {
        boolean judgeRequestType = CrowdUtils.judgeRequestType(request);
        ModelAndView modelAndView = new ModelAndView();
        //如果是ajax请求
        if (judgeRequestType) {
            //返回异常的错误信息
            RespBean error = RespBean.error(respBeanEnum,exception);
            //将异常信息转换为json字符串
            String errorJson = JSON.toJSONString(error);
            //响应给浏览器
            response.getWriter().write(errorJson);
            return null;
        }
        //向请求域共享数据
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION,exception);
        //设置视图对应的名称
        modelAndView.setViewName(viewName);
        return modelAndView;
    }


}
