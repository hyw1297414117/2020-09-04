package com.project.app.imperial.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.exception.BusinessException;
import com.common.utils.StringUtils;
import com.common.utils.text.Convert;
import com.project.app.imperial.domain.ImpBasicData;
import com.project.app.imperial.domain.ImpBagNumber;
import com.project.app.imperial.domain.ImpMainnoTackNo;
import com.project.app.imperial.mapper.ImpBasicDataMapper;
import com.project.app.imperial.service.IImpBasicDataService;

/**
 * 清关系统基础数据表处理Service业务层处理
 * 
 * @author fanke
 * @date 2020-07-08
 */
@Service
public class ImpBasicDataServiceImpl implements IImpBasicDataService 
{
	private static final Logger log = LoggerFactory.getLogger(ImpBasicDataServiceImpl.class);
    @Autowired
    private ImpBasicDataMapper impBasicDataMapper;

    /**
     * 查询清关系统基础数据表处理
     * 
     * @param id 清关系统基础数据表处理ID
     * @return 清关系统基础数据表处理
     */
    @Override
    public ImpBasicData selectImpBasicDataById(Long id)
    {
        return impBasicDataMapper.selectImpBasicDataById(id);
    }

    /**
     * 查询清关系统基础数据表处理
     * 
     * @param tackingNumber1 清关系统基础数据表处理tackingNumber1
     * @return 清关系统基础数据表处理
     */
    @Override
	public ImpBasicData selectImpBasicDataByTackNum(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return impBasicDataMapper.selectImpBasicDataByTackNum(map);
	}
    /**
     * 查询清关系统基础数据表处理列表
     * 
     * @param impBasicData 清关系统基础数据表处理
     * @return 清关系统基础数据表处理
     */
    @Override
    public List<ImpBasicData> selectImpBasicDataList(Map<String,Object> paramsMap)
    {
        return impBasicDataMapper.selectImpBasicDataList(paramsMap);
    }

    /**
     * 新增清关系统基础数据表处理
     * 
     * @param impBasicData 清关系统基础数据表处理
     * @return 结果
     */
    @Override
    public int insertImpBasicData(ImpBasicData impBasicData)
    {
        return impBasicDataMapper.insertImpBasicData(impBasicData);
    }

    /**
     * 修改清关系统基础数据表处理
     * 
     * @param impBasicData 清关系统基础数据表处理
     * @return 结果
     */
    @Override
    public int updateImpBasicData(ImpBasicData impBasicData)
    {
        return impBasicDataMapper.updateImpBasicData(impBasicData);
    }

    /**
     * 删除清关系统基础数据表处理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImpBasicDataByIds(String ids)
    {
        return impBasicDataMapper.deleteImpBasicDataByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除清关系统基础数据表处理信息
     * 
     * @param id 清关系统基础数据表处理ID
     * @return 结果
     */
    @Override
    public int deleteImpBasicDataById(Long id)
    {
        return impBasicDataMapper.deleteImpBasicDataById(id);
    }

	/**
     * 导入用户数据
     * 
     * @param impBasicDataList 数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importImpBasicData(List<ImpBasicData> impBasicDataList, Boolean isUpdateSupport,Integer draftFlag)
    {
        if (StringUtils.isNull(impBasicDataList) || impBasicDataList.size() == 0)
        {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ImpBasicData impBasicData : impBasicDataList)
        {
            try
            {
            	Map<String,Object> map = new HashMap<String,Object>();
            	map.put("tackingNumber1", impBasicData.getTackingNumber1());
            	map.put("draftFlag", draftFlag);
            	ImpBasicData ibd = impBasicDataMapper.selectImpBasicDataByTackNum(map);
                if (StringUtils.isNull(ibd))
                {
                    this.insertImpBasicData(impBasicData);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、快递号 " + impBasicData.getTackingNumber1() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                	impBasicData.setId(ibd.getId());  //因为模板里的数据是没ID的，在此设置ID
                    this.updateImpBasicData(impBasicData);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、快递号 " + impBasicData.getTackingNumber1() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、快递号 " + impBasicData.getTackingNumber1() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、快递号 " + impBasicData.getTackingNumber1() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

	@Override
	public int checkTackNumUnique(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return impBasicDataMapper.checkTackNumUnique(map);
	}

	@Override
	public boolean updateImpBasicDataBylist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return impBasicDataMapper.updateImpBasicDataBylist(map);
	}

	@Override
	public List<ImpBagNumber> selectBagNumberList(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return impBasicDataMapper.selectBagNumberList(map);
	}

	@Override
	public List<ImpMainnoTackNo> selectMainOrderNoList(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return impBasicDataMapper.selectMainOrderNoList(map);
	}

	
}
