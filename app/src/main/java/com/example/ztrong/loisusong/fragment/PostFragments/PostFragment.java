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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.supercharge.shimmerlayout.ShimmerLayout;

public abstract class PostFragment extends Fragment
		implements SwipeRefreshLayout.OnRefreshListener,
		PostNetworkStatus {

	@BindView(R.id.rv_home)
	RecyclerView recyclerView;
	@BindView(R.id.srl_home)
	SwipeRefreshLayout swipeRefreshLayout;
	@BindView(R.id.shimmer_layout)
	ShimmerLayout shimmerLayout;

	private Realm realm;
	private PostsRecyclerAdapter postsRecyclerAdapter = new PostsRecyclerAdapter();
	private String typePost;
	private Network network;
	protected RecyclerView.LayoutManager layoutManager;
	private boolean isRefresh = false;

	public static Fragment newInstance(String type) {
		switch (type) {
			case Constant.POST_ALL:
				return new PostAllFragment();
			case Constant.POST_VIETNAM:
				return new PostVietNamFragment();
			case Constant.POST_INTERNATIONAL:
				return new PostInternationalFragment();
			default:
				throw new Error("No Such Fragment");
		}
	}

	protected void setUpDatabase(String typePost) {
		this.typePost = typePost;
		setUpRealm();
		postsRecyclerAdapter.setDatabase(realm);
	}

	// Each fragment has its own realm for pagination
	private void setUpRealm() {
		RealmConfiguration realmConfiguration = RealmConfigs.getConfig(typePost);
		realm = Realm.getInstance(realmConfiguration);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
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
		return inflater.inflate(R.layout.fragment_posts_home, container, false);
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
		recyclerView.setAdapter(postsRecyclerAdapter);
		swipeRefreshLayout.setOnRefreshListener(this);
	}

	abstract boolean isFirstLaunch();

	private void setUpFirstLaunch() {
		shimmerLayout.startShimmerAnimation();
		onRefresh();
	}

	@Override
	public void onRefresh() {
		isRefresh = true;
		network.getNewPosts(Constant.POST_ALL);
	}

	private void onListenerNetworkPost() {
		isRefresh = false;
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
	public void onPosts() {
		onListenerNetworkPost();
	}

	@Override
	public void onEmpty() {
		onListenerNetworkPost();
	}

	@Override
	public void onError() {
		onListenerNetworkPost();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		network.removeListener(this);
	}
}
