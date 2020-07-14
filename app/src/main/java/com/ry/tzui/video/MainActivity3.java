package com.ry.tzui.video;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ry.tzui.R;
import com.ry.tzui.net.module.ServiceMoudle;
import com.ry.tzui.net.service.IServiceApi;
import com.ry.tzui.video.widget.NewDetailVideoPlayer;
import com.shuyu.gsyvideoplayer.GSYVideoADManager;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class MainActivity3 extends AppCompatActivity {

    private OrientationUtils orientationUtils;
    private String TAG = "MainActivity3";

    private NewDetailVideoPlayer videoPlayer;
    private RelativeLayout flHeader;
    private CircleImageView ivPullDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        videoPlayer = findViewById(R.id.videoPlayer);
        flHeader = findViewById(R.id.flHeader);
        ivPullDown = findViewById(R.id.ivPullDown);

        setupViews();
    }

    public void setupViews() {
        initParams();
        orientationUtils = new OrientationUtils(this, videoPlayer);

        startVideoPlayer();
//        viewModel.onRefresh()
    }

    private void initParams() {
        //videoInfoData | videoId   to viewModel
    }

    private void startVideoPlayer() {
        startPlay();
    }

    public void startPlay() {
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFull();
            }
        });

        //防止错位设置
        videoPlayer.setPlayTag(TAG);

        //音频焦点冲突时是否释放
        videoPlayer.setReleaseWhenLossAudio(false);
        //增加封面
        ImageView imageView =new ImageView(this );
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.load(it.cover.detail)
        videoPlayer.setThumbImageView(imageView);
        videoPlayer.getThumbImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTitleBarVisible();
            }
        });

        //是否开启自动旋转
        videoPlayer.setRotateViewAuto(false);
        //是否需要全屏锁定屏幕功能
        videoPlayer.setNeedLockFull(true);
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置触摸显示控制ui的消失时间
        videoPlayer.setDismissControlTime(5000);
        //设置播放过程中的回调
        videoPlayer.setVideoAllCallBack(new GSYSampleCallBack(){
            @Override
            public void onStartPrepared(String url, Object... objects) {
                super.onStartPrepared(url, objects);
                flHeader.setVisibility(View.GONE);
            }

            @Override
            public void onClickBlank(String url, Object... objects) {
                super.onClickBlank(url, objects);
                switchTitleBarVisible();
            }

            @Override
            public void onClickStop(String url, Object... objects) {
                super.onClickStop(url, objects);
                delayHideBottomContainer();
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);

                flHeader.setVisibility(View.VISIBLE);
                ivPullDown.setVisibility(View.VISIBLE);
            }
        });
        //设置播放URL  "eyepetizer://tag/592/?title=VR"  http://baobab.kaiyanapp.com/api/v2/video/1094
        //http://baobab.kaiyanapp.com/eyepetizer://tag/592/?title=VR
        String playUrl = "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=1094&resourceType=video&editionType=default&source=aliyun&playUrlType=url_oss";
        videoPlayer.setUp(playUrl, false, "VR");

        //开始播放
        videoPlayer.startPlayLogic();
    }

    private void showFull() {
        if(orientationUtils.getIsLand() !=1){
            orientationUtils.resolveByClick();
        }
        videoPlayer.startWindowFullscreen(this, true, false);
    }

    private void switchTitleBarVisible() {
        if (videoPlayer.getCurrentPlayer().getCurrentState() == GSYVideoView.CURRENT_STATE_AUTO_COMPLETE){
            return;
        }
        if (flHeader.getVisibility() == View.VISIBLE) {
            hideTitleBar();
        } else {
            flHeader.setVisibility(View.VISIBLE);
            delayHideTitleBar();
        }
    }

    private void hideTitleBar() {
        flHeader.setVisibility(View.GONE);
    }

    private void delayHideTitleBar() {
        flHeader.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideTitleBar();
            }
        }, 1000);
    }

    private void delayHideBottomContainer() {
        flHeader.postDelayed(new Runnable() {
            @Override
            public void run() {
                videoPlayer.getBottomContainer().setVisibility(View.GONE);
                videoPlayer.getStartButton().setVisibility(View.GONE);
            }
        }, videoPlayer.getDismissControlTime());


    }

    @Override
    public void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        videoPlayer.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
    }

    @Override
    public void onBackPressed() {
        orientationUtils.backToProtVideo();
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoADManager.releaseAllVideos();
        orientationUtils.releaseListener();
        videoPlayer.release();
        videoPlayer.setVideoAllCallBack(null);
    }

}
