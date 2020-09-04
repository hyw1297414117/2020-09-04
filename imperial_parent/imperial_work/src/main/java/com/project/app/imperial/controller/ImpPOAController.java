package com.project.app.imperial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImpPOAController {
    @RequestMapping("/test")
    public String main(){
        return "app/POAdispose/test";
    }
}
