package com.project.app.imperial.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.framework.aspectj.lang.annotation.Excel;
import com.framework.web.domain.BaseEntity;

/**
 * 发货方基本信息操作对象 imp_shipper_info
 * 
 * @author zhudemao
 * @date 2020-08-20
 */
public class ImpShipperInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 机构名字 */
    @Excel(name = "机构名字")
    private String orgName;

    /** 机构代码 */
    @Excel(name = "机构代码")
    private String orgCode;

    /** 机构法人 */
    @Excel(name = "机构法人")
    private String orgLegal;

    /** 手机号 */
    @Excel(name = "手机号")
    private String telephone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 货物类型 */
    @Excel(name = "货物类型")
    private String goodsType;

    /** 结算规则关联 */
    @Excel(name = "结算规则关联")
    private String costRuleRelate;

    /** 托运方式 */
    @Excel(name = "托运方式")
    private String transType;

    /** 插入时间 */
    @Excel(name = "插入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inserttime;
    
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
    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    public String getOrgName()
    {
        return orgName;
    }
    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    public String getOrgCode()
    {
        return orgCode;
    }
    public void setOrgLegal(String orgLegal)
    {
        this.orgLegal = orgLegal;
    }

    public String getOrgLegal()
    {
        return orgLegal;
    }
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getTelephone()
    {
        return telephone;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setGoodsType(String goodsType)
    {
        this.goodsType = goodsType;
    }

    public String getGoodsType()
    {
        return goodsType;
    }
    public void setCostRuleRelate(String costRuleRelate)
    {
        this.costRuleRelate = costRuleRelate;
    }

    public String getCostRuleRelate()
    {
        return costRuleRelate;
    }
    public void setTransType(String transType)
    {
        this.transType = transType;
    }

    public String getTransType()
    {
        return transType;
    }
    public void setInserttime(Date inserttime)
    {
        this.inserttime = inserttime;
    }

    public Date getInserttime()
    {
        return inserttime;
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
            .append("orgName", getOrgName())
            .append("orgCode", getOrgCode())
            .append("orgLegal", getOrgLegal())
            .append("telephone", getTelephone())
            .append("email", getEmail())
            .append("goodsType", getGoodsType())
            .append("costRuleRelate", getCostRuleRelate())
            .append("transType", getTransType())
            .append("inserttime", getInserttime())
            .append("updatetime", getUpdatetime())
            .toString();
    }
}
