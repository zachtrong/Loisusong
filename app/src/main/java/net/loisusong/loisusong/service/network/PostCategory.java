package net.loisusong.loisusong.service.network;

import net.loisusong.loisusong.service.constant.Constant;

import java.util.ArrayList;

public final class PostCategory {
	private static final ArrayList<String> postVietNam = new ArrayList<String>() {{
		add(Constant.CATEGORY_NEWS_VIETNAM_1);
		add(Constant.CATEGORY_NEWS_VIETNAM_2);
		add(Constant.CATEGORY_NEWS_VIETNAM_3);
		add(Constant.CATEGORY_NEWS_VIETNAM_4);
		add(Constant.CATEGORY_NEWS_VIETNAM_5);
	}};

	private static final ArrayList<String> postInternational = new ArrayList<String>() {{
		add(Constant.CATEGORY_NEWS_INTERNATIONAL_1);
		add(Constant.CATEGORY_NEWS_INTERNATIONAL_2);
		add(Constant.CATEGORY_NEWS_INTERNATIONAL_3);
		add(Constant.CATEGORY_NEWS_INTERNATIONAL_4);
	}};

	private static final ArrayList<String> postCoffee = new ArrayList<String>() {{
		add(Constant.CATEGORY_NIGHT_COFFEE);
	}};

	public static ArrayList<String> getCategory(String type) {
		switch (type) {
			case Constant.POST_VIETNAM:
				return postVietNam;
			case Constant.POST_INTERNATIONAL:
				return postInternational;
			case Constant.POST_COFFEE:
				return postCoffee;
			default:
				return null;
		}
	}
}

