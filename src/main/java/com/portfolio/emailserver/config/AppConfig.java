package com.portfolio.emailserver.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
	private static final Properties properties = new Properties();

	static {
		try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
			if (input == null) {
				throw new RuntimeException("Unable to find application.properties");
			}
			properties.load(input);
		} catch (IOException ex) {
			throw new RuntimeException("Failed to load configuration", ex);
		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}
}
