package com.jingdong.content.component.widget.videocontrol;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.content.component.R;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;

/* loaded from: classes12.dex */
public class ContentCustomVideoView extends FrameLayout {
    public static final int VOICE_OFF = 0;
    public static final int VOICE_ON = 1;
    private String TAG;
    boolean isVoiceOn;
    private float mCornerRadius;
    private JDVideoView mVideoView;
    public String playTypeId;

    public ContentCustomVideoView(@NonNull Context context) {
        super(context);
        this.TAG = "CustomVideoView";
        this.playTypeId = "";
        this.isVoiceOn = false;
        this.mCornerRadius = 0.0f;
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.content_custom_video_view, this);
        this.mVideoView = (JDVideoView) findViewById(R.id.video_view);
    }

    public void configVideoView(String str, boolean z, boolean z2, float f2, boolean z3) {
        if (this.mVideoView == null) {
            return;
        }
        IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
        playerOptions.setCouldMediaCodec(true);
        playerOptions.setReconnectCount(2);
        playerOptions.setVolume(z ? 1.0f : 0.0f);
        playerOptions.setAspectRatio(1);
        playerOptions.setPlayTypeId(this.playTypeId);
        playerOptions.setUseCache(true);
        if (TextUtils.isEmpty(str)) {
            str = "-100";
        }
        playerOptions.setPlayerReportInfoEntity(new PlayerReportInfoEntity(str, this.playTypeId));
        playerOptions.setIsRequestAudioFocus(z2);
        playerOptions.setSharePlayer(z3);
        this.mVideoView.setPlayerOptions(playerOptions);
        setCornerRadius(f2);
    }

    public JDVideoView getVideoView() {
        return this.mVideoView;
    }

    public JDVideoView getmVideoView() {
        return this.mVideoView;
    }

    public boolean isPlaying() {
        JDVideoView jDVideoView = this.mVideoView;
        if (jDVideoView != null) {
            return jDVideoView.isPlaying();
        }
        return false;
    }

    public void pauseVideo() {
        JDVideoView jDVideoView = this.mVideoView;
        if (jDVideoView != null) {
            jDVideoView.pause();
        }
    }

    public void releaseInThread() {
        JDVideoView jDVideoView = this.mVideoView;
        if (jDVideoView != null) {
            jDVideoView.releaseInThread(true);
        }
    }

    public void setCornerRadius(float f2) {
        JDVideoView jDVideoView = this.mVideoView;
        if (jDVideoView == null || this.mCornerRadius == f2) {
            return;
        }
        jDVideoView.setCorner(f2, f2, f2, f2);
        this.mCornerRadius = f2;
    }

    public void setListener() {
        JDVideoView jDVideoView = this.mVideoView;
        if (jDVideoView != null) {
            jDVideoView.setOnPlayerStateListener(new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.content.component.widget.videocontrol.ContentCustomVideoView.1
                {
                    ContentCustomVideoView.this = this;
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public void onCompletion() {
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public void onCreatePlayer() {
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public boolean onError(int i2, int i3) {
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
        }
    }

    public void setPlayTypeId(String str) {
        this.playTypeId = str;
    }

    public void setVideoUrl(String str) {
        JDVideoView jDVideoView = this.mVideoView;
        if (jDVideoView == null) {
            return;
        }
        jDVideoView.setVideoPath(str);
    }

    public void setVoiceOn(boolean z) {
        this.isVoiceOn = z;
        JDVideoView jDVideoView = this.mVideoView;
        if (jDVideoView != null) {
            jDVideoView.setVolume(z ? 1.0f : 0.0f);
        }
    }

    public void startVideo() {
        JDVideoView jDVideoView = this.mVideoView;
        if (jDVideoView != null) {
            jDVideoView.start();
        }
    }

    public ContentCustomVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "CustomVideoView";
        this.playTypeId = "";
        this.isVoiceOn = false;
        this.mCornerRadius = 0.0f;
        init(context);
    }

    public ContentCustomVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = "CustomVideoView";
        this.playTypeId = "";
        this.isVoiceOn = false;
        this.mCornerRadius = 0.0f;
        init(context);
    }
}
