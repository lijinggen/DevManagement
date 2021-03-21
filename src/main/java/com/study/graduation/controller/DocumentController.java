package com.study.graduation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doc")
public class DocumentController {

    @RequestMapping("/document")
    public String document(){
        return "document";
    }
}
