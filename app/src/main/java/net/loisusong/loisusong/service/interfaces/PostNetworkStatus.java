package net.loisusong.loisusong.service.interfaces;

public interface PostNetworkStatus {
	void onNewPosts();
	void onPosts();
	void onEmpty();
	void onError();
}
