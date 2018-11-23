package com.demo.flashSale.controller;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.HandlerMapping;

import com.demo.flashSale.entity.EmailIdEntity;
import com.demo.flashSale.entity.ProductDetailsEntity;
import com.demo.flashSale.entity.PurchaseDateTimeEntity;
import com.demo.flashSale.entity.RegisterBean;
import com.demo.flashSale.entity.RegisterEntity;
import com.demo.flashSale.mail.SendMail;
import com.demo.flashSale.service.ProductDetailsService;
import com.demo.flashSale.service.PublishEmailIdsService;
import com.demo.flashSale.service.PurchasedDateTimeService;
import com.demo.flashSale.service.UserRegistrationService;

@Controller
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService regService;

	@Autowired
	private PublishEmailIdsService emailService;

	@Autowired
	private ProductDetailsService prodService;

	@Autowired
	private PurchasedDateTimeService dateService;

	@Autowired
	private SendMail mail;

	@PostMapping("/register")
	public String register(@ModelAttribute RegisterBean bean, HttpServletRequest request) {
		System.out.println("Welcome" + bean);
		RegisterEntity entity = convertBeanToEntity(bean);
		entity = regService.save(entity);
		String msg = "";
		if (entity != null) {
			EmailIdEntity mailIdEntity = emailService.findById(entity.getMailId());
			mailIdEntity.setRegisterdStatus(Boolean.TRUE.toString());
			emailService.saveData(mailIdEntity);
			String content = "<a href=\"http://10.188.37.55:2020/regSuccess/" + mailIdEntity.getEmailId() + "/"
					+ entity.getRegId() + "\">" + "Your Register Successfully.. Sale starts at Tomorrow 3pm.</a>";
			System.out.println("Reg--->" + content);
			// msg = mail.sendMail(mailIdEntity, content);

			return "success";
		}
		return "/error";
	}

	@GetMapping("/regSuccess/{emailId}/{regId}")
	public String regSuccess(@PathVariable("emailId") String emailId, @PathVariable("regId") String regId, Model model,
			HttpServletRequest request) throws InterruptedException, ExecutionException {

		final String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
		final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
				.toString();

		String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);

		System.out.println(emailId + "-->" + regId);
		RegisterEntity entity = regService.findUserRegistration(emailId, regId);
		if (entity == null) {
			model.addAttribute("message", "You are not authorized to buy a Product....");
			return "/error";
		}

		if (entity.getStatus().equalsIgnoreCase("ACT")) {
			model.addAttribute("user", entity.getName());
			PurchaseDateTimeEntity dateEntity = dateService.list();
			model.addAttribute("message",
					"Please Buy a Product on this Day. Sale starts at this time = " + dateEntity.getDateTime());
			System.out.println("Date & Time ::" + new Date().compareTo(dateEntity.getDateTime()));
			model.addAttribute("buyBtnDisable", "disabled");
			if (new Date().compareTo(dateEntity.getDateTime()) > 0) {
				System.out.println("Check");
				model.addAttribute("message", "Please Buy a Product");
				model.addAttribute("buyBtnDisable", "");
				ProductDetailsEntity prodEntity = prodService.list();

				if (prodEntity == null) {
					model.addAttribute("message", "No Products to Buy");
					return "/error";
				}

				if (prodEntity.getQuantity() == 0) {
					model.addAttribute("message",
							"No Product is in Stock. Please try for Some other sale. Sorry for Inconvenince");
					return "/error";
				}

				model.addAttribute("stockAvailable", prodEntity.getQuantity() - prodEntity.getSoldOut());
				model.addAttribute("totalStock", prodEntity.getQuantity());
				model.addAttribute("mailId", entity.getMailId());
				model.addAttribute("regId", entity.getRegId());
				System.out.println("Test---> " + model.containsAttribute("regEntity"));
				model.addAttribute("regEntity", new RegisterEntity());
			}
			return "buyProduct";
		}
		model.addAttribute("message", "You are already purchased a Product.User allows to order One time Only...");
		return "/error";
	}

	private RegisterEntity convertBeanToEntity(RegisterBean bean) {

		RegisterEntity entity = new RegisterEntity();
		entity.setRegId(UUID.randomUUID().toString());
		entity.setAddress(bean.getAddress());
		entity.setContactNo(bean.getContactNo());
		entity.setCreatedDate(new Date());
		entity.setGender(bean.getGender());
		entity.setMailId(bean.getMailId());
		entity.setName(bean.getName());
		entity.setStatus("ACT");
		return entity;
	}
}
