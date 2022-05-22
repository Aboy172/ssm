package com.cym.crowd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cym.crowd.entity.po.Member;
import com.cym.crowd.mapper.MemberMapper;
import com.cym.crowd.service.MemberService;
import com.cym.crowdfundingcommonutil.exception.MemberException;
import com.cym.crowdfundingcommonutil.vo.error.MemberFailCode;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cym
 * @description 针对表【t_member】的数据库操作Service实现
 * @createDate 2022-05-17 15:07:53
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService {

  @Resource
  private MemberMapper memberMapper;

  /**
   * @param loginacct 登录账号
   * @return 返回会员用户详细信息
   */
  @Override
  @Transactional(readOnly = true)
  public Member getMemberByLoginAcct(String loginacct) {
    //健壮性判断
    if (loginacct == null) {
      return null;
    }
    //从数据库中查询数据
    Member member = memberMapper.selectByLoginacct(loginacct);
    //如果查不到，返回查不到会员信息
    if (member == null) {
      throw new MemberException(MemberFailCode.NULL_ERROR_MEMBERS);
    }
    return member;
  }
}




