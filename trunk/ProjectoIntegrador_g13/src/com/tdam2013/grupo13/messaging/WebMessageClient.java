package com.tdam2013.grupo13.messaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class WebMessageClient {

	protected Context context;
	protected SharedPreferences sharedPreferences;
	protected Logger logger;
	

	public WebMessageClient(Context context) {
		super();
		this.context = context;
		this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		this.logger = Logger.getLogger(getClass().getName());
	}
	
	public String[] execute(String... params){
		String request = getRequest(params);
		logger.log(Level.INFO, "Request: " + request);

		String result = doPost(request);
		logger.log(Level.INFO, "Result: " + result);
		
		String[] response = parseResult(result);
		logger.log(Level.INFO, "Result: " + response.toString());
		
		return response;
	}
	
	public abstract String getRequest(String... params);
	
	public abstract String[] parseResult(String params);
	
	
	private String doPost(String request) {
		String urlStr = sharedPreferences.getString("service_url_pref",
				"http://localhost:8080/MessageSender/");
		logger.log(Level.INFO, "URL: " + urlStr);
	
		try{
			URL url = new URL(urlStr);
			URLConnection c = url.openConnection();
	
			c.setDoOutput(true);
			if (c instanceof HttpURLConnection) {
				((HttpURLConnection) c).setRequestMethod("POST");
			}
	
			OutputStreamWriter out = new OutputStreamWriter(c.getOutputStream());
	
			out.write(request);
	
			out.close();
	
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
	
			StringBuilder sb = new StringBuilder();
			String s = null;
			while ((s = in.readLine()) != null) {
				sb.append(s);
				System.out.println(s);
			}
			in.close();
			
			return sb.toString();
		}
		catch (MalformedURLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
			return null;
		}
	}
}