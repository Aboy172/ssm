package com.cym.crowd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cym.crowd.entity.po.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author cym
 * @description 针对表【t_member】的数据库操作Mapper
 * @createDate 2022-05-17 15:07:53
 * @Entity com.cym.crowd.entity.po.Member
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

  /**
   * 插入数据
   *
   * @param member
   * @return
   */
  int insertSelective(Member member);

  /**
   * 根据用户名查询数据库中会员的详细信息
   *
   * @param loginacct 登录账号
   * @return 返回会员用户详细信息
   */
  Member selectByLoginacct(@Param("loginacct") String loginacct);
}




