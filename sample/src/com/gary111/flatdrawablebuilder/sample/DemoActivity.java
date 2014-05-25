package com.gary111.flatdrawablebuilder.sample;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.flatdrawablebuilder.DrawableBuilder;
import com.flatdrawablebuilder.DrawableBuilder.State;
import com.flatdrawablebuilder.StateBuilder;
import com.flatdrawablebuilder.StateBuilder.Volume;
import com.flatdrawablebuilder.utils.BackgroundSetter;

public class DemoActivity extends Activity {

	private Button buttonBlue;
	private Button buttonViolet;
	private Button buttonGreen;
	private Button buttonOrange;
	private Button buttonRed;
	private Button buttonGrey;
	
	private Button buttonCustom;
	
	private CheckBox checkBoxMainColor;
	private EditText editTextMainColor;

	private CheckBox checkBoxVolumeColor;
	private EditText editTextVolumeColor;

	private CheckBox checkBoxCorner;
	private SeekBar seekBarCorner;

	private CheckBox checkBoxVolume;
	private SeekBar seekBarVolume;
	
	private CheckBox checkBoxPadding;
	private SeekBar seekBarPadding;

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
		
		buttonCustom = (Button) findViewById(R.id.buttonCustom);
		
		checkBoxMainColor = (CheckBox) findViewById(R.id.checkBoxMainColor);
		editTextMainColor = (EditText) findViewById(R.id.editTextMainColor);
		
		checkBoxVolumeColor = (CheckBox) findViewById(R.id.checkBoxVolumeColor);
		editTextVolumeColor = (EditText) findViewById(R.id.editTextVolumeColor);
		
		checkBoxCorner = (CheckBox) findViewById(R.id.checkBoxCorner);
		seekBarCorner = (SeekBar) findViewById(R.id.seekBarCorner);
		
		checkBoxVolume = (CheckBox) findViewById(R.id.checkBoxVolume);
		seekBarVolume = (SeekBar) findViewById(R.id.seekBarVolume);
		
		checkBoxPadding = (CheckBox) findViewById(R.id.checkBoxPadding);
		seekBarPadding = (SeekBar) findViewById(R.id.seekBarPadding);
		
		initializing();
	}
	
	private void initializing() {
		BackgroundSetter.setBackgroundDrawable(DrawableBuilder.DEFAULT_BLUE, buttonBlue);
		BackgroundSetter.setBackgroundDrawable(DrawableBuilder.DEFAULT_VIOLET, buttonViolet);
		BackgroundSetter.setBackgroundDrawable(DrawableBuilder.DEFAULT_GREEN, buttonGreen);
		BackgroundSetter.setBackgroundDrawable(DrawableBuilder.DEFAULT_ORANGE, buttonOrange);
		BackgroundSetter.setBackgroundDrawable(DrawableBuilder.DEFAULT_RED, buttonRed);
		BackgroundSetter.setBackgroundDrawable(DrawableBuilder.DEFAULT_GREY, buttonGrey);
		
		checkBoxMainColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				editTextMainColor.setEnabled(isChecked);
				
				updateCustomButtonDrawable();
			}
		});
		
		checkBoxVolumeColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				editTextVolumeColor.setEnabled(isChecked);
				
				updateCustomButtonDrawable();
			}
		});

		checkBoxCorner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				seekBarCorner.setEnabled(isChecked);
				
				updateCustomButtonDrawable();
			}
		});

		checkBoxVolume.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				seekBarVolume.setEnabled(isChecked);
				
				updateCustomButtonDrawable();
			}
		});
		
		checkBoxPadding.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				seekBarPadding.setEnabled(isChecked);
				
				updateCustomButtonDrawable();
			}
		});
		
		editTextMainColor.addTextChangedListener(new TextWatcher() {
			
			@Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
			
			@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {
				updateCustomButtonDrawable();
			}
		});
		
		editTextVolumeColor.addTextChangedListener(new TextWatcher() {
			
			@Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
			
			@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {
				updateCustomButtonDrawable();
			}
		});
		
		seekBarCorner.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override public void onStopTrackingTouch(SeekBar seekBar) {}
			
			@Override public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				updateCustomButtonDrawable();
			}
		});
		
		seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override public void onStopTrackingTouch(SeekBar seekBar) {}
			
			@Override public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				updateCustomButtonDrawable();
			}
		});
		
		seekBarPadding.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override public void onStopTrackingTouch(SeekBar seekBar) {}
			
			@Override public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				updateCustomButtonDrawable();
			}
		});
	}
	
	private void updateCustomButtonDrawable() {
		int mainColor = getMainColor();
		int volumeColor = getVolumeColor();
		int corner = getCorner();
		int volume = getVolume();
		int padding = getPadding();
		
		Drawable drawable = DrawableBuilder.create(StateBuilder.create()
				.setColor(mainColor)
				.setCornerRadius(corner)
				.setVolume(volume == 0 ? Volume.NONE : Volume.BOTTOM, volume, volumeColor)
				.setPadding(padding))
			.addState(State.PRESSED, StateBuilder.create()
				.setColor(volume == 0 ? volumeColor : mainColor)
				.setCornerRadius(corner)
				.setVolume(Volume.NONE, volume, volumeColor)
				.setPadding(padding))
			.build();
		
		BackgroundSetter.setBackgroundDrawable(drawable, buttonCustom);
	}
	
	private int getMainColor() {
		if (editTextMainColor.isEnabled()) {
			try {
				return Color.parseColor("#" + editTextMainColor.getText().toString());
			} catch (Exception e) {
				return Color.parseColor(StateBuilder.LIGHT_GREY);
			}
		} else {
			return Color.parseColor(StateBuilder.LIGHT_GREY);
		}
	}
	
	private int getVolumeColor() {
		if (editTextVolumeColor.isEnabled()) {
			try {
				return Color.parseColor("#" + editTextVolumeColor.getText().toString());
			} catch (Exception e) {
				return Color.parseColor(StateBuilder.DARK_GREY);
			}
		} else {
			return Color.parseColor(StateBuilder.DARK_GREY);
		}
	}
	
	private int getCorner() {
		if (seekBarCorner.isEnabled()) {
			return seekBarCorner.getProgress();
		} else {
			return 0;
		}
	}
	
	private int getVolume() {
		if (seekBarVolume.isEnabled()) {
			return seekBarVolume.getProgress();
		} else {
			return 0;
		}
	}
	
	private int getPadding() {
		if (seekBarVolume.isEnabled()) {
			return seekBarPadding.getProgress() + 10;
		} else {
			return 10;
		}
	}

}
