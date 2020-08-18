package com.project.app.imperial.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.utils.text.Convert;
import com.project.app.imperial.domain.ImpDicItemRule;
import com.project.app.imperial.mapper.ImpDicItemRuleMapper;
import com.project.app.imperial.service.IImpDicItemRuleService;

/**
 * 字典条目规则操作Service业务层处理
 * 
 * @author fanke
 * @date 2020-07-15
 */
@Service
public class ImpDicItemRuleServiceImpl implements IImpDicItemRuleService 
{
    @Autowired
    private ImpDicItemRuleMapper impDicItemRuleMapper;

    /**
     * 查询字典条目规则操作
     * 
     * @param id 字典条目规则操作ID
     * @return 字典条目规则操作
     */
    @Override
    public ImpDicItemRule selectImpDicItemRuleById(Long id)
    {
        return impDicItemRuleMapper.selectImpDicItemRuleById(id);
    }

    /**
     * 查询字典条目规则操作列表
     * 
     * @param impDicItemRule 字典条目规则操作
     * @return 字典条目规则操作
     */
    @Override
    public List<ImpDicItemRule> selectImpDicItemRuleList(ImpDicItemRule impDicItemRule)
    {
        return impDicItemRuleMapper.selectImpDicItemRuleList(impDicItemRule);
    }

    /**
     * 新增字典条目规则操作
     * 
     * @param impDicItemRule 字典条目规则操作
     * @return 结果
     */
    @Override
    public int insertImpDicItemRule(ImpDicItemRule impDicItemRule)
    {
        return impDicItemRuleMapper.insertImpDicItemRule(impDicItemRule);
    }

    /**
     * 修改字典条目规则操作
     * 
     * @param impDicItemRule 字典条目规则操作
     * @return 结果
     */
    @Override
    public int updateImpDicItemRule(ImpDicItemRule impDicItemRule)
    {
        return impDicItemRuleMapper.updateImpDicItemRule(impDicItemRule);
    }

    /**
     * 删除字典条目规则操作对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImpDicItemRuleByIds(String ids)
    {
        return impDicItemRuleMapper.deleteImpDicItemRuleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除字典条目规则操作信息
     * 
     * @param id 字典条目规则操作ID
     * @return 结果
     */
    @Override
    public int deleteImpDicItemRuleById(Long id)
    {
        return impDicItemRuleMapper.deleteImpDicItemRuleById(id);
    }
}
