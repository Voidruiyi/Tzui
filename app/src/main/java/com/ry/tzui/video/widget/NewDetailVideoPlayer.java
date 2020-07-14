package com.ry.tzui.video.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ry.tzui.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView;

public class NewDetailVideoPlayer extends StandardGSYVideoPlayer {

    /**
     * 是否第一次加载视频。用于隐藏进度条、播放按钮等UI。播放完成后，重新加载视频，会重置为true。
     */
    private boolean initFirstLoad = true;

    public NewDetailVideoPlayer(Context context) {
        super(context);
    }

    public NewDetailVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public NewDetailVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_new_detail_video_player;
    }

    @Override
    public void updateStartImage() {
        if (mStartButton instanceof ImageView) {
            ImageView imageView = (ImageView) mStartButton;
            switch (mCurrentState) {
                case GSYVideoView.CURRENT_STATE_PLAYING:
                    imageView.setImageResource(R.drawable.ic_pause_white_24dp);
                    imageView.setBackgroundResource(R.drawable.sel_pause_white_bg);
                    break;
                case GSYVideoView.CURRENT_STATE_ERROR:
                    imageView.setImageResource(R.drawable.ic_play_white_24dp);
                    imageView.setBackgroundResource(R.drawable.sel_play_white_bg);
                    break;
                case GSYVideoView.CURRENT_STATE_AUTO_COMPLETE:
                    imageView.setImageResource(R.drawable.ic_refresh_white_24dp);
                    imageView.setBackgroundResource(0);
                    break;
                default:
                    imageView.setImageResource(R.drawable.ic_play_white_24dp);
                    imageView.setBackgroundResource(R.drawable.sel_play_white_bg);
                    break;
            }

        } else {
            super.updateStartImage();
        }
    }

    //正常
    @Override
    public void changeUiToNormal() {
        super.changeUiToNormal();
        Log.e("eee", "changeUiToNormal");
        initFirstLoad = true;
    }

    //准备中

    @Override
    public void changeUiToPreparingShow() {
        super.changeUiToPreparingShow();
        Log.e("eee", "changeUiToPreparingShow");
        mBottomContainer.setVisibility(GONE);
        mStartButton.setVisibility(GONE);
    }

    //播放中

    @Override
    public void changeUiToPlayingShow() {
        super.changeUiToPlayingShow();
        Log.e("eee", "changeUiToPlayingShow");
        if (initFirstLoad) {
            mBottomContainer.setVisibility(GONE);
            mStartButton.setVisibility(GONE);
        }
        initFirstLoad = false;
    }

    //开始缓冲

    @Override
    public void changeUiToPlayingBufferingShow() {
        super.changeUiToPlayingBufferingShow();
        Log.e("eee", "changeUiToPlayingBufferingShow");
    }

    //暂停

    @Override
    public void changeUiToPauseShow() {
        super.changeUiToPauseShow();
        Log.e("eee", "changeUiToPauseShow");
    }

    //自动播放结束

    @Override
    public void changeUiToCompleteShow() {
        super.changeUiToCompleteShow();
        Log.e("eee", "changeUiToCompleteShow");
        mBottomContainer.setVisibility(GONE);
    }

    //错误状态

    @Override
    public void changeUiToError() {
        super.changeUiToError();
        Log.e("eee", "changeUiToError");
    }

    public ViewGroup getBottomContainer(){
        return mBottomContainer;
    }

}
