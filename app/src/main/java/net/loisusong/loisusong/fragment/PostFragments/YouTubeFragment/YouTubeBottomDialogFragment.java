package net.loisusong.loisusong.fragment.PostFragments.YouTubeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import net.loisusong.loisusong.R;
import net.loisusong.loisusong.adapter.YouTubeBottomDialogAdapter;
import net.loisusong.loisusong.service.interfaces.BottomSheetItemListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class YouTubeBottomDialogFragment extends BottomSheetDialogFragment {
	@BindView(R.id.rv_bottom_sheet)
	RecyclerView recyclerView;

	private static final String YOU_TUBE_VIDEO_TAG = "YOU_TUBE_VIDEO_TAG";
	private RecyclerView.LayoutManager layoutManager;
	private ArrayList<String> videos;
	private ArrayList<BottomSheetItemListener> bottomSheetItemListener = new ArrayList<>();

	public static YouTubeBottomDialogFragment newInstance(ArrayList<String> youTubeVideos) {
		Bundle bundle = new Bundle();
		bundle.putStringArrayList(YOU_TUBE_VIDEO_TAG, youTubeVideos);
		YouTubeBottomDialogFragment youTubeBottomDialogFragment = new YouTubeBottomDialogFragment();
		youTubeBottomDialogFragment.setArguments(bundle);
		return youTubeBottomDialogFragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();
		if (bundle.getStringArrayList(YOU_TUBE_VIDEO_TAG) != null) {
			videos = getArguments().getStringArrayList(YOU_TUBE_VIDEO_TAG);
		} else {
			videos = new ArrayList<String>(0);
		}
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_you_tube_bottom_dialog, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setUpLayout(view);
	}

	private void setUpLayout(View view) {
		ButterKnife.bind(this, view);

		layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);

		YouTubeBottomDialogAdapter adapter = new YouTubeBottomDialogAdapter(videos, bottomSheetItemListener);
		recyclerView.setAdapter(adapter);
	}

	public void addOnItemClickListener(BottomSheetItemListener bottomSheetItemListener) {
		this.bottomSheetItemListener.add(bottomSheetItemListener);
	}

	public void removeOnItemClickListener(BottomSheetItemListener bottomSheetItemListener) {
		this.bottomSheetItemListener.remove(bottomSheetItemListener);
	}
}
