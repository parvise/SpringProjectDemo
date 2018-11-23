package com.demo.flashSale.threads;

import java.util.concurrent.CyclicBarrier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.demo.flashSale.entity.ProductDetailsEntity;
import com.demo.flashSale.service.ProductDetailsService;

@Component
public class BuyProductExecutor {

	private CyclicBarrier _cyclicBarrier;
	@Autowired
	private ProductDetailsService prodService;

	private BuyProductExecutor() {

	}

	public CyclicBarrier _getInstance() {
		synchronized (BuyProductExecutor.class) {
			System.out.println("BuyProductExecutor class" + _cyclicBarrier);
			if (_cyclicBarrier == null) {
				ProductDetailsEntity entity = prodService.list();
				_cyclicBarrier = new CyclicBarrier(entity.getQuantity());
			}
		}
		return _cyclicBarrier;
	}
}
