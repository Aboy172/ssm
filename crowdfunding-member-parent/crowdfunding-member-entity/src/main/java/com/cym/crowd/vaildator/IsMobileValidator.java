//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cym.crowd.vaildator;

import com.cym.crowdfundingcommonutil.utils.ValidatorUtil;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

/**
 * @author cym
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

  private boolean required = false;

  public IsMobileValidator() {
  }

  @Override
  public void initialize(IsMobile constraintAnnotation) {
    boolean required = constraintAnnotation.required();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (this.required) {
      return ValidatorUtil.isMobile(value);
    } else {
      return StringUtils.isEmpty(value) || ValidatorUtil.isMobile(value);
    }
  }
}