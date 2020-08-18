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
@RequestMapping("/shipperModule/impBasicDataDraft")
public class ImpBasicDataDraftController extends BaseController
{
    private String prefix = "/app/impbasicdatadraft";

    @Autowired
    private IImpBasicDataService impBasicDataService;

    @RequiresPermissions("shipperModule:data:view")
    @GetMapping()
    public String data()
    {
        return prefix + "/datadraft";
    }

    /**
     * 查询清关系统基础数据表处理列表（订单草稿）
     */
    /*
    @RequiresPermissions("shipperModule:data:list")
    @PostMapping("/draftList")
    @ResponseBody
    public TableDataInfo draftList(ImpBasicData impBasicData)
    {
    	Map<String,Object> paramsMap = new HashMap<String,Object>();
        startPage();
        impBasicData.setOrderIsdraftFlag(1);  //草稿订单
        impBasicData.setOrderEndFlag(0); //非历史
        List<ImpBasicData> list = impBasicDataService.selectImpBasicDataList(impBasicData);
        return getDataTable(list);
    }
    */
    /**
     * 修改清关系统基础数据草稿数据
     */
    @GetMapping("/editdraft/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ImpBasicData impBasicData = impBasicDataService.selectImpBasicDataById(id);
        mmap.put("impBasicData", impBasicData);
        return prefix + "/editdraft";
    }
    
    /**
     * 修改保存清关系统基础数据草稿数据TO标准
     */
    @RequiresPermissions("shipperModule:data:edit")
    @Log(title = "清关系统基础数据表处理", businessType = BusinessType.UPDATE)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/editToStandard")
    @ResponseBody
    public AjaxResult editSaveToStandard(ImpBasicData impBasicData)
    {
    	impBasicData.setOrderIsdraftFlag(0);
        return toAjax(impBasicDataService.updateImpBasicData(impBasicData));
    }
    
    /**
     * 修改保存清关系统基础数据草稿数据TO草稿
     */
    @RequiresPermissions("shipperModule:data:edit")
    @Log(title = "清关系统基础数据表处理", businessType = BusinessType.UPDATE)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/editToDraft")
    @ResponseBody
    public AjaxResult editSaveToDraft(ImpBasicData impBasicData)
    {
        return toAjax(impBasicDataService.updateImpBasicData(impBasicData));
    }

    /**
     * 删除清关系统基础数据草稿数据
     */
    @RequiresPermissions("shipperModule:data:remove")
    @Log(title = "清关系统基础数据表处理", businessType = BusinessType.DELETE)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(impBasicDataService.deleteImpBasicDataByIds(ids));
    }
    
    /**
     *导出清关基础数据模板 用Excel导入数据时用
     */
    @RequiresPermissions("shipperModule:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<ImpBasicData> util = new ExcelUtil<ImpBasicData>(ImpBasicData.class);
        return util.importTemplateExcel("订单数据Excel模板");
    }
    /**
     * 导入清关系统基础数据表处理
     */
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("shipperModule:data:import")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport,String draftFlag) throws Exception
    {
    	Integer idraftFlag = null;
    	if(draftFlag!=null&&draftFlag!="") {
    		idraftFlag = Integer.parseInt(draftFlag);
    	}
        ExcelUtil<ImpBasicData> util = new ExcelUtil<ImpBasicData>(ImpBasicData.class);
        List<ImpBasicData> impBasicDataList = util.importExcel(file.getInputStream());
        if(draftFlag!=null&&draftFlag.equals("1")) {  //有存为草稿标识
        	for (ImpBasicData impBasicData : impBasicDataList) {
        		impBasicData.setOrderIsdraftFlag(1);
			}
        }
        String message = impBasicDataService.importImpBasicData(impBasicDataList, updateSupport,idraftFlag);
        return AjaxResult.success(message);
    }
}
