package com.project.app.imperial.serviceImpl;

import java.util.List;
import com.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.app.imperial.mapper.ImpMainnoTasknoMapper;
import com.project.app.imperial.domain.ImpMainnoTaskno;
import com.project.app.imperial.service.IImpMainnoTasknoService;
import com.common.utils.text.Convert;

/**
 * 主单号和任务号映射Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
@Service
public class ImpMainnoTasknoServiceImpl implements IImpMainnoTasknoService 
{
    @Autowired
    private ImpMainnoTasknoMapper impMainnoTasknoMapper;

    /**
     * 查询主单号和任务号映射
     * 
     * @param id 主单号和任务号映射ID
     * @return 主单号和任务号映射
     */
    @Override
    public ImpMainnoTaskno selectImpMainnoTasknoById(Long id)
    {
        return impMainnoTasknoMapper.selectImpMainnoTasknoById(id);
    }

    /**
     * 查询主单号和任务号映射列表
     * 
     * @param impMainnoTaskno 主单号和任务号映射
     * @return 主单号和任务号映射
     */
    @Override
    public List<ImpMainnoTaskno> selectImpMainnoTasknoList(ImpMainnoTaskno impMainnoTaskno)
    {
        return impMainnoTasknoMapper.selectImpMainnoTasknoList(impMainnoTaskno);
    }

    /**
     * 新增主单号和任务号映射
     * 
     * @param impMainnoTaskno 主单号和任务号映射
     * @return 结果
     */
    @Override
    public int insertImpMainnoTaskno(ImpMainnoTaskno impMainnoTaskno)
    {
        return impMainnoTasknoMapper.insertImpMainnoTaskno(impMainnoTaskno);
    }

    /**
     * 修改主单号和任务号映射
     * 
     * @param impMainnoTaskno 主单号和任务号映射
     * @return 结果
     */
    @Override
    public int updateImpMainnoTaskno(ImpMainnoTaskno impMainnoTaskno)
    {
        impMainnoTaskno.setUpdateTime(DateUtils.getNowDate());
        return impMainnoTasknoMapper.updateImpMainnoTaskno(impMainnoTaskno);
    }

    /**
     * 删除主单号和任务号映射对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImpMainnoTasknoByIds(String ids)
    {
        return impMainnoTasknoMapper.deleteImpMainnoTasknoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除主单号和任务号映射信息
     * 
     * @param id 主单号和任务号映射ID
     * @return 结果
     */
    @Override
    public int deleteImpMainnoTasknoById(Long id)
    {
        return impMainnoTasknoMapper.deleteImpMainnoTasknoById(id);
    }
}
