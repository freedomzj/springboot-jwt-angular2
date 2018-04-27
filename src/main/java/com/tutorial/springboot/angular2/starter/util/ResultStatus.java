package com.tutorial.springboot.angular2.starter.util;

public enum ResultStatus {

	SUCCESS(0, "成功"), 
	HTTP_PARAM_PART(-5002,"token失效或者过期"),
	HTTP_PARAM_TOKEN_ERROR(-5003,"缺少token"),
	HTTP_400_ERROR(-4000,"http请求错误"),
	HTTP_401_ERROR(-4001,"token授权失败"),
	HTTP_403_ERROR(-4003,"用户权限不足"),
	USER_001_ERROR(-400,"用户名或者密码错误"),
	USER_002_ERROR(-400,"密码错误");
	/**
	 * 返回码
	 */
	private int code;

	/**
	 * 返回结果描述
	 */
	private String message;

	ResultStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
