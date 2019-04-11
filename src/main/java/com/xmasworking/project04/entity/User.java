package com.xmasworking.project04.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2019/4/11 - 10:13 AM
 * Created by IntelliJ IDEA.
 */
@Entity
@Data
@Table(name = "sys_user")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class User extends IDBaseEntity {
    /**
     * 所属公司
     */
    private Long organizationId;
    /**
     * 登陆ID
     */
    private String loginId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 加密密码的盐
     */
    private String salt;
    /**
     * 拥有的角色列表
     */
//    @Transient
    private String roleIds;

    private Boolean locked = Boolean.FALSE;

//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

    public String getCredentialsSalt() {
        return username + salt;
    }

    /**
     * 生成随机盐值对密码进行加密
     * @param password 登录识别串（用户名）
     * @return
     */
    public void setPassword(String password) {
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

        if(salt == null || "".equals(salt)) {
            this.salt = randomNumberGenerator.nextBytes().toHex();
        }

        this.password = new SimpleHash(
                "MD5",
                password,
                ByteSource.Util.bytes(this.getCredentialsSalt()),
                2).toHex();
    }
}
