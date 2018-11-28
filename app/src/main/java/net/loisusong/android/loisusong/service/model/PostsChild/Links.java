package net.loisusong.android.loisusong.service.model.PostsChild;

import com.google.gson.annotations.SerializedName;

import net.loisusong.android.loisusong.service.utils.realm.RealmListParcelConverter;

import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by android on 1/29/18.
 */
public class Links extends RealmObject {

    @SerializedName("self")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<Info> self;

    @SerializedName("collection")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<Info> collection;

    @SerializedName("about")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<Info> about;

    @SerializedName("author")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<InfoDetail> author;

    @SerializedName("replies")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<InfoDetail> Replies;

    @SerializedName("version-history")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<Info> vertionHistory;

    @SerializedName("wp:featuredmedia")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<InfoDetail> featuredmedia;

    @SerializedName("wp:attachment")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<Info> attachment;

    @SerializedName("wp:term")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<Term> term;

    @SerializedName("wp:curies")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<Curies> curies;
}
