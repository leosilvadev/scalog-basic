package br.com.vaiftech.properties;

import java.io.IOException;
import java.util.Properties;

public class PersistenceProperties {
	
	private static Properties properties;
	
	static {
		properties = new Properties();
		try {
			properties.load(PersistenceProperties.class.getClassLoader().getResourceAsStream("db.properties"));
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static String getDriverClassName(){
		return properties.getProperty("db_driverclassname");
	}
	
	public static String getURL(){
		return properties.getProperty("db_url");
	}

	public static String getUsername(){
		return properties.getProperty("db_username");
	}

	public static String getPassword(){
		return properties.getProperty("db_password");
	}
	
	public static String getDDLMode(){
		return properties.getProperty("db_ddl_mode");
	}
	
	public static String getDialect(){
		return properties.getProperty("db_dialect");
	}

	public static String getShowSQL(){
		return properties.getProperty("db_show_sql");
	}

	public static int getC3P0MinSize(){
		return Integer.parseInt(properties.getProperty("db_c3p0_min_size"));
	}
	
	public static int getC3P0Timeout(){
		return Integer.parseInt(properties.getProperty("db_c3p0_timeout"));
	}
	
	public static int getC3P0MaxStatements(){
		return Integer.parseInt(properties.getProperty("db_c3p0_max_statements"));
	}
	
	public static int getC3P0IdleTestPeriod(){
		return Integer.parseInt(properties.getProperty("db_c3p0_idle_test_period"));
	}
	

}
