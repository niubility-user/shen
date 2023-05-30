package com.jingdong.common.widget.custom.livewidget.playerview;

import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.JsonParser;
import com.jingdong.common.widget.custom.livewidget.bean.LiveVideoEntity;
import com.jingdong.common.widget.custom.livewidget.bean.PlayerParamsUtils;
import com.jingdong.common.widget.custom.livewidget.util.LiveConstant;
import com.jingdong.common.widget.custom.livewidget.util.NetWorkManager;
import com.jingdong.common.widget.custom.livewidget.util.PlayerManager;
import com.jingdong.common.widget.custom.livewidget.util.VideoViewManager;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Field;
import java.util.HashMap;
import tv.danmaku.ijk.media.example.widget.media.IRenderView;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;
import tv.danmaku.ijk.media.example.widget.media.TextureRenderView;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

/* loaded from: classes12.dex */
public class VideoViewLayout extends RelativeLayout implements NetWorkManager.NetCall {
    public static final String JUMP_TAG = "VideoViewLayout";
    public static final int LIVE_SCREEN_ORIENTATION_LANDSCAPE = 1;
    public static final int LIVE_SCREEN_ORIENTATION_PORTRAIT = 0;
    private static final float SMALL_LANDSCAPE_VIDEO_VIEW_HEIGHT_RATIO = 0.5625f;
    public static final String TAG = "==VideoViewLayout";
    public static final String TRANSITION_ANIMATION_SHARE_NAME = "liveVideoShare";
    private long initPlayerEnd;
    private long initPlayerStart;
    private boolean isMicLink;
    private boolean isSmoothPlaying;
    private BaseActivity mActivity;
    private Bundle mActivityOptionsBundle;
    private boolean mAutoPlay;
    private Context mContext;
    private boolean mHasActivityJumpAnimation;
    public SimpleDraweeView mImgVideoContainerBg;
    private View.OnClickListener mInnerOnClickListener;
    private boolean mIsNeedKeepVideoView;
    public ImageView mIvVideoTempView;
    private String mJdLivePlayerOptionJsonStr;
    private String mJdReplayOptionJsonStr;
    private JumpEntity mJumpEntity;
    private LifecycleObserver mLifecycleObserver;
    public LiveVideoEntity mLiveVideoEntity;
    private boolean mMutePlay;
    private Uri mOpenAppUri;
    private PlayerManager mPlayEngin;
    private boolean mPlayOnlyInWifi;
    private long mPlayStartTime;
    private String mPlayTag;
    private AlphaAnimation mVideoAlphaAnimation;
    public FrameLayout mVideoParentLayout;
    private VideoStausListener mVideoStausListener;
    private long renderViewPagerStart;

    /* loaded from: classes12.dex */
    public interface VideoStausListener {
        void onDestory();

        void onError(int i2, String str);

        void onPlay();
    }

    public VideoViewLayout(@NonNull Context context) {
        this(context, null);
    }

    private void cancelVideoAnimation() {
        AlphaAnimation alphaAnimation = this.mVideoAlphaAnimation;
        if (alphaAnimation != null) {
            alphaAnimation.cancel();
        }
    }

    private void destoryPlayer() {
        NetWorkManager.getInstance().removeNetCall(this);
        tryDestoryPlayEngine();
    }

    private void disPlayImage() {
        if (this.mImgVideoContainerBg == null || this.mLiveVideoEntity == null) {
            return;
        }
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.showImageOnFail(this.mLiveVideoEntity.mPlaceHolderImgId);
        jDDisplayImageOptions.showImageOnLoading(this.mLiveVideoEntity.mPlaceHolderImgId);
        jDDisplayImageOptions.showImageForEmptyUri(this.mLiveVideoEntity.mPlaceHolderImgId);
        String str = (String) this.mImgVideoContainerBg.getTag();
        if (TextUtils.isEmpty(str) || !str.equals(this.mLiveVideoEntity.mIndexImage)) {
            LiveVideoEntity liveVideoEntity = this.mLiveVideoEntity;
            JDImageUtils.displayImage(liveVideoEntity.mIndexImage, this.mImgVideoContainerBg, jDDisplayImageOptions, liveVideoEntity.mNeedDefaultImg);
            this.mImgVideoContainerBg.setTag(this.mLiveVideoEntity.mIndexImage);
        }
    }

    @Nullable
    private BaseActivity getActivity() {
        BaseActivity baseActivity = this.mActivity;
        if (baseActivity != null || (this.mContext instanceof BaseActivity)) {
            Context context = this.mContext;
            return context instanceof BaseActivity ? (BaseActivity) context : baseActivity;
        }
        return null;
    }

    private String getChannelPlayStatus() {
        try {
            return JDJSON.parseObject(JDMobileConfig.getInstance().getConfig("liveroom", "config", "videoViewLayoutStatus", "")).getString(this.mLiveVideoEntity.mOrigin);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "0";
        }
    }

    private String getQuickPassSwitch() {
        try {
            return JDJSON.parseObject(JDMobileConfig.getInstance().getConfig("liveroom", "config", "quickPass", "")).getString(this.mLiveVideoEntity.mOrigin);
        } catch (Exception unused) {
            return "0";
        }
    }

    private String getQuickPassWithTransition() {
        try {
            return JDJSON.parseObject(JDMobileConfig.getInstance().getConfig("liveroom", "config", "quickPassWithTransition", "")).getString(this.mLiveVideoEntity.mOrigin);
        } catch (Exception unused) {
            return "0";
        }
    }

    private RelativeLayout.LayoutParams getSmallLandScapeVideoViewParams() {
        FrameLayout frameLayout = this.mVideoParentLayout;
        if (frameLayout != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) frameLayout.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            return layoutParams;
        }
        return null;
    }

    private void hideVideoView() {
        PlayerManager playerManager = this.mPlayEngin;
        if (playerManager == null || playerManager.getVideoView() == null) {
            return;
        }
        this.mPlayEngin.getVideoView().setVisibility(4);
    }

    private void init(Context context) {
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        this.mImgVideoContainerBg = simpleDraweeView;
        simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(this.mImgVideoContainerBg, new RelativeLayout.LayoutParams(-1, -1));
        FrameLayout frameLayout = new FrameLayout(context);
        this.mVideoParentLayout = frameLayout;
        addView(frameLayout, new RelativeLayout.LayoutParams(-1, -1));
        ImageView imageView = new ImageView(context);
        this.mIvVideoTempView = imageView;
        addView(imageView, new RelativeLayout.LayoutParams(-1, -1));
        super.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.livewidget.playerview.VideoViewLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (VideoViewLayout.this.mInnerOnClickListener != null) {
                    VideoViewLayout.this.mInnerOnClickListener.onClick(view);
                }
                VideoViewLayout.this.jump();
            }
        });
    }

    private void innerDestoryPlayEngine() {
        removeListener();
        cancelVideoAnimation();
        removeVideoViewTempBg();
        if (this.mPlayEngin != null) {
            FrameLayout frameLayout = this.mVideoParentLayout;
            if (frameLayout != null && frameLayout.getChildCount() != 0) {
                this.mPlayEngin.onDestroy();
            }
            this.mPlayEngin = null;
        }
        FrameLayout frameLayout2 = this.mVideoParentLayout;
        if (frameLayout2 != null) {
            frameLayout2.removeAllViews();
            this.mVideoParentLayout.clearDisappearingChildren();
        }
        sendPlayDurationTime();
    }

    private boolean isVideoViewVisible() {
        PlayerManager playerManager = this.mPlayEngin;
        return (playerManager == null || playerManager.getVideoView() == null || this.mPlayEngin.getVideoView().getVisibility() != 0) ? false : true;
    }

    private void playVideo(PlayerParamsUtils.PlayerParamsEntity playerParamsEntity) {
        LiveVideoEntity liveVideoEntity = this.mLiveVideoEntity;
        if (liveVideoEntity == null || TextUtils.isEmpty(liveVideoEntity.mLiveId)) {
            return;
        }
        innerDestoryPlayEngine();
        this.mPlayEngin = new PlayerManager(this.mContext, playerParamsEntity, new PlayerManager.IPlayVideoStatusCallBack() { // from class: com.jingdong.common.widget.custom.livewidget.playerview.VideoViewLayout.3
            @Override // com.jingdong.common.widget.custom.livewidget.util.PlayerManager.IPlayVideoStatusCallBack
            public void onStateResult(int i2, int i3) {
                VideoViewLayout.this.processStateResult(i2, i3);
            }
        }, this.mLiveVideoEntity.mLiveId);
        VideoViewManager.getInstance().addVideoViewLayout(this.mPlayTag, this);
        addVideoView();
    }

    private void preparePlayVideo(boolean z) {
        try {
            this.mJdLivePlayerOptionJsonStr = JDMobileConfig.getInstance().getConfig("liveroom", "livePlayOption", "option", "");
            this.mJdReplayOptionJsonStr = JDMobileConfig.getInstance().getConfig("liveroom", "replayOption", "option", "");
        } catch (Exception unused) {
        }
        LiveVideoEntity liveVideoEntity = this.mLiveVideoEntity;
        if (liveVideoEntity == null || TextUtils.isEmpty(liveVideoEntity.mUrl)) {
            return;
        }
        LiveVideoEntity liveVideoEntity2 = this.mLiveVideoEntity;
        playVideo(PlayerParamsUtils.buildLiveParams(liveVideoEntity2.mUrl, liveVideoEntity2.mStatus == 3, this.mJdLivePlayerOptionJsonStr, this.mJdReplayOptionJsonStr, z));
    }

    private void processMediaState(int i2, int i3) {
        if (i2 == 202) {
            OKLog.e(TAG, "====> play has completed");
            tryDestoryPlayEngine();
        } else if (i2 != 205) {
            if (i2 != 208) {
                if (i2 == 210) {
                    setLinkMic();
                    return;
                } else if (i2 != 211) {
                    return;
                } else {
                    setEndLinkMic();
                    return;
                }
            }
            this.initPlayerEnd = SystemClock.elapsedRealtime();
            OKLog.d(TAG, "\u64ad\u653e\u5668\u521d\u59cb\u5316\u8017\u65f6:" + ((this.initPlayerEnd - this.initPlayerStart) - PlayerManager.FIRST_FRAME_COST_TIME));
            startPlayAnimation();
        } else {
            if (i3 == 500008) {
                OKLog.e(TAG, "====> error PlayerEvent.PLAY_ERROR::StatusCode.PLAY_ERROR_NONETWORK:status" + i3);
                tryDestoryPlayEngine();
            } else if (i3 == 500001) {
                OKLog.e(TAG, "====> error PlayerEvent.PLAY_ERROR::StatusCode.PLAY_ERROR_PREPARE:status" + i3);
                tryDestoryPlayEngine();
            } else if (i3 == 600006) {
                tryDestoryPlayEngine();
            } else if (i3 == 510000) {
                hideVideoView();
                return;
            } else {
                OKLog.e(TAG, "====> error PlayerEvent.PLAY_ERROR::else:status" + i3);
                tryDestoryPlayEngine();
            }
            VideoStausListener videoStausListener = this.mVideoStausListener;
            if (videoStausListener != null) {
                videoStausListener.onError(i2, i3 + "");
            }
        }
    }

    private void processPlayerState(int i2) {
        if (i2 == 600004) {
            OKLog.e(TAG, "====> error StatusCode.MEDIADATA_NETWORK_ERROR:status" + i2);
        } else if (i2 == 600005) {
            OKLog.e(TAG, "====> error StatusCode.MEDIADATA_INTERNAL_ERROR:status" + i2);
        } else if (i2 == 600003) {
            OKLog.e(TAG, "====> error StatusCode.MEDIADATA_SERVER_ERROR:status" + i2);
        } else if (i2 == 500004) {
            OKLog.d(TAG, "====> StatusCode.PLAY_INFO_BUFFERING_START");
            this.isSmoothPlaying = false;
        } else if (i2 == 500005) {
            OKLog.d(TAG, "====> StatusCode.PLAY_INFO_BUFFERING_END");
            this.isSmoothPlaying = true;
            showVideoView();
        } else if (i2 == 500006) {
            this.isSmoothPlaying = true;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.mPlayStartTime = elapsedRealtime;
            OKLog.d(TAG, "\u9996\u5e27\u52a0\u8f7d\u8017\u65f6:" + (elapsedRealtime - this.initPlayerEnd));
            OKLog.d(TAG, "\u6574\u4f53\u8017\u65f6(Viewpager+\u64ad\u653e\u5668\u521d\u59cb\u5316+\u62c9\u6d41+\u9996\u5e27):" + (elapsedRealtime - this.renderViewPagerStart));
            OKLog.d(TAG, "====> StatusCode.PLAY_INFO_VIDEO_RENDERING_START");
            VideoStausListener videoStausListener = this.mVideoStausListener;
            if (videoStausListener != null) {
                videoStausListener.onPlay();
            }
        }
    }

    private void realPlay(boolean z) {
        NetWorkManager.getInstance().setNetCall(this).build();
        tryPlay(z);
    }

    private void registActivityLifeCycle(BaseActivity baseActivity) {
        if (baseActivity == null) {
            return;
        }
        if (this.mLifecycleObserver != null) {
            baseActivity.getLifecycle().removeObserver(this.mLifecycleObserver);
        }
        if (this.mLifecycleObserver == null) {
            this.mLifecycleObserver = new LifecycleObserver() { // from class: com.jingdong.common.widget.custom.livewidget.playerview.VideoViewLayout.4
                @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
                public void pause() {
                    VideoViewLayout.this.removeVideoViewTempBg();
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
                public void resume() {
                    boolean unused = VideoViewLayout.this.mAutoPlay;
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
                public void stop() {
                }
            };
        }
        baseActivity.getLifecycle().addObserver(this.mLifecycleObserver);
    }

    private void sendPlayDurationTime() {
        if (this.mPlayStartTime == 0) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        LiveVideoEntity liveVideoEntity = this.mLiveVideoEntity;
        jDJSONObject.put("live_component_channel", (Object) (liveVideoEntity == null ? "-100" : liveVideoEntity.mOrigin));
        jDJSONObject.put("liveroom_play_time", (Object) ((SystemClock.elapsedRealtime() - this.mPlayStartTime) + ""));
        JDMtaUtils.sendExposureDataWithExt(this.mContext, "live_component_viewtime", "", "", "", "", jDJSONObject.toJSONString(), null);
        this.mPlayStartTime = 0L;
    }

    private void sendViewExpoMta() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        LiveVideoEntity liveVideoEntity = this.mLiveVideoEntity;
        jDJSONObject.put("live_component_channel", (Object) (liveVideoEntity == null ? "-100" : liveVideoEntity.mOrigin));
        JDMtaUtils.sendExposureDataWithExt(this.mContext, "live_component_expo", "", "", "", "", jDJSONObject.toJSONString(), null);
    }

    private void showVideoView() {
        PlayerManager playerManager = this.mPlayEngin;
        if (playerManager == null || playerManager.getVideoView() == null) {
            return;
        }
        this.mPlayEngin.getVideoView().setVisibility(0);
    }

    private void startPlayAnimation() {
        PlayerManager playerManager = this.mPlayEngin;
        if (playerManager == null || playerManager.getVideoView() == null) {
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        this.mVideoAlphaAnimation = alphaAnimation;
        alphaAnimation.setDuration(1000L);
        this.mPlayEngin.getVideoView().setAnimation(this.mVideoAlphaAnimation);
        this.mVideoAlphaAnimation.start();
    }

    private void storeVideoView() {
        PlayerManager playerManager = this.mPlayEngin;
        if (playerManager == null || this.mLiveVideoEntity == null || !playerManager.isPlaying()) {
            return;
        }
        this.mIsNeedKeepVideoView = true;
        IjkVideoView videoView = this.mPlayEngin.getVideoView();
        HashMap hashMap = new HashMap();
        hashMap.put(ReportConstant.CommonInfo.LIVE_ID, this.mLiveVideoEntity.mLiveId);
        hashMap.put(LiveConstant.KEY_VIDEO_VIEW_ORIGINAL, this.mLiveVideoEntity.mOrigin);
        hashMap.put(LiveConstant.KEY_VIDEO_VIEW_USAGE, LiveConstant.VideoViewUsage.FOR_SMOOTH_IN);
        hashMap.put("screen", this.mLiveVideoEntity.mVideoOrientation);
        hashMap.put("isMicLink", Boolean.valueOf(this.isMicLink));
        videoView.setTag(hashMap);
        VideoViewManager.getInstance().addPlayer(LiveConstant.IJK_VIDEO_VIEW_WITH_REPORT_FLAG, videoView);
    }

    private void storeVideoViewAndPrepareJump() {
        storeVideoView();
        removeListener();
        if (Build.VERSION.SDK_INT >= 21) {
            prepareTransitionAnimation();
        }
    }

    private void tryDestoryPlayEngine() {
        innerDestoryPlayEngine();
        VideoStausListener videoStausListener = this.mVideoStausListener;
        if (videoStausListener != null) {
            videoStausListener.onDestory();
        }
    }

    private void tryPlay(boolean z) {
        this.mMutePlay = z;
        LiveVideoEntity liveVideoEntity = this.mLiveVideoEntity;
        if (liveVideoEntity == null || TextUtils.isEmpty(liveVideoEntity.mUrl)) {
            return;
        }
        preparePlayVideo(z);
    }

    public void addVideoView() {
        FrameLayout frameLayout;
        IjkVideoView videoView = this.mPlayEngin.getVideoView();
        if (videoView == null || videoView.getParent() != null || (frameLayout = this.mVideoParentLayout) == null) {
            return;
        }
        frameLayout.removeAllViews();
        this.mVideoParentLayout.addView(videoView);
        RelativeLayout.LayoutParams layoutParams = null;
        LiveVideoEntity liveVideoEntity = this.mLiveVideoEntity;
        if (liveVideoEntity != null && "1".equals(liveVideoEntity.mVideoOrientation)) {
            layoutParams = getSmallLandScapeVideoViewParams();
        }
        if (layoutParams != null) {
            this.mVideoParentLayout.setLayoutParams(layoutParams);
        }
    }

    public boolean checkSwitchAndNotJumpWithTransition(String str, JDJSONObject jDJSONObject) {
        BaseActivity activity;
        if (TextUtils.isEmpty(str) || jDJSONObject == null || (activity = getActivity()) == null || !"1".equals(getQuickPassWithTransition())) {
            return true;
        }
        jDJSONObject.put(BaseActivity.DISPOSEABLE_UNABLE_ANIM, (Object) Boolean.FALSE);
        setVideoViewTempBg();
        jumpByTransitionAnimation(str, jDJSONObject.toJSONString(), activity);
        return false;
    }

    public PlayerManager getPlayEngin() {
        return this.mPlayEngin;
    }

    public boolean isVideoPlaying() {
        PlayerManager playerManager = this.mPlayEngin;
        if (playerManager == null || playerManager.getVideoView() == null) {
            return false;
        }
        return this.mPlayEngin.getVideoView().isPlaying();
    }

    public void jump(JumpEntity jumpEntity) {
        cancelVideoAnimation();
        if (jumpEntity == null || this.mLiveVideoEntity == null) {
            return;
        }
        if (!isVideoViewVisible()) {
            JumpUtil.execJump(this.mContext, jumpEntity, -1);
        } else if (!JumpUtil.VALUE_DES_LIVE_ROOM.equals(jumpEntity.des)) {
            JumpUtil.execJump(this.mContext, jumpEntity, -1);
        } else if (!this.isSmoothPlaying) {
            JumpUtil.execJump(this.mContext, jumpEntity, -1);
        } else {
            PlayerManager playerManager = this.mPlayEngin;
            if (playerManager != null && !playerManager.isPlaying()) {
                JumpUtil.execJump(this.mContext, jumpEntity, -1);
                return;
            }
            if (this.mPlayEngin != null) {
                jumpEntity.addParam(BaseActivity.DISPOSEABLE_UNABLE_ANIM, Boolean.FALSE);
                storeVideoViewAndChangeJumpStyle(jumpEntity);
            }
            if (this.mActivityOptionsBundle == null) {
                JumpUtil.execJump(getContext(), jumpEntity, -1);
            } else if (!TextUtils.isEmpty(jumpEntity.params) && checkSwitchAndNotJumpWithTransition(jumpEntity.des, JDJSON.parseObject(jumpEntity.params))) {
                JumpUtil.execJump(getContext(), jumpEntity, -1);
            }
        }
    }

    public void jumpByTransitionAnimation(final String str, String str2, final BaseActivity baseActivity) {
        final Bundle bundleFromJson = JumpUtil.getBundleFromJson(JsonParser.parseParamsJsonFromString(str2));
        bundleFromJson.putBundle("activityOptionsBundle", this.mActivityOptionsBundle);
        bundleFromJson.putString("jumpFrom", JUMP_TAG);
        post(new Runnable() { // from class: com.jingdong.common.widget.custom.livewidget.playerview.VideoViewLayout.2
            @Override // java.lang.Runnable
            public void run() {
                JumpUtil.execJumpByDes(str, baseActivity, bundleFromJson);
            }
        });
    }

    @Override // com.jingdong.common.widget.custom.livewidget.util.NetWorkManager.NetCall
    public void netCall(Context context, int i2) {
        if (i2 == 0) {
            tryDestoryPlayEngine();
        } else if (i2 == 1) {
            tryPlay(this.mMutePlay);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        sendViewExpoMta();
        if (this.mLiveVideoEntity != null && this.mAutoPlay) {
            try {
                play(this.mMutePlay);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        destoryPlayer();
        VideoViewManager.getInstance().removeVideoViewLayout(this.mPlayTag);
        BaseActivity activity = getActivity();
        if (activity != null && this.mLifecycleObserver != null) {
            activity.getLifecycle().removeObserver(this.mLifecycleObserver);
            this.mLifecycleObserver = null;
        }
        super.onDetachedFromWindow();
    }

    public void play(boolean z) {
        this.mIsNeedKeepVideoView = false;
        String channelPlayStatus = getChannelPlayStatus();
        if (!TextUtils.isEmpty(channelPlayStatus) && !"0".equals(channelPlayStatus)) {
            if ("1".equals(channelPlayStatus)) {
                return;
            }
            if ("2".equals(channelPlayStatus) && NetUtils.isWifi()) {
                realPlay(z);
            } else if ("3".equals(channelPlayStatus)) {
                realPlay(z);
            }
        } else if (!this.mPlayOnlyInWifi || NetUtils.isWifi()) {
            realPlay(z);
        }
    }

    @RequiresApi(api = 21)
    public void prepareTransitionAnimation() {
        BaseActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        this.mActivityOptionsBundle = ActivityOptions.makeSceneTransitionAnimation(activity, this, TRANSITION_ANIMATION_SHARE_NAME).toBundle();
    }

    public void processStateResult(int i2, int i3) {
        processPlayerState(i3);
        processMediaState(i2, i3);
    }

    public void removeListener() {
        PlayerManager playerManager = this.mPlayEngin;
        if (playerManager != null) {
            playerManager.removeListener();
        }
    }

    public void removeVideoViewTempBg() {
        ImageView imageView = this.mIvVideoTempView;
        if (imageView != null) {
            imageView.setImageDrawable(null);
            this.mIvVideoTempView.setVisibility(8);
        }
    }

    public void setAutoPlay(boolean z) {
        this.mAutoPlay = z;
    }

    @Deprecated
    public void setDataSource(LiveVideoEntity liveVideoEntity) {
        if (liveVideoEntity == null) {
            return;
        }
        this.mLiveVideoEntity = liveVideoEntity;
        String str = liveVideoEntity.mOrigin;
        if (str != null) {
            this.mPlayTag = str;
        }
        disPlayImage();
        registActivityLifeCycle(getActivity());
    }

    public void setEndLinkMic() {
        FrameLayout frameLayout = this.mVideoParentLayout;
        if (frameLayout != null) {
            frameLayout.invalidate();
            this.isMicLink = false;
        }
    }

    public void setHasActivityJumpAnimation(boolean z) {
        this.mHasActivityJumpAnimation = z;
    }

    public void setLinkMic() {
        FrameLayout frameLayout = this.mVideoParentLayout;
        if (frameLayout != null) {
            frameLayout.invalidate();
            this.isMicLink = true;
        }
    }

    public void setMutePlay(boolean z) {
        this.mMutePlay = z;
        PlayerManager playerManager = this.mPlayEngin;
        if (playerManager == null || playerManager.getVideoView() == null) {
            return;
        }
        this.mPlayEngin.getVideoView().setVolume(z ? 0.0f : 1.0f);
    }

    @Override // android.view.View
    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.mInnerOnClickListener = onClickListener;
    }

    public void setPlayOnlyInWIfi(boolean z) {
        this.mPlayOnlyInWifi = z;
    }

    public void setVideoStatusListener(VideoStausListener videoStausListener) {
        this.mVideoStausListener = videoStausListener;
    }

    public void setVideoViewTempBg() {
        PlayerManager playerManager = this.mPlayEngin;
        if (playerManager == null || this.mLiveVideoEntity == null || !playerManager.isPlaying()) {
            return;
        }
        IjkVideoView videoView = this.mPlayEngin.getVideoView();
        try {
            Field declaredField = videoView.getClass().getSuperclass().getDeclaredField("mRenderView");
            declaredField.setAccessible(true);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), ((TextureRenderView) ((IRenderView) declaredField.get(videoView)).getView()).getBitmap());
            this.mIvVideoTempView.setVisibility(0);
            this.mIvVideoTempView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            this.mIvVideoTempView.setImageDrawable(bitmapDrawable);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void stop() {
        if (this.mIsNeedKeepVideoView) {
            return;
        }
        destoryPlayer();
    }

    public void storeVideoViewAndChangeJumpStyle(JumpEntity jumpEntity) {
        if (jumpEntity != null && "1".equals(getQuickPassSwitch())) {
            storeVideoViewAndPrepareJump();
            if (this.mHasActivityJumpAnimation) {
                return;
            }
            jumpEntity.addParam(BaseActivity.DISPOSEABLE_UNABLE_ANIM, Boolean.TRUE);
        }
    }

    public VideoViewLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoViewLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.renderViewPagerStart = 0L;
        this.initPlayerStart = 0L;
        this.initPlayerEnd = 0L;
        this.mPlayStartTime = 0L;
        this.isMicLink = false;
        this.mPlayTag = "-1";
        this.mMutePlay = true;
        this.mPlayOnlyInWifi = true;
        this.mAutoPlay = true;
        this.mHasActivityJumpAnimation = true;
        this.isSmoothPlaying = false;
        init(context);
        this.mContext = context;
    }

    public void setDataSource(LiveVideoEntity liveVideoEntity, JumpEntity jumpEntity) {
        this.mJumpEntity = jumpEntity;
        setDataSource(liveVideoEntity);
    }

    public Uri storeVideoViewAndChangeJumpStyle(JDJSONObject jDJSONObject, Uri uri) {
        if (jDJSONObject == null) {
            return null;
        }
        if ("1".equals(getQuickPassSwitch())) {
            storeVideoViewAndPrepareJump();
            if (this.mHasActivityJumpAnimation) {
                return uri;
            }
            jDJSONObject.put(BaseActivity.DISPOSEABLE_UNABLE_ANIM, (Object) Boolean.TRUE);
            return Uri.parse(uri.getScheme() + "://" + uri.getHost() + "?params=" + jDJSONObject.toJSONString());
        }
        return uri;
    }

    public void setDataSource(BaseActivity baseActivity, LiveVideoEntity liveVideoEntity, JumpEntity jumpEntity) {
        this.mActivity = baseActivity;
        setDataSource(liveVideoEntity, jumpEntity);
    }

    public void setDataSource(BaseActivity baseActivity, LiveVideoEntity liveVideoEntity, Uri uri) {
        this.mActivity = baseActivity;
        setDataSource(liveVideoEntity, uri);
    }

    public void play() {
        play(this.mMutePlay);
    }

    public void setDataSource(LiveVideoEntity liveVideoEntity, Uri uri) {
        this.mOpenAppUri = uri;
        setDataSource(liveVideoEntity);
    }

    public void jump(Uri uri) {
        String str;
        cancelVideoAnimation();
        if (uri == null || this.mLiveVideoEntity == null) {
            return;
        }
        String queryParameter = uri.getQueryParameter("params");
        if (TextUtils.isEmpty(queryParameter)) {
            return;
        }
        JDJSONObject jDJSONObject = null;
        try {
            jDJSONObject = JDJSON.parseObject(queryParameter);
            str = jDJSONObject.getString("des");
        } catch (Exception e2) {
            e2.printStackTrace();
            str = "";
        }
        if (!isVideoViewVisible()) {
            new OpenAppJumpBuilder.Builder(uri).build().jump(this.mContext);
        } else if (!JumpUtil.VALUE_DES_LIVE_ROOM.equals(str)) {
            new OpenAppJumpBuilder.Builder(uri).build().jump(this.mContext);
        } else if (!this.isSmoothPlaying) {
            new OpenAppJumpBuilder.Builder(uri).build().jump(this.mContext);
        } else {
            PlayerManager playerManager = this.mPlayEngin;
            if (playerManager != null && !playerManager.isPlaying()) {
                new OpenAppJumpBuilder.Builder(uri).build().jump(this.mContext);
                return;
            }
            if (this.mPlayEngin != null) {
                uri = storeVideoViewAndChangeJumpStyle(jDJSONObject, uri);
            }
            if (this.mActivityOptionsBundle == null) {
                new OpenAppJumpBuilder.Builder(uri).build().jump(this.mContext);
            } else if (!checkSwitchAndNotJumpWithTransition(str, jDJSONObject) || uri == null) {
            } else {
                new OpenAppJumpBuilder.Builder(uri).build().jump(this.mContext);
            }
        }
    }

    public void jump() {
        JumpEntity jumpEntity = this.mJumpEntity;
        if (jumpEntity != null) {
            jump(jumpEntity);
            return;
        }
        Uri uri = this.mOpenAppUri;
        if (uri != null) {
            jump(uri);
        }
    }
}
