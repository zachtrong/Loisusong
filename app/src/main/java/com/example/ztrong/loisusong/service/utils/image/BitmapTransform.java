package com.example.ztrong.loisusong.service.utils.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.squareup.picasso.Transformation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class BitmapTransform implements Transformation {
	private int width, height;
	public static final int MAX_DIMEN = 500;

	@Override
	public Bitmap transform(Bitmap source) {
		width = source.getWidth();
		height = source.getHeight();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		source.compress(Bitmap.CompressFormat.JPEG, 10, bos);
		Bitmap smallBitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(bos.toByteArray()));
		if (smallBitmap != source) {
			source.recycle();
		}
		return smallBitmap;
	}

	@Override
	public String key() {
		return String.valueOf(width) + 'x' + String.valueOf(height);
	}
}
