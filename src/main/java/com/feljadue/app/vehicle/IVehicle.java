package com.feljadue.app.vehicle;

import java.util.List;

public interface IVehicle {
	
	public int getVehiculeId();
	public void setVehiculeId(int id);
	public List<String> setRoutes(List<String> routes);
	public List<String> getRoutes();
	public boolean startDelivery();
	public void returnStart();
	public String SummaryDeliveryRoutes();

}
