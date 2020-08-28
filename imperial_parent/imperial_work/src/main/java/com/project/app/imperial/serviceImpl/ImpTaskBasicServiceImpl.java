package com.project.app.imperial.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.app.imperial.mapper.ImpTaskBasicMapper;
import com.project.app.imperial.domain.ImpTaskBasic;
import com.project.app.imperial.service.IImpTaskBasicService;
import com.common.utils.text.Convert;

/**
 * 添加任务-基础数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
@Service
public class ImpTaskBasicServiceImpl implements IImpTaskBasicService 
{
    @Autowired
    private ImpTaskBasicMapper impTaskBasicMapper;

    /**
     * 查询添加任务-基础数据
     * 
     * @param id 添加任务-基础数据ID
     * @return 添加任务-基础数据
     */
    @Override
    public ImpTaskBasic selectImpTaskBasicById(Long id)
    {
        return impTaskBasicMapper.selectImpTaskBasicById(id);
    }

    /**
     * 查询添加任务-基础数据列表
     * 
     * @param impTaskBasic 添加任务-基础数据
     * @return 添加任务-基础数据
     */
    @Override
    public List<ImpTaskBasic> selectImpTaskBasicList(ImpTaskBasic impTaskBasic)
    {
        return impTaskBasicMapper.selectImpTaskBasicList(impTaskBasic);
    }

    /**
     * 新增添加任务-基础数据
     * 
     * @param impTaskBasic 添加任务-基础数据
     * @return 结果
     */
    @Override
    public int insertImpTaskBasic(ImpTaskBasic impTaskBasic)
    {
        return impTaskBasicMapper.insertImpTaskBasic(impTaskBasic);
    }

    /**
     * 修改添加任务-基础数据
     * 
     * @param impTaskBasic 添加任务-基础数据
     * @return 结果
     */
    @Override
    public int updateImpTaskBasic(ImpTaskBasic impTaskBasic)
    {
        return impTaskBasicMapper.updateImpTaskBasic(impTaskBasic);
    }

    /**
     * 删除添加任务-基础数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImpTaskBasicByIds(String ids)
    {
        return impTaskBasicMapper.deleteImpTaskBasicByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除添加任务-基础数据信息
     * 
     * @param id 添加任务-基础数据ID
     * @return 结果
     */
    @Override
    public int deleteImpTaskBasicById(Long id)
    {
        return impTaskBasicMapper.deleteImpTaskBasicById(id);
    }
}
