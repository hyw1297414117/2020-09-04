package com.project.app.imperial.controller;

import com.project.app.imperial.Util.IpUtil;
import com.project.app.imperial.service.IImpDDUEmailService;
import com.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.framework.web.domain.AjaxResult.error;
import static com.framework.web.domain.AjaxResult.success;

@Controller
@RequestMapping("/DDU")
public class ImpDDUEmailController {
    @Autowired
    private IImpDDUEmailService iImpDDUEmailService;

    /*
     * DDU点击邮件发送
     * */
    @PostMapping("/DDUSendEmail")
    @ResponseBody
    public Map<String, Integer> DDUSendEmail(HttpServletRequest request, User user)
    {
        String mainOrderNoVal = request.getParameter("mainOrderNoVal");//主单号
        //公网
        //String ServerUrl = IpUtil.getIP();
        //内网
        String ServerUrl = IpUtil.getIpAdd();
        Map<String, Integer> count=new HashMap<>();
        count = iImpDDUEmailService.DDUSendEmail(mainOrderNoVal,ServerUrl);
        return count;
    }

}
