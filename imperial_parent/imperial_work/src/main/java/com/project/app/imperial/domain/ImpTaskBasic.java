package com.project.app.imperial.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.framework.aspectj.lang.annotation.Excel;
import com.framework.web.domain.BaseEntity;

/**
 * 添加任务-基础数据对象 imp_task_basic
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
public class ImpTaskBasic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 任务号 */
    @Excel(name = "任务号")
    private String taskNumber;

    /** 任务分类 */
    @Excel(name = "任务分类")
    private String taskType;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String businessType;

    /** 优先级 */
    @Excel(name = "优先级")
    private String priority;

    /** 关联任务 */
    @Excel(name = "关联任务")
    private String taskRelate;

    /** 客户选择 */
    @Excel(name = "客户选择")
    private String customer;

    /** 选择待审核文档 */
    @Excel(name = "选择待审核文档")
    private String pendCheckDoc;

    /** 任务起始地 */
    @Excel(name = "任务起始地")
    private String taskOrigin;

    /** 任务目的地 */
    @Excel(name = "任务目的地")
    private String taskDestination;

    /** 收费标准 */
    @Excel(name = "收费标准")
    private String tariff;

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
    public void setTaskType(String taskType) 
    {
        this.taskType = taskType;
    }

    public String getTaskType() 
    {
        return taskType;
    }
    public void setBusinessType(String businessType) 
    {
        this.businessType = businessType;
    }

    public String getBusinessType() 
    {
        return businessType;
    }
    public void setPriority(String priority) 
    {
        this.priority = priority;
    }

    public String getPriority() 
    {
        return priority;
    }
    public void setTaskRelate(String taskRelate) 
    {
        this.taskRelate = taskRelate;
    }

    public String getTaskRelate() 
    {
        return taskRelate;
    }
    public void setCustomer(String customer) 
    {
        this.customer = customer;
    }

    public String getCustomer() 
    {
        return customer;
    }
    public void setPendCheckDoc(String pendCheckDoc) 
    {
        this.pendCheckDoc = pendCheckDoc;
    }

    public String getPendCheckDoc() 
    {
        return pendCheckDoc;
    }
    public void setTaskOrigin(String taskOrigin) 
    {
        this.taskOrigin = taskOrigin;
    }

    public String getTaskOrigin() 
    {
        return taskOrigin;
    }
    public void setTaskDestination(String taskDestination) 
    {
        this.taskDestination = taskDestination;
    }

    public String getTaskDestination() 
    {
        return taskDestination;
    }
    public void setTariff(String tariff) 
    {
        this.tariff = tariff;
    }

    public String getTariff() 
    {
        return tariff;
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
            .append("taskType", getTaskType())
            .append("businessType", getBusinessType())
            .append("priority", getPriority())
            .append("taskRelate", getTaskRelate())
            .append("customer", getCustomer())
            .append("pendCheckDoc", getPendCheckDoc())
            .append("taskOrigin", getTaskOrigin())
            .append("taskDestination", getTaskDestination())
            .append("tariff", getTariff())
            .append("inserttime", getInserttime())
//            .append("updatetime", getUpdatetime())
            .toString();
    }
}
