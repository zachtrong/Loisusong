package net.loisusong.android.loisusong.fragment.PostFragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.loisusong.android.loisusong.R;
import net.loisusong.android.loisusong.service.interfaces.PostNetworkStatus;
import net.loisusong.android.loisusong.service.network.Network;
import net.loisusong.android.loisusong.service.utils.realm.RealmConfigs;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.supercharge.shimmerlayout.ShimmerLayout;

public abstract class PostFragment extends Fragment
		implements SwipeRefreshLayout.OnRefreshListener,
		PostNetworkStatus {

	private static final int TIME_RELOADING_ANIMATION = 3000;

	@BindView(R.id.rv)
	RecyclerView recyclerView;
	@BindView(R.id.srl)
	SwipeRefreshLayout swipeRefreshLayout;
	@BindView(R.id.shimmer_layout)
	ShimmerLayout shimmerLayout;

	protected Realm realm;
	protected String typePost;
	protected Network network;
	protected LinearLayoutManager layoutManager;
	protected boolean isLoading = false;
	protected int pageLoading;

	private Handler handler;
	private Runnable delayedRemoveLoadingLayout = this::onListenerNetworkPost;

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
		network = initNetwork();
		network.addListener(this);
	}

	public abstract Network initNetwork();

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_posts, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setUpAdapter();
		setUpLayout(view);
		if (isFirstLaunch()) {
			setUpFirstLaunch();
		} else {
			setUpNormalLaunch();
		}
	}

	abstract void setUpAdapter();

	private void setUpLayout(View view) {
		ButterKnife.bind(this, view);
		swipeRefreshLayout.setOnRefreshListener(this);

		layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(getAdapter());
		recyclerView.addOnScrollListener(getOnScrollListener());
	}

	abstract protected RecyclerView.Adapter getAdapter();

	abstract protected RecyclerView.OnScrollListener getOnScrollListener();

	abstract boolean isFirstLaunch();

	private void setUpFirstLaunch() {
		shimmerLayout.startShimmerAnimation();
		setUpDelayedRemoveLoadingLayout();
		onRefresh();
	}

	private void setUpDelayedRemoveLoadingLayout() {
		handler = new Handler();
		handler.postDelayed(delayedRemoveLoadingLayout, TIME_RELOADING_ANIMATION);
	}

	private void removeDelayedRemoveLoadingLayout() {
		if (handler != null && delayedRemoveLoadingLayout != null) {
			handler.removeCallbacks(delayedRemoveLoadingLayout);
		}
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

	protected void onListenerNetworkPost() {
		isLoading = false;
		swipeRefreshLayout.setRefreshing(false);
		removeDelayedRemoveLoadingLayout();
		if (shimmerLayout.getVisibility() != View.GONE) {
			shimmerLayout.stopShimmerAnimation();
			shimmerLayout.setVisibility(View.GONE);
		}
	}

	protected void setUpNormalLaunch() {
		shimmerLayout.setVisibility(View.GONE);
	}


	protected void saveLoadedPage(int page) {
		Hawk.put(typePost, page);
	}

	protected int getLastPageLoaded() {
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

	public String getTypePost() {
		return typePost;
	}
}
