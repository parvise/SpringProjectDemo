package com.journaldev.spring.dao;

import java.util.List;

import com.journaldev.spring.model.Employee;

public interface EmployeeDAO {

	public void addEmployee(Employee p);

	public void updateEmployee(Employee p);

	public List<Employee> listEmployees();

	public Employee getEmployeeById(int id);

	public void removeEmployee(int id);
}
