package com.atermenji.android.iconicdroid;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;

import com.atermenji.android.iconicdroid.icon.Icon;
import com.atermenji.android.iconicdroid.util.ColorStateList;

public class IconicFontStateListDrawable extends StateListDrawable {
    private final IconicFontDrawable mIconDrawable;
    private final ColorStateList mColorFilter;

    public IconicFontStateListDrawable(Context context,
                                       ColorStateList colorFilter) {
        mIconDrawable = new IconicFontDrawable(context);
        /*mIconDrawable.setIconColor(Color.WHITE); // So SRC_IN works. */
        mColorFilter = colorFilter;
        for (int[] states : mColorFilter.getStates()) {
            addState(states, mIconDrawable);
        }
    }

    public void setIconPadding(int iconPadding) {
        mIconDrawable.setIconPadding(iconPadding);
    }

    public void setIcon(Icon icon) {
        mIconDrawable.setIcon(icon);
    }

    @Override protected boolean onStateChange(int[] state) {
        super.onStateChange(state);
        if (mColorFilter != null) {
            int argb = mColorFilter.getColorForState(state, mColorFilter.getDefaultColor());
            mIconDrawable.setIconColor(argb/*, PorterDuff.Mode.SRC_IN*/);
            invalidateSelf();
            return true;
        }
        return false;
    }
}