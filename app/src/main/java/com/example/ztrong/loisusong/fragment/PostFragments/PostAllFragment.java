package com.example.ztrong.loisusong.fragment.PostFragments;

import android.content.Context;

import com.example.ztrong.loisusong.service.constant.Constant;

public class PostAllFragment extends PostFragment {
	private static boolean isFirstLaunch = true;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}

	@Override
	void initTypePost() {
		setTypePost(Constant.POST_ALL);
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
