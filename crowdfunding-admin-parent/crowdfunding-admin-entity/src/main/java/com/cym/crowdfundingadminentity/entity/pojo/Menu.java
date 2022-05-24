package com.cym.crowdfundingadminentity.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 
 * @author 86152
 * @TableName t_menu
 */
@TableName(value ="t_menu")
@Data
public class Menu implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer pid;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private String icon;


    @TableField(exist = false)
    private List<Menu> children = new ArrayList<>();
    @TableField(exist = false)
    private boolean open = true;

    @Version
    private Integer version;

    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}