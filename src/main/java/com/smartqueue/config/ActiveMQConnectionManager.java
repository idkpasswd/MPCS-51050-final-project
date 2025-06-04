package com.smartqueue.config;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.ConnectionFactory;

public class ActiveMQConnectionManager {

	private static ActiveMQConnectionFactory connectionFactory;

	private static final String BROKER_URL = "tcp://localhost:61616"; 

	private ActiveMQConnectionManager() {
		// Private constructor to prevent instantiation
	}

	public static synchronized ConnectionFactory getConnectionFactory() {
		if (connectionFactory == null) {
			connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
		}
		return connectionFactory;
	}
}
