package com.project.app.imperial.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.app.imperial.mapper.ImpTaskSeaMapper;
import com.project.app.imperial.domain.ImpTaskSea;
import com.project.app.imperial.service.IImpTaskSeaService;
import com.common.utils.text.Convert;

/**
 * 添加任务-海运类别详细Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
@Service
public class ImpTaskSeaServiceImpl implements IImpTaskSeaService 
{
    @Autowired
    private ImpTaskSeaMapper impTaskSeaMapper;

    /**
     * 查询添加任务-海运类别详细
     * 
     * @param id 添加任务-海运类别详细ID
     * @return 添加任务-海运类别详细
     */
    @Override
    public ImpTaskSea selectImpTaskSeaById(Long id)
    {
        return impTaskSeaMapper.selectImpTaskSeaById(id);
    }

    /**
     * 查询添加任务-海运类别详细列表
     * 
     * @param impTaskSea 添加任务-海运类别详细
     * @return 添加任务-海运类别详细
     */
    @Override
    public List<ImpTaskSea> selectImpTaskSeaList(ImpTaskSea impTaskSea)
    {
        return impTaskSeaMapper.selectImpTaskSeaList(impTaskSea);
    }

    /**
     * 新增添加任务-海运类别详细
     * 
     * @param impTaskSea 添加任务-海运类别详细
     * @return 结果
     */
    @Override
    public int insertImpTaskSea(ImpTaskSea impTaskSea)
    {
        return impTaskSeaMapper.insertImpTaskSea(impTaskSea);
    }

    /**
     * 修改添加任务-海运类别详细
     * 
     * @param impTaskSea 添加任务-海运类别详细
     * @return 结果
     */
    @Override
    public int updateImpTaskSea(ImpTaskSea impTaskSea)
    {
        return impTaskSeaMapper.updateImpTaskSea(impTaskSea);
    }

    /**
     * 删除添加任务-海运类别详细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImpTaskSeaByIds(String ids)
    {
        return impTaskSeaMapper.deleteImpTaskSeaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除添加任务-海运类别详细信息
     * 
     * @param id 添加任务-海运类别详细ID
     * @return 结果
     */
    @Override
    public int deleteImpTaskSeaById(Long id)
    {
        return impTaskSeaMapper.deleteImpTaskSeaById(id);
    }
}
