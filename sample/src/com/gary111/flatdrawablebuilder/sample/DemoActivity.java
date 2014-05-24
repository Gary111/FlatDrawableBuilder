package com.gary111.flatdrawablebuilder.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.flatdrawablebuilder.DrawableBuilder;
import com.flatdrawablebuilder.utils.BackgroundSetter;

public class DemoActivity extends Activity {

	private Button buttonBlue;
	private Button buttonViolet;
	private Button buttonGreen;
	private Button buttonOrange;
	private Button buttonRed;
	private Button buttonGrey;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);
		
		buttonBlue = (Button) findViewById(R.id.buttonBlue);
		buttonViolet = (Button) findViewById(R.id.buttonViolet);
		buttonGreen = (Button) findViewById(R.id.buttonGreen);
		buttonOrange = (Button) findViewById(R.id.buttonOrange);
		buttonRed = (Button) findViewById(R.id.buttonRed);
		buttonGrey = (Button) findViewById(R.id.buttonGrey);

		BackgroundSetter.setBackgroundDrawable(DrawableBuilder.DEFAULT_BLUE, buttonBlue);
		BackgroundSetter.setBackgroundDrawable(DrawableBuilder.DEFAULT_VIOLET, buttonViolet);
		BackgroundSetter.setBackgroundDrawable(DrawableBuilder.DEFAULT_GREEN, buttonGreen);
		BackgroundSetter.setBackgroundDrawable(DrawableBuilder.DEFAULT_ORANGE, buttonOrange);
		BackgroundSetter.setBackgroundDrawable(DrawableBuilder.DEFAULT_RED, buttonRed);
		BackgroundSetter.setBackgroundDrawable(DrawableBuilder.DEFAULT_GREY, buttonGrey);
	}

}
