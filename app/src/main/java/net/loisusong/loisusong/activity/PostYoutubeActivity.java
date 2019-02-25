package net.loisusong.loisusong.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.youtubeplayer.ui.PlayerUIController;

import net.loisusong.loisusong.R;
import net.loisusong.loisusong.service.utils.screen.FullScreenManager;
import net.loisusong.loisusong.service.utils.youtube.LoisusongYouTubePlayerListener;
import net.loisusong.loisusong.service.wrapper.PostIntentWrapper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostYoutubeActivity extends AppCompatActivity {
	@BindView(R.id.tl_post_youtube)
	Toolbar toolbar;
	@BindView(R.id.tv_post_youtube)
	TextView contentView;
	@BindView(R.id.youtube_view)
	YouTubePlayerView youTubePlayerView;
	@BindView(R.id.bt_list_video)
	MaterialButton listVideoButton;

	private String content;
	private String title;
	private ArrayList<String> videos;
	private YouTubePlayer youTubePlayer;
	private FullScreenManager fullScreenManager;

	LoisusongYouTubePlayerListener playerListener = new LoisusongYouTubePlayerListener() {
		@Override
		public void onReady() {
			super.onReady();
			String url = "";
			if (videos != null) {
				url = videos.get(0);
			}
			youTubePlayer.cueVideo(url, 0);
		}
	};

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_youtube);

		setUpLayout();
		initData();
		setUpData();
		setUpYouTube();
	}

	private void setUpLayout() {
		ButterKnife.bind(this);
		fullScreenManager = new FullScreenManager(this);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void initData() {
		Intent i = getIntent();
		PostIntentWrapper intentWrapper = new PostIntentWrapper(i);
		content = intentWrapper.getContent();
		title = intentWrapper.getTitle();
	}

	private void setUpData() {
		getSupportActionBar().setTitle(title);
		videos = getYoutubeUrlLists(content);
		if (videos.size() > 1) {
			listVideoButton.setVisibility(View.VISIBLE);
		}
		content = removeAbundance(content);
		contentView.setText(Html.fromHtml(content));
	}

	private ArrayList<String> getYoutubeUrlLists(String content) {
		Pattern pattern = Pattern.compile("https://www\\.youtube\\.com/embed/([^\"]+)");
		Matcher matcher = pattern.matcher(content);
		ArrayList<String> videos = new ArrayList<>();
		while (matcher.find()) {
			String video = matcher.group(1);
			videos.add(video);
		}
		return videos;
	}

	private String removeAbundance(String content) {
		content = content.replaceAll("<[^>]*>", "");
		content = content.replaceAll("&#8211;", "-");
		content = content.replaceAll("&#8220;", "\"");
		content = content.replaceAll("&#8221;", "\"");
		content = content.replaceAll("Mời quý vị[\\w\\W]*", "");
		content = content.replaceAll("Bài giảng audio[\\w\\W]*", "");
		content = content.replaceAll("Download article[\\w\\W]*", "");
		return content;
	}

	private void setUpYouTube() {
		if (videos.size() == 0) {
			youTubePlayerView.setVisibility(View.GONE);
			return;
		}
		youTubePlayerView.initialize(youTubePlayer -> {
			this.youTubePlayer = youTubePlayer;
			youTubePlayer.addListener(playerListener);

			setUpFullScreen();
		}, true);

		youTubePlayerView.getPlayerUIController().showMenuButton(false);
	}

	private void setUpFullScreen() {
		youTubePlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
			@Override
			public void onYouTubePlayerEnterFullScreen() {
				fullScreenManager.enterFullScreen();
			}

			@Override
			public void onYouTubePlayerExitFullScreen() {
				fullScreenManager.exitFullScreen();
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onDestroy() {
		youTubePlayerView.release();
		super.onDestroy();
	}
}
