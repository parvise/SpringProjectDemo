package com.demo.flashSale.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registration")
public class RegisterEntity {

	@Id
	@Column(name = "REG_ID")
	private String regId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "CONTACT")
	private String contactNo;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "PURCHASED_DATE")
	private Date purchasedDate;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "MAIL_ID")
	private String mailId;

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	@Override
	public String toString() {
		return "RegisterEntity [regId=" + regId + ", name=" + name + ", address=" + address + ", contactNo=" + contactNo
				+ ", gender=" + gender + ", createdDate=" + createdDate + ", purchasedDate=" + purchasedDate
				+ ", status=" + status + ", mailId=" + mailId + "]";
	}

}
