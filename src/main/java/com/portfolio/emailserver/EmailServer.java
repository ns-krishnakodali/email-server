package com.portfolio.emailserver;

import com.google.gson.Gson;
import com.portfolio.emailserver.model.ContactInfo;
import com.portfolio.emailserver.service.ContactMe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;
import static spark.Spark.post;

public class EmailServer {
	private static final Logger logger = LoggerFactory.getLogger(EmailServer.class);

	public static void main(String[] args) {
		get("/health", (request, response) -> "Server up and running.");

		post("/contact-me", (request, response) -> {
			ContactInfo contactInfo = new Gson().fromJson(request.body(), ContactInfo.class);

			ContactMe.sendEmail(contactInfo);

			String clientIp = request.ip();
			logger.info("Called /contact-me endpoint." + clientIp);

			response.status(200);
			return "";
		});
	}
}
