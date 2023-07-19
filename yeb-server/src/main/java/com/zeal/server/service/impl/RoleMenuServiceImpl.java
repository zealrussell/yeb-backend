package com.zeal.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zeal.server.entity.RoleMenu;
import com.zeal.server.entity.vo.RespBean;
import com.zeal.server.entity.vo.RespBeanEnum;
import com.zeal.server.mapper.RoleMenuMapper;
import com.zeal.server.service.IRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {
    private final RoleMenuMapper roleMenuMapper;

    public RoleMenuServiceImpl(RoleMenuMapper roleMenuMapper) {
        this.roleMenuMapper = roleMenuMapper;
    }

    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("rid", rid));
        if (null ==mids || 0 == mids.length) {
            return RespBean.success("更新成功！");
        }
        Integer result = roleMenuMapper.insertRecord(rid, mids);
        if (result == mids.length) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error(RespBeanEnum.UPDATE_FILED);
    }
}
