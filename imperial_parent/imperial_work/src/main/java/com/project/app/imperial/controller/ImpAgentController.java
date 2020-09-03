package com.project.app.imperial.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.common.utils.DateUtils;
import com.common.utils.TaskNumGenerater;
import com.framework.web.page.PageDomain;
import com.project.app.imperial.domain.*;
import com.project.app.imperial.service.*;
import com.project.app.imperial.vo.ImpTaskBasicVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.common.utils.poi.ExcelUtil;
import com.common.utils.text.Convert;
import com.framework.aspectj.lang.annotation.Log;
import com.framework.aspectj.lang.enums.BusinessType;
import com.framework.web.controller.BaseController;
import com.framework.web.domain.AjaxResult;
import com.framework.web.page.TableDataInfo;
import com.project.system.user.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 清关系统代理人审核处理Controller
 * 
 * @author fanke
 * @date 2020-07-08
 */
@Controller
@RequestMapping("/agentModule/agentData")
public class ImpAgentController extends BaseController
{
    private String prefix = "/app/agent";
    private String registerprefix = "/app/shipperregister";

    @Autowired
    private IImpBasicDataService impBasicDataService;
    
    @Autowired
    private IImpOrdercheckHisService impOrdercheckHisService;

    @Autowired
    private IImpTaskService impTaskService;

    @RequiresPermissions("agentModule:data:view")
    @GetMapping()
    public String data()
    {
        return prefix + "/agentdata";
    }
    
    @RequiresPermissions("agentModule:data:view")
    @GetMapping("/ddpHandle")
    public String ddpHandleView()
    {
        return prefix + "/ddplist";
    }
    
    @RequiresPermissions("agentModule:data:view")
    @GetMapping("/dduHandle")
    public String dduHandleView()
    {
        return prefix + "/ddulist";
    }

    /**
     * @ Description:任务创建页面
    */
    @GetMapping("/showCreateTaskPage")
    @RequiresPermissions("createTask:data:view")
    public String showCreateTaskPage()
    {
        return prefix + "/createtask";
    }

    /**
     * @ author     :LianZheng
     * @ Description:创建任务
     * @ Date       :2020-08-28
    */
    @PostMapping("/createTask")
    @RequiresPermissions("createTask:data:view")
    @ResponseBody
    @Transactional
    public AjaxResult createTask(@RequestBody Map<String,Object> taskData) {
        //任务流水号
        String taskNum = impTaskService.createTask(taskData);

        return AjaxResult.success(taskNum);
    }

    /**
     * @ author     :LianZheng
     * @ Description:任务综合查询页
     * @ Date       :2020-09-01
    */
    @GetMapping("/showQueryTaskPage")
    @RequiresPermissions("createTask:data:view")
    public String showQueryTaskPage() {
        return prefix + "/taskData";
    }

    /**
     * @ author     :LianZheng
     * @ Description:查询任务
     * @ Date       :2020-09-01
    */
    @PostMapping("/queryTask")
    @RequiresPermissions("createTask:data:view")
    @ResponseBody
    public TableDataInfo queryTask(@RequestBody Map<String,Object> params) {
        PageDomain pageDomain = JSON.parseObject(JSON.toJSONString(params), PageDomain.class);
        startPage(pageDomain);
        List<ImpTaskBasicVo> taskBasicList = impTaskService.selectImpTaskBasicsByConditions((Map) params.get("impParams"));
        return getDataTable(taskBasicList);
    }

    /**
     * @ author     :LianZheng
     * @ Description:撤销任务
     * @ Date       :2020-09-01
    */
    @PostMapping("/revokeTask")
    @RequiresPermissions("createTask:data:view")
    @ResponseBody
    public AjaxResult revokeTask(@RequestParam(value = "revokeTaskIds[]") String revokeTaskIds) {
        return toAjax(impTaskService.deleteImpTaskBasicByIds(revokeTaskIds));
    }




    /**
     * 
     * @Title ImpAgentController.java 
     * @description 展示注册页面 
     * @time 2020年8月15日 下午7:57:22 
     * @author zhudemao
     * @version 1.0 
    *
     */
    @RequiresPermissions("agentModule:data:view")
    @GetMapping("/showRegistPage")
    public String showShipperRegistPage() {
        return registerprefix + "/shipperregister";
    }
    
    /**
     * 
     * @Title ImpAgentController.java 
     * @description 展示清关系统基础数据表详情
     * @time 2020年8月15日 下午7:57:52 
     * @author zhudemao
     * @version 1.0 
    *
     */
    @GetMapping("/toEditPage/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ImpBasicData impBasicData = impBasicDataService.selectImpBasicDataById(id);
        mmap.put("impBasicData", impBasicData);
        return prefix + "/agentexamorder";
    }
    
    /**
     * 
     * @Title ImpAgentController.java 
     * @description 报关公司审核界定 
     * @time 2020年8月15日 下午7:58:20 
     * @author zhudemao
     * @version 1.0 
    *
     */
    @RequiresPermissions("agentModule:data:submit")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/agentCheck")
    @ResponseBody
    public Object agentCheck(@RequestParam Map<String, Object> params)
    {
    	User user = (User) SecurityUtils.getSubject().getPrincipal(); //获取当前登录用户信息
    	String ids = (String) params.get("ids");
    	String checkFlag = (String) params.get("checkFlag");
    	String isresubmitCheckFlag = (String) params.get("isresubmitCheckFlag");
    	Map<String,Object> map = new HashMap<String,Object>();
    	String[] arrid = Convert.toStrArray(ids);
    	map.put("ids", arrid);
    	map.put("checkFlag", checkFlag!=null?Integer.parseInt(checkFlag):null);
    	map.put("isresubmitCheckFlag", isresubmitCheckFlag!=null?Integer.parseInt(isresubmitCheckFlag):null);
    	
    	if(checkFlag!=null&&checkFlag.equals("2")) {   //申请被驳回
    		//将审核结果插入审核记录表中 
        	String tackingNumber1 = (String) params.get("tackingNumber1");
        	String opinionType = (String) params.get("opinionType");
        	String opinion = (String) params.get("opinion");
        	ImpOrdercheckHis impOrdercheckHis = new ImpOrdercheckHis();
        	impOrdercheckHis.setOrderNumber(tackingNumber1);
        	impOrdercheckHis.setCheckFlag(checkFlag!=null?Integer.parseInt(checkFlag):null);
        	impOrdercheckHis.setChecker(user.getLoginName());
        	impOrdercheckHis.setOpinionType(opinionType);
        	impOrdercheckHis.setCheckOpinion(opinion);
        	impOrdercheckHisService.insertOrdercheckHis(impOrdercheckHis);
    	}
    	return impBasicDataService.updateImpBasicDataBylist(map);
    }
    
    /**
     * 
     * @Title ImpAgentController.java 
     * @description查询清关系统基础数据表处理列表
     * @time 2020年8月15日 下午7:58:44 
     * @author zhudemao
     * @version 1.0 
    *
     */
    @RequiresPermissions("shipperModule:data:list")
    @PostMapping("/successList")
    @ResponseBody
    public TableDataInfo historyList()
    {
    	Map<String,Object> paramsMap = new HashMap<String,Object>();
        startPage();
        paramsMap.put("submitFlag", 1);
        List<ImpBasicData> list = impBasicDataService.selectImpBasicDataList(paramsMap);
        return getDataTable(list);
    }

    
}
