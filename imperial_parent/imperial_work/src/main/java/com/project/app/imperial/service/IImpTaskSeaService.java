package com.project.app.imperial.service;

import java.util.List;
import com.project.app.imperial.domain.ImpTaskSea;

/**
 * 添加任务-海运类别详细Service接口
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
public interface IImpTaskSeaService 
{
    /**
     * 查询添加任务-海运类别详细
     * 
     * @param id 添加任务-海运类别详细ID
     * @return 添加任务-海运类别详细
     */
    public ImpTaskSea selectImpTaskSeaById(Long id);

    /**
     * 查询添加任务-海运类别详细列表
     * 
     * @param impTaskSea 添加任务-海运类别详细
     * @return 添加任务-海运类别详细集合
     */
    public List<ImpTaskSea> selectImpTaskSeaList(ImpTaskSea impTaskSea);

    /**
     * 新增添加任务-海运类别详细
     * 
     * @param impTaskSea 添加任务-海运类别详细
     * @return 结果
     */
    public int insertImpTaskSea(ImpTaskSea impTaskSea);

    /**
     * 修改添加任务-海运类别详细
     * 
     * @param impTaskSea 添加任务-海运类别详细
     * @return 结果
     */
    public int updateImpTaskSea(ImpTaskSea impTaskSea);

    /**
     * 批量删除添加任务-海运类别详细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpTaskSeaByIds(String ids);

    /**
     * 删除添加任务-海运类别详细信息
     * 
     * @param id 添加任务-海运类别详细ID
     * @return 结果
     */
    public int deleteImpTaskSeaById(Long id);
}
