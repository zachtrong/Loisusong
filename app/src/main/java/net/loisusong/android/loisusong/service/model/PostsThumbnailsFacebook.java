package net.loisusong.android.loisusong.service.model;

import com.google.gson.annotations.SerializedName;

import net.loisusong.android.loisusong.service.utils.realm.RealmListParcelConverter;

import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by android on 2/20/18.
 */

public class PostsThumbnailsFacebook extends RealmObject {
    @SerializedName("data")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<ThumbnailsDataFacebook> data;
}
