package com.tutorial.springboot.angular2.starter.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Audience {

	@Value("${audience.clientId}")
	private String clientId;
	@Value("${audience.base64Secret}")
	private String base64Secret;
	
	@Value("${audience.name}")
	private String name;
	@Value("${audience.expiresSecond}")
	private int expiresSecond;
	
	public Audience(){
	}
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getBase64Secret() {
		return base64Secret;
	}

	public void setBase64Secret(String base64Secret) {
		this.base64Secret = base64Secret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExpiresSecond() {
		return expiresSecond;
	}

	public void setExpiresSecond(int expiresSecond) {
		this.expiresSecond = expiresSecond;
	}


}
