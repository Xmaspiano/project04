package com.xmasworking.project04.config;

import com.xmasworking.project04.entity.User;
import com.xmasworking.project04.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 *
 *
 * @author XmasPiano
 * @date 2018/3/1 上午10:16
 * @param
 * @return
 */
public class MyShiroRealm extends AuthorizingRealm implements CacheManagerAware {
    @Value("${admin.loginid}")
    private String adminName;

    @Value("${admin.password}")
    private String password;

    @Value("${admin.lastname}")
    private String lastname;
    @Value("${admin.salt}")
    private String salt;

    @Autowired
    UserRepository userRepository;

    /**
     * 认证.登录
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户输入的token
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        String username = utoken.getUsername();

        //如果用户名是管理员
        if(this.adminName.equals(username)) {
            //验证管理员
            return new SimpleAuthenticationInfo(this.lastname, this.password, ByteSource.Util.bytes(adminName+this.salt), getName());
        }

        User accountEntity = new User();

        //验证用户
        try {

            accountEntity.setLoginId(utoken.getUsername());
            Optional<User> optional = userRepository.findOne(Example.of(accountEntity));
            if (optional.isPresent()) {
                accountEntity = optional.get();
            } else {
                throw new UnknownAccountException();
            }

        }catch (NoSuchElementException nee){
            nee.printStackTrace();
            throw new UnknownAccountException(nee.getMessage());
        }
        return new SimpleAuthenticationInfo(accountEntity.getUsername(), accountEntity.getPassword(),
                ByteSource.Util.bytes(accountEntity.getCredentialsSalt()), this.getClass().getName());
    }

    /**
     * 授权
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User accountEntity = (User) SecurityUtils.getSubject().getPrincipal();
        if(this.adminName.equals(accountEntity.getLoginId())) {
            authorizationInfo.addRole("manager");
        }else {
            authorizationInfo.addRole("user");
        }
        return authorizationInfo;
    }
}