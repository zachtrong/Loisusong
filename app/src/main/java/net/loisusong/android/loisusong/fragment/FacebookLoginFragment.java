package net.loisusong.android.loisusong.fragment;

import android.content.Intent;
import android.media.FaceDetector;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.loisusong.android.loisusong.R;
import net.loisusong.android.loisusong.service.interfaces.FacebookLogin;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

public class FacebookLoginFragment extends Fragment {
	private static final ArrayList<String> permissions = new ArrayList<String>() {{
		add("email");
		add("public_profile");
	}};

	private CallbackManager callbackManager;
	private AccessTokenTracker accessTokenTracker;
	private LoginManager loginManager;
	private FacebookLogin facebookLoginListener;

	@BindView(R.id.fb_facebook_login)
	FancyButton fancyButton;

	public void setFacebookLoginListener(FacebookLogin facebookLoginListener) {
		this.facebookLoginListener = facebookLoginListener;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_facebook_login, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);

		setUpFacebook();
		updateFacebookLoginUI();

		fancyButton.setOnClickListener(v -> handleFacebookLogin());
	}

	private void setUpFacebook() {
		accessTokenTracker = new AccessTokenTracker() {
			@Override
			protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
				updateFacebookLoginUI();
			}
		};

		loginManager = LoginManager.getInstance();
		callbackManager = CallbackManager.Factory.create();

		loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
			@Override
			public void onSuccess(LoginResult loginResult) {
				updateFacebookLoginUI();
				if (facebookLoginListener != null) {
					facebookLoginListener.onFacebookLogin();
				}
			}

			@Override
			public void onCancel() {

			}

			@Override
			public void onError(FacebookException error) {

			}
		});
	}

	private void updateFacebookLoginUI() {
		if (AccessToken.getCurrentAccessToken() == null
				|| AccessToken.getCurrentAccessToken().isExpired()) {
			fancyButton.setText("Facebook Connect");
		} else {
			fancyButton.setText("Logout");
		}
	}

	private void handleFacebookLogin() {
		if (AccessToken.getCurrentAccessToken() != null) {
			loginManager.logOut();
		} else {
			accessTokenTracker.startTracking();
			loginManager.logInWithReadPermissions(this, permissions);
		}
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		callbackManager.onActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}
}
