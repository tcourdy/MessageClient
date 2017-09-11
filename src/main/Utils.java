package main;

import java.util.Properties;
import javax.naming.Context;


public class Utils {
	private static Properties env;
	
	public Utils() {
		
	}
	
	public static Properties getProperties() {
		if(env == null) {
			env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			env.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
			env.put(Context.SECURITY_PRINCIPAL, "jmsuser");
			env.put(Context.SECURITY_CREDENTIALS, "password1!");
		}
		
		return env;
	}

}
