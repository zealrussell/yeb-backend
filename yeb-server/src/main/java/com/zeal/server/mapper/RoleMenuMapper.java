package com.zeal.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zeal.server.entity.RoleMenu;
import com.zeal.server.entity.vo.RespBean;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 更新角色菜单
     * @param rid 角色id
     * @param mids 菜单id数组
     * @return
     */
    Integer insertRecord(@Param("rid") Integer rid,@Param("mids") Integer[] mids);
}
