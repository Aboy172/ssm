package com.cym.crowd.exception;

import com.cym.crowdfundingcommonutil.exception.GlobalException;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author cym
 * @date 2022-06-02  23:17
 * @description
 */
@RestControllerAdvice
public class AuthorizationExceptionResolver {

  @ExceptionHandler(value = GlobalException.class)
  public RespBean resolverGlobalException(
      GlobalException ex) {
    return RespBean.error(ex.getSendMessageFailCode().getCode(),
        ex.getSendMessageFailCode().getMessage());
  }
  @ExceptionHandler(value = BindException.class )
  public RespBean resolverMethodArgumentNotValidException(
      BindException ex) {

      RespBean respBean = RespBean.error(RespBeanEnum.MENU_PID_ERROR);
      respBean.setMessage("" + ((ObjectError)ex.getBindingResult().getAllErrors().get(0)).getDefaultMessage());
      return respBean;
  }

}
