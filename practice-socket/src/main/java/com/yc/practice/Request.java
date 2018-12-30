package com.yc.practice;

public class Request{

	private long requestTimeMs;
	private String requestBody;
	private String clientId;
	
	public String toJson() {
		String json = JsonUtil.toString(this);
		return json;
	}
	
	public static Request parseFrom(String jsonStr) {
		Request request = JsonUtil.parse(jsonStr, Request.class);
		return request;
	}

	public long getRequestTimeMs() {
		return requestTimeMs;
	}

	public void setRequestTimeMs(long requestTimeMs) {
		this.requestTimeMs = requestTimeMs;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
