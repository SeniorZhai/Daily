package com.zoe.daily.task;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.zoe.daily.model.Latest;
import com.zoe.daily.model.News;
import com.zoe.daily.util.NetUtil;

public class LatestTask extends AsyncTask<String, Integer, Void> {
	private static final String TAG = "LatestTask";
	private NetUtil netUtil;
	private Latest latest;
	private Handler handler;
	public LatestTask(Latest latest,Handler handler) {
		super();
		this.latest = latest;
		this.handler = handler;
		netUtil = new NetUtil();
	}

	@Override
	protected Void doInBackground(String... params) {
		String str = netUtil.getString(params[0]);
		try {
			JSONTokener jsonTokener = new JSONTokener(str);
			JSONObject data = (JSONObject) jsonTokener.nextValue();
			JSONArray array = data.getJSONArray("news");
			for (int i = 0; i < array.length(); i++) {
				
				JSONObject obj = (JSONObject) array.get(i);
				
				News news = new News();
				
				news.setTitle(obj.getString("title"));

				news.setImage(obj.getString("image"));

				news.setShare_url(obj.getString("share_url"));

				news.setThumbnail(obj.getString("thumbnail"));

				news.setUrl(obj.getString("url"));

				latest.getNews().add(news);
				Message message = Message.obtain();
				message.what = 0x1234;
				handler.sendMessage(message);
			}
		} catch (Exception e) {

		}
		return null;
	}

}
