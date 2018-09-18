package com.example.ztrong.loisusong.service.network;

import com.example.ztrong.loisusong.service.model.PostsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkGetCallback implements Callback {
	private static final int CODE_SUCCESS = 200;
	private static final int CODE_EMPTY = 400;
	Network network;

	NetworkGetCallback(Network network) {
		this.network = network;
	}

	@Override
	public void onResponse(Call call, Response response) {
		switch (response.code()) {
			case CODE_SUCCESS:
				saveReceivedPosts((ArrayList<PostsModel>) response.body());
				network.notifySuccess();
				break;
			case CODE_EMPTY:
				network.notifyEmpty();
				break;
			default: //error
				network.notifyError();
				break;
		}
	}

	protected void saveReceivedPosts(ArrayList<PostsModel> postsModels) {
		network.getRealm().beginTransaction();
		network.getRealm().copyToRealmOrUpdate(postsModels);
		network.getRealm().commitTransaction();
	}

	@Override
	public void onFailure(Call call, Throwable t) {
		onFinish();
		network.notifyError();
	}

	private void onFinish() {
		network.setLoading(false);
	}
}
