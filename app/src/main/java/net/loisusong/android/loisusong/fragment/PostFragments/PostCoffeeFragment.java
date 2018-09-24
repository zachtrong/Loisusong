package net.loisusong.android.loisusong.fragment.PostFragments;

import net.loisusong.android.loisusong.service.constant.Constant;
import net.loisusong.android.loisusong.service.model.PostsDataFacebook;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

public class PostCoffeeFragment extends PostFacebookFragment {
	private static boolean isFirstLaunch = true;
	@Override
	void initTypePost() {
		setTypePost(Constant.POST_COFFEE);
	}

	@Override
	public RealmResults<PostsDataFacebook> initPostsModel() {
		return getRealm().where(PostsDataFacebook.class)
				.contains("universalVideoId", "cafetoi")
				.findAll()
				.sort("createdTime", Sort.DESCENDING);
	}

	@Override
	boolean isFirstLaunch() {
		if (isFirstLaunch) {
			isFirstLaunch = false;
			return true;
		}
		return true;
	}
}
