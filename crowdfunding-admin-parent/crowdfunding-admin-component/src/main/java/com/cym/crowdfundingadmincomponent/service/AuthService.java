package com.cym.crowdfundingadmincomponent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cym.crowdfundingadminentity.entity.pojo.Auth;
import java.util.List;

/**
 * @author 86152
 * @description 针对表【t_auth】的数据库操作Service
 * @createDate 2022-04-07 09:02:50
 */
public interface AuthService extends IService<Auth> {

  /**
   * 获取所有的权限
   *
   * @return 成功返回权限集合
   */
  List<Auth> getAllAuth();

  /**
   * 根据roleId获取角色对应的资源
   *
   * @param roleId
   * @return
   */
  List<Integer> getAssignedAuthIdByRoleId(Integer roleId);


  List<String> getAssignedAuthNameByAdminId(Integer adminId);

}
