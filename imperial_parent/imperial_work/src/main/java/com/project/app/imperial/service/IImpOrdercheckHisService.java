package com.project.app.imperial.service;

import java.util.List;

import com.project.app.imperial.domain.ImpOrdercheckHis;

public interface IImpOrdercheckHisService {
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
