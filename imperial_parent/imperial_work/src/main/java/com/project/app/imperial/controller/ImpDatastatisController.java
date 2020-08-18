package com.project.app.imperial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.common.utils.poi.ExcelUtil;
import com.common.utils.text.Convert;
import com.framework.aspectj.lang.annotation.Log;
import com.framework.aspectj.lang.enums.BusinessType;
import com.framework.web.controller.BaseController;
import com.framework.web.domain.AjaxResult;
import com.framework.web.page.TableDataInfo;
import com.project.app.imperial.domain.ImpBasicData;
import com.project.app.imperial.domain.ImpOrdercheckHis;
import com.project.app.imperial.service.IImpBasicDataService;
import com.project.app.imperial.service.IImpOrdercheckHisService;

/**
 * 清关系统代理人审核处理Controller
 * 
 * @author fanke
 * @date 2020-07-08
 */
@Controller
@RequestMapping("/imp/datastatis")
public class ImpDatastatisController extends BaseController
{
    private String prefix = "/app/datastatis";

    

    @RequiresPermissions("datastatis:data:view")
    @GetMapping("/shipperStatis")
    public String data()
    {
        return prefix + "/shipperdatastatis";
    }
    
    
   
    
    
    
}
