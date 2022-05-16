package com.cym.atcrowdfunding03admincomponent.handler;

import com.cym.atcrowdfunding03admincomponent.pojo.Auth;
import com.cym.atcrowdfunding03admincomponent.pojo.Role;
import com.cym.atcrowdfunding03admincomponent.service.AdminService;
import com.cym.atcrowdfunding03admincomponent.service.AuthService;
import com.cym.atcrowdfunding03admincomponent.service.RoleService;
import com.cym.atcrowdfunding05commonutil.utils.CrowdConstant;
import com.cym.atcrowdfunding05commonutil.vo.RespBean;
import com.cym.atcrowdfunding05commonutil.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * program: ssm
 * Date: 2022-04-06  20:24
 * Author: cym
 * Description:
 */
@Controller
public class AssignHandler {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @GetMapping("/assign/to/assign/role/page.html")
    public String toAssignRolePage (@RequestParam("adminId") Integer adminId,
                                    @RequestParam("pageNum") Integer pageNum,
                                    @RequestParam("keyword") String keyword,
                                    Model model) {
        //查询已分配角色
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
        //查询未分配角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);
        model.addAttribute(CrowdConstant.ATTR_ADMIN_ID,adminId);
        model.addAttribute(CrowdConstant.ATTR_PAGENUM,pageNum);
        model.addAttribute(CrowdConstant.ATTR_KEYWORD,keyword);
        model.addAttribute(CrowdConstant.ATTR_ASSIGN_ROLE,assignedRoleList);
        model.addAttribute(CrowdConstant.ATTR_UN_ASSIGN_ROLE,unAssignedRoleList);

        return "assign-role";

    }

    @PostMapping("/assign/do/role/assign.html")
    public String saveAdminRoleRelationship (@RequestParam("adminId") Integer adminId,
                                             @RequestParam("pageNum") Integer pageNum,
                                             @RequestParam("keyword") String keyword,
                                             @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList) {
        adminService.saveAdminRoleRelationship(adminId,roleIdList);
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }


    @PostMapping("/assgin/get/all/auth.json")
    @ResponseBody
    public RespBean getAllAuth ( ) {
        List<Auth> authList = authService.getAllAuth();
        if (authList == null) {
            return RespBean.error(RespBeanEnum.NULL_ENTITY);
        }
        return RespBean.success(authList);
    }

    @PostMapping("/assign/get/assigned/auth/id/by/role/id.json")
    @ResponseBody
    public RespBean getAssignedAuthIdByRoleId (@RequestParam("roleId") Integer roleId) {
        if (roleId == null) {
            return RespBean.error(RespBeanEnum.ROLE_IS_NOT_EXITS);
        }
        //根据roleId获取对应的资源
        List<Integer> roleIdList = authService.getAssignedAuthIdByRoleId(roleId);
        if (Objects.isNull(roleIdList)) {
            return RespBean.error(RespBeanEnum.AUTH_IS_NOT_EXITS);
        }
        return RespBean.success(roleIdList);
    }
    @PostMapping("/assign/do/role/assign/auth.json")
    @ResponseBody
    public RespBean saveAdminRoleRelationship(@RequestBody Map<String,List<Integer>> map){
        int result = roleService.saveAdminRoleRelationship(map);
        if (result == 0){
            return RespBean.error(RespBeanEnum.ERROR);
        }
        return RespBean.success();
    }
}
