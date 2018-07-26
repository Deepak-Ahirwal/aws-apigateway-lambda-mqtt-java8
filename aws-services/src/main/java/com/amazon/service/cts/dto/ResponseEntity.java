package com.amazon.service.cts.dto;


public class ResponseEntity {

	private String message;
	private String transactionId;
	
	public String getMessage () {
		return message;
	}
	
	public void setMessage ( String message ) {
		this.message = message;
	}
	
	public String getTransactionId () {
		return transactionId;
	}
	
	public void setTransactionId ( String transactionId ) {
		this.transactionId = transactionId;
	}

	@ Override
	public String toString () {
		return "ResponseEntity [message=" + message + ", transactionId=" + transactionId + "]";
	}
	
	
	
}
