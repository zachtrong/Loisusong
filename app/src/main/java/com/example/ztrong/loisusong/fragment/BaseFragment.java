package com.example.ztrong.loisusong.fragment;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ztrong.loisusong.activity.MainActivity;

import butterknife.ButterKnife;

public class BaseFragment extends Fragment {
	private MainActivity activity;
	private ActionBarDrawerToggle mToggle = null;

	@Nullable
	public MainActivity getMainActivity() {
		return activity;
	}

	private void setMainActivity(Activity activity) {
		this.activity = (MainActivity) activity;
	}

	protected void setUpCommon(@NonNull View childView) {
		ButterKnife.bind(this, childView);
		setMainActivity(getActivity());
	}

	protected void setUpToolbar(@NonNull Toolbar toolbar) {
		getMainActivity().setSupportActionBar(toolbar);
		mToggle = getMainActivity().registerDrawerToggle(toolbar);
	}

	@Override
	public void onDestroyView() {
		if (mToggle != null) {
			getMainActivity().unRegisterDrawerToggle(mToggle);
		}
		super.onDestroyView();
	}
}
