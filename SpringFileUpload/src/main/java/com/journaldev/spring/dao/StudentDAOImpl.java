package com.journaldev.spring.dao;

import java.sql.Blob;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.model.DBFiles;
import com.journaldev.spring.model.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addPerson(Student p) {
		System.out.println("Save enter"+ sessionFactory);
		Session session = this.sessionFactory.getCurrentSession();
		// org.hibernate.Transaction tx = session.beginTransaction();
		// tx.begin();
		
		Set<DBFiles> dbFiles = 	p.getStudentDocuments();
		for (DBFiles dbFiles2 : dbFiles) {
			Blob blob = session.getLobHelper().createBlob(dbFiles2.getFileBytes());
			dbFiles2.setContent(blob);
		}
		session.persist(p);
		// logger.info("Person saved successfully, Person Details=" + p);
		// tx.commit();
		//session.close();
		System.out.println("Person saved successfully, Person Details=" + p);
	}

	@Override
	public void updatePerson(Student p) {
		Session session = this.sessionFactory.getCurrentSession();
		// org.hibernate.Transaction tx = session.beginTransaction();
		// tx.begin();
		session.update(p);
		// logger.info("Person updated successfully, Person Details=" + p);
		System.out.println("Person updated successfully, Person Details=" + p);
		// tx.commit();
		//session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
		// org.hibernate.Transaction tx = session.beginTransaction();
		// tx.begin();
		List<Student> personsList = session.createQuery("from Student").list();
		for (Student p : personsList) {
			// logger.info("Person List::" + p);
			System.out.println("Person List::" + p);
		}
		// tx.commit();
		//session.close();
		return personsList;
	}

	@Override
	public Student getPersonById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		// org.hibernate.Transaction tx = session.beginTransaction();
		// tx.begin();
		Student p = (Student) session.load(Student.class, new Integer(id));
		// logger.info("Person loaded successfully, Person details=" + p);
		System.out.println("Person Edit successfully, Person details=" + p);
		// tx.commit();
		//session.close();
		return p;
	}

	@Override
	public void removePerson(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		// org.hibernate.Transaction tx = session.beginTransaction();
		// tx.begin();
		Student p = (Student) session.load(Student.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
		// logger.info("Person deleted successfully, person details=" + p);
		System.out.println("Person deleted successfully, person details=" + p);
		// tx.commit();
		//session.close();
	}

	@Override
	public void uploadFile(DBFiles dbFiles) {
		
	}

}
