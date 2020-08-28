package com.project.app.imperial.mapper;

import java.util.List;
import com.project.app.imperial.domain.ImpMainnoTaskno;

/**
 * 主单号和任务号映射Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
public interface ImpMainnoTasknoMapper 
{
    /**
     * 查询主单号和任务号映射
     * 
     * @param id 主单号和任务号映射ID
     * @return 主单号和任务号映射
     */
    public ImpMainnoTaskno selectImpMainnoTasknoById(Long id);

    /**
     * 查询主单号和任务号映射列表
     * 
     * @param impMainnoTaskno 主单号和任务号映射
     * @return 主单号和任务号映射集合
     */
    public List<ImpMainnoTaskno> selectImpMainnoTasknoList(ImpMainnoTaskno impMainnoTaskno);

    /**
     * 新增主单号和任务号映射
     * 
     * @param impMainnoTaskno 主单号和任务号映射
     * @return 结果
     */
    public int insertImpMainnoTaskno(ImpMainnoTaskno impMainnoTaskno);

    /**
     * 修改主单号和任务号映射
     * 
     * @param impMainnoTaskno 主单号和任务号映射
     * @return 结果
     */
    public int updateImpMainnoTaskno(ImpMainnoTaskno impMainnoTaskno);

    /**
     * 删除主单号和任务号映射
     * 
     * @param id 主单号和任务号映射ID
     * @return 结果
     */
    public int deleteImpMainnoTasknoById(Long id);

    /**
     * 批量删除主单号和任务号映射
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpMainnoTasknoByIds(String[] ids);
}
