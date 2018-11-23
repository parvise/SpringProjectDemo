package com.demo.flashSale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.flashSale.entity.ProductDetailsEntity;
import com.demo.flashSale.service.ProductDetailsService;

@RestController
public class AddProductDetailsController {

	@Autowired
	private ProductDetailsService service;

	@GetMapping("/addProduct")
	public String addProduct() {
		ProductDetailsEntity entity = new ProductDetailsEntity();
		entity.setProdId(1);
		entity.setProdName("Titan Watch");
		entity.setQuantity(4);
		entity.setSoldOut(0);
		entity.setStatus("ACT");

		service.saveProduct(entity);
		return "Products Added";
	}

}
