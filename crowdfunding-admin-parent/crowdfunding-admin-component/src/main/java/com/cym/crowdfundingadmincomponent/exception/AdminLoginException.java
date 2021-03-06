package com.cym.crowdfundingadmincomponent.exception;

import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * program: ssm Date: 2022-04-04  22:23 Author: cym Description:
 *
 * @author 86152
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginException extends RuntimeException {

  private RespBeanEnum respBeanEnum;
}
