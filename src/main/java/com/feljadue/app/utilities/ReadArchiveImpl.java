package com.feljadue.app.utilities;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReadArchiveImpl implements IReadArchive {

	private final static String DIRECTORY_PATH = "inputRoutes";

	//Read the routes of the list
	public HashMap<Integer, List<String>> readRoutes() {

		File files = new File(DIRECTORY_PATH);
		File content[] = files.listFiles(filter());

		HashMap<Integer, List<String>> dronRoutes = new HashMap<Integer, List<String>>();
		if(files.exists()) {
			if (content.length > 0) {
				for (File cont : content) {
					Integer dronNumber = getDronNumber(cont.getName());
					dronRoutes.put(dronNumber,readAllLines(cont));
				}
				return dronRoutes;
			}
		}
		return null;
	}

	//Filter the name of the files in the input directory,
	private FileFilter filter() {
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) {
				String pathern = "^in[0-9]*[\\.]txt";

				if (file.getName().endsWith(".txt") && file.getName().matches(pathern)) {
					return true;
				}
				return false;
			}
		};
		return fileFilter;
	}

	//Helps to read all the lines in the input file
	private List<String> readAllLines(File file) {
		List<String> reading = new ArrayList<String>();
		
		try {
			reading = Files.readAllLines(Paths.get(file.getAbsolutePath()))
					.stream().map(String::trim).map(String::toUpperCase).
					collect(Collectors.toList());
			
		} catch (IOException e) {
			reading = null;
		}

		return reading;
	}
	
	//Take the name of the file, and takes the number of the drone to use
	private Integer getDronNumber(String fileName) {

		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(fileName);
		Integer dronNumber = null;
		if (matcher.find()) {
			try {
				dronNumber = Integer.parseInt(matcher.group());
			}catch(Exception e) {
				dronNumber = null;
			}
		}
		return dronNumber;
	}
}
