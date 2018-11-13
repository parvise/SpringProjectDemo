package com.journaldev.spring.model;

import java.io.Serializable;
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

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author pankaj
 *
 */
@Entity
@Table(name = "Student")
public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4251181634874289889L;

	@Id
	@Column(name = "Student_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String country;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	@Basic(fetch = FetchType.EAGER)
	private Set<DBFiles> studentDocuments = new HashSet<DBFiles>();

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

	public Set<DBFiles> getStudentDocuments() {
		return studentDocuments;
	}

	public void setStudentDocuments(Set<DBFiles> studentDocuments) {
		this.studentDocuments = studentDocuments;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", country=" + country + ", studentDocuments="
				+ studentDocuments + "]";
	}

}
