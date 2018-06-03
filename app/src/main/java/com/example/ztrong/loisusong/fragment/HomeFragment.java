package com.example.ztrong.loisusong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.ztrong.loisusong.R;
import com.example.ztrong.loisusong.adapter.PostsPagerAdapter;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

	@BindView(R.id.tb_home)
	Toolbar toolbar;
	@BindView(R.id.vp_home)
	ViewPager viewPager;
	@BindView(R.id.tl_home)
	TabLayout tabLayout;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		super.setUpCommon(view);
		super.setUpToolbar(toolbar);
		setUpViewPager();
	}

	private void setUpViewPager() {
		viewPager.setAdapter(new PostsPagerAdapter(getChildFragmentManager(), getContext()));
		tabLayout.setupWithViewPager(viewPager);
	}
}
