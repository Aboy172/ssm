package com.cym.crowdfundingadmincomponent.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.cym.crowdfundingadmincomponent.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 86152
* @description 针对表【t_role】的数据库操作Mapper
* @createDate 2022-04-04 13:46:49
* @Entity com.cym.crowdfundingadmincomponent.pojo.Role
*/

@Repository
public interface RoleMapper extends BaseMapper<Role> {

    Role selectOneByName (@Param("name") String name);


    int insertSelective (Role role);

    List<Role> selectAssignedById (@Param("id") Integer id);

    List<Role> selectUnAssignById ( @Param("id") Integer id);

    int saveAdminRoleRelationship (@Param("roleId")Integer roleId,@Param("authIdArray")List<Integer> authIdArray);

    void deleteInnerRoleAuthId (Integer roleId);
}




