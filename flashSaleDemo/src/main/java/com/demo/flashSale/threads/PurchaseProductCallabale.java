package com.demo.flashSale.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

import com.demo.flashSale.entity.RegisterEntity;

public class PurchaseProductCallabale implements Callable<String> {

	private CyclicBarrier cyclicBarrier = null;
	private RegisterEntity bean = null;

	public PurchaseProductCallabale(RegisterEntity bean, CyclicBarrier barrier) {
		this.cyclicBarrier = barrier;
		this.bean = bean;
	}

	@Override
	public String call() throws Exception {

		String message = null;
		try {
			System.out.println(bean.getName()+ " has Ordered placed  "
					+ cyclicBarrier.getNumberWaiting() + " Parties " + cyclicBarrier.getParties());

			int count = cyclicBarrier.await();
			if (count == 0) {
				message = "Stock is Over";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return message;

	}

}
