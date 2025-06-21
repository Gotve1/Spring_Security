package com.example.security.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PathController {

    @GetMapping("/update")
    public String redirect() {
        return "redirect:/update/";
    }

    @GetMapping("/update/")
    public String update() {
        return "forward:/update.html";
    }

    @GetMapping("/login")
    public String login() {
        return "forward:/login.html";
    }

}
