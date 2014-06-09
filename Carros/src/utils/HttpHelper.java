package utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import android.util.Log;

public class HttpHelper {

	private static final String TAG = "Http";
	public static boolean LOG_ON = false;
	
	public static String doGet(String url, String charset) throws IOException{
		if(LOG_ON){
		Log.d(TAG,">> Httpp.doGet: "+url);	
		}
		
		URL link = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) link.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.connect();
		InputStream in = conn.getInputStream();
		String s = IOUtils.toString(in,charset);
		
		if(LOG_ON){
			Log.d(TAG,">> Http.doGET: "+ s);
		}
		
		in.close();
		conn.disconnect();
		return s;
	}	
}
