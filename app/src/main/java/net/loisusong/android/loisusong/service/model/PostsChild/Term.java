package net.loisusong.android.loisusong.service.model.PostsChild;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import io.realm.RealmObject;

/**
 * Created by android on 1/29/18.
 */
public class Term extends RealmObject {
    @SerializedName("taxonomy")
    public String taxonomy;

    @SerializedName("embeddable")
    public boolean embeddable;

    @SerializedName("href")
    public String href;
}
