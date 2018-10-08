package net.loisusong.android.loisusong.service.network;

import net.loisusong.android.loisusong.service.model.PostsModel;

import java.util.ArrayList;

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
