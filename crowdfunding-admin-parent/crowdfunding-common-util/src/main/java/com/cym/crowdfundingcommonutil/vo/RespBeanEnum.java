package com.cym.crowdfundingcommonutil.vo;

import lombok.*;

/**
 * @author cym
 */

@AllArgsConstructor
@ToString
@Getter
public enum RespBeanEnum {

  SUCCESS(200, "SUCCESS"),
  ERROR(500, "服务器启动失败"),

  USERNAME_OR_PASSWORD_IS_NULL(40000, "用户名或密码为空"),
  ADMIN_ERROR(40001, "管理员账号或密码错误"),
  REQUEST_ERROR(40002, "请登录以后再访问"),
  ADMIN_IS_NOT_LOGIN(40003, "用户未登录"),
  ADMIN_EXIST(40004, "用户已存在"),
  ADMIN_INFO_IS_NULL(40005, "信息为空"),
  ADMIN_NOT_EXIST(40004, "用户已存在"),
  ROLE_IS_EXITS(41000, "角色已存在"),
  ROLE_IS_NOT_EXITS(41001, "角色不存在"),
  ROLE_REPEAT_OPERATION(41002, "重复操作"),
  ROLE_ADD_ERROR(41002, "插入失败"),

  MENU_NAME_IS_EXITS(42001, "名称重复，请重试"),
  MENU_ID_ERROR(42002, "标签不存在"),
  MENU_PID_ERROR(42003, "参数异常"),
  MENU_REPEAT_OPERATION(42004, "重复操作"),

  AUTH_IS_NOT_EXITS(43001, "抱歉，不允许访问"),

  NULL_ENTITY(50000, "抱歉，找不到任何信息"),


  NULL_EXCEPTION(0, "发生错误，请重试");
  private final Integer code;
  private final String message;
}
