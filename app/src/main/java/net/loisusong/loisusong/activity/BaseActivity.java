package net.loisusong.loisusong.activity;

import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {
	boolean doubleBackToExitPressedOnce = false;
	@Override
	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
			super.onBackPressed();
			return;
		}
		handlerDoubleBackPressed();
	}

	public void handlerDoubleBackPressed() {
		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(
				this,
				"Please click BACK again to exit",
				Toast.LENGTH_SHORT
		).show();
		new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);
	}
}
