package com.portfolio.emailserver;

import com.google.gson.Gson;
import com.portfolio.emailserver.config.CorsConfig;
import com.portfolio.emailserver.model.ContactInfo;
import com.portfolio.emailserver.service.ContactMe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static spark.Spark.get;
import static spark.Spark.post;

public class EmailServer {
	private static final Logger logger = LogManager.getLogger(EmailServer.class);

	public static void main(String[] args) {
		CorsConfig.enableCORS("https://www.nskrishnakodali.com", "https://nskrishnakodali.com", "http://localhost:5173");

		get("/health", (request, response) -> "Server up and running.");

		post("/contact-me", (request, response) -> {
			ContactInfo contactInfo = new Gson().fromJson(request.body(), ContactInfo.class);
			if (!ContactMe.validateInfo(contactInfo)) {
				response.status(400);
				return "Message: Invalid details";
			}
			ContactMe.sendEmail(contactInfo);

			String clientIp = request.ip();
			logger.info("Called /contact-me endpoint." + clientIp);

			response.status(200);
			return "";
		});
	}
}
