//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

/*
  对ajax返回的数据进行统一处理
 */
package com.cym.crowdfundingcommonutil.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author 86152
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class RespBean {


    private long code;
    private String message;
    private  Object obj;

    public RespBean (Object obj) {
        this.obj = obj;
    }


    /**
     * 默认成功的返回结果
     *
     * @return
     */
    public static RespBean success() {
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功的返回结果
     *
     * @param obj
     * @return
     */
    public static  RespBean success(Object obj) {
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBean.success().getMessage(), obj);
    }

    /**
     * 失败以后返回的结果
     * @return
     */
    /**
     * 失败的返回结果
     *
     * @param respBeanEnum
     * @return
     */
    public static RespBean error(RespBeanEnum respBeanEnum) {
        return new RespBean(respBeanEnum.getCode(), respBeanEnum.getMessage(), null);
    }

    /**
     * 失败的返回结果
     *
     * @param respBeanEnum
     * @param obj
     * @return
     */
    public static RespBean error(RespBeanEnum respBeanEnum, Object obj) {
        return new RespBean(respBeanEnum.getCode(), respBeanEnum.getMessage(), obj);
    }


}
