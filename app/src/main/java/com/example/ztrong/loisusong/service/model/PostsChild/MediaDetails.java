package com.example.ztrong.loisusong.service.model.PostsChild;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import io.realm.RealmObject;

/**
 * Created by ztrong on 1/29/18.
 */
public class MediaDetails extends RealmObject {

    @SerializedName("width")
    public int width;

    @SerializedName("height")
    public int height;

    @SerializedName("file")
    public String file;

    @SerializedName("sizes")
    public Sizes sizes;

    @SerializedName("image_meta")
    public ImageMeta imageMeta;
}
