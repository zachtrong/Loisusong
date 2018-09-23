package net.loisusong.android.loisusong.fragment.PostFragments;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import net.loisusong.android.loisusong.adapter.PostsFacebookRecyclerAdapter;
import net.loisusong.android.loisusong.service.constant.Constant;

public abstract class PostFacebookFragment extends PostFragment {

	private PostsFacebookRecyclerAdapter postsFacebookRecyclerAdapter;

	public static Fragment newInstance(String type) {
		switch (type) {
			case Constant.POST_COFFEE:
				return new PostCoffeeFragment();
			case Constant.POST_MESSAGE:
				return new PostMessageFragment();
			default:
				throw new Error("No Such Fragment");
		}
	}

	@Override
	void setUpData() {
		postsFacebookRecyclerAdapter = new PostsFacebookRecyclerAdapter(this);
	}

	@Override
	protected RecyclerView.Adapter getAdapter() {
		return postsFacebookRecyclerAdapter;
	}

	@Override
	protected RecyclerView.OnScrollListener getOnScrollListener() {
		return new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
			}

			@Override
			public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
			}
		};
	}

	@Override
	public void onNewPosts() {

	}

	@Override
	public void onPosts() {

	}

	@Override
	public void onEmpty() {

	}

	@Override
	public void onError() {

	}
}
