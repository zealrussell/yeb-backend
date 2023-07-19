package com.zeal.server.exception;

import com.zeal.server.entity.vo.RespBean;
import com.zeal.server.entity.vo.RespBeanEnum;
import jakarta.servlet.http.PushBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * WHAT THE ZZZZEAL
 *
 * 全局异常处理
 *  你
 * @author zeal
 * @version 1.0
 * @since 2023/7/14 21:38
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(SQLException.class)
    public RespBean mySQLException(SQLException e) {

        if (e instanceof SQLIntegrityConstraintViolationException) {
            return RespBean.error(RespBeanEnum.DELETE_HAVE_FOR_FILED);
        }
        return RespBean.error(RespBeanEnum.DATABASE_FILED);
    }
}
