package com.BeerMe.DrinkMachineServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DMServerMain {
	
	static ServerSocket socket1;
	protected final static int port = 3307;
	static Socket connection;
	static boolean first;
	static StringBuffer process;
	static String TimeStamp;
	
	public static void main ( String [] arguments ){
		
		try{
		    socket1 = new ServerSocket(port);
		    System.out.println("SingleSocketServer Initialized");
		    int character;
		    
		    while (true) {
		        connection = socket1.accept();

		        BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
		        InputStreamReader isr = new InputStreamReader(is);
		        process = new StringBuffer();
		        while((character = isr.read()) != 13) {
		          process.append((char)character);
		        }
		        System.out.println(process);
		        try {
		          Thread.sleep(1000);
		        }
		        catch (Exception e){
		        }
		        TimeStamp = new java.util.Date().toString();
		        String returnCode = "Drink Dispensed at "+ TimeStamp + (char) 13;
		        BufferedOutputStream os = new BufferedOutputStream(connection.getOutputStream());
		        OutputStreamWriter osw = new OutputStreamWriter(os, "US-ASCII");
		        osw.write(returnCode);
		        osw.flush();
		    }
		}
	    catch (IOException e) {
	    }
		
	    try {
	        connection.close();
	    }
	    catch (IOException e) {
	    }
	}

}
