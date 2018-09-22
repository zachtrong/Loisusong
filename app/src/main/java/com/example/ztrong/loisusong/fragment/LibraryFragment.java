package com.example.ztrong.loisusong.fragment;

import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.ztrong.loisusong.R;
import com.example.ztrong.loisusong.fragment.PostFragments.PostFragment;
import com.example.ztrong.loisusong.service.constant.Constant;

import butterknife.BindView;

public class LibraryFragment extends BaseFragment {
	@BindView(R.id.tb_library)
	Toolbar toolbar;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_library, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		super.setUpCommon(view);
		super.setUpToolbar(toolbar);
		replaceFragment(PostFragment.newInstance(Constant.POST_MEDIA));
	}

	private void replaceFragment(Fragment fragment) {
		getChildFragmentManager().beginTransaction()
				.replace(R.id.fl_library, fragment)
				.commit();
	}
}
