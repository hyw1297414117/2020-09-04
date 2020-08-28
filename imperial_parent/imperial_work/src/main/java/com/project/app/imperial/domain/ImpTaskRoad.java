package com.project.app.imperial.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.framework.aspectj.lang.annotation.Excel;
import com.framework.web.domain.BaseEntity;

/**
 * 添加任务-陆运类别详细对象 imp_task_road
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
public class ImpTaskRoad extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 任务号 */
    @Excel(name = "任务号")
    private String taskNumber;

    /** 主单号 */
    @Excel(name = "主单号")
    private String roadMainOrderNo;

    /** 承运人 */
    @Excel(name = "承运人")
    private String carrier;

    /** 计费重量 */
    @Excel(name = "计费重量")
    private String chargedWeight;

    /** 实际重量 */
    @Excel(name = "实际重量")
    private String actualWeight;

    /** 装载数量 */
    @Excel(name = "装载数量")
    private String loadAmount;

    /** 装载单位 */
    @Excel(name = "装载单位")
    private String loadUnit;

    /** 付费方式 */
    @Excel(name = "付费方式")
    private String payType;

    /** 补充说明 */
    @Excel(name = "补充说明")
    private String notes;

    /** 插入时间 */
    @Excel(name = "插入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inserttime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTaskNumber(String taskNumber) 
    {
        this.taskNumber = taskNumber;
    }

    public String getTaskNumber() 
    {
        return taskNumber;
    }
    public void setRoadMainOrderNo(String roadMainOrderNo) 
    {
        this.roadMainOrderNo = roadMainOrderNo;
    }

    public String getRoadMainOrderNo() 
    {
        return roadMainOrderNo;
    }
    public void setCarrier(String carrier) 
    {
        this.carrier = carrier;
    }

    public String getCarrier() 
    {
        return carrier;
    }
    public void setChargedWeight(String chargedWeight) 
    {
        this.chargedWeight = chargedWeight;
    }

    public String getChargedWeight() 
    {
        return chargedWeight;
    }
    public void setActualWeight(String actualWeight) 
    {
        this.actualWeight = actualWeight;
    }

    public String getActualWeight() 
    {
        return actualWeight;
    }
    public void setLoadAmount(String loadAmount) 
    {
        this.loadAmount = loadAmount;
    }

    public String getLoadAmount() 
    {
        return loadAmount;
    }
    public void setLoadUnit(String loadUnit) 
    {
        this.loadUnit = loadUnit;
    }

    public String getLoadUnit() 
    {
        return loadUnit;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setNotes(String notes) 
    {
        this.notes = notes;
    }

    public String getNotes() 
    {
        return notes;
    }
    public void setInserttime(Date inserttime) 
    {
        this.inserttime = inserttime;
    }

    public Date getInserttime() 
    {
        return inserttime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskNumber", getTaskNumber())
            .append("roadMainOrderNo", getRoadMainOrderNo())
            .append("carrier", getCarrier())
            .append("chargedWeight", getChargedWeight())
            .append("actualWeight", getActualWeight())
            .append("loadAmount", getLoadAmount())
            .append("loadUnit", getLoadUnit())
            .append("payType", getPayType())
            .append("notes", getNotes())
            .append("inserttime", getInserttime())
//            .append("updatetime", getUpdatetime())
            .toString();
    }
}
