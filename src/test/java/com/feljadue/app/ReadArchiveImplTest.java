package com.feljadue.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.feljadue.app.utilities.IReadArchive;
import com.feljadue.app.utilities.ReadArchiveImpl;

public class ReadArchiveImplTest {

	static IReadArchive reader;
	
	@BeforeClass
	public static void init() {
		reader = new ReadArchiveImpl();
	}
	
	@Test
	public void testReadRoutes() {
		HashMap<Integer, List<String>> routeTest = reader.readRoutes();
		
		HashMap<Integer, List<String>> Test = new HashMap<Integer, List<String>>();
		
		Test.put(1, new ArrayList<String>(Arrays.asList("AAAAIAA","DDDAIAD","AAIADAD")));
		Test.put(2, new ArrayList<String>(Arrays.asList("AADAIADAIA","ADAIAIAAA","IAAADA","AADIADA")));
		Test.put(8, new ArrayList<String>(Arrays.asList("IAIAADADAA","ADAIAADA","AIADAA")));
		
		assertEquals("Equals Objects", Test, routeTest);
	}

}
