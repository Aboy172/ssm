package com.cym.crowdfundingadmincomponent.handler;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cym.crowdfundingadmincomponent.service.AdminService;
import com.cym.crowdfundingadminentity.entity.Student;
import com.cym.crowdfundingadminentity.entity.pojo.Admin;
import com.cym.crowdfundingcommonutil.constant.CrowdConstant;
import com.cym.crowdfundingcommonutil.utils.CrowdUtils;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * program: ssm Date: 2022-03-29  12:48 Author: cym Description:
 *
 * @author 86152
 */
@Controller
@Slf4j
public class AdminHandler {

  @Autowired
  private AdminService adminService;


  @GetMapping("send.exception.html")
  public String index() {
    String a = null;
    System.out.println(a.length());
    return CrowdConstant.ATTR_NAME_SUCCESS;
  }


  /**
   * 对ajax和普通请求的全局异常处理
   *
   * @param array
   * @param request
   * @return
   */
  @PostMapping("/send.ajax.html")
  @ResponseBody
  public String test(@RequestBody List<Integer> array, HttpServletRequest request) {
    //获取当前请求类型
    boolean judgeRequest = CrowdUtils.judgeRequestType(
        request);
    log.info("judgeRequest = " + judgeRequest);
    for (Integer number : array) {
      System.out.println("number = " + number);
    }
    return CrowdConstant.ATTR_NAME_SUCCESS;
  }

  /**
   * 对浏览器端响应自定义的数据和获取浏览器发送的请求体内容
   *
   * @param student
   * @return
   */
  @PostMapping("/save/student")
  @ResponseBody
  public RespBean test(
      @RequestBody Student student) {
    if (student == null) {
      return RespBean.error(
          RespBeanEnum.NULL_ENTITY, student);
    }
    log.info(student.toString());
    return RespBean.success(student);
  }

  /**
   * 跳转到登录页面
   *
   * @param
   * @return
   */
  @RequestMapping("/admin-login.html")
  public String adminLogin() {
    return "admin-login";
  }

  /**
   * 跳转到 主页
   *
   * @return
   */
  @GetMapping("/admin-main.html")
  public String adminMain() {
    return "admin-main";
  }

  /**
   * 获取用户维护的分页信息
   *
   * @param keyword
   * @param pageNum
   * @param pageSize
   * @param model
   * @return
   */
  @PostAuthorize("hasRole('老板')")
  @RequestMapping("/admin/to/page.html")
  public String getPage(@RequestParam(value = "keyword", defaultValue = "") String keyword,
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
      Model model) {
    //获取分页数据
    Page<Admin> page = adminService.getPage(keyword, pageNum, pageSize);
    //将数据保存在request请求作用域中
    model.addAttribute(CrowdConstant.ATTR_PAGE_INFO, page);
    model.addAttribute(CrowdConstant.ATTR_KEYWORD,
        keyword);
    //log.info("page="+page);
    return "admin-page";

  }

  /**
   * 删除用户功能。成功返回到用户维护页面
   *
   * @param id
   * @return
   */
  @GetMapping("/admin/remove/{adminId}.html")
  public String adminRemove(@PathVariable("adminId") Integer id) {
    if (id == null) {
      return CrowdConstant.ATTR_ERROR;
    }
    //执行删除
    int result = adminService.removeAdminId(id);

    log.info("result = " + result);

    //重定向到admin-login页面
    return "redirect:/admin/get/page.html";
  }

  /**
   * 新增用户页面
   *
   * @return
   */
  @GetMapping("/admin-add.html")
  public String toAddAdmin() {
    return "admin-add";
  }

  /**
   * 新增用户功能，成功返回到用户维护页面
   *
   * @param loginAcct
   * @param userName
   * @param userPswd
   * @param email
   * @return
   */
  @PostMapping("/admin/add.html")
  public String addAdmin(@RequestParam("loginAcct") String loginAcct,
      @RequestParam("userName") String userName,
      @RequestParam("userPswd") String userPswd,
      @RequestParam("email") String email) {
    int adminResult = adminService.saveAdmin(loginAcct, userName, userPswd, email);
    log.info("adminResult" + adminResult);
    return "redirect:/admin/get/page.html";
  }

  /**
   * 跳转到修改用户页面
   *
   * @param adminId
   * @param model
   * @return
   */
  @GetMapping("/admin/edit.html")
  public String toEditAdmin(@RequestParam("adminId") Integer adminId,
      Model model) {
    Admin admin = adminService.getById(adminId);
    model.addAttribute(CrowdConstant.ATTR_ADMIN, admin);
    return "admin-edit";
  }

  /**
   * 修改用户功能，成功返回用户维护页面
   *
   * @param adminId
   * @param pageNum
   * @param keyword
   * @param loginAcct
   * @param userName
   * @param userPswd
   * @param email
   * @return
   */
  @PostMapping("/admin/do/edit.html")
  public String editAdmin(@RequestParam("adminId") Integer adminId,
      @RequestParam("pageNum") Integer pageNum,
      @RequestParam("keyword") String keyword,
      @RequestParam("loginAcct") String loginAcct,
      @RequestParam("userName") String userName,
      @RequestParam("userPswd") String userPswd,
      @RequestParam("email") String email) {
    int result = adminService.updateByAdmin(adminId, loginAcct, userName, userPswd, email);
    log.info("result:" + result);
    return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
  }

}
