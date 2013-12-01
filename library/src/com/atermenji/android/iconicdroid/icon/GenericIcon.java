package com.atermenji.android.iconicdroid.icon;

import com.atermenji.android.iconicdroid.util.TypefaceManager.IconicTypeface;

/** Generic class for translating client Strings to internal icon
 *  representations. */
public class GenericIcon implements Icon {
    private static final long serialVersionUID = 74214504474849939L;

    private final IconicTypeface mTypeface;
    private final int mIconUtfValue;
    public GenericIcon(IconicTypeface typeface, String iconStr) {
        mTypeface = typeface;
        mIconUtfValue = getIconUtfValue(iconStr);
    }

    @Override public IconicTypeface getIconicTypeface() {
        return mTypeface;
    }

    @Override public int getIconUtfValue() {
        return mIconUtfValue;
    }

    public static int getIconUtfValue(String iconStr) {
        return Character.codePointAt(iconStr, 0);
    }
}
