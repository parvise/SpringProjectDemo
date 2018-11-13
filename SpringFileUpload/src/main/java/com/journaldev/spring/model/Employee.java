package com.journaldev.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@Column(name = "Employee_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String country;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	@Basic(fetch = FetchType.EAGER)
	private Set<DBFiles> employeeDocuments = new HashSet<DBFiles>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<DBFiles> getEmployeeDocuments() {
		return employeeDocuments;
	}

	public void setEmployeeDocuments(Set<DBFiles> employeeDocuments) {
		this.employeeDocuments = employeeDocuments;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", country=" + country + ", employeeDocuments="
				+ employeeDocuments + "]";
	}

}
