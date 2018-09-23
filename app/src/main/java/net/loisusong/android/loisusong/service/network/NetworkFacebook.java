package net.loisusong.android.loisusong.service.network;

import net.loisusong.android.loisusong.service.constant.Constant;
import net.loisusong.android.loisusong.service.interfaces.PostFacebookApi;
import net.loisusong.android.loisusong.service.interfaces.PostLoisusongApi;
import com.facebook.AccessToken;

import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkFacebook extends Network {
	private PostFacebookApi postFacebookApi;

	public NetworkFacebook(Realm realm) {
		super(realm);
		postFacebookApi = new Retrofit.Builder()
				.baseUrl(Constant.FACEBOOK_GRAPH)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.callFactory(httpClientBuilder.build())
				.build()
				.create(PostFacebookApi.class);
	}

	public void getNewPosts(String typePost) {
		postFacebookApi.getFacebookVideos(Constant.LOI_SU_SONG_NET_PAGE_ID,
				AccessToken.getCurrentAccessToken().getToken())
				.enqueue(new NetworkGetNewCallback(this));
	}
}
