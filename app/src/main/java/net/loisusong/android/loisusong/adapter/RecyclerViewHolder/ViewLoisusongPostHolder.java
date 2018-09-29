package net.loisusong.android.loisusong.adapter.RecyclerViewHolder;

import android.content.Intent;
import android.text.Html;
import android.view.View;

import com.squareup.picasso.Picasso;

import net.loisusong.android.loisusong.R;
import net.loisusong.android.loisusong.factory.PostViewFactory;
import net.loisusong.android.loisusong.service.model.PostsModel;
import net.loisusong.android.loisusong.service.wrapper.PostIntentWrapper;

import io.realm.RealmObject;

public class ViewLoisusongPostHolder extends ViewPostHolder {
	private String typePost;
	private PostsModel postsModel;

	public ViewLoisusongPostHolder(View itemView, String typePost) {
		super(itemView);
		this.typePost = typePost;
	}

	@Override
	public void setPost(RealmObject realmObject) {
		postsModel = (PostsModel) realmObject;
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

	@Override
	public void onClick(View v) {
		Intent intent = PostViewFactory.createActivityIntent(v.getContext(), typePost);
		PostIntentWrapper intentWrapper = new PostIntentWrapper(intent);
		intentWrapper.addTitle(postsModel.title.rendered);
		intentWrapper.addDate(postsModel.modified);
		intentWrapper.addContent(postsModel.content.rendered);
		intentWrapper.addImg(getBestSizeType(postsModel));

		intent = intentWrapper.getIntent();
		v.getContext().startActivity(intent);
	}
}
