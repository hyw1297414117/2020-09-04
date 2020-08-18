package com.project.app.imperial.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.framework.aspectj.lang.annotation.Excel;
import com.framework.web.domain.BaseEntity;

/**
 * 字典条目规则操作对象 imp_dic_item_rule
 * 
 * @author fanke
 * @date 2020-07-15
 */
public class ImpDicItemRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 字典条目code */
    @Excel(name = "字典条目code")
    private String itemCode;

    /** 条目触发依托字段 */
    @Excel(name = "条目触发依托字段")
    private String touchField;

    /** 条目触发关键词 */
    @Excel(name = "条目触发关键词")
    private String keyWords;

    /** 状态 0无效1有效 */
    @Excel(name = "状态 0无效1有效")
    private Integer status;

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
    public void setTouchField(String touchField)
    {
        this.touchField = touchField;
    }

    public String getTouchField()
    {
        return touchField;
    }
    public void setKeyWords(String keyWords)
    {
        this.keyWords = keyWords;
    }

    public String getKeyWords()
    {
        return keyWords;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("itemCode", getItemCode())
            .append("touchField", getTouchField())
            .append("keyWords", getKeyWords())
            .append("status", getStatus())
            .toString();
    }
}
