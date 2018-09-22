package com.example.ztrong.loisusong.fragment.PostFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ztrong.loisusong.R;
import com.example.ztrong.loisusong.adapter.PostsRecyclerAdapter;
import com.example.ztrong.loisusong.service.constant.Constant;
import com.example.ztrong.loisusong.service.interfaces.PostNetworkStatus;
import com.example.ztrong.loisusong.service.network.Network;
import com.example.ztrong.loisusong.service.utils.realm.RealmConfigs;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.supercharge.shimmerlayout.ShimmerLayout;

public abstract class PostFragment extends Fragment
		implements SwipeRefreshLayout.OnRefreshListener,
		PostNetworkStatus {

	public static final int POST_OVER = -1;

	@BindView(R.id.rv)
	RecyclerView recyclerView;
	@BindView(R.id.srl)
	SwipeRefreshLayout swipeRefreshLayout;
	@BindView(R.id.shimmer_layout)
	ShimmerLayout shimmerLayout;

	private Realm realm;
	private PostsRecyclerAdapter postsRecyclerAdapter;
	private String typePost;
	private Network network;
	protected LinearLayoutManager layoutManager;
	private boolean isLoading = false;
	private int pageLoading;

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

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		initTypePost();
		setUpRealm();
	}

	abstract void initTypePost();

	// Each fragment has its own realm for pagination
	private void setUpRealm() {
		RealmConfiguration realmConfiguration = RealmConfigs.getConfig(typePost);
		realm = Realm.getInstance(realmConfiguration);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setUpNetwork();
	}

	private void setUpNetwork() {
		network = new Network(realm);
		network.addListener(this);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_posts, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setUpLayout(view);
		if (isFirstLaunch()) {
			setUpFirstLaunch();
		} else {
			setUpNormalLaunch();
		}
	}

	private void setUpLayout(View view) {
		ButterKnife.bind(this, view);
		layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		postsRecyclerAdapter = new PostsRecyclerAdapter(this);
		recyclerView.setAdapter(postsRecyclerAdapter);
		recyclerView.addOnScrollListener(onScrollListener);
		swipeRefreshLayout.setOnRefreshListener(this);
	}

	abstract boolean isFirstLaunch();

	private void setUpFirstLaunch() {
		shimmerLayout.startShimmerAnimation();
		onRefresh();
	}

	@Override
	public void onRefresh() {
		if (!isLoading) {
			isLoading = true;
			pageLoading = getLastPageLoaded();
			network.getNewPosts(typePost);
		} else {
			swipeRefreshLayout.setRefreshing(false);
		}
	}

	private void onListenerNetworkPost() {
		isLoading = false;
		swipeRefreshLayout.setRefreshing(false);
		if (shimmerLayout.getVisibility() != View.GONE) {
			shimmerLayout.stopShimmerAnimation();
			shimmerLayout.setVisibility(View.GONE);
		}
	}

	private void setUpNormalLaunch() {
		shimmerLayout.setVisibility(View.GONE);
	}

	@Override
	public void onNewPosts() {
		postsRecyclerAdapter.notifyDataSetChanged();
		postsRecyclerAdapter.recoverLoadingView();
		onListenerNetworkPost();
		saveLoadedPage(1);
	}

	@Override
	public void onPosts() {
		postsRecyclerAdapter.notifyDataSetChanged();
		onListenerNetworkPost();
		saveLoadedPage(pageLoading);
	}

	@Override
	public void onEmpty() {
		onListenerNetworkPost();
		saveLoadedPage(POST_OVER);
		postsRecyclerAdapter.removeLoadingView();
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
			return layoutManager.findLastVisibleItemPosition() + 1 == postsRecyclerAdapter.getItemCount();
		}

		private void onRequestMorePosts() {
			if (!isLoading) {
				int lastPage = getLastPageLoaded();
				if (lastPage == POST_OVER) {
					postsRecyclerAdapter.removeLoadingView();
				} else {
					isLoading = true;
					pageLoading = lastPage + 1;
					network.getPosts(typePost, pageLoading);
				}
			}
		}
	};

	private void saveLoadedPage(int page) {
		Hawk.put(typePost, page);
	}

	private int getLastPageLoaded() {
		return Hawk.get(typePost, 1);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		network.removeListener(this);
	}

	public Realm getRealm() {
		return realm;
	}

	protected void setTypePost(String typePost) {
		this.typePost = typePost;
	}
}
