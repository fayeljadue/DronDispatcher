package com.feljadue.app.inventory;

import java.util.List;

import com.feljadue.app.vehicle.IVehicle;

public interface IInventory {
	
	public List<IVehicle> listVehicules();
	
	public boolean addVehicule(IVehicle vehicule);
	
	public boolean removeVehicule(int idVehicle);

}
