package com.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {

		Add add = (x, y) -> x - y;
		System.out.println(add.sub(20, 30));
		add.test();
		Add.display();

		List<String> list = new ArrayList<String>();
		list.add("Rick");
		list.add("Negan");
		list.add("Daryl");
		list.add("Glen88n");
		list.add("Carl");
		list.add("Carl");

		// Stream Collectors groupingBy and counting Example
		Map<String, Long> map = list.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		// Stream Collectors example of fetching data as List
		// List<String> list = studentList.stream().map(n->n.name).collect(Collectors.toList());
		
		//Collecting data as Set
		//Set<Student> list = studentList.stream().filter(n->n.id>22).collect(Collectors.toSet());

		System.out.println(map);

		list.forEach((names) -> {
			System.out.println("Test " + names);
		});

		long count = list.stream().filter(name -> name.length() < 6).count();
		System.out.println(count);

		Test test = new Test();
		Add te = test::myMethod;
		te.sub(5, 10);
		String[] stringArray = { "Steve", "rick", "aditya", "Negan", "Lucy", "Sansa", "Jon" };
		/*
		 * Method reference to an instance method of an arbitrary object of a
		 * particular type
		 */
		Arrays.sort(stringArray, String::compareToIgnoreCase);
		System.out.println(Arrays.toString(stringArray));
		Stream.iterate(1, inc -> inc + 1).limit(6).forEach(System.out::println);

	}

	public int myMethod(int x, int y) {
		System.out.println("Instance Method " + (x + y));
		return 0;
	}
}

@FunctionalInterface
interface Add {
	// int add(int x, int y);
	int sub(int x, int y);

	default void test() {
		System.out.println("Welcome");
	}

	static void display() {
		System.out.println("Static");
	}
}