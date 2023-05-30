package com.jingdong.common.jdreactFramework.views.videoview;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.common.widget.custom.CustomIjkPlayer;
import com.jingdong.common.widget.video.DefaultFullVideoChanger;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Map;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

/* loaded from: classes5.dex */
public class JDCustomIjkPlayerManager extends SimpleViewManager<CustomIjkPlayer> {
    private static final int BUFFER_END = 1;
    private static final int BUFFER_START = 0;
    private static final int CM_CHANGE_VOLUME = 1;
    private static final int CM_DESTROY = 4;
    private static final int CM_FULLSCREEN = 5;
    private static final int CM_NEEDAUTOTRANSFORM = 7;
    private static final int CM_PAUSE = 2;
    private static final int CM_PLAY = 3;
    private static final int CM_SEEK = 6;
    private static final int ERROR_NETWORK = 0;
    private static final int ERROR_PLAYER = 1;
    private static final String NET_STATUS_UNAVAILABLE = "unavailable";
    private static String TAG = "JDCustomIjkPlayerManager";

    /* loaded from: classes5.dex */
    public interface PlayState {
        public static final int DESTROY = 4;
        public static final int DRAG = 5;
        public static final int FULL_SCREEN = 2;
        public static final int NORMAL_SCRREN = 3;
        public static final int PAUSE = 1;
        public static final int PLAY = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class RCTCustomIjkPlayer extends CustomIjkPlayer implements IPlayerControl.OnPlayerStateListener, LifecycleEventListener {
        private DefaultFullVideoChanger fullChanger;
        private View.OnClickListener fullScreenClickListener;
        private boolean hideControlView;
        private boolean isCurrentFullScreen;
        private boolean isLoopPlay;
        private ThemedReactContext mContext;
        private JDReactMtaListener mtaListener;
        private String pendingUrl;
        private JDReactProgressListener progressListener;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes5.dex */
        public class JDReactMtaListener extends CustomIjkPlayer.MtaListener {
            JDReactMtaListener() {
            }

            @Override // com.jingdong.common.widget.custom.CustomIjkPlayer.MtaListener
            public void netChange() {
                OKLog.i(JDCustomIjkPlayerManager.TAG, "MtaListener.netChange");
                WritableMap createMap = Arguments.createMap();
                JDCustomIjkPlayerManager.addNetStatus(createMap);
                RCTCustomIjkPlayer.this.dispatchEvent(VideoEvent.NETWORK_CHANGE.toString(), createMap);
            }

            @Override // com.jingdong.common.widget.custom.CustomIjkPlayer.MtaListener
            public void playBtnOnClick(boolean z, int i2) {
                OKLog.i(JDCustomIjkPlayerManager.TAG, "MtaListener.playBtnOnClick, isPlay:" + z + ", playStatus:" + i2);
                RCTCustomIjkPlayer.this.dispatchButtonClickEvent(z ? "start" : "pause");
            }

            @Override // com.jingdong.common.widget.custom.CustomIjkPlayer.MtaListener
            public void playStatusChange(int i2) {
                OKLog.i(JDCustomIjkPlayerManager.TAG, "MtaListener.playStatusChange, playStatus: " + i2);
                int mapPlayState = JDCustomIjkPlayerManager.mapPlayState(i2);
                if (mapPlayState == -1) {
                    return;
                }
                RCTCustomIjkPlayer.this.dispatchStateChangeEvent(mapPlayState);
            }

            @Override // com.jingdong.common.widget.custom.CustomIjkPlayer.MtaListener
            public void seekBarOnSeek(long j2) {
                OKLog.i(JDCustomIjkPlayerManager.TAG, "MtaListener.seekBarOnSeek, position:" + j2);
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("onSeek", (int) j2);
                RCTCustomIjkPlayer.this.dispatchEvent(VideoEvent.SEEK.toString(), createMap);
            }

            @Override // com.jingdong.common.widget.custom.CustomIjkPlayer.MtaListener
            public void voiceBtnOnClick(boolean z) {
                OKLog.i(JDCustomIjkPlayerManager.TAG, "MtaListener.voiceBtnOnClick, isVoiceOn:" + z);
                RCTCustomIjkPlayer.this.dispatchButtonClickEvent(z ? "VoiceOn" : "VoiceOff");
            }

            @Override // com.jingdong.common.widget.custom.CustomIjkPlayer.MtaListener
            public void voiceStateChange(boolean z) {
                OKLog.i(JDCustomIjkPlayerManager.TAG, "MtaListener.voiceStateChange, isVoiceOn:" + z);
                WritableMap createMap = Arguments.createMap();
                createMap.putBoolean("isVoiceOn", z);
                RCTCustomIjkPlayer.this.dispatchEvent(VideoEvent.VOICE_STATE_CHANGE.toString(), createMap);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes5.dex */
        public class JDReactProgressListener extends CustomIjkPlayer.OnUpdatePositionListener {
            JDReactProgressListener() {
            }

            @Override // com.jingdong.common.widget.custom.CustomIjkPlayer.OnUpdatePositionListener
            public void updatePosition(boolean z, long j2, long j3, boolean z2) {
                OKLog.i(JDCustomIjkPlayerManager.TAG, "OnUpdatePositionListener.updatePosition, isPlay:" + z + ", position:" + j2 + ", duration:" + j3 + ", isBarInChanging:" + z2);
                WritableMap createMap = Arguments.createMap();
                createMap.putBoolean("isPlay", z);
                int i2 = (int) j2;
                createMap.putInt("position", i2);
                createMap.putInt("duration", (int) j3);
                createMap.putBoolean("isBarInChanging", z2);
                createMap.putInt(NotificationCompat.CATEGORY_PROGRESS, i2);
                RCTCustomIjkPlayer.this.dispatchEvent(VideoEvent.PROGRESS.toString(), createMap);
            }
        }

        public RCTCustomIjkPlayer(ThemedReactContext themedReactContext) {
            super(themedReactContext);
            this.isLoopPlay = false;
            this.hideControlView = false;
            this.mContext = themedReactContext;
            setOnPlayerStateListener(this);
            if (themedReactContext != null) {
                themedReactContext.addLifecycleEventListener(this);
            }
            JDReactMtaListener jDReactMtaListener = new JDReactMtaListener();
            this.mtaListener = jDReactMtaListener;
            setMtaListener(jDReactMtaListener);
            JDReactProgressListener jDReactProgressListener = new JDReactProgressListener();
            this.progressListener = jDReactProgressListener;
            setOnUpdatePositionListener(jDReactProgressListener);
            initFullScreenChanger();
            this.ijkPlayStatusBar.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.jdreactFramework.views.videoview.JDCustomIjkPlayerManager.RCTCustomIjkPlayer.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    RCTCustomIjkPlayer.this.requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });
        }

        private void initFullScreenChanger() {
            DefaultFullVideoChanger defaultFullVideoChanger = new DefaultFullVideoChanger(AbstractJDReactInitialHelper.getCurrentMyActivity(), this);
            this.fullChanger = defaultFullVideoChanger;
            defaultFullVideoChanger.setScreenChangeListener(new DefaultFullVideoChanger.ScreenChangeListener() { // from class: com.jingdong.common.jdreactFramework.views.videoview.JDCustomIjkPlayerManager.RCTCustomIjkPlayer.2
                @Override // com.jingdong.common.widget.video.DefaultFullVideoChanger.ScreenChangeListener
                public void onChange(boolean z, int i2) {
                    RCTCustomIjkPlayer.this.isCurrentFullScreen = z;
                    RCTCustomIjkPlayer.this.dispatchStateChangeEvent(z ? 2 : 3);
                }
            });
            View.OnClickListener fullBtnOnClickListener = this.fullChanger.getFullBtnOnClickListener();
            this.fullScreenClickListener = fullBtnOnClickListener;
            setFullBtnOnClickListener(fullBtnOnClickListener);
        }

        public void changeFullScreen(boolean z) {
            View.OnClickListener onClickListener = this.fullScreenClickListener;
            if (onClickListener != null) {
                if (z && this.isCurrentFullScreen) {
                    return;
                }
                if (z || this.isCurrentFullScreen) {
                    onClickListener.onClick(null);
                }
            }
        }

        public synchronized void destroy() {
            this.mtaListener = null;
            this.progressListener = null;
            sensorDisable();
            this.fullChanger = null;
            Context context = getContext();
            if (context instanceof ThemedReactContext) {
                ((ThemedReactContext) context).removeLifecycleEventListener(this);
            }
            suspend();
            releaseInThread();
        }

        public void dispatchBufferEvent(int i2) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("buffer", i2);
            dispatchEvent(VideoEvent.BUFFER_UPDATE.toString(), createMap);
        }

        public void dispatchButtonClickEvent(String str) {
            WritableMap createMap = Arguments.createMap();
            createMap.putString("buttonclick", str);
            dispatchEvent(VideoEvent.BUTTON_CLICK.toString(), createMap);
        }

        public void dispatchEvent(String str, WritableMap writableMap) {
            Context context = getContext();
            if (context == null || !(context instanceof ReactContext)) {
                return;
            }
            OKLog.i(JDCustomIjkPlayerManager.TAG, "dispatchEvent, eventName:" + str + ", eventData:" + writableMap);
            try {
                ((RCTEventEmitter) ((ReactContext) context).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), str, writableMap);
            } catch (Exception e2) {
                OKLog.e(JDCustomIjkPlayerManager.TAG, e2);
            }
        }

        public void dispatchStateChangeEvent(int i2) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("SHRNAVPlayerStateKey", i2);
            dispatchEvent(VideoEvent.STATE_CHANGE.toString(), createMap);
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCompletion() {
            OKLog.i(JDCustomIjkPlayerManager.TAG, "OnPlayerStateListener.onCompletion");
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("complete", 1);
            dispatchEvent(VideoEvent.COMPLETION.toString(), createMap);
            preformOnCompletion();
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCreatePlayer() {
            OKLog.i(JDCustomIjkPlayerManager.TAG, "OnPlayerStateListener.onCreatePlayer");
            this.pendingUrl = null;
            dispatchEvent(VideoEvent.CREATE_PLAYER.toString(), Arguments.createMap());
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onError(int i2, int i3) {
            OKLog.i(JDCustomIjkPlayerManager.TAG, "OnPlayerStateListener.onError what=" + i2 + "extra= " + i3);
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("type", NetUtils.isNetworkAvailable() ? 1 : 0);
            dispatchEvent(VideoEvent.ERROR.toString(), createMap);
            return true;
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
            OKLog.i(JDCustomIjkPlayerManager.TAG, "onHostDestroy");
            destroy();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
            OKLog.i(JDCustomIjkPlayerManager.TAG, "onHostPause");
            pauseIfPlaying();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
            OKLog.i(JDCustomIjkPlayerManager.TAG, "onHostResume");
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onInfo(int i2, int i3) {
            OKLog.i(JDCustomIjkPlayerManager.TAG, "OnPlayerStateListener.onInfo, what=" + i2 + ", extra=" + i3);
            if (i2 == 3) {
                OKLog.i(JDCustomIjkPlayerManager.TAG, "\u5f00\u59cb\u6e32\u67d3\u89c6\u9891\u7b2c\u4e00\u5e27\u753b\u9762");
            } else if (i2 == 701) {
                OKLog.i(JDCustomIjkPlayerManager.TAG, "\u5f00\u59cb\u7f13\u51b2");
                dispatchBufferEvent(0);
            } else if (i2 == 702) {
                OKLog.i(JDCustomIjkPlayerManager.TAG, "\u7ed3\u675f\u7f13\u51b2");
                dispatchBufferEvent(1);
            }
            return false;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onPrepared(long j2) {
            OKLog.i(JDCustomIjkPlayerManager.TAG, "OnPlayerStateListener.onPrepared, loadCost:" + j2);
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("duration", (int) j2);
            JDCustomIjkPlayerManager.addNetStatus(createMap);
            dispatchEvent(VideoEvent.PREPARED.toString(), createMap);
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onSeekComplete() {
            OKLog.i(JDCustomIjkPlayerManager.TAG, "OnPlayerStateListener.onSeekComplete");
            dispatchEvent(VideoEvent.SEEK_COMPLETE.toString(), Arguments.createMap());
        }

        public void pauseIfPlaying() {
            if (isPlaying()) {
                pause();
            }
        }

        public void playIfNotPlaying() {
            if (isPlaying()) {
                return;
            }
            if (!TextUtils.isEmpty(this.pendingUrl)) {
                setVideoPath(this.pendingUrl, true);
            } else if (isComplete()) {
                resume();
            } else {
                start();
            }
        }

        public void preformOnCompletion() {
            if (this.isLoopPlay) {
                playIfNotPlaying();
            }
        }

        public void sensorDisable() {
            DefaultFullVideoChanger defaultFullVideoChanger = this.fullChanger;
            if (defaultFullVideoChanger != null) {
                defaultFullVideoChanger.sensorDisable();
            }
        }

        public void sensorEnable() {
            DefaultFullVideoChanger defaultFullVideoChanger = this.fullChanger;
            if (defaultFullVideoChanger != null) {
                defaultFullVideoChanger.sensorEnable();
            }
        }

        public void setHideControlView(boolean z) {
            this.hideControlView = z;
        }

        public void setKeepScreenOn() {
            ThemedReactContext themedReactContext = this.mContext;
            if (themedReactContext == null || !themedReactContext.hasCurrentActivity() || this.mContext.getCurrentActivity() == null) {
                return;
            }
            setKeepScreenOnActivity(this.mContext.getCurrentActivity());
        }

        public void setLoopPlay(boolean z) {
            this.isLoopPlay = z;
        }

        public void setVideoPath(String str, boolean z) {
            if (StringUtil.isEmpty(str)) {
                return;
            }
            if (z) {
                setVideoPath(str);
                this.pendingUrl = null;
            } else {
                setVideoPathWithOutAutoPlay(str);
                this.pendingUrl = str;
            }
            if (this.hideControlView) {
                hideControlView();
            }
        }

        @Override // com.jingdong.common.widget.custom.CustomIjkPlayer
        public void showControlView() {
            if (this.hideControlView) {
                super.hideControlView();
            } else {
                super.showControlView();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum VideoEvent {
        PREPARED("onPrepared"),
        PROGRESS("onProgress"),
        ERROR("onErrorHappened"),
        COMPLETION("onCompletion"),
        BUTTON_CLICK("onButtonClick"),
        STATE_CHANGE("onStateChangeEvent"),
        NETWORK_CHANGE("onNetWorkStateChangeedEvent"),
        BUFFER_UPDATE("onBufferUpdate"),
        VOICE_STATE_CHANGE("onVoiceStateChange"),
        DURATION_OBTAINED("onDurationObtained"),
        CREATE_PLAYER("onCreatePlayer"),
        SEEK("onSeek"),
        SEEK_COMPLETE("onSeekComplete");
        
        private String mName;

        VideoEvent(String str) {
            this.mName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mName;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addNetStatus(WritableMap writableMap) {
        if (writableMap != null) {
            writableMap.putString("SHRNAVPlayerNetworkChangedKey", getNetworkStatus());
        }
    }

    private void getDurationAsync(final RCTCustomIjkPlayer rCTCustomIjkPlayer, final String str) {
        if (TextUtils.isEmpty(str) || rCTCustomIjkPlayer == null) {
            return;
        }
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() { // from class: com.jingdong.common.jdreactFramework.views.videoview.JDCustomIjkPlayerManager.1
            @Override // java.lang.Runnable
            public void run() {
                int i2;
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
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
                    } catch (Exception e2) {
                        OKLog.e(JDCustomIjkPlayerManager.TAG, e2.toString());
                        try {
                            mediaMetadataRetriever.release();
                        } catch (RuntimeException unused2) {
                        }
                        i2 = 0;
                    }
                    if (rCTCustomIjkPlayer != null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putInt("duration", i2);
                        rCTCustomIjkPlayer.dispatchEvent(VideoEvent.DURATION_OBTAINED.toString(), createMap);
                    }
                } catch (Throwable th) {
                    try {
                        mediaMetadataRetriever.release();
                    } catch (RuntimeException unused3) {
                    }
                    throw th;
                }
            }
        });
    }

    private static String getNetworkStatus() {
        return NetUtils.isNetworkAvailable() ? NetUtils.getNetworkType() : NET_STATUS_UNAVAILABLE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int mapPlayState(int i2) {
        int i3 = 1;
        if (i2 != 1) {
            if (i2 != 2) {
                i3 = 4;
                if (i2 != 4) {
                    return -1;
                }
            }
            return i3;
        }
        return 0;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        MapBuilder.Builder builder = MapBuilder.builder();
        builder.put("changeVolume", 1);
        builder.put("pause", 2);
        builder.put("play", 3);
        builder.put("destroy", 4);
        builder.put("fullScreen", 5);
        builder.put(JsApiVideoPlayer.CM_SEEK, 6);
        builder.put("needAutoTransform", 7);
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder builder = MapBuilder.builder();
        for (VideoEvent videoEvent : VideoEvent.values()) {
            builder.put(videoEvent.toString(), MapBuilder.of("registrationName", videoEvent.toString()));
        }
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDCustomIjkPlayer";
    }

    @ReactProp(name = "source")
    public void setSource(CustomIjkPlayer customIjkPlayer, @Nullable ReadableMap readableMap) {
        OKLog.i(TAG, "setSource:" + readableMap);
        if (readableMap == null || customIjkPlayer == null) {
            return;
        }
        try {
            String string = readableMap.hasKey("url") ? readableMap.getString("url") : "";
            if (readableMap.hasKey("imgUrl")) {
                customIjkPlayer.setBgImg(readableMap.getString("imgUrl"), R.drawable.jdreact_vd_player_fail_bg);
            }
            if (readableMap.hasKey("scaleType")) {
                customIjkPlayer.setAspectRatio(readableMap.getInt("scaleType"));
            }
            boolean z = false;
            customIjkPlayer.changeVoiceState(!(readableMap.hasKey("mute") && readableMap.getBoolean("mute")));
            customIjkPlayer.getVodPlayerOptions().setIsRequestAudioFocus(true);
            boolean z2 = readableMap.hasKey("hideControlView") && readableMap.getBoolean("hideControlView");
            customIjkPlayer.setReportParams(readableMap.hasKey("videoId") ? readableMap.getString("videoId") : "", readableMap.hasKey(ReportConstant.CommonInfo.PLAY_TYPE) ? readableMap.getString(ReportConstant.CommonInfo.PLAY_TYPE) : "", string, readableMap.hasKey("pageId") ? readableMap.getString("pageId") : "", readableMap.hasKey("skuId") ? readableMap.getString("skuId") : null, readableMap.hasKey("articleId") ? readableMap.getString("articleId") : null, readableMap.hasKey("referPageId") ? readableMap.getString("referPageId") : null, readableMap.hasKey("projectId") ? readableMap.getString("projectId") : null);
            boolean z3 = readableMap.hasKey("autoPlay") && readableMap.getBoolean("autoPlay");
            if (readableMap.hasKey(JDPureVideoManager.SourceKey.LOOP) && readableMap.getBoolean(JDPureVideoManager.SourceKey.LOOP)) {
                z = true;
            }
            if (customIjkPlayer instanceof RCTCustomIjkPlayer) {
                RCTCustomIjkPlayer rCTCustomIjkPlayer = (RCTCustomIjkPlayer) customIjkPlayer;
                rCTCustomIjkPlayer.setVideoPath(string, z3);
                try {
                    getDurationAsync(rCTCustomIjkPlayer, string);
                    if (readableMap.hasKey("sensorEnable") && !readableMap.getBoolean("sensorEnable")) {
                        rCTCustomIjkPlayer.sensorDisable();
                    } else {
                        rCTCustomIjkPlayer.sensorEnable();
                    }
                    rCTCustomIjkPlayer.setLoopPlay(z);
                    rCTCustomIjkPlayer.setHideControlView(z2);
                } catch (Exception e2) {
                    e = e2;
                    OKLog.e(TAG, e);
                }
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public CustomIjkPlayer createViewInstance(ThemedReactContext themedReactContext) {
        OKLog.i(TAG, "createViewInstance");
        RCTCustomIjkPlayer rCTCustomIjkPlayer = new RCTCustomIjkPlayer(themedReactContext);
        rCTCustomIjkPlayer.registerVoiceReceiver();
        rCTCustomIjkPlayer.setCouldAutoHide(true);
        rCTCustomIjkPlayer.setIsForceLayout(true);
        rCTCustomIjkPlayer.showVoiceBtn();
        rCTCustomIjkPlayer.setKeepScreenOn();
        return rCTCustomIjkPlayer;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(CustomIjkPlayer customIjkPlayer) {
        OKLog.i(TAG, "onDropViewInstance");
        super.onDropViewInstance((JDCustomIjkPlayerManager) customIjkPlayer);
        if (customIjkPlayer instanceof RCTCustomIjkPlayer) {
            ((RCTCustomIjkPlayer) customIjkPlayer).destroy();
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(CustomIjkPlayer customIjkPlayer, int i2, @Nullable ReadableArray readableArray) {
        OKLog.i(TAG, "receiveCommand, id:" + i2 + ", source:" + readableArray);
        switch (i2) {
            case 1:
                if (readableArray == null || customIjkPlayer == null) {
                    return;
                }
                customIjkPlayer.changeVoiceState(readableArray.getInt(0) > 0);
                return;
            case 2:
                if (customIjkPlayer instanceof RCTCustomIjkPlayer) {
                    ((RCTCustomIjkPlayer) customIjkPlayer).pauseIfPlaying();
                    return;
                }
                return;
            case 3:
                if (customIjkPlayer instanceof RCTCustomIjkPlayer) {
                    ((RCTCustomIjkPlayer) customIjkPlayer).playIfNotPlaying();
                    return;
                }
                return;
            case 4:
                if (customIjkPlayer != null) {
                    customIjkPlayer.suspend();
                    customIjkPlayer.releaseInThread();
                    return;
                }
                return;
            case 5:
                if (!(customIjkPlayer instanceof RCTCustomIjkPlayer) || readableArray == null) {
                    return;
                }
                ((RCTCustomIjkPlayer) customIjkPlayer).changeFullScreen(readableArray.getBoolean(0));
                return;
            case 6:
                if (readableArray == null) {
                    return;
                }
                double d = readableArray.getDouble(0);
                double d2 = readableArray.getDouble(1);
                double duration = customIjkPlayer.getDuration();
                Double.isNaN(duration);
                customIjkPlayer.seekTo((int) Math.round((duration * d) / d2));
                return;
            case 7:
                if (!(customIjkPlayer instanceof RCTCustomIjkPlayer) || readableArray == null) {
                    return;
                }
                if (!readableArray.getBoolean(0)) {
                    ((RCTCustomIjkPlayer) customIjkPlayer).sensorDisable();
                    return;
                } else {
                    ((RCTCustomIjkPlayer) customIjkPlayer).sensorEnable();
                    return;
                }
            default:
                return;
        }
    }
}
