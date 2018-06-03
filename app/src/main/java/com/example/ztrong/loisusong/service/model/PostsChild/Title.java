package com.example.ztrong.loisusong.service.model.PostsChild;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import io.realm.RealmObject;

/**
 * Created by ztrong on 1/29/18.
 */
public class Title extends RealmObject {
    @SerializedName("rendered")
    public String rendered;
}
