package com.project.app.imperial.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.app.imperial.mapper.ImpTaskRoadMapper;
import com.project.app.imperial.domain.ImpTaskRoad;
import com.project.app.imperial.service.IImpTaskRoadService;
import com.common.utils.text.Convert;

/**
 * 添加任务-陆运类别详细Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
@Service
public class ImpTaskRoadServiceImpl implements IImpTaskRoadService 
{
    @Autowired
    private ImpTaskRoadMapper impTaskRoadMapper;

    /**
     * 查询添加任务-陆运类别详细
     * 
     * @param id 添加任务-陆运类别详细ID
     * @return 添加任务-陆运类别详细
     */
    @Override
    public ImpTaskRoad selectImpTaskRoadById(Long id)
    {
        return impTaskRoadMapper.selectImpTaskRoadById(id);
    }

    /**
     * 查询添加任务-陆运类别详细列表
     * 
     * @param impTaskRoad 添加任务-陆运类别详细
     * @return 添加任务-陆运类别详细
     */
    @Override
    public List<ImpTaskRoad> selectImpTaskRoadList(ImpTaskRoad impTaskRoad)
    {
        return impTaskRoadMapper.selectImpTaskRoadList(impTaskRoad);
    }

    /**
     * 新增添加任务-陆运类别详细
     * 
     * @param impTaskRoad 添加任务-陆运类别详细
     * @return 结果
     */
    @Override
    public int insertImpTaskRoad(ImpTaskRoad impTaskRoad)
    {
        return impTaskRoadMapper.insertImpTaskRoad(impTaskRoad);
    }

    /**
     * 修改添加任务-陆运类别详细
     * 
     * @param impTaskRoad 添加任务-陆运类别详细
     * @return 结果
     */
    @Override
    public int updateImpTaskRoad(ImpTaskRoad impTaskRoad)
    {
        return impTaskRoadMapper.updateImpTaskRoad(impTaskRoad);
    }

    /**
     * 删除添加任务-陆运类别详细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImpTaskRoadByIds(String ids)
    {
        return impTaskRoadMapper.deleteImpTaskRoadByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除添加任务-陆运类别详细信息
     * 
     * @param id 添加任务-陆运类别详细ID
     * @return 结果
     */
    @Override
    public int deleteImpTaskRoadById(Long id)
    {
        return impTaskRoadMapper.deleteImpTaskRoadById(id);
    }
}
