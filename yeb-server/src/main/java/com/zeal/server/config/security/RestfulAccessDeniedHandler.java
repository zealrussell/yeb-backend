package com.zeal.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeal.server.entity.vo.RespBean;
import com.zeal.server.entity.vo.RespBeanEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *
 * 权限不足时自定义的返回结果
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/12 11:47
 */
@Slf4j
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("权限不足 {}",request.getRequestURI());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        RespBean bean = RespBean.error(RespBeanEnum.AUTHORITY_DENIED);
        bean.setCode(403);
        response.getWriter().write(new ObjectMapper().writeValueAsString(bean));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
