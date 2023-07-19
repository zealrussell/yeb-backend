package com.zeal.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zeal.server.entity.Admin;
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
public interface AdminMapper extends BaseMapper<Admin> {

    Admin getAdminByUsername(String username);

}
