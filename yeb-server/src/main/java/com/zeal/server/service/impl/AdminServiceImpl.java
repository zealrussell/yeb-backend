package com.zeal.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zeal.server.entity.Admin;
import com.zeal.server.entity.Role;
import com.zeal.server.entity.vo.AdminLogin;
import com.zeal.server.entity.vo.RespBean;
import com.zeal.server.entity.vo.RespBeanEnum;
import com.zeal.server.mapper.AdminMapper;
import com.zeal.server.mapper.RoleMapper;
import com.zeal.server.service.IAdminService;
import com.zeal.server.utils.JWTTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
@Slf4j
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    private final AdminMapper adminMapper;
    private final RoleMapper roleMapper;



    public AdminServiceImpl(AdminMapper adminMapper, RoleMapper roleMapper) {
        this.adminMapper = adminMapper;
        this.roleMapper = roleMapper;
    }



    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    @Override
    public Admin getAdminByUsername(String username) {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username).eq("enabled", true));
        if (ObjectUtils.isEmpty(admin)) {
            return null;
        }
        return admin;
    }

    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

}
