package com.cym.crowdfundingadmincomponent.handler;

/**
 * program: ssm
 * Date: 2022-04-05  21:44
 * Author: cym
 * Description:
 */

import com.cym.crowdfundingadmincomponent.exception.GlobalException;
import com.cym.crowdfundingadmincomponent.pojo.Menu;
import com.cym.crowdfundingadmincomponent.service.MenuService;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 86152
 */
@Controller
public class MenuHandler {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu/to/page.html")
    public String getMenuPage ( ) {
        return "menu-page";
    }


    @PostMapping("/menu/get/whole/tree.json")
    @ResponseBody
    public RespBean getWholeTree ( ) {
        Menu rootMenu = menuService.getRootMenu();
        if (rootMenu == null) {
            throw new GlobalException(RespBeanEnum.NULL_ENTITY);
        }
        return RespBean.success(rootMenu);
    }

    @PostMapping("/menu/save.json")
    @ResponseBody
    public RespBean savaMenu (Menu menu) {
        int result = menuService.saveMenu(menu);
        if (result == 0) {
            return RespBean.error(RespBeanEnum.ERROR);
        }
        return RespBean.success();
    }

    @PostMapping("/menu/edit.json")
    @ResponseBody
    public RespBean editMenu (@RequestParam("id") Integer id,
                              @RequestParam("name") String name,
                              @RequestParam("url") String url,
                              @RequestParam("icon") String icon
                              ) {
        //执行更新方法
        int result = menuService.editMenu(id,name,url,icon);
        //失败了抛全局异常
        if (result == 0){
            return RespBean.error(RespBeanEnum.ERROR);
        }
        return RespBean.success(result);
    }
    @PostMapping("/menu/delete.json")
    @ResponseBody
    public RespBean deleteMenu(@RequestParam("id") Integer id){
        int result = menuService.deleteMenuById(id);
        if (result == 0){
            return  RespBean.error(RespBeanEnum.ERROR);
        }
        return  RespBean.success(result);
    }

}
