package com.demo.flashSale.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "product_purchased_date")
public class PurchaseDateTimeEntity {

	@Id
	@Column(name = "id")
	private String dateId;

	@Column(name = "LAUNCH_DATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

	public String getDateId() {
		return dateId;
	}

	public void setDateId(String dateId) {
		this.dateId = dateId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "PurchaseDateTimeEntity [dateId=" + dateId + ", dateTime=" + dateTime + "]";
	}

}
