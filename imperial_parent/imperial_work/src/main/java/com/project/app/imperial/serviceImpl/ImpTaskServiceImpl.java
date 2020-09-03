package com.project.app.imperial.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.common.utils.DateUtils;
import com.common.utils.TaskNumGenerater;
import com.common.utils.text.Convert;
import com.project.app.imperial.domain.*;
import com.project.app.imperial.mapper.*;
import com.project.app.imperial.service.IImpTaskService;
import com.project.app.imperial.vo.ImpTaskBasicVo;
import com.project.system.user.domain.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ImpTaskServiceImpl implements IImpTaskService {
    @Autowired
    private ImpTaskBasicMapper impTaskBasicMapper;

    @Autowired
    private ImpTaskAirMapper impTaskAirMapper;

    @Autowired
    private ImpTaskSeaMapper impTaskSeaMapper;

    @Autowired
    private ImpTaskRoadMapper impTaskRoadMapper;

    @Autowired
    private ImpTaskConsultMapper impTaskConsultMapper;

    @Autowired
    private ImpMainnoTasknoMapper impMainnoTasknoMapper;


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

    @Override
    public List<ImpTaskBasicVo> selectImpTaskBasicsByConditions(Map<String, Object> conditions) {
        return impTaskBasicMapper.selectImpTaskBasicsByConditions(conditions);
    }

    /**
     * 查询任务-当天最新一条数据
     *
     * @return 添加任务-基础数据
     */
    @Override
    public String selectTaskNumLatestToday()
    {
        return impTaskBasicMapper.selectTaskNumLatestToday();
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
     * 批量删除任务
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
     * 删除任务
     *
     * @param id 添加任务-基础数据ID
     * @return 结果
     */
    @Override
    public int deleteImpTaskBasicById(Long id)
    {
        return impTaskBasicMapper.deleteImpTaskBasicById(id);
    }

    /**
     * 创建任务
     *
     * @param taskData 添加任务-基础数据ID
     * @return 任务流水号，失败为空
     */
    @Override
    public String createTask(Map<String, Object> taskData) {
        String taskNum = TaskNumGenerater.getInstance().generaterNextNumber(impTaskBasicMapper.selectTaskNumLatestToday());
        Date nowDate = DateUtils.getNowDate();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //基本任务数据
        ImpTaskBasic basicData = JSON.parseObject(JSON.toJSONString(taskData.get("basicData")), ImpTaskBasic.class);
        basicData.setTaskNumber(taskNum);
        basicData.setInserttime(nowDate);
        basicData.setCreator(user.getUserId());
        impTaskBasicMapper.insertImpTaskBasic(basicData);
        //关联主单
        ImpMainnoTaskno impMainnoTaskno = new ImpMainnoTaskno();
        impMainnoTaskno.setMainOrderNo(taskData.get("mainOrderNo").toString());
        impMainnoTaskno.setTaskNumber(taskNum);
        impMainnoTaskno.setInsertTime(nowDate);
        impMainnoTasknoMapper.insertImpMainnoTaskno(impMainnoTaskno);
        //任务运单数据
        if(taskData.containsKey("transportData")){
            Map transportData = (Map) taskData.get("transportData");
            if(transportData.containsKey("airMainOrderNo")){
                ImpTaskAir taskAir = JSON.parseObject(JSON.toJSONString(transportData), ImpTaskAir.class);
                taskAir.setTaskNumber(taskNum);
                taskAir.setInsertitme(nowDate);
                impTaskAirMapper.insertImpTaskAir(taskAir);
            }else if(transportData.containsKey("seaMainOrderNo")){
                ImpTaskSea taskSea = JSON.parseObject(JSON.toJSONString(transportData), ImpTaskSea.class);
                taskSea.setTaskNumber(taskNum);
                taskSea.setInsertitme(nowDate);
                impTaskSeaMapper.insertImpTaskSea(taskSea);
            }else if(transportData.containsKey("roadMainOrderNo")){
                ImpTaskRoad taskRoad = JSON.parseObject(JSON.toJSONString(transportData), ImpTaskRoad.class);
                taskRoad.setTaskNumber(taskNum);
                taskRoad.setInserttime(nowDate);
                impTaskRoadMapper.insertImpTaskRoad(taskRoad);
            }
        }
        //任务咨询数据
        if(taskData.containsKey("consultData")){
            ImpTaskConsult consultData = JSON.parseObject(JSON.toJSONString(taskData.get("consultData")), ImpTaskConsult.class);
            consultData.setTaskNumber(taskNum);
            consultData.setInsertitme(nowDate);
            impTaskConsultMapper.insertImpTaskConsult(consultData);
        }
        return taskNum;
    }
}
