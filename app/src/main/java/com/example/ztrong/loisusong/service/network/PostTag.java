package com.example.ztrong.loisusong.service.network;

import com.example.ztrong.loisusong.service.constant.Constant;

import java.util.ArrayList;

public final class PostTag {
	private static final ArrayList<String> postAll = new ArrayList<String>() {{
		add(Constant.TAG_MOBILE_NEWS);
	}};
	private static final ArrayList<String> postVietNam = new ArrayList<String>() {{
		add(Constant.TAG_MOBILE_NEWS);
	}};
	private static final ArrayList<String> postInternaltional = new ArrayList<String>() {{
		add(Constant.TAG_MOBILE_NEWS);
	}};
	private static final ArrayList<String> postAboutUs = new ArrayList<String>() {{
		add(Constant.TAG_MOBILE_ABOUT_US);
	}};
	private static final ArrayList<String> postMedia = new ArrayList<String>() {{
		add(Constant.TAG_MOBILE_MEDIA);
	}};

	public static ArrayList<String> getTag(String type) {
		switch (type) {
			case Constant.POST_ALL:
				return postAll;
			case Constant.POST_VIETNAM:
				return postVietNam;
			case Constant.POST_INTERNATIONAL:
				return postInternaltional;
			case Constant.POST_MEDIA:
				return postMedia;
			case Constant.POST_ABOUT_US:
				return postAboutUs;
			default:
				return null;
		}
	}
}
