package com.cym.crowdfundingadmincomponent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cym.crowdfundingadmincomponent.exception.AdminExitsAddException;
import com.cym.crowdfundingadmincomponent.exception.AdminExitsUpdateException;
import com.cym.crowdfundingadmincomponent.mapper.AdminMapper;
import com.cym.crowdfundingadmincomponent.mapper.RoleMapper;
import com.cym.crowdfundingadmincomponent.service.AdminService;
import com.cym.crowdfundingadminentity.entity.pojo.Admin;
import com.cym.crowdfundingadminentity.entity.pojo.Role;
import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import freemarker.template.utility.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;

/**
 * @author 86152
 * @description 针对表【t_admin(管理员信息表)】的数据库操作Service实现
 * @createDate 2022-03-30 11:36:44
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService {

  @Autowired
  private AdminMapper adminMapper;
  @Autowired
  private RoleMapper roleMapper;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;


  /**
   * 新增用户，并对密码进行加密
   *
   * @param loginAcct 登录账号
   * @param userName  用户名
   * @param userPswd  登录密码
   * @param email     邮箱地址
   * @return 返回结果
   */
  @Override
  public int saveAdmin(String loginAcct, String userName, String userPswd, String email) {
    int resultInsert;
    if (Strings.isEmpty(loginAcct) || Strings.isEmpty(userPswd) || Strings.isEmpty(userPswd)) {
      throw new AdminExitsAddException(RespBeanEnum.ADMIN_INFO_IS_NULL);
    }
    QueryWrapper<Admin> wrapper = new QueryWrapper<>();
    //查询用户是否已注册
    wrapper.select("login_acct");
    List<Admin> list = adminMapper.selectList(wrapper);
    list.forEach(item -> {
      //如果已经注册抛出异常
      if (Objects.equals(item.getLoginAcct(), loginAcct)) {
        throw new AdminExitsAddException(RespBeanEnum.ADMIN_EXIST);
      }
    });
    //使用security带盐值的加密方式
    userPswd = passwordEncoder.encode(userPswd);
    //生成创建时间
    SimpleDateFormat sdf = new SimpleDateFormat();
    sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
    String date = sdf.format(new Date());
    //存入admin对象中
    Admin admin = new Admin();
    admin.setLoginAcct(loginAcct);
    admin.setUserPswd(userPswd);
    admin.setUserName(userName);
    admin.setEmail(email);
    admin.setCreateTime(date);
    resultInsert = adminMapper.insertSelective(admin);
    return resultInsert;
  }

  /**
   * 用户登录验证
   *
   * @param loginAcct 登录账号
   * @param userPswd  登录密码
   */

  @Override
  public Admin getAdminByLoginAcctAndUserPswd(String loginAcct, String userPswd) {
    //将传入的密码进行二次MD5加密
    userPswd = passwordEncoder.encode(userPswd);
    //根据加密的密文查找管理员信息
    return adminMapper.selectOneByLoginAcctAndUserPswd(loginAcct, userPswd);
  }

  /**
   * 数据分页
   *
   * @param keyword
   * @param pageNum
   * @param pageSize
   * @return
   */
  @Override
  public Page<Admin> getPage(String keyword, Integer pageNum, Integer pageSize) {
    LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
    wrapper.like(Admin::getLoginAcct, keyword).or().like(Admin::getUserName, keyword).or()
        .like(Admin::getEmail, keyword);
    Page<Admin> page = new Page<>(pageNum, pageSize);
    return adminMapper.selectPage(page, wrapper);

  }

  /**
   * 删除
   *
   * @param id
   * @return
   */
  @Override
  public int removeAdminId(Integer id) {
    return adminMapper.deleteById(id);
  }

  /**
   * 修改用户信息，将加密的密码存入数据库
   *
   * @param adminId
   * @param loginAcct
   * @param userName
   * @param userPswd
   * @param email
   * @return
   */
  @Override
  public int updateByAdmin(Integer adminId, String loginAcct, String userName, String userPswd,
      String email) {
    if (Strings.isEmpty(loginAcct) || Strings.isEmpty(userPswd) || Strings.isEmpty(userPswd)) {
      throw new AdminExitsUpdateException(RespBeanEnum.ADMIN_INFO_IS_NULL);
    }
    //如果用户已存在，不执行更改方法,抛出异常，否则执行更改方法
    keepLoginAcct(adminId, loginAcct);
    //查询当前用户信息
    Admin admin;
    admin = adminMapper.selectById(adminId);
    //使用security带盐值的加密方式
    userPswd = passwordEncoder.encode(userPswd);
    //存入admin对象中
    admin.setId(adminId);
    admin.setLoginAcct(loginAcct);
    admin.setUserPswd(userPswd);
    admin.setUserName(userName);
    admin.setEmail(email);
    int updateResult = adminMapper.updateById(admin);
    if (updateResult == 0) {
      //重新获取数据，执行之前的操作
      admin = adminMapper.selectById(adminId);
      //对密码进行二次MD5加密，执行新增方法
      userPswd = passwordEncoder.encode(userPswd);
      //存入admin对象中
      admin.setId(adminId);
      admin.setLoginAcct(loginAcct);
      admin.setUserPswd(userPswd);
      admin.setUserName(userName);
      admin.setEmail(email);
      updateResult = adminMapper.updateById(admin);
    }
    return updateResult;
  }

  /**
   * 保存分配给用户的角色功能
   *
   * @param adminId
   * @param roleIdList
   * @return
   */
  @Override
  public int saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {
    int saveResult = 0;
    //健壮性判断
    if (ObjectUtils.isEmpty(adminId)) {
      return saveResult;
    }
    //对集合中的每个角色进行检查，确保id不为null
    if (CollectionUtils.isEmpty(roleIdList)) {
      saveResult = 1;
      return saveResult;
    }
    for (Integer roleId : roleIdList) {
      if (ObjectUtils.isEmpty(roleId)) {
        return saveResult;
      }
      //获取当前的角色信息
      Role role = roleMapper.selectById(roleId);
      log.info("Role " + role);
      //找不到说明角色不存在,直接返回
      if (ObjectUtils.isEmpty(null)) {
        return saveResult;
      }
    }
    //清空adminId的角色信息
    adminMapper.deleteByAdminId(adminId);
    //执行新增分配角色功能
    saveResult = adminMapper.insertNewRelationship(adminId, roleIdList);

    return saveResult;
  }

  /**
   * 根据adminId获取当前用户信息
   *
   * @param loginAcct
   * @return
   */
  @Override
  public Admin getLoginAcctByLoginAcct(String loginAcct) {
    if (StringUtils.isEmpty(loginAcct)) {
      return null;
    }
    return adminMapper.selectOneByLoginAcct(loginAcct);
  }

  /**
   * 保持用户名唯一
   *
   * @param adminId
   * @param loginAcct
   * @return
   */
  private void keepLoginAcct(Integer adminId, String loginAcct) {

    //查询当前用户的用户信息
    Admin admin = adminMapper.selectOneByLoginAcct(loginAcct);
    ///如果传入的用户名在数据库中没有，保持唯一，可以更改，直接放行，如果一致，返回用户已存在异常
    //但此时有个问题：本身的用户名也会被锁定，如果用户不对用户名作更改，传入的用户名和数据库中的用户名会一致，然后抛出异常，这是不可以的
    //解决思路：用当前用户的id，去跟根据用户名查询到的id进行比较，如果不作更改，比较的值默认true，如果用了别人的用户名肯定是false
    if (ObjectUtils.isEmpty(admin)) {
      return;
    }
    if (!Objects.equals(adminId, admin.getId())) {
      throw new AdminExitsUpdateException(RespBeanEnum.ADMIN_EXIST);
    }
  }


}




