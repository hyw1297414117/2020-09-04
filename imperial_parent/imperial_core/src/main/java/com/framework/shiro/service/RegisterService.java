package com.framework.shiro.service;

import com.common.constant.Constants;
import com.common.utils.MailUtils;
import com.common.utils.MessageUtils;
import com.framework.manager.AsyncManager;
import com.framework.manager.factory.AsyncFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.common.constant.ShiroConstants;
import com.common.constant.UserConstants;
import com.common.utils.ServletUtils;
import com.project.system.user.domain.User;
import com.project.system.user.service.IUserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.UUID;

/**
 * 注册校验方法
 * 
 * @author ruoyi
 */
@Component
public class RegisterService
{
    @Autowired
    private IUserService userService;
    @Autowired
    private MailUtils mailUtils;
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 注册
     */
    public String register(User user,String serverUrl)
    {
        String msg = "", username = user.getUserName(),loginname=user.getLoginName(), password = user.getPassword(),email=user.getEmail(),phonenome=user.getPhonenumber();
        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            msg = "验证码错误";
        }
        else if (StringUtils.isEmpty(username))
        {
            msg = "用户真实姓名不能为空";
        }
        else if (StringUtils.isEmpty(loginname))
        {
            msg = "登录名不能为空";
        }else if (StringUtils.isEmpty(email))
        {
            msg = "邮箱不能为空";
        }
        else if (StringUtils.isEmpty(phonenome))
        {
            msg = "手机号码不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(username)))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else
        {
            String activeCode = UUID.randomUUID().toString();
            user.setActiveCode(activeCode);
            boolean regFlag = userService.registerUser(user);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                //          发送账户激活邮件
                //          渲染html模板
                Context context = new Context();
                context.setVariable("activeCode", activeCode);
                context.setVariable("url", serverUrl);
                String emailContent = templateEngine.process("register/emailTemplate", context);

                mailUtils.sendHtmlMail(email, "账户激活", emailContent);//发邮件
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }
}
