package com.project.app.imperial.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.framework.aspectj.lang.annotation.Excel;
import com.framework.web.domain.BaseEntity;

/**
 * 发货人信息完善对象 imp_customer_info
 * 
 * @author zhudemao
 * @date 2020-09-02
 */
public class ImpCustomerInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String customer;

    /** 用户所在国 */
    @Excel(name = "用户所在国")
    private String customerState;

    /** 注册号 */
    @Excel(name = "注册号")
    private String registerNo;

    /** 注册地址 */
    @Excel(name = "注册地址")
    private String registerLocation;

    /** 注册EORI号 */
    @Excel(name = "注册EORI号")
    private String registerEori;

    /** 主联系号码1 */
    @Excel(name = "主联系号码1")
    private String mainConn1;

    /** 主联系邮箱1 */
    @Excel(name = "主联系邮箱1")
    private String mainMail1;

    /** 主联系号码2 */
    @Excel(name = "主联系号码2")
    private String mainConn2;

    /** 主联系邮箱2 */
    @Excel(name = "主联系邮箱2")
    private String mainMail2;

    /** 操作人联系号码1 */
    @Excel(name = "操作人联系号码1")
    private String operConn1;

    /** 操作人联系邮箱1 */
    @Excel(name = "操作人联系邮箱1")
    private String operMail1;

    /** 操作人联系号码2 */
    @Excel(name = "操作人联系号码2")
    private String operConn2;

    /** 操作人联系邮箱2 */
    @Excel(name = "操作人联系邮箱2")
    private String operMail2;

    /** 财务联系号码1 */
    @Excel(name = "财务联系号码1")
    private String financeConn1;

    /** 财务联系邮箱1 */
    @Excel(name = "财务联系邮箱1")
    private String financeMail1;

    /** 财务联系号码2 */
    @Excel(name = "财务联系号码2")
    private String financeConn2;

    /** 财务联系邮箱2 */
    @Excel(name = "财务联系邮箱2")
    private String financeMail2;

    /** sop文件 */
    @Excel(name = "sop文件")
    private Long fileSop;

    /** sop文件有效期  vt为validity_term简写 */
    @Excel(name = "sop文件有效期  vt为validity_term简写")
    private String vtSop;

    /** 职责划分文档 */
    @Excel(name = "职责划分文档")
    private Long fileDutyDivision;

    /** 职责划分文档有效期 */
    @Excel(name = "职责划分文档有效期")
    private String vtDutyDivision;

    /** 授权委托书文档 */
    @Excel(name = "授权委托书文档")
    private Long filePowerAttorney;

    /** 授权委托书文档有效期 */
    @Excel(name = "授权委托书文档有效期")
    private String vtPowerAttorney;

    /** NDA保密协议 */
    @Excel(name = "NDA保密协议")
    private Long fileNdaSecret;

    /** NDA保密协议有效期 */
    @Excel(name = "NDA保密协议有效期")
    private String vtNdaSecret;

    /** 公司登记副本   register_copy */
    @Excel(name = "公司登记副本   register_copy")
    private Long fileRegCopy;

    /** 公司登记副本有效期 */
    @Excel(name = "公司登记副本有效期")
    private String vtRegCopy;

    /** 附加文档1 */
    @Excel(name = "附加文档1")
    private Long fileAdd1;

    /** 附加文档1有效期 */
    @Excel(name = "附加文档1有效期")
    private String vtAdd1;

    /** 附加文档2 */
    @Excel(name = "附加文档2")
    private Long fileAdd2;

    /** 附加文档2有效期 */
    @Excel(name = "附加文档2有效期")
    private String vtAdd2;

    /** 附加文档3 */
    @Excel(name = "附加文档3")
    private Long fileAdd3;

    /** 附加文档3有效期 */
    @Excel(name = "附加文档3有效期")
    private String vtAdd3;

    /** 附加文档4 */
    @Excel(name = "附加文档4")
    private Long fileAdd4;

    /** 附加文档4有效期 */
    @Excel(name = "附加文档4有效期")
    private String vtAdd4;

    /** 附加文档5 */
    @Excel(name = "附加文档5")
    private Long fileAdd5;

    /** 附加文档5有效期 */
    @Excel(name = "附加文档5有效期")
    private String vtAdd5;

    /** 网络联盟 */
    @Excel(name = "网络联盟")
    private String netCosn;

    /** 公司财务审核凭证  Financial audit certificate of the company */
    @Excel(name = "公司财务审核凭证  Financial audit certificate of the company")
    private Long fileFinanAuditCerti;

    /** 公司财务审核凭证有效期 */
    @Excel(name = "公司财务审核凭证有效期")
    private String vtFinanAuditCerti;

    /** 附加保函核实  Verification of additional guarantee */
    @Excel(name = "附加保函核实  Verification of additional guarantee")
    private Long fileVertAddGuar;

    /** 附加保函核实有效期 */
    @Excel(name = "附加保函核实有效期")
    private String vtVertAddGuar;

    /** 此条信息录入者 */
    @Excel(name = "此条信息录入者")
    private String inputer;

    

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCustomer(String customer)
    {
        this.customer = customer;
    }

    public String getCustomer()
    {
        return customer;
    }
    public void setCustomerState(String customerState)
    {
        this.customerState = customerState;
    }

    public String getCustomerState()
    {
        return customerState;
    }
    public void setRegisterNo(String registerNo)
    {
        this.registerNo = registerNo;
    }

    public String getRegisterNo()
    {
        return registerNo;
    }
    public void setRegisterLocation(String registerLocation)
    {
        this.registerLocation = registerLocation;
    }

    public String getRegisterLocation()
    {
        return registerLocation;
    }
    public void setRegisterEori(String registerEori)
    {
        this.registerEori = registerEori;
    }

    public String getRegisterEori()
    {
        return registerEori;
    }
    public void setMainConn1(String mainConn1)
    {
        this.mainConn1 = mainConn1;
    }

    public String getMainConn1()
    {
        return mainConn1;
    }
    public void setMainMail1(String mainMail1)
    {
        this.mainMail1 = mainMail1;
    }

    public String getMainMail1()
    {
        return mainMail1;
    }
    public void setMainConn2(String mainConn2)
    {
        this.mainConn2 = mainConn2;
    }

    public String getMainConn2()
    {
        return mainConn2;
    }
    public void setMainMail2(String mainMail2)
    {
        this.mainMail2 = mainMail2;
    }

    public String getMainMail2()
    {
        return mainMail2;
    }
    public void setOperConn1(String operConn1)
    {
        this.operConn1 = operConn1;
    }

    public String getOperConn1()
    {
        return operConn1;
    }
    public void setOperMail1(String operMail1)
    {
        this.operMail1 = operMail1;
    }

    public String getOperMail1()
    {
        return operMail1;
    }
    public void setOperConn2(String operConn2)
    {
        this.operConn2 = operConn2;
    }

    public String getOperConn2()
    {
        return operConn2;
    }
    public void setOperMail2(String operMail2)
    {
        this.operMail2 = operMail2;
    }

    public String getOperMail2()
    {
        return operMail2;
    }
    public void setFinanceConn1(String financeConn1)
    {
        this.financeConn1 = financeConn1;
    }

    public String getFinanceConn1()
    {
        return financeConn1;
    }
    public void setFinanceMail1(String financeMail1)
    {
        this.financeMail1 = financeMail1;
    }

    public String getFinanceMail1()
    {
        return financeMail1;
    }
    public void setFinanceConn2(String financeConn2)
    {
        this.financeConn2 = financeConn2;
    }

    public String getFinanceConn2()
    {
        return financeConn2;
    }
    public void setFinanceMail2(String financeMail2)
    {
        this.financeMail2 = financeMail2;
    }

    public String getFinanceMail2()
    {
        return financeMail2;
    }
    public void setFileSop(Long fileSop)
    {
        this.fileSop = fileSop;
    }

    public Long getFileSop()
    {
        return fileSop;
    }
    public void setVtSop(String vtSop)
    {
        this.vtSop = vtSop;
    }

    public String getVtSop()
    {
        return vtSop;
    }
    public void setFileDutyDivision(Long fileDutyDivision)
    {
        this.fileDutyDivision = fileDutyDivision;
    }

    public Long getFileDutyDivision()
    {
        return fileDutyDivision;
    }
    public void setVtDutyDivision(String vtDutyDivision)
    {
        this.vtDutyDivision = vtDutyDivision;
    }

    public String getVtDutyDivision()
    {
        return vtDutyDivision;
    }
    public void setFilePowerAttorney(Long filePowerAttorney)
    {
        this.filePowerAttorney = filePowerAttorney;
    }

    public Long getFilePowerAttorney()
    {
        return filePowerAttorney;
    }
    public void setVtPowerAttorney(String vtPowerAttorney)
    {
        this.vtPowerAttorney = vtPowerAttorney;
    }

    public String getVtPowerAttorney()
    {
        return vtPowerAttorney;
    }
    public void setFileNdaSecret(Long fileNdaSecret)
    {
        this.fileNdaSecret = fileNdaSecret;
    }

    public Long getFileNdaSecret()
    {
        return fileNdaSecret;
    }
    public void setVtNdaSecret(String vtNdaSecret)
    {
        this.vtNdaSecret = vtNdaSecret;
    }

    public String getVtNdaSecret()
    {
        return vtNdaSecret;
    }
    public void setFileRegCopy(Long fileRegCopy)
    {
        this.fileRegCopy = fileRegCopy;
    }

    public Long getFileRegCopy()
    {
        return fileRegCopy;
    }
    public void setVtRegCopy(String vtRegCopy)
    {
        this.vtRegCopy = vtRegCopy;
    }

    public String getVtRegCopy()
    {
        return vtRegCopy;
    }
    public void setFileAdd1(Long fileAdd1)
    {
        this.fileAdd1 = fileAdd1;
    }

    public Long getFileAdd1()
    {
        return fileAdd1;
    }
    public void setVtAdd1(String vtAdd1)
    {
        this.vtAdd1 = vtAdd1;
    }

    public String getVtAdd1()
    {
        return vtAdd1;
    }
    public void setFileAdd2(Long fileAdd2)
    {
        this.fileAdd2 = fileAdd2;
    }

    public Long getFileAdd2()
    {
        return fileAdd2;
    }
    public void setVtAdd2(String vtAdd2)
    {
        this.vtAdd2 = vtAdd2;
    }

    public String getVtAdd2()
    {
        return vtAdd2;
    }
    public void setFileAdd3(Long fileAdd3)
    {
        this.fileAdd3 = fileAdd3;
    }

    public Long getFileAdd3()
    {
        return fileAdd3;
    }
    public void setVtAdd3(String vtAdd3)
    {
        this.vtAdd3 = vtAdd3;
    }

    public String getVtAdd3()
    {
        return vtAdd3;
    }
    public void setFileAdd4(Long fileAdd4)
    {
        this.fileAdd4 = fileAdd4;
    }

    public Long getFileAdd4()
    {
        return fileAdd4;
    }
    public void setVtAdd4(String vtAdd4)
    {
        this.vtAdd4 = vtAdd4;
    }

    public String getVtAdd4()
    {
        return vtAdd4;
    }
    public void setFileAdd5(Long fileAdd5)
    {
        this.fileAdd5 = fileAdd5;
    }

    public Long getFileAdd5()
    {
        return fileAdd5;
    }
    public void setVtAdd5(String vtAdd5)
    {
        this.vtAdd5 = vtAdd5;
    }

    public String getVtAdd5()
    {
        return vtAdd5;
    }
    public void setNetCosn(String netCosn)
    {
        this.netCosn = netCosn;
    }

    public String getNetCosn()
    {
        return netCosn;
    }
    public void setFileFinanAuditCerti(Long fileFinanAuditCerti)
    {
        this.fileFinanAuditCerti = fileFinanAuditCerti;
    }

    public Long getFileFinanAuditCerti()
    {
        return fileFinanAuditCerti;
    }
    public void setVtFinanAuditCerti(String vtFinanAuditCerti)
    {
        this.vtFinanAuditCerti = vtFinanAuditCerti;
    }

    public String getVtFinanAuditCerti()
    {
        return vtFinanAuditCerti;
    }
    public void setFileVertAddGuar(Long fileVertAddGuar)
    {
        this.fileVertAddGuar = fileVertAddGuar;
    }

    public Long getFileVertAddGuar()
    {
        return fileVertAddGuar;
    }
    public void setVtVertAddGuar(String vtVertAddGuar)
    {
        this.vtVertAddGuar = vtVertAddGuar;
    }

    public String getVtVertAddGuar()
    {
        return vtVertAddGuar;
    }
    public void setInputer(String inputer)
    {
        this.inputer = inputer;
    }

    public String getInputer()
    {
        return inputer;
    }
    

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customer", getCustomer())
            .append("customerState", getCustomerState())
            .append("registerNo", getRegisterNo())
            .append("registerLocation", getRegisterLocation())
            .append("registerEori", getRegisterEori())
            .append("mainConn1", getMainConn1())
            .append("mainMail1", getMainMail1())
            .append("mainConn2", getMainConn2())
            .append("mainMail2", getMainMail2())
            .append("operConn1", getOperConn1())
            .append("operMail1", getOperMail1())
            .append("operConn2", getOperConn2())
            .append("operMail2", getOperMail2())
            .append("financeConn1", getFinanceConn1())
            .append("financeMail1", getFinanceMail1())
            .append("financeConn2", getFinanceConn2())
            .append("financeMail2", getFinanceMail2())
            .append("fileSop", getFileSop())
            .append("vtSop", getVtSop())
            .append("fileDutyDivision", getFileDutyDivision())
            .append("vtDutyDivision", getVtDutyDivision())
            .append("filePowerAttorney", getFilePowerAttorney())
            .append("vtPowerAttorney", getVtPowerAttorney())
            .append("fileNdaSecret", getFileNdaSecret())
            .append("vtNdaSecret", getVtNdaSecret())
            .append("fileRegCopy", getFileRegCopy())
            .append("vtRegCopy", getVtRegCopy())
            .append("fileAdd1", getFileAdd1())
            .append("vtAdd1", getVtAdd1())
            .append("fileAdd2", getFileAdd2())
            .append("vtAdd2", getVtAdd2())
            .append("fileAdd3", getFileAdd3())
            .append("vtAdd3", getVtAdd3())
            .append("fileAdd4", getFileAdd4())
            .append("vtAdd4", getVtAdd4())
            .append("fileAdd5", getFileAdd5())
            .append("vtAdd5", getVtAdd5())
            .append("netCosn", getNetCosn())
            .append("fileFinanAuditCerti", getFileFinanAuditCerti())
            .append("vtFinanAuditCerti", getVtFinanAuditCerti())
            .append("fileVertAddGuar", getFileVertAddGuar())
            .append("vtVertAddGuar", getVtVertAddGuar())
            .append("inputer", getInputer())
            .toString();
    }
}
