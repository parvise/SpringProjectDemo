package com.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QueueWaitNotify {

	public static void main(String[] args) {

		List<String> list = new ArrayList<>();

		Thread producer = new Thread(new Producer(list));
		Thread consumer = new Thread(new Consumer(list));
		System.out.println("Welcome");
		producer.start();
		consumer.start();

	}

}

class Producer implements Runnable {
	private List<String> list;
	private boolean check = true;

	public Producer(List<String> list) {
		this.list = list;
	}

	@Override
	public void run() {
		produce();
	}

	private void produce() {
		synchronized (list) {
			while (check) {
				if (list.size() == 4) {
					try {
						System.out.println("Queue is Full consumer  has to consume");
						list.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					Random random = new Random();
					int i = random.nextInt();
					try {
						Thread.sleep(2000);
						list.add("pervez" + i);
						System.out.println("List added" + list);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					list.notifyAll();
				}
			}

		}
	}

}

class Consumer implements Runnable {

	private List<String> list;

	public Consumer(List<String> list) {
		this.list = list;
	}

	@Override
	public void run() {
		consume();
	}

	private void consume() {
		synchronized (list) {
			while (true) {
				if (list.size() == 0) {
					System.out.println("Queue is empty producer has to produce");
					try {
						list.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(2000);
					list.remove(0);
					System.out.println("List removed" + list);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				list.notifyAll();
			}

		}

	}

}