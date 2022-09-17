package com.ibtech.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebHelper {

	public static URLConnection connect(String adress) throws IOException {
		URL url = new URL(adress);

		URLConnection connection = url.openConnection();
		
		return connection;
	}
	
	public static InputStream get(String adress) throws MalformedURLException, IOException {
		URL url = new URL(adress);
		HttpURLConnection connection =
		    (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/xml");

		InputStream in = connection.getInputStream();
		return in;
	}

}
