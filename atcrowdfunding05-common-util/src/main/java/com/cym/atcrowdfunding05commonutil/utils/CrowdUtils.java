package com.cym.atcrowdfunding05commonutil.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * program: ssm
 * Date: 2022-03-30  10:11
 * Author: cym
 * Description:
 */

/**
 * 判断是否为ajax请求，如果是返回true，如果不是，说明是普通请求，返回false
 */
public class CrowdUtils {

    public static boolean judgeRequestType (HttpServletRequest request) {
        String contentTypeHeader = request.getHeader("Content-Type");
        String xRequestHeader = request.getHeader("X-Requested-With");
        return
                contentTypeHeader != null && contentTypeHeader.contains("application/json")
                        ||
                        ("XMLHttpRequest".equals(xRequestHeader));

    }
}
