package net.loisusong.loisusong.service.model.PostsChild;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by android on 1/29/18.
 */
public class Content extends RealmObject {
    @SerializedName("rendered")
    public String rendered;

    @SerializedName("protected")
    public boolean isProtected;
}
