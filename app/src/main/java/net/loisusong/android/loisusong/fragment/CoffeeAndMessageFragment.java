package net.loisusong.android.loisusong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import net.loisusong.android.loisusong.R;
import net.loisusong.android.loisusong.adapter.PostsFacebookPagerAdapter;
import net.loisusong.android.loisusong.fragment.PostFragments.PostCoffeeFragment;
import net.loisusong.android.loisusong.service.interfaces.FacebookLogin;
import com.facebook.AccessToken;

import butterknife.BindView;

public class CoffeeAndMessageFragment extends BaseFragment
		implements FacebookLogin {
	@BindView(R.id.tb_coffee_and_message)
	Toolbar toolbar;
	@BindView(R.id.vp_coffee_and_message)
	ViewPager viewPager;
	@BindView(R.id.tl_coffee_and_message)
	TabLayout tabLayout;
	@BindView(R.id.fl_coffee_and_message)
	FrameLayout frameLayout;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_coffee_and_message, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		super.setUpCommon(view);
		super.setUpToolbar(toolbar);
		setUpFragment();
	}

	private void setUpFragment() {
		if (AccessToken.getCurrentAccessToken() != null) {
			setUpViewPager();
		} else {
			FacebookLoginFragment facebookLoginFragment = new FacebookLoginFragment();
			facebookLoginFragment.setFacebookLoginListener(this);
			frameLayout.setVisibility(View.VISIBLE);
			super.replaceFragment(R.id.fl_coffee_and_message, facebookLoginFragment);
		}
	}

	private void setUpViewPager() {
		viewPager.setAdapter(new PostsFacebookPagerAdapter(getChildFragmentManager()));
		tabLayout.setupWithViewPager(viewPager);
	}

	@Override
	public void onFacebookLogin() {
		frameLayout.setVisibility(View.GONE);
		setUpViewPager();
	}
}
