package com.zoe.daily.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class NetUtil {
	private static final String TAG = "NetUtil";
	public String getString(String uri){
		try {
			StringBuffer text =  new StringBuffer();
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(3000);
			InputStreamReader isr = new InputStreamReader(conn.getInputStream(),"utf-8");
			BufferedReader br = new BufferedReader(isr);
			String temp;
			while((temp = br.readLine())!= null){
				text.append(temp);
			}
			br.close();
			isr.close();
			return text.toString();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return null;
		}
	}
}
