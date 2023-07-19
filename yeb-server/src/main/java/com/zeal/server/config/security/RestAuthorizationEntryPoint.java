package com.zeal.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeal.server.entity.vo.RespBean;
import com.zeal.server.entity.vo.RespBeanEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * WHAT THE ZZZZEAL
 *
 * 当未登录或者token失效访问接口时，自定义的返回结果
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/12 11:44
 */
@Slf4j
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint  {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("未登录或token失效 {}", request.getRequestURL());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        RespBean bean = RespBean.error(RespBeanEnum.NOT_LOGIN);
        bean.setCode(401);
        response.getWriter().write(new ObjectMapper().writeValueAsString(bean));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
