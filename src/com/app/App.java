package com.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
	
	private static final ExecutorService preparationExecutors = Executors.newFixedThreadPool(3);
	private static final ExecutorService cuissonExecutors = Executors.newFixedThreadPool(4);
	private static final ExecutorService embalageExecutors = Executors.newFixedThreadPool(2);
	private static final Integer amount = 100;
	
	public static void main(String[] args) {
		for(int i=1; i <= amount; i++) {
			preparerGateau(i);
		}
	}
	
	private static void preparerGateau(Integer num) {
		preparationExecutors.execute(() -> { 
			printEach(num, "Gateau "+num+" en cours de préparation", 2000);
			cuireGateau(num);
		});
	}
	
	private static void cuireGateau(Integer num) {
		cuissonExecutors.execute(() -> {
			printEach(num, "Gateau "+num+" en cours de cuisson", 3000);
			embalerGateau(num);
		});
	}
	
	private static void embalerGateau(Integer num) {
		embalageExecutors.execute(() -> printEach(num, "Gateau "+num+" en cours de d’emballage", 3000));
	}

	private static void printEach(Integer num, String message, Integer sleepTime) {
		System.out.println(message);
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
