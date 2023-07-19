package com.zeal.server.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * WHAT THE ZZZZEAL
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/12 13:09
 */
@RestController
@RequestMapping(value = "/captcha")
@Slf4j
public class CaptchaController {

    private final DefaultKaptcha defaultKaptcha;

    public CaptchaController(DefaultKaptcha defaultKaptcha) {
        this.defaultKaptcha = defaultKaptcha;
    }

    /**
     *
     * 生成验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("/getCaptcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
         //设置请求头输出位image类型
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "No-cache");
        response.setContentType("image/jpg");
        //生成验证码
        String captcha = defaultKaptcha.createText();
        log.info("生成的验证码为：{}", captcha);
        request.getSession().setAttribute("captcha", captcha);
        // 生成验证码图片
        BufferedImage image = defaultKaptcha.createImage(captcha);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            log.error("获取验证码失败：{}", e.getMessage());
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("获取验证码失败：{}", e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
