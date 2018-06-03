package com.example.ztrong.loisusong.service.model.PostsChild;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.RealmObject;

/**
 * Created by ztrong on 1/29/18.
 */

public class BetterFeaturedImage extends RealmObject {

    @SerializedName("id")
    public int id;

    @SerializedName("alt_text")
    public String altText;

    @SerializedName("caption")
    public String caption;

    @SerializedName("description")
    public String description;

    @SerializedName("media_type")
    public String mediaType;

    @SerializedName("media_details")
    public MediaDetails mediaDetails;

    @SerializedName("post")
    public int post;

    @SerializedName("source_url")
    public String sourceUrl;
}
