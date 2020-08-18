package com.project.app.imperial.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.framework.aspectj.lang.annotation.Excel;
import com.framework.web.domain.BaseEntity;

/**
 * 清关系统基础数据表处理对象 imp_basic_data
 * 
 * @author fanke
 * @date 2020-07-08
 */
public class ImpBasicData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 快递号1 */
    @Excel(name = "快递号1")
    private String tackingNumber1;

    /** 快递号2 */
    @Excel(name = "快递号2")
    private String tackingNumber2;

    /** 包编号 */
    @Excel(name = "包编号")
    private String bagNumber;

    /** 箱编号 */
    @Excel(name = "箱编号")
    private String boxNumber;

    /** 运货板编号 */
    @Excel(name = "运货板编号")
    private String palletNumber;

    /** 集装箱编号 */
    @Excel(name = "集装箱编号")
    private String containerNumber;

    /** 参考编码1 */
    @Excel(name = "参考编码1")
    private String reference1;

    /** 参考编码2 */
    @Excel(name = "参考编码2")
    private String referrnce2;

    /** 发货人姓名 */
    @Excel(name = "发货人姓名")
    private String shipperName;

    /** 发货人参考信息 */
    @Excel(name = "发货人参考信息")
    private String shipperReference;

    /** 收货人姓名 */
    @Excel(name = "收货人姓名")
    private String consigneeName;

    /** 第一行地址 */
    @Excel(name = "第一行地址")
    private String lineAddress1;

    /** 第二行地址 */
    @Excel(name = "第二行地址")
    private String lineAddress2;

    /** 第三行地址 */
    @Excel(name = "第三行地址")
    private String lineAddress3;

    /** 集镇 */
    @Excel(name = "集镇")
    private String town;

    /** 国家 */
    @Excel(name = "国家")
    private String state;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String postCode;

    /** 国家代码 */
    @Excel(name = "国家代码")
    private String countryCode;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 数量单位 */
    @Excel(name = "数量单位")
    private String pieces;

    /** 重量（大小） */
    @Excel(name = "重量", readConverterExp = "大=小")
    private String weight;

    /** 重量单位 */
    @Excel(name = "重量单位")
    private String weightUom;

    /** null */
    @Excel(name = "null")
    private Long value;

    /** 运费、发货税率 */
    @Excel(name = "运费、发货税率")
    private Long shippingRate;

    /** 货币 */
    @Excel(name = "货币")
    private String currency;

    /** 贸易术语 DDU/DDP */
    @Excel(name = "贸易术语 DDU/DDP")
    private String incoterms;

    /** 进口目的 */
    @Excel(name = "进口目的")
    private String importPurpose;

    /** null */
    @Excel(name = "null")
    private String eoriNumber;

    /** null */
    @Excel(name = "null")
    private String mossNumber;

    /** 说明 */
    @Excel(name = "说明")
    private String description;

    /** null */
    @Excel(name = "null")
    private String hsCode;

    /** 项目数量 */
    @Excel(name = "项目数量")
    private String itemQuantity;

    /** 产品sku编号 */
    @Excel(name = "产品sku编号")
    private Long itemValue;

    /** null */
    @Excel(name = "null")
    private String skuNumber;

    /** 返回指令 */
    @Excel(name = "返回指令")
    private String returnInstruction;

    /** 销售连结 */
    @Excel(name = "销售连结")
    private String salesLink;

    /** 最后一英里供应商 */
    @Excel(name = "最后一英里供应商")
    private String lastMileProvider;

    /** 最后一英里账户名 */
    @Excel(name = "最后一英里账户名")
    private String lastMileAccountName;

    /** 最后一英里服务 */
    @Excel(name = "最后一英里服务")
    private String lastMileService;
    
    /** 是否为草稿标识 */
    private Integer orderIsdraftFlag;
    
    /** 是否为历史数据标识 */
    private Integer orderEndFlag;
    
    /** 是否提交审核标识 */
    private Integer submitFlag;
    
    /** 是否为多次提交标识 */
    private Integer isresubmitFlag;
    
    /** 为多次提交标识 */
    private Integer isresubmitCheckFlag;
    
    /** 审核结果 */
    private Integer checkFlag;
    
    /**方法参数1*/
    private String strParams;
    
    /**方法参数2*/
    private String [] arrParams;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTackingNumber1(String tackingNumber1)
    {
        this.tackingNumber1 = tackingNumber1;
    }

    public String getTackingNumber1()
    {
        return tackingNumber1;
    }
    public void setTackingNumber2(String tackingNumber2)
    {
        this.tackingNumber2 = tackingNumber2;
    }

    public String getTackingNumber2()
    {
        return tackingNumber2;
    }
    public void setBagNumber(String bagNumber)
    {
        this.bagNumber = bagNumber;
    }

    public String getBagNumber()
    {
        return bagNumber;
    }
    public void setBoxNumber(String boxNumber)
    {
        this.boxNumber = boxNumber;
    }

    public String getBoxNumber()
    {
        return boxNumber;
    }
    public void setPalletNumber(String palletNumber)
    {
        this.palletNumber = palletNumber;
    }

    public String getPalletNumber()
    {
        return palletNumber;
    }
    public void setContainerNumber(String containerNumber)
    {
        this.containerNumber = containerNumber;
    }

    public String getContainerNumber()
    {
        return containerNumber;
    }
    public void setReference1(String reference1)
    {
        this.reference1 = reference1;
    }

    public String getReference1()
    {
        return reference1;
    }
    public void setReferrnce2(String referrnce2)
    {
        this.referrnce2 = referrnce2;
    }

    public String getReferrnce2()
    {
        return referrnce2;
    }
    public void setShipperName(String shipperName)
    {
        this.shipperName = shipperName;
    }

    public String getShipperName()
    {
        return shipperName;
    }
    public void setShipperReference(String shipperReference)
    {
        this.shipperReference = shipperReference;
    }

    public String getShipperReference()
    {
        return shipperReference;
    }
    public void setConsigneeName(String consigneeName)
    {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeName()
    {
        return consigneeName;
    }
    public void setLineAddress1(String lineAddress1)
    {
        this.lineAddress1 = lineAddress1;
    }

    public String getLineAddress1()
    {
        return lineAddress1;
    }
    public void setLineAddress2(String lineAddress2)
    {
        this.lineAddress2 = lineAddress2;
    }

    public String getLineAddress2()
    {
        return lineAddress2;
    }
    public void setLineAddress3(String lineAddress3)
    {
        this.lineAddress3 = lineAddress3;
    }

    public String getLineAddress3()
    {
        return lineAddress3;
    }
    public void setTown(String town)
    {
        this.town = town;
    }

    public String getTown()
    {
        return town;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }
    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    public String getPostCode()
    {
        return postCode;
    }
    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getCountryCode()
    {
        return countryCode;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setPieces(String pieces)
    {
        this.pieces = pieces;
    }

    public String getPieces()
    {
        return pieces;
    }
    public void setWeight(String weight)
    {
        this.weight = weight;
    }

    public String getWeight()
    {
        return weight;
    }
    public void setWeightUom(String weightUom)
    {
        this.weightUom = weightUom;
    }

    public String getWeightUom()
    {
        return weightUom;
    }
    public void setValue(Long value)
    {
        this.value = value;
    }

    public Long getValue()
    {
        return value;
    }
    public void setShippingRate(Long shippingRate)
    {
        this.shippingRate = shippingRate;
    }

    public Long getShippingRate()
    {
        return shippingRate;
    }
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getCurrency()
    {
        return currency;
    }
    public void setIncoterms(String incoterms)
    {
        this.incoterms = incoterms;
    }

    public String getIncoterms()
    {
        return incoterms;
    }
    public void setImportPurpose(String importPurpose)
    {
        this.importPurpose = importPurpose;
    }

    public String getImportPurpose()
    {
        return importPurpose;
    }
    public void setEoriNumber(String eoriNumber)
    {
        this.eoriNumber = eoriNumber;
    }

    public String getEoriNumber()
    {
        return eoriNumber;
    }
    public void setMossNumber(String mossNumber)
    {
        this.mossNumber = mossNumber;
    }

    public String getMossNumber()
    {
        return mossNumber;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public void setHsCode(String hsCode)
    {
        this.hsCode = hsCode;
    }

    public String getHsCode()
    {
        return hsCode;
    }
    public void setItemQuantity(String itemQuantity)
    {
        this.itemQuantity = itemQuantity;
    }

    public String getItemQuantity()
    {
        return itemQuantity;
    }
    public void setItemValue(Long itemValue)
    {
        this.itemValue = itemValue;
    }

    public Long getItemValue()
    {
        return itemValue;
    }
    public void setSkuNumber(String skuNumber)
    {
        this.skuNumber = skuNumber;
    }

    public String getSkuNumber()
    {
        return skuNumber;
    }
    public void setReturnInstruction(String returnInstruction)
    {
        this.returnInstruction = returnInstruction;
    }

    public String getReturnInstruction()
    {
        return returnInstruction;
    }
    public void setSalesLink(String salesLink)
    {
        this.salesLink = salesLink;
    }

    public String getSalesLink()
    {
        return salesLink;
    }
    public void setLastMileProvider(String lastMileProvider)
    {
        this.lastMileProvider = lastMileProvider;
    }

    public String getLastMileProvider()
    {
        return lastMileProvider;
    }
    public void setLastMileAccountName(String lastMileAccountName)
    {
        this.lastMileAccountName = lastMileAccountName;
    }

    public String getLastMileAccountName()
    {
        return lastMileAccountName;
    }
    public void setLastMileService(String lastMileService)
    {
        this.lastMileService = lastMileService;
    }

    public String getLastMileService()
    {
        return lastMileService;
    }

	
	public Integer getOrderIsdraftFlag() {
		return orderIsdraftFlag;
	}

	public void setOrderIsdraftFlag(Integer orderIsdraftFlag) {
		this.orderIsdraftFlag = orderIsdraftFlag;
	}

	public Integer getOrderEndFlag() {
		return orderEndFlag;
	}

	public void setOrderEndFlag(Integer orderEndFlag) {
		this.orderEndFlag = orderEndFlag;
	}

	public String getStrParams() {
		return strParams;
	}

	public void setStrParams(String strParams) {
		this.strParams = strParams;
	}

	public Integer getSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(Integer submitFlag) {
		this.submitFlag = submitFlag;
	}

	public String[] getArrParams() {
		return arrParams;
	}

	public void setArrParams(String[] arrParams) {
		this.arrParams = arrParams;
	}

	public Integer getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(Integer checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Integer getIsresubmitFlag() {
		return isresubmitFlag;
	}

	public void setIsresubmitFlag(Integer isresubmitFlag) {
		this.isresubmitFlag = isresubmitFlag;
	}

	public Integer getIsresubmitCheckFlag() {
		return isresubmitCheckFlag;
	}

	public void setIsresubmitCheckFlag(Integer isresubmitCheckFlag) {
		this.isresubmitCheckFlag = isresubmitCheckFlag;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("tackingNumber1", getTackingNumber1())
            .append("tackingNumber2", getTackingNumber2())
            .append("bagNumber", getBagNumber())
            .append("boxNumber", getBoxNumber())
            .append("palletNumber", getPalletNumber())
            .append("containerNumber", getContainerNumber())
            .append("reference1", getReference1())
            .append("referrnce2", getReferrnce2())
            .append("shipperName", getShipperName())
            .append("shipperReference", getShipperReference())
            .append("consigneeName", getConsigneeName())
            .append("lineAddress1", getLineAddress1())
            .append("lineAddress2", getLineAddress2())
            .append("lineAddress3", getLineAddress3())
            .append("town", getTown())
            .append("state", getState())
            .append("postCode", getPostCode())
            .append("countryCode", getCountryCode())
            .append("email", getEmail())
            .append("phone", getPhone())
            .append("pieces", getPieces())
            .append("weight", getWeight())
            .append("weightUom", getWeightUom())
            .append("value", getValue())
            .append("shippingRate", getShippingRate())
            .append("currency", getCurrency())
            .append("incoterms", getIncoterms())
            .append("importPurpose", getImportPurpose())
            .append("eoriNumber", getEoriNumber())
            .append("mossNumber", getMossNumber())
            .append("description", getDescription())
            .append("hsCode", getHsCode())
            .append("itemQuantity", getItemQuantity())
            .append("itemValue", getItemValue())
            .append("skuNumber", getSkuNumber())
            .append("returnInstruction", getReturnInstruction())
            .append("salesLink", getSalesLink())
            .append("lastMileProvider", getLastMileProvider())
            .append("lastMileAccountName", getLastMileAccountName())
            .append("lastMileService", getLastMileService())
            .append("orderIsdraftFlag", getOrderIsdraftFlag())
            .append("orderEndFlag", getOrderEndFlag())
            .toString();
    }
}
