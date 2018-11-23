package com.demo.flashSale.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "product_details")
@DynamicUpdate
public class ProductDetailsEntity {

	@Id
	@Column(name = "PROD_ID")
	private Integer prodId;

	@Column(name = "PROD_NAME")
	private String prodName;

	@Column(name = "QUANTITY")
	private int quantity;

	@Column(name = "SOLD_OUT")
	private Integer soldOut;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "STATUS")
	private String status;

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSoldOut() {
		return soldOut;
	}

	public void setSoldOut(int soldOut) {
		this.soldOut = soldOut;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProductDetailsEntity [prodId=" + prodId + ", prodName=" + prodName + ", quantity=" + quantity
				+ ", soldOut=" + soldOut + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status
				+ "]";
	}

}
