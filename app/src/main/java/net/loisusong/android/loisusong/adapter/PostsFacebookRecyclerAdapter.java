package net.loisusong.android.loisusong.adapter;

import android.view.View;

import net.loisusong.android.loisusong.adapter.RecyclerViewHolder.ViewFacebookPostHolder;
import net.loisusong.android.loisusong.adapter.RecyclerViewHolder.ViewPostHolder;
import net.loisusong.android.loisusong.fragment.PostFragments.PostFacebookFragment;
import net.loisusong.android.loisusong.fragment.PostFragments.PostFragment;
import net.loisusong.android.loisusong.fragment.PostFragments.PostLoisusongFragment;
import net.loisusong.android.loisusong.service.model.PostsDataFacebook;
import net.loisusong.android.loisusong.service.model.PostsModel;

import io.realm.RealmObject;
import io.realm.RealmResults;

public class PostsFacebookRecyclerAdapter extends PostsRecyclerAdapter {
	private RealmResults<PostsDataFacebook> postsDataFacebookRealmResults;

	public PostsFacebookRecyclerAdapter(PostFacebookFragment postFacebookFragment) {
		super(postFacebookFragment);
		postsDataFacebookRealmResults = postFacebookFragment.initPostsModel();
	}

	@Override
	protected ViewPostHolder onCreateViewPostHolder(View v) {
		return new ViewFacebookPostHolder(v);
	}

	@Override
	public RealmObject getRealmResultsItem(int position) {
		return postsDataFacebookRealmResults.get(position);
	}

	@Override
	public int getRealmResultsSize() {
		return postsDataFacebookRealmResults.size();
	}

	@Override
	protected boolean isLoadingView() {
		return false;
	}
}
