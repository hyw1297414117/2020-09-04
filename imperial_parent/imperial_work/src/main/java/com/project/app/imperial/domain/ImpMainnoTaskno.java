package com.project.app.imperial.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.framework.aspectj.lang.annotation.Excel;
import com.framework.web.domain.BaseEntity;

/**
 * 主单号和任务号映射对象 imp_mainno_taskno
 * 
 * @author ruoyi
 * @date 2020-08-28
 */
public class ImpMainnoTaskno extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 主单号 */
    @Excel(name = "主单号")
    private String mainOrderNo;

    /** 任务号 */
    @Excel(name = "任务号")
    private String taskNumber;

    /** $column.columnComment */
    @Excel(name = "任务号", width = 30, dateFormat = "yyyy-MM-dd")
    private Date insertTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMainOrderNo(String mainOrderNo) 
    {
        this.mainOrderNo = mainOrderNo;
    }

    public String getMainOrderNo() 
    {
        return mainOrderNo;
    }
    public void setTaskNumber(String taskNumber) 
    {
        this.taskNumber = taskNumber;
    }

    public String getTaskNumber() 
    {
        return taskNumber;
    }
    public void setInsertTime(Date insertTime) 
    {
        this.insertTime = insertTime;
    }

    public Date getInsertTime() 
    {
        return insertTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mainOrderNo", getMainOrderNo())
            .append("taskNumber", getTaskNumber())
            .append("insertTime", getInsertTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
