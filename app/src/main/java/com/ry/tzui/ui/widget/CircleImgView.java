package com.ry.tzui.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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

public class CircleImgView extends View {

    private static final float WIDTH = UiUtils.dp2px(300);
    private static final float PADDING = UiUtils.dp2px(50);

    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    Bitmap bitmap;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF saveArea = new RectF();

    public CircleImgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = getSrcBitmap((int) WIDTH);
        saveArea.set(PADDING, PADDING, PADDING+WIDTH, PADDING+WIDTH);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawOval(PADDING, PADDING, PADDING+WIDTH, PADDING+WIDTH, paint);
        int savad = canvas.saveLayer(saveArea, paint);
        canvas.drawOval(PADDING+2, PADDING+2, PADDING+WIDTH-2, PADDING+WIDTH-2, paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap, PADDING, PADDING, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(savad);
    }

    Bitmap getSrcBitmap(int width){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.insur3_claim_car, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;

        return   BitmapFactory.decodeResource(getResources(), R.drawable.insur3_claim_car, options);
    }
}
