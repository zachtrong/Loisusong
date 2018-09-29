package net.loisusong.android.loisusong.adapter;

import android.view.View;

import net.loisusong.android.loisusong.adapter.RecyclerViewHolder.ViewLoisusongPostHolder;
import net.loisusong.android.loisusong.adapter.RecyclerViewHolder.ViewPostHolder;
import net.loisusong.android.loisusong.fragment.PostFragments.PostLoisusongFragment;
import net.loisusong.android.loisusong.service.model.PostsModel;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

public class PostsLoisusongRecyclerAdapter extends PostsRecyclerAdapter {
	public RealmResults<PostsModel> postsModelRealmResults;

	public PostsLoisusongRecyclerAdapter(PostLoisusongFragment postLoisusongFragment) {
		super(postLoisusongFragment);
		postsModelRealmResults = postLoisusongFragment.initPostsModel();
	}

	@Override
	protected ViewPostHolder onCreateViewPostHolder(View v, String typePost) {
		return new ViewLoisusongPostHolder(v, typePost);
	}

	@Override
	public RealmObject getRealmResultsItem(int position) {
		return postsModelRealmResults.get(position);
	}

	@Override
	public int getRealmResultsSize() {
		return postsModelRealmResults.size();
	}

	protected boolean isLoadingView() {
		return true;
	}
}
