package com.project.app.imperial.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.framework.aspectj.lang.annotation.Excel;
import com.framework.web.domain.BaseEntity;

/**
 * 添加任务-空运类别详细对象 imp_task_air
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
public class ImpTaskAir extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 任务号 */
    @Excel(name = "任务号")
    private String taskNumber;

    /** 空运主单号 */
    @Excel(name = "空运主单号")
    private String airMainOrderNo;

    /** 航班号 */
    @Excel(name = "航班号")
    private String flightNumber;

    /** 预计到达时间 */
    @Excel(name = "预计到达时间")
    private String arrivalTime;

    /** 航空公司名 */
    @Excel(name = "航空公司名")
    private String airlineName;

    /** 航空公司代码 */
    @Excel(name = "航空公司代码")
    private String airlineCode;

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
    private Date insertitme;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatetime;

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
    public void setAirMainOrderNo(String airMainOrderNo) 
    {
        this.airMainOrderNo = airMainOrderNo;
    }

    public String getAirMainOrderNo() 
    {
        return airMainOrderNo;
    }
    public void setFlightNumber(String flightNumber) 
    {
        this.flightNumber = flightNumber;
    }

    public String getFlightNumber() 
    {
        return flightNumber;
    }
    public void setArrivalTime(String arrivalTime) 
    {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalTime() 
    {
        return arrivalTime;
    }
    public void setAirlineName(String airlineName) 
    {
        this.airlineName = airlineName;
    }

    public String getAirlineName() 
    {
        return airlineName;
    }
    public void setAirlineCode(String airlineCode) 
    {
        this.airlineCode = airlineCode;
    }

    public String getAirlineCode() 
    {
        return airlineCode;
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
    public void setInsertitme(Date insertitme) 
    {
        this.insertitme = insertitme;
    }

    public Date getInsertitme() 
    {
        return insertitme;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskNumber", getTaskNumber())
            .append("airMainOrderNo", getAirMainOrderNo())
            .append("flightNumber", getFlightNumber())
            .append("arrivalTime", getArrivalTime())
            .append("airlineName", getAirlineName())
            .append("airlineCode", getAirlineCode())
            .append("chargedWeight", getChargedWeight())
            .append("actualWeight", getActualWeight())
            .append("loadAmount", getLoadAmount())
            .append("loadUnit", getLoadUnit())
            .append("payType", getPayType())
            .append("notes", getNotes())
            .append("insertitme", getInsertitme())
            .append("updatetime", getUpdatetime())
            .toString();
    }
}
