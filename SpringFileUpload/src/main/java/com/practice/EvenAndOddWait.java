package com.practice;

public class EvenAndOddWait {

	public static void main(String[] args) throws InterruptedException {

		final EvenAndOdd obj = new EvenAndOdd();

		Thread even = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					
					obj.printEven();
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread odd = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					obj.printOdd();
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		odd.setName("Odd");
		even.setName("Even");

		odd.start();
		even.start();

		odd.join();
		even.join();

		System.out.println("excetion done");

	}

}

class EvenAndOdd {

	private String threadName;
	private boolean odd = true;
	private int min = 1;
	private int max = 10;

	public EvenAndOdd() {
	}

	public void printOdd() throws InterruptedException {
		synchronized (this) {
			Thread.sleep(2000);
			while (min <= max) {
				// System.out.println("Checking Odd loop");
				while (odd) {
					if (min % 2 == 1) {
						System.out.println("The give number is  " + Thread.currentThread().getName() + "--->" + min
								+ "," + "Goes to waiting state");
						wait();
						// System.out.println("Notified Odd:" + min);
					}
				}
				odd = true;
				// System.out.println("Notifying from Odd method");
				notify();
				min++;
			}
		}

	}

	public void printEven() throws InterruptedException {
		synchronized (this) {
			//Thread.sleep(2000);
			while (min <= max) {
				// System.out.println("Checking Even loop");
				while (!odd) {
					if (min % 2 == 0) {
						System.out.println("The give number is  " + Thread.currentThread().getName() + "--->" + min
								+ "," + "Goes to waiting state");
						wait();
						// System.out.println("Notified even:" + min);
					}
				}
				odd = false;
				// System.out.println("Notifying from Even method");
				notify();
				min++;
			}
		}
	}

}