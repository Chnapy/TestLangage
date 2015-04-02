/*
 * 
 * 
 * 
 */
package modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Network.java
 *
 */
public class Network {

    public static String get(String url) throws IOException {

	String source = "";
	URL oracle = new URL(url);
	URLConnection yc = oracle.openConnection();
	try (BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()))) {
	    String inputLine;

	    while ((inputLine = in.readLine()) != null) {
		source += inputLine + "\n";
	    }
	}
	return source;
    }

}
