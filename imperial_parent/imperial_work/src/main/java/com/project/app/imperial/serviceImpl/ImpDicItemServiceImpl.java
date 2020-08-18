package com.project.app.imperial.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.utils.text.Convert;
import com.project.app.imperial.domain.ImpDicItem;
import com.project.app.imperial.mapper.ImpDicItemMapper;
import com.project.app.imperial.service.IImpDicItemService;

/**
 * 字典条目操作Service业务层处理
 * 
 * @author fanke
 * @date 2020-07-15
 */
@Service
public class ImpDicItemServiceImpl implements IImpDicItemService 
{
    @Autowired
    private ImpDicItemMapper impDicItemMapper;

    /**
     * 查询字典条目操作
     * 
     * @param id 字典条目操作ID
     * @return 字典条目操作
     */
    @Override
    public ImpDicItem selectImpDicItemById(Long id)
    {
        return impDicItemMapper.selectImpDicItemById(id);
    }

    /**
     * 查询字典条目操作列表
     * 
     * @param impDicItem 字典条目操作
     * @return 字典条目操作
     */
    @Override
    public List<ImpDicItem> selectImpDicItemList(ImpDicItem impDicItem)
    {
        return impDicItemMapper.selectImpDicItemList(impDicItem);
    }

    /**
     * 新增字典条目操作
     * 
     * @param impDicItem 字典条目操作
     * @return 结果
     */
    @Override
    public int insertImpDicItem(ImpDicItem impDicItem)
    {
        return impDicItemMapper.insertImpDicItem(impDicItem);
    }

    /**
     * 修改字典条目操作
     * 
     * @param impDicItem 字典条目操作
     * @return 结果
     */
    @Override
    public int updateImpDicItem(ImpDicItem impDicItem)
    {
        return impDicItemMapper.updateImpDicItem(impDicItem);
    }

    /**
     * 删除字典条目操作对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImpDicItemByIds(String ids)
    {
        return impDicItemMapper.deleteImpDicItemByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除字典条目操作信息
     * 
     * @param id 字典条目操作ID
     * @return 结果
     */
    @Override
    public int deleteImpDicItemById(Long id)
    {
        return impDicItemMapper.deleteImpDicItemById(id);
    }
}
