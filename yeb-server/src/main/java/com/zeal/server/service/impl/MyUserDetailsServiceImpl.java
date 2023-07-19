package com.zeal.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zeal.server.entity.Admin;
import com.zeal.server.entity.Role;
import com.zeal.server.mapper.AdminMapper;
import com.zeal.server.service.IAdminService;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * WHAT THE ZZZZEAL
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/12 17:08
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService, UserDetailsPasswordService {

    private final AdminMapper adminMapper;
    private final IAdminService adminService;
    public MyUserDetailsServiceImpl(AdminMapper adminMapper, IAdminService adminService) {
        this.adminMapper = adminMapper;
        this.adminService = adminService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username).eq("enabled", true));
        if (ObjectUtils.isEmpty(admin)) {
            return (UserDetails) new UsernameNotFoundException("用户名或密码不正确");
        }
        admin.setRoles(adminService.getRoles(admin.getId()));
        return User.withUsername(admin.getUsername())
                .password(admin.getPassword())
                .roles(admin.getRoles().stream().map(role -> {
                    String name = role.getName();
                    name = name.substring(name.indexOf("_") + 1);
                    return name;
                }).toArray(String[]::new))
                .build();
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }
}
