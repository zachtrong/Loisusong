package net.loisusong.android.loisusong.activity;

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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import net.loisusong.android.loisusong.R;
import net.loisusong.android.loisusong.fragment.AboutFragment;
import net.loisusong.android.loisusong.fragment.CoffeeAndMessageFragment;
import net.loisusong.android.loisusong.fragment.HomeFragment;
import net.loisusong.android.loisusong.fragment.LibraryFragment;

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


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
			case R.id.action_bar_right:
				onHandleDrawerLayout();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void onHandleDrawerLayout() {
		if (drawerLayout.isDrawerOpen(Gravity.END)) {
			drawerLayout.closeDrawer(Gravity.END);
		} else {
			drawerLayout.openDrawer(Gravity.END);
		}
	}

	private Fragment getFragmentFromMenuItem(@NonNull MenuItem item) {
		int id = item.getItemId();
		switch (id) {
			case R.id.nav_home:
				return new HomeFragment();
			case R.id.nav_lib:
				return new LibraryFragment();
			case R.id.nav_cafe:
				return new CoffeeAndMessageFragment();
			case R.id.nav_about:
				return new AboutFragment();
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
		return true;
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
				R.string.navigation_drawer_open,
				R.string.navigation_drawer_close
		);
		drawerLayout.addDrawerListener(toggle);
		toggle.syncState();
		toggle.setDrawerIndicatorEnabled(false);
		toolbar.setNavigationOnClickListener(v -> onHandleDrawerLayout());
		return toggle;
	}

	public void unRegisterDrawerToggle(ActionBarDrawerToggle drawerToggle) {
		drawerLayout.removeDrawerListener(drawerToggle);
	}
}
