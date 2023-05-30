package com.jingdong.common.jdreactFramework.views.pureVideo;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.jingdong.app.mall.R;
import com.jingdong.common.jdreactFramework.views.pureVideo.RCTVideoPlayView;
import com.jingdong.common.unification.video.player.AVideoPlayStateListener;
import com.jingdong.common.unification.video.player.IProgrssChangeListener;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PureVideoPlayView extends FrameLayout implements LifecycleEventListener {
    private static final String TAG = PureVideoPlayView.class.getName();
    private boolean isMute;
    private boolean isShowVoiceIcon;
    ThemedReactContext mContext;
    private GestureDetector mGestureDetector;
    @PlayStatus
    int mPlayStatus;
    RCTVideoPlayView videoView;

    /* loaded from: classes5.dex */
    @interface PlayStatus {
        public static final int STATUS_COMPLETE = 3;
        public static final int STATUS_ERROR = 4;
        public static final int STATUS_IDLE = 0;
        public static final int STATUS_PAUSE = 2;
        public static final int STATUS_PLAYING = 1;
    }

    /* loaded from: classes5.dex */
    public enum VideoEvent {
        PREPARED("onPrepared"),
        PROGRESS("onProgress"),
        ERROR("onErrorHappened"),
        COMPLETION("onCompletion"),
        NETWORK_CHANGE("onNetWorkStateChangedEvent"),
        BUFFER_UPDATE("onBufferUpdate"),
        VOICE_STATE_CHANGE("onVoiceStateChange"),
        STATE_CHANGE("onStateChangeEvent"),
        DURATION_OBTAINED("onDurationObtained"),
        CREATE_PLAYER("onCreatePlayer"),
        SEEK("onSeek"),
        SEEK_COMPLETE("onSeekComplete"),
        DOUBLE_CLICK("onDoubleClick"),
        SINGLE_CLICK("onClick");
        
        private String mName;

        VideoEvent(String str) {
            this.mName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mName;
        }
    }

    public PureVideoPlayView(@NonNull ThemedReactContext themedReactContext) {
        super(themedReactContext);
        this.isShowVoiceIcon = true;
        this.isMute = true;
        this.mContext = themedReactContext;
        initView();
        initListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b() {
        measure(View.MeasureSpec.makeMeasureSpec(getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getHeight(), 1073741824));
        layout(getLeft(), getTop(), getRight(), getBottom());
    }

    private void getDurationAsync(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() { // from class: com.jingdong.common.jdreactFramework.views.pureVideo.PureVideoPlayView.5
            @Override // java.lang.Runnable
            public void run() {
                String str2;
                StringBuilder sb;
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                int i2 = 0;
                try {
                    try {
                        if (Build.VERSION.SDK_INT >= 14) {
                            mediaMetadataRetriever.setDataSource(str, new HashMap());
                        } else {
                            mediaMetadataRetriever.setDataSource(str);
                        }
                        i2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
                        try {
                            mediaMetadataRetriever.release();
                        } catch (RuntimeException unused) {
                        }
                        str2 = PureVideoPlayView.TAG;
                        sb = new StringBuilder();
                    } catch (Exception e2) {
                        OKLog.e(PureVideoPlayView.TAG, e2.toString());
                        try {
                            mediaMetadataRetriever.release();
                        } catch (RuntimeException unused2) {
                        }
                        str2 = PureVideoPlayView.TAG;
                        sb = new StringBuilder();
                    }
                    sb.append("durationObtained  during: ");
                    sb.append(i2);
                    OKLog.i(str2, sb.toString());
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("duration", i2);
                    PureVideoPlayView.this.dispatchRNEvent(VideoEvent.DURATION_OBTAINED.toString(), createMap);
                } catch (Throwable th) {
                    try {
                        mediaMetadataRetriever.release();
                    } catch (RuntimeException unused3) {
                    }
                    OKLog.i(PureVideoPlayView.TAG, "durationObtained  during: 0");
                    WritableMap createMap2 = Arguments.createMap();
                    createMap2.putInt("duration", 0);
                    PureVideoPlayView.this.dispatchRNEvent(VideoEvent.DURATION_OBTAINED.toString(), createMap2);
                    throw th;
                }
            }
        });
    }

    private void initListener() {
        this.videoView.setOnPlayerStateListener(new AVideoPlayStateListener() { // from class: com.jingdong.common.jdreactFramework.views.pureVideo.PureVideoPlayView.4
            /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
            /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
            @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onCompletion() {
                boolean z;
                super.onCompletion();
                int duration = PureVideoPlayView.this.videoView.getDuration();
                int currentPosition = PureVideoPlayView.this.videoView.getCurrentPosition();
                if (duration > 0 && currentPosition > 0) {
                    double d = duration;
                    Double.isNaN(d);
                    if (currentPosition < d * 0.95d && currentPosition + 3000 < duration) {
                        z = true;
                        PureVideoPlayView.this.setPlayStatus(!z ? 4 : 3);
                        PureVideoPlayView.this.onCompletion();
                    }
                }
                z = false;
                PureVideoPlayView.this.setPlayStatus(!z ? 4 : 3);
                PureVideoPlayView.this.onCompletion();
            }

            @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
                super.onCreatePlayer();
                OKLog.i(PureVideoPlayView.TAG, "onCreatePlayer");
                PureVideoPlayView.this.dispatchRNEvent(VideoEvent.CREATE_PLAYER.toString(), Arguments.createMap());
            }

            @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener
            public boolean onCustomCompletion() {
                return super.onCustomCompletion();
            }

            @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                super.onError(i2, i3);
                OKLog.i(PureVideoPlayView.TAG, "onError what: " + i2 + "  extra: " + i3);
                PureVideoPlayView.this.setPlayStatus(4);
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("type", NetUtils.isNetworkAvailable() ? 1 : 0);
                createMap.putInt("what", i2);
                createMap.putInt("extra", i3);
                PureVideoPlayView.this.dispatchRNEvent(VideoEvent.ERROR.toString(), createMap);
                return true;
            }

            @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                super.onInfo(i2, i3);
                OKLog.i(PureVideoPlayView.TAG, "OnPlayerStateListener.onInfo, what=" + i2 + ", extra=" + i3);
                if (i2 == 3) {
                    OKLog.i(PureVideoPlayView.TAG, "\u5f00\u59cb\u6e32\u67d3\u89c6\u9891\u7b2c\u4e00\u5e27\u753b\u9762");
                } else if (i2 == 701) {
                    OKLog.i(PureVideoPlayView.TAG, "\u5f00\u59cb\u7f13\u51b2");
                    PureVideoPlayView.this.dispatchBufferEvent(0);
                } else if (i2 == 702) {
                    OKLog.i(PureVideoPlayView.TAG, "\u7ed3\u675f\u7f13\u51b2");
                    PureVideoPlayView.this.dispatchBufferEvent(1);
                }
                return false;
            }

            @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                super.onPrepared(j2);
                OKLog.i(PureVideoPlayView.TAG, "onPrepared, loadCost:" + j2);
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("duration", (int) j2);
                PureVideoPlayView.this.dispatchRNEvent(VideoEvent.PREPARED.toString(), createMap);
            }

            @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
                super.onSeekComplete();
                OKLog.i(PureVideoPlayView.TAG, "onSeekComplete");
                PureVideoPlayView.this.dispatchRNEvent(VideoEvent.SEEK_COMPLETE.toString(), Arguments.createMap());
            }
        });
    }

    private void initView() {
        FrameLayout.inflate(getContext(), R.layout.pure_video_view_old, this);
        this.videoView = (RCTVideoPlayView) findViewById(R.id.rct_video_view);
        this.mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.jingdong.common.jdreactFramework.views.pureVideo.PureVideoPlayView.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                OKLog.i(PureVideoPlayView.TAG, "onDoubleClick");
                PureVideoPlayView.this.dispatchRNEvent(VideoEvent.DOUBLE_CLICK.toString(), Arguments.createMap());
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                OKLog.i(PureVideoPlayView.TAG, "onClick");
                PureVideoPlayView.this.dispatchRNEvent(VideoEvent.SINGLE_CLICK.toString(), Arguments.createMap());
                return true;
            }
        });
        this.videoView.setListener(new RCTVideoPlayView.RCTVideoPlayViewListener() { // from class: com.jingdong.common.jdreactFramework.views.pureVideo.PureVideoPlayView.2
            @Override // com.jingdong.common.jdreactFramework.views.pureVideo.RCTVideoPlayView.RCTVideoPlayViewListener
            public void netChange() {
                OKLog.i(PureVideoPlayView.TAG, "netWorkChange");
                WritableMap createMap = Arguments.createMap();
                createMap.putString("net", NetUtils.getNetworkType());
                PureVideoPlayView.this.dispatchRNEvent(VideoEvent.NETWORK_CHANGE.toString(), createMap);
            }
        });
        this.videoView.setProgrssChangeListener(new IProgrssChangeListener() { // from class: com.jingdong.common.jdreactFramework.views.pureVideo.PureVideoPlayView.3
            @Override // com.jingdong.common.unification.video.player.IProgrssChangeListener, tv.danmaku.ijk.media.widget.uniform.inter.IJDProgressChangeListener
            public void onProgressChange(int i2, int i3) {
                OKLog.i(PureVideoPlayView.TAG, "onProgressChange progress: " + i2 + "  duration: " + i3);
                WritableMap createMap = Arguments.createMap();
                createMap.putInt(NotificationCompat.CATEGORY_PROGRESS, i2);
                createMap.putInt("duration", i3);
                PureVideoPlayView.this.dispatchRNEvent(VideoEvent.PROGRESS.toString(), createMap);
            }

            @Override // com.jingdong.common.unification.video.player.IProgrssChangeListener, tv.danmaku.ijk.media.widget.uniform.inter.IJDProgressChangeListener
            public void onProgressPointSelect(int i2) {
            }
        });
        ThemedReactContext themedReactContext = this.mContext;
        if (themedReactContext != null) {
            themedReactContext.addLifecycleEventListener(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayStatus(@PlayStatus int i2) {
        this.mPlayStatus = i2;
        dispatchStateChangeEvent(i2);
    }

    public void destroy() {
        RCTVideoPlayView rCTVideoPlayView = this.videoView;
        if (rCTVideoPlayView != null) {
            rCTVideoPlayView.onDestroy();
        }
        ThemedReactContext themedReactContext = this.mContext;
        if (themedReactContext != null) {
            themedReactContext.removeLifecycleEventListener(this);
        }
    }

    public void dispatchBufferEvent(int i2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("buffer", i2);
        dispatchRNEvent(VideoEvent.BUFFER_UPDATE.toString(), createMap);
    }

    public void dispatchRNEvent(String str, WritableMap writableMap) {
        Context context = getContext();
        if (context instanceof ReactContext) {
            OKLog.i(TAG, "dispatchEvent, eventName:" + str + ", eventData:" + writableMap);
            try {
                ((RCTEventEmitter) ((ReactContext) context).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), str, writableMap);
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void dispatchStateChangeEvent(int i2) {
        OKLog.i(TAG, "stateChange state: " + i2);
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("SHRNAVPlayerStateKey", i2);
        dispatchRNEvent(VideoEvent.STATE_CHANGE.toString(), createMap);
    }

    public int getDuration() {
        RCTVideoPlayView rCTVideoPlayView = this.videoView;
        if (rCTVideoPlayView != null) {
            return rCTVideoPlayView.getDuration();
        }
        return 0;
    }

    public RCTVideoPlayView getVideoView() {
        return this.videoView;
    }

    public void onCompletion() {
        OKLog.i(TAG, "onCompletion");
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("complete", 1);
        dispatchRNEvent(VideoEvent.COMPLETION.toString(), createMap);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        OKLog.i(TAG, "onHostDestroy");
        destroy();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        OKLog.i(TAG, "onHostPause");
        RCTVideoPlayView rCTVideoPlayView = this.videoView;
        if (rCTVideoPlayView != null) {
            rCTVideoPlayView.pausePlay();
        }
        setPlayStatus(2);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        OKLog.i(TAG, "onHostResume");
        RCTVideoPlayView rCTVideoPlayView = this.videoView;
        if (rCTVideoPlayView != null) {
            rCTVideoPlayView.requestLayout();
            this.videoView.onResume();
            this.videoView.invalidate();
            this.videoView.requestLayout();
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 == 0) {
            requestLayout();
        }
    }

    public void pause() {
        OKLog.i(TAG, "pause");
        RCTVideoPlayView rCTVideoPlayView = this.videoView;
        if (rCTVideoPlayView != null && rCTVideoPlayView.isPlaying()) {
            this.videoView.pausePlay();
        }
        setPlayStatus(2);
    }

    public void play() {
        OKLog.i(TAG, "play");
        RCTVideoPlayView rCTVideoPlayView = this.videoView;
        if (rCTVideoPlayView != null && !rCTVideoPlayView.isPlaying()) {
            this.videoView.startAndRetry();
        }
        setPlayStatus(1);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.views.pureVideo.a
            @Override // java.lang.Runnable
            public final void run() {
                PureVideoPlayView.this.b();
            }
        });
    }

    public void seekTo(int i2) {
        OKLog.i(TAG, "Seek to: " + i2);
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("onSeek", i2);
        dispatchRNEvent(VideoEvent.SEEK.toString(), createMap);
        RCTVideoPlayView rCTVideoPlayView = this.videoView;
        if (rCTVideoPlayView != null) {
            rCTVideoPlayView.seekToPosition(i2);
        }
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        this.videoView.setGestureDetector(z ? this.mGestureDetector : null);
    }

    public void setVideoPath(String str, RCTVideoPlayView.AutoPlayEnum autoPlayEnum) {
        RCTVideoPlayView rCTVideoPlayView = this.videoView;
        if (rCTVideoPlayView != null) {
            setPlayStatus(rCTVideoPlayView.setVideoPath(str, autoPlayEnum) ? 1 : 0);
        }
        getDurationAsync(str);
    }

    public void setVoiceMute(boolean z) {
        OKLog.i(TAG, "voiceStateChange");
        RCTVideoPlayView rCTVideoPlayView = this.videoView;
        if (rCTVideoPlayView != null) {
            rCTVideoPlayView.setShowVoice(false, z);
            this.videoView.setShowBottomVoice(false, z);
            this.videoView.setVoiceState(!z);
            WritableMap createMap = Arguments.createMap();
            createMap.putBoolean("isVideoMute", z);
            dispatchRNEvent(VideoEvent.VOICE_STATE_CHANGE.toString(), createMap);
        }
    }

    public void stop() {
        OKLog.i(TAG, "stop");
        RCTVideoPlayView rCTVideoPlayView = this.videoView;
        if (rCTVideoPlayView != null) {
            rCTVideoPlayView.stopPlay();
        }
        setPlayStatus(2);
    }
}
