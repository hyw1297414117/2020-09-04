package com.project.app.imperial.service;

import java.util.List;
import com.project.app.imperial.domain.ImpTaskBasic;

/**
 * 添加任务-基础数据Service接口
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
public interface IImpTaskBasicService 
{
    /**
     * 查询添加任务-基础数据
     * 
     * @param id 添加任务-基础数据ID
     * @return 添加任务-基础数据
     */
    public ImpTaskBasic selectImpTaskBasicById(Long id);

    /**
     * 查询添加任务-基础数据列表
     * 
     * @param impTaskBasic 添加任务-基础数据
     * @return 添加任务-基础数据集合
     */
    public List<ImpTaskBasic> selectImpTaskBasicList(ImpTaskBasic impTaskBasic);

    /**
     * 新增添加任务-基础数据
     * 
     * @param impTaskBasic 添加任务-基础数据
     * @return 结果
     */
    public int insertImpTaskBasic(ImpTaskBasic impTaskBasic);

    /**
     * 修改添加任务-基础数据
     * 
     * @param impTaskBasic 添加任务-基础数据
     * @return 结果
     */
    public int updateImpTaskBasic(ImpTaskBasic impTaskBasic);

    /**
     * 批量删除添加任务-基础数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpTaskBasicByIds(String ids);

    /**
     * 删除添加任务-基础数据信息
     * 
     * @param id 添加任务-基础数据ID
     * @return 结果
     */
    public int deleteImpTaskBasicById(Long id);
}
