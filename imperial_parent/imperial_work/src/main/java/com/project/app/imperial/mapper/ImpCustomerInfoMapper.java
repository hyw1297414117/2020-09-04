package com.project.app.imperial.mapper;

import java.util.List;
import com.project.app.imperial.domain.ImpCustomerInfo;

/**
 * 发货人信息完善Mapper接口
 * 
 * @author zhudemao
 * @date 2020-09-02
 */
public interface ImpCustomerInfoMapper 
{
    /**
     * 查询发货人信息完善
     * 
     * @param id 发货人信息完善ID
     * @return 发货人信息完善
     */
    public ImpCustomerInfo selectImpCustomerInfoByName(String customer);

    /**
     * 查询发货人信息完善列表
     * 
     * @param impCustomerInfo 发货人信息完善
     * @return 发货人信息完善集合
     */
    public List<ImpCustomerInfo> selectImpCustomerInfoList(ImpCustomerInfo impCustomerInfo);

    /**
     * 新增发货人信息完善
     * 
     * @param impCustomerInfo 发货人信息完善
     * @return 结果
     */
    public int insertImpCustomerInfo(ImpCustomerInfo impCustomerInfo);

    /**
     * 修改发货人信息完善
     * 
     * @param impCustomerInfo 发货人信息完善
     * @return 结果
     */
    public int updateImpCustomerInfo(ImpCustomerInfo impCustomerInfo);

    /**
     * 删除发货人信息完善
     * 
     * @param id 发货人信息完善ID
     * @return 结果
     */
    public int deleteImpCustomerInfoById(Long id);

    /**
     * 批量删除发货人信息完善
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpCustomerInfoByIds(String[] ids);
}
