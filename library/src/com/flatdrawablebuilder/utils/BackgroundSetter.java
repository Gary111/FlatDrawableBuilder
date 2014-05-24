package com.flatdrawablebuilder.utils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

public class BackgroundSetter {

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public static void setBackgroundDrawable(Drawable drawable, View view) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			view.setBackground(drawable);			
		} else {
			view.setBackgroundDrawable(drawable);
		}
	}
	
}
