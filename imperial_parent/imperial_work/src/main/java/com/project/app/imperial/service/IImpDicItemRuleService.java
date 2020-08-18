package com.project.app.imperial.service;

import java.util.List;

import com.project.app.imperial.domain.ImpDicItemRule;

/**
 * 字典条目规则操作Service接口
 * 
 * @author fanke
 * @date 2020-07-15
 */
public interface IImpDicItemRuleService 
{
    /**
     * 查询字典条目规则操作
     * 
     * @param id 字典条目规则操作ID
     * @return 字典条目规则操作
     */
    public ImpDicItemRule selectImpDicItemRuleById(Long id);

    /**
     * 查询字典条目规则操作列表
     * 
     * @param impDicItemRule 字典条目规则操作
     * @return 字典条目规则操作集合
     */
    public List<ImpDicItemRule> selectImpDicItemRuleList(ImpDicItemRule impDicItemRule);

    /**
     * 新增字典条目规则操作
     * 
     * @param impDicItemRule 字典条目规则操作
     * @return 结果
     */
    public int insertImpDicItemRule(ImpDicItemRule impDicItemRule);

    /**
     * 修改字典条目规则操作
     * 
     * @param impDicItemRule 字典条目规则操作
     * @return 结果
     */
    public int updateImpDicItemRule(ImpDicItemRule impDicItemRule);

    /**
     * 批量删除字典条目规则操作
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpDicItemRuleByIds(String ids);

    /**
     * 删除字典条目规则操作信息
     * 
     * @param id 字典条目规则操作ID
     * @return 结果
     */
    public int deleteImpDicItemRuleById(Long id);
}
