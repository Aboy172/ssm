package com.cym.crowdfundingadmincomponent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cym.crowdfundingadminentity.entity.pojo.Menu;

/**
 * @author 86152
 * @description 针对表【t_menu】的数据库操作Service
 * @createDate 2022-04-05 21:39:14
 */
public interface MenuService extends IService<Menu> {

  /**
   * 获取根节点
   *
   * @return
   */
  Menu getRootMenu();

  /**
   * 保存节点
   *
   * @param menu
   * @return
   */
  int saveMenu(Menu menu);

  /**
   * 修改节点
   *
   * @param id
   * @param name
   * @param url
   * @param icon
   * @return
   */
  int editMenu(Integer id, String name, String url, String icon);

  /**
   * 根据id删除指定的节点信息
   *
   * @param id
   * @return
   */
  int deleteMenuById(Integer id);
}
