package net.loisusong.loisusong.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import net.loisusong.loisusong.fragment.PostFragments.PostLoisusongFragment;
import net.loisusong.loisusong.service.constant.Constant;

public class PostsLoisusongPagerAdapter extends FragmentPagerAdapter {

	private static final int POST_PAGER_COUNT = 3;
	private static final String[] PAGE_TITLE =
			{Constant.POST_ALL, Constant.POST_VIETNAM, Constant.POST_INTERNATIONAL};

	public PostsLoisusongPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return PostLoisusongFragment.newInstance(PAGE_TITLE[position]);
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
