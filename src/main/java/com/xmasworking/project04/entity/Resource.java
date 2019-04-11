package com.xmasworking.project04.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2019/4/11 - 10:50 AM
 * Created by IntelliJ IDEA.
 */
@Entity
@Data
@Table(name = "sys_resource")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@DynamicUpdate
public class Resource extends IDBaseEntity{
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源类型
     */
    private ResourceType type = ResourceType.menu;
    /**
     * 资源路径
     */
    private String url;
    /**
     * 权限字符串
     */
    private String permission;
    /**
     * 父编号
     */
    private Long parentId;
    /**
     * 父编号列表
     */
    private String parentIds;
    private Boolean available = Boolean.FALSE;

    /**
     * menu("菜单"), button("按钮");
     */
    public static enum ResourceType {
        /**
         * 菜单
         */
        menu("菜单"),
        /**
         * 按钮
         */
        button("按钮");

        private final String info;
        ResourceType(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }
}
