package com.tutorial.springboot.angular2.starter.util;


/**
 * 自定义返回结果
 * 
 * 
 */
public class ResultModel {

	/**
	 * 返回码
	 */
	private int code;

	/**
	 * 返回结果描述
	 */
	private String msg;

	/**
	 * 返回内容
	 */
	private Object data;

	public int getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}


	public ResultModel(int code, String msg) {
		this.code = code;
		this.msg = msg;
		this.data = "";
	}

	public ResultModel(int code, String message, Object content) {
		this.code = code;
		this.msg = message;
		this.data = content;
	}

	public ResultModel(ResultStatus status) {
		this.code = status.getCode();
		this.msg = status.getMessage();
		this.data = "";
	}

	public ResultModel(ResultStatus status, Object content) {
		this.code = status.getCode();
		this.msg = status.getMessage();
		this.data = content;
	}

	public static ResultModel ok(Object content) {
		return new ResultModel(ResultStatus.SUCCESS, content);
	}

	public static ResultModel ok() {
		return new ResultModel(ResultStatus.SUCCESS);
	}

	public static ResultModel error(ResultStatus error) {
		return new ResultModel(error);
	}
}