package com.example.ztrong.loisusong.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ztrong.loisusong.R;
import com.example.ztrong.loisusong.fragment.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

	private static final int DELAY_FRAGMENT_MS = 500;

	@BindView(R.id.drawer_layout)
	DrawerLayout drawerLayout;
	@BindView(R.id.nv_main)
	NavigationView navigationView;

	android.support.v4.app.FragmentManager fragmentManager;
	private boolean isFirstStart = true;

	public static void open(Context context) {
		context.startActivity(new Intent(context, MainActivity.class));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setUpLayout();
		setUpFragment();
		setUpNavigation();
	}

	void setUpLayout() {
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}

	void setUpFragment() {
		fragmentManager = getSupportFragmentManager();
	}

	void setUpNavigation() {
		navigationView.setNavigationItemSelectedListener(this);
		onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_home));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	boolean isActionSettingItem(MenuItem item) {
		int id = item.getItemId();
		return id == R.id.action_settings;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (isActionSettingItem(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private Fragment getFragmentFromMenuItem(@NonNull MenuItem item) {
		int id = item.getItemId();
		switch (id) {
			case R.id.nav_home:
				return new HomeFragment();
			default:
				return new HomeFragment();
		}
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		Fragment fragment = getFragmentFromMenuItem(item);
		if (isFirstStart) {
			isFirstStart = false;
			startFragmentInstant(fragment);
		} else {
			startFragmentDelayed(fragment);
		}
		closeDrawer();
		return false;
	}

	private void startFragmentInstant(Fragment fragment) {
		replayFragment(fragment);
	}

	private void replayFragment(Fragment fragment) {
		fragmentManager.beginTransaction()
				.replace(R.id.fl_main_content, fragment)
				.commit();
	}

	private void startFragmentDelayed(Fragment fragment) {
		final Handler handler = new Handler();
		handler.postDelayed(() -> {
			replayFragment(fragment);
		}, DELAY_FRAGMENT_MS);
	}

	private void closeDrawer() {
		drawerLayout.closeDrawers();
	}

	public ActionBarDrawerToggle registerDrawerToggle(Toolbar toolbar) {
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this,
				drawerLayout,
				toolbar,
				R.string.navigation_drawer_close,
				R.string.navigation_drawer_close
		);
		drawerLayout.addDrawerListener(toggle);
		toggle.syncState();
		return toggle;
	}

	public void unRegisterDrawerToggle(ActionBarDrawerToggle drawerToggle) {
		drawerLayout.removeDrawerListener(drawerToggle);
	}
}
