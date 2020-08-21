package com.project.app.imperial.controller.creatask;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/createask")
public class ImpCreaTaskController {
    private String registerprefix = "/app/createask";
    @GetMapping("/showcreateaskPage")
    public String showShipperRegistPage()
    {
        return registerprefix + "/createask";
    }
}
