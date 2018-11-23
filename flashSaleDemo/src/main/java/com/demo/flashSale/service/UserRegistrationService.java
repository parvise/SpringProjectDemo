package com.demo.flashSale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.flashSale.dao.UserRegisterDao;
import com.demo.flashSale.entity.RegisterEntity;

@Service
public class UserRegistrationService {

	@Autowired
	public UserRegisterDao dao;

	public RegisterEntity save(RegisterEntity entity) {
		return dao.save(entity);
	}

	public RegisterEntity findUserRegistration(String emailId, String regId) {
		return dao.findUserRegistration(emailId, regId);
	}

}
