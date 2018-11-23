package com.demo.flashSale.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.flashSale.entity.RegisterEntity;
import com.demo.flashSale.service.UserRegistrationService;
import com.demo.flashSale.threads.BuyProductExecutor;
import com.demo.flashSale.threads.PurchaseProductCallabale;

@Controller
public class BuyProductController {

	private CyclicBarrier _cyclicBarrier;

	@Autowired
	private BuyProductExecutor executor;

	private Map<Integer, RegisterEntity> storeMap = new HashMap<>();

	private static int count = 1;

	@Autowired
	private UserRegistrationService regService;

	@PostMapping(name = "/purchaseProduct")
	public String purchaseProduct(@ModelAttribute RegisterEntity entity, Model model)
			throws InterruptedException, ExecutionException {
		System.out.println("buyProduct---> " + entity);
		entity = regService.findUserRegistration(entity.getMailId(), entity.getRegId());
		if (entity == null) {
			model.addAttribute("message", "You are not Authorized to Buy a Product.");
			return "/error";
		}
		if (!entity.getStatus().equalsIgnoreCase("ACT")) {
			model.addAttribute("message", "Your Order Already Has Placed .");
			return "/error";
		}
		_cyclicBarrier = executor._getInstance();
		System.out.println("count" + count);
		if (count <= _cyclicBarrier.getParties()) {
			entity.setPurchasedDate(new Date());
			entity.setStatus("INACT");
			storeMap.put(count, entity);
			count++;
		} else {
			Set<Entry<Integer, RegisterEntity>> set = storeMap.entrySet();

			for (Entry<Integer, RegisterEntity> entry : set) {
				RegisterEntity regEntity = entry.getValue();

				PurchaseProductCallabale callabale = new PurchaseProductCallabale(regEntity, _cyclicBarrier);
				ExecutorService executor = Executors.newFixedThreadPool(10);
				Future<String> future = executor.submit(callabale);

				if ("Stock is Over".equalsIgnoreCase(future.get())) {
					System.out.println("--->" + future.get());
					_cyclicBarrier = null;
					count = 1;
					storeMap.clear();
				}

			}
		}

		System.out.println("storeMap" + storeMap.size());
		System.out.println("Dead End returned");
		return "success";
	}
}
