package net.loisusong.loisusong.service.model;

import com.google.gson.annotations.SerializedName;

import net.loisusong.loisusong.service.utils.realm.RealmListParcelConverter;

import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by android on 2/11/18.
 */

public class PostsDataFacebook extends RealmObject {
    @PrimaryKey
    @SerializedName("id")
    public String videoId;

    @SerializedName("universal_video_id")
    public String universalVideoId;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String videoDescription;

    @SerializedName("source")
    public String sourceUrl;

    @SerializedName("picture")
    public String pictureUrl;

    @SerializedName("created_time")
    public String createdTime;

    @SerializedName("updated_time")
    public String updatedTime;

    @SerializedName("format")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<PostsFormatFacebook> format;
}
