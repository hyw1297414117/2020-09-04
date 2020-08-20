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
import com.project.app.imperial.domain.ImpDicItemRule;
import com.project.app.imperial.service.IImpDicItemRuleService;

/**
 * 字典条目规则操作Controller
 * 
 * @author fanke
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/dict/dictitemrule")
public class ImpDicItemRuleController extends BaseController
{
    private String prefix = "/app/dictitemrule";

    @Autowired
    private IImpDicItemRuleService impDicItemRuleService;

    @RequiresPermissions("dictitemrule:data:view")
    @GetMapping()
    public String imperial()
    {
        return prefix + "/itemrule";
    }

    /**
     * 查询字典条目规则操作列表
     */
    @RequiresPermissions("dictitemrule:data:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ImpDicItemRule impDicItemRule)
    {
        startPage();
        List<ImpDicItemRule> list = impDicItemRuleService.selectImpDicItemRuleList(impDicItemRule);
        return getDataTable(list);
    }

    /**
     * 导出字典条目规则操作列表
     */
    @RequiresPermissions("dictitemrule:data:export")
    @Log(title = "字典条目规则操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ImpDicItemRule impDicItemRule)
    {
        List<ImpDicItemRule> list = impDicItemRuleService.selectImpDicItemRuleList(impDicItemRule);
        ExcelUtil<ImpDicItemRule> util = new ExcelUtil<ImpDicItemRule>(ImpDicItemRule.class);
        return util.exportExcel(list, "imperial");
    }

    /**
     * 新增字典条目规则操作
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/itemruleadd";
    }

    /**
     * 新增保存字典条目规则操作
     */
    @RequiresPermissions("dictitemrule:data:add")
    @Log(title = "字典条目规则操作", businessType = BusinessType.INSERT)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ImpDicItemRule impDicItemRule)
    {
        return toAjax(impDicItemRuleService.insertImpDicItemRule(impDicItemRule));
    }

    /**
     * 修改字典条目规则操作
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ImpDicItemRule impDicItemRule = impDicItemRuleService.selectImpDicItemRuleById(id);
        mmap.put("impDicItemRule", impDicItemRule);
        return prefix + "/itemruleedit";
    }

    /**
     * 修改保存字典条目规则操作
     */
    @RequiresPermissions("dictitemrule:data:edit")
    @Log(title = "字典条目规则操作", businessType = BusinessType.UPDATE)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ImpDicItemRule impDicItemRule)
    {
        return toAjax(impDicItemRuleService.updateImpDicItemRule(impDicItemRule));
    }

    /**
     * 删除字典条目规则操作
     */
    @RequiresPermissions("dictitemrule:data:remove")
    @Log(title = "字典条目规则操作", businessType = BusinessType.DELETE)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(impDicItemRuleService.deleteImpDicItemRuleByIds(ids));
    }
    //
}
