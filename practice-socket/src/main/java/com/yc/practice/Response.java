package com.yc.practice;

public class Response<T> {

	private long requestTimeMS;
	private long serverDealBeginTimeMS;
	private long serverDealUsingTimeMS;
	private long serverDealEndTimeMS;
	private String clientId;
	private T result;
	
	public String toJson() {
		String json = JsonUtil.toString(this);
		return json;
	}
	
	public static <T> Response<T> parseFrom(String jsonStr) {
		Response<T> response = JsonUtil.parse(jsonStr, Response.class);
		return response;
	}

	public long getRequestTimeMS() {
		return requestTimeMS;
	}

	public void setRequestTimeMS(long requestTimeMS) {
		this.requestTimeMS = requestTimeMS;
	}

	public long getServerDealBeginTimeMS() {
		return serverDealBeginTimeMS;
	}

	public void setServerDealBeginTimeMS(long serverDealBeginTimeMS) {
		this.serverDealBeginTimeMS = serverDealBeginTimeMS;
	}

	public long getServerDealUsingTimeMS() {
		return serverDealUsingTimeMS;
	}

	public void setServerDealUsingTimeMS(long serverDealUsingTimeMS) {
		this.serverDealUsingTimeMS = serverDealUsingTimeMS;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public long getServerDealEndTimeMS() {
		return serverDealEndTimeMS;
	}

	public void setServerDealEndTimeMS(long serverDealEndTimeMS) {
		this.serverDealEndTimeMS = serverDealEndTimeMS;
	}

}
