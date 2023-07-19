package com.zeal.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zeal.server.entity.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 通过用户id获取菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId();

    /**
     * 通过角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();


    List<Menu> getAllMenus();
}
