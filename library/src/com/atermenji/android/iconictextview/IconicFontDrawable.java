package com.atermenji.android.iconictextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import com.atermenji.android.iconictextview.icon.Icon;

public class IconicFontDrawable extends Drawable {

    private Context mContext;
    
    private Paint mPaint;
    private Icon mIcon;
    private char[] mIconUtfChars;
    
    public IconicFontDrawable(Context context) {
        mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }
    
    public IconicFontDrawable(Context context, Icon icon) {
        this(context);
        init(icon);
    }
    
    public void setIcon(final Icon icon) {
        init(icon);
    }
    
    public void setIconSize(final float iconSize) {
        mPaint.setTextSize(iconSize);
        invalidateSelf();
    }
    
    public void setIconColor(final int color) {
        mPaint.setColor(color);
        invalidateSelf();
    }
    
    @Override
    public void draw(Canvas canvas) {
        if (mIcon != null) {
            canvas.drawText(mIconUtfChars, 0, mIconUtfChars.length, canvas.getWidth() / 2, canvas.getHeight() / 2, mPaint);
        }
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }
    
    private void init(final Icon icon) {
        mIcon = icon;
        mIconUtfChars = Character.toChars(icon.getIconUtfValue());
        mPaint.setTypeface(mIcon.getIconicTypeface().getTypeface(mContext));
        mPaint.setTextAlign(Paint.Align.CENTER);
    }
}
