package com.cym.crowdfundingadmincomponent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cym.crowdfundingadminentity.entity.pojo.Admin;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 86152
 * @description 针对表【t_admin(管理员信息表)】的数据库操作Mapper
 * @createDate 2022-03-30 11:36:44
 * @Entity com.cym.crowdfundingadmincomponent.pojo.Admin
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

  /**
   * 新增用户
   *
   * @param admin
   * @return
   */
  int insertSelective(Admin admin);


  /**
   * 根据管理员名字和密码查询管理员信息
   *
   * @param loginAcct
   * @param userPswd
   * @return
   */
  Admin selectOneByLoginAcctAndUserPswd(@Param("loginAcct") String loginAcct,
      @Param("userPswd") String userPswd);

  /**
   * 根据登录账号、用户名、邮箱查询用户
   *
   * @param loginAcct
   * @param userName
   * @param email
   * @return
   */
  List<Admin> selectByLoginAcctAndUserNameAndEmail(@Param("loginAcct") String loginAcct,
      @Param("userName") String userName, @Param("email") String email);

  /**
   * 修改用户
   *
   * @param admin
   * @return
   */
  int updateAdmin(Admin admin);

  /**
   * 根据用户名查询所有用户信息
   *
   * @param loginAcct
   * @return
   */
  List<Admin> selectAllByLoginAcct(@Param("loginAcct") String loginAcct);

  /**
   * 根据用户名查询当前用户信息
   *
   * @param loginAcct
   * @return
   */
  Admin selectOneByLoginAcct(@Param("loginAcct") String loginAcct);

  /**
   * 根据主键id删除用户信息
   *
   * @param adminId
   * @return
   */
  int deleteByAdminId(@Param("adminId") Integer adminId);

  /**
   * 往中间表插入用户对应的权限关系
   *
   * @param adminId
   * @param roleIdList
   * @return
   */
  int insertNewRelationship(@Param("adminId") Integer adminId,
      @Param("roleIdList") List<Integer> roleIdList);

}




