
package com.amazon.service.cts.dto;

public class RequestEntity {

	private String serialNumber;
	
	private String clickType;

	
	public String getSerialNumber () {
		return serialNumber;
	}

	
	public void setSerialNumber ( String serialNumber ) {
		this.serialNumber = serialNumber;
	}

	
	public String getClickType () {
		return clickType;
	}

	
	public void setClickType ( String clickType ) {
		this.clickType = clickType;
	}


	@ Override
	public String toString () {
		return "RequestEntity [serialNumber=" + serialNumber + ", clickType=" + clickType + "]";
	}

}
