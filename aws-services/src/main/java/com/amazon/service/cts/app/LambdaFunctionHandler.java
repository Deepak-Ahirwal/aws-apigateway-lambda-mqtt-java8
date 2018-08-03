
package com.amazon.service.cts.app;

import java.util.UUID;

import com.amazon.service.cts.dto.RequestEntity;
import com.amazon.service.cts.dto.ResponseEntity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler < RequestEntity , ResponseEntity > {

	private static RequestEntity input;
	
	@ Override
	public ResponseEntity handleRequest ( RequestEntity input,Context context ) {
		context.getLogger ( ).log ( "Input: " + input );
		ResponseEntity response = new ResponseEntity ( );
		try {
			response.setMessage ( "Message published for serial number " + input.getSerialNumber ( ) + "," + input.getClickType ( ) + " action performed" );
			response.setTransactionId ( UUID.randomUUID ( ).toString ( ) );
		} catch ( Exception e ) {
			e.printStackTrace ( );
			response.setMessage ( e.getMessage ( ) );
		}
		context.getLogger ( ).log ( "Response : " + response );
		
		return response;

	}

}
