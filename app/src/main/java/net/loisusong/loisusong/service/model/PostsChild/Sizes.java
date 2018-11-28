package net.loisusong.loisusong.service.model.PostsChild;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by android on 1/29/18.
 */
public class Sizes extends RealmObject {

    @SerializedName("thumbnail")
    public SizeType thumbnail;

    @SerializedName("medium")
    public SizeType medium;

    @SerializedName("medium_large")
    public SizeType mediumLarge;

    @SerializedName("large")
    public SizeType large;

    @SerializedName("slider-large")
    public SizeType sliderLarge;

    @SerializedName("slider-normal")
    public SizeType sliderNormal;

    @SerializedName("feature-grid")
    public SizeType featureGrid;

    @SerializedName("small-grid")
    public SizeType smallGrid;

    @SerializedName("medium-feature")
    public SizeType mediumFeature;

    @SerializedName("small-feature")
    public SizeType smallFeature;

    @SerializedName("slider-feature")
    public SizeType sliderFeature;
}
