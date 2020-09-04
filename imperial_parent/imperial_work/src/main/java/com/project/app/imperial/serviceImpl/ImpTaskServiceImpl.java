package com.project.app.imperial.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.common.utils.DateUtils;
import com.common.utils.PDFItextUtils;
import com.common.utils.TaskNumGenerater;
import com.common.utils.ZipFilesUtils;
import com.common.utils.text.Convert;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.app.imperial.domain.*;
import com.project.app.imperial.mapper.*;
import com.project.app.imperial.service.IImpTaskService;
import com.project.app.imperial.vo.ImpTaskBasicVo;
import com.project.system.user.domain.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

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

    /**
     * 生成任务PDF
     *
     * @param ids 任务-基础数据IDs
     * @return 文件名
     */
    @Override
    public String createPdf(String ids)
    {
        List<File> pdfFileList = new ArrayList<>();
        Map<String,Object> conditions = new HashMap<>();
        String[] idArr = Convert.toStrArray(ids);
        String zipName=null;
        for(String taskId:idArr){
            conditions.put("taskId",Integer.valueOf(taskId));
            List<ImpTaskBasicVo> impTaskBasicVos = impTaskBasicMapper.selectImpTaskBasicsByConditions(conditions);
            File pdfFile=null;
            try {
                pdfFile = createTaskPDF(impTaskBasicVos.get(0));
                pdfFileList.add(pdfFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            zipName = "/taskPDFs"+new Date().getTime()+".zip";
            ZipFilesUtils.zip(pdfFileList,zipName);
            return zipName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
    public File createTaskPDF(ImpTaskBasicVo taskVo) throws IOException {
        Document document = new Document(PageSize.A4);
        File file = new File("/task-" + taskVo.getImpTaskBasic().getTaskNumber()+".pdf");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
            document.addTitle("TaskInfo");
            document.open();
            PdfPTable title = new PdfPTable(1);
            title.addCell(PDFItextUtils.createCenterPdfPCell("任务基本信息"));
            PdfPTable table = new PdfPTable(2);//生成一个两列的表格
            table.addCell(PDFItextUtils.createCenterPdfPCell("任务编号"));
            table.addCell(PDFItextUtils.createCenterPdfPCell(taskVo.getImpTaskBasic().getTaskNumber()));
            table.addCell(PDFItextUtils.createCenterPdfPCell("业务类型"));
            table.addCell(PDFItextUtils.createCenterPdfPCell(taskVo.getImpTaskBasic().getBusinessType()));
//            table.addCell(PDFItextUtils.createCenterPdfPCell("任务类型"));
//            table.addCell(PDFItextUtils.createCenterPdfPCell(taskVo.getImpTaskBasic().getTaskType()));
            table.addCell(PDFItextUtils.createCenterPdfPCell("优先级"));
            table.addCell(PDFItextUtils.createCenterPdfPCell(taskVo.getImpTaskBasic().getPriority()));
            table.addCell(PDFItextUtils.createCenterPdfPCell("客户"));
            table.addCell(PDFItextUtils.createCenterPdfPCell("张三"));
            table.addCell(PDFItextUtils.createCenterPdfPCell("关联主单"));
            table.addCell(PDFItextUtils.createCenterPdfPCell(taskVo.getImpMainnoTaskno().getMainOrderNo()));
            table.addCell(PDFItextUtils.createCenterPdfPCell("起始地"));
            table.addCell(PDFItextUtils.createCenterPdfPCell(taskVo.getImpTaskBasic().getTaskOrigin()));
            table.addCell(PDFItextUtils.createCenterPdfPCell("目的地"));
            table.addCell(PDFItextUtils.createCenterPdfPCell(taskVo.getImpTaskBasic().getTaskDestination()));
            table.addCell(PDFItextUtils.createCenterPdfPCell("收费类型"));
            table.addCell(PDFItextUtils.createCenterPdfPCell(taskVo.getImpTaskBasic().getTariff()));
            table.addCell(PDFItextUtils.createCenterPdfPCell("创建时间"));
            table.addCell(PDFItextUtils.createCenterPdfPCell(DateUtils.dateTime(taskVo.getImpTaskBasic().getInserttime())));
            document.add(table);
            if(taskVo.getImpTaskBasic().getTaskType().contains("A")){
                PdfPTable tableA = new PdfPTable(1);
                tableA.addCell(PDFItextUtils.createCenterPdfPCell("空运信息"));
                tableA.addCell(PDFItextUtils.createCenterPdfPCell("……"));
                document.add(tableA);
            }
            if(taskVo.getImpTaskBasic().getTaskType().contains("B")){
                PdfPTable tableB = new PdfPTable(1);
                tableB.addCell(PDFItextUtils.createCenterPdfPCell("海运信息"));
                tableB.addCell(PDFItextUtils.createCenterPdfPCell("……"));
                document.add(tableB);
            }
            if(taskVo.getImpTaskBasic().getTaskType().contains("C")){
                PdfPTable tableC = new PdfPTable(1);
                tableC.addCell(PDFItextUtils.createCenterPdfPCell("陆运信息"));
                tableC.addCell(PDFItextUtils.createCenterPdfPCell("……"));
                document.add(tableC);
            }
            if(taskVo.getImpTaskBasic().getTaskType().contains("D")){
                PdfPTable tableD = new PdfPTable(1);
                tableD.addCell(PDFItextUtils.createCenterPdfPCell("咨询信息"));
                tableD.addCell(PDFItextUtils.createCenterPdfPCell("……"));
                document.add(tableD);
            }
        }  catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        return file;
    }
}
