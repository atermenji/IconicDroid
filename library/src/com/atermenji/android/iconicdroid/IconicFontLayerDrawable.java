package com.atermenji.android.iconicdroid;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import com.atermenji.android.iconicdroid.icon.Icon;
import com.atermenji.android.iconicdroid.util.ArrayUtils;

public class IconicFontLayerDrawable extends LayerDrawable {
    private IconicFontStateListDrawable mIconDrawable;
    public IconicFontLayerDrawable(Drawable[] layers, IconicFontStateListDrawable iconDrawable) {
        super(ArrayUtils.appendElement(Drawable.class, layers, iconDrawable));
        mIconDrawable = iconDrawable;
    }

    public void setIcon(Icon icon) {
        mIconDrawable.setIcon(icon);
    }

    public void setIconPadding(int iconPadding) {
        mIconDrawable.setIconPadding(iconPadding);
    }
}