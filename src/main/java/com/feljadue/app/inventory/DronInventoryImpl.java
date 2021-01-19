package com.feljadue.app.inventory;

import java.util.ArrayList;
import java.util.List;

import com.feljadue.app.vehicle.DronVehicle;
import com.feljadue.app.vehicle.IVehicle;

public class DronInventoryImpl implements IInventory{
	
	private List<IVehicle> dronInventory;
	private int maxNumberDrons; 
	// Create a inventory of drones with the number indicate in the constructor
	public DronInventoryImpl(int numberOfDrons) {
		this.maxNumberDrons = numberOfDrons;
		dronInventory = new ArrayList<IVehicle>();
		
		for(int i=1;i<=maxNumberDrons;i++) {
			dronInventory.add(new DronVehicle(i));
		}
	}
	
	// List all vehicles in the inventory
	public List<IVehicle> listVehicules() {
		return dronInventory;
	}

	// Add new vehicles to the list
	public boolean addVehicule(IVehicle vehicule) {
		boolean operation = false;
		boolean dronIdExist = false;
		
		for(int i = 1;i<=dronInventory.size();i++) {
			if(vehicule.getVehiculeId() == dronInventory.get(i-1).getVehiculeId()) {
				dronIdExist = true;
			}
		}
		if(dronInventory.size()<maxNumberDrons && !dronIdExist) {
			dronInventory.add(vehicule);
			operation = true;
		}
		
		return operation;
	}

	//Remove a vehicle in the list
	public boolean removeVehicule(int idVehicle) {
		boolean remove = false;
		
		for(int i = 1;i<=dronInventory.size();i++) {
			IVehicle vehicle = dronInventory.get(i);
			if(vehicle.getVehiculeId() == idVehicle) {
				dronInventory.remove(i);
				i = dronInventory.size()+1;
				remove = true;
			}
		}
		
		return remove;
	}	

}
