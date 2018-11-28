package net.loisusong.loisusong.service.model;

import com.google.gson.annotations.SerializedName;

import net.loisusong.loisusong.service.utils.realm.RealmListParcelConverter;

import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by android on 2/3/18.
 */

public class PostsModelFacebook extends RealmObject {
    @SerializedName("data")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<PostsDataFacebook> data;
}
