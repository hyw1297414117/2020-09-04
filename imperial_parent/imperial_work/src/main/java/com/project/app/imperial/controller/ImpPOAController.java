package com.project.app.imperial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImpPOAController {
    private String perfectinformationprefix = "/app/shipperregister";
    private String POAprefix = "/app/POAdispose";
    @RequestMapping("/test")
    public String main(){
        return "app/POAdispose/test";
    }
    @RequestMapping("/showPOA")
    public String poa(){
        return POAprefix+"/POA";
    }

    @RequestMapping("/showconsignerperfectinformation")
    public String show(){
        return perfectinformationprefix + "/shipperregister";
    }
}
