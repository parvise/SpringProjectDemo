package com.demo.flashSale.threads;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.flashSale.entity.ProductDetailsEntity;
import com.demo.flashSale.entity.RegisterEntity;
import com.demo.flashSale.service.ProductDetailsService;

@Component
public class SoldProductThread implements Callable<ProductDetailsEntity> {

	@Autowired
	private ProductDetailsService prodService;

	@Override
	public ProductDetailsEntity call() throws Exception {
		synchronized (SoldProductThread.class) {
			ProductDetailsEntity entity = prodService.list();
			System.out.println("Quantity" + entity.getQuantity());
			System.out.println("SoldOut" + entity.getSoldOut());
			if (entity.getQuantity() == entity.getSoldOut()) {
				entity.setEndDate(new Date());
				entity.setStatus("INACT");
			}

			entity.setSoldOut(entity.getSoldOut() + 1);

			if (entity.getSoldOut() == 1) {
				entity.setStartDate(new Date());
			}
			prodService.saveProduct(entity);
			return entity;
		}
	}

}
