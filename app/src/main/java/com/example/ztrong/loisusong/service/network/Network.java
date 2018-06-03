package com.example.ztrong.loisusong.service.network;

import com.example.ztrong.loisusong.service.constant.Constant;
import com.example.ztrong.loisusong.service.interfaces.PostApi;
import com.example.ztrong.loisusong.service.utils.realm.RealmInt;
import com.example.ztrong.loisusong.service.utils.realm.RealmIntListTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import retrofit2.Callback;

import io.realm.RealmList;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
	private Gson gson;
	private OkHttpClient.Builder httpClientBuilder;

	private static final Network INSTANCE = new Network();
	public static Network getInstance() {
		return INSTANCE;
	}

	private Network() {
		gson = new GsonBuilder()
				.registerTypeAdapter(new TypeToken<RealmList<RealmInt>>() {}.getType(),
						RealmIntListTypeAdapter.INSTANCE)
				.create();
		httpClientBuilder = new OkHttpClient.Builder();
	}

	public void downloadPosts(String typePost, int page, Callback callback) {
		PostApi postApi = new Retrofit.Builder()
				.baseUrl(Constant.REST_SERVER)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.callFactory(httpClientBuilder.build())
				.build()
				.create(PostApi.class);

		postApi.getPosts(page, PostTag.getTag(typePost), PostCategory.getCategory(typePost))
				.enqueue(callback);
	}
}
