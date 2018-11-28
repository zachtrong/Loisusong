package net.loisusong.loisusong.service.utils.screen;

import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FullScreenManager {
	private AppCompatActivity activity;
	private View[] views;

	public FullScreenManager(AppCompatActivity activity, View... views) {
		this.activity = activity;
		this.views = views;
	}

	public void enterFullScreen() {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		View decorView = activity.getWindow().getDecorView();
		hideSystemUI(decorView);

		for (View view : views) {
			view.setVisibility(View.GONE);
			view.invalidate();
		}

		ActionBar actionBar = activity.getSupportActionBar();
		if (actionBar != null) {
			actionBar.hide();
		}
	}

	public void exitFullScreen() {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		View decorView = activity.getWindow().getDecorView();
		showSystemUI(decorView);

		for (View view : views) {
			view.setVisibility(View.VISIBLE);
			view.invalidate();
		}

		ActionBar actionBar = activity.getSupportActionBar();
		if (actionBar != null) {
			actionBar.show();
		}
	}

	private void hideSystemUI(View decorView) {
		decorView.setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
		);
	}

	private void showSystemUI(View decorView) {
		decorView.setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		);
	}
}
