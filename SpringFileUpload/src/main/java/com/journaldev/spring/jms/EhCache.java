package com.journaldev.spring.jms;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhCache {

	public static void main(String[] args) {
		
		CacheManager mgr = SingetonCache._getInstance();
		System.out.println(mgr);
		Cache cache = mgr.getCache("cache1");
		Element ele = cache.get(1);
		System.out.println(ele);

	}

}
