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
package com.atermenji.android.iconicdroid.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.util.Log;

import com.atermenji.android.iconicdroid.R;

/**
 * Helper class that wraps icon fonts and manages {@link Typeface} loading.
 */
public class TypefaceManager {

    private static final String TAG = "TypefaceManager";

    private TypefaceManager() {
    }

    public enum IconicTypefaceRaw {

        ENTYPO(R.raw.entypo), 
        ENTYPO_SOCIAL(R.raw.entypo_social),
        FONT_AWESOME(R.raw.font_awesome);

        private final int mTypefaceResourceId;
        private Typeface mTypeface;

        private IconicTypefaceRaw(int typefaceResourceId) {
            mTypefaceResourceId = typefaceResourceId;
        }

        /**
         * Loads a {@link Typeface} for the given icon font. 
         * {@link Typeface} is loaded only once to avoid memory consumption.
         * 
         * @param context
         * @return {@link Typeface}
         */
        public Typeface getTypeface(final Context context) {
            if (mTypeface == null) {
                mTypeface = createTypefaceFromResource(context, mTypefaceResourceId);
            }

            return mTypeface;
        }
    }

    public enum IconicTypefaceAsset {

        ICONIC("iconic.ttf");

        private final String mTypefaceAssetName;
        private Typeface mTypeface;

        private IconicTypefaceAsset(final String typefaceAssetName) {
            mTypefaceAssetName = typefaceAssetName;
        }

        /**
         * Loads a {@link Typeface} for the given icon font. 
         * {@link Typeface} is loaded only once to avoid memory consumption.
         * 
         * @param context
         * @return {@link Typeface}
         */
        public Typeface getTypeface(final Context context) {
            if (mTypeface == null) {
                mTypeface = createTypefaceFromAsset(context, mTypefaceAssetName);
            }

            return mTypeface;
        }
    }

    private static Typeface createTypefaceFromResource(final Context context, final int resource) {
        Typeface typeface = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = context.getResources().openRawResource(resource);
        } catch (NotFoundException ex) {
            Log.e(TAG, "Could not find typeface in resources.", ex);
        }

        String outPath = context.getCacheDir() + "/tmp.raw";

        try {
            byte[] buffer = new byte[inputStream.available()];
            outputStream = new BufferedOutputStream(new FileOutputStream(outPath));

            int l = 0;
            while ((l = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, l);
            }

            typeface = Typeface.createFromFile(outPath);

            new File(outPath).delete();
        } catch (IOException ex) {
            Log.e(TAG, "Error reading typeface from resource.", ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ex) {
                Log.e(TAG, "Error closing typeface streams.", ex);
            }
        }

        return typeface;
    }

    private static Typeface createTypefaceFromAsset(final Context context, final String assetName) {
        Typeface typeface = null;

        try {
            typeface = Typeface.createFromAsset(context.getAssets(), assetName);
        } catch (Exception ex) {
            Log.e(TAG, "Could not load typeface from assets.", ex);
        }

        return typeface;
    }
}
