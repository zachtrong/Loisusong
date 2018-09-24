package net.loisusong.android.loisusong.adapter.RecyclerViewHolder;

import android.view.View;

import com.squareup.picasso.Picasso;

import io.realm.RealmObject;
import net.loisusong.android.loisusong.service.model.PostsDataFacebook;
import net.loisusong.android.loisusong.R;
import android.text.Html;

public class ViewFacebookPostHolder extends ViewPostHolder {
	public ViewFacebookPostHolder(View itemView) {
		super(itemView);
	}

	@Override
	public void setPost(RealmObject realmObject) {
		PostsDataFacebook postsDataFacebook = (PostsDataFacebook) realmObject;
		Picasso.get()
				.load(postsDataFacebook.format.last().url)
				.placeholder(R.drawable.image_placeholder)
				.into(imageView);

		titleTextView.setText(Html.fromHtml(postsDataFacebook.videoDescription));
		dateTextView.setText(postsDataFacebook.updatedTime);
	}
}
