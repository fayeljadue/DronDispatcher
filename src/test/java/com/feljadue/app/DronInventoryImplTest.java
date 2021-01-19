package com.feljadue.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.feljadue.app.inventory.DronInventoryImpl;
import com.feljadue.app.inventory.IInventory;
import com.feljadue.app.vehicle.DronVehicle;
import com.feljadue.app.vehicle.IVehicle;


public class DronInventoryImplTest {
	
	
	static IInventory dronInventory;
	
	@BeforeClass
	public static void init() {
		dronInventory = new DronInventoryImpl(5);
	}

	@Test
	public void testListVehicules() {
		List<Integer> testDrones = new ArrayList<>();
		for(int i= 1;i<=5;i++) {
			testDrones.add(i);
		}		
		List<Integer> dronesId = new ArrayList<>();
		
		for(IVehicle dronN:dronInventory.listVehicules()) {
			dronesId.add(dronN.getVehiculeId());
		}
		
		assertEquals("Equals Objects", testDrones,dronesId);
	}

	@Test
	public void testAddVehicule() {
		IVehicle dron = new DronVehicle(6);
		dronInventory.removeVehicule(5);
		dronInventory.addVehicule(dron);
		
		List<Integer> testDrones = new ArrayList<>();
		for(int i= 1;i<=3;i++) {
			testDrones.add(i);
		}
		testDrones.add(6);
		List<Integer> dronesId = new ArrayList<>();
		
		for(IVehicle dronN:dronInventory.listVehicules()) {
			dronesId.add(dronN.getVehiculeId());
		}
		assertEquals("Equals Objects", testDrones,dronesId);
		
	}
	
	@Test
	public void testAddVehicule1() {
		IVehicle dron = new DronVehicle(5);
		assertEquals("Equals Objects", false,dronInventory.addVehicule(dron));
		
	}
	
	@Test
	public void testRemoveVehicule() {
		dronInventory.removeVehicule(4);
		
		List<Integer> testDrones = new ArrayList<>();
		for(int i= 1;i<=3;i++) {
			testDrones.add(i);
		}
		testDrones.add(5);
		List<Integer> dronesId = new ArrayList<>();
		
		for(IVehicle dronN:dronInventory.listVehicules()) {
			dronesId.add(dronN.getVehiculeId());
		}
		assertEquals("Equals Objects", testDrones,dronesId);
	}

}
