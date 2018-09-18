package com.example.ztrong.loisusong.fragment.PostFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.ztrong.loisusong.service.constant.Constant;

public class PostVietNamFragment extends PostFragment {
	private static boolean isFirstLaunch = true;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		setUpDatabase(Constant.POST_VIETNAM);
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
