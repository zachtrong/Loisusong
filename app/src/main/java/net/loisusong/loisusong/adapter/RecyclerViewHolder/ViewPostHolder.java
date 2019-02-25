package net.loisusong.loisusong.adapter.RecyclerViewHolder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.loisusong.loisusong.R;

import io.realm.RealmObject;

public abstract class ViewPostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
	protected ImageView imageView;
	protected TextView titleTextView;
	protected TextView dateTextView;

	public ViewPostHolder(View itemView) {
		super(itemView);
		imageView = itemView.findViewById(R.id.iv_item);
		titleTextView = itemView.findViewById(R.id.tv_title);
		dateTextView = itemView.findViewById(R.id.tv_date);
		itemView.setOnClickListener(this);
	}

	public abstract void setPost(RealmObject postsModel);
}
