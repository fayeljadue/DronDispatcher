package com.feljadue.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.feljadue.app.inventory.DeliveryDispatcherImpl;
import com.feljadue.app.inventory.IDeliveryDispatcher;
import com.feljadue.app.vehicle.DronVehicle;
import com.feljadue.app.vehicle.IVehicle;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryDispatcherImplTest {

	static IDeliveryDispatcher deliveryDispatcher1;
	static IDeliveryDispatcher deliveryDispatcher2;
	
	@BeforeClass
	public static void init() {
		HashMap<Integer, List<String>> testMap = new HashMap<Integer, List<String>>();
		testMap.put(1, new ArrayList<>(Arrays.asList("ADDADIADDAD","AIDAADAIA","ADDIADADAAAAAAAAAAAAAAAA")));
		testMap.put(2, new ArrayList<>(Arrays.asList("ADDADI","AIDD","ADDI")));
		testMap.put(3, new ArrayList<>(Arrays.asList("ADDADI","AIDAAD","ADDIAD")));
		deliveryDispatcher1 = new DeliveryDispatcherImpl(testMap);
		deliveryDispatcher2 = new DeliveryDispatcherImpl(null);
	}
	
	@Test
	public void testVerifyRoutes() {
		deliveryDispatcher1.verifyRoutes();
		deliveryDispatcher2.verifyRoutes();
	}

	@Test
	public void testDispatchLunchs() {
		List<IVehicle> listOfMockDrons=new ArrayList<IVehicle>();
		
		IVehicle dron1 = Mockito.mock(DronVehicle.class);
		IVehicle dron2 = Mockito.mock(DronVehicle.class);
		IVehicle dron3 = Mockito.mock(DronVehicle.class);
		
		listOfMockDrons.add(dron1);
		listOfMockDrons.add(dron2);
		listOfMockDrons.add(dron3);
		
		when(dron1.getVehiculeId()).thenReturn(1);
		when(dron2.getVehiculeId()).thenReturn(2);
		when(dron3.getVehiculeId()).thenReturn(3);
		
		assertEquals("Equals Objects", true, deliveryDispatcher1.dispatchLunchs(listOfMockDrons));
	}

	@Test
	public void testStartDispatch() {
		
		List<IVehicle> listOfMockDrons=new ArrayList<IVehicle>();
		
		IVehicle dron1 = Mockito.mock(DronVehicle.class);
		IVehicle dron2 = Mockito.mock(DronVehicle.class);
		IVehicle dron3 = Mockito.mock(DronVehicle.class);
		
		listOfMockDrons.add(dron1);
		listOfMockDrons.add(dron2);
		listOfMockDrons.add(dron3);
		
		when(dron1.startDelivery()).thenReturn(true);
		when(dron2.startDelivery()).thenReturn(true);
		when(dron3.startDelivery()).thenReturn(true);
		
		assertEquals("Equals Objects", true, deliveryDispatcher1.startDispatch(listOfMockDrons));
	}
	
	@Test
	public void testStartDispatch2() {
		
		List<IVehicle> listOfMockDrons=new ArrayList<IVehicle>();
		
		assertEquals("Equals Objects", false, deliveryDispatcher1.startDispatch(listOfMockDrons));
	}

	@Test
	public void testPrintDronRoute() {
		
	}

}
