package com.demo.flashSale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.flashSale.dao.AddProductDetailsDao;
import com.demo.flashSale.entity.ProductDetailsEntity;

@Service
public class ProductDetailsService {

	@Autowired
	private AddProductDetailsDao dao;

	public void saveProduct(ProductDetailsEntity entity) {
		dao.save(entity);
	}

	public ProductDetailsEntity list() {
		Iterable<ProductDetailsEntity> list = dao.findAll();
		return list.iterator().next();
	}
}
