package com.zeal.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
@Data
@TableName("t_admin")
public class Admin implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 家庭电话
     */
    private String telphone;

    /**
     * 地址
     */
    private String address;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String userFace;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private List<Role> roles;

    @Override
    public String toString() {
        return "Admin{" +
            "id = " + id +
            ", name = " + name +
            ", phone = " + phone +
            ", telphone = " + telphone +
            ", address = " + address +
            ", enabled = " + enabled +
            ", username = " + username +
            ", password = " + password +
            ", userFace = " + userFace +
            ", remark = " + remark +
        "}";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> collect = roles
                .stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName()))
                .toList();

        return collect;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
