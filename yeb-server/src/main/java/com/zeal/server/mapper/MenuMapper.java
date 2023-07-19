package com.zeal.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zeal.server.entity.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
public interface MenuMapper extends BaseMapper<Menu> {

     /**
     *
     * @param id 用户id
     * @return 菜单列表
     */
    List<Menu> getMenusByAdminId(Integer id);

    List<Menu> getMenusWithRole();

    List<Menu> getAllMenus();
}
