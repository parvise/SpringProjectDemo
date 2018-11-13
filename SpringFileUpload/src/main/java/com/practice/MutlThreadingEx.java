package com.practice;

public class MutlThreadingEx {

	public static void main(String[] args) {

		int proc = Runtime.getRuntime().availableProcessors();
		System.out.println(proc);
		
		System.out.println(3*0.1);
		System.out.println(3*0.1==0.3);

		for (int i = 0; i < 5; i++) {
			SingletonInstance inst = SingletonInstance._getInstance();
			System.out.println(inst.hashCode());

			Thread th = new Thread(new MultiThreadSingleTon(inst), "ThreadName" + i);
		//	th.start();
			try {
				th.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int arr[][]= { { 32, 45, 35 }, { 53, 65, 67 }, { 43, 23, 76 } };
		int elementTobeFound=53;
		outer: for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {

				if (arr[i][j] == elementTobeFound) {
					System.out.println(elementTobeFound + " is present in the array ");
					break outer; // labeled break statement is encountered,
									// control will exit
					// outer loop now
				}
			}
		}

	}

}

class SingletonInstance {
	private static SingletonInstance _instance;
	private int _count;

	public static SingletonInstance _getInstance() {
		if (_instance == null) {
			synchronized (SingletonInstance.class) {
				try {
					Thread.sleep(2000);
					_instance = new SingletonInstance();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return _instance;
	}

	public synchronized int increment() {
		return _count++;
	}

}

class MultiThreadSingleTon implements Runnable {
	private static SingletonInstance _instance;

	public MultiThreadSingleTon(SingletonInstance _instance) {
		MultiThreadSingleTon._instance = _instance;
	}

	@Override
	public void run() {
		int msg = _instance.increment();
		boolean test = Thread.holdsLock(_instance);
		System.out.println(msg + "," + _instance.hashCode() + "," + test);
	}

}