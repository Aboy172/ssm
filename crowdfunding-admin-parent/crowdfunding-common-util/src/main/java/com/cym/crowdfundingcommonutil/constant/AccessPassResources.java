package com.cym.crowdfundingcommonutil.constant;

import com.cym.crowdfundingcommonutil.exception.GlobalException;
import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import java.util.HashSet;
import java.util.Set;

/**
 * @author cym
 * @date 2022-05-22  22:43
 * @description 放行资源常量类
 */
public class AccessPassResources {
  public static final Set<String> PASS_RES_SET = new HashSet<>();
  //可访问页面
  static{
    PASS_RES_SET.add("/");
    PASS_RES_SET.add("/auth/member/to/reg/page");
    PASS_RES_SET.add("/auth/member/to/login/page");
  }

  public static final Set<String> STATIC_RES_SET = new HashSet<>();
  //静态资源
  static{
    STATIC_RES_SET.add("bootstrap");
    STATIC_RES_SET.add("css");
    STATIC_RES_SET.add("fonts");
    STATIC_RES_SET.add("img");
    STATIC_RES_SET.add("jquery");
    STATIC_RES_SET.add("layer");
    STATIC_RES_SET.add("script");
    STATIC_RES_SET.add("ztree");
  }

  /**
   * 判断请求路径是否是访问静态资源
   * @param servletPath 请求路径
   * @return 返回结果
   */
  public static boolean judgeCurrentServletPathWhetherStaticResource(String servletPath) {
    //排除字符串为空的情况
    if (servletPath == null || servletPath.length() == 0){
      throw new GlobalException(RespBeanEnum.valueOf(CrowdConstant.MESSAGE_STATIC_INVALIDATE));
    }
    if (PASS_RES_SET.contains(servletPath)){
      return true;
    }
    //以"/"拆分路径
    String[] split = servletPath.split("/");
    String firstLevelPath = split[1];
    //判断路径是静态资源
    return STATIC_RES_SET.contains(firstLevelPath);

  }

}
