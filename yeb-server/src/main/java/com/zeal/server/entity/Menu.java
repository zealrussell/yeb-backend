package com.zeal.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
@TableName("t_menu")
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * url
     */
    private String url;

    /**
     * path
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 图标
     */
    private String iconCls;

    /**
     * 是否保持激活
     */
    private Boolean keepAlive;

    /**
     * 是否要求权限
     */
    private Boolean requireAuth;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<Menu> children;

    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<Role> roles;

    @Override
    public String toString() {
        return "Menu{" +
            "id = " + id +
            ", url = " + url +
            ", path = " + path +
            ", component = " + component +
            ", name = " + name +
            ", iconCls = " + iconCls +
            ", keepAlive = " + keepAlive +
            ", requireAuth = " + requireAuth +
            ", parentId = " + parentId +
            ", enabled = " + enabled +
        "}";
    }
}
