package com.cym.crowdfundingadmincomponent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cym.crowdfundingadminentity.entity.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 86152
 * @description 针对表【t_menu】的数据库操作Mapper
 * @createDate 2022-04-05 21:39:14
 * @Entity com.cym.crowdfundingadmincomponent.pojo.Menu
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}




