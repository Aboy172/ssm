package com.cym.atcrowdfunding03admincomponent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cym.atcrowdfunding03admincomponent.mapper.AuthMapper;
import com.cym.atcrowdfunding03admincomponent.mapper.RoleMapper;
import com.cym.atcrowdfunding03admincomponent.pojo.Auth;
import com.cym.atcrowdfunding03admincomponent.pojo.Role;
import com.cym.atcrowdfunding03admincomponent.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 86152
 * @description 针对表【t_auth】的数据库操作Service实现
 * @createDate 2022-04-07 09:02:50
 */
@Service
@Slf4j
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth>
        implements AuthService {


    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取所有权限功能
     *
     * @return 成功返回权限的集合
     */
    @Override
    public List<Auth> getAllAuth ( ) {
        return authMapper.selectList(null);

    }

    /**
     * 获取已分配权限的资源
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> getAssignedAuthIdByRoleId (Integer roleId) {
        List<Integer> list = authMapper.selectAssignedAuthIdByRoleId(roleId);
        return list;
    }

    /**
     * 根据adminId获取可以操作的资源名称
     * @param adminId
     * @return
     */
    @Override
    public List<String> getAssignedAuthNameByAdminId (Integer adminId) {
        if (adminId == null) {
            return  null;
        }
        return authMapper.selectAssignedAuthNameByAdminId( adminId);
    }

}




