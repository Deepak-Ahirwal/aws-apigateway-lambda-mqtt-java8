
package com.amazon.client.cts.dto;

public class RequestEntity {

	private String name;

	public String getName () {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	@ Override
	public String toString () {
		return "RequestEntity [name=" + name + "]";
	}

}
