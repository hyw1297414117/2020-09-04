package com.project.app.imperial.service;

import java.util.List;
import com.project.app.imperial.domain.ImpTaskRoad;

/**
 * 添加任务-陆运类别详细Service接口
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
public interface IImpTaskRoadService 
{
    /**
     * 查询添加任务-陆运类别详细
     * 
     * @param id 添加任务-陆运类别详细ID
     * @return 添加任务-陆运类别详细
     */
    public ImpTaskRoad selectImpTaskRoadById(Long id);

    /**
     * 查询添加任务-陆运类别详细列表
     * 
     * @param impTaskRoad 添加任务-陆运类别详细
     * @return 添加任务-陆运类别详细集合
     */
    public List<ImpTaskRoad> selectImpTaskRoadList(ImpTaskRoad impTaskRoad);

    /**
     * 新增添加任务-陆运类别详细
     * 
     * @param impTaskRoad 添加任务-陆运类别详细
     * @return 结果
     */
    public int insertImpTaskRoad(ImpTaskRoad impTaskRoad);

    /**
     * 修改添加任务-陆运类别详细
     * 
     * @param impTaskRoad 添加任务-陆运类别详细
     * @return 结果
     */
    public int updateImpTaskRoad(ImpTaskRoad impTaskRoad);

    /**
     * 批量删除添加任务-陆运类别详细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpTaskRoadByIds(String ids);

    /**
     * 删除添加任务-陆运类别详细信息
     * 
     * @param id 添加任务-陆运类别详细ID
     * @return 结果
     */
    public int deleteImpTaskRoadById(Long id);
}
