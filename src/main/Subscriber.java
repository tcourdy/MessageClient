package main;

import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Subscriber implements Runnable {
	private Destination dest;
	private JMSContext jmsContext;
	private Context context;
	public Subscriber(String queueName, JMSContext jmsContext) {
		try {
			context = new InitialContext(Utils.getProperties());
			this.jmsContext = jmsContext;
			this.dest = (Destination)context.lookup(queueName);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			JMSConsumer consumer = jmsContext.createConsumer(dest);
			Message msg = consumer.receive();
			System.out.println(msg.getBody(String.class));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
