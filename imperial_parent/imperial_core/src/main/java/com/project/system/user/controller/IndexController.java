package com.project.system.user.controller;

import java.util.List;

import com.project.system.user.domain.ImpMenulinguistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.framework.config.RuoYiConfig;
import com.framework.web.controller.BaseController;
import com.project.system.config.service.IConfigService;
import com.project.system.menu.domain.Menu;
import com.project.system.menu.service.IMenuService;
import com.project.system.user.domain.User;

import static com.project.system.user.controller.LoginController.LoginMenulinguistictype;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private RuoYiConfig ruoYiConfig;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap, String linguistic)
    {
        String IndexMenulinguistictype=LoginMenulinguistictype;
        // 取身份信息
        User user = getSysUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        ImpMenulinguistic impMenulinguistic=new ImpMenulinguistic();
        //判断index页面是否选择语言
        if(linguistic=="" || linguistic==null){
            //判断login页面是否选择语言
            if("".equals(IndexMenulinguistictype) || IndexMenulinguistictype==null) {
                impMenulinguistic.setLinguistic("zh_CN");
            }else {
                impMenulinguistic.setLinguistic(IndexMenulinguistictype);
            }
        }else {
            impMenulinguistic.setLinguistic(linguistic);
        }
        mmap.put("linguistic",impMenulinguistic.getLinguistic());
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());
        mmap.put("demoEnabled", ruoYiConfig.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", ruoYiConfig.getVersion());
        return "main";
    }
}
