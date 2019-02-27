package net.loisusong.loisusong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.loisusong.loisusong.R;
import net.loisusong.loisusong.adapter.RecyclerViewHolder.ViewItemBottomDialogHolder;
import net.loisusong.loisusong.service.interfaces.BottomSheetItemListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class YouTubeBottomDialogAdapter extends RecyclerView.Adapter {
	private ArrayList<String> videos;
	private ArrayList<BottomSheetItemListener> bottomSheetItemListeners;

	public YouTubeBottomDialogAdapter(ArrayList<String> videos,
									  ArrayList<BottomSheetItemListener> bottomSheetItemListeners) {
		this.videos = videos;
		this.bottomSheetItemListeners = bottomSheetItemListeners;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_bottom_dialog, parent, false);
		return new ViewItemBottomDialogHolder(view, bottomSheetItemListeners);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		((ViewItemBottomDialogHolder) holder).setVideo(videos.get(position), position);
	}

	@Override
	public int getItemCount() {
		return videos.size();
	}
}
