package com.jingdong.common.recommend.ui.video;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.R;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendDataLoader;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.widget.custom.livewidget.bean.LiveVideoEntity;
import com.jingdong.common.widget.custom.livewidget.playerview.VideoViewLayout;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;

/* loaded from: classes6.dex */
public class RecommendVideoWidget extends FrameLayout {
    private boolean canPlayWithNoWifi;
    public SimpleDraweeView coverImg;
    public boolean isToPlay;
    private JDDisplayImageOptions jdDisplayImageOptions;
    public LiveVideoEntity liveVideoEntity;
    public VideoViewLayout liveVideoLayout;
    private boolean loopPlay;
    public View rootParentView;
    public RecommendVideo videoData;
    private RecommendVideoPlayState videoPlayStateListener;
    public JDVideoView videoPlayView;

    public RecommendVideoWidget(@NonNull Context context) {
        super(context);
        this.jdDisplayImageOptions = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.RGB_565).setReferer(RecommendConstant.HTTP_REFER);
        this.loopPlay = false;
        this.canPlayWithNoWifi = false;
        this.isToPlay = false;
        initView();
    }

    private void addLiveView() {
        setBackgroundResource(0);
        removeAllViews();
        addView(this.liveVideoLayout);
    }

    private void addVideoView() {
        setBackgroundResource(R.color.black);
        removeAllViews();
        addView(this.videoPlayView);
        addView(this.coverImg);
    }

    private IPlayerControl.PlayerOptions generateRecommendOpt() {
        IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
        playerOptions.setCouldMediaCodec(true);
        playerOptions.setAspectRatio(1);
        playerOptions.setPlayTypeId("150");
        playerOptions.setIsRequestAudioFocus(false);
        playerOptions.setPlayContent(2);
        playerOptions.setUseCache(true);
        playerOptions.setLoop(this.loopPlay);
        return playerOptions;
    }

    private void initLiveView() {
        VideoViewLayout videoViewLayout = new VideoViewLayout(getContext());
        this.liveVideoLayout = videoViewLayout;
        videoViewLayout.setAutoPlay(false);
        this.liveVideoLayout.setDescendantFocusability(393216);
        this.liveVideoLayout.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jingdong.common.recommend.ui.video.RecommendVideoWidget.2
            {
                RecommendVideoWidget.this = this;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                RecommendVideoWidget recommendVideoWidget = RecommendVideoWidget.this;
                if (recommendVideoWidget.isToPlay) {
                    recommendVideoWidget.liveVideoLayout.play();
                }
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
            }
        });
        this.liveVideoLayout.setVideoStatusListener(new VideoViewLayout.VideoStausListener() { // from class: com.jingdong.common.recommend.ui.video.RecommendVideoWidget.3
            {
                RecommendVideoWidget.this = this;
            }

            @Override // com.jingdong.common.widget.custom.livewidget.playerview.VideoViewLayout.VideoStausListener
            public void onDestory() {
            }

            @Override // com.jingdong.common.widget.custom.livewidget.playerview.VideoViewLayout.VideoStausListener
            public void onError(int i2, String str) {
                if (RecommendVideoWidget.this.videoPlayStateListener != null) {
                    RecommendVideoWidget.this.videoPlayStateListener.onError(i2, 0);
                }
            }

            @Override // com.jingdong.common.widget.custom.livewidget.playerview.VideoViewLayout.VideoStausListener
            public void onPlay() {
            }
        });
        this.liveVideoLayout.setClickable(false);
        this.liveVideoLayout.clearFocus();
        this.liveVideoLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.liveVideoLayout.setOnClickListener(null);
    }

    private void initVideoView() {
        JDVideoView jDVideoView = new JDVideoView(getContext());
        this.videoPlayView = jDVideoView;
        jDVideoView.setFocusable(false);
        this.videoPlayView.setFocusableInTouchMode(false);
        this.videoPlayView.clearFocus();
        this.videoPlayView.setOnPlayerStateListener(new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.recommend.ui.video.RecommendVideoWidget.1
            {
                RecommendVideoWidget.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                RecommendVideoWidget.this.coverImg.setVisibility(0);
                if (RecommendVideoWidget.this.videoPlayStateListener != null) {
                    RecommendVideoWidget.this.videoPlayStateListener.onCompletion();
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                RecommendVideoWidget.this.coverImg.setVisibility(0);
                if (RecommendVideoWidget.this.videoPlayStateListener != null) {
                    RecommendVideoWidget.this.videoPlayStateListener.onError(i2, i3);
                }
                return false;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                if (i2 != 3) {
                    if (i2 == 30003 && RecommendVideoWidget.this.videoPlayStateListener != null) {
                        RecommendVideoWidget.this.videoPlayStateListener.onInfo(i2, i3);
                        return false;
                    }
                    return false;
                }
                RecommendVideoWidget.this.coverImg.setVisibility(8);
                if (RecommendVideoWidget.this.videoPlayStateListener != null) {
                    RecommendVideoWidget.this.videoPlayStateListener.onPrepared(1L);
                    return false;
                }
                return false;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
            }
        });
        this.videoPlayView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
        this.coverImg = simpleDraweeView;
        simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.coverImg.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    }

    private void initView() {
        initVideoView();
        addVideoView();
    }

    private boolean isLiveState() {
        VideoViewLayout videoViewLayout = this.liveVideoLayout;
        return (videoViewLayout == null || videoViewLayout.getParent() == null) ? false : true;
    }

    private boolean isVideoState() {
        JDVideoView jDVideoView = this.videoPlayView;
        return (jDVideoView == null || jDVideoView.getParent() == null) ? false : true;
    }

    public void playVideo(boolean z) {
        RecommendVideo recommendVideo = this.videoData;
        if ((recommendVideo == null || TextUtils.isEmpty(recommendVideo.getVideoUrl()) || isPlaying()) && !z) {
            return;
        }
        this.videoPlayView.suspend();
        this.videoPlayView.setPlayerOptions(generateRecommendOpt());
        this.videoPlayView.setVideoPathWithoutOpen(this.videoData.getVideoUrl());
        this.videoPlayView.initRenders();
    }

    public boolean canPlayVideo() {
        RecommendVideo recommendVideo = this.videoData;
        if (recommendVideo != null) {
            return recommendVideo.canPlay;
        }
        return true;
    }

    public boolean enableLoop() {
        RecommendVideo recommendVideo = this.videoData;
        if (recommendVideo != null) {
            return recommendVideo.enableLoop;
        }
        return true;
    }

    public int getCurrentPosition() {
        return this.videoPlayView.getCurrentPosition();
    }

    public int getDuration() {
        return this.videoPlayView.getDuration();
    }

    public boolean hasVideoData() {
        return (this.videoData == null && this.liveVideoEntity == null) ? false : true;
    }

    public boolean isCanPlayWithNoWifi() {
        return this.canPlayWithNoWifi;
    }

    public boolean isPlaying() {
        if (isVideoState()) {
            return this.videoPlayView.isPlaying();
        }
        if (isLiveState()) {
            return this.liveVideoLayout.isVideoPlaying();
        }
        return false;
    }

    public void jumpToLive(Uri uri) {
        if (isLiveState()) {
            this.liveVideoLayout.jump(uri);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RecommendVideoPlayState recommendVideoPlayState = this.videoPlayStateListener;
        if (recommendVideoPlayState != null) {
            recommendVideoPlayState.onDetach();
        }
    }

    public void pause() {
        if (isVideoState()) {
            this.videoPlayView.pause();
        }
        if (isLiveState()) {
            this.liveVideoLayout.stop();
        }
    }

    public void release() {
        this.isToPlay = false;
        releaseVideo();
        if (isLiveState()) {
            this.liveVideoLayout.stop();
        }
    }

    public void releaseVideo() {
        if (isVideoState()) {
            this.isToPlay = false;
            this.coverImg.setVisibility(0);
            this.videoPlayView.releaseInThread(true);
        }
    }

    public void resume() {
        if (isVideoState()) {
            this.videoPlayView.start();
        }
        if (isLiveState()) {
            this.liveVideoLayout.play();
        }
    }

    public void setCanPlayWithNoWifi(boolean z) {
        this.canPlayWithNoWifi = z;
    }

    public void setLiVeData(LiveVideoEntity liveVideoEntity) {
        this.liveVideoEntity = liveVideoEntity;
        this.videoData = null;
        if (this.liveVideoLayout == null) {
            initLiveView();
        }
        if (this.liveVideoLayout.getParent() == null) {
            addLiveView();
        }
        this.liveVideoLayout.setDataSource(liveVideoEntity);
    }

    public void setLoop(boolean z) {
        this.loopPlay = z;
        if (isVideoState() && this.videoPlayView.getIjkMediaPlayer() != null && this.videoPlayView.isPlaying()) {
            this.videoPlayView.getIjkMediaPlayer().setLooping(z);
        }
    }

    public void setVideoData(RecommendVideo recommendVideo, String str) {
        this.videoData = recommendVideo;
        this.liveVideoEntity = null;
        if (this.videoPlayView.getParent() == null) {
            addVideoView();
        }
        JDImageUtils.displayImage(str, (ImageView) this.coverImg, this.jdDisplayImageOptions, true);
        this.coverImg.setVisibility(0);
    }

    public void toPlayVideo(final RecommendVideoPlayState recommendVideoPlayState, final boolean z) {
        RecommendVideo recommendVideo;
        this.isToPlay = true;
        this.videoPlayStateListener = recommendVideoPlayState;
        if (isVideoState() && (recommendVideo = this.videoData) != null) {
            if (!TextUtils.isEmpty(recommendVideo.getVideoUrl())) {
                playVideo(z);
            } else if (!TextUtils.isEmpty(this.videoData.videoId)) {
                HttpGroupUtils.getHttpGroupaAsynPool().add(RecommendDataLoader.getVideoPlayUrl(this.videoData, hashCode(), new RecommendDataLoader.OnRecommendGetVideoUrlListener() { // from class: com.jingdong.common.recommend.ui.video.RecommendVideoWidget.4
                    {
                        RecommendVideoWidget.this = this;
                    }

                    @Override // com.jingdong.common.recommend.RecommendDataLoader.OnRecommendGetVideoUrlListener
                    public void onError() {
                        RecommendVideoWidget recommendVideoWidget = RecommendVideoWidget.this;
                        if (recommendVideoWidget.isToPlay) {
                            recommendVideoWidget.post(new Runnable() { // from class: com.jingdong.common.recommend.ui.video.RecommendVideoWidget.4.2
                                {
                                    AnonymousClass4.this = this;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    RecommendVideoPlayState recommendVideoPlayState2 = recommendVideoPlayState;
                                    if (recommendVideoPlayState2 != null) {
                                        recommendVideoPlayState2.onError(0, 0);
                                    }
                                }
                            });
                        }
                    }

                    @Override // com.jingdong.common.recommend.RecommendDataLoader.OnRecommendGetVideoUrlListener
                    public void onSucceed(String str, int i2) {
                        RecommendVideoWidget.this.post(new Runnable() { // from class: com.jingdong.common.recommend.ui.video.RecommendVideoWidget.4.1
                            {
                                AnonymousClass4.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                RecommendVideoWidget recommendVideoWidget = RecommendVideoWidget.this;
                                if (recommendVideoWidget.isToPlay) {
                                    recommendVideoWidget.playVideo(z);
                                }
                            }
                        });
                    }

                    @Override // com.jingdong.common.recommend.RecommendDataLoader.OnRecommendGetVideoUrlListener
                    public void onSucceed(String str, String str2) {
                    }
                }));
            } else if (recommendVideoPlayState != null) {
                recommendVideoPlayState.onError(0, 0);
            }
        }
        if (isLiveState()) {
            if (TextUtils.isEmpty(this.liveVideoEntity.mUrl)) {
                RecommendVideoPlayState recommendVideoPlayState2 = this.videoPlayStateListener;
                if (recommendVideoPlayState2 != null) {
                    recommendVideoPlayState2.onError(0, 0);
                    return;
                }
                return;
            }
            this.liveVideoLayout.play();
        }
    }

    public RecommendVideoWidget(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.jdDisplayImageOptions = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.RGB_565).setReferer(RecommendConstant.HTTP_REFER);
        this.loopPlay = false;
        this.canPlayWithNoWifi = false;
        this.isToPlay = false;
        initView();
    }

    public RecommendVideoWidget(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.jdDisplayImageOptions = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.RGB_565).setReferer(RecommendConstant.HTTP_REFER);
        this.loopPlay = false;
        this.canPlayWithNoWifi = false;
        this.isToPlay = false;
        initView();
    }

    @RequiresApi(api = 21)
    public RecommendVideoWidget(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.jdDisplayImageOptions = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.RGB_565).setReferer(RecommendConstant.HTTP_REFER);
        this.loopPlay = false;
        this.canPlayWithNoWifi = false;
        this.isToPlay = false;
        initView();
    }
}
