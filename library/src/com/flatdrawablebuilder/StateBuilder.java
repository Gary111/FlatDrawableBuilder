package com.flatdrawablebuilder;

import java.lang.reflect.Field;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.LayerDrawable;

public class StateBuilder {
	
	public enum Volume {
		NONE, TOP, BOTTOM
	}
	
	public static final String LIGHT_BLUE = "#33B5E5";
	public static final String LIGHT_VIOLET = "#AA66CC";
	public static final String LIGHT_GREEN = "#99CC00";
	public static final String LIGHT_ORANGE = "#FFBB33";
	public static final String LIGHT_RED = "#FF4444";
	public static final String LIGHT_GREY = "#606060";
	
	public static final String DARK_BLUE = "#0099CC";
	public static final String DARK_VIOLET = "#9933CC";
	public static final String DARK_GREEN = "#669900";
	public static final String DARK_ORANGE = "#FF8800";
	public static final String DARK_RED = "#CC0000";
	public static final String DARK_GREY = "#404040";
	
	private static final int TRANSPARENT = Color.argb(0, 0, 0, 0);
	
	private int mColor;
	
	private float mCornerRadius;
	
	private Volume mVolume;
	private int mVolumeSize;
	private int mVolumeColor;
	
	private Rect mPadding;
	
	private int mStrokeWidth;
	private int mStrokeColor;
	private float mStrokeDashWidth;
	private float mStrokeDashGap;
	
	private int mVolumeStrokeWidth;
	private int mVolumeStrokeColor;
	private float mVolumeStrokeDashWidth;
	private float mVolumeStrokeDashGap;
	
	private StateBuilder() {
		mColor = Color.parseColor(LIGHT_GREY);
		
		mCornerRadius = 20f;

		mVolume = Volume.BOTTOM;
		mVolumeSize = 5;
		mVolumeColor = Color.parseColor(DARK_GREY);
		
		mPadding = new Rect(10, 10, 10, 10);
		
		mStrokeWidth = 0;
		mStrokeColor = TRANSPARENT;
		mStrokeDashWidth = 0f;
		mStrokeDashGap = 0f;
		
		mVolumeStrokeWidth = 0;
		mVolumeStrokeColor = TRANSPARENT;
		mVolumeStrokeDashWidth = 0f;
		mVolumeStrokeDashGap = 0f;
	}
	
	public static StateBuilder create() {
		return new StateBuilder();
	}
	
	public StateBuilder setColor(int color) {
		mColor = color;
		return this;
	}
	
	public StateBuilder setColor(String colorString) {
		return setColor(Color.parseColor(colorString));
	}
	
	public StateBuilder setColor(int alpha, int red, int green, int blue) {
		return setColor(Color.argb(alpha, red, green, blue));
	}
	
	public StateBuilder setCornerRadius(float cornerRadius) {
		mCornerRadius = cornerRadius;
		return this;
	}
	
	public StateBuilder setVolume(Volume volume, int size, int color) {
		mVolume = volume;
		mVolumeSize = size;
		mVolumeColor = color;
		return this;
	}
	
	public StateBuilder setVolume(Volume volume, int size, String colorString) {
		return setVolume(volume, size, Color.parseColor(colorString));
	}
	
	public StateBuilder setVolume(Volume volume, int size, int alpha, int red, int green, int blue) {
		return setVolume(volume, size, Color.argb(alpha, red, green, blue));
	}
	
	public StateBuilder resetVolume() {
		return setVolume(Volume.NONE, 0, TRANSPARENT);
	}
	
	public StateBuilder setPadding(int left, int top, int right, int bottom) {
		mPadding.left = left;
		mPadding.top = top;
		mPadding.right = right;
		mPadding.bottom = bottom;
		return this;
	}
	
	public StateBuilder setPadding(int padding) {
		return setPadding(padding, padding, padding, padding);
	}
	
	public StateBuilder resetPadding() {
		return setPadding(10);
	}
	
	public StateBuilder setStroke(int width, int color, float dashWidth, float dashGap) {
		mStrokeWidth = width;
		mStrokeColor = color;
		mStrokeDashWidth = dashWidth;
		mStrokeDashGap = dashGap;
		return this;
	}
	
	public StateBuilder setStroke(int width, String colorString, float dashWidth, float dashGap) {
		return setStroke(width, Color.parseColor(colorString), dashWidth, dashGap);
	}
	
	public StateBuilder setStroke(int width, int color) {
		return setStroke(width, color, 0f, 0f);
	}
	
	public StateBuilder setStroke(int width, String colorString) {
		return setStroke(width, Color.parseColor(colorString));
	}
	
	public StateBuilder resetStroke() {
		return setStroke(0, TRANSPARENT, 0, 0);
	}
	
	public StateBuilder setVolumeStroke(int width, int color, float dashWidth, float dashGap) {
		mVolumeStrokeWidth = width;
		mVolumeStrokeColor = color;
		mVolumeStrokeDashWidth = dashWidth;
		mVolumeStrokeDashGap = dashGap;
		return this;
	}
	
	public StateBuilder setVolumeStroke(int width, String colorString, float dashWidth, float dashGap) {
		return setVolumeStroke(width, Color.parseColor(colorString), dashWidth, dashGap);
	}
	
	public StateBuilder setVolumeStroke(int width, int color) {
		return setVolumeStroke(width, color, 0f, 0f);
	}
	
	public StateBuilder setVolumeStroke(int width, String colorString) {
		return setVolumeStroke(width, Color.parseColor(colorString));
	}
	
	public StateBuilder resetVolumeStroke() {
		return setStroke(0, TRANSPARENT, 0, 0);
	}

	Drawable build() {
		GradientDrawable bottomLayer = new GradientDrawable(Orientation.TOP_BOTTOM, new int[] {mVolumeColor, mVolumeColor});
		bottomLayer.setShape(GradientDrawable.RECTANGLE);
		bottomLayer.setCornerRadius(mCornerRadius);
		bottomLayer.setBounds(40, 40, 40, 40);
		bottomLayer.setStroke(mVolumeStrokeWidth, mVolumeStrokeColor, mVolumeStrokeDashWidth, mVolumeStrokeDashGap);
		
		GradientDrawable topLayer = new GradientDrawable(Orientation.TOP_BOTTOM, new int[] {mColor, mColor});
		topLayer.setShape(GradientDrawable.RECTANGLE);
		topLayer.setCornerRadius(mCornerRadius);
		topLayer.setBounds(40, 40, 40, 40);
		topLayer.setStroke(mStrokeWidth, mStrokeColor, mStrokeDashWidth, mStrokeDashGap);
		
		try {
			Field mPaddingField = topLayer.getClass().getDeclaredField("mPadding");
			mPaddingField.setAccessible(true);
			mPaddingField.set(topLayer, mPadding);
			mPaddingField.setAccessible(false);
		} catch (Exception e) {
		}
		
		LayerDrawable drawable = new LayerDrawable(new Drawable [] { bottomLayer, topLayer });
		drawable.setLayerInset(0, 0, mVolume == Volume.BOTTOM || mVolume == Volume.NONE ? mVolumeSize : 0, 0, mVolume == Volume.TOP || mVolume == Volume.NONE ? mVolumeSize : 0);
		drawable.setLayerInset(1, 0, mVolumeSize, 0, mVolumeSize);
		drawable.setBounds(40, 40, 40, 40);
		
		return drawable;
	}

}
