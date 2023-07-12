package com.zeal.server.controller;

import com.zeal.server.entity.vo.AdminLogin;
import com.zeal.server.entity.vo.RespBean;
import com.zeal.server.service.IAdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WHAT THE ZZZZEAL
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/12 10:02
 */

@RestController
public class LoginController {

    private final IAdminService adminService;

    @Autowired
    public LoginController(IAdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 登录后返回token
     * @param adminLogin
     * @param request
     * @return
     */
    @PostMapping("/login")
    public RespBean login(AdminLogin adminLogin, HttpServletRequest request) {
        return adminService.login(adminLogin.getUsername(), adminLogin.getPassword(), request);
    }

}
