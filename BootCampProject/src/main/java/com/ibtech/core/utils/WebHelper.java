package com.ibtech.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebHelper {

	public static URLConnection connect(String adress) throws IOException {
		URL url = new URL(adress);

		URLConnection connection = url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);

		return connection;
	}
	
	public static InputStream get(String adress) throws MalformedURLException, IOException {
		InputStream in = new URL(adress).openStream();
		return in;
	}

}
