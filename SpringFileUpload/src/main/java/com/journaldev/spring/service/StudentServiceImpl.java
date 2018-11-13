package com.journaldev.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journaldev.spring.dao.StudentDAO;
import com.journaldev.spring.model.DBFiles;
import com.journaldev.spring.model.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO personDAO;

	@Override
	public void addStudent(Student p) {
		this.personDAO.addPerson(p);
	}

	@Override
	public void updateStudent(Student p) {
		this.personDAO.updatePerson(p);
	}

	@Override
	public List<Student> listStudents() {
		return this.personDAO.listPersons();
	}

	@Override
	public Student getStudentById(int id) {
		return this.personDAO.getPersonById(id);
	}

	@Override
	public void removeStudent(int id) {
		this.personDAO.removePerson(id);
	}

	@Override
	public void uploadFile(DBFiles dbFiles) {
		// TODO Auto-generated method stub
		
	}

}
