package com.cym.crowd.exception;

import com.cym.crowdfundingcommonutil.exception.GlobalException;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author cym
 * @date 2022-06-02  23:17
 * @description
 */
@RestControllerAdvice
public class ShortMessageExceptionResolver {

  @ExceptionHandler(value = GlobalException.class)
  public RespBean resolverGlobalException(
      GlobalException ex) {
    return RespBean.error(ex.getSendMessageFailCode().getCode(),
        ex.getSendMessageFailCode().getMessage());
  }
}
