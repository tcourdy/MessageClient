package main;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		int id = 0;
		ArrayList<Endpoint> endpoints = new ArrayList<>();
		while(id < 10) {
			Endpoint e = new Endpoint(id);
			e.listen();
			endpoints.add(e);
		}
	}

}
