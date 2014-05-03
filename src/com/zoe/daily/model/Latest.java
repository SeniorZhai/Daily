package com.zoe.daily.model;

import java.util.ArrayList;

public class Latest {
	private String date;
	private ArrayList<News> news;
	private boolean is_today;
	private ArrayList<Top_Story> top_stories;
	
	public Latest() {
		news = new ArrayList<News>();
		top_stories = new ArrayList<Top_Story>();
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<News> getNews() {
		return news;
	}

	public void setNews(ArrayList<News> news) {
		this.news = news;
	}

	public boolean isIs_today() {
		return is_today;
	}

	public void setIs_today(boolean is_today) {
		this.is_today = is_today;
	}

	public ArrayList<Top_Story> getTop_stories() {
		return top_stories;
	}

	public void setTop_stories(ArrayList<Top_Story> top_stories) {
		this.top_stories = top_stories;
	}

}
