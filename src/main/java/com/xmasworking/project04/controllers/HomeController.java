package com.xmasworking.project04.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2019/4/11 - 12:54 PM
 * Created by IntelliJ IDEA.
 */
@Controller
@RequestMapping(value = "/home")
@Log4j2
public class HomeController {
    @GetMapping
    public String home(){
        return "/home";
    }
}
