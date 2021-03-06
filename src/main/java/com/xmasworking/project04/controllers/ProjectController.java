package com.xmasworking.project04.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2019/4/11 - 4:00 PM
 * Created by IntelliJ IDEA.
 */
@Controller
@RequestMapping(value = "/project")
@Log4j2
public class ProjectController {

    @GetMapping
    public String project() {
        return "/project/index";
    }

    @GetMapping("/watch")
    public String watch() {
        return "/project/watch";
    }

    @GetMapping("/edit")
    public String edit() {
        return "/project/edit";
    }

    @RequestMapping("/del")
    public String del() {
        return "/project/index";
    }
}
