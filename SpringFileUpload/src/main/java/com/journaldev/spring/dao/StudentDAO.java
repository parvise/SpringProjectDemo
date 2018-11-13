package com.journaldev.spring.dao;

import java.util.List;

import com.journaldev.spring.model.DBFiles;
import com.journaldev.spring.model.Student;

public interface StudentDAO {

	public void addPerson(Student p);
	public void updatePerson(Student p);
	public List<Student> listPersons();
	public Student getPersonById(int id);
	public void removePerson(int id);
	public void uploadFile(DBFiles dbFiles);
}
