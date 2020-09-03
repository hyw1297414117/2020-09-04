package com.project.app.imperial.service;

import com.project.app.imperial.domain.ImpTaskBasic;
import com.project.app.imperial.vo.ImpTaskBasicVo;

import java.util.List;
import java.util.Map;

public interface IImpTaskService {
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
     * 多条件查询任务
     *
     * @param conditions 条件
     * @return 添加任务-基础数据集合
     */
    public List<ImpTaskBasicVo> selectImpTaskBasicsByConditions(Map<String,Object> conditions);

    /**
     * 查询任务-当天最新一条数据
     *
     * @return 添加任务-基础数据
     */
    public String selectTaskNumLatestToday();

    /**
     * 修改添加任务-基础数据
     *
     * @param impTaskBasic 添加任务-基础数据
     * @return 结果
     */
    public int updateImpTaskBasic(ImpTaskBasic impTaskBasic);

    /**
     * 批量删除任务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpTaskBasicByIds(String ids);

    /**
     * 删除任务
     *
     * @param id 添加任务-基础数据ID
     * @return 结果
     */
    public int deleteImpTaskBasicById(Long id);

    /**
     * 创建任务
     *
     * @param taskData 添加任务-基础数据ID
     * @return 任务流水号，失败为空
     */
    String createTask(Map<String, Object> taskData);
}
