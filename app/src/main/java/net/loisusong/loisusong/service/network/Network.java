package net.loisusong.loisusong.service.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.loisusong.loisusong.service.interfaces.PostNetworkStatus;
import net.loisusong.loisusong.service.utils.realm.RealmInt;
import net.loisusong.loisusong.service.utils.realm.RealmIntListTypeAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import okhttp3.OkHttpClient;

public abstract class Network {
	protected Gson gson;
	protected Realm realm;
	protected OkHttpClient.Builder httpClientBuilder;
	protected ArrayList<PostNetworkStatus> listeners = new ArrayList<>();

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

	protected void notifyNewPosts() {
		for (PostNetworkStatus listener : listeners) {
			listener.onNewPosts();
		}
	}

	protected void notifyPosts() {
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

	public abstract void getNewPosts(String typePost);
}
