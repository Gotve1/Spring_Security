package com.example.security.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UpdateController {

    @GetMapping("/update")
    public String redirect() {
        return "redirect:/update/";
    }

    @GetMapping("/update/")
    public String update() {
        return "forward:/update.html";
    }

}
