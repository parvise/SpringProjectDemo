package com.demo.flashSale.controller;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.flashSale.entity.ProductDetailsEntity;
import com.demo.flashSale.entity.RegisterEntity;
import com.demo.flashSale.service.ProductDetailsService;
import com.demo.flashSale.service.UserRegistrationService;
import com.demo.flashSale.threads.SoldProductThread;

@Controller
public class SoldProductController {

	@Autowired
	private UserRegistrationService regService;

	@Autowired
	private ProductDetailsService prodService;

	@Autowired
	private SoldProductThread soldThread;

	@PostMapping("/soldProduct")
	public String soldProduct(@ModelAttribute RegisterEntity regEntity, Model model)
			throws InterruptedException, ExecutionException {

		System.out.println("soldProduct Controller---> " + regEntity);
		regEntity = regService.findUserRegistration(regEntity.getMailId(), regEntity.getRegId());
		if (regEntity == null) {
			model.addAttribute("message", "You are not Authorized to Buy a Product.");
			return "/error";
		}
		if (!regEntity.getStatus().equalsIgnoreCase("ACT")) {
			model.addAttribute("message", "Your Order Already Has Placed .");
			return "/error";
		}

		regEntity.setStatus("INACT");
		regEntity.setPurchasedDate(new Date());
		regService.save(regEntity);

		ExecutorService executor = Executors.newFixedThreadPool(10);
		Future<ProductDetailsEntity> future = executor.submit(soldThread);
		ProductDetailsEntity prodEntity = future.get();
		System.out.println(prodEntity);
		return "success";
	}
}
