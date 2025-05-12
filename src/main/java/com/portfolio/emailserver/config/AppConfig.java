package com.portfolio.emailserver.config;

import lombok.Getter;

@Getter
public class AppConfig {
	private final String smtpEmail;
	private final String smtpPassword;
	private final String smtpPort;

	private AppConfig() {
		smtpEmail = System.getenv("SMTP_EMAIL");
		smtpPassword = System.getenv("SMTP_PASSWORD");
		smtpPort = System.getenv("SMTP_PORT") != null ? System.getenv("SMTP_PORT") : "587";

		if (smtpEmail == null || smtpPassword == null) {
			throw new RuntimeException("Required environment variables are missing.");
		}
	}

	public static AppConfig getInstance() {
		return InstanceHolder.instance;
	}

	private static final class InstanceHolder {
		private static final AppConfig instance = new AppConfig();
	}
}
