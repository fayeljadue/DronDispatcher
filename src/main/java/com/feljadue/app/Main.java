package com.feljadue.app;


import com.feljadue.app.inventory.DeliveryDispatcherImpl;
import com.feljadue.app.inventory.DronInventoryImpl;
import com.feljadue.app.inventory.IDeliveryDispatcher;
import com.feljadue.app.inventory.IInventory;
import com.feljadue.app.utilities.IReadArchive;
import com.feljadue.app.utilities.IWriteArchive;
import com.feljadue.app.utilities.ReadArchiveImpl;
import com.feljadue.app.utilities.WriteArchiveImpl;

public class Main {

	public static void main(String[] args) {
		
		IReadArchive reader = new ReadArchiveImpl();
		IWriteArchive writer = new WriteArchiveImpl();
		
		IInventory dronInventory = new DronInventoryImpl(20);
		IDeliveryDispatcher dispatcher = new DeliveryDispatcherImpl(reader.readRoutes());
		
		dispatcher.verifyRoutes();
		
		dispatcher.dispatchLunchs(dronInventory.listVehicules());
		
		dispatcher.startDispatch(dronInventory.listVehicules());
		
		System.out.println(dronInventory.listVehicules().get(0).SummaryDeliveryRoutes());
		
		dispatcher.printDronRoute(dronInventory.listVehicules(),writer);
		
		
	}

}
