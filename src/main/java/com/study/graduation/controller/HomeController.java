package com.study.graduation.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping("/index")
    public String home(Model model,String tabIndex){
        if(Strings.isEmpty(tabIndex)){
            model.addAttribute("tabIndex",1);
        }else{
            model.addAttribute("tabIndex",Integer.parseInt(tabIndex));
        }
        return "home";
    }
}
