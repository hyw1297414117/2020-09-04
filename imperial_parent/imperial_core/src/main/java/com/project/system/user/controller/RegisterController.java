package com.project.system.user.controller;

import com.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.framework.shiro.service.RegisterService;
import com.framework.web.controller.BaseController;
import com.framework.web.domain.AjaxResult;
import com.project.system.config.service.IConfigService;
import com.project.system.user.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册验证
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController
{
    @Autowired
    private RegisterService registerService;
    @Autowired
    private IUserService userService;

    @Autowired
    private IConfigService configService;

    @GetMapping("")
    public String register()
    {
        return "register/register";
    }

    @PostMapping("")
    @ResponseBody
    public AjaxResult ajaxRegister(HttpServletRequest request, User user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String ServerUrl = request.getServerName()+":"+request.getServerPort();//返回服务器的主机名+端口
        String msg = registerService.register(user,ServerUrl);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

    /**
     * @ author     :LianZheng
     * @ Description:邮件激活地址，跳转至一个提示激活成功的页面，后自动跳转至登录页
     * @ Date       :2020/8/26
    */
    @GetMapping("/active/{activeCode}")
    public String ajaxRegister(@PathVariable("activeCode") String activeCode, Model model)
    {
        int i = userService.activeUser(activeCode);
        return "/register/active";
    }
}
