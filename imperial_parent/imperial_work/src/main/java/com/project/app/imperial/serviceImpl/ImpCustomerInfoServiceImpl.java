package com.project.app.imperial.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.app.imperial.mapper.ImpCustomerInfoMapper;
import com.project.app.imperial.domain.ImpCustomerInfo;
import com.project.app.imperial.service.IImpCustomerInfoService;
import com.common.utils.text.Convert;

/**
 * 发货人信息完善Service业务层处理
 * 
 * @author zhudemao
 * @date 2020-09-02
 */
@Service
public class ImpCustomerInfoServiceImpl implements IImpCustomerInfoService 
{
    @Autowired
    private ImpCustomerInfoMapper impCustomerInfoMapper;

    /**
     * 查询发货人信息完善
     * 
     * @param id 发货人信息完善ID
     * @return 发货人信息完善
     */
    @Override
    public ImpCustomerInfo selectImpCustomerInfoByName(String customer)
    {
        return impCustomerInfoMapper.selectImpCustomerInfoByName(customer);
    }

    /**
     * 查询发货人信息完善列表
     * 
     * @param impCustomerInfo 发货人信息完善
     * @return 发货人信息完善
     */
    @Override
    public List<ImpCustomerInfo> selectImpCustomerInfoList(ImpCustomerInfo impCustomerInfo)
    {
        return impCustomerInfoMapper.selectImpCustomerInfoList(impCustomerInfo);
    }

    /**
     * 新增发货人信息完善
     * 
     * @param impCustomerInfo 发货人信息完善
     * @return 结果
     */
    @Override
    public int insertImpCustomerInfo(ImpCustomerInfo impCustomerInfo)
    {
        return impCustomerInfoMapper.insertImpCustomerInfo(impCustomerInfo);
    }

    /**
     * 修改发货人信息完善
     * 
     * @param impCustomerInfo 发货人信息完善
     * @return 结果
     */
    @Override
    public int updateImpCustomerInfo(ImpCustomerInfo impCustomerInfo)
    {
        return impCustomerInfoMapper.updateImpCustomerInfo(impCustomerInfo);
    }

    /**
     * 删除发货人信息完善对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImpCustomerInfoByIds(String ids)
    {
        return impCustomerInfoMapper.deleteImpCustomerInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除发货人信息完善信息
     * 
     * @param id 发货人信息完善ID
     * @return 结果
     */
    @Override
    public int deleteImpCustomerInfoById(Long id)
    {
        return impCustomerInfoMapper.deleteImpCustomerInfoById(id);
    }
}
