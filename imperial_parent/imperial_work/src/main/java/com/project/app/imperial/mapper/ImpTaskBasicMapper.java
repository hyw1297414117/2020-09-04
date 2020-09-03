package com.project.app.imperial.mapper;

import java.util.List;
import java.util.Map;

import com.project.app.imperial.domain.ImpTaskBasic;
import com.project.app.imperial.vo.ImpTaskBasicVo;

/**
 * 添加任务-基础数据Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
public interface ImpTaskBasicMapper 
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
     * 删除添加任务-基础数据
     * 
     * @param id 添加任务-基础数据ID
     * @return 结果
     */
    public int deleteImpTaskBasicById(Long id);

    /**
     * 批量删除添加任务-基础数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpTaskBasicByIds(String[] ids);
}
