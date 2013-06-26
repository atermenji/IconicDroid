/*
 * Copyright (C) 2013 Artur Termenji
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
package com.atermenji.android.iconicdroid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.atermenji.android.iconicdroid.icon.Icon;

/**
 * A custom {@link TextView} which can display icons from icon fonts. 
 */
public class IconicTextView extends TextView {

    public IconicTextView(Context context) {
        this(context, null, 0);
    }
    
    public IconicTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public IconicTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Loads and displays given {@link Icon} in a {@link TextView}.
     * 
     * @param icon
     */
    public void setIcon(final Icon icon) {
        setTypeface(icon.getIconicTypeface().getTypeface(getContext()));
        setIconUtfValue(icon.getIconUtfValue());
    }

    private void setIconUtfValue(final int utfValue) {
        char[] utfChars = Character.toChars(utfValue);
        setText(utfChars, 0, utfChars.length);
    }
}
