package com.cym.crowdfundingadmincomponent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cym.crowdfundingadmincomponent.pojo.Admin;

import java.util.List;

/**
 * @author 86152
 * @description 针对表【t_admin(管理员信息表)】的数据库操作Service
 * @createDate 2022-03-30 11:36:44
 */
public interface AdminService extends IService<Admin> {


    /**
     * 新增用户信息
     * @param loginAcct
     * @param userName
     * @param userPswd
     * @param email
     * @return
     */
    int saveAdmin (String loginAcct,String userName,String userPswd,String email);

    /**
     * 查询管理员的用户和密码
     *
     * @param loginAcct
     * @param userPswd
     */
    Admin getAdminByLoginAcctAndUserPswd (String loginAcct,String userPswd);

    /**
     * 获取分页数据，pageNum第几页，pageSize总页数
     *
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Admin> getPage (String keyword,Integer pageNum,Integer pageSize);

    /**
     * 根据id删除admin的用户信息
     * @param id
     */
    int removeAdminId (Integer id);

    /**
     * 修改用户信息
     *
     * @param adminId
     * @param loginAcct
     * @param userName
     * @param userPswd
     * @param email
     * @return
     */
    int updateByAdmin (Integer adminId,String loginAcct,String userName,String userPswd,String email);

    /**
     * 保存分配给用户的角色
     * @param adminId
     * @param roleIdList
     * @return
     */
    int saveAdminRoleRelationship (Integer adminId,List<Integer> roleIdList);

    /**
     * 根据用户名获取当前的用户信息
     * @param loginAcct
     * @return
     */
    Admin getLoginAcctByLoginAcct (String loginAcct);
}
