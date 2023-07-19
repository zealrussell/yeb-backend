package com.zeal.server.controller;

import com.zeal.server.entity.Admin;
import com.zeal.server.entity.vo.AdminLogin;
import com.zeal.server.entity.vo.RespBean;
import com.zeal.server.entity.vo.RespBeanEnum;
import com.zeal.server.service.IAdminService;
import com.zeal.server.service.ILoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * WHAT THE ZZZZEAL
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/12 10:02
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    private final ILoginService loginService;
    private final IAdminService adminService;

    @Autowired
    public LoginController(ILoginService loginService, IAdminService adminService) {
        this.loginService = loginService;
        this.adminService = adminService;
    }

    /**
     * 登录后返回token
     * @param adminLogin
     * @param request
     * @return
     */
    @PostMapping("/doLogin")
    public RespBean doLogin(@RequestBody AdminLogin adminLogin, HttpServletRequest request) {
        return loginService.login(adminLogin, request);
    }

    @GetMapping("/getUserInfo")
    public RespBean getAdminInfo(Principal principal) {
        if (ObjectUtils.isEmpty(principal)) {
            return RespBean.error(RespBeanEnum.NOT_LOGIN);
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUsername(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return RespBean.success(admin);
    }

    @PostMapping("/doLogout")
    public RespBean logout() {
        return RespBean.success("注销成功");
    }

}
