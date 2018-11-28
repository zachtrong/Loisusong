package net.loisusong.android.loisusong.service.network;

import net.loisusong.android.loisusong.service.constant.Constant;
import net.loisusong.android.loisusong.service.interfaces.PostLoisusongApi;

import io.realm.Realm;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkLoisusong extends Network {
	private PostLoisusongApi postLoisusongApi;

	public NetworkLoisusong(Realm realm) {
		super(realm);
		postLoisusongApi = new Retrofit.Builder()
				.baseUrl(Constant.REST_SERVER)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.callFactory(httpClientBuilder.build())
				.build()
				.create(PostLoisusongApi.class);
	}

	public void getPosts(String typePost, int page) {
		postLoisusongApi.getPosts(page, PostTag.getTag(typePost), PostCategory.getCategory(typePost))
				.enqueue(new NetworkGetCallback(this));
	}

	public void getNewPosts(String typePost) {
		postLoisusongApi.getPosts(1, PostTag.getTag(typePost), PostCategory.getCategory(typePost))
				.enqueue(new NetworkGetNewCallback(this));
	}
}
