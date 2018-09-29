package net.loisusong.android.loisusong.service.wrapper;

import android.content.Intent;

import net.loisusong.android.loisusong.service.constant.Constant;

public class PostIntentWrapper {
	private static final String TITLE = "title";
	private static final String DATE = "date";
	private static final String CONTENT = "content";
	private static final String IMG = "img";

	private Intent intent;
	public PostIntentWrapper(Intent intent) {
		this.intent = intent;
	}

	public void addTitle(String title) {
		intent.putExtra(TITLE, title);
	}

	public void addDate(String date) {
		intent.putExtra(DATE, date);
	}

	public void addContent(String content) {
		intent.putExtra(CONTENT, content);
	}

	public void addImg(String img) {
		intent.putExtra(IMG, img);
	}

	public String getTitle() {
		return intent.getExtras().getString(TITLE, "");
	}

	public String getDate() {
		return intent.getExtras().getString(DATE, "");
	}

	public String getContent() {
		return intent.getExtras().getString(CONTENT, "");
	}

	public String getImg() {
		return intent.getExtras().getString(IMG, "");
	}

	public Intent getIntent() {
		return intent;
	}
}
