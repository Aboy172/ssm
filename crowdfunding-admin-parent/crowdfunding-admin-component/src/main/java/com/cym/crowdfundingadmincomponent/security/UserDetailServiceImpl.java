package com.cym.crowdfundingadmincomponent.security;

import com.cym.crowdfundingadmincomponent.pojo.Admin;
import com.cym.crowdfundingadmincomponent.pojo.Role;
import com.cym.crowdfundingadmincomponent.service.AdminService;
import com.cym.crowdfundingadmincomponent.service.AuthService;
import com.cym.crowdfundingadmincomponent.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * program: ssm
 * Date: 2022-04-10  00:14
 * Author: cym
 * Description:
 * @author 86152
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;


    @Override
    public UserDetails loadUserByUsername (String loginAcct) throws UsernameNotFoundException {
        
        //根据用户名查询当前用户信息
        Admin admin = adminService.getLoginAcctByLoginAcct(loginAcct);
        //获取当前用户的id
        Integer adminId = admin.getId();
        //根据adminId获取对应的角色
        List<Role> assignedRole = roleService.getAssignedRole(adminId);
        List<GrantedAuthority> authorities = new ArrayList<>();
        //给每个角色名加上前缀ROLE_k,存放到authorities中
        assignedRole.forEach(role -> {
            String roleName=  "ROLE_"  +role.getName();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(simpleGrantedAuthority);

        });
        //将每个权限名称存放到authorities中
        List<String> authNameList = authService.getAssignedAuthNameByAdminId(adminId);
        authNameList.forEach(authName ->{
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authName);
            authorities.add(simpleGrantedAuthority);
        });
        //将SecurityAdmin对象返回
        return new SecurityAdmin(admin,authorities);


    }
}
