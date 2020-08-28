package com.project.app.imperial.service;

import java.util.List;
import com.project.app.imperial.domain.ImpTaskConsult;

/**
 * 添加任务-咨询类别详细Service接口
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
public interface IImpTaskConsultService 
{
    /**
     * 查询添加任务-咨询类别详细
     * 
     * @param id 添加任务-咨询类别详细ID
     * @return 添加任务-咨询类别详细
     */
    public ImpTaskConsult selectImpTaskConsultById(Long id);

    /**
     * 查询添加任务-咨询类别详细列表
     * 
     * @param impTaskConsult 添加任务-咨询类别详细
     * @return 添加任务-咨询类别详细集合
     */
    public List<ImpTaskConsult> selectImpTaskConsultList(ImpTaskConsult impTaskConsult);

    /**
     * 新增添加任务-咨询类别详细
     * 
     * @param impTaskConsult 添加任务-咨询类别详细
     * @return 结果
     */
    public int insertImpTaskConsult(ImpTaskConsult impTaskConsult);

    /**
     * 修改添加任务-咨询类别详细
     * 
     * @param impTaskConsult 添加任务-咨询类别详细
     * @return 结果
     */
    public int updateImpTaskConsult(ImpTaskConsult impTaskConsult);

    /**
     * 批量删除添加任务-咨询类别详细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpTaskConsultByIds(String ids);

    /**
     * 删除添加任务-咨询类别详细信息
     * 
     * @param id 添加任务-咨询类别详细ID
     * @return 结果
     */
    public int deleteImpTaskConsultById(Long id);
}
