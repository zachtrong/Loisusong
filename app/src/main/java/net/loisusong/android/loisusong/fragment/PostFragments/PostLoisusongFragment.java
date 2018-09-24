package net.loisusong.android.loisusong.fragment.PostFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.loisusong.android.loisusong.R;
import net.loisusong.android.loisusong.adapter.PostsLoisusongRecyclerAdapter;
import net.loisusong.android.loisusong.service.constant.Constant;
import net.loisusong.android.loisusong.service.interfaces.PostNetworkStatus;
import net.loisusong.android.loisusong.service.model.PostsModel;

import butterknife.BindView;
import io.realm.RealmResults;
import io.supercharge.shimmerlayout.ShimmerLayout;

public abstract class PostLoisusongFragment extends PostFragment
		implements SwipeRefreshLayout.OnRefreshListener,
		PostNetworkStatus {

	public static final int POST_OVER = -1;

	@BindView(R.id.rv)
	RecyclerView recyclerView;
	@BindView(R.id.srl)
	SwipeRefreshLayout swipeRefreshLayout;
	@BindView(R.id.shimmer_layout)
	ShimmerLayout shimmerLayout;

	private PostsLoisusongRecyclerAdapter postsLoisusongRecyclerAdapter;

	public static Fragment newInstance(String type) {
		switch (type) {
			case Constant.POST_ALL:
				return new PostAllFragment();
			case Constant.POST_VIETNAM:
				return new PostVietNamFragment();
			case Constant.POST_INTERNATIONAL:
				return new PostInternationalFragment();
			case Constant.POST_MEDIA:
				return new PostMediaFragment();
			case Constant.POST_ABOUT_US:
				return new PostAboutFragment();
			default:
				throw new Error("No Such Fragment");
		}
	}

	public abstract RealmResults<PostsModel> initPostsModel();

	@Override
	protected void setUpAdapter() {
		postsLoisusongRecyclerAdapter = new PostsLoisusongRecyclerAdapter(this);
	}

	@Override
	protected RecyclerView.Adapter getAdapter() {
		return postsLoisusongRecyclerAdapter;
	}

	@Override
	protected RecyclerView.OnScrollListener getOnScrollListener() {
		return onScrollListener;
	}

	@Override
	public void onNewPosts() {
		postsLoisusongRecyclerAdapter.notifyDataSetChanged();
		postsLoisusongRecyclerAdapter.recoverLoadingView();
		onListenerNetworkPost();
		saveLoadedPage(1);
	}

	@Override
	public void onPosts() {
		postsLoisusongRecyclerAdapter.notifyDataSetChanged();
		onListenerNetworkPost();
		saveLoadedPage(pageLoading);
	}

	@Override
	public void onEmpty() {
		onListenerNetworkPost();
		saveLoadedPage(POST_OVER);
		postsLoisusongRecyclerAdapter.removeLoadingView();
	}

	@Override
	public void onError() {
		onListenerNetworkPost();
	}

	private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
		@Override
		public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
			super.onScrollStateChanged(recyclerView, newState);
		}

		@Override
		public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);
			if (isScrolledLastPost()) {
				onRequestMorePosts();
			}
		}

		private boolean isScrolledLastPost() {
			return layoutManager.findLastVisibleItemPosition() + 1 == postsLoisusongRecyclerAdapter.getItemCount();
		}

		private void onRequestMorePosts() {
			if (!isLoading) {
				int lastPage = getLastPageLoaded();
				if (lastPage == POST_OVER) {
					postsLoisusongRecyclerAdapter.removeLoadingView();
				} else {
					isLoading = true;
					pageLoading = lastPage + 1;
					networkLoisusong.getPosts(typePost, pageLoading);
				}
			}
		}
	};
}
