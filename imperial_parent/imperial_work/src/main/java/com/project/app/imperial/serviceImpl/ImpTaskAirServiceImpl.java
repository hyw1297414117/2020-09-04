package com.project.app.imperial.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.app.imperial.mapper.ImpTaskAirMapper;
import com.project.app.imperial.domain.ImpTaskAir;
import com.project.app.imperial.service.IImpTaskAirService;
import com.common.utils.text.Convert;

/**
 * 添加任务-空运类别详细Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
@Service
public class ImpTaskAirServiceImpl implements IImpTaskAirService 
{
    @Autowired
    private ImpTaskAirMapper impTaskAirMapper;

    /**
     * 查询添加任务-空运类别详细
     * 
     * @param id 添加任务-空运类别详细ID
     * @return 添加任务-空运类别详细
     */
    @Override
    public ImpTaskAir selectImpTaskAirById(Long id)
    {
        return impTaskAirMapper.selectImpTaskAirById(id);
    }

    /**
     * 查询添加任务-空运类别详细列表
     * 
     * @param impTaskAir 添加任务-空运类别详细
     * @return 添加任务-空运类别详细
     */
    @Override
    public List<ImpTaskAir> selectImpTaskAirList(ImpTaskAir impTaskAir)
    {
        return impTaskAirMapper.selectImpTaskAirList(impTaskAir);
    }

    /**
     * 新增添加任务-空运类别详细
     * 
     * @param impTaskAir 添加任务-空运类别详细
     * @return 结果
     */
    @Override
    public int insertImpTaskAir(ImpTaskAir impTaskAir)
    {
        return impTaskAirMapper.insertImpTaskAir(impTaskAir);
    }

    /**
     * 修改添加任务-空运类别详细
     * 
     * @param impTaskAir 添加任务-空运类别详细
     * @return 结果
     */
    @Override
    public int updateImpTaskAir(ImpTaskAir impTaskAir)
    {
        return impTaskAirMapper.updateImpTaskAir(impTaskAir);
    }

    /**
     * 删除添加任务-空运类别详细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImpTaskAirByIds(String ids)
    {
        return impTaskAirMapper.deleteImpTaskAirByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除添加任务-空运类别详细信息
     * 
     * @param id 添加任务-空运类别详细ID
     * @return 结果
     */
    @Override
    public int deleteImpTaskAirById(Long id)
    {
        return impTaskAirMapper.deleteImpTaskAirById(id);
    }
}
