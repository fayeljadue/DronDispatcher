package com.feljadue.app;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.feljadue.app.utilities.IWriteArchive;
import com.feljadue.app.utilities.WriteArchiveImpl;

public class WriteAchiveImplTest {
	
	
	static IWriteArchive writer;
	
	@BeforeClass
	public static void init() {
		writer = new WriteArchiveImpl();
	}

	@Test
	public void testWriteDeliveryRoutes() throws IOException {
		String outText = writer.writeDeliveryRoutes("prueba", "== Reporte de entregas ==\r\n" + 
				"(-2, 4) dirección Norte\r\n" + 
				"(-3, 3) dirección Sur\r\n" + 
				"(-4, 2) dirección Oriente");
		
		String Test = "== Reporte de entregas ==\r\n" + 
				"(-2, 4) dirección Norte\r\n" + 
				"(-3, 3) dirección Sur\r\n" + 
				"(-4, 2) dirección Oriente";
		
		assertEquals("Equals Objects", Test, outText);
	}
	
	@Test
	public void testWriteDeliveryRoutes1() throws IOException {
		String path = "outputRoutesSummary\\prueba";
		File file = new File(path);
		if(file.exists()) {
			file.delete();
		}
		
		String outText = writer.writeDeliveryRoutes("prueba2", "== Reporte de entregas ==\r\n" + 
				"(-2, 4) dirección Norte\r\n" + 
				"(-3, 3) dirección Sur\r\n" + 
				"(-4, 2) dirección Oriente");
		
		String Test = "== Reporte de entregas ==\r\n" + 
				"(-2, 4) dirección Norte\r\n" + 
				"(-3, 3) dirección Sur\r\n" + 
				"(-4, 2) dirección Oriente";
		
		assertEquals("Equals Objects", Test, outText);
	}

}
