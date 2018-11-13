package com.journaldev.spring.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQTextMessage;

public class SimpleMessageListener implements MessageListener {
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("Welcome");
			if (message instanceof ActiveMQObjectMessage) {
				ActiveMQObjectMessage msg = (ActiveMQObjectMessage) message;
				System.out.println("Obj" + msg.getObject());
			}

			if (message instanceof ActiveMQTextMessage) {
				ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
				System.out.println("Text"+msg.getText());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
