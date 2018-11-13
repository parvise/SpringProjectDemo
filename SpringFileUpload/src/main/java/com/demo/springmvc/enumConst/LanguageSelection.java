package com.demo.springmvc.enumConst;

public enum LanguageSelection {
	SPANISH("sp"), FRENCH("fr"), ENGLISH("en");

	private String code;

	private LanguageSelection(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
