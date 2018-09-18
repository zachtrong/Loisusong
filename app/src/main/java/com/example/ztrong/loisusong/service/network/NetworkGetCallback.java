package com.example.ztrong.loisusong.service.network;

import com.example.ztrong.loisusong.service.model.PostsModel;

import java.util.ArrayList;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkGetCallback implements Callback {
	private static final int CODE_SUCCESS = 200;
	private static final int CODE_EMPTY = 400;
	Network network;
	Realm realm;

	NetworkGetCallback(Network network) {
		this.network = network;
		this.realm = network.getRealm();
	}

	@Override
	public void onResponse(Call call, Response response) {
		switch (response.code()) {
			case CODE_SUCCESS:
				if (appendPostsToExistedPosts((ArrayList<PostsModel>) response.body())) {
					network.notifyPosts();
				} else {
					network.notifyNewPosts();
				}
				break;
			case CODE_EMPTY:
				network.notifyEmpty();
				break;
			default: //error
				network.notifyError();
				break;
		}
	}

	protected boolean appendPostsToExistedPosts(ArrayList<PostsModel> postsModels) {
		realm.beginTransaction();
		realm.copyToRealmOrUpdate(postsModels);
		realm.commitTransaction();
		return true;
	}

	@Override
	public void onFailure(Call call, Throwable t) {
		network.notifyError();
	}
}
