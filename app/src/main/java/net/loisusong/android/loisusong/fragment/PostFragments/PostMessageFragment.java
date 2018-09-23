package net.loisusong.android.loisusong.fragment.PostFragments;

import net.loisusong.android.loisusong.service.constant.Constant;

public class PostMessageFragment extends PostFacebookFragment {
	private static boolean isFirstLaunch = true;
	@Override
	void initTypePost() {
		setTypePost(Constant.POST_MESSAGE);
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
