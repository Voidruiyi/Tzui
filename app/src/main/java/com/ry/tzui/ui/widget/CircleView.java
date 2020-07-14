package com.ry.tzui.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ry.tzui.ui.util.UiUtils;

public class CircleView extends View {
    private  int RADIUS = (int) UiUtils.dp2px(40);
    private  int PADDING = (int) UiUtils.dp2px(10);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (PADDING + RADIUS) * 2;
        int height = (PADDING + RADIUS) * 2;

        Log.e("eee", "w1="+width);

//        width = resolveSizeAndState(width, widthMeasureSpec, 0);
//        height = resolveSizeAndState(height, widthMeasureSpec, 0);

        width = resolveSize(width, widthMeasureSpec);
        height = resolveSize(height, heightMeasureSpec);
        Log.e("eee", "w2="+width);

        RADIUS = (width/2) - PADDING;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.RED);
        canvas.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, paint);
    }
}
