package net.loisusong.android.loisusong.service.model.PostsChild;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.RealmObject;

/**
 * Created by android on 1/29/18.
 */
public class SizeType extends RealmObject {
    @SerializedName("file")
    public String file;

    @SerializedName("width")
    public int width;

    @SerializedName("height")
    public int height;

    @SerializedName("mime-type")
    public String mimeType;

    @SerializedName("source_url")
    public String sourceUrl;
}
