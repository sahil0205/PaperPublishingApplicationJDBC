package com.cg.ppa.configuration;

import java.io.FileReader;

public class AccessFile {
	
	public FileReader readFile() throws Exception {
		FileReader dbFile = new FileReader(
				"C:\\Users\\SAPALASK\\git\\PaperPublishingUsingJDBC\\PaperPublishingJDBC\\src\\com\\cg\\ppa\\configuration\\db.properties");
	return dbFile;
	}
}
