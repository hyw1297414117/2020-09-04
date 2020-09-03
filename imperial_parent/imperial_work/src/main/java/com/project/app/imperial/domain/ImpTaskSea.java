package com.project.app.imperial.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.framework.aspectj.lang.annotation.Excel;
import com.framework.web.domain.BaseEntity;

/**
 * 添加任务-海运类别详细对象 imp_task_sea
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
public class ImpTaskSea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 任务号 */
    @Excel(name = "任务号")
    private String taskNumber;

    /** 海运主单号 */
    @Excel(name = "海运主单号")
    private String seaMainOrderNo;

    /** 集装箱号 */
    @Excel(name = "集装箱号")
    private String containerNumber;

    /** 船运公司名 */
    @Excel(name = "船运公司名")
    private String shippingName;

    /** 船运公司代码 */
    @Excel(name = "船运公司代码")
    private String shippingCode;

    /** 船班次号 */
    @Excel(name = "船班次号")
    private String shipFrequency;

    /** 预计到达时间 */
    @Excel(name = "预计到达时间")
    private String arrivalTime;

    /** 计费重量 */
    @Excel(name = "计费重量")
    private String chargedWeight;

    /** 实际重量 */
    @Excel(name = "实际重量")
    private String actualWeight;

    /** 体积 */
    @Excel(name = "体积")
    private String volume;

    /** 装载数量 */
    @Excel(name = "装载数量")
    private String loadAmount;

    /** 装载单位 */
    @Excel(name = "装载单位")
    private String loadUnit;

    /** 付费方式 */
    @Excel(name = "付费方式")
    private String payType;

    /** 放行方式 */
    @Excel(name = "放行方式")
    private String releaseWay;

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
    public void setSeaMainOrderNo(String seaMainOrderNo) 
    {
        this.seaMainOrderNo = seaMainOrderNo;
    }

    public String getSeaMainOrderNo() 
    {
        return seaMainOrderNo;
    }
    public void setContainerNumber(String containerNumber) 
    {
        this.containerNumber = containerNumber;
    }

    public String getContainerNumber() 
    {
        return containerNumber;
    }
    public void setShippingName(String shippingName) 
    {
        this.shippingName = shippingName;
    }

    public String getShippingName() 
    {
        return shippingName;
    }
    public void setShippingCode(String shippingCode) 
    {
        this.shippingCode = shippingCode;
    }

    public String getShippingCode() 
    {
        return shippingCode;
    }
    public void setShipFrequency(String shipFrequency) 
    {
        this.shipFrequency = shipFrequency;
    }

    public String getShipFrequency() 
    {
        return shipFrequency;
    }
    public void setArrivalTime(String arrivalTime) 
    {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalTime() 
    {
        return arrivalTime;
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
    public void setVolume(String volume) 
    {
        this.volume = volume;
    }

    public String getVolume() 
    {
        return volume;
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
    public void setReleaseWay(String releaseWay) 
    {
        this.releaseWay = releaseWay;
    }

    public String getReleaseWay() 
    {
        return releaseWay;
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
            .append("seaMainOrderNo", getSeaMainOrderNo())
            .append("containerNumber", getContainerNumber())
            .append("shippingName", getShippingName())
            .append("shippingCode", getShippingCode())
            .append("shipFrequency", getShipFrequency())
            .append("arrivalTime", getArrivalTime())
            .append("chargedWeight", getChargedWeight())
            .append("actualWeight", getActualWeight())
            .append("volume", getVolume())
            .append("loadAmount", getLoadAmount())
            .append("loadUnit", getLoadUnit())
            .append("payType", getPayType())
            .append("releaseWay", getReleaseWay())
            .append("notes", getNotes())
            .append("insertitme", getInsertitme())
            .append("updatetime", getUpdatetime())
            .toString();
    }
}
