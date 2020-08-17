package com.project.app.imperial.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.framework.aspectj.lang.annotation.Log;
import com.framework.aspectj.lang.enums.BusinessType;
import com.framework.web.controller.BaseController;
import com.framework.web.domain.AjaxResult;
import com.framework.web.page.TableDataInfo;
import com.project.app.imperial.domain.ImpBasicData;
import com.project.app.imperial.domain.ImpOrdercheckHis;
import com.project.app.imperial.service.IImpBasicDataService;
import com.project.app.imperial.service.IImpOrdercheckHisService;
import com.project.system.user.domain.User;


/**
 * 清关系统已提交审核数据Controller
 * 
 * @author fanke
 * @date 2020-07-08
 */
@Controller
@RequestMapping("/shipperModule/impBasicDataSubmit")
public class ImpBasicDataSubmitController extends BaseController
{
    private String prefix = "/app/impbasicsubmit";

    @Autowired
    private IImpBasicDataService impBasicDataService;
    @Autowired
    private IImpOrdercheckHisService impOrdercheckHisService;

    @RequiresPermissions("shipperModule:data:view")
    @GetMapping("/alreadySub")
    public String alreadySubData()
    {
        return prefix + "/alreadysubmit";
    }
    
    @RequiresPermissions("shipperModule:data:view")
    @GetMapping("/reject")
    public String rejectData()
    {
        return prefix + "/submitreject";
    }
    
    @RequiresPermissions("shipperModule:data:view")
    @GetMapping("/success")
    public String successData()
    {
        return prefix + "/submitsuccess";
    }
    
    @RequiresPermissions("shipperModule:data:view")
    @GetMapping("/to_refuseMsg/{tackingNumber1}")
    public String to_refuseMsg(@PathVariable("tackingNumber1") String tackingNumber1, Model model)
    {
    	model.addAttribute("tackingNumber1", tackingNumber1);
        return prefix + "/refusemsg";
    }

    /**
     * 查询清关系统基础数据表处理列表（已提交审核订单）
     */
    @RequiresPermissions("shipperModule:data:list")
    @PostMapping("/orderCheckRefuseMsgList")
    @ResponseBody
    public TableDataInfo orderCheckRefuseMsgList(@RequestParam Map<String, Object> params)
    {
    	JSONObject jsStr = JSONObject.parseObject((String)params.get("impParams"));
    	String tackingNumber1 = null;
        startPage();
        if(jsStr!=null) {
    		tackingNumber1 = (String) jsStr.get("tackingNumber1");
    	}
        ImpOrdercheckHis impOrdercheckHis = new ImpOrdercheckHis();
        impOrdercheckHis.setOrderNumber(tackingNumber1);
        List<ImpOrdercheckHis> list = impOrdercheckHisService.selectImpOrdercheckHisList(impOrdercheckHis);
        return getDataTable(list);
    }
    
    
    
    
    /**
     * 查询清关系统基础数据表处理列表（已提交审核订单）
     */
    /*
    @RequiresPermissions("shipperModule:data:list")
    @PostMapping("/alreadysubmitList")
    @ResponseBody
    public TableDataInfo historyList(ImpBasicData impBasicData)
    {
        startPage();
        impBasicData.setSubmitFlag(1);  //历史订单
        List<ImpBasicData> list = impBasicDataService.selectImpBasicDataList(impBasicData);
        return getDataTable(list);
    }
    */
    /**
     * 导出清关系统基础数据表处理列表
     */
    @RequiresPermissions("shipperModule:data:export")
    @Log(title = "清关系统基础数据表处理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ImpBasicData impBasicData)
    {
    	/*
    	impBasicData.setOrderEndFlag(1);  //历史订单
    	impBasicData.setOrderIsdraftFlag(0); //非草稿
        List<ImpBasicData> list = impBasicDataService.selectImpBasicDataList(impBasicData);
        ExcelUtil<ImpBasicData> util = new ExcelUtil<ImpBasicData>(ImpBasicData.class);
        return util.exportExcel(list, "data");
        */
    	return null;
    }
    
}
