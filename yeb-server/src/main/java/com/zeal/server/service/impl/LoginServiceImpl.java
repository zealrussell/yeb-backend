package com.zeal.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zeal.server.entity.Admin;
import com.zeal.server.entity.vo.AdminLogin;
import com.zeal.server.entity.vo.RespBean;
import com.zeal.server.entity.vo.RespBeanEnum;
import com.zeal.server.mapper.AdminMapper;
import com.zeal.server.service.ILoginService;
import com.zeal.server.utils.JWTTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * WHAT THE ZZZZEAL
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/16 10:29
 */
@Service
@Slf4j
public class LoginServiceImpl extends ServiceImpl<AdminMapper, Admin> implements ILoginService {

    private final MyUserDetailsServiceImpl myUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public LoginServiceImpl(MyUserDetailsServiceImpl myUserDetailsService, PasswordEncoder passwordEncoder, JWTTokenUtil jwtTokenUtil) {
        this.myUserDetailsService = myUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * 登录
     *
     * @param adminLogin 登录对象
     * @return RespBean
     */
    @Override
    public RespBean login(AdminLogin adminLogin, HttpServletRequest request) {
        if (adminLogin == null) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        String username = adminLogin.getUsername();
        String password = adminLogin.getPassword();
        String code = adminLogin.getCode();
        String captcha = (String) request.getSession().getAttribute("captcha");
        log.info("验证码：{}", captcha);
        log.info("用户输入的：{} {} {}", username, password, code);
        // 验证码校验失败
        if ( !StringUtils.hasLength(captcha) || !captcha.equalsIgnoreCase(code)) {
            log.info("验证码校验失败 {}", RespBeanEnum.CAPTCHA_ERROR);
            return RespBean.error(RespBeanEnum.CAPTCHA_ERROR);
        }
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        log.info("userDetails: {}", userDetails.getPassword());
        if (ObjectUtils.isEmpty(userDetails) || !passwordEncoder.matches(password, userDetails.getPassword())) {
            log.info("用户名或密码不正确 {}", RespBeanEnum.LOGIN_ERROR);
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        if (!userDetails.isEnabled()) {
            log.info("账号被禁用 {}", RespBeanEnum.LOGIN_ERROR);
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

        log.info("登录成功 {}", RespBean.success(map));
        return RespBean.success(map);
    }

}
