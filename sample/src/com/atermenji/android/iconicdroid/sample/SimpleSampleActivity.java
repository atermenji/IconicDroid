package com.atermenji.android.iconicdroid.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.atermenji.android.iconicdroid.icon.EntypoSocialIcon;
import com.atermenji.android.iconicdroid.icon.FontAwesomeIcon;
import com.atermenji.android.iconicdroid.icon.Icon;
import com.atermenji.android.iconicdroid.icon.IconicIcon;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;

public class SimpleSampleActivity extends Activity {

    private static final int ICON_PADDING_MAX = 500;
    private static final int ICON_CONTOUR_WIDTH = 10;

    private static final int BUTTON_ICON_CONTOUR_WIDTH = 5;
    private static final int BUTTON_ICON_INTRINSIC_WIDTH = 70;
    private static final int BUTTON_ICON_INTRINSIC_HEIGHT = 70;

    private static final String EXTRA_ICON = "extra_icon";

    private View mIconView;
    private Spinner mIconsSpinner;
    private SeekBar mPaddingSeekBar;
    private Button mChangeColorButton;
    private TextView mCurPaddingTextView;
    private CheckBox mDrawContourCheckBox;
    
    private IconicFontDrawable mIconicFontDrawable;
    private IconicFontDrawable mIconicFontDrawableButton;

    private Icon mIcon;

    private boolean firstSelect;

    public static Intent createIntent(final Context context, final Icon icon) {
        Intent intent = new Intent(context, SimpleSampleActivity.class);
        intent.putExtra(EXTRA_ICON, icon);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple_sample);

        mIconView = findViewById(R.id.view_icon);
        mIconsSpinner = (Spinner) findViewById(R.id.sp_icons);
        mPaddingSeekBar = (SeekBar) findViewById(R.id.sb_size);
        mChangeColorButton = (Button) findViewById(R.id.bt_change_color);
        mCurPaddingTextView = (TextView) findViewById(R.id.tv_size);
        mDrawContourCheckBox = (CheckBox) findViewById(R.id.cb_draw_stroke);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mIcon = (Icon) extras.getSerializable(EXTRA_ICON);
        }

        initSimpleSample();
    }

    private void initSimpleSample() {
        mIconicFontDrawable = new IconicFontDrawable(this);
        mIconicFontDrawableButton = new IconicFontDrawable(this);

        mIconicFontDrawableButton.setIntrinsicWidth(BUTTON_ICON_INTRINSIC_WIDTH);
        mIconicFontDrawableButton.setIntrinsicHeight(BUTTON_ICON_INTRINSIC_HEIGHT);

        if (SDK_INT < JELLY_BEAN) {
            mIconView.setBackgroundDrawable(mIconicFontDrawable);
        } else {
            mIconView.setBackground(mIconicFontDrawable);
        }

        mChangeColorButton.setCompoundDrawablesWithIntrinsicBounds(
                mIconicFontDrawableButton, null, null, null);

        List<Icon> icons = new ArrayList<Icon>();
        icons.addAll(Arrays.asList(EntypoIcon.values()));
        icons.addAll(Arrays.asList(EntypoSocialIcon.values()));
        icons.addAll(Arrays.asList(FontAwesomeIcon.values()));
        icons.addAll(Arrays.asList(IconicIcon.values()));

        final ArrayAdapter<Icon> adapter =
                new ArrayAdapter<Icon>(this, android.R.layout.simple_spinner_item, icons);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mIconsSpinner.setAdapter(adapter);

        if (mIcon != null) {
            mIconicFontDrawable.setIcon(mIcon);
            mIconicFontDrawableButton.setIcon(mIcon);
            mIconsSpinner.setSelection(adapter.getPosition(mIcon));
            firstSelect = true;
        }

        mIconsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (!firstSelect) {
                    Icon icon = adapter.getItem(pos);
                    mIconicFontDrawable.setIcon(icon);
                    mIconicFontDrawableButton.setIcon(icon);
                } else {
                    firstSelect = false;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mIconicFontDrawable.setIconColor(Utils.randomColor());
        mIconicFontDrawableButton.setIconColor(Utils.randomColor());

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

                mIconicFontDrawableButton.setIconColor(Utils.randomColor());
                mIconicFontDrawableButton.setContourColor(Utils.randomColor());
            }
        });

        mDrawContourCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mIconicFontDrawable.setContour(Utils.randomColor(), ICON_CONTOUR_WIDTH);
                    mIconicFontDrawableButton.setContour(Utils.randomColor(), BUTTON_ICON_CONTOUR_WIDTH);
                }

                mIconicFontDrawable.drawContour(isChecked);
                mIconicFontDrawableButton.drawContour(isChecked);
            }
        });
    }
}
