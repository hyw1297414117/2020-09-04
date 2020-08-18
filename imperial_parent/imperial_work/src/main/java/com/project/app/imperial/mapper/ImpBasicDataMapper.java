package com.project.app.imperial.mapper;

import java.util.List;
import java.util.Map;

import com.project.app.imperial.domain.ImpBasicData;
import com.project.app.imperial.domain.ImpBagNumber;
import com.project.app.imperial.domain.ImpBasicRation;

/**
 * 清关系统基础数据表处理Mapper接口
 * 
 * @author fanke
 * @date 2020-07-08
 */
public interface ImpBasicDataMapper 
{
    /**
     * 查询清关系统基础数据表处理
     * 
     * @param id 清关系统基础数据表处理ID
     * @return 清关系统基础数据表处理
     */
    public ImpBasicData selectImpBasicDataById(Long id);
    
    /**
     * 查询清关系统基础数据表处理
     * 
     * @param tacking_number_1 清关系统基础数据表处理快递号1
     * @return 清关系统基础数据表处理
     */
    public ImpBasicData selectImpBasicDataByTackNum(Map<String,Object> map);

    /**
     * 查询是否有重复的快递号
     * 
     * @param tacking_number_1 清关系统基础数据表处理快递号1
     * @return 清关系统基础数据表处理
     */
    public int checkTackNumUnique(Map<String,Object> map);
    /**
     * 查询清关系统基础数据表处理列表
     * 
     * @param impBasicData 清关系统基础数据表处理
     * @return 清关系统基础数据表处理集合
     */
    public List<ImpBasicData> selectImpBasicDataList(Map<String,Object> map);

    /**
     * 新增清关系统基础数据表处理
     * 
     * @param impBasicData 清关系统基础数据表处理
     * @return 结果
     */
    public int insertImpBasicData(ImpBasicData impBasicData);

    /**
     * 修改清关系统基础数据表处理
     * 
     * @param impBasicData 清关系统基础数据表处理
     * @return 结果
     */
    public int updateImpBasicData(ImpBasicData impBasicData);

    /**基础数据提交审核*/
    public boolean updateImpBasicDataBylist(Map<String,Object> map);
    
    /**
     * 删除清关系统基础数据表处理
     * 
     * @param id 清关系统基础数据表处理ID
     * @return 结果
     */
    public int deleteImpBasicDataById(Long id);

    /**
     * 批量删除清关系统基础数据表处理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImpBasicDataByIds(String[] ids);
    
    /**
           * 查询基础数据的父类列表
     * */
    public List<ImpBagNumber> selectBagNumberList(Map<String,Object> map);

    /**
     * 查询主单号的list
     * */
    public List<ImpBasicRation> selectMainOrderNoList(Map<String,Object> map);

}
