package com.ry.tzui.ui.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.ry.tzui.R;

public class UiUtils {

    //dp to sp
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());

    }

    /**
     * 获取手机屏幕宽度
     *
     * @return 单位px
     */
    public static int getScreenWidths(Context context) {
        WindowManager _manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics _displayMetrics = new DisplayMetrics();
        _manager.getDefaultDisplay().getMetrics(_displayMetrics);

        int[] screenWHPixels = new int[2];
        screenWHPixels[0] = _displayMetrics.widthPixels;
        screenWHPixels[1] = _displayMetrics.heightPixels;

        return screenWHPixels[0];
    }

    public static Bitmap getAvatar(Resources res, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, R.drawable.avatar_rengwuxian, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(res, R.drawable.avatar_rengwuxian, options);
    }
}
