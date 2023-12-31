package com.zeal.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zeal.server.entity.RoleMenu;
import com.zeal.server.entity.vo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
