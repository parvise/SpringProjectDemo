package com.journaldev.spring.jms;

import net.sf.ehcache.CacheManager;

public class SingetonCache {

	private static CacheManager _instance;

	public static CacheManager _getInstance() {
		if (_instance == null) {
			_instance = CacheManager.create();
		}

		return _instance;
	}

}
