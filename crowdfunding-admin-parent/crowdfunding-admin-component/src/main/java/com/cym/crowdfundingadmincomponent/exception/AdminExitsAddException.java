package com.cym.crowdfundingadmincomponent.exception;

import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * program: ssm Date: 2022-04-02  21:25 Author: cym Description:
 *
 * @author 86152
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminExitsAddException extends RuntimeException {

  private RespBeanEnum respBeanEnum;
}
