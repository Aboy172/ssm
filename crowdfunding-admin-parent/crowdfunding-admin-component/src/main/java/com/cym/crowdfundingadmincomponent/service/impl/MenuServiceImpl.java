package com.cym.crowdfundingadmincomponent.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cym.crowdfundingadmincomponent.exception.GlobalException;
import com.cym.crowdfundingadmincomponent.mapper.MenuMapper;
import com.cym.crowdfundingadmincomponent.service.MenuService;
import com.cym.crowdfundingadminentity.entity.pojo.Menu;
import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author 86152
 * @description 针对表【t_menu】的数据库操作Service实现
 * @createDate 2022-04-05 21:39:14
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取所有的根节点,并以树状结构返回
     *
     * @return
     */
    @Override
    public Menu getRootMenu ( ) {
        List<Menu> menuList = menuMapper.selectList(null);
        Map<Integer, Menu> menuMap = new HashMap<>(menuList.size());
        Menu root = null;
        //用map集合管理id和menu之间的关系
        for (Menu menu : menuList) {
            Integer id = menu.getId();
            menuMap.put(id,menu);
        }
        for (Menu menu : menuList) {
            Integer pid = menu.getPid();
            if (ObjectUtils.isEmpty(pid)) {
                //如果pid为空，说明当前就是根节点，直接进入下一次循环
                root = menu;
                continue;
            }
            //如果pid不为空说明有根节点，那么就可以根据pid获取到父节点，并生成menu对象，存入到children中
            Menu father = menuMap.get(pid);
            father.getChildren().add(menu);
        }
        return root;
    }

    /**
     * 保存节点功能
     *
     * @param menu
     * @return
     */
    @Override
    public int saveMenu (Menu menu) {
        //健壮性判断
        if (StringUtils.isEmpty(menu.getIcon()) || StringUtils.isEmpty(menu.getName())
                || StringUtils.isEmpty(menu.getUrl())) {
            throw new GlobalException(RespBeanEnum.ADMIN_INFO_IS_NULL);
        }
        if (menu.getPid() < 0) {
            throw new GlobalException(RespBeanEnum.MENU_PID_ERROR);
        }
        //获取所有节点信息
        List<Menu> menuList = menuMapper.selectList(null);
        AtomicInteger result = new AtomicInteger();
        menuList.forEach(item -> {
            //如果传递的名字和数据库中名字相同，抛出名称相同异常
            if (Objects.equals(item.getName(),menu.getName())) {
                throw new GlobalException(RespBeanEnum.MENU_NAME_IS_EXITS);
            }
            //如果分支节点跟所有叶子节点都不同，说明是违法操作，直接抛出标签不存在异常
            if (Objects.equals(item.getId(),menu.getPid())) {
                result.set(menuMapper.insert(menu));
            }
        });
        return result.get();
    }

    /**
     * 修改节点信息功能
     *
     * @param id
     * @param name
     * @param url
     * @param icon
     * @return
     */
    @Override
    public int editMenu (Integer id,String name,String url,String icon) {
        //健壮性判断
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(url) || StringUtils.isEmpty(icon)) {
            throw new GlobalException(RespBeanEnum.ADMIN_INFO_IS_NULL);
        }
        //查询当前节点信息
        Menu menu = menuMapper.selectById(id);
        //查不到返回标签不存在
        Optional.ofNullable(menu).orElseThrow(( ) -> {
            throw new GlobalException(RespBeanEnum.MENU_ID_ERROR);
        });
        //如果前端传过来的name值跟数据库中name字段的值相同，说明重复操作，返回重复操作
        if (Objects.equals(menu.getName(),name)) {
            throw new GlobalException(RespBeanEnum.MENU_REPEAT_OPERATION);
        }
        //执行修改方法
        menu.setName(name);
        menu.setUrl(url);
        menu.setIcon(icon);
        int resultEdit;
        resultEdit = menuMapper.updateById(menu);
        //乐观锁，如果更改失败，再次获取数据，并执行修改方法，以防被后面的人覆盖了前面的人修改的数据
        if (resultEdit == 0) {
            menu = menuMapper.selectById(id);
            menu.setName(name);
            menu.setUrl(url);
            menu.setIcon(icon);
            resultEdit = menuMapper.updateById(menu);
        }
        //返回结果
        return resultEdit;
    }

    /**
     * 根据节点删除指定节点信息功能
     *
     * @param id
     * @return
     */
    @Override
    public int deleteMenuById (Integer id) {
        //健壮性判断
        if (ObjectUtils.isEmpty(id)) {
            throw new GlobalException(RespBeanEnum.ADMIN_INFO_IS_NULL);
        }
        //根据id获取信息
        Menu menu = menuMapper.selectById(id);
        //如果获取不到，抛出参数异常
        Optional.ofNullable(menu).orElseThrow(( ) -> {
            throw new GlobalException(RespBeanEnum.MENU_PID_ERROR);
        });
        //执行删除方法，并返回结果
        return menuMapper.deleteById(id);
    }

}




