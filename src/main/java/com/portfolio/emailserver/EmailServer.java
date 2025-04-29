package com.portfolio.emailserver;

import static spark.Spark.get;
import static spark.Spark.post;

public class EmailServer {
	public static void main(String[] args) {
		get("/health", (request, response) -> "Server up and running.");

		post("/contact-me", (request, response) -> "");
	}
}
