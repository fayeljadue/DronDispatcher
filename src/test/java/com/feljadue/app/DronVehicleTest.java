package com.feljadue.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.feljadue.app.vehicle.DronVehicle;
import com.feljadue.app.vehicle.IVehicle;

public class DronVehicleTest {
	
	static IVehicle dron;
	
	@BeforeClass
	public static void init() {
		dron = new DronVehicle(1);
		List<String> TestRoutes = new ArrayList<String>();
		TestRoutes.add("AAAAIAA");
		TestRoutes.add("DDDAIAD");
		TestRoutes.add("AAIADAD");
		dron.setRoutes(TestRoutes);
	}

	@Test
	public void testDronVehicle() {
		IVehicle dronTest = new DronVehicle(1);
		assertEquals("Equals Objects", 1, dronTest.getVehiculeId());
	}

	@Test
	public void testGetVehiculeId() {
		assertEquals("Equals Objects", 1, dron.getVehiculeId());
	}

	@Test
	public void testSetVehiculeId() {
		dron.setVehiculeId(3);
		assertEquals("Equals Objects", 3, dron.getVehiculeId());
	}

	@Test
	public void testSetRoutes() {
		List<String> TestRoutes = new ArrayList<String>();
		TestRoutes.add("AAADADDAD");
		TestRoutes.add("AIDADIA");
		TestRoutes.add("AAAIIDAAD");
		
		dron.setRoutes(TestRoutes);
		
		assertEquals("Equals Objects", TestRoutes, dron.getRoutes());	
	}
	
	@Test
	public void testGetRoutes() {
		List<String> TestRoutes = new ArrayList<String>();
		TestRoutes.add("AAADA");
		TestRoutes.add("AIDAAIA");
		TestRoutes.add("AAAAADA");
		
		dron.setRoutes(TestRoutes);
		assertEquals("Equals Objects", TestRoutes, dron.getRoutes());
	}
	
	@Test
	public void testStartDelivery() {
		assertEquals("Equals Objects", true, dron.startDelivery());
		
	}
	
	@Test
	public void testSummaryDeliveryRoutes() {
		dron.startDelivery();
		String outputTest = "== Reporte de entregas ==\n" + 
				"\n" + 
				"(-2, 4) dirección Occidente\n" + 
				"(-1, 3) dirección Sur\n" + 
				"(0, 0) dirección Occidente\n";
		assertEquals("Equals Objects", outputTest, dron.SummaryDeliveryRoutes());
	}

}
