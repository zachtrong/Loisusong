package net.loisusong.android.loisusong.adapter.RecyclerViewHolder;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.loisusong.android.loisusong.R;

import io.realm.RealmModel;
import io.realm.RealmObject;

public abstract class ViewPostHolder extends RecyclerView.ViewHolder {
	protected ImageView imageView;
	protected TextView titleTextView;
	protected TextView dateTextView;

	public ViewPostHolder(View itemView) {
		super(itemView);
		imageView = itemView.findViewById(R.id.iv_item);
		titleTextView = itemView.findViewById(R.id.tv_title);
		dateTextView = itemView.findViewById(R.id.tv_date);
	}

	public abstract void setPost(RealmObject postsModel);
}
