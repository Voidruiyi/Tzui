package com.ry.tzui.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ry.tzui.R;
import com.ry.tzui.ui.util.UiUtils;

public class TextLineView extends View {
    private static final float WIDTH = UiUtils.dp2px(350);
    private static final float HEIGHT = UiUtils.dp2px(80);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    String content, cut;
    float cutWidth, allWidth;

    int maxLine, mLine;
    int index, oldIndex;
    float[] lines;
    int length;
    float y;

    private Context mContext;

    public TextLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public TextLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    {
        y=50;
        maxLine = 5;
        content = "并不是！x代表所要绘制文字所在矩形的相对位置。相对位置就是指指定点（x,y）在在所要绘制矩形的位置。我们知道所绘制矩形的纵坐标是由Y值来确定的，而相对x坐标的位置，只有左、中、右三个位置了。也就是所绘制矩形可能是在x坐标的左侧绘制，也有可能在x坐标的中间，也有可能在x坐标的右侧。而定义在x坐标在所绘制矩形相对位置的函数是";
        cut = "...展开";
        length = content.length();
        lines = new float[5];
//        bitmap = getSrcBitmap((int) WIDTH);

        paint.setTextSize(60); //以px为单位
        cutWidth = paint.measureText(cut);
//        allWidth = UiUtils.getScreenWidths(mContext);
        allWidth = WIDTH;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mLine = index = oldIndex = 0;

        while (mLine<5){
            y= y+paint.getFontSpacing();
            if(mLine==4){
                index = paint.breakText(content.substring(oldIndex, length), true, allWidth - cutWidth-10, lines);
                canvas.drawText(content.substring(oldIndex, length)+cut, 0, index+cut.length(), 0, y+paint.getFontSpacing(), paint);
            }else {
                index = paint.breakText(content.substring(oldIndex, length), true, allWidth, lines);
                canvas.drawText(content.substring(oldIndex, length), 0, index, 0, y+paint.getFontSpacing(), paint);
            }



            oldIndex += index;

            if(oldIndex>length){
                return;

            }
            mLine++;
        }


    }


    Bitmap getSrcBitmap(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.insur3_claim_car, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;

        return BitmapFactory.decodeResource(getResources(), R.drawable.insur3_claim_car, options);
    }

}
