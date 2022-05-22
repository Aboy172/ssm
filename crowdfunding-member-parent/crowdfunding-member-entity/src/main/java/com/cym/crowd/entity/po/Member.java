package com.cym.crowd.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cym
 * @TableName t_member
 */
@TableName(value = "t_member")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member implements Serializable {

  /**
   *
   */
  @TableId(type = IdType.AUTO)
  private Integer id;

  /**
   *
   */
  private String loginacct;

  /**
   *
   */
  private String userpswd;

  /**
   *
   */
  private String username;

  /**
   *
   */
  private String email;

  /**
   * 实名认证状态 0 - 未实名认证， 1 - 实名认证申 请中， 2 - 已实名认证
   */
  private Integer authstatus;

  /**
   * 0 - 个人， 1 - 企业
   */
  private Integer usertype;

  /**
   *
   */
  private String realname;

  /**
   *
   */
  private String cardnum;

  /**
   * 0 - 企业， 1 - 个体， 2 - 个人， 3 - 政府
   */
  private Integer accttype;

  @TableField(exist = false)
  private static final long serialVersionUID = 1L;


}