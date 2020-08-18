package com.project.app.imperial.domain;

import java.util.Date;

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
public class ImpOrdercheckHis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNumber;

    /** 订单审核状态 */
    @Excel(name = "订单审核状态")
    private Integer checkFlag;
    /** 审核人 */
    @Excel(name = "审核人")
    private String checker;
    /** 意见类型 */
    @Excel(name = "意见类型")
    private String opinionType;
    /** 审核意见 */
    @Excel(name = "审核意见")
    private String checkOpinion;
    /** 插入时间 */
    @Excel(name = "插入时间")
    private Date insertTime;
    /** 更新时间 */
    @Excel(name = "更新时间")
    private Date updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public Integer getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(Integer checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getOpinionType() {
		return opinionType;
	}
	public void setOpinionType(String opinionType) {
		this.opinionType = opinionType;
	}
	public String getCheckOpinion() {
		return checkOpinion;
	}
	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    

    
}
