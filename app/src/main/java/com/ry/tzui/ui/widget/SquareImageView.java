package com.ry.tzui.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

public class SquareImageView extends AppCompatImageView {

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int size = Math.max(measuredWidth, measuredHeight);

        setMeasuredDimension(size, size); //
    }
}
