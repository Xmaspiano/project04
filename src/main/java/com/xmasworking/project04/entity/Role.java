package com.xmasworking.project04.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2019/4/11 - 10:48 AM
 * Created by IntelliJ IDEA.
 */
@Entity
@Data
@Table(name = "sys_role")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Role extends IDBaseEntity{
    /**
     * 角色标识 程序中判断使用,如"admin"
     */
    private String role;
    /**
     * 角色描述,UI界面显示使用
     */
    private String description;
    /**
     * 拥有的资源
     */
    private String resourceIds;
    /**
     * 是否可用,如果不可用将不会添加给用户
     */
    private Boolean available = Boolean.FALSE;

//    public Role(String role, String description, Boolean available) {
//        this.role = role;
//        this.description = description;
//        this.available = available;
//    }
}
