package com.zoe.daily.constant;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class Constant {
	public static final String latest = "http://news-at.zhihu.com/api/2/news/latest";
	public static final String before = "http://news.at.zhihu.com/api/2/news/before/";
	public static final String hot = "http://news-at.zhihu.com/api/2/news/hot";
	public static final String sections = "http://news-at.zhihu.com/api/2/sections";
	public static final String image(Context context) {
		String url = "http://news-at.zhihu.com/api/2/start-image/";
		int width = getScreenWidth(context);
		if (width>=1080) {
			url+="1080*1776";
		}else if (width>=720) {
			url+="720*1184";
		}else if (width>=480) {
			url+="480*728";
		}else {
			url+="320*432";
		}
		return url;				
	}
	private static int getScreenWidth(Context context) {
		WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return display.getWidth();
	}
}
