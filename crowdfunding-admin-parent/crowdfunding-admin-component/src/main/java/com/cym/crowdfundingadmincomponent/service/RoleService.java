package com.cym.crowdfundingadmincomponent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cym.crowdfundingadmincomponent.pojo.Role;

import java.util.List;
import java.util.Map;

/**
 * @author 86152
 * @description 针对表【t_role】的数据库操作Service
 * @createDate 2022-04-04 13:46:49
 */
public interface RoleService extends IService<Role> {

    /**
     * 获取分页信息，根据指定关键词返回相关信息并分页
     *
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    Page<Role> getPage (Integer pageNum,Integer pageSize,String keyword);

    /**
     * 新增角色
     *
     * @param roleName
     * @return
     */
    int addRole (String roleName);

    /**
     * 修改角色
     *
     * @param roleId
     * @param roleName
     */
    int editRole (Integer roleId,String roleName);


    /**
     * 批量删除角色
     *
     * @param roleIdList
     * @return
     */
    int removeRoles (List<Integer> roleIdList);

    /**
     * 查询已分配角色
     *
     * @param adminId
     * @return
     */
    List<Role> getAssignedRole (Integer adminId);

    /**
     * 查询未分配的角色
     *
     * @param adminId
     * @return
     */
    List<Role> getUnAssignedRole (Integer adminId);

    /**
     * 保存分配给角色的资源
     * @param map
     * @return
     */
    int saveAdminRoleRelationship (Map<String, List<Integer>> map);


}
