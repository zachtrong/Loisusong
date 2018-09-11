package com.example.ztrong.loisusong.service.network;

import com.example.ztrong.loisusong.service.constant.Constant;
import com.example.ztrong.loisusong.service.interfaces.PostApi;
import com.example.ztrong.loisusong.service.interfaces.PostNetworkStatus;
import com.example.ztrong.loisusong.service.model.PostsModel;
import com.example.ztrong.loisusong.service.utils.realm.RealmInt;
import com.example.ztrong.loisusong.service.utils.realm.RealmIntListTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;

import io.realm.RealmList;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
	private Gson gson;
	private Realm realm;
	private OkHttpClient.Builder httpClientBuilder;
	private static final int CODE_SUCCESS = 200;
	private static final int CODE_EMPTY = 400;
	private ArrayList<PostNetworkStatus> listeners = new ArrayList<>();

	public Network(Realm realm) {
		this.realm = realm;
		gson = new GsonBuilder()
				.registerTypeAdapter(new TypeToken<RealmList<RealmInt>>() {}.getType(),
						RealmIntListTypeAdapter.INSTANCE)
				.create();
		httpClientBuilder = new OkHttpClient.Builder();
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

	public void downloadPosts(String typePost, int page) {
		PostApi postApi = new Retrofit.Builder()
				.baseUrl(Constant.REST_SERVER)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.callFactory(httpClientBuilder.build())
				.build()
				.create(PostApi.class);

		postApi.getPosts(page, PostTag.getTag(typePost), PostCategory.getCategory(typePost))
				.enqueue(postApiCallback);
	}

	private Callback postApiCallback = new Callback() {
		@Override
		public void onResponse(Call call, Response response) {
			switch (response.code()) {
				case CODE_SUCCESS:
					saveReceivedPosts((ArrayList<PostsModel>) response.body());
					notifySuccess();
					break;
				case CODE_EMPTY:
					notifyEmpty();
					break;
				default: //error
					notifyError();
					break;
			}
		}

		private void saveReceivedPosts(ArrayList<PostsModel> postsModels) {
			realm.beginTransaction();
			realm.copyToRealmOrUpdate(postsModels);
			realm.commitTransaction();
		}

		private void notifySuccess() {
			for (PostNetworkStatus listener : listeners) {
				listener.onPosts();
			}
		}

		private void notifyEmpty() {
			for (PostNetworkStatus listener : listeners) {
				listener.onEmpty();
			}
		}

		private void notifyError() {
			for (PostNetworkStatus listener : listeners) {
				listener.onError();
			}
		}

		@Override
		public void onFailure(Call call, Throwable t) {
			notifyError();
		}
	};
}
