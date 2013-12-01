
package com.atermenji.android.iconicdroid;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Button;

import com.atermenji.android.iconicdroid.icon.GenericIcon;
import com.atermenji.android.iconicdroid.util.ColorStateList;
import com.atermenji.android.iconicdroid.util.TypefaceManager.IconicTypeface;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class IconicFontButton extends Button {
    private static final String TAG = "IconicFontButton";
    private IconicFontLayerDrawable mBackgroundDrawable;

    public static enum FontIdx {
        SYS    (0, null), // TODO: implement.
        ICONIC (1, IconicTypeface.ICONIC),
        AWESOME(2, IconicTypeface.FONT_AWESOME),
        ENTYPO (3, IconicTypeface.ENTYPO),
        SOCIAL (4, IconicTypeface.ENTYPO_SOCIAL);

        private final int mVal;
        private final IconicTypeface mTypeface;
        private FontIdx(int val, IconicTypeface typeface) {
            mVal = val;
            mTypeface = typeface;
        }
        public static FontIdx of(int val) {
            return mValMap.get(val);
        }

        public int getVal() {
            return mVal;
        }

        public IconicTypeface getTypeface() {
            return mTypeface;
        }

        private static final SparseArray<FontIdx> mValMap;
        static {
            mValMap = new SparseArray<FontIdx>();
            for (FontIdx font : values()) {
                if (BuildConfig.DEBUG && mValMap.get(font.mVal) != null) {
                    throw new AssertionError("duplicate index detected at " + font.mVal
                                           + " with font " + font);
                }
                mValMap.put(font.mVal, font);
            }
        }
    }

    public IconicFontButton(Context context) {
        this(context, null);
    }

    public IconicFontButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.style.IconicFontButton);
    }

    public IconicFontButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setBackground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(mBackgroundDrawable);
        } else {
            setBackgroundDrawable(mBackgroundDrawable);
        }
    }

    // TODO: optimizations...
    private void init(Context context, AttributeSet attrs, int defStyle) {
        Resources r = getResources();
        ColorStateList csl = null;
        try {
            csl = ColorStateList.createFromXml(r,
                                               r.getXml(R.color.holo_blue_dark_btn));
        } catch (XmlPullParserException e) {
            Log.e(TAG, "IconicFontButton.init(): failed parsing ColorStateList", e);
        } catch (IOException e) {
            Log.e(TAG, "IconicFontButton.init(): failed reading ColorStateList", e);
        }

        mBackgroundDrawable = new IconicFontLayerDrawable(new Drawable [] { r.getDrawable(R.drawable.holo_blue_dark_btn_bg) },
                                                          new IconicFontStateListDrawable(getContext(), csl));
        TypedArray a = context.obtainStyledAttributes(attrs,
                                                      R.styleable.IconicFontButton,
                                                      defStyle,
                                                      0);

        int idx           = 0;
        float iconPadding = 0;
        String icon       = null;
        try {
            idx         = a.getInt(R.styleable.IconicFontButton_font, FontIdx.SYS.getVal());
            iconPadding = a.getDimension(R.styleable.IconicFontButton_iconPadding, 0);
            icon        = a.getString(R.styleable.IconicFontButton_iconUtf);
        } finally {
            a.recycle();
        }

        mBackgroundDrawable.setIcon(new GenericIcon(FontIdx.of(idx).getTypeface(), icon));
        mBackgroundDrawable.setIconPadding((int) iconPadding);
        setBackground();
    }
}
