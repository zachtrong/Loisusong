package net.loisusong.loisusong.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.loisusong.loisusong.R;
import net.loisusong.loisusong.adapter.RecyclerViewHolder.ViewLoadingHolder;
import net.loisusong.loisusong.adapter.RecyclerViewHolder.ViewPostHolder;
import net.loisusong.loisusong.fragment.PostFragments.PostFragment;

import io.realm.Realm;
import io.realm.RealmObject;

public abstract class PostsRecyclerAdapter extends RecyclerView.Adapter {

	private static final int VIEW_POST = 0;
	private static final int VIEW_LOADING = 1;

	private Realm realm;
	private boolean isLoadingView = isLoadingView();
	private String typePost;

	protected abstract boolean isLoadingView();

	public PostsRecyclerAdapter(PostFragment postFragment) {
		realm = postFragment.getRealm();
		typePost = postFragment.getTypePost();
	}

	public Realm getRealm() {
		return realm;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v;
		if (viewType == VIEW_POST) {
			v = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.item_post, parent, false);
			return onCreateViewPostHolder(v, typePost);
		} else if (viewType == VIEW_LOADING) {
			v = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.progressbar, parent, false);
			return new ViewLoadingHolder(v);
		} else {
			throw new Error("No such ViewType Holder");
		}
	}

	protected abstract ViewPostHolder onCreateViewPostHolder(View v, String typePost);

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof ViewPostHolder) {
			((ViewPostHolder) holder).setPost(getRealmResultsItem(position));
		} else if (holder instanceof ViewLoadingHolder) {

		} else {
			throw new Error("No such view to bind");
		}
	}

	public abstract RealmObject getRealmResultsItem(int position);

	@Override
	public int getItemViewType(int position) {
		if (position < getRealmResultsSize()) {
			return VIEW_POST;
		} else {
			return VIEW_LOADING;
		}
	}

	public abstract int getRealmResultsSize();

	@Override
	public int getItemCount() {
		return getRealmResultsSize() + (isLoadingView ? 1 : 0);
	}

	public void removeLoadingView() {
		if (isLoadingView) {
			notifyItemRemoved(getItemCount() - 1);
			isLoadingView = false;
		}
	}

	public void recoverLoadingView() {
		if (!isLoadingView) {
			isLoadingView = true;
			notifyItemChanged(getItemCount());
		}
	}
}
