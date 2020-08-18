package com.project.app.imperial.controller;

import java.util.List;
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

import com.common.utils.poi.ExcelUtil;
import com.framework.aspectj.lang.annotation.Log;
import com.framework.aspectj.lang.enums.BusinessType;
import com.framework.web.controller.BaseController;
import com.framework.web.domain.AjaxResult;
import com.framework.web.page.TableDataInfo;
import com.project.app.imperial.domain.ImpDicItem;
import com.project.app.imperial.service.IImpDicItemService;

/**
 * 字典条目操作Controller
 * 
 * @author fanke
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/dict/dictitem")
public class ImpDicItemController extends BaseController
{
    private String prefix = "/app/dictitem";

    @Autowired
    private IImpDicItemService impDicItemService;

    @RequiresPermissions("dictitem:data:view")
    @GetMapping()
    public String imperial()
    {
        return prefix + "/dictitem";
    }

    /**
     * 查询字典条目操作列表
     */
    @RequiresPermissions("dictitem:data:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ImpDicItem impDicItem)
    {
        startPage();
        List<ImpDicItem> list = impDicItemService.selectImpDicItemList(impDicItem);
        return getDataTable(list);
    }

    /**
     * 导出字典条目操作列表
     */
    @RequiresPermissions("dictitem:data:export")
    @Log(title = "字典条目操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ImpDicItem impDicItem)
    {
        List<ImpDicItem> list = impDicItemService.selectImpDicItemList(impDicItem);
        ExcelUtil<ImpDicItem> util = new ExcelUtil<ImpDicItem>(ImpDicItem.class);
        return util.exportExcel(list, "imperial");
    }

    /**
     * 新增字典条目操作
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/dictitemadd";
    }

    /**
     * 新增保存字典条目操作
     */
    @RequiresPermissions("dictitem:data:add")
    @Log(title = "字典条目操作", businessType = BusinessType.INSERT)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ImpDicItem impDicItem)
    {
        return toAjax(impDicItemService.insertImpDicItem(impDicItem));
    }

    /**
     * 修改字典条目操作
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ImpDicItem impDicItem = impDicItemService.selectImpDicItemById(id);
        mmap.put("impDicItem", impDicItem);
        return prefix + "/dictitemedit";
    }

    /**
     * 修改保存字典条目操作
     */
    @RequiresPermissions("dictitem:data:edit")
    @Log(title = "字典条目操作", businessType = BusinessType.UPDATE)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ImpDicItem impDicItem)
    {
        return toAjax(impDicItemService.updateImpDicItem(impDicItem));
    }

    /**
     * 删除字典条目操作
     */
    @RequiresPermissions("dictitem:data:remove")
    @Log(title = "字典条目操作", businessType = BusinessType.DELETE)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(impDicItemService.deleteImpDicItemByIds(ids));
    }
}
