package com.cym.crowdfundingadmincomponent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cym.crowdfundingadmincomponent.exception.GlobalException;
import com.cym.crowdfundingadmincomponent.mapper.RoleMapper;
import com.cym.crowdfundingadmincomponent.pojo.Role;
import com.cym.crowdfundingadmincomponent.service.RoleService;
import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 86152
 * @description 针对表【t_role】的数据库操作Service实现
 * @createDate 2022-04-04 13:46:49
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    /**
     * 分页功能
     *
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    @Override
    public Page<Role> getPage (Integer pageNum,Integer pageSize,String keyword) {
        //构建条件
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        //查询条件字段name中有关键词的信息，并进行分页
        wrapper.like("name",keyword);
        //封装成Page对象返回
        Page<Role> page = new Page<>(pageNum,pageSize);
        return roleMapper.selectPage(page,wrapper);
    }

    /**
     * 新增角色功能
     *
     * @param roleName
     * @return
     */
    @Override
    public int addRole (String roleName) {
        if (StringUtils.isEmpty(roleName)) {
            throw new GlobalException(RespBeanEnum.ADMIN_INFO_IS_NULL);
        }
        //遍历数据库中的角色名，如果角色名重复，则抛出异常
        List<Role> roleList = roleMapper.selectList(null);
        roleList.forEach(item -> {
            if (Objects.equals(roleName,item.getName())) {
                throw new GlobalException(RespBeanEnum.ROLE_IS_EXITS);
            }
        });
        Role role = new Role();
        role.setName(roleName);
        return roleMapper.insertSelective(role);
    }

    /**
     * 修改角色功能
     *
     * @param roleId
     * @param roleName
     * @return
     */
    @Override
    public int editRole (Integer roleId,String roleName) {
        keepRoleName(roleId,roleName);
        //执行修改方法
        Role role = roleMapper.selectById(roleId);
        role.setName(roleName);
        int resultUpdate = roleMapper.updateById(role);
        //如果修改失败，根据版本号再次执行修改方法
        if (resultUpdate == 0) {
            role = roleMapper.selectById(roleId);
            role.setName(roleName);
            resultUpdate = roleMapper.updateById(role);
        }
        return resultUpdate;
    }


    /**
     * 批量删除角色功能
     *
     * @param roleIdList
     * @return
     */
    @Override
    public int removeRoles (List<Integer> roleIdList) {
        //迭代，如果用户不存在返回用户不存在异常
        roleIdList.forEach(item -> {
            Role role = roleMapper.selectById(item);
            if (role == null) {
                throw new GlobalException(RespBeanEnum.ROLE_IS_NOT_EXITS);
            }

        });
        //返回执行结果
        return roleMapper.deleteBatchIds(roleIdList);
    }

    /**
     * 获取已分配角色功能
     *
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getAssignedRole (Integer adminId) {

        return roleMapper.selectAssignedById(adminId);
    }

    /**
     * 获取未分配角色功能
     *
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getUnAssignedRole (Integer adminId) {
        return roleMapper.selectUnAssignById(adminId);
    }


    /**
     * 保存分配角色的资源
     * @param map
     * @return
     */
    @Override
    public int saveAdminRoleRelationship (Map<String, List<Integer>> map) {
        List<Integer> authIdArray = map.get("authIdArray");
        List<Integer> roleIdList = map.get("roleId");
        //如果roleId为0，说明角色不存在
        if(roleIdList.size() == 0){
            throw new GlobalException(RespBeanEnum.ROLE_IS_NOT_EXITS);
        }
        //如果authIdArray为0，说明没有做任何操作，直接返回
        if(authIdArray.size() ==0){
            return  1;
        }
        Integer roleId = roleIdList.get(0);

        //批量删除
        roleMapper.deleteInnerRoleAuthId(roleId);
        return roleMapper.saveAdminRoleRelationship(roleId,authIdArray);


    }


    /**
     * 保持角色名唯一
     *
     * @param roleId
     * @param roleName
     */
    private void keepRoleName (Integer roleId,String roleName) {
        Role role = roleMapper.selectOneByName(roleName);
        //如果为空说明角色名不存在，直接退出
        if (role == null) {
            return;
        }
        //如果不是空，去查询是否跟其他角色id相同，如果roleId和查询出来的roleId不同说明角色重复，抛出角色重复异常
        if (!Objects.equals(role.getId(),roleId)) {
            throw new GlobalException(RespBeanEnum.ROLE_IS_EXITS);
        }
        if (Objects.equals(role.getName(),roleName)) {
            throw new GlobalException(RespBeanEnum.ROLE_REPEAT_OPERATION);
        }
    }
}




