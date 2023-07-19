package com.zeal.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zeal.server.entity.Admin;
import com.zeal.server.entity.Menu;
import com.zeal.server.entity.Role;
import com.zeal.server.entity.vo.AdminLogin;
import com.zeal.server.entity.vo.RespBean;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
public interface IAdminService extends IService<Admin> {

    Admin getAdminByUsername(String username);

    /**
     * 根据用户id查询菜单列表
     * @return 菜单列表
     */
    List<Role> getRoles(Integer adminId);

}
