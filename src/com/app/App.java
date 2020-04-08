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
			System.out.println("Gateau "+num+" en cours de préparation");
			try {
				TimeUnit.MILLISECONDS.sleep(2000);
				cuireGateau(num);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
	
	private static void cuireGateau(Integer num) {
		cuissonExecutors.execute(() -> {
			System.out.println("Gateau "+num+" en cours de cuisson");
			try {
				TimeUnit.MILLISECONDS.sleep(3000);
				embalerGateau(num);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

	
	private static void embalerGateau(Integer num) {
		embalageExecutors.execute(() -> {
			System.out.println("Gateau "+num+" en cours de d’emballage");
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}


}
