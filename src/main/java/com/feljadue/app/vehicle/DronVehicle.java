package com.feljadue.app.vehicle;

import java.util.List;

public class DronVehicle implements IVehicle {
	
	private int id;
	private List<String> routes;
	private int coordX,CoordY,orientation;
	private String deliveryRoutesSumary;
	
	public DronVehicle(int id) {
		this.id = id;
		this.coordX = 0;
		this.CoordY = 0;
		this.orientation = 0;
		this.deliveryRoutesSumary = "";
	}

	
	public int getVehiculeId() {
		return this.id;
	}

	public void setVehiculeId(int id) {
		this.id = id;
	}
	
	
	public void setRoutes(List<String> routes) {
		this.routes = routes;
	}

	public void startDelivery() {
		
		
	}

	private void returnStart() {
		
	}

	public String SummaryDeliveryRoutes() {
		
		return null;
	}

}
