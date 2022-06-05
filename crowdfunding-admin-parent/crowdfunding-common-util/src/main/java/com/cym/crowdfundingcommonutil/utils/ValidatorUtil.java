//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cym.crowdfundingcommonutil.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

/**
 * @author cym
 */
public class ValidatorUtil {
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^1[3|4578]\\d{9}$");

    public ValidatorUtil() {
    }

    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        } else {
            Matcher matcher = MOBILE_PATTERN.matcher(mobile);
            return matcher.matches();
        }
    }
}
