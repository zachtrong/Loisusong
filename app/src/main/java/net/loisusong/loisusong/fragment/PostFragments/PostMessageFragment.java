package net.loisusong.loisusong.fragment.PostFragments;

import net.loisusong.loisusong.service.constant.Constant;
import net.loisusong.loisusong.service.model.PostsDataFacebook;

import io.realm.RealmResults;
import io.realm.Sort;

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

	@Override
	public RealmResults<PostsDataFacebook> initPostsModel() {
		return getRealm().where(PostsDataFacebook.class)
				.contains("universalVideoId", "sudiepmucsu")
				.findAll()
				.sort("createdTime", Sort.DESCENDING);
	}
}
