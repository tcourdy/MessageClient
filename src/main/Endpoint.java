package main;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.google.gson.Gson;

//jmsuser
//password1!

public class Endpoint {
	private String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private String DESTINATION = "jms/queue/notificationQueue";
	
	private JMSContext jmsContext;
	private Context namingContext;
	private ConnectionFactory connectionFactory;
	private boolean keepListening;
	private Integer endpointId;
	
	public Endpoint(Integer endpointId) {
		this.endpointId = endpointId; 
		try {
			namingContext = new InitialContext(Utils.getProperties());
			connectionFactory = (ConnectionFactory) namingContext.lookup(CONNECTION_FACTORY);
			jmsContext = connectionFactory.createContext("jmsuser", "password1!", JMSContext.AUTO_ACKNOWLEDGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listen() {
		try {
			Destination destination = (Destination) namingContext.lookup(DESTINATION);
			JMSConsumer consumer = jmsContext.createConsumer(destination, "endpointId = " + endpointId);
			while(keepListening) {
				Message msg = consumer.receive();
				processMessage(msg);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void processMessage(Message msg) {
		try {
			Gson gson = new Gson();
			Notification notification = gson.fromJson(msg.getBody(String.class), Notification.class);

			switch (notification.getNotificationType()) {
			case SubscribeToCommandQueue:
				subscribeToQueue(notification.getPayload());
			case Terminate:
				this.keepListening = false;
			default:
				System.out.println(notification.getNotificationType() + " not currently supported");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void subscribeToQueue(String queueName) {
		Subscriber sub = new Subscriber(queueName, jmsContext.createContext(0));
		Thread t = new Thread(sub);
		t.start();
	}
}
