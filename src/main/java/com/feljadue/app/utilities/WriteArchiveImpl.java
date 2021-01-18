package com.feljadue.app.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteArchiveImpl implements IWriteArchive{
	
	private static final String DIRECTORY_PATH = "outputRoutesSummary";


	public boolean writeDeliveryRoutes(String fileName, String deliveryText) throws IOException {
		
		File file = new File(DIRECTORY_PATH+"\\"+fileName);
		FileWriter fileWrite;
		if(file.exists()) {
			fileWrite = new FileWriter(file.getAbsoluteFile(),true);
			fileWrite.write(deliveryText);
			fileWrite.close();
		}else {
			if(file.createNewFile()) {
				fileWrite = new FileWriter(file.getAbsoluteFile(),true);
				fileWrite.write(deliveryText);
				fileWrite.close();
			}
		}
		return false;
	}

}
