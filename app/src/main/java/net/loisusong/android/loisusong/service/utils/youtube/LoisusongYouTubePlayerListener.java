package net.loisusong.android.loisusong.service.utils.youtube;

import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;

public class LoisusongYouTubePlayerListener extends AbstractYouTubePlayerListener {
	private float currentSecond = 0;

	public float getCurrentSecond() {
		return currentSecond;
	}

	@Override
	public void onCurrentSecond(float second) {
		currentSecond = second;
		super.onCurrentSecond(second);
	}
}
