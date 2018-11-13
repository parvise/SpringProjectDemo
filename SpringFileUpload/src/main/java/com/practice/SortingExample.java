package com.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class SortingExample {

	public static void main(String[] args) {

		Employee emp1 = new Employee(1, "bervez", 1000);
		Employee emp2 = new Employee(2, "aishnu", 6000);
		Employee emp3 = new Employee(3, "ciran", 500);
		Employee emp4 = new Employee(4, "dhiva", 4000);

		TreeMap<Employee, Integer> map = new TreeMap<Employee, Integer>();
		map.put(emp1, 1);
		map.put(emp4, 4);
		map.put(emp2, 2);
		map.put(emp3, 3);

		List<Employee> list = new ArrayList<Employee>();
		list.add(emp1);
		list.add(emp2);
		list.add(emp3);
		list.add(emp4);
		
		Collections.sort(list,new EmpSalarySort());
		System.out.println(list);
	}

}

class Employee implements Comparable<Employee> {
	private int eid;
	private String empName;
	private long empSalary;

	public Employee(int eid, String empName, long empSalary) {
		super();
		this.eid = eid;
		this.empName = empName;
		this.empSalary = empSalary;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public long getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(long empSalary) {
		this.empSalary = empSalary;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", empName=" + empName + ", empSalary=" + empSalary + "]";
	}

	@Override
	public int compareTo(Employee o) {
		return this.empName.compareTo(o.getEmpName());
	}

}

class EmpSalarySort implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		
		return (int) (o1.getEmpSalary()-o2.getEmpSalary());
	}

}