package net.loisusong.loisusong.service.model;

import com.google.gson.annotations.SerializedName;

import net.loisusong.loisusong.service.model.PostsChild.BetterFeaturedImage;
import net.loisusong.loisusong.service.model.PostsChild.Content;
import net.loisusong.loisusong.service.model.PostsChild.Guid;
import net.loisusong.loisusong.service.model.PostsChild.Links;
import net.loisusong.loisusong.service.model.PostsChild.Title;
import net.loisusong.loisusong.service.utils.realm.RealmInt;
import net.loisusong.loisusong.service.utils.realm.RealmListParcelConverter;

import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by android on 1/28/18.
 */

public class PostsModel extends RealmObject {
    @PrimaryKey
    @SerializedName("id")
    public int id;

    @SerializedName("date")
    public String date;

    @SerializedName("date_gmt")
    public String dateGmt;

    @SerializedName("guid")
    public Guid guid;

    @SerializedName("modified")
    public String modified;

    @SerializedName("modified_gmt")
    public String modifiedGmt;

    @SerializedName("slug")
    public String slug;

    @SerializedName("status")
    public String status;

    @SerializedName("type")
    public String type;

    @SerializedName("link")
    public String link;

    @SerializedName("title")
    public Title title;

    @SerializedName("content")
    public Content content;

    @SerializedName("excerpt")
    public Content excerpt;

    @SerializedName("author")
    public int author;

    @SerializedName("featured_media")
    public int featuredMedia;

    @SerializedName("comment_status")
    public String commentStatus;

    @SerializedName("ping_status")
    public String pingStatus;

    @SerializedName("sticky")
    public boolean sticky;

    @SerializedName("template")
    public String template;

    @SerializedName("format")
    public String format;

    @SerializedName("categories")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<RealmInt> categories;

    @SerializedName("tags")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<RealmInt> tags;

    @SerializedName("better_featured_image")
    public BetterFeaturedImage betterFeaturedImage;

    @SerializedName("_links")
    public Links links;
}

