package com.project.app.imperial.mapper;

import java.util.List;

import com.project.app.imperial.domain.ImpDicItem;
import com.project.app.imperial.domain.ImpOrdercheckHis;

/**
 * 字典条目操作Mapper接口
 * 
 * @author fanke
 * @date 2020-07-15
 */
public interface ImpOrdercheckHisMapper 
{
    /**
     * 查询字典条目操作
     * 
     * @param id 字典条目操作ID
     * @return 字典条目操作
     */
    public ImpOrdercheckHis selectImpDicItemById(Long id);

    /**
     * 查询字典条目操作列表
     * 
     * @param impDicItem 字典条目操作
     * @return 字典条目操作集合
     */
    public List<ImpOrdercheckHis> selectImpOrdercheckHisList(ImpOrdercheckHis impOrdercheckHis);

    /**
     * 新增字典条目操作
     * 
     * @param impDicItem 字典条目操作
     * @return 结果
     */
    public int insertOrdercheckHis(ImpOrdercheckHis impOrdercheckHis);

   
}
