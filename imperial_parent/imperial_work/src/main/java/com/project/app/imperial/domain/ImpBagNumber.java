package com.project.app.imperial.domain;


public class ImpBagNumber {
	private static final long serialVersionUID = 1L;

    /** null */
    private Long id;
    
    /** 报编号 */
    private String bagNumber;

    /** 收货人姓名 */
    private String consigneeName;
   
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBagNumber() {
		return bagNumber;
	}

	public void setBagNumber(String bagNumber) {
		this.bagNumber = bagNumber;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
    
}
