package com.example.ztrong.loisusong.service.model;

import com.google.gson.annotations.SerializedName;

import com.example.ztrong.loisusong.service.utils.realm.RealmListParcelConverter;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by ztrong on 2/3/18.
 */

public class PostsModelFacebook extends RealmObject {
    @SerializedName("data")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<PostsDataFacebook> data;
}
