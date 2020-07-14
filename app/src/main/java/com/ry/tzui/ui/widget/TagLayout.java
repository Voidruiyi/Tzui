package com.ry.tzui.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends ViewGroup {

    List<Rect> childrenBounds = new ArrayList<>();

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthUsed = 0;
        int heightUsed = 0;

        int lineWidthUsed = 0;
        int lineMaxHeight = 0;

        int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);

        for(int i=0; i<getChildCount();i++){
            View view = getChildAt(i);

            measureChildWithMargins(view, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            if(specWidthMode!=MeasureSpec.UNSPECIFIED &&
                lineWidthUsed+view.getMeasuredWidth() > specWidthSize){ //换行

                lineWidthUsed = 0;
                heightUsed+=lineMaxHeight;
                lineMaxHeight = 0;

                measureChildWithMargins(view, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }

            Rect childBound;
            if (childrenBounds.size() <= i) {
                childBound = new Rect();
                childrenBounds.add(childBound);
            } else {
                childBound = childrenBounds.get(i);
            }

            childBound.set(lineWidthUsed, heightUsed, lineWidthUsed+view.getMeasuredWidth(), heightUsed+view.getMeasuredHeight());

            lineWidthUsed+=view.getMeasuredWidth();

            widthUsed = Math.max(widthUsed, lineWidthUsed);
            lineMaxHeight = Math.max(lineMaxHeight, view.getMeasuredHeight());
        }

        int width = widthUsed;
        int height = heightUsed+lineMaxHeight;

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect childBounds = childrenBounds.get(i);
            child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom);
        }
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
