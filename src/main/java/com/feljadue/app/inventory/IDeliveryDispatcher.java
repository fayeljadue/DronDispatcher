package com.feljadue.app.inventory;

import java.util.HashMap;
import java.util.List;

import com.feljadue.app.vehicle.IVehicle;

public interface IDeliveryDispatcher {

	public boolean dispatchLunchs(HashMap<Integer,List<String>> deliveryRoutes,List<IVehicle> vehicleList);
	public HashMap<Integer,List<String>> verifyRoutes(HashMap<Integer,List<String>> deliveryRoutes);
	
}
