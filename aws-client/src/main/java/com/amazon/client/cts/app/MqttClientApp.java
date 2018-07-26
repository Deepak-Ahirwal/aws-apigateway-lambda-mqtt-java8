
package com.amazon.client.cts.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.amazon.client.cts.utils.CommandArguments;
import com.amazon.client.cts.utils.MqttUtil;
import com.amazon.client.cts.utils.MqttUtil.KeyStorePasswordPair;
import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTimeoutException;
import com.amazonaws.services.iot.client.AWSIotTopic;


public class MqttClientApp {

	private static final String TestTopic = "your/topic/name";
	private static final AWSIotQos TestTopicQos = AWSIotQos.QOS0;
	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL = "your get url endpoint";
	private static final String POST_URL ="specify your post url endpoint";

	private static AWSIotMqttClient awsIotClient;

	public static void setClient ( AWSIotMqttClient client ) {
		awsIotClient = client;
	}

	private static void initClient ( CommandArguments arguments ) {
		String clientEndpoint = arguments.getNotNull ( "clientEndpoint" , MqttUtil.getConfig ( "clientEndpoint" ) );
		String clientId = arguments.getNotNull ( "clientId" , MqttUtil.getConfig ( "clientId" ) );

		String certificateFile = arguments.get ( "certificateFile" , MqttUtil.getConfig ( "certificateFile" ) );
		String privateKeyFile = arguments.get ( "privateKeyFile" , MqttUtil.getConfig ( "privateKeyFile" ) );
		if ( awsIotClient == null && certificateFile != null && privateKeyFile != null ) {
			String algorithm = arguments.get ( "keyAlgorithm" , MqttUtil.getConfig ( "keyAlgorithm" ) );

			KeyStorePasswordPair pair = MqttUtil.getKeyStorePasswordPair ( certificateFile , privateKeyFile , algorithm );

			awsIotClient = new AWSIotMqttClient ( clientEndpoint , clientId , pair.keyStore , pair.keyPassword );
		}

		if ( awsIotClient == null ) {
			String awsAccessKeyId = arguments.get ( "awsAccessKeyId" , MqttUtil.getConfig ( "awsAccessKeyId" ) );
			String awsSecretAccessKey = arguments.get ( "awsSecretAccessKey" , MqttUtil.getConfig ( "awsSecretAccessKey" ) );
			String sessionToken = arguments.get ( "sessionToken" , MqttUtil.getConfig ( "sessionToken" ) );

			if ( awsAccessKeyId != null && awsSecretAccessKey != null ) {
				awsIotClient = new AWSIotMqttClient ( clientEndpoint , clientId , awsAccessKeyId , awsSecretAccessKey , sessionToken );
			}
		}

		if ( awsIotClient == null ) {
			throw new IllegalArgumentException ( "Failed to construct client due to missing certificate or credentials." );
		}
	}

	public static void main ( String args[] ) throws InterruptedException, AWSIotException, AWSIotTimeoutException, IOException {
		System.out.println ( "deepak" );
		CommandArguments arguments = CommandArguments.parse ( args );
		initClient ( arguments );

		awsIotClient.connect ( );

		AWSIotTopic topic = new TestTopicListener ( TestTopic , TestTopicQos );
		awsIotClient.subscribe ( topic , true );
		String message;
		JSONObject json = new JSONObject ( );
		json.put ( "name" , "deepak-ahirwal" );
		message = json.toString ( );
		System.out.println ( message );
		try {
			awsIotClient.publish ( TestTopic , message );
			sendGET ( );
			sendPOST ( message );
			System.out.println ( "POST DONE" );
			
		} catch ( AWSIotException e ) {
			System.out.println ( System.currentTimeMillis ( ) + ": publish failed for " + message );
		}finally {
				awsIotClient.disconnect ( );
		}
		

	}

	private static void sendPOST ( String message ) throws IOException {
		System.out.println("messsage------------------->" +message.toString());
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		
		os.write(message.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
	}
	

	private static void sendGET () throws IOException {
		URL obj = new URL ( GET_URL );
		HttpURLConnection con = ( HttpURLConnection ) obj.openConnection ( );
		con.setRequestMethod ( "GET" );
		con.setRequestProperty ( "User-Agent" , USER_AGENT );
		int responseCode = con.getResponseCode ( );
		System.out.println ( "GET Response Code :: " + responseCode );
		if ( responseCode == HttpURLConnection.HTTP_OK ) { // success
			BufferedReader in = new BufferedReader ( new InputStreamReader ( con.getInputStream ( ) ) );
			String inputLine;
			StringBuffer response = new StringBuffer ( );

			while ( ( inputLine = in.readLine ( ) ) != null ) {
				response.append ( inputLine );
			}
			in.close ( );

			// print result
			System.out.println ( response.toString ( ) );
		} else {
			System.out.println ( "GET request not worked" );
		}

	}
}
