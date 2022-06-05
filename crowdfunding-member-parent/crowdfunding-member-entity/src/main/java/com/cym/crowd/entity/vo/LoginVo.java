package com.cym.crowd.entity.vo;

import com.cym.crowd.vaildator.IsMobile;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cym
 * @date 2022-06-05  19:15
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVo {

  @IsMobile
  @NotNull
  private String phoneNumber;

}
