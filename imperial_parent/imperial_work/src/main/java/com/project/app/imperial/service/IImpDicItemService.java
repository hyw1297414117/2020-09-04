package com.project.app.imperial.service;

import java.util.List;

import com.project.app.imperial.domain.ImpDicItem;

/**
 * 字典条目操作Service接口
 * 
 * @author fanke
 * @date 2020-07-15
 */
public interface IImpDicItemService 
{
    /**
     * 查询字典条目操作
     * 
     * @param id 字典条目操作ID
     * @return 字典条目操作
     */
    public ImpDicItem selectImpDicItemById(Long id);

    /**
     * 查询字典条目操作列表
     * 
     * @param impDicItem 字典条目操作
     * @return 字典条目操作集合
     */
    public List<ImpDicItem> selectImpDicItemList(ImpDicItem impDicItem);

    /**
     * 新增字典条目操作
     * 
     * @param impDicItem 字典条目操作
     * @return 结果
     */
    public int insertImpDicItem(ImpDicItem impDicItem);

    /**
     * 修改字典条目操作
     * 
     * @param impDicItem 字典条目操作
     * @return 结果
     */
    public int updateImpDicItem(ImpDicItem impDicItem);

    /**
     * 批量删除字典条目操作
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpDicItemByIds(String ids);

    /**
     * 删除字典条目操作信息
     * 
     * @param id 字典条目操作ID
     * @return 结果
     */
    public int deleteImpDicItemById(Long id);
}
