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
import lombok.ToString;

/**
 * @author 86152
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RespBean<T> {


    private long code;
    private String message;
    private  T obj;

    public RespBean(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public RespBean(String message) {
        this.message = message;
    }

    /**
     * 默认成功的返回结果
     *
     * @return
     */
    public static RespBean success() {
        return new RespBean<>(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功的返回结果
     *
     * @param obj
     * @return
     */
    public static <T> RespBean<T> success(T obj) {
        return new RespBean<>(RespBeanEnum.SUCCESS.getCode(), RespBean.success().getMessage(), obj);
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
    public  static <T> RespBean<T> error(RespBeanEnum respBeanEnum) {
        return new RespBean<>(respBeanEnum.getCode(), respBeanEnum.getMessage(), null);
    }

    /**
     * 失败的返回结果
     *
     * @param respBeanEnum
     * @param obj
     * @return
     */
    public static <T> RespBean<T> error(RespBeanEnum respBeanEnum, T obj) {
        return new RespBean<>(respBeanEnum.getCode(), respBeanEnum.getMessage(), obj);
    }


    public static <T> RespBean<T> error(long code,String message) {
        return new RespBean<>(code,message);
    }


}
