package com.demo.flashSale.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.flashSale.entity.EmailAddressBean;
import com.demo.flashSale.entity.EmailIdEntity;
import com.demo.flashSale.entity.RegisterBean;
import com.demo.flashSale.mail.SendMail;
import com.demo.flashSale.service.PublishEmailIdsService;

@Controller
public class PublishEmailController implements ErrorController {

	@Autowired
	private PublishEmailIdsService service;

	@Autowired
	private SendMail mail;

	@ModelAttribute
	public void addCommon(Model model) {
		model.addAttribute("emailBean", new EmailAddressBean());
		model.addAttribute("registerBean", new RegisterBean());
	}

	@GetMapping("/home")
	// @RequestMapping(value = "/home/{test}", method = RequestMethod.GET)
	public String mailHome() {
		return "sendMail";
	}

	@PostMapping("/admin/publish/mail")
	public String saveEmail(@ModelAttribute EmailAddressBean bean, Model model) {
		createNewEMailId(bean);
		Iterable<EmailIdEntity> list = service.listAllEmailIds();
		model.addAttribute("list", list);
		return "mailIdList";
	}

	private void createNewEMailId(EmailAddressBean bean) {
		EmailIdEntity entity = new EmailIdEntity();
		entity.setEmailAddress(bean.getEmailAddress());
		entity.setName(bean.getName());
		entity.setMobileNumber(bean.getMobileNumber());
		entity.setRegisterdStatus(Boolean.FALSE.toString());
		entity.setEmailId(UUID.randomUUID().toString());
		service.saveData(entity);
	}

	@GetMapping("/error")
	public String goToErrorPage(Model model) {
		model.addAttribute("message", "Invalid Url");
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@GetMapping("/publishMail")
	public String sendMail(Model model) {
		Iterable<EmailIdEntity> list = service.listAllEmailIds();
		String msg = "";
		for (EmailIdEntity emailIdEntity : list) {
			String content = "<a href=\"http://10.188.37.55:2020/validCheck/" + emailIdEntity.getEmailId() + "\">"
					+ "Please Register Flash Sale Starts at Tomorrow 3pm.</a>";
			//msg = mail.sendMail(emailIdEntity, content);
			System.out.println(content);
			msg = "Mail Ok";
		}

		model.addAttribute("sucess", msg);
		return "mailIdList";
	}

	@GetMapping("/validCheck/{id}")
	public String register(@PathVariable("id") String id, Model model) {
		EmailIdEntity entity = service.findById(id);
		System.out.println("id-->" + id);
		if (entity == null) {
			model.addAttribute("message", "You are not invited for this Sale");
			return "/error";
		}
		model.addAttribute("mailId", id);
		return "register";
	}
}
