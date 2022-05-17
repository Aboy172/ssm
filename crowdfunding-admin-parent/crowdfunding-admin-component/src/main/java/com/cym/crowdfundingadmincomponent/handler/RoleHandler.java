package com.cym.crowdfundingadmincomponent.handler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cym.crowdfundingadmincomponent.exception.GlobalException;
import com.cym.crowdfundingadmincomponent.pojo.Role;
import com.cym.crowdfundingadmincomponent.service.RoleService;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * program: ssm
 * Date: 2022-04-04  14:12
 * Author: cym
 * Description:
 * @author 86152
 */

@Controller
public class RoleHandler {

    @GetMapping("/role/to/page.html")
    public String roleToPage(){
        return "role-page";
    }

    @Autowired
    private RoleService roleService;
    @PreAuthorize("hasRole('老板')")
    @PostMapping("/role/get/page.html")
    @ResponseBody
    public RespBean roleGetPage (@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                 @RequestParam(value = "keyword", defaultValue = "") String keyword,Model model) {

        Page<Role> page = roleService.getPage(pageNum,pageSize,keyword);
        if (page == null) {
            throw new GlobalException(RespBeanEnum.NULL_ENTITY);
        }
        return RespBean.success(page);
    }
    @PreAuthorize("hasAuthority('role:add')")
    @PostMapping("/role/to/add.json")
    @ResponseBody
    public RespBean roleAdd (@RequestParam("roleName") String roleName) {
        int result = roleService.addRole(roleName);
        if (result == 0){
            return RespBean.error(RespBeanEnum.ERROR);
        }
        return RespBean.success();
    }

    @PostMapping("/role/to/edit.json")
    @ResponseBody
    public  RespBean roleEdit(@RequestParam("roleId")Integer roleId,
                              @RequestParam("roleName")String roleName){
        int result = roleService.editRole(roleId,roleName);
        if (result == 0){
            return  RespBean.error(RespBeanEnum.ERROR);
        }
        return RespBean.success();
    }
    @PostMapping("/role/to/remove.json")
    @ResponseBody
    public RespBean roleRemoves(@RequestBody List<Integer> roleIdList){

        //执行批量删除
        int result = roleService.removeRoles(roleIdList);
        if (result == 0){
            return RespBean.error(RespBeanEnum.ERROR);
        }
        return  RespBean.success();
    }


}
