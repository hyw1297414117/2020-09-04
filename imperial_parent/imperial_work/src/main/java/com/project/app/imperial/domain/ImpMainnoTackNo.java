package com.project.app.imperial.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.framework.aspectj.lang.annotation.Excel;
import com.framework.web.domain.BaseEntity;

/**
 * 关联表操作对象 imp_basic_relation
 * 
 * @author fanke
 * @date 2020-07-15
 */
public class ImpMainnoTackNo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private int id;
    private String mainOrderNo;
    private String taskNumber;
    private String tackNumber1;
    private String tackNumber2;
    private Date inserttime;
    private Date updatetime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMainOrderNo() {
		return mainOrderNo;
	}
	public void setMainOrderNo(String mainOrderNo) {
		this.mainOrderNo = mainOrderNo;
	}
	public String getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
	public String getTackNumber1() {
		return tackNumber1;
	}
	public void setTackNumber1(String tackNumber1) {
		this.tackNumber1 = tackNumber1;
	}
	public String getTackNumber2() {
		return tackNumber2;
	}
	public void setTackNumber2(String tackNumber2) {
		this.tackNumber2 = tackNumber2;
	}
	public Date getInserttime() {
		return inserttime;
	}
	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
    
   
}
