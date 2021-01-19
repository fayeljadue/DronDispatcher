package com.feljadue.app;


import java.io.File;

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
		
		init();
		
		//Create the i/o intefaces for read and write files in the program
		IReadArchive reader = new ReadArchiveImpl();
		IWriteArchive writer = new WriteArchiveImpl();
		
		//Creation of the drones Inventory and the delivery dispatcher of drones 
		IInventory dronInventory = new DronInventoryImpl(20);
		IDeliveryDispatcher dispatcher = new DeliveryDispatcherImpl(reader.readRoutes());
		
		// Dispatcher verify the routes for the Drones
		dispatcher.verifyRoutes();
		
		// Dispatcher programm the routes of each dron
		dispatcher.dispatchLunchs(dronInventory.listVehicules());
		
		// Dispatcher starts the delivery for the lunch
		dispatcher.startDispatch(dronInventory.listVehicules());
		
		// Dispatcher take the drones route and generate the report of each drone
		dispatcher.printDronRoute(dronInventory.listVehicules(),writer);
		
		
	}
	
	private static void init() {
		File fIn = new File("inputRoutes");
		File fOut = new File("outputRoutesSummary");
		try{
		    if(fIn.mkdir() && fOut.mkdir()) { 
		        System.out.println("Directories Created");
		    } else {
		        System.out.println("Directories exists");
		    }
		} catch(Exception e){
		    e.printStackTrace();
		} 
	}

}
