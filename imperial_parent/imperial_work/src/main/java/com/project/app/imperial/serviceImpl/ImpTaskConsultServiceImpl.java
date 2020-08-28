package com.project.app.imperial.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.app.imperial.mapper.ImpTaskConsultMapper;
import com.project.app.imperial.domain.ImpTaskConsult;
import com.project.app.imperial.service.IImpTaskConsultService;
import com.common.utils.text.Convert;

/**
 * 添加任务-咨询类别详细Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
@Service
public class ImpTaskConsultServiceImpl implements IImpTaskConsultService 
{
    @Autowired
    private ImpTaskConsultMapper impTaskConsultMapper;

    /**
     * 查询添加任务-咨询类别详细
     * 
     * @param id 添加任务-咨询类别详细ID
     * @return 添加任务-咨询类别详细
     */
    @Override
    public ImpTaskConsult selectImpTaskConsultById(Long id)
    {
        return impTaskConsultMapper.selectImpTaskConsultById(id);
    }

    /**
     * 查询添加任务-咨询类别详细列表
     * 
     * @param impTaskConsult 添加任务-咨询类别详细
     * @return 添加任务-咨询类别详细
     */
    @Override
    public List<ImpTaskConsult> selectImpTaskConsultList(ImpTaskConsult impTaskConsult)
    {
        return impTaskConsultMapper.selectImpTaskConsultList(impTaskConsult);
    }

    /**
     * 新增添加任务-咨询类别详细
     * 
     * @param impTaskConsult 添加任务-咨询类别详细
     * @return 结果
     */
    @Override
    public int insertImpTaskConsult(ImpTaskConsult impTaskConsult)
    {
        return impTaskConsultMapper.insertImpTaskConsult(impTaskConsult);
    }

    /**
     * 修改添加任务-咨询类别详细
     * 
     * @param impTaskConsult 添加任务-咨询类别详细
     * @return 结果
     */
    @Override
    public int updateImpTaskConsult(ImpTaskConsult impTaskConsult)
    {
        return impTaskConsultMapper.updateImpTaskConsult(impTaskConsult);
    }

    /**
     * 删除添加任务-咨询类别详细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImpTaskConsultByIds(String ids)
    {
        return impTaskConsultMapper.deleteImpTaskConsultByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除添加任务-咨询类别详细信息
     * 
     * @param id 添加任务-咨询类别详细ID
     * @return 结果
     */
    @Override
    public int deleteImpTaskConsultById(Long id)
    {
        return impTaskConsultMapper.deleteImpTaskConsultById(id);
    }
}
