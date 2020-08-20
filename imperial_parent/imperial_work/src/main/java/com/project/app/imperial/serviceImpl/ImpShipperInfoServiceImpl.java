package com.project.app.imperial.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.app.imperial.mapper.ImpShipperInfoMapper;
import com.project.app.imperial.domain.ImpShipperInfo;
import com.project.app.imperial.service.IImpShipperInfoService;
import com.common.utils.text.Convert;

/**
 * 发货方基本信息操作Service业务层处理
 * 
 * @author zhudemao
 * @date 2020-08-20
 */
@Service
public class ImpShipperInfoServiceImpl implements IImpShipperInfoService 
{
    @Autowired
    private ImpShipperInfoMapper impShipperInfoMapper;

    /**
     * 查询发货方基本信息操作
     * 
     * @param id 发货方基本信息操作ID
     * @return 发货方基本信息操作
     */
    @Override
    public ImpShipperInfo selectImpShipperInfoById(Long id)
    {
        return impShipperInfoMapper.selectImpShipperInfoById(id);
    }

    /**
     * 查询发货方基本信息操作列表
     * 
     * @param impShipperInfo 发货方基本信息操作
     * @return 发货方基本信息操作
     */
    @Override
    public List<ImpShipperInfo> selectImpShipperInfoList(ImpShipperInfo impShipperInfo)
    {
        return impShipperInfoMapper.selectImpShipperInfoList(impShipperInfo);
    }

    /**
     * 新增发货方基本信息操作
     * 
     * @param impShipperInfo 发货方基本信息操作
     * @return 结果
     */
    @Override
    public int insertImpShipperInfo(ImpShipperInfo impShipperInfo)
    {
        return impShipperInfoMapper.insertImpShipperInfo(impShipperInfo);
    }

    /**
     * 修改发货方基本信息操作
     * 
     * @param impShipperInfo 发货方基本信息操作
     * @return 结果
     */
    @Override
    public int updateImpShipperInfo(ImpShipperInfo impShipperInfo)
    {
        return impShipperInfoMapper.updateImpShipperInfo(impShipperInfo);
    }

    /**
     * 删除发货方基本信息操作对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImpShipperInfoByIds(String ids)
    {
        return impShipperInfoMapper.deleteImpShipperInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除发货方基本信息操作信息
     * 
     * @param id 发货方基本信息操作ID
     * @return 结果
     */
    @Override
    public int deleteImpShipperInfoById(Long id)
    {
        return impShipperInfoMapper.deleteImpShipperInfoById(id);
    }
}
