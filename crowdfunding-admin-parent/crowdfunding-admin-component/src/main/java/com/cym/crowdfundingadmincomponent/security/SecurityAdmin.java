package com.cym.crowdfundingadmincomponent.security;


import com.cym.crowdfundingadmincomponent.pojo.Admin;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * program: ssm
 * Date: 2022-04-09  23:33
 * Author: cym
 * Description: 考虑User对象中只有用户名和密码，所以对User进行扩展，方便后续的使用
 * @author 86152
 */

@Setter
@Getter
public class SecurityAdmin extends User {

    /**
     *  因为继承的User类中实现的接口继承了序列化接口，所以需要进行serialVersionUID的指定
     */
    private static final long serialVersionUID = 1L;

    private Admin originalAdmin;

    public SecurityAdmin (Admin originalAdmin,List<GrantedAuthority> authorities) {
        super(originalAdmin.getUserName(),originalAdmin.getUserPswd(),authorities);
        this.originalAdmin = originalAdmin;
    }


}
