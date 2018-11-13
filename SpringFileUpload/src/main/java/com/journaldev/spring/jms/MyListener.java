package com.journaldev.spring.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;

public class MyListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		System.out.println("MyListener called");
		if (message instanceof ActiveMQTextMessage) {
			ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
			try {
				System.out.println("Text" + msg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
