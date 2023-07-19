package com.zeal.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.zeal.server.entity.Menu;
import com.zeal.server.entity.Role;
import com.zeal.server.entity.RoleMenu;
import com.zeal.server.entity.vo.RespBean;
import com.zeal.server.entity.vo.RespBeanEnum;
import com.zeal.server.service.IMenuService;
import com.zeal.server.service.IRoleMenuService;
import com.zeal.server.service.IRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * WHAT THE ZZZZEAL
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/15 10:26
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    private final IRoleService roleService;
    private final IMenuService menuService;
    private final IRoleMenuService roleMenuService;

    public PermissController(IRoleService roleService, IMenuService menuService, IRoleMenuService roleMenuService) {
        this.roleService = roleService;
        this.menuService = menuService;
        this.roleMenuService = roleMenuService;
    }

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.list();
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_" + role.getName());
        }
        if(roleService.save(role)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error(RespBeanEnum.ADD_FILED);
    }
    @DeleteMapping("/role/{id}")
    public RespBean deleteRole(@PathVariable Integer id) {
        if(roleService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error(RespBeanEnum.DELETE_FILED);
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }


    /**
     * 根据角色id获取菜单id
     * @param rid 角色id
     * @return java.util.List<java.lang.Integer>
     */
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid){
        return roleMenuService.list(new QueryWrapper<RoleMenu>().eq("rid",rid))
                .stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
    }
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        return roleMenuService.updateMenuRole(rid,mids);
    }
}
