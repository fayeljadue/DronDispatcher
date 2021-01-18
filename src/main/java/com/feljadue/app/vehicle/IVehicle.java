package com.feljadue.app.vehicle;

import java.util.List;

public interface IVehicle {
	
	public int getVehiculeId();
	public void setVehiculeId(int id);
	public void setRoutes(List<String> routes);
	public void startDelivery();
	public String SummaryDeliveryRoutes();

}
