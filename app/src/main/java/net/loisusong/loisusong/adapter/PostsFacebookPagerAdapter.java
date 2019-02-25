package net.loisusong.loisusong.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import net.loisusong.loisusong.fragment.PostFragments.PostFacebookFragment;
import net.loisusong.loisusong.service.constant.Constant;

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
