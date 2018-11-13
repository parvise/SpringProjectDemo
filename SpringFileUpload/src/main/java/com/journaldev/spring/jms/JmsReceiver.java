package com.journaldev.spring.jms;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsReceiver {

	public static void main(String[] args) throws JMSException {

		System.out.println(ActiveMQConnection.DEFAULT_BROKER_URL);

		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
		TopicConnection conn = factory.createTopicConnection();
		TopicSession sess = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = sess.createTopic("jmsTopic");
		TopicSubscriber sub = sess.createSubscriber(topic);
		MyListener listener = new MyListener();
		sub.setMessageListener(listener);
		conn.start();

	}

}
