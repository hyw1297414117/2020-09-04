package com.project.app.imperial.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.framework.aspectj.lang.annotation.Excel;
import com.framework.web.domain.BaseEntity;

/**
 * 添加任务-咨询类别详细对象 imp_task_consult
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
public class ImpTaskConsult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 任务号 */
    @Excel(name = "任务号")
    private String taskNumber;

    /** 咨询领域 */
    @Excel(name = "咨询领域")
    private String consultField;

    /** 咨询期 */
    @Excel(name = "咨询期")
    private String consultPeriod;

    /** 补充说明 */
    @Excel(name = "补充说明")
    private String notes;

    /** 插入时间 */
    @Excel(name = "插入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date insertitme;

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
    public void setConsultField(String consultField) 
    {
        this.consultField = consultField;
    }

    public String getConsultField() 
    {
        return consultField;
    }
    public void setConsultPeriod(String consultPeriod) 
    {
        this.consultPeriod = consultPeriod;
    }

    public String getConsultPeriod() 
    {
        return consultPeriod;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskNumber", getTaskNumber())
            .append("consultField", getConsultField())
            .append("consultPeriod", getConsultPeriod())
            .append("notes", getNotes())
            .append("insertitme", getInsertitme())
//            .append("updatetime", getUpdatetime())
            .toString();
    }
}
