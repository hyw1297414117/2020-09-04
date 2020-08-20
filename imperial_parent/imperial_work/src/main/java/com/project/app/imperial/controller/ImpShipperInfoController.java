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
import com.project.app.imperial.domain.ImpShipperInfo;
import com.project.app.imperial.service.IImpShipperInfoService;
import com.framework.web.controller.BaseController;
import com.framework.web.domain.AjaxResult;
import com.common.utils.poi.ExcelUtil;
import com.framework.web.page.TableDataInfo;

/**
 * 发货方基本信息操作Controller
 * 
 * @author zhudemao
 * @date 2020-08-20
 */
@Controller
@RequestMapping("/shipperInfo")
public class ImpShipperInfoController extends BaseController
{
    private String prefix = "/app/shipperregister";

    @Autowired
    private IImpShipperInfoService impShipperInfoService;

    @RequiresPermissions("shipperInfo:data:view")
    @GetMapping()
    public String imperial()
    {
        return prefix + "/imperial";
    }

    /**
     * 查询发货方基本信息列表
     */
    @RequiresPermissions("shipperInfo:data:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ImpShipperInfo impShipperInfo)
    {
        startPage();
        List<ImpShipperInfo> list = impShipperInfoService.selectImpShipperInfoList(impShipperInfo);
        return getDataTable(list);
    }

    /**
     * 导出发货方基本信息操作列表
     */
    @RequiresPermissions("shipperInfo:data:export")
    @Log(title = "发货方基本信息操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ImpShipperInfo impShipperInfo)
    {
        List<ImpShipperInfo> list = impShipperInfoService.selectImpShipperInfoList(impShipperInfo);
        ExcelUtil<ImpShipperInfo> util = new ExcelUtil<ImpShipperInfo>(ImpShipperInfo.class);
        return util.exportExcel(list, "imperial");
    }

    /**
     * 新增发货方基本信息操作
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存发货方基本信息操作
     */
    @RequiresPermissions("shipperInfo:data:add")
    @Log(title = "发货方基本信息操作", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ImpShipperInfo impShipperInfo)
    {
        return toAjax(impShipperInfoService.insertImpShipperInfo(impShipperInfo));
    }

    /**
     * 修改发货方基本信息操作
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ImpShipperInfo impShipperInfo = impShipperInfoService.selectImpShipperInfoById(id);
        mmap.put("impShipperInfo", impShipperInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存发货方基本信息操作
     */
    @RequiresPermissions("shipperInfo:data:edit")
    @Log(title = "发货方基本信息操作", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ImpShipperInfo impShipperInfo)
    {
        return toAjax(impShipperInfoService.updateImpShipperInfo(impShipperInfo));
    }

    /**
     * 删除发货方基本信息操作
     */
    @RequiresPermissions("shipperInfo:data:remove")
    @Log(title = "发货方基本信息操作", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(impShipperInfoService.deleteImpShipperInfoByIds(ids));
    }
}
