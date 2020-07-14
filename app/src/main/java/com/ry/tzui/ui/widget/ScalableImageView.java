package com.ry.tzui.ui.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import com.ry.tzui.ui.util.UiUtils;

public class ScalableImageView extends View{
    private static final float IMAGE_WIDTH = UiUtils.dp2px(300);
    private static final float OVER_SCALE_FACTOR = 1.5f;

    Bitmap bitmap;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float offsetX;
    float offsetY;
    float originalOffsetX;
    float originalOffsetY;
    float smallScale;
    float bigScale;
    boolean big;

    float currentScale;
    ObjectAnimator scaleAnimator;

    GestureDetectorCompat  detector;
    OverScroller scroller;
    MyGestureListener gestureListener = new MyGestureListener();
    HenFlingRunner henFlingRunner = new HenFlingRunner();

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmap = UiUtils.getAvatar(getResources(), (int) IMAGE_WIDTH);
        scroller = new OverScroller(context);
        detector = new GestureDetectorCompat(context, gestureListener);
    }

    private float getCurrentScale() {
        return currentScale;
    }

    private void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }

    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0);
        }
        scaleAnimator.setFloatValues(smallScale, bigScale);
        return scaleAnimator;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        originalOffsetX = ((float) getWidth() - bitmap.getWidth()) / 2;
        originalOffsetY = ((float) getHeight() - bitmap.getHeight()) / 2;

        if((float)bitmap.getWidth()/bitmap.getHeight() > (float) getWidth()/getHeight()){
            smallScale = (float) getWidth()/bitmap.getWidth();
            bigScale = (float) getHeight()/bitmap.getHeight()* OVER_SCALE_FACTOR;
        }else {
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTOR;
        }
        currentScale = smallScale;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(offsetX * 1, offsetY * 1);
        canvas.scale(currentScale, currentScale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent down, MotionEvent event, float distanceX, float distanceY) {
            if (big) {
                offsetX -= distanceX;
                offsetY -= distanceY;
                fixOffsets();
                invalidate();
            }
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            big = !big;
//            if (big) {
//                offsetX = (e.getX() - getWidth() / 2f) - (e.getX() - getWidth() / 2) * bigScale / smallScale;
//                offsetY = (e.getY() - getHeight() / 2f) - (e.getY() - getHeight() / 2) * bigScale / smallScale;
//                fixOffsets();
//                getScaleAnimator().start();
//            } else {
//                getScaleAnimator().reverse();
//            }

            if(big){
                currentScale = bigScale;
            }else {
                currentScale = smallScale;
            }
            invalidate();
            return false;
        }

        @Override
        public boolean onFling(MotionEvent down, MotionEvent event, float velocityX, float velocityY) {
            if (big) {
                scroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY,
                        - (int) (bitmap.getWidth() * bigScale - getWidth()) / 2,
                        (int) (bitmap.getWidth() * bigScale - getWidth()) / 2,
                        - (int) (bitmap.getHeight() * bigScale - getHeight()) / 2,
                        (int) (bitmap.getHeight() * bigScale - getHeight()) / 2);

                postOnAnimation(henFlingRunner);
            }
            return false;
        }
    }

    private void fixOffsets() {
        offsetX = Math.min(offsetX, (bitmap.getWidth() * bigScale - getWidth()) / 2);
        offsetX = Math.max(offsetX, - (bitmap.getWidth() * bigScale - getWidth()) / 2);
        offsetY = Math.min(offsetY, (bitmap.getHeight() * bigScale - getHeight()) / 2);
        offsetY = Math.max(offsetY, - (bitmap.getHeight() * bigScale - getHeight()) / 2);
    }


    class HenFlingRunner implements Runnable {

        @Override
        public void run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.getCurrX();
                offsetY = scroller.getCurrY();
                invalidate();
                postOnAnimation(this);
            }
        }
    }
}
