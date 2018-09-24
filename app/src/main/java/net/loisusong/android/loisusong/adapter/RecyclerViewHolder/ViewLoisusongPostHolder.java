package net.loisusong.android.loisusong.adapter.RecyclerViewHolder;

import android.text.Html;
import android.view.View;

import com.squareup.picasso.Picasso;

import net.loisusong.android.loisusong.R;
import net.loisusong.android.loisusong.service.model.PostsModel;

import io.realm.RealmObject;

public class ViewLoisusongPostHolder extends ViewPostHolder {
	public ViewLoisusongPostHolder(View itemView) {
		super(itemView);
	}

	@Override
	public void setPost(RealmObject realmObject) {
		PostsModel postsModel = (PostsModel) realmObject;
		Picasso.get()
				.load(getBestSizeType(postsModel))
				.placeholder(R.drawable.image_placeholder)
				.into(imageView);

		titleTextView.setText(Html.fromHtml(postsModel.title.rendered));
		dateTextView.setText(postsModel.modified);
	}

	private String getBestSizeType(PostsModel postsModel) {
		return postsModel
				.betterFeaturedImage
				.sourceUrl;
	}
}
