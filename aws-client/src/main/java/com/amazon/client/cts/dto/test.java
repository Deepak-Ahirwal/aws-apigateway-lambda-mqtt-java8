package com.amazon.client.cts.dto;

import java.nio.charset.Charset;
import java.util.Arrays;

public class test {

	private static final Charset UTF_8 = Charset.forName("UTF-8");

	public static void main(String[] args) {
		
		String text = "Deepak";
		byte[] bytes = text.getBytes(UTF_8);
		System.out.println("bytes= "+Arrays.toString(bytes));
		System.out.println("text again= "+new String(bytes, UTF_8));
		/*String s = "Deepak";
		  byte[] bytes = s.getBytes();
		  StringBuilder binary = new StringBuilder();
		  for (byte b : bytes)
		  {
		     int val = b;
		     for (int i = 0; i < 8; i++)
		     {
		        binary.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     binary.append(' ');
		  }
		  System.out.println("'" + s + "' to binary: " + binary);*/
	}
}
