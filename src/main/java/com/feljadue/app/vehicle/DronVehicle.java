package com.feljadue.app.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DronVehicle implements IVehicle {
	
	private int id;
	private List<String> routes;
	private HashMap<String, Integer> position;
	private String deliveryRoutesSumary;
	
	//Create the dron with and input id
	public DronVehicle(int id) {
		this.id = id;
		this.position = new HashMap<String,Integer>();
		this.position.put("posX", 0);
		this.position.put("posY", 0);
		this.position.put("orientation", 0);
		this.deliveryRoutesSumary = "";
		this.routes = new ArrayList<String>();
	}
	
	//Get the vehicule Id
	public int getVehiculeId() {
		return this.id;
	}
	
	//Set a new id for a vehicle
	public void setVehiculeId(int id) {
		this.id = id;
	}
	
	//Program the routes for the dron to deliver
	public List<String> setRoutes(List<String> routes) {
		this.routes.clear();
		this.routes.addAll(routes);
		return this.routes;
	}
	
	//Start the delivery of the dron using the program route
	public boolean startDelivery() {
		boolean deliveryStatus = false;
		if(!routes.isEmpty()) {
			for(String route: routes) {
				if(route != null) {
					for(String command:route.split("")) {
						move(command);
					}
					deliveryWriter(position);
				}
			}
			deliveryStatus = true;
			returnStart();
		}
		return deliveryStatus;
	}
	
	//Set the dron position to the incial position
	public void returnStart() {
		this.position.put("posX", 0);
		this.position.put("posY", 0);
		this.position.put("orientation", 0);
	}
	
	//Retuns de information of the delivery made
	public String SummaryDeliveryRoutes() {
		String aux = deliveryRoutesSumary;
		deliveryRoutesSumary = "";
		return aux;
	}
	
	//Take the position and write it in the summaryReport
	private void deliveryWriter(HashMap<String, Integer> position) {
		
		String deliveryInfo = "("+position.get("posX")+", "+position.get("posY")+") dirección ";
		if(position.get("orientation") ==-1) {
			deliveryInfo += "Oriente\n";
		}else if(position.get("orientation") ==0) {
			deliveryInfo += "Norte\n";
		}else if(position.get("orientation") ==1) {
			deliveryInfo += "Occidente\n";
		}else {
			deliveryInfo += "Sur\n";
		}
		
		if(deliveryRoutesSumary.isEmpty()) {
			deliveryRoutesSumary +="== Reporte de entregas ==\n"+deliveryInfo;
		}else {
			deliveryRoutesSumary += deliveryInfo; 
		}
	}
	
	//Make the movement of the drone using the comand
	private HashMap<String, Integer> move(String command) {
		Integer x = position.get("posX"); 
		Integer y = position.get("posY");
		Integer orientation = position.get("orientation");
		
		if (command.equals("A")) {
			if (orientation == 0) {
				y++;
			} else if (orientation == 1) {
				x--;
			} else if (orientation == 2) {
				y--;
			} else if (orientation == -1) {
				x++;
			}
		} else if (command.equals("I")) {
			orientation++;
			if (orientation > 2) {
				orientation = -1;
			}
		} else if (command.equals("D")) {
			orientation--;
			if (orientation < -1) {
				orientation = 2;
			}
		}
		this.position.put("posX", x);
		this.position.put("posY", y);
		this.position.put("orientation", orientation);
		return this.position;
	}
	
	//Get the program routes of the drone
	@Override
	public List<String> getRoutes() {
		return this.routes;
	}

}
