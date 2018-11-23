package com.demo.flashSale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.flashSale.dao.PurchasedDateTimeDao;
import com.demo.flashSale.entity.PurchaseDateTimeEntity;

@Service
public class PurchasedDateTimeService {

	@Autowired
	private PurchasedDateTimeDao dao;

	public void save(PurchaseDateTimeEntity entity) {
		dao.save(entity);
	}

	public PurchaseDateTimeEntity list() {
		Iterable<PurchaseDateTimeEntity> list = dao.findAll();
		return list.iterator().next();
	}
}
