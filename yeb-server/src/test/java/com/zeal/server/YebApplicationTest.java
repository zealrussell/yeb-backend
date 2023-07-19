package com.zeal.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.zeal.server.entity.Admin;
import com.zeal.server.mapper.AdminMapper;
import com.zeal.server.service.IAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * WHAT THE ZZZZEAL
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/11 20:46
 */
@SpringBootTest
public class YebApplicationTest {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    void contextLoads() {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", "zeal"));
        System.out.println(admin);
    }
}
