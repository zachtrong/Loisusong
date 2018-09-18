package com.example.ztrong.loisusong.service.network;

import com.example.ztrong.loisusong.service.constant.Constant;
import com.example.ztrong.loisusong.service.interfaces.PostApi;
import com.example.ztrong.loisusong.service.interfaces.PostNetworkStatus;
import com.example.ztrong.loisusong.service.utils.realm.RealmInt;
import com.example.ztrong.loisusong.service.utils.realm.RealmIntListTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import io.realm.Realm;
import retrofit2.Callback;

import io.realm.RealmList;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
	private boolean isLoading = false;
	private Gson gson;
	private Realm realm;
	private OkHttpClient.Builder httpClientBuilder;
	private ArrayList<PostNetworkStatus> listeners = new ArrayList<>();

	public Network(Realm realm) {
		this.realm = realm;
		gson = new GsonBuilder()
				.registerTypeAdapter(new TypeToken<RealmList<RealmInt>>() {}.getType(),
						RealmIntListTypeAdapter.INSTANCE)
				.create();
		httpClientBuilder = new OkHttpClient.Builder();
	}

	public Realm getRealm() {
		return realm;
	}

	public void getPosts(String typePost, int page) {
		if (!isLoading) {
			isLoading = true;
			PostApi postApi = new Retrofit.Builder()
					.baseUrl(Constant.REST_SERVER)
					.addConverterFactory(GsonConverterFactory.create(gson))
					.callFactory(httpClientBuilder.build())
					.build()
					.create(PostApi.class);

			postApi.getPosts(page, PostTag.getTag(typePost), PostCategory.getCategory(typePost))
					.enqueue(getPostsCallback);
		}
	}

	private Callback getPostsCallback = new NetworkGetCallback(this);

	protected void notifySuccess() {
		for (PostNetworkStatus listener : listeners) {
			listener.onPosts();
		}
	}

	protected void notifyEmpty() {
		for (PostNetworkStatus listener : listeners) {
			listener.onEmpty();
		}
	}

	protected void notifyError() {
		for (PostNetworkStatus listener : listeners) {
			listener.onError();
		}
	}

	public void getNewPosts(String typePost) {
		PostApi postApi = new Retrofit.Builder()
				.baseUrl(Constant.REST_SERVER)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.callFactory(httpClientBuilder.build())
				.build()
				.create(PostApi.class);

		postApi.getPosts(1, PostTag.getTag(typePost), PostCategory.getCategory(typePost))
				.enqueue(getNewPostsCallback);
	}

	private Callback getNewPostsCallback = new NetworkGetNewCallback(this);

	protected void setLoading(boolean isLoading) {
		this.isLoading = isLoading;
	}

	public void addListener(PostNetworkStatus listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public void removeListener(PostNetworkStatus listener) {
		if (listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}
}
