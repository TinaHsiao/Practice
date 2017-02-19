package Util;

import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
	
	public static void main(String args[]){
		load();
	}
	
	public static Properties load(){
		Properties properties = new Properties();
		try {
		    properties.load(LoadProperties.class.getResourceAsStream("properties/config.properties"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return properties;
	}
}
