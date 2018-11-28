package net.loisusong.loisusong.fragment.PostFragments;

import net.loisusong.loisusong.service.constant.Constant;
import net.loisusong.loisusong.service.model.PostsModel;

import io.realm.RealmResults;
import io.realm.Sort;

public class PostMediaFragment extends PostLoisusongFragment {
	private static boolean isFirstLaunch = true;
	@Override
	void initTypePost() {
		setTypePost(Constant.POST_MEDIA);
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
	public RealmResults<PostsModel> initPostsModel() {
		return getRealm().where(PostsModel.class)
				.findAll()
				.sort("date", Sort.DESCENDING);
	}
}
