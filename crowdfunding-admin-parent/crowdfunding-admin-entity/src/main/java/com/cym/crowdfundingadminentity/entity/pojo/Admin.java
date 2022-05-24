package com.cym.crowdfundingadminentity.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员信息表
 * @author 86152
 * @TableName t_admin
 */
@TableName(value ="t_admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    /**
     * 主键
     */
    @TableId(value="id")
    private Integer id;

    /**
     * 登录账号
     */
    private String loginAcct;

    /**
     * 登录密码
     */
    private String userPswd;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 乐观锁版本号
     */
    @Version
    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}