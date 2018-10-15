package net.loisusong.android.loisusong.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import net.loisusong.android.loisusong.R;
import net.loisusong.android.loisusong.service.utils.LogUtils;
import net.loisusong.android.loisusong.service.wrapper.PostIntentWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostVideoActivity extends AppCompatActivity {

	@BindView(R.id.pv_video)
	PlayerView playerView;

	private SimpleExoPlayer player;
	private String title, videoUrl;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_video);
		ButterKnife.bind(this);

		initData();
		setUpPlayer();
	}

	private void initData() {
		PostIntentWrapper intentWrapper = new PostIntentWrapper(getIntent());
		title = intentWrapper.getTitle();
		videoUrl = intentWrapper.getUrl();
	}

	private void setUpPlayer() {
		initPlayer();
		loadVideo();
	}

	private void initPlayer() {
		player = ExoPlayerFactory.newSimpleInstance(
				new DefaultRenderersFactory(this),
				new DefaultTrackSelector(),
				new DefaultLoadControl()
		);

		playerView.setPlayer(player);
		player.setPlayWhenReady(false);
	}

	private void loadVideo() {
		Uri uri = Uri.parse(videoUrl);
		MediaSource mediaSource = buildMediaSource(uri);
		player.prepare(mediaSource, true, false);
	}

	private MediaSource buildMediaSource(Uri uri) {
		return new ExtractorMediaSource.Factory(
				new DefaultHttpDataSourceFactory("exoplayer-codelab")).
				createMediaSource(uri);
	}
}
