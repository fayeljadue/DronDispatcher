package com.feljadue.app;

import com.feljadue.app.inventory.DeliveryDispatcherImpl;
import com.feljadue.app.inventory.IDeliveryDispatcher;
import com.feljadue.app.utilities.IReadArchive;
import com.feljadue.app.utilities.ReadArchiveImpl;

public class Main {

	public static void main(String[] args) {
		
		IReadArchive reader = new ReadArchiveImpl();
		
		IDeliveryDispatcher dispatcher = new DeliveryDispatcherImpl();
		
		System.out.println(dispatcher.verifyRoutes(reader.readRoutes()));
		
	}

}
