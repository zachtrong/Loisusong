package net.loisusong.android.loisusong.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.loisusong.android.loisusong.fragment.PostFragments.PostFacebookFragment;
import net.loisusong.android.loisusong.service.constant.Constant;

public class PostsFacebookPagerAdapter extends FragmentPagerAdapter {
	private static final int POST_PAGER_COUNT = 2;
	private static final String[] PAGE_TITLE =
			{Constant.POST_COFFEE, Constant.POST_MESSAGE};

	public PostsFacebookPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		return PostFacebookFragment.newInstance(PAGE_TITLE[i]);
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return PAGE_TITLE[position];
	}

	@Override
	public int getCount() {
		return POST_PAGER_COUNT;
	}
}
