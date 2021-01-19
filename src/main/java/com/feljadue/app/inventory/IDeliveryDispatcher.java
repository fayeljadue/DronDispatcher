package com.feljadue.app.inventory;

import java.util.HashMap;
import java.util.List;

import com.feljadue.app.utilities.IWriteArchive;
import com.feljadue.app.vehicle.IVehicle;

public interface IDeliveryDispatcher {

	public boolean dispatchLunchs(List<IVehicle> vehicleList);
	public boolean startDispatch(List<IVehicle> vehicleList);
	public HashMap<Integer,List<String>> verifyRoutes();
	public void printDronRoute(List<IVehicle> vehicleList,IWriteArchive writer);
	
}
