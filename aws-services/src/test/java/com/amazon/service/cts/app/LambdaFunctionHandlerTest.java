
package com.amazon.service.cts.app;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazon.service.cts.dto.RequestEntity;
import com.amazon.service.cts.dto.ResponseEntity;
import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

	private static RequestEntity input;

	@ BeforeClass
	public static void createInput () throws IOException {
		// TODO: set up your sample input object here.
		RequestEntity entity = new RequestEntity ( );
		entity.setSerialNumber ( "1" );
		entity.setClickType ( "button" );
		input = entity;
	}

	private Context createContext () {
		TestContext ctx = new TestContext ( );

		// TODO: customize your context here if needed.
		ctx.setFunctionName ( "Your Function Name" );

		return ctx;
	}

	@ Test
	public void testLambdaFunctionHandler () {
		LambdaFunctionHandler handler = new LambdaFunctionHandler ( );
		Context ctx = createContext ( );

		ResponseEntity output = handler.handleRequest (  input , ctx );
		if ( output != null ) {
			System.out.println ( output.toString ( ) );
		}
		Assert.assertEquals ( output , output );
	}
}
