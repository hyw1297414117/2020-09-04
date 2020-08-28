package com.project.app.imperial.mapper;

import java.util.List;
import com.project.app.imperial.domain.ImpTaskAir;

/**
 * 添加任务-空运类别详细Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
public interface ImpTaskAirMapper 
{
    /**
     * 查询添加任务-空运类别详细
     * 
     * @param id 添加任务-空运类别详细ID
     * @return 添加任务-空运类别详细
     */
    public ImpTaskAir selectImpTaskAirById(Long id);

    /**
     * 查询添加任务-空运类别详细列表
     * 
     * @param impTaskAir 添加任务-空运类别详细
     * @return 添加任务-空运类别详细集合
     */
    public List<ImpTaskAir> selectImpTaskAirList(ImpTaskAir impTaskAir);

    /**
     * 新增添加任务-空运类别详细
     * 
     * @param impTaskAir 添加任务-空运类别详细
     * @return 结果
     */
    public int insertImpTaskAir(ImpTaskAir impTaskAir);

    /**
     * 修改添加任务-空运类别详细
     * 
     * @param impTaskAir 添加任务-空运类别详细
     * @return 结果
     */
    public int updateImpTaskAir(ImpTaskAir impTaskAir);

    /**
     * 删除添加任务-空运类别详细
     * 
     * @param id 添加任务-空运类别详细ID
     * @return 结果
     */
    public int deleteImpTaskAirById(Long id);

    /**
     * 批量删除添加任务-空运类别详细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpTaskAirByIds(String[] ids);
}
