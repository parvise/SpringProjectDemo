package com.demo.flashSale.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.flashSale.entity.PurchaseDateTimeEntity;
import com.demo.flashSale.service.PurchasedDateTimeService;

public class PurchaseDateTimeController {

	@Autowired
	private PurchasedDateTimeService service;

	@PostMapping(name = "/time")
	public String saveTime() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse("2018-11-22 14:00:00"); // mysql datetime
															// format
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		System.out.println(calendar.getTime());
		PurchaseDateTimeEntity entity = new PurchaseDateTimeEntity();
		entity.setDateId(UUID.randomUUID().toString());
		entity.setDateTime(calendar.getTime());
		service.save(entity);
		return "Save success";
	}

}
