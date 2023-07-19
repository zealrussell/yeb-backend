package com.zeal.server.config.filter;

import com.zeal.server.utils.JWTTokenUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * WHAT THE ZZZZEAL
 *
 * JWT登录授权过滤器
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/12 11:26
 */

@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private JWTTokenUtil jwtTokenUtil;
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JWT url {}", request.getRequestURL() );
        String header = request.getHeader(tokenHeader);
        if (null != header && !header.startsWith(tokenHead)) {
            String token = header.substring(tokenHead.length());
            String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token);
            // token中有用户名，但是没有登录
            if (usernameFromToken != null && null == SecurityContextHolder.getContext().getAuthentication()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(usernameFromToken);
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    // 更新security登录用户对象
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    // 将用户信息存入security上下文
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    log.info("用户{}登录成功", usernameFromToken);
                }
            }
        } else {
            log.info("没有token");
        }
        filterChain.doFilter(request, response);
    }
}
