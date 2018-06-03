package com.example.ztrong.loisusong.service.interfaces;

import com.example.ztrong.loisusong.service.constant.Constant;
import com.example.ztrong.loisusong.service.model.PostsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostApi {
	@GET(Constant.REST_NEWSPAPER)
	Call<List<PostsModel>> getPosts(@Query("page") int page,
	                                   @Query("tags[]") ArrayList<String> tags,
	                                   @Query("categories[]") ArrayList<String> categories);
}
