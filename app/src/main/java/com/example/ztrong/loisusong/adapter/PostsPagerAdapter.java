package com.example.ztrong.loisusong.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ztrong.loisusong.fragment.PostFragments.PostFragment;
import com.example.ztrong.loisusong.service.constant.Constant;

public class PostsPagerAdapter extends FragmentPagerAdapter {

	private static final int POST_PAGER_COUNT = 3;
	private static final String[] PAGE_TITLE =
			{Constant.POST_ALL, Constant.POST_VIETNAM, Constant.POST_INTERNATIONAL};
	private Context context;

	public PostsPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
	}

	@Override
	public Fragment getItem(int position) {
		return PostFragment.newInstance(PAGE_TITLE[position]);
	}

	@Override
	public int getCount() {
		return POST_PAGER_COUNT;
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return PAGE_TITLE[position];
	}
}
