/*
 * Copyright (C) 2012 Artur Termenji
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.atermenji.android.iconictextview.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.atermenji.android.iconictextview.IconicFontDrawable;
import com.atermenji.android.iconictextview.icon.EntypoIcon;
import com.atermenji.android.iconictextview.icon.EntypoSocialIcon;
import com.atermenji.android.iconictextview.icon.FontAwesomeIcon;
import com.atermenji.android.iconictextview.icon.Icon;
import com.atermenji.android.iconictextview.icon.IconicIcon;

public class SimpleSampleActivity extends Activity {

    private static final int ICON_PADDING_MAX = 500;
    private static final int ICON_CONTOUR_WIDTH = 10;

    private View mIconButton;
    private Spinner mIconsSpinner;
    private SeekBar mPaddingSeekBar;
    private Button mChangeColorButton;
    private TextView mCurPaddingTextView;
    private CheckBox mDrawContourCheckBox;
    
    private IconicFontDrawable mIconicFontDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Simple Sample");
        setContentView(R.layout.activity_simple_sample);

        mIconButton = findViewById(R.id.view_icon);
        mIconsSpinner = (Spinner) findViewById(R.id.sp_icons);
        mPaddingSeekBar = (SeekBar) findViewById(R.id.sb_size);
        mChangeColorButton = (Button) findViewById(R.id.bt_change_color);
        mCurPaddingTextView = (TextView) findViewById(R.id.tv_size);
        mDrawContourCheckBox = (CheckBox) findViewById(R.id.cb_draw_stroke);

        initSimpleSample();
    }

    private void initSimpleSample() {
        mIconicFontDrawable = new IconicFontDrawable(this);
        mIconButton.setBackground(mIconicFontDrawable);

        List<Icon> icons = new ArrayList<Icon>();
        icons.addAll(Arrays.asList(EntypoIcon.values()));
        icons.addAll(Arrays.asList(EntypoSocialIcon.values()));
        icons.addAll(Arrays.asList(FontAwesomeIcon.values()));
        icons.addAll(Arrays.asList(IconicIcon.values()));
        
        final ArrayAdapter<Icon> adapter = 
                new ArrayAdapter<Icon>(this, android.R.layout.simple_spinner_item, icons);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mIconsSpinner.setAdapter(adapter);

        mIconsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Icon icon = adapter.getItem(pos);
                mIconicFontDrawable.setIcon(icon);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        
        mIconicFontDrawable.setIconColor(Utils.randomColor());

        mCurPaddingTextView.setText("Padding: " + 0);
        
        mPaddingSeekBar.setMax(ICON_PADDING_MAX);
        mPaddingSeekBar.setProgress(0);
        mPaddingSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mIconicFontDrawable.setIconPadding(progress);
                mCurPaddingTextView.setText("Padding: " + progress);
            }
        });
        
        mChangeColorButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mIconicFontDrawable.setIconColor(Utils.randomColor());
                mIconicFontDrawable.setContourColor(Utils.randomColor());
            }
        });

        mDrawContourCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mIconicFontDrawable.setContour(Utils.randomColor(), ICON_CONTOUR_WIDTH);
                }

                mIconicFontDrawable.drawContour(isChecked);
            }
        });
    }
}
