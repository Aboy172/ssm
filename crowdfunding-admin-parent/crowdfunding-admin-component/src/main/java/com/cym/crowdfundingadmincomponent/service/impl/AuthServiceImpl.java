package com.cym.crowdfundingadmincomponent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cym.crowdfundingadmincomponent.mapper.AuthMapper;
import com.cym.crowdfundingadmincomponent.mapper.RoleMapper;
import com.cym.crowdfundingadmincomponent.service.AuthService;
import com.cym.crowdfundingadminentity.entity.pojo.Auth;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public List<Auth> getAllAuth() {
    return authMapper.selectList(null);

  }

  /**
   * 获取已分配权限的资源
   *
   * @param roleId
   * @return
   */
  @Override
  public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {

    return authMapper.selectAssignedAuthIdByRoleId(roleId);
  }

  /**
   * 根据adminId获取可以操作的资源名称
   *
   * @param adminId
   * @return
   */
  @Override
  public List<String> getAssignedAuthNameByAdminId(Integer adminId) {
    if (adminId == null) {
      return null;
    }
    return authMapper.selectAssignedAuthNameByAdminId(adminId);
  }

}




