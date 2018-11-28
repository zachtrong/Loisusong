package net.loisusong.loisusong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.loisusong.loisusong.R;
import net.loisusong.loisusong.service.constant.Constant;

import butterknife.BindView;

public class AboutFragment extends BaseFragment {
	@BindView(R.id.tb_about)
	Toolbar toolbar;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_about, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		super.setUpCommon(view);
		super.setUpToolbar(toolbar);
		super.replacePostFragment(R.id.fl_about, Constant.POST_ABOUT_US);
	}
}