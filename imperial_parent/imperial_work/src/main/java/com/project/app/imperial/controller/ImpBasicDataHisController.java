package com.project.app.imperial.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.common.utils.poi.ExcelUtil;
import com.framework.aspectj.lang.annotation.Log;
import com.framework.aspectj.lang.enums.BusinessType;
import com.framework.web.controller.BaseController;
import com.framework.web.domain.AjaxResult;
import com.framework.web.page.TableDataInfo;
import com.project.app.imperial.domain.ImpBasicData;
import com.project.app.imperial.service.IImpBasicDataService;
import com.project.system.user.domain.User;

/**
 * 清关系统基础数据表处理Controller
 * 
 * @author fanke
 * @date 2020-07-08
 */
@Controller
@RequestMapping("/shipperModule/impBasicDatahis")
public class ImpBasicDataHisController extends BaseController
{
    private String prefix = "/app/impbasicdatahis";

    @Autowired
    private IImpBasicDataService impBasicDataService;

    @RequiresPermissions("shipperModule:data:view")
    @GetMapping()
    public String data()
    {
        return prefix + "/datahis";
    }

    /**
     * 查询清关系统基础数据表处理列表（历史订单）
     */
    /*
    @RequiresPermissions("shipperModule:data:list")
    @PostMapping("/historyList")
    @ResponseBody
    public TableDataInfo historyList(ImpBasicData impBasicData)
    {
        startPage();
        impBasicData.setOrderEndFlag(1);  //历史订单
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
