package com.demo.flashSale.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "Contacts")
// NamedQuery with index params and JPQL
/*
 * @NamedQueries(value = {
 * 
 * @NamedQuery(name = "EmailIdEntity.getByEmailAddress", query =
 * "SELECT p FROM EmailIdEntity p WHERE p.emailAddress=?1") })
 */

// Named Native Sql queries with named params
@NamedNativeQueries(value = {
		@NamedNativeQuery(name = "EmailIdEntity.getByEmailAddress", query = "SELECT * FROM Contacts p WHERE p.email_address=:emailAddress", resultClass = EmailIdEntity.class) })
@DynamicUpdate
public class EmailIdEntity {

	/*@Id
	@GeneratedValue(generator = "uuid2","UUID")
	@GenericGenerator(name = "uuid2","UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "VARCHAR(255)")
	private UUID id;*/

	@Id
	@Column(name = "mail_Id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private String emailId;

	@Column(name = "name")
	private String name;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "status")
	private String registerdStatus;

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

	public String getRegisterdStatus() {
		return registerdStatus;
	}

	public void setRegisterdStatus(String registerdStatus) {
		this.registerdStatus = registerdStatus;
	}

	@Override
	public String toString() {
		return "EmailIdEntity [emailId=" + emailId + ", name=" + name + ", emailAddress=" + emailAddress
				+ ", mobileNumber=" + mobileNumber + ", registerdStatus=" + registerdStatus + "]";
	}

}
