package com.portfolio.emailserver.config;

import spark.Request;
import spark.Response;

import static spark.Spark.before;
import static spark.Spark.options;

public class CorsConfig {
	public static void enableCORS(String... allowedOrigins) {
		before((req, res) -> {
			if (res.raw().getHeader("Access-Control-Allow-Origin") == null) {
				setOriginPolicies(req, res, allowedOrigins);
			}
		});

		options("/*", (req, res) -> {
			if (res.raw().getHeader("Access-Control-Allow-Origin") == null) {
				setOriginPolicies(req, res, allowedOrigins);
			}
			return "OK";
		});
	}

	private static void setOriginPolicies(Request req, Response res, String[] allowedOrigins) {
		String origin = req.headers("Origin");
		if (origin != null && isAllowedOrigin(origin, allowedOrigins)) {
			// Set the CORS headers only once
			res.header("Access-Control-Allow-Origin", origin);
			res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			res.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
			res.header("Access-Control-Allow-Credentials", "true");
		}
	}

	// Check if the origin is in the allowed origins list
	private static boolean isAllowedOrigin(String origin, String[] allowedOrigins) {
		for (String allowedOrigin : allowedOrigins) {
			if (origin.equals(allowedOrigin)) {
				return true;
			}
		}
		return false;
	}
}
