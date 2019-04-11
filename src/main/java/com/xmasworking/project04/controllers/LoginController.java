package com.xmasworking.project04.controllers;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2019/4/11 - 12:44 PM
 * Created by IntelliJ IDEA.
 */
@Controller
@RequestMapping(value = "/login")
@Log4j2
public class LoginController {

    @GetMapping
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/in")
    public ModelAndView loginUserAjax(String username, String password, boolean rememberMe) {
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        ModelAndView modelAndView = new ModelAndView("login");
        String error = "";
        try {
            //完成登录
            subject.login(usernamePasswordToken);
            usernamePasswordToken.setRememberMe(rememberMe);
            return new ModelAndView("redirect:/home");
        } catch (UnknownAccountException uae) {
            modelAndView.addObject("msg","用户不存在,请申请用户!!!");
        } catch (IncorrectCredentialsException ice) {
            modelAndView.addObject("msg","用户名密码错误,请确认后重新登陆!!!");
        } catch (LockedAccountException lae) {
            modelAndView.addObject("msg","账户已被锁定，无法登陆!!!");
        } catch (AuthenticationException ae) {
            modelAndView.addObject("msg","unexpected condition...");
        } catch(Exception e) {
            modelAndView.addObject("msg","登录异常："  + e.getMessage());
        }
        log.info(error);
        return modelAndView;
    }
}
