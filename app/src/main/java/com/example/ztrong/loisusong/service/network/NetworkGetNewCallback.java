package com.example.ztrong.loisusong.service.network;

import com.example.ztrong.loisusong.service.model.PostsModel;

import java.util.ArrayList;

public class NetworkGetNewCallback extends NetworkGetCallback {
	NetworkGetNewCallback(Network network) {
		super(network);
	}

	@Override
	protected void saveReceivedPosts(ArrayList<PostsModel> postsModels) {
		network.getRealm().beginTransaction();
		network.getRealm().deleteAll();
		network.getRealm().copyToRealmOrUpdate(postsModels);
		network.getRealm().commitTransaction();
	}
}
