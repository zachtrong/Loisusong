package com.example.ztrong.loisusong.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ztrong.loisusong.R;
import com.example.ztrong.loisusong.fragment.PostFragments.PostFragment;
import com.example.ztrong.loisusong.service.interfaces.RequestMorePosts;
import com.example.ztrong.loisusong.service.model.PostsModel;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmResults;

public class PostsRecyclerAdapter extends RecyclerView.Adapter {

	private static final int VIEW_POST = 0;
	private static final int VIEW_LOADING = 1;

	private Realm realm;
	private RealmResults<PostsModel> postsModels;
	private boolean isLoadingView = true;

	public PostsRecyclerAdapter(PostFragment postFragment) {
		realm = postFragment.getRealm();
		postsModels = realm.where(PostsModel.class)
				.findAll();
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v;
		if (viewType == VIEW_POST) {
			v = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.item_post, parent, false);
			return new ViewPostHolder(v);
		} else if (viewType == VIEW_LOADING) {
			v = LayoutInflater.from(parent.getContext())
							.inflate(R.layout.progressbar, parent, false);
			return new ViewLoadingHolder(v);
		} else {
			throw new Error("No such ViewType Holder");
		}
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof ViewPostHolder) {
			((ViewPostHolder) holder).setPost(postsModels.get(position));
		} else if (holder instanceof ViewLoadingHolder) {

		} else {
			throw new Error("No such view to bind");
		}
	}

	@Override
	public int getItemViewType(int position) {
		if (position < postsModels.size()) {
			return VIEW_POST;
		} else {
			return VIEW_LOADING;
		}
	}

	@Override
	public int getItemCount() {
		return postsModels.size() + (isLoadingView ? 1 : 0);
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

	public class ViewPostHolder extends RecyclerView.ViewHolder {
		ImageView imageView;
		TextView titleTextView;
		TextView dateTextView;

		ViewPostHolder(View itemView) {
			super(itemView);
			imageView = itemView.findViewById(R.id.iv_item);
			titleTextView = itemView.findViewById(R.id.tv_title);
			dateTextView = itemView.findViewById(R.id.tv_date);
		}

		private void setPost(PostsModel postsModel) {
			Picasso.get()
					.load(getBestSizeType(postsModel))
					.placeholder(R.drawable.image_placeholder)
					.into(imageView);

			titleTextView.setText(Html.fromHtml(postsModel.title.rendered));
			dateTextView.setText(postsModel.modified);
		}

		String getBestSizeType(PostsModel postsModel) {
			return postsModel
					.betterFeaturedImage
					.sourceUrl;
		}
	}

	public class ViewLoadingHolder extends RecyclerView.ViewHolder {
		ProgressBar progressBar;

		ViewLoadingHolder(View itemView) {
			super(itemView);
			progressBar = itemView.findViewById(R.id.progress_bar);
		}
	}
}
