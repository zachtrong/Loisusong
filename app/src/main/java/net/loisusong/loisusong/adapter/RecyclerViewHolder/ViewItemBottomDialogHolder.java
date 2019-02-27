package net.loisusong.loisusong.adapter.RecyclerViewHolder;

import android.view.View;
import android.widget.TextView;

import net.loisusong.loisusong.R;
import net.loisusong.loisusong.service.interfaces.BottomSheetItemListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewItemBottomDialogHolder extends RecyclerView.ViewHolder {
	private String video;
	private TextView textView;

	public ViewItemBottomDialogHolder(@NonNull View itemView,
									  ArrayList<BottomSheetItemListener> bottomSheetItemListeners) {
		super(itemView);
		textView = itemView.findViewById(R.id.tv_item_bottom_sheet);
		textView.setOnClickListener(v -> {
			for (BottomSheetItemListener bottomSheetItemListener : bottomSheetItemListeners) {
				bottomSheetItemListener.onBottomSheetItemClick(video);
			}
		});
	}

	public void setVideo(String video, int position) {
		this.video = video;
		String itemLabel = "Bài giảng " + String.valueOf(position + 1) + " - " + video;
		textView.setText(itemLabel);
	}

	public String getVideo() {
		return video;
	}
}
