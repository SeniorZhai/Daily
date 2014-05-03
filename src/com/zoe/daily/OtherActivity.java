package com.zoe.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OtherActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
		WebView webView = (WebView)findViewById(R.id.myWebView);
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		webView.setWebViewClient(new SampleWebViewClient());
		webView.loadUrl(url);
	};
	private static class SampleWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}
}
