package net.loisusong.loisusong.adapter;

import android.view.View;

import net.loisusong.loisusong.adapter.RecyclerViewHolder.ViewFacebookPostHolder;
import net.loisusong.loisusong.adapter.RecyclerViewHolder.ViewPostHolder;
import net.loisusong.loisusong.fragment.PostFragments.PostFacebookFragment;
import net.loisusong.loisusong.service.model.PostsDataFacebook;

import io.realm.RealmObject;
import io.realm.RealmResults;

public class PostsFacebookRecyclerAdapter extends PostsRecyclerAdapter {
	private RealmResults<PostsDataFacebook> postsDataFacebookRealmResults;

	public PostsFacebookRecyclerAdapter(PostFacebookFragment postFacebookFragment) {
		super(postFacebookFragment);
		postsDataFacebookRealmResults = postFacebookFragment.initPostsModel();
	}

	@Override
	protected ViewPostHolder onCreateViewPostHolder(View v, String typePost) {
		return new ViewFacebookPostHolder(v, typePost);
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
