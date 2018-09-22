package com.example.ztrong.loisusong.fragment.PostFragments;

import com.example.ztrong.loisusong.service.constant.Constant;

public class PostMediaFragment extends PostFragment {
	private static boolean isFirstLaunch = true;
	@Override
	void initTypePost() {
		setTypePost(Constant.POST_MEDIA);
	}

	@Override
	boolean isFirstLaunch() {
		if (isFirstLaunch) {
			isFirstLaunch = false;
			return true;
		}
		return false;
	}
}
