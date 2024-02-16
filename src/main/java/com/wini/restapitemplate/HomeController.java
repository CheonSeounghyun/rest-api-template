package com.wini.restapitemplate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value ="/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping(value = "/")
    public String main(Model model){
        return "home";
    }

}
