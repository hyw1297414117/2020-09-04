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
import com.framework.aspectj.lang.annotation.Log;
import com.framework.aspectj.lang.enums.BusinessType;
import com.project.app.imperial.domain.ImpCustomerInfo;
import com.project.app.imperial.service.IImpCustomerInfoService;
import com.framework.web.controller.BaseController;
import com.framework.web.domain.AjaxResult;
import com.common.utils.poi.ExcelUtil;
import com.framework.web.page.TableDataInfo;

/**
 * 发货人信息完善Controller
 * 
 * @author zhudemao
 * @date 2020-09-02
 */
@Controller
@RequestMapping("/customerInfo")
public class ImpCustomerInfoController extends BaseController
{
    private String prefix = "/app/shipperregister";

    @Autowired
    private IImpCustomerInfoService impCustomerInfoService;

    @RequiresPermissions("customerInfo:data:view")
    @GetMapping()
    public String imperial()
    {
        return prefix + "/imperial";
    }

    /**
     * 
     * @Title ImpCustomerInfoController.java 
     * @description TODO 跳转到发货账户详情页面
     * @time 2020年9月3日 下午3:17:32 
     * @author zhudemao
     * @version 1.0 
     */
    @RequiresPermissions("customerInfo:data:view")
    @GetMapping("/showCustomerInfoDetail/{customer}")
    public String showCustomerInfoDetail(@PathVariable("customer") String customer,ModelMap mmap)
    {
    	//根据用户名查询发货人详细信息
    	ImpCustomerInfo impCustomerInfo = impCustomerInfoService.selectImpCustomerInfoByName(customer);
    	mmap.put("impCustomerInfo", impCustomerInfo);
    	return prefix + "/customerInfoDetail";
    }
    
    /**
     * 查询发货人信息完善列表
     */
    
    @RequiresPermissions("customerInfo:imperial:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ImpCustomerInfo impCustomerInfo)
    {
        startPage();
        List<ImpCustomerInfo> list = impCustomerInfoService.selectImpCustomerInfoList(impCustomerInfo);
        return getDataTable(list);
    }
    

    /**
     * 导出发货人信息完善列表
     */
    /*
    @RequiresPermissions("customerInfo:imperial:export")
    @Log(title = "发货人信息完善", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ImpCustomerInfo impCustomerInfo)
    {
        List<ImpCustomerInfo> list = impCustomerInfoService.selectImpCustomerInfoList(impCustomerInfo);
        ExcelUtil<ImpCustomerInfo> util = new ExcelUtil<ImpCustomerInfo>(ImpCustomerInfo.class);
        return util.exportExcel(list, "imperial");
    }
    */

    /**
     * 新增发货人信息完善
     */
    /*
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
	*/
    /**
     * 新增保存发货人信息完善
     */
    
    @RequiresPermissions("customerInfo:data:view")
    @Log(title = "发货人信息完善", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ImpCustomerInfo impCustomerInfo)
    {
        return toAjax(impCustomerInfoService.insertImpCustomerInfo(impCustomerInfo));
    }
    /**
     * 修改发货人信息完善
     */
    /*
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ImpCustomerInfo impCustomerInfo = impCustomerInfoService.selectImpCustomerInfoById(id);
        mmap.put("impCustomerInfo", impCustomerInfo);
        return prefix + "/edit";
    }
     */

    /**
     * 修改保存发货人信息完善
     */
    @RequiresPermissions("customerInfo:data:view")
    @Log(title = "发货人信息完善", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ImpCustomerInfo impCustomerInfo)
    {
        return toAjax(impCustomerInfoService.updateImpCustomerInfo(impCustomerInfo));
    }

    /**
     * 删除发货人信息完善
     */
    /*
    @RequiresPermissions("customerInfo:data:view")
    @Log(title = "发货人信息完善", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(impCustomerInfoService.deleteImpCustomerInfoByIds(ids));
    }
    */
}
