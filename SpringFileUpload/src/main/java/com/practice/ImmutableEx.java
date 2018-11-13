package com.practice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ImmutableEx {

	public static void main(String[] args) {
		String name = "pervez";
		int i=10;
		Map<String,Integer> list = new HashMap<String,Integer>();
		list.put("pervez",544);
		list.put("Vishnu",560);
		Student s1 = new Student(i, name,list);
		System.out.println(s1.getSname()+","+s1.getList()+","+s1.getSid());
		i=20;
		list.put("kiran",000);
		name = "suresh";
		System.out.println(s1.getSname()+","+s1.getList()+","+s1.getSid());
		
		Date d = new Date(2011343412345L);
		DateFormat df = new SimpleDateFormat();
		System.out.println(df.format(d));
	}

}

class Student {
	private final int sid;
	private final String sname;
	private final Map<String,Integer> map;

	public Student(int sid, String sname, Map<String,Integer> map) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.map = new HashMap<String, Integer>(map);
		//this.list=list;
	}

	public int getSid() {
		return sid;
	}

	public String getSname() {
		return sname;
	}

	public Map<String,Integer> getList() {
		return map;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + "]";
	}

}