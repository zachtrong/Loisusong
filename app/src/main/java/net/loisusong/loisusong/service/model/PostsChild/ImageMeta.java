package net.loisusong.loisusong.service.model.PostsChild;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by android on 1/29/18.
 */
public class ImageMeta extends RealmObject {
    @SerializedName("aperture")
    public float aperture;

    @SerializedName("credit")
    public String credit;

    @SerializedName("camera")
    public String camera;

    @SerializedName("caption")
    public String caption;

    @SerializedName("created_timestamp")
    public float createdTimestamp;

    @SerializedName("copyright")
    public String copyright;

    @SerializedName("focal_length")
    public float focalLength;

    @SerializedName("iso")
    public float iso;

    @SerializedName("shutter_speed")
    public float shutterSpeed;

    @SerializedName("title")
    public String title;

    @SerializedName("orientation")
    public int orientation;
}
