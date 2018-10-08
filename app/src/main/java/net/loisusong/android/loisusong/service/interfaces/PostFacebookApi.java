package net.loisusong.android.loisusong.service.interfaces;

import net.loisusong.android.loisusong.service.model.PostsModelFacebook;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostFacebookApi {
	@GET("{page_id}/videos?fields=id,universal_video_id," +
			"title,description,source,picture," +
			"created_time,updated_time,format&limit=1000")
	Call<PostsModelFacebook> getFacebookVideos(@Path("page_id") String pageId,
											   @Query("access_token") String accessToken);
}
