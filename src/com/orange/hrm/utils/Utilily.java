package com.orange.hrm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilily {

	public String getBaseProperty(String propertyName) {
		
		FileInputStream input = null;
		Properties prop = null;
		
		try {
			input = new FileInputStream(new File(Constants.userDirectory + "\\resources\\application.properties"));
			prop = new Properties();
			prop.load(input);
		} catch (IOException e) {
			e.getStackTrace();
		}
		
		return prop.getProperty(propertyName);
	}
}
