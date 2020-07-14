package com.ry.tzui.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ry.tzui.R;
import com.ry.tzui.ui.util.UiUtils;

public class TextNView extends View {
    int baseLineX = 0 ;
    float baseLineY = 200;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
    Rect rect = new Rect();

    public TextNView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextNView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setColor(Color.RED);
        paintText.setColor(Color.GREEN);
        paintText.setTextSize(120); //以px为单位

        Paint.FontMetrics fontMetrics = paintText.getFontMetrics();

        float ascent = fontMetrics.ascent;
        float descent = fontMetrics.descent;

        float half = (descent-ascent)/2;
        baseLineY = 350 + half-descent;


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawLine(0, 100, 3000, 100, paint);
        canvas.drawLine(0, 600, 3000, 600, paint);

        //写文字


        paintText.getTextBounds("hdadffdfdfdfds blog", 0, 5, rect);

        canvas.drawText("hdadffdfdfdfds blog", -rect.left, baseLineY, paintText);
    }


}
