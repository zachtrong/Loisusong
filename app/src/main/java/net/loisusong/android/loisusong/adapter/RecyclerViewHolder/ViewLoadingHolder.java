package net.loisusong.android.loisusong.adapter.RecyclerViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import net.loisusong.android.loisusong.R;

public class ViewLoadingHolder extends RecyclerView.ViewHolder {
	private ProgressBar progressBar;

	public ViewLoadingHolder(View itemView) {
		super(itemView);
		progressBar = itemView.findViewById(R.id.progress_bar);
	}
}
