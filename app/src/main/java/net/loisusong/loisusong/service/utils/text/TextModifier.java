package net.loisusong.loisusong.service.utils.text;

public class TextModifier {
	public static String beautifyDate(String date) {
		return date.substring(8, 10)
				+ '/' + date.substring(5, 7)
				+ '/' + date.substring(0, 4);
	}
}
