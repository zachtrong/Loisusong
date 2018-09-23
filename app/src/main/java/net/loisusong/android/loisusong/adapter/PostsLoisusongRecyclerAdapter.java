package net.loisusong.android.loisusong.adapter;

import net.loisusong.android.loisusong.fragment.PostFragments.PostLoisusongFragment;
import net.loisusong.android.loisusong.service.model.PostsModel;

import io.realm.RealmResults;

public class PostsLoisusongRecyclerAdapter extends PostsRecyclerAdapter {

	public PostsLoisusongRecyclerAdapter(PostLoisusongFragment postLoisusongFragment) {
		super(postLoisusongFragment);
	}

	@Override
	protected RealmResults<PostsModel> initPostsModel() {
		return getRealm().where(PostsModel.class)
				.findAll();
	}

	protected boolean isLoadingView() {
		return true;
	}
}
