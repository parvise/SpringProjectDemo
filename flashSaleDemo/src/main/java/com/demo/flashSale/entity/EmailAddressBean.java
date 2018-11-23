package com.demo.flashSale.entity;

public class EmailAddressBean {

	private String emailId;
	private String emailAddress;
	private String mobileNumber;
	private String name;
	private Boolean registerdStatus;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getRegisterdStatus() {
		return registerdStatus;
	}

	public void setRegisterdStatus(Boolean registerdStatus) {
		this.registerdStatus = registerdStatus;
	}

	@Override
	public String toString() {
		return "EmailAddressBean [emailId=" + emailId + ", emailAddress=" + emailAddress + ", mobileNumber="
				+ mobileNumber + ", name=" + name + ", registerdStatus=" + registerdStatus + "]";
	}

}
