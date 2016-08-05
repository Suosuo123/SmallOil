package com.hh.oil.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.hh.oil.utils.BitmapTools;

public class RoundImageView extends ImageView {

	public RoundImageView(Context context) {
		super(context);

	}

	public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RoundImageView(Context context, AttributeSet attributeSet, int defStyle) {
		super(context, attributeSet, defStyle);

	}

	@Override
	public void setImageBitmap(Bitmap bm) {
		super.setImageBitmap(BitmapTools.toRoundBitmap(bm));
	}

}
