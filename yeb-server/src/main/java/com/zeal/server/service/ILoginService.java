package com.zeal.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zeal.server.entity.Admin;
import com.zeal.server.entity.vo.AdminLogin;
import com.zeal.server.entity.vo.RespBean;
import jakarta.servlet.http.HttpServletRequest;

/**
 * WHAT THE ZZZZEAL
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/16 10:28
 */
public interface ILoginService extends IService<Admin> {
    RespBean login(AdminLogin adminLogin, HttpServletRequest request);
}
