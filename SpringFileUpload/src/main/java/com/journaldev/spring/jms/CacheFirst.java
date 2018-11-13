package com.journaldev.spring.jms;

import com.journaldev.spring.model.StudentBean;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheFirst {

	public static void main(String[] args) {

		StudentBean bean = new StudentBean();
		bean.setId(1);
		bean.setName("JMS");
		bean.setCountry("USA");
		
		CacheManager manager = SingetonCache._getInstance();
		//manager.addCache("cache1");
		System.out.println(manager);
		Cache cache = manager.getCache("cache1");
		cache.put(new Element(1, bean));
		
		Element ele = cache.get(1);
		System.out.println(ele);
	}

}
