package com.project.app.imperial.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.framework.aspectj.lang.annotation.Excel;
import com.framework.web.domain.BaseEntity;

/**
 * 字典条目操作对象 imp_dic_item
 * 
 * @author fanke
 * @date 2020-07-15
 */
public class ImpDicItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 条目code */
    @Excel(name = "条目code")
    private String itemCode;

    /** 条目内容 */
    @Excel(name = "条目内容")
    private String itemContent;

    /** 条目状态 0无效 1有效 */
    @Excel(name = "条目状态 0无效 1有效")
    private Long status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }

    public String getItemCode()
    {
        return itemCode;
    }
    public void setItemContent(String itemContent)
    {
        this.itemContent = itemContent;
    }

    public String getItemContent()
    {
        return itemContent;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("itemCode", getItemCode())
            .append("itemContent", getItemContent())
            .append("status", getStatus())
            .toString();
    }
}
