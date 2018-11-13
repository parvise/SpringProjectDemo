package com.demo.springmvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.journaldev.spring.model.StudentBean;

public class LoginValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		System.out.println("LoginValidator" + arg0);
		return StudentBean.class.equals(arg0);
	}

	public void validate(Object arg0, Errors errors) {
		System.out.println("validator done" + errors);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "valid.userName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
	}

}
