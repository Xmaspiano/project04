package com.xmasworking.project04.repository;

import com.xmasworking.project04.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/17 - 下午5:26
 * Created by IntelliJ IDEA.
 */
public interface UserRepository
        extends JpaRepository<User, Long> {

}
