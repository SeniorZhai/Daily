package com.zoe.daily;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.extras.listfragment.PullToRefreshListFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zoe.daily.constant.Constant;
import com.zoe.daily.model.Latest;
import com.zoe.daily.model.News;
import com.zoe.daily.util.CommonUtil;
import com.zoe.daily.util.NetUtil;
import com.zoe.daily.util.cache.ImageLoader;

public class MainActivity extends FragmentActivity implements
		OnRefreshListener<ListView>, OnItemClickListener {
	private static final String TAG = "MainActivity";
	private Latest latest;
	private MyAdapter mAdapter;
	private PullToRefreshListFragment mPullRefreshListFragment;
	private PullToRefreshListView mPullRefreshListView;

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		latest = new Latest();

		mPullRefreshListFragment = (PullToRefreshListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.frag_ptr_list);
		mPullRefreshListFragment.setListShown(true);

		mPullRefreshListView = mPullRefreshListFragment
				.getPullToRefreshListView();
		mPullRefreshListView.setMode(Mode.PULL_FROM_END);
		mPullRefreshListView.setOnRefreshListener(MainActivity.this);
		mPullRefreshListView.setOnItemClickListener(MainActivity.this);
		mAdapter = new MyAdapter();
		mPullRefreshListView.setAdapter(mAdapter);

	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		new GetDataTask().execute(Constant.latest);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View v, int position,
			long id) {
		Intent intent = new Intent(MainActivity.this, OtherActivity.class);
		intent.putExtra("url", latest.getNews().get(position - 1)
				.getShare_url());

		startActivity(intent);
	}

	private class MyAdapter extends BaseAdapter {
		ImageLoader imageLoader = new ImageLoader(MainActivity.this);

		@Override
		public int getCount() {
			return latest.getNews().size();
		}

		@Override
		public Object getItem(int position) {
			return latest.getNews().get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater layoutInflater = LayoutInflater
					.from(MainActivity.this);
			;
			if (convertView == null) {
				convertView = layoutInflater.inflate(R.layout.item_news, null);
			}
			TextView textView = (TextView) convertView
					.findViewById(R.id.textView1);
			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.imageView1);
			String url = latest.getNews().get(position).getImage();
			imageView.setImageResource(R.drawable.ic_launcher);
			imageLoader.DisplayImage(url, imageView, false);
			Log.d("Main", url);
			textView.setText(latest.getNews().get(position).getTitle());
			return convertView;
		}

	}

	private class GetDataTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			NetUtil netUtil = new NetUtil();
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

				}

			} catch (Exception e) {
				Log.d(TAG, e.getMessage());
				return null;
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mAdapter.notifyDataSetChanged();
			mPullRefreshListView.onRefreshComplete();
		}
	}

}
