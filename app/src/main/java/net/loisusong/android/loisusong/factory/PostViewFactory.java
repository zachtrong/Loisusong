package net.loisusong.android.loisusong.factory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import net.loisusong.android.loisusong.activity.PostLoisusongActivity;
import net.loisusong.android.loisusong.service.constant.Constant;

public class PostViewFactory {
	public static Intent createActivityIntent(Context context, String typePost) {
		Intent i;
		switch (typePost) {
			case Constant.POST_ALL:
				i = new Intent(context, PostLoisusongActivity.class);
				break;
			case Constant.POST_INTERNATIONAL:
				i = new Intent(context, PostLoisusongActivity.class);
				break;
			case Constant.POST_VIETNAM:
				i = new Intent(context, PostLoisusongActivity.class);
				break;
			case Constant.POST_ABOUT_US:
				i = new Intent(context, PostLoisusongActivity.class);
				break;
			default:
				i = new Intent(context, PostLoisusongActivity.class);
				// TODO
				break;
		}
		return i;
	}
}
