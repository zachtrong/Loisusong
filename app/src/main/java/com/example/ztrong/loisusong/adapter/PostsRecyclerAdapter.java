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
import com.example.ztrong.loisusong.service.model.PostsChild.SizeType;
import com.example.ztrong.loisusong.service.model.PostsChild.Sizes;
import com.example.ztrong.loisusong.service.model.PostsModel;
import com.example.ztrong.loisusong.service.utils.image.BitmapTransform;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class PostsRecyclerAdapter extends RecyclerView.Adapter {

	private Realm realm;
	private RealmResults<PostsModel> postsModels;

	public void setDatabase(Realm realm) {
		this.realm = realm;
		postsModels = realm.where(PostsModel.class)
				.findAll();
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_post, parent, false);
		return new ViewPostHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof ViewPostHolder) {
			((ViewPostHolder) holder).setPost(postsModels.get(position));
		} else {
			// TODO ((ViewLoadingHolder) holder);
		}
	}

	@Override
	public int getItemCount() {
		return postsModels.size();
	}

	public class ViewPostHolder extends RecyclerView.ViewHolder {
		ImageView imageView;
		TextView titleTextView;
		TextView dateTextView;

		public ViewPostHolder(View itemView) {
			super(itemView);
			imageView = itemView.findViewById(R.id.iv_item);
			titleTextView = itemView.findViewById(R.id.tv_title);
			dateTextView = itemView.findViewById(R.id.tv_date);
		}

		public void setPost(PostsModel postsModel) {
			Picasso.get()
					.load(getBestSizeType(postsModel))
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

		public ViewLoadingHolder(View itemView) {
			super(itemView);
			progressBar = itemView.findViewById(R.id.progress_bar);
		}
	}
}
