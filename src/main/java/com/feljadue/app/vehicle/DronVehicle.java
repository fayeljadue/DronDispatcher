package com.feljadue.app.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DronVehicle implements IVehicle {
	
	private int id;
	private List<String> routes;
	private HashMap<String, Integer> position;
	private String deliveryRoutesSumary;
	
	public DronVehicle(int id) {
		this.id = id;
		this.position = new HashMap<String,Integer>();
		this.position.put("posX", 0);
		this.position.put("posY", 0);
		this.position.put("orientation", 0);
		this.deliveryRoutesSumary = "";
		this.routes = new ArrayList<String>();
	}

	public int getVehiculeId() {
		return this.id;
	}

	public void setVehiculeId(int id) {
		this.id = id;
	}
	
	public List<String> setRoutes(List<String> routes) {
		this.routes.addAll(routes);
		return this.routes;
	}

	public void startDelivery() {
		if(!routes.isEmpty()) {
			for(String route: routes) {
				if(route != null) {
					for(String command:route.split("")) {
						move(command);
					}
					deliveryWriter(position);
				}
			}
		}
		returnStart();
	}

	public void returnStart() {
		this.position.put("posX", 0);
		this.position.put("posY", 0);
		this.position.put("orientation", 0);
	}

	public String SummaryDeliveryRoutes() {
		String aux = deliveryRoutesSumary;
		deliveryRoutesSumary = "";
		return aux;
	}
	
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
			deliveryRoutesSumary +="== Reporte de entregas ==\n\n"+deliveryInfo;
		}else {
			deliveryRoutesSumary += deliveryInfo; 
		}
	}
	
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

	@Override
	public List<String> getRoutes() {
		return this.routes;
	}

}
