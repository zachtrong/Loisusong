package com.example.ztrong.loisusong.fragment.PostFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.ztrong.loisusong.service.constant.Constant;

public class PostInternationalFragment extends PostFragment {
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		setUpDatabase(Constant.POST_INTERNATIONAL);
	}
}
