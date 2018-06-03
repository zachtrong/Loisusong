package com.example.ztrong.loisusong.fragment.PostFragments;

import android.content.Context;

import com.example.ztrong.loisusong.service.constant.Constant;

public class PostAllFragment extends PostFragment {
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		super.setUpDatabase(Constant.POST_ALL);
	}
}
