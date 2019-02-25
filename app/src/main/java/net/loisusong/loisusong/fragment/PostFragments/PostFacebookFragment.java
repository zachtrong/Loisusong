package net.loisusong.loisusong.fragment.PostFragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import net.loisusong.loisusong.adapter.PostsFacebookRecyclerAdapter;
import net.loisusong.loisusong.service.constant.Constant;
import net.loisusong.loisusong.service.model.PostsDataFacebook;
import net.loisusong.loisusong.service.network.Network;
import net.loisusong.loisusong.service.network.NetworkFacebook;

import io.realm.RealmResults;

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

	public abstract RealmResults<PostsDataFacebook> initPostsModel();

	@Override
	public Network initNetwork() {
		return new NetworkFacebook(realm);
	}

	@Override
	void setUpAdapter() {
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
		postsFacebookRecyclerAdapter.notifyDataSetChanged();
		postsFacebookRecyclerAdapter.recoverLoadingView();
		postsFacebookRecyclerAdapter.removeLoadingView();
		onListenerNetworkPost();
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
}
