package com.example.ztrong.loisusong.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ztrong.loisusong.R;

import butterknife.BindView;
import io.realm.Realm;

public class PostsRecyclerAdapter extends RecyclerView.Adapter {

	private Realm realm;

	public void setDatabase(Realm realm) {
		this.realm = realm;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 0;
	}

	public class ViewPostBigHolder extends RecyclerView.ViewHolder {
		ImageView imageView;
		TextView titleTextView;
		TextView dateTextView;

		public ViewPostBigHolder(View itemView) {
			super(itemView);
			imageView = itemView.findViewById(R.id.iv_item);
			titleTextView = itemView.findViewById(R.id.tv_title);
			dateTextView = itemView.findViewById(R.id.tv_date);
		}
	}

	public class ViewPostHolder extends RecyclerView.ViewHolder {

		public ViewPostHolder(View itemView) {
			super(itemView);
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
