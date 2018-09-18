package com.example.ztrong.loisusong.service.network;

import com.example.ztrong.loisusong.service.model.PostsModel;
import com.example.ztrong.loisusong.service.utils.LogUtils;

import java.util.ArrayList;

import io.realm.Realm;

public class NetworkGetNewCallback extends NetworkGetCallback {
	NetworkGetNewCallback(Network network) {
		super(network);
	}

	@Override
	protected boolean appendPostsToExistedPosts(ArrayList<PostsModel> postsModels) {
		boolean isClearedDatabase = false;
		realm.beginTransaction();
		if (!isContainLastPost(postsModels)) {
			realm.deleteAll();
			isClearedDatabase = true;
		}
		realm.copyToRealmOrUpdate(postsModels);
		realm.commitTransaction();
		return !isClearedDatabase;
	}

	private boolean isContainLastPost(ArrayList<PostsModel> postsModels) {
		int postsModelsSize = postsModels.size();
		if (postsModelsSize > 0) {
			int lastPostId = postsModels.get(postsModelsSize - 1).id;
			return realm.where(PostsModel.class)
					.equalTo("id", lastPostId)
					.findFirst() != null;
		}
		return false;
	}
}
