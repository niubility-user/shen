package com.jd.lib.flexcube.owidgets.view.video;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.jd.lib.flexcube.iwidget.entity.material.FlexVideoModel;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.jdreactFramework.SpecialMtaConstants;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.widget.JDPlayerView;
import tv.danmaku.ijk.media.widget.controller.JDControllerOptions;

/* loaded from: classes15.dex */
public class BabelVideoPlayerViewV2 extends FrameLayout {
    JDControllerOptions build;
    int handState;
    int handStateTemp;
    private boolean isRelease;
    JDPlayerView jdPlayerView;
    FlexVideoModel mVideoModel;
    PlayerStateListenerV2 playerStateListener;

    /* loaded from: classes15.dex */
    public interface PlayerStateListenerV2 {
        void onCompletion();

        boolean onError(int i2, int i3);

        void onPlayState(int i2, int i3);

        void release();
    }

    public BabelVideoPlayerViewV2(@NonNull Context context) {
        super(context);
        this.isRelease = false;
        this.handState = 0;
        this.handStateTemp = 1;
    }

    public int getCurrentPosition() {
        JDPlayerView jDPlayerView = this.jdPlayerView;
        if (jDPlayerView != null) {
            return jDPlayerView.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration() {
        JDPlayerView jDPlayerView = this.jdPlayerView;
        if (jDPlayerView != null) {
            return jDPlayerView.getDuration();
        }
        return 0;
    }

    public float getVolume() {
        JDVideoView videoView;
        JDPlayerView jDPlayerView = this.jdPlayerView;
        if (jDPlayerView == null || (videoView = jDPlayerView.getVideoView(false)) == null) {
            return -1.0f;
        }
        return videoView.getVolume();
    }

    public void initPlayer(FlexVideoModel flexVideoModel) {
        IPlayerControl.PlayerOptions playerOptions;
        this.isRelease = false;
        removeView(this.jdPlayerView);
        Activity activity = null;
        this.jdPlayerView = null;
        this.mVideoModel = flexVideoModel;
        if (flexVideoModel == null) {
            return;
        }
        JDPlayerView jDPlayerView = new JDPlayerView(getContext());
        this.jdPlayerView = jDPlayerView;
        jDPlayerView.setBackgroundColor(this.mVideoModel.getBgColor());
        addView(this.jdPlayerView, new ViewGroup.LayoutParams(-1, -1));
        if (this.mVideoModel.isNewWindowPlay()) {
            this.jdPlayerView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BabelVideoPlayerViewV2.this.jdPlayerView.requestScreenChange(0);
                    if (BabelVideoPlayerViewV2.this.jdPlayerView.isFullScreen()) {
                        BabelVideoPlayerViewV2.this.jdPlayerView.forceChangeControlVisible(0);
                    }
                }
            });
        } else {
            this.jdPlayerView.setClickable(false);
        }
        if ("2".equals(flexVideoModel.getPlayerType())) {
            playerOptions = new IPlayerControl.PlayerOptions(true);
        } else {
            playerOptions = new IPlayerControl.PlayerOptions(false);
        }
        playerOptions.setPlayTypeId(SpecialMtaConstants.JDReact_ModuleUpgradeFailed);
        playerOptions.setCouldMediaCodec(true);
        playerOptions.setEnableAccurateSeek(true);
        playerOptions.showControllerOnStart(false);
        playerOptions.setReconnectCount(2);
        if ("2".equals(flexVideoModel.getPlayerType())) {
            playerOptions.setUseCache(false);
            playerOptions.setLoop(false);
        } else {
            playerOptions.setUseCache(true);
            if (this.mVideoModel.isWifiPoolPlay()) {
                playerOptions.setLoop(true);
            }
        }
        if ("0".equals(flexVideoModel.getVideoFitType())) {
            playerOptions.setAspectRatio(1);
        } else if ("1".equals(flexVideoModel.getVideoFitType())) {
            playerOptions.setAspectRatio(3);
        } else {
            playerOptions.setAspectRatio(0);
        }
        if (this.mVideoModel.isMute()) {
            playerOptions.setVolume(0.0f);
        }
        playerOptions.setEnableReport(false);
        Context context = getContext();
        if (context instanceof Activity) {
            activity = (Activity) context;
        } else if (BaseFrameUtil.getInstance().getCurrentMyActivity() != null) {
            activity = (Activity) BaseFrameUtil.getInstance().getCurrentMyActivity();
        }
        JDControllerOptions build = new JDControllerOptions.Builder().fullMode(JDControllerOptions.FullMode.FULL_PORT).manualControlVisible(true).enableLoading(this.mVideoModel.isShowLoading()).activity(activity).enableNonWifiTip(this.mVideoModel.isShowNonWifiTip()).enableErrorTip(this.mVideoModel.isShowErrorTip()).build();
        this.build = build;
        this.jdPlayerView.setPlayerOptions(playerOptions, build);
        this.jdPlayerView.forceChangeControlVisible(4);
        this.jdPlayerView.addPlayerEventCallback(new IMediaPlayer.OnPlayerEventListener() { // from class: com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2.2
            /* JADX WARN: Code restructure failed: missing block: B:14:0x0016, code lost:
                if (r5 != 14) goto L22;
             */
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onPlayEvent(int r5) {
                /*
                    r4 = this;
                    com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2 r0 = com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2.this
                    com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2$PlayerStateListenerV2 r1 = r0.playerStateListener
                    if (r1 != 0) goto L7
                    return
                L7:
                    r2 = 1
                    if (r5 == r2) goto L75
                    r3 = 2
                    if (r5 == r3) goto L5b
                    r3 = 3
                    if (r5 == r3) goto L31
                    r3 = 9
                    if (r5 == r3) goto L19
                    r0 = 14
                    if (r5 == r0) goto L41
                    goto L51
                L19:
                    int r5 = r0.handState
                    if (r5 != 0) goto L20
                    r1.onPlayState(r2, r5)
                L20:
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r5.<init>()
                    r5.append(r4)
                    java.lang.String r0 = " PLAYER_RENDERING_START"
                    r5.append(r0)
                    r5.toString()
                    goto L8f
                L31:
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    r0.append(r4)
                    java.lang.String r1 = " PLAYER_COMPLETED"
                    r0.append(r1)
                    r0.toString()
                L41:
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    r0.append(r4)
                    java.lang.String r1 = " PLAYER_RELEASE"
                    r0.append(r1)
                    r0.toString()
                L51:
                    com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2 r0 = com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2.this
                    com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2$PlayerStateListenerV2 r1 = r0.playerStateListener
                    int r0 = r0.handState
                    r1.onPlayState(r5, r0)
                    goto L8f
                L5b:
                    int r5 = r0.handStateTemp
                    if (r5 != 0) goto L64
                    int r5 = r0.handState
                    r1.onPlayState(r3, r5)
                L64:
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r5.<init>()
                    r5.append(r4)
                    java.lang.String r0 = " PLAYER_PAUSE"
                    r5.append(r0)
                    r5.toString()
                    goto L8f
                L75:
                    r3 = 0
                    r0.handStateTemp = r3
                    int r0 = r0.handState
                    if (r0 != r2) goto L7f
                    r1.onPlayState(r5, r0)
                L7f:
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r5.<init>()
                    r5.append(r4)
                    java.lang.String r0 = " PLAYER_START"
                    r5.append(r0)
                    r5.toString()
                L8f:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2.AnonymousClass2.onPlayEvent(int):void");
            }
        });
        this.jdPlayerView.addPlayerControllerCallback(new JDPlayerView.PlayerControllerCallback() { // from class: com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2.3
            @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
            public void onNetChanged() {
            }

            @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
            public void onOrientationChanged(boolean z, int i2) {
                if (z) {
                    BabelVideoPlayerViewV2.this.jdPlayerView.forceChangeControlVisible(0);
                } else {
                    BabelVideoPlayerViewV2.this.jdPlayerView.forceChangeControlVisible(4);
                }
            }

            @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
            public void onPlayBtnClick(boolean z) {
                if (z) {
                    return;
                }
                BabelVideoPlayerViewV2 babelVideoPlayerViewV2 = BabelVideoPlayerViewV2.this;
                babelVideoPlayerViewV2.handState = 1;
                babelVideoPlayerViewV2.handStateTemp = 1;
                PlayerStateListenerV2 playerStateListenerV2 = babelVideoPlayerViewV2.playerStateListener;
                if (playerStateListenerV2 != null) {
                    playerStateListenerV2.onPlayState(2, 1);
                }
            }

            @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
            public void onProgressUpdate(boolean z, int i2, long j2, boolean z2) {
            }

            @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
            public void onVoiceBtnClick(boolean z) {
            }

            @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
            public void onVoiceStateChange(boolean z) {
            }

            @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
            public void seekBarOnSeek(int i2) {
            }
        });
        this.jdPlayerView.setPlayerStateListener(new IPlayerControl.OnPlayerStateListener() { // from class: com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2.4
            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                if (BabelVideoPlayerViewV2.this.jdPlayerView.isFullScreen()) {
                    BabelVideoPlayerViewV2.this.jdPlayerView.forceChangeControlVisible(0);
                    return;
                }
                FlexVideoModel flexVideoModel2 = BabelVideoPlayerViewV2.this.mVideoModel;
                if (flexVideoModel2 == null || flexVideoModel2.isWifiPoolPlay()) {
                    return;
                }
                PlayerStateListenerV2 playerStateListenerV2 = BabelVideoPlayerViewV2.this.playerStateListener;
                if (playerStateListenerV2 != null) {
                    playerStateListenerV2.onCompletion();
                }
                BabelVideoHandlerV2.releasePlayer();
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                PlayerStateListenerV2 playerStateListenerV2 = BabelVideoPlayerViewV2.this.playerStateListener;
                if (playerStateListenerV2 != null) {
                    playerStateListenerV2.onError(i2, i3);
                    return false;
                }
                return false;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                return false;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
            }
        });
        this.jdPlayerView.setVideoPath(this.mVideoModel.getVideoUrl(), JDPlayerView.PlayMode.AUTO_PLAY);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(@NonNull View view, int i2) {
        super.onVisibilityChanged(view, i2);
        if (i2 == 0 || this.isRelease) {
            return;
        }
        JDPlayerView jDPlayerView = this.jdPlayerView;
        if (jDPlayerView != null && jDPlayerView.isFullScreen()) {
            this.jdPlayerView.pause();
        } else {
            post(new Runnable() { // from class: com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2.5
                @Override // java.lang.Runnable
                public void run() {
                    BabelVideoHandlerV2.releasePlayer();
                }
            });
        }
    }

    public void releasePlayer() {
        if (!this.isRelease) {
            PlayerStateListenerV2 playerStateListenerV2 = this.playerStateListener;
            if (playerStateListenerV2 != null) {
                playerStateListenerV2.release();
            }
            JDPlayerView jDPlayerView = this.jdPlayerView;
            if (jDPlayerView != null) {
                jDPlayerView.release();
            }
        }
        this.isRelease = true;
    }

    public void setPlayerStateListener(PlayerStateListenerV2 playerStateListenerV2) {
        this.playerStateListener = playerStateListenerV2;
    }

    public void setVolume(float f2) {
        JDVideoView videoView;
        JDPlayerView jDPlayerView = this.jdPlayerView;
        if (jDPlayerView == null || (videoView = jDPlayerView.getVideoView(false)) == null || videoView.getVolume() == f2) {
            return;
        }
        videoView.setVolume(f2);
    }
}
