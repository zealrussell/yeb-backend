package com.zeal.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zeal.server.entity.Admin;
import com.zeal.server.entity.vo.RespBean;
import com.zeal.server.entity.vo.RespBeanEnum;
import com.zeal.server.mapper.AdminMapper;
import com.zeal.server.service.IAdminService;
import com.zeal.server.utils.JWTTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    private final UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    private JWTTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public AdminServiceImpl(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JWTTokenUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !password.equals(userDetails.getPassword())) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error(RespBeanEnum.NOT_LOGIN);
        }
        // 更新security登录用户对象
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // 将用户信息存入security上下文
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("tokenHead", tokenHead);
        return RespBean.success(map);
    }
}
