package com.project.system.user.controller;

import java.util.List;

import com.project.system.user.domain.ImpMenulanguagetype;
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

import static com.project.system.user.controller.LoginController.LoginMenulanguagetype;


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
    public String index(ModelMap mmap, String lang)
    {
        String IndexMenulanguagetype=LoginMenulanguagetype;
        // 取身份信息
        User user = getSysUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);

        ImpMenulanguagetype impMenulanguagetype=new ImpMenulanguagetype();
        //判断index页面是否选择语言
        if(lang=="" || lang==null){
            //判断login页面是否选择语言
            if("".equals(IndexMenulanguagetype) || IndexMenulanguagetype==null) {
                impMenulanguagetype.setLanguagetype("zh_CN");
            }else {
                impMenulanguagetype.setLanguagetype(IndexMenulanguagetype);
            }
        }else {
            impMenulanguagetype.setLanguagetype(lang);
        }

        for (int i = 0; i < menus.size(); i++) {
            if (impMenulanguagetype.getLanguagetype() == "zh_CN" || impMenulanguagetype.getLanguagetype().equals("zh_CN")) {
                menus.get(i).setMenuName(menus.get(i).getZh_CN());
                if (menus.get(i).getChildren().size() > 0) {
                    for (int j = 0; j < menus.get(i).getChildren().size(); j++) {
                        menus.get(i).getChildren().get(j).setMenuName(menus.get(i).getChildren().get(j).getZh_CN());
                        if (menus.get(i).getChildren().get(j).getChildren().size() > 0) {
                            for (int k = 0; k < menus.get(i).getChildren().get(j).getChildren().size(); k++) {
                                menus.get(i).getChildren().get(j).getChildren().get(k).setMenuName(menus.get(i).getChildren().get(j).getChildren().get(k).getZh_CN());
                            }
                        }
                    }
                }
            } else if (impMenulanguagetype.getLanguagetype() == "en_US" || impMenulanguagetype.getLanguagetype().equals("en_US")) {
                menus.get(i).setMenuName(menus.get(i).getEn_US());
                if (menus.get(i).getChildren().size() > 0) {
                    for (int j = 0; j < menus.get(i).getChildren().size(); j++) {
                        menus.get(i).getChildren().get(j).setMenuName(menus.get(i).getChildren().get(j).getEn_US());
                        if (menus.get(i).getChildren().get(j).getChildren().size() > 0) {
                            for (int k = 0; k < menus.get(i).getChildren().get(j).getChildren().size(); k++) {
                                menus.get(i).getChildren().get(j).getChildren().get(k).setMenuName(menus.get(i).getChildren().get(j).getChildren().get(k).getEn_US());
                            }
                        }
                    }
                }
            } else if (impMenulanguagetype.getLanguagetype() == "ja_JP" || impMenulanguagetype.getLanguagetype().equals("ja_JP")) {

                menus.get(i).setMenuName(menus.get(i).getJa_JP());
                if (menus.get(i).getChildren().size() > 0) {
                    for (int j = 0; j < menus.get(i).getChildren().size(); j++) {
                        menus.get(i).getChildren().get(j).setMenuName(menus.get(i).getChildren().get(j).getJa_JP());
                        if (menus.get(i).getChildren().get(j).getChildren().size() > 0) {
                            for (int k = 0; k < menus.get(i).getChildren().get(j).getChildren().size(); k++) {
                                menus.get(i).getChildren().get(j).getChildren().get(k).setMenuName(menus.get(i).getChildren().get(j).getChildren().get(k).getJa_JP());
                            }
                        }
                    }
                }
            } else if (impMenulanguagetype.getLanguagetype() == "fr_FR" || impMenulanguagetype.getLanguagetype().equals("fr_FR")) {
                menus.get(i).setMenuName(menus.get(i).getFr_FR());
                if (menus.get(i).getChildren().size() > 0) {
                    for (int j = 0; j < menus.get(i).getChildren().size(); j++) {
                        menus.get(i).getChildren().get(j).setMenuName(menus.get(i).getChildren().get(j).getFr_FR());
                        if (menus.get(i).getChildren().get(j).getChildren().size() > 0) {
                            for (int k = 0; k < menus.get(i).getChildren().get(j).getChildren().size(); k++) {
                                menus.get(i).getChildren().get(j).getChildren().get(k).setMenuName(menus.get(i).getChildren().get(j).getChildren().get(k).getFr_FR());
                            }
                        }
                    }
                }
            }

        }
        mmap.put("language", impMenulanguagetype.getLanguagetype());
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
        // 取身份信息
        User user = getSysUser();
        mmap.put("user", user);
        mmap.put("version", ruoYiConfig.getVersion());
        return "main";
    }
}
