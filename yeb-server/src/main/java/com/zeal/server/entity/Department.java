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
@TableName("t_department")
@Data
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 路径
     */
    private String depPath;

    /**
     * 启用禁用
     */
    private Boolean enabled;

    /**
     * 是否父级
     */
    private Boolean isParent;

    @TableField(exist = false)
    private List<Department> children;

    @TableField(exist = false)
    private Integer result;

    @Override
    public String toString() {
        return "Department{" +
            "id = " + id +
            ", name = " + name +
            ", parentId = " + parentId +
            ", depPath = " + depPath +
            ", enabled = " + enabled +
            ", isParent = " + isParent +
        "}";
    }
}
