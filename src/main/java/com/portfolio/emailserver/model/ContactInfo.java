package com.portfolio.emailserver.model;

import lombok.Data;

@Data
public class ContactInfo {
	private String name;
	private String email;
	private String mobile;
	private String message;
}
