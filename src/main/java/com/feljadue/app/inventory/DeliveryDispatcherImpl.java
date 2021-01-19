package com.feljadue.app.inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.feljadue.app.utilities.IWriteArchive;
import com.feljadue.app.vehicle.IVehicle;

public class DeliveryDispatcherImpl implements IDeliveryDispatcher {

	public static final Integer MAX_DELIVERY_BLOCKS = 10;
	public static final Integer MAX_LUCH_DELIVERY = 3;
	private HashMap<Integer, List<String>> deliveryRoutes;
	private HashMap<Integer, List<String>> verifiedDeliveryRoutes;
	
	//Class incharge of de dron routes verification and dron delivery
	public DeliveryDispatcherImpl(HashMap<Integer, List<String>> deliveryRoutes) {
		HashMap<Integer, List<String>> copyDeliveryRoutes = new HashMap<Integer, List<String>>();
		
		if(deliveryRoutes != null) {
			for (Map.Entry<Integer, List<String>> entry : deliveryRoutes.entrySet())
		    {
				copyDeliveryRoutes.put(entry.getKey(),
		           new ArrayList<String>(entry.getValue()));
		    }
			this.deliveryRoutes = copyDeliveryRoutes;
		}else {
			this.deliveryRoutes = null;
		}
		
		this.verifiedDeliveryRoutes = new HashMap<Integer, List<String>>();
	}

	//Asign the verified routes to the drons, using the list of drones and the mapped routes
	@Override
	public boolean dispatchLunchs(List<IVehicle> vehicleList) {
		
		boolean assigment = false;
		if(!vehicleList.isEmpty() || !verifiedDeliveryRoutes.isEmpty()) {
			for (Map.Entry<Integer, List<String>> entry : verifiedDeliveryRoutes.entrySet()) {
				int dronId = entry.getKey();
				for (int i = 0; i < vehicleList.size(); i++) {
					if (vehicleList.get(i).getVehiculeId() == dronId) {
						List<String> maxRoutes = new ArrayList<String>(entry.getValue())
								.stream().limit(MAX_LUCH_DELIVERY).collect(Collectors.toList());
						
						vehicleList.get(i).setRoutes(maxRoutes);
						i = vehicleList.size();
					}
				}
			}
			assigment = true;
		}
		return assigment;
	}
	//verified de routes of the input directory, the number of delivery and the range of deliver
	@Override
	public HashMap<Integer,List<String>> verifyRoutes() {

		HashMap<Integer, List<String>> verifiedDeliveryRoutes = new HashMap<Integer, List<String>>();
		if(deliveryRoutes != null) {
			for (Map.Entry<Integer, List<String>> entry : deliveryRoutes.entrySet()) {
				verifiedDeliveryRoutes.put(entry.getKey(), new ArrayList<String>(entry.getValue()));
				List<String> validRoutes = validateRoutes(entry.getValue());
				verifiedDeliveryRoutes.replace(entry.getKey(), validRoutes);
			}
			this.verifiedDeliveryRoutes = verifiedDeliveryRoutes;
			return verifiedDeliveryRoutes;
		}else {
			return null;
		}
	}
	//Start the dispatch of the drones
	@Override
	public boolean startDispatch(List<IVehicle> vehicleList) {
		if(!vehicleList.isEmpty()) {
			for(IVehicle vehicle : vehicleList) {
				vehicle.startDelivery();
			}
			return true;
		}
		return false;
	}
	//Take the route the drones made an with the writer make the reports
	@Override
	public void printDronRoute(List<IVehicle> vehicleList, IWriteArchive writer) {
		if(!vehicleList.isEmpty()) {
			for(IVehicle vehicle:vehicleList) {
				String name = "out"+vehicle.getVehiculeId()+".txt";
				String vehicleSummary = vehicle.SummaryDeliveryRoutes();
				try {
					writer.writeDeliveryRoutes(name, vehicleSummary);
				} catch (IOException e) {
					System.out.println("Cant write summary routes for dron number : "+vehicle.getVehiculeId());
				}
			}
		}
	}
	//Make the validation of the routes for the dron
	private List<String> validateRoutes(List<String> routes) {
		int posX = 0,posY = 0,orientation = 0;
		boolean routeOutOfBounds = false;
		List<String> validRoutes = new ArrayList<String>(routes);
		
		for (int i = 0; i < routes.size(); i++) {
			if (!routeOutOfBounds) {
				for (String routeCommand : validRoutes.get(i).split("")) {
					HashMap<String, Integer> dronPosition = dronMoveSimultion(routeCommand, posX, posY, orientation);
					posX = dronPosition.get("posX");
					posY = dronPosition.get("posY");
					orientation = dronPosition.get("orientation");
				}
			}
			
			double routeMagnitud = Math.sqrt(posX * posX + posY * posY);
			if (routeMagnitud > MAX_DELIVERY_BLOCKS || routeOutOfBounds) {
				routeOutOfBounds = true;
				routes.set(i, null);
			}

		}
		return routes;
	}
	//Make the movemt simulation of the drone to know if its valid or not
	private HashMap<String, Integer> dronMoveSimultion(String command,int posX,int posY,int orientation) {
		if (command.equals("A")) {
			if (orientation == 0) {
				posY++;
			} else if (orientation == 1) {
				posX--;
			} else if (orientation == 2) {
				posY--;
			} else if (orientation == -1) {
				posX++;
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
		HashMap<String, Integer> mapVariable = new HashMap<>();
		mapVariable.put("posX", posX);
		mapVariable.put("posY", posY);
		mapVariable.put("orientation", orientation);
		return mapVariable;
	}

}
