package com.zeal.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zeal.server.entity.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询菜单列表
     * @param adminId 用户id
     * @return 菜单列表
     */
    List<Role> getRoles(Integer adminId);
}
