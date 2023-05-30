package com.jingdong.common.jdreactFramework.views.videoview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
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
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.widget.JDPlayerView;
import tv.danmaku.ijk.media.widget.controller.JDControllerOptions;

/* loaded from: classes5.dex */
public class JDCustomIjkPlayerManagerV2 extends SimpleViewManager<JDPlayerView> {
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
    private static String TAG = "JDCustomIjkPlayerManagerV2";

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
    public static class RCTCustomIjkPlayer extends JDPlayerView implements IPlayerControl.OnPlayerStateListener, LifecycleEventListener {
        private boolean isCurrentFullScreen;
        private boolean isLoopPlay;
        private ThemedReactContext mContext;
        private String pendingUrl;

        public RCTCustomIjkPlayer(ThemedReactContext themedReactContext) {
            super(themedReactContext);
            this.isLoopPlay = false;
            this.mContext = themedReactContext;
            setPlayerStateListener(this);
            if (themedReactContext != null) {
                themedReactContext.addLifecycleEventListener(this);
            }
            addPlayerEventCallback(new IMediaPlayer.OnPlayerEventListener() { // from class: com.jingdong.common.jdreactFramework.views.videoview.JDCustomIjkPlayerManagerV2.RCTCustomIjkPlayer.1
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
                public void onPlayEvent(int i2) {
                    JLog.i(JDCustomIjkPlayerManagerV2.TAG, "MtaListener.playStatusChange, playStatus: " + i2);
                    int mapPlayState = JDCustomIjkPlayerManagerV2.mapPlayState(i2);
                    JLog.i(JDCustomIjkPlayerManagerV2.TAG, "MtaListener.playStatusChange, mapPlayState: " + mapPlayState);
                    if (mapPlayState == -1) {
                        return;
                    }
                    RCTCustomIjkPlayer.this.dispatchStateChangeEvent(mapPlayState);
                }
            });
            addPlayerControllerCallback(new JDPlayerView.PlayerControllerCallback() { // from class: com.jingdong.common.jdreactFramework.views.videoview.JDCustomIjkPlayerManagerV2.RCTCustomIjkPlayer.2
                @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
                public void onNetChanged() {
                    OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "MtaListener.netChange");
                    WritableMap createMap = Arguments.createMap();
                    JDCustomIjkPlayerManagerV2.addNetStatus(createMap);
                    RCTCustomIjkPlayer.this.dispatchEvent(VideoEvent.NETWORK_CHANGE.toString(), createMap);
                }

                @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
                public void onOrientationChanged(boolean z, int i2) {
                    RCTCustomIjkPlayer.this.isCurrentFullScreen = z;
                    RCTCustomIjkPlayer.this.dispatchStateChangeEvent(z ? 2 : 3);
                }

                @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
                public void onPlayBtnClick(boolean z) {
                    OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "MtaListener.playBtnOnClick, isPlay:" + z + ", playStatus:");
                    RCTCustomIjkPlayer.this.dispatchButtonClickEvent(z ? "start" : "pause");
                }

                @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
                public void onProgressUpdate(boolean z, int i2, long j2, boolean z2) {
                    OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "OnUpdatePositionListener.updatePosition, isPlay:" + z + ", position:" + i2 + ", duration:" + j2 + ", isBarInChanging:" + z2);
                    WritableMap createMap = Arguments.createMap();
                    createMap.putBoolean("isPlay", z);
                    createMap.putInt("position", i2);
                    createMap.putInt("duration", (int) j2);
                    createMap.putBoolean("isBarInChanging", z2);
                    createMap.putInt(NotificationCompat.CATEGORY_PROGRESS, i2);
                    RCTCustomIjkPlayer.this.dispatchEvent(VideoEvent.PROGRESS.toString(), createMap);
                }

                @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
                public void onVoiceBtnClick(boolean z) {
                    OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "MtaListener.voiceBtnOnClick, isVoiceOn:" + z);
                    RCTCustomIjkPlayer.this.dispatchButtonClickEvent(z ? "VoiceOn" : "VoiceOff");
                }

                @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
                public void onVoiceStateChange(boolean z) {
                    OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "MtaListener.voiceStateChange, isVoiceOn:" + z);
                    WritableMap createMap = Arguments.createMap();
                    createMap.putBoolean("isVoiceOn", z);
                    RCTCustomIjkPlayer.this.dispatchEvent(VideoEvent.VOICE_STATE_CHANGE.toString(), createMap);
                }

                @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
                public void seekBarOnSeek(int i2) {
                    OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "MtaListener.seekBarOnSeek, position:" + i2);
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("onSeek", i2);
                    RCTCustomIjkPlayer.this.dispatchEvent(VideoEvent.SEEK.toString(), createMap);
                }
            });
        }

        public void changeFullScreen(boolean z) {
            if (!z) {
                requestScreenChange(1);
            } else {
                requestScreenChange(0);
            }
        }

        public synchronized void destroy() {
            sensorDisable();
            Context context = getContext();
            if (context instanceof ThemedReactContext) {
                ((ThemedReactContext) context).removeLifecycleEventListener(this);
            }
            release();
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
            OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "dispatchEvent, eventName:" + str + ", eventData:" + writableMap);
            try {
                ((RCTEventEmitter) ((ReactContext) context).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), str, writableMap);
            } catch (Exception e2) {
                OKLog.e(JDCustomIjkPlayerManagerV2.TAG, e2);
            }
        }

        public void dispatchStateChangeEvent(int i2) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("SHRNAVPlayerStateKey", i2);
            dispatchEvent(VideoEvent.STATE_CHANGE.toString(), createMap);
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCompletion() {
            OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "OnPlayerStateListener.onCompletion");
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("complete", 1);
            dispatchEvent(VideoEvent.COMPLETION.toString(), createMap);
            preformOnCompletion();
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCreatePlayer() {
            OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "OnPlayerStateListener.onCreatePlayer");
            this.pendingUrl = null;
            dispatchEvent(VideoEvent.CREATE_PLAYER.toString(), Arguments.createMap());
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onError(int i2, int i3) {
            OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "OnPlayerStateListener.onError what=" + i2 + "extra= " + i3);
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("type", NetUtils.isNetworkAvailable() ? 1 : 0);
            dispatchEvent(VideoEvent.ERROR.toString(), createMap);
            return true;
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
            OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "onHostDestroy");
            destroy();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
            OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "onHostPause");
            pauseIfPlaying();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
            OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "onHostResume");
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onInfo(int i2, int i3) {
            OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "OnPlayerStateListener.onInfo, what=" + i2 + ", extra=" + i3);
            if (i2 == 3) {
                OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "\u5f00\u59cb\u6e32\u67d3\u89c6\u9891\u7b2c\u4e00\u5e27\u753b\u9762");
            } else if (i2 == 701) {
                OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "\u5f00\u59cb\u7f13\u51b2");
                dispatchBufferEvent(0);
            } else if (i2 == 702) {
                OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "\u7ed3\u675f\u7f13\u51b2");
                dispatchBufferEvent(1);
            }
            return false;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onPrepared(long j2) {
            OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "OnPlayerStateListener.onPrepared, loadCost:" + j2);
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("duration", (int) j2);
            JDCustomIjkPlayerManagerV2.addNetStatus(createMap);
            dispatchEvent(VideoEvent.PREPARED.toString(), createMap);
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onSeekComplete() {
            OKLog.i(JDCustomIjkPlayerManagerV2.TAG, "OnPlayerStateListener.onSeekComplete");
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
            } else if (isCompleted()) {
                replay();
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
            updateControllerSensor(false);
        }

        public void sensorEnable() {
            updateControllerSensor(true);
        }

        public void setLoopPlay(boolean z) {
            this.isLoopPlay = z;
        }

        public void setVideoPath(String str, boolean z) {
            if (StringUtil.isEmpty(str)) {
                return;
            }
            if (z) {
                setVideoPath(str, JDPlayerView.PlayMode.AUTO_PLAY);
                this.pendingUrl = null;
                return;
            }
            this.pendingUrl = str;
            setVideoPath(str, JDPlayerView.PlayMode.CLICK_PLAY);
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
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() { // from class: com.jingdong.common.jdreactFramework.views.videoview.JDCustomIjkPlayerManagerV2.1
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
                        OKLog.e(JDCustomIjkPlayerManagerV2.TAG, e2.toString());
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

    protected static int mapPlayState(int i2) {
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
        return "JDCustomIjkNewPlayer";
    }

    @ReactProp(name = "source")
    @SuppressLint({"ResourceAsColor"})
    public void setSource(JDPlayerView jDPlayerView, @Nullable ReadableMap readableMap) {
        boolean z;
        String str;
        OKLog.i(TAG, "setSource:" + readableMap);
        if (readableMap == null || jDPlayerView == null) {
            return;
        }
        try {
            String string = readableMap.hasKey("url") ? readableMap.getString("url") : "";
            if (readableMap.hasKey("imgUrl")) {
                jDPlayerView.setImgCover(readableMap.getString("imgUrl"), R.drawable.jdreact_vd_player_fail_bg);
            }
            boolean z2 = readableMap.hasKey("mute") && readableMap.getBoolean("mute");
            boolean z3 = readableMap.hasKey("hideControlView") && readableMap.getBoolean("hideControlView");
            boolean z4 = readableMap.hasKey("usingFileCache") && readableMap.getBoolean("usingFileCache");
            int i2 = readableMap.hasKey("bufferingTimeout") ? readableMap.getInt("bufferingTimeout") : 10;
            boolean z5 = readableMap.hasKey("controlAudioSession") && readableMap.getBoolean("controlAudioSession");
            int i3 = readableMap.hasKey("playContent") ? readableMap.getInt("playContent") : 0;
            if (readableMap.hasKey("extString")) {
                readableMap.getString("extString");
            }
            String string2 = readableMap.hasKey("videoId") ? readableMap.getString("videoId") : "";
            String string3 = readableMap.hasKey("pageId") ? readableMap.getString("pageId") : "";
            String string4 = readableMap.hasKey("skuId") ? readableMap.getString("skuId") : null;
            String string5 = readableMap.hasKey("articleId") ? readableMap.getString("articleId") : null;
            String str2 = string;
            if (readableMap.hasKey("referPageId")) {
                z = z3;
                str = readableMap.getString("referPageId");
            } else {
                z = z3;
                str = null;
            }
            String str3 = string2;
            String str4 = string5;
            String string6 = readableMap.hasKey("projectId") ? readableMap.getString("projectId") : null;
            int i4 = i3;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pageId", string3);
            jSONObject.put("skuId", string4);
            jSONObject.put("referPageId", str);
            jSONObject.put("projectId", string6);
            String jSONObject2 = jSONObject.toString();
            boolean z6 = readableMap.hasKey("autoPlay") && readableMap.getBoolean("autoPlay");
            boolean z7 = readableMap.hasKey(JDPureVideoManager.SourceKey.LOOP) && readableMap.getBoolean(JDPureVideoManager.SourceKey.LOOP);
            if (jDPlayerView instanceof RCTCustomIjkPlayer) {
                RCTCustomIjkPlayer rCTCustomIjkPlayer = (RCTCustomIjkPlayer) jDPlayerView;
                IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
                playerOptions.setCouldMediaCodec(true);
                playerOptions.setEnableAccurateSeek(true);
                playerOptions.setEnableReport(false);
                if (readableMap.hasKey("scaleType")) {
                    playerOptions.setAspectRatio(readableMap.getInt("scaleType"));
                }
                playerOptions.setUseCache(z4);
                playerOptions.setLoop(z7);
                playerOptions.setVolume(z2 ? 0.0f : 1.0f);
                playerOptions.setPlayTypeId("155");
                playerOptions.addCustomOption(1, "timeout", i2 * 1000 * 1000);
                playerOptions.setIsRequestAudioFocus(z5);
                playerOptions.setDebugLog(JDReactHelper.newInstance().isDebug());
                boolean z8 = i4 != 2;
                try {
                    playerOptions.setPlayContent(i4);
                } catch (Exception unused) {
                }
                playerOptions.setPlayerReportInfoEntity(new PlayerReportInfoEntity(str4, str3, jSONObject2));
                boolean z9 = z;
                rCTCustomIjkPlayer.setPlayerOptions(playerOptions, new JDControllerOptions.Builder().fullMode(JDControllerOptions.FullMode.FULL_LAND).systemBarFlag(8192).enableMuteSwitch(z8).enableReplay(true).enableFullSwitch(true).manualControlVisible(z9).progressInterval(1000).activity(AbstractJDReactInitialHelper.getCurrentMyActivity()).enableLoading(z9).build());
                rCTCustomIjkPlayer.setVideoPath(str2, z6);
                rCTCustomIjkPlayer.setBackgroundColor(17170445);
                try {
                    getDurationAsync(rCTCustomIjkPlayer, str2);
                    if (readableMap.hasKey("sensorEnable") && !readableMap.getBoolean("sensorEnable")) {
                        rCTCustomIjkPlayer.sensorDisable();
                    } else {
                        rCTCustomIjkPlayer.sensorEnable();
                    }
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
    public JDPlayerView createViewInstance(ThemedReactContext themedReactContext) {
        OKLog.i(TAG, "createViewInstance");
        RCTCustomIjkPlayer rCTCustomIjkPlayer = new RCTCustomIjkPlayer(themedReactContext);
        rCTCustomIjkPlayer.setForceLayout(true);
        return rCTCustomIjkPlayer;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(JDPlayerView jDPlayerView) {
        OKLog.i(TAG, "onDropViewInstance");
        super.onDropViewInstance((JDCustomIjkPlayerManagerV2) jDPlayerView);
        if (jDPlayerView instanceof RCTCustomIjkPlayer) {
            ((RCTCustomIjkPlayer) jDPlayerView).destroy();
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(JDPlayerView jDPlayerView, int i2, @Nullable ReadableArray readableArray) {
        OKLog.i(TAG, "receiveCommand, id:" + i2 + ", source:" + readableArray);
        switch (i2) {
            case 1:
                if (readableArray == null || jDPlayerView == null) {
                    return;
                }
                jDPlayerView.toggleMute(readableArray.getInt(0) > 0);
                return;
            case 2:
                if (jDPlayerView instanceof RCTCustomIjkPlayer) {
                    ((RCTCustomIjkPlayer) jDPlayerView).pauseIfPlaying();
                    return;
                }
                return;
            case 3:
                if (jDPlayerView instanceof RCTCustomIjkPlayer) {
                    ((RCTCustomIjkPlayer) jDPlayerView).playIfNotPlaying();
                    return;
                }
                return;
            case 4:
                if (jDPlayerView != null) {
                    jDPlayerView.release();
                    return;
                }
                return;
            case 5:
                if (!(jDPlayerView instanceof RCTCustomIjkPlayer) || readableArray == null) {
                    return;
                }
                ((RCTCustomIjkPlayer) jDPlayerView).changeFullScreen(readableArray.getBoolean(0));
                return;
            case 6:
                if (readableArray == null) {
                    return;
                }
                double d = readableArray.getDouble(0);
                double d2 = readableArray.getDouble(1);
                double duration = jDPlayerView.getDuration();
                Double.isNaN(duration);
                jDPlayerView.seekTo((int) Math.round((duration * d) / d2));
                return;
            case 7:
                if (!(jDPlayerView instanceof RCTCustomIjkPlayer) || readableArray == null) {
                    return;
                }
                if (!readableArray.getBoolean(0)) {
                    ((RCTCustomIjkPlayer) jDPlayerView).sensorDisable();
                    return;
                } else {
                    ((RCTCustomIjkPlayer) jDPlayerView).sensorEnable();
                    return;
                }
            default:
                return;
        }
    }
}
