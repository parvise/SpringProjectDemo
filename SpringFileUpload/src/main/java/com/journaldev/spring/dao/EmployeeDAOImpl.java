package com.journaldev.spring.dao;

import java.sql.Blob;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.model.DBFiles;
import com.journaldev.spring.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addEmployee(Employee p) {
		System.out.println("Employee Added");
		Session session = this.sessionFactory.getCurrentSession();
		// org.hibernate.Transaction tx = session.beginTransaction();
		// tx.begin();
		
		Set<DBFiles> dbFiles = 	p.getEmployeeDocuments();
		for (DBFiles dbFiles2 : dbFiles) {
			Blob blob = session.getLobHelper().createBlob(dbFiles2.getFileBytes());
			dbFiles2.setContent(blob);
		}
		session.persist(p);
		// tx.commit();
		// session.close();
	}

	@Override
	public void updateEmployee(Employee p) {
		System.out.println("Employee Updated");
		Session session = this.sessionFactory.getCurrentSession();
		// org.hibernate.Transaction tx = session.beginTransaction();
		// tx.begin();
		session.update(p);
		// tx.commit();
		// session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> listEmployees() {
		System.out.println("List of Employees");
		Session session = this.sessionFactory.getCurrentSession();
		// org.hibernate.Transaction tx = session.beginTransaction();
		// tx.begin();
		List<Employee> personsList = session.createQuery("from Employee").list();
		for (Employee p : personsList) {
			System.out.println("Employe List::" + p);
		}
		// tx.commit();
		// session.close();
		return personsList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		System.out.println("Employee find by id");
		Session session = this.sessionFactory.getCurrentSession();
		// org.hibernate.Transaction tx = session.beginTransaction();
		// tx.begin();
		Employee p = (Employee) session.load(Employee.class, new Integer(id));
		// tx.commit();
		// session.close();
		return p;
	}

	@Override
	public void removeEmployee(int id) {
		System.out.println("Employee Remove");
		Session session = this.sessionFactory.getCurrentSession();
		// org.hibernate.Transaction tx = session.beginTransaction();
		// tx.begin();
		Employee p = (Employee) session.load(Employee.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
		// tx.commit();
		// session.close();
	}

}
