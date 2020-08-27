package com.project.app.imperial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.poi.ExcelUtil;
import com.common.utils.text.Convert;
import com.framework.aspectj.lang.annotation.Log;
import com.framework.aspectj.lang.enums.BusinessType;
import com.framework.web.controller.BaseController;
import com.framework.web.domain.AjaxResult;
import com.framework.web.page.TableDataInfo;
import com.project.app.imperial.domain.ImpBasicData;
import com.project.app.imperial.domain.ImpBagNumber;
import com.project.app.imperial.domain.ImpMainnoTackNo;
import com.project.app.imperial.service.IImpBasicDataService;
import com.project.system.user.domain.User;

/**
 * 清关系统基础数据表处理Controller
 * 
 * @author fanke
 * @date 2020-07-08
 */
@Controller
@RequestMapping("/shipperModule/impBasicData")
public class ImpBasicDataController extends BaseController
{
    private String prefix = "/app/impbasicdata";

    @Autowired
    private IImpBasicDataService impBasicDataService;

    @RequiresPermissions("shipperModule:data:view")
    @GetMapping()
    public String data()
    {
        return prefix + "/data";
    }

    @RequiresPermissions("shipperModule:data:view")
    @GetMapping("/orderenterpage")
    public String orderenterpage()
    {
        return prefix + "/orderenter";
    }
    
    /**
     * 查询清关系统基础数据表处理列表
     */
    @RequiresPermissions("shipperModule:data:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam Map<String, Object> params)
    {
    	JSONObject jsStr = JSONObject.parseObject((String)params.get("impParams"));
        startPage();
        List<ImpBasicData> list = impBasicDataService.selectImpBasicDataList(jsStr);
        return getDataTable(list);
    }
    
    /**
     * 查询清关系统基础数据表处理列表 包裹号构成的表
     */
    @RequiresPermissions("shipperModule:data:list")
    @PostMapping("/getBagNumberList")
    @ResponseBody
    public TableDataInfo impBasicDataFatlist(@RequestParam Map<String, Object> params)
    {
    	JSONObject jsStr = JSONObject.parseObject((String)params.get("impParams"));
    	System.out.println("===jsStr="+jsStr);
        startPage();
        List<ImpBagNumber> list = impBasicDataService.selectBagNumberList(jsStr);
        return getDataTable(list);
    }
    
    /**
           * 查询主单号列表
     * */
    @RequiresPermissions("shipperModule:data:list")
    @PostMapping("/getMainOrderNoList")
    @ResponseBody
    public TableDataInfo getMainOrderNoList(@RequestParam Map<String, Object> params) {
    	JSONObject jsStr = JSONObject.parseObject((String)params.get("impParams"));
    	startPage();
    	List<ImpMainnoTackNo> list = impBasicDataService.selectMainOrderNoList(jsStr);
    	return getDataTable(list);
    }
    
    /**
     * 发货人提交审核
     */
    @RequiresPermissions("shipperModule:data:submit")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/submit")
    @ResponseBody
    public Object submitExam(@RequestParam Map<String, Object> params)
    {
    	String ids = (String) params.get("ids");
    	String[] arrid = Convert.toStrArray(ids);
    	params.put("ids", arrid); //ids重新赋值
    	return impBasicDataService.updateImpBasicDataBylist(params);
    }
    
   
    /**
     * 导出清关系统基础数据表处理列表
     */
    @RequiresPermissions("shipperModule:data:export")
    @Log(title = "清关系统基础数据表处理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	//String key  = (String) params.get("dataParam");
    	//List<Object> json = JSON.parseArray(key);
    	//System.out.println("===JSON="+json.size());
        List<ImpBasicData> list = impBasicDataService.selectImpBasicDataList(params);
        ExcelUtil<ImpBasicData> util = new ExcelUtil<ImpBasicData>(ImpBasicData.class);
        return util.exportExcel(list, "data");
    }

    /**
     * 新增清关系统基础数据表处理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存清关系统基础数据表处理（正式数据）
     */
    @RequiresPermissions("shipperModule:data:add")
    @Log(title = "清关系统基础数据表处理", businessType = BusinessType.INSERT)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ImpBasicData impBasicData)
    {
        return toAjax(impBasicDataService.insertImpBasicData(impBasicData));
    }
    
    /**
     * 新增保存清关系统基础数据表处理（草稿数据）
     */
    @RequiresPermissions("shipperModule:data:add")
    @Log(title = "清关系统基础数据表处理", businessType = BusinessType.INSERT)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addDraft")
    @ResponseBody
    public AjaxResult addDraftSave(ImpBasicData impBasicData)
    {
    	impBasicData.setOrderIsdraftFlag(1);
        return toAjax(impBasicDataService.insertImpBasicData(impBasicData));
    }

    /**
     * 修改清关系统基础数据表处理
     * /basEditdraft/{id}
     * @PathVariable("id") Long id
     */
    @GetMapping("/toEditPage/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
    	//Long id = Long.parseLong(params.get("ids").toString());
        ImpBasicData impBasicData = impBasicDataService.selectImpBasicDataById(id);
        mmap.put("impBasicData", impBasicData);
        return prefix + "/edit";
    }
    
    /**
     * 修改保存清关系统基础数据表处理 存为标准数据
     */
    @RequiresPermissions("shipperModule:data:edit")
    @Log(title = "清关系统基础数据表处理", businessType = BusinessType.UPDATE)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ImpBasicData impBasicData)
    {
        return toAjax(impBasicDataService.updateImpBasicData(impBasicData));
    }
    
    /**
     * 修改保存清关系统基础数据表处理 存为草稿
     */
    @RequiresPermissions("shipperModule:data:edit")
    @Log(title = "清关系统基础数据表处理", businessType = BusinessType.UPDATE)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/editToDraft")
    @ResponseBody
    public AjaxResult editToDraftSave(ImpBasicData impBasicData)
    {
    	impBasicData.setOrderIsdraftFlag(1);
        return toAjax(impBasicDataService.updateImpBasicData(impBasicData));
    }
   

    /**
     * 删除清关系统基础数据表处理
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
        String message = impBasicDataService.importImpBasicData(impBasicDataList, updateSupport,idraftFlag);
        return AjaxResult.success(message);
    }
    
    /**
     * 校验追踪号
     */
    @PostMapping("/checkTackNumUnique")
    @ResponseBody
    public int checkTackNumUnique(String tackingNumber1,Integer draftFlag)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("tackingNumber1",tackingNumber1);//快递号1
        map.put("draftFlag",draftFlag);
        return impBasicDataService.checkTackNumUnique(map);
    }
}
