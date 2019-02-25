package net.loisusong.loisusong.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.loisusong.loisusong.R;
import net.loisusong.loisusong.adapter.PostsLoisusongPagerAdapter;

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
		viewPager.setAdapter(new PostsLoisusongPagerAdapter(getChildFragmentManager()));
		tabLayout.setupWithViewPager(viewPager);
	}
}
