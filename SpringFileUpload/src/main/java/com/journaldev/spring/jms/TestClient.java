package com.journaldev.spring.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.journaldev.spring.model.StudentBean;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class TestClient {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ApplicationContext jmsContext = null;
		jmsContext = new FileSystemXmlApplicationContext(
				"C:/Users/parvisecp11788/Downloads/SpringProjects/SpringFileUpload/src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml");

		//C:\Users\parvisecp11788\Downloads\SpringProjects\SpringFileUpload\src\main\webapp\WEB-INF\spring\appServlet\servlet-context.xml
		
		SimpleMessageSender messageSender = (SimpleMessageSender) jmsContext.getBean("simpleMessageSender");

		// Create a session within the connection.
		messageSender.sendMessage("Welcome To India");
		
		
		StudentBean bean = new StudentBean();
		bean.setId(1);
		bean.setName("JMS");
		bean.setCountry("USA");
		
	//	messageSender.sendMessage(bean);
		
		
		
		//messageSender.sendMessage(cache);
	}

}


