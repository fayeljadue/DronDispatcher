package com.feljadue.app.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feljadue.app.vehicle.IVehicle;

public class DeliveryDispatcherImpl implements IDeliveryDispatcher {

	public static final Integer MAX_DELIVERY_BLOCKS = 10;

	public DeliveryDispatcherImpl() {

	}

	@Override
	public boolean dispatchLunchs(HashMap<Integer, List<String>> DeliveryRoutes, List<IVehicle> vehicleList) {

		return false;
	}

	@Override
	public HashMap<Integer,List<String>> verifyRoutes(HashMap<Integer, List<String>> deliveryRoutes) {

		HashMap<Integer, List<String>> verifiedDeliveryRoutes = new HashMap<Integer, List<String>>();
		
		for (Map.Entry<Integer, List<String>> entry : deliveryRoutes.entrySet()) {
			verifiedDeliveryRoutes.put(entry.getKey(), entry.getValue());
			List<String> validRoutes = validateRoutes(entry.getValue());
			verifiedDeliveryRoutes.replace(entry.getKey(), validRoutes);
		}
		
		return verifiedDeliveryRoutes;
	}

	private List<String> validateRoutes(List<String> routes) {
		int posX = 0,posY = 0,orientation = 0;
		boolean routeOutOfBounds = false;

		for (int i = 0; i < routes.size(); i++) {
			if (!routeOutOfBounds) {
				for (String routeCommand : routes.get(i).split("")) {
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
