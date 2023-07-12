package com.zeal.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;
import java.util.Scanner;

/**
 * WHAT THE ZZZZEAL
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/11 16:35
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        FastAutoGenerator.create("jdbc:mysql://106.15.191.2:3306/yebdb?useSSL=false&serverTimezone=Asia/Shanghai", "zeal", "1234")
                .globalConfig(builder -> {
                    builder.author("zeal") // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            .outputDir("C:\\Work\\TestJava\\SpringSecurityDemo\\yeb-server\\src\\main\\java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.zeal") // 设置父包名
                            .moduleName("server") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\Work\\TestJava\\SpringSecurityDemo\\yeb-server\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_admin",
                                    "t_admin_role",
                                    "t_appraise",
                                    "t_department",
                                    "t_employee",
                                    "t_employee_ec",
                                    "t_employee_remove",
                                    "t_employee_train",
                                    "t_joblevel",
                                    "t_mail_log",
                                    "t_menu",
                                    "t_nation",
                                    "t_oplog",
                                    "t_politics_status",
                                    "t_position",
                                    "t_role",
                                    "t_role_menu",
                                    "t_salary",
                                    "t_salary_adjust",
                                    "t_sys_msg",
                                    "t_sys_msg_content") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
