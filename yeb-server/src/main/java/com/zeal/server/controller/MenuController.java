package com.zeal.server.controller;

import com.zeal.server.entity.Menu;
import com.zeal.server.service.IAdminService;
import com.zeal.server.service.IMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
@RestController
@RequestMapping("/server/cfg")
public class MenuController {

    private final IMenuService menuService;

    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 根据用户id查询菜单列表
     * @return
     */
    @GetMapping("/menu")
    public List<Menu> getMenuByAdminId() {
        return menuService.getMenusByAdminId();
    }
}
