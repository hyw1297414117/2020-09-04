package com.project.app.imperial.service;

import java.util.List;
import com.project.app.imperial.domain.ImpShipperInfo;

/**
 * 发货方基本信息操作Service接口
 * 
 * @author zhudemao
 * @date 2020-08-20
 */
public interface IImpShipperInfoService 
{
    /**
     * 查询发货方基本信息操作
     * 
     * @param id 发货方基本信息操作ID
     * @return 发货方基本信息操作
     */
    public ImpShipperInfo selectImpShipperInfoById(Long id);

    /**
     * 查询发货方基本信息操作列表
     * 
     * @param impShipperInfo 发货方基本信息操作
     * @return 发货方基本信息操作集合
     */
    public List<ImpShipperInfo> selectImpShipperInfoList(ImpShipperInfo impShipperInfo);

    /**
     * 新增发货方基本信息操作
     * 
     * @param impShipperInfo 发货方基本信息操作
     * @return 结果
     */
    public int insertImpShipperInfo(ImpShipperInfo impShipperInfo);

    /**
     * 修改发货方基本信息操作
     * 
     * @param impShipperInfo 发货方基本信息操作
     * @return 结果
     */
    public int updateImpShipperInfo(ImpShipperInfo impShipperInfo);

    /**
     * 批量删除发货方基本信息操作
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpShipperInfoByIds(String ids);

    /**
     * 删除发货方基本信息操作信息
     * 
     * @param id 发货方基本信息操作ID
     * @return 结果
     */
    public int deleteImpShipperInfoById(Long id);
}
