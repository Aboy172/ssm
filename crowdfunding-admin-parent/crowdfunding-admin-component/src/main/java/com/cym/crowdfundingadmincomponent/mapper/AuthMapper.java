package com.cym.crowdfundingadmincomponent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cym.crowdfundingadminentity.entity.pojo.Auth;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 86152
 * @description 针对表【t_auth】的数据库操作Mapper
 * @createDate 2022-04-07 09:02:50
 * @Entity com.cym.crowdfundingadmincomponent.pojo.Auth
 */
@Repository
public interface AuthMapper extends BaseMapper<Auth> {

  List<Integer> selectAssignedAuthIdByRoleId(@Param("roleId") Integer roleId);


  List<String> selectAssignedAuthNameByAdminId(@Param("adminId") Integer adminId);
}




