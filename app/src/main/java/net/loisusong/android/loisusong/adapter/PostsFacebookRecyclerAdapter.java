package net.loisusong.android.loisusong.adapter;

import net.loisusong.android.loisusong.fragment.PostFragments.PostFragment;
import net.loisusong.android.loisusong.fragment.PostFragments.PostLoisusongFragment;
import net.loisusong.android.loisusong.service.model.PostsModel;

import io.realm.RealmResults;

public class PostsFacebookRecyclerAdapter extends PostsRecyclerAdapter {


	public PostsFacebookRecyclerAdapter(PostFragment postFragment) {
		super(postFragment);
	}

	@Override
	protected RealmResults<PostsModel> initPostsModel() {
		return getRealm().where(PostsModel.class)
				.findAll();
	}

	@Override
	protected boolean isLoadingView() {
		return false;
	}
}
