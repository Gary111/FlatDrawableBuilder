package com.flatdrawablebuilder;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.DisplayMetrics;

import com.flatdrawablebuilder.StateBuilder.Volume;

@SuppressLint("InlinedApi")
public class DrawableBuilder {

	public enum State {
		DEFAULT,
		
		PRESSED(android.R.attr.state_pressed),
		UNPRESSED(-android.R.attr.state_pressed),
		
		ENABLED(android.R.attr.state_enabled),
		DISABLED(-android.R.attr.state_enabled),
		
		SELECTED(android.R.attr.state_selected),
		UNSELECTED(-android.R.attr.state_selected),
		
		CHECKED(android.R.attr.state_checked),
		UNCHECKED(-android.R.attr.state_checked),
		
		ACTIVATED(isHoneycombOrHigher() ? android.R.attr.state_activated : android.R.attr.state_checked),
		DEACTIVATED(isHoneycombOrHigher() ? -android.R.attr.state_activated : -android.R.attr.state_checked),
		
		FOCUSED(android.R.attr.state_focused),
		UNFOCUSED(-android.R.attr.state_focused);

		private final int[] mIds;

		private State(int id) {
			mIds = new int[] { id };
		}

		private State() {
			mIds = new int[] {};
		}
		
		private static boolean isHoneycombOrHigher() {
			return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
		}
	}
	
	private enum Color {
		BLUE(StateBuilder.LIGHT_BLUE, StateBuilder.DARK_BLUE),
		VIOLET(StateBuilder.LIGHT_VIOLET, StateBuilder.DARK_VIOLET),
		GREEN(StateBuilder.LIGHT_GREEN, StateBuilder.DARK_GREEN),
		ORANGE(StateBuilder.LIGHT_ORANGE, StateBuilder.DARK_ORANGE),
		RED(StateBuilder.LIGHT_RED, StateBuilder.DARK_RED),
		GREY(StateBuilder.LIGHT_GREY, StateBuilder.DARK_GREY);
		
		private final String mMainColor;
		private final String mVolumeColor;
		
		private Color(String mainColor, String shadowColor) {
			mMainColor = mainColor;
			mVolumeColor = shadowColor;
		}
	}
	
	public static final Drawable DEFAULT_BLUE = create(Color.BLUE).build();
	public static final Drawable DEFAULT_VIOLET = create(Color.VIOLET).build();
	public static final Drawable DEFAULT_GREEN = create(Color.GREEN).build();
	public static final Drawable DEFAULT_ORANGE = create(Color.ORANGE).build();
	public static final Drawable DEFAULT_RED = create(Color.RED).build();
	public static final Drawable DEFAULT_GREY = create(Color.GREY).build();
	
	private final Map<State, StateBuilder> mStates;

	private DrawableBuilder(StateBuilder dafaultStateBuilder) {
		mStates = new HashMap<DrawableBuilder.State, StateBuilder>();
		
		mStates.put(State.DEFAULT, dafaultStateBuilder);
	}
	
	public static DrawableBuilder create(StateBuilder defaultStateBuilder) {
		return new DrawableBuilder(defaultStateBuilder);
	}
	
	private static DrawableBuilder create(Color color) {
		return create(color, 3, 2, 8);
	}
	
	private static DrawableBuilder create(Color color, int radius, int volumeSize, int padding) {
		return DrawableBuilder.create(StateBuilder.create()
				.setColor(color.mMainColor)
				.setCornerRadius(getPxByDp(radius))
				.setVolume(Volume.BOTTOM, getPxByDp(volumeSize), color.mVolumeColor)
				.setPadding(getPxByDp(padding))
				.setStroke(2, color.mVolumeColor))
		.addState(State.PRESSED, StateBuilder.create()
				.setColor(color.mMainColor)
				.setCornerRadius(getPxByDp(radius))
				.setVolume(Volume.NONE, getPxByDp(volumeSize), color.mVolumeColor)
				.setPadding(getPxByDp(padding))
				.setStroke(2, color.mVolumeColor));
	}
	
	private static int getPxByDp(int dp) {
		return dp * (Resources.getSystem().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
	}
	
	public DrawableBuilder addState(State state, StateBuilder stateBuilder) {
		mStates.put(state, stateBuilder);
		return this;
	}
	
	public Drawable build() {
		StateListDrawable drawable = new StateListDrawable();
		
		for (State state : mStates.keySet()) {
			drawable.addState(state.mIds, mStates.get(state).build());
		}
		
		return drawable;
	}

}
