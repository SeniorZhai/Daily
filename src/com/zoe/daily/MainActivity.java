package com.zoe.daily;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.zoe.daily.constant.Constant;
import com.zoe.daily.model.Latest;
import com.zoe.daily.task.LatestTask;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private Latest latest;

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Handler handler = new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {
				Log.d(TAG, "成功！！！");
				if (msg.what == 0x1234) {
					for (int i = 0; i < latest.getNews().size(); i++) {
						Log.d(TAG, latest.getNews().get(i).getImage());
					}
				}
			};
		};
		LatestTask task = new LatestTask(latest, handler);
		task.execute(Constant.latest);

	}
}
