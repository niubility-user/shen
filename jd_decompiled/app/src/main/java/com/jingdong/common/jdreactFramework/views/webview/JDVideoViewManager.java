package com.jingdong.common.jdreactFramework.views.webview;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class JDVideoViewManager extends SimpleViewManager<JDReactVideoPlayer> {
    private static final int CHANGE_DESTROY = 4;
    private static final int CHANGE_FULLSCREEN = 5;
    private static final int CHANGE_PAUSE = 2;
    private static final int CHANGE_PLAY = 3;
    private static final int CHANGE_VOLUMN = 1;
    private static String TAG = "JDVideoViewManager";
    private LoadingAsyncTask mLoadingAsyncTask;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class LoadingAsyncTask extends AsyncTask<Void, Integer, Bitmap> {
        String duration;
        private int height;
        private RCTVideoView mRCTVideoView;
        boolean photo;
        boolean show;
        private String url;
        private int width;

        public LoadingAsyncTask(RCTVideoView rCTVideoView, int i2, int i3, String str, boolean z, boolean z2) {
            this.mRCTVideoView = rCTVideoView;
            this.height = i2;
            this.width = i3;
            this.url = str;
            this.show = z;
            this.photo = z2;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(Void... voidArr) {
            if (!this.show) {
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                try {
                    try {
                        if (Build.VERSION.SDK_INT >= 14) {
                            mediaMetadataRetriever.setDataSource(this.url, new HashMap());
                        } else {
                            mediaMetadataRetriever.setDataSource(this.url);
                        }
                        this.duration = mediaMetadataRetriever.extractMetadata(9);
                    } catch (RuntimeException unused) {
                    }
                } catch (Exception unused2) {
                    mediaMetadataRetriever.release();
                }
            }
            if (this.photo) {
                return JDVideoViewManager.this.createVideoThumbnail(this.url, this.width, this.height);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                this.mRCTVideoView.setCoverBitmap(bitmap);
            }
            if (this.show) {
                this.mRCTVideoView.start();
                this.mRCTVideoView.getVideoView().start();
                return;
            }
            this.mRCTVideoView.setShowTimeSecond(this.duration);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class RCTVideoView extends JDReactVideoPlayer implements LifecycleEventListener, MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener, Runnable, EventListener {
        private int duration;
        private Handler mHandler;

        public RCTVideoView(ThemedReactContext themedReactContext) {
            super(themedReactContext);
            this.duration = 0;
            themedReactContext.addLifecycleEventListener(this);
            this.mHandler = new Handler();
        }

        private void dispatchEvent(String str, WritableMap writableMap) {
            ((RCTEventEmitter) ((ReactContext) getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), str, writableMap);
        }

        @Override // com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer, android.media.MediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
            int i3;
            super.onBufferingUpdate(mediaPlayer, i2);
            OKLog.d(JDVideoViewManager.TAG, "onBufferingUpdate percent = " + i2);
            int i4 = this.duration;
            if (i4 != 0) {
                double d = i4 * i2;
                Double.isNaN(d);
                i3 = (int) Math.round(d / 100.0d);
            } else {
                i3 = 0;
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("buffer", i3);
            dispatchEvent(VideoEvent.EVENT_UPDATE.toString(), createMap);
        }

        @Override // com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer, com.jingdong.common.jdreactFramework.views.webview.EventListener
        public void onButtonClick(String str) {
            super.onButtonClick(str);
            OKLog.d(JDVideoViewManager.TAG, "onButtonClick");
            WritableMap createMap = Arguments.createMap();
            createMap.putString("buttonclick", str);
            dispatchEvent(VideoEvent.EVENT_BUTTON_CLICK.toString(), createMap);
        }

        @Override // com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer, android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            super.onCompletion(mediaPlayer);
            OKLog.d(JDVideoViewManager.TAG, "onCompletion");
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("complete", 1);
            dispatchEvent(VideoEvent.EVENT_COMPLETION.toString(), createMap);
            this.mHandler.removeCallbacks(this);
            int i2 = this.duration;
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt(NotificationCompat.CATEGORY_PROGRESS, i2);
            dispatchEvent(VideoEvent.EVENT_PROGRESS.toString(), createMap2);
        }

        @Override // com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer, android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
            super.onError(mediaPlayer, i2, i3);
            OKLog.d(JDVideoViewManager.TAG, "onError what = " + i2 + " extra = " + i3);
            this.mHandler.removeCallbacks(this);
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("what", i2);
            createMap.putInt("extra", i2);
            dispatchEvent(VideoEvent.EVENT_ERROR.toString(), createMap);
            onStateChangeEvent(4);
            return true;
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
            OKLog.d(JDVideoViewManager.TAG, "onHostDestroy");
            this.mHandler.removeCallbacks(this);
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
            OKLog.d(JDVideoViewManager.TAG, "onHostPause");
            getVideoView().pause();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
            OKLog.d(JDVideoViewManager.TAG, "onHostResume");
        }

        @Override // android.media.MediaPlayer.OnInfoListener
        public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
            OKLog.d(JDVideoViewManager.TAG, "onInfo");
            if (i2 == 3) {
                OKLog.d(JDVideoViewManager.TAG, "\u5f00\u59cb\u6e32\u67d3\u89c6\u9891\u7b2c\u4e00\u5e27\u753b\u9762");
                return false;
            } else if (i2 == 701) {
                OKLog.d(JDVideoViewManager.TAG, "\u5f00\u59cb\u7f13\u51b2");
                return false;
            } else if (i2 != 702) {
                return false;
            } else {
                OKLog.d(JDVideoViewManager.TAG, "\u7ed3\u675f\u7f13\u51b2");
                return false;
            }
        }

        @Override // com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer, com.jingdong.common.jdreactFramework.views.webview.EventListener
        public void onNetWorkStateChangeedEvent(int i2) {
            super.onNetWorkStateChangeedEvent(i2);
            OKLog.d(JDVideoViewManager.TAG, "onStateChangeEvent");
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("SHRNAVPlayerNetworkChangedKey", i2);
            dispatchEvent(VideoEvent.EVENT_NETWORK_CHANGE.toString(), createMap);
        }

        @Override // com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer, android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            super.onPrepared(mediaPlayer);
            this.duration = mediaPlayer.getDuration();
            OKLog.d(JDVideoViewManager.TAG, "onPrepared duration = " + this.duration);
            mediaPlayer.setOnInfoListener(this);
            mediaPlayer.setOnBufferingUpdateListener(this);
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("duration", this.duration);
            dispatchEvent(VideoEvent.EVENT_PREPARE.toString(), createMap);
            this.mHandler.post(this);
        }

        @Override // com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer, com.jingdong.common.jdreactFramework.views.webview.EventListener
        public void onStateChangeEvent(int i2) {
            super.onStateChangeEvent(i2);
            OKLog.d(JDVideoViewManager.TAG, "onStateChangeEvent" + i2);
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("SHRNAVPlayerStateKey", i2);
            dispatchEvent(VideoEvent.EVENT_STATECHANGE.toString(), createMap);
        }

        @Override // java.lang.Runnable
        public void run() {
            int currentPosition = getVideoView().getCurrentPosition();
            WritableMap createMap = Arguments.createMap();
            createMap.putInt(NotificationCompat.CATEGORY_PROGRESS, currentPosition);
            dispatchEvent(VideoEvent.EVENT_PROGRESS.toString(), createMap);
            this.mHandler.postDelayed(this, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum VideoEvent {
        EVENT_PREPARE("onPrepared"),
        EVENT_PROGRESS("onProgress"),
        EVENT_UPDATE("onBufferUpdate"),
        EVENT_ERROR("onErrorHappened"),
        EVENT_COMPLETION("onCompletion"),
        EVENT_BUTTON_CLICK("onButtonClick"),
        EVENT_STATECHANGE("onStateChangeEvent"),
        EVENT_NETWORK_CHANGE("onNetWorkStateChangeedEvent");
        
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
    public Bitmap createVideoThumbnail(String str, int i2, int i3) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                if (Build.VERSION.SDK_INT >= 14) {
                    mediaMetadataRetriever.setDataSource(str, new HashMap());
                } else {
                    mediaMetadataRetriever.setDataSource(str);
                }
                Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime();
                try {
                    mediaMetadataRetriever.release();
                    return frameAtTime;
                } catch (RuntimeException unused) {
                    return frameAtTime;
                }
            } catch (IllegalArgumentException | RuntimeException unused2) {
                mediaMetadataRetriever.release();
                return null;
            } catch (Throwable th) {
                try {
                    mediaMetadataRetriever.release();
                } catch (RuntimeException unused3) {
                }
                throw th;
            }
        } catch (RuntimeException unused4) {
            return null;
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        MapBuilder.Builder builder = MapBuilder.builder();
        builder.put("changeVolume", 1);
        builder.put("changePause", 2);
        builder.put("changePlay", 3);
        builder.put("changeDestroy", 4);
        builder.put("changeFullScreen", 5);
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
        return "RTCJDReactVideoView";
    }

    @ReactProp(name = "source")
    public void setSource(RCTVideoView rCTVideoView, @Nullable ReadableMap readableMap) {
        if (readableMap != null) {
            boolean z = readableMap.hasKey("mute") ? readableMap.getBoolean("mute") : false;
            if (readableMap.hasKey("url")) {
                String string = readableMap.getString("url");
                String string2 = readableMap.hasKey("img") ? readableMap.getString("img") : null;
                boolean z2 = readableMap.hasKey("autoPlay") ? readableMap.getBoolean("autoPlay") : false;
                HashMap hashMap = new HashMap();
                if (readableMap.hasKey("headers")) {
                    ReadableMap map = readableMap.getMap("headers");
                    ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
                    while (keySetIterator.hasNextKey()) {
                        String nextKey = keySetIterator.nextKey();
                        hashMap.put(nextKey, map.getString(nextKey));
                    }
                }
                rCTVideoView.setVideoUri(Uri.parse(string));
                if (z) {
                    rCTVideoView.getVideoView().closeVolume();
                }
                if (!TextUtils.isEmpty(string2) && !z2) {
                    rCTVideoView.setCoverUrl(string2);
                    if (z2) {
                        rCTVideoView.start();
                        rCTVideoView.getVideoView().start();
                        return;
                    }
                    LoadingAsyncTask loadingAsyncTask = new LoadingAsyncTask(rCTVideoView, rCTVideoView.getHeight(), rCTVideoView.getWidth(), string, z2, false);
                    this.mLoadingAsyncTask = loadingAsyncTask;
                    loadingAsyncTask.execute(new Void[0]);
                    return;
                }
                LoadingAsyncTask loadingAsyncTask2 = this.mLoadingAsyncTask;
                if (loadingAsyncTask2 != null && loadingAsyncTask2.getStatus() == AsyncTask.Status.RUNNING) {
                    this.mLoadingAsyncTask.cancel(true);
                    this.mLoadingAsyncTask = null;
                }
                LoadingAsyncTask loadingAsyncTask3 = new LoadingAsyncTask(rCTVideoView, rCTVideoView.getHeight(), rCTVideoView.getWidth(), string, z2, true);
                this.mLoadingAsyncTask = loadingAsyncTask3;
                loadingAsyncTask3.execute(new Void[0]);
            } else if (readableMap.hasKey("mute")) {
                if (z) {
                    rCTVideoView.getVideoView().closeVolume();
                } else {
                    rCTVideoView.getVideoView().openVolume();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public JDReactVideoPlayer createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTVideoView(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(JDReactVideoPlayer jDReactVideoPlayer) {
        super.onDropViewInstance((JDVideoViewManager) jDReactVideoPlayer);
        ((ThemedReactContext) jDReactVideoPlayer.getContext()).removeLifecycleEventListener((RCTVideoView) jDReactVideoPlayer);
        jDReactVideoPlayer.getVideoView().stopPlayback();
        LoadingAsyncTask loadingAsyncTask = this.mLoadingAsyncTask;
        if (loadingAsyncTask == null || loadingAsyncTask.getStatus() != AsyncTask.Status.RUNNING) {
            return;
        }
        this.mLoadingAsyncTask.cancel(true);
        this.mLoadingAsyncTask = null;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(JDReactVideoPlayer jDReactVideoPlayer, int i2, @Nullable ReadableArray readableArray) {
        if (i2 == 1) {
            if (readableArray != null) {
                int i3 = readableArray.getInt(0);
                if (i3 == 0) {
                    jDReactVideoPlayer.closeVolume();
                } else if (i3 == -1) {
                    jDReactVideoPlayer.openVolume();
                } else {
                    jDReactVideoPlayer.setVolume(i3);
                }
            }
        } else if (i2 == 2) {
            jDReactVideoPlayer.pause();
        } else if (i2 != 3) {
            if (i2 == 4) {
                jDReactVideoPlayer.stopPlay();
            } else if (i2 != 5) {
            } else {
                jDReactVideoPlayer.showFullScreen();
            }
        } else {
            JDReactVideoView videoView = jDReactVideoPlayer.getVideoView();
            if (videoView == null || videoView.isPlaying()) {
                return;
            }
            if (videoView.isPause()) {
                jDReactVideoPlayer.play();
                return;
            }
            jDReactVideoPlayer.start();
            videoView.start();
        }
    }
}
