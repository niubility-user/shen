package com.jingdong.common.jdreactFramework.views.webview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.videoplayer.IVideoControl;
import com.jingdong.common.videoplayer.TextureVideoView;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class JDReactVideoView extends TextureView implements TextureView.SurfaceTextureListener, IVideoControl, Handler.Callback {
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    private static final int STATE_STOP = 6;
    private final String TAG;
    private boolean isMute;
    private boolean isScale;
    private boolean isScreenOn;
    private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener;
    MediaPlayer.OnCompletionListener mCompletionListener;
    private Context mContext;
    private int mCurrentBufferPercentage;
    private int mCurrentState;
    MediaPlayer.OnErrorListener mErrorListener;
    private IViewVisible mIViewVisible;
    MediaPlayer.OnInfoListener mInfoListener;
    PlayerStateListener mPlayerStateListener;
    MediaPlayer.OnPreparedListener mPreparedListener;
    MediaPlayer.OnSeekCompleteListener mSeekCompleteListener;
    private int mTargetState;
    private int mVideoHeight;
    MediaPlayer.OnVideoSizeChangedListener mVideoSizeChangedListener;
    private int mVideoWidth;
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener;
    private MediaPlayer.OnCompletionListener onCompletionListener;
    private MediaPlayer.OnErrorListener onErrorListener;
    private MediaPlayer.OnInfoListener onInfoListener;
    private MediaPlayer.OnPreparedListener onPreparedListener;
    private MediaPlayer.OnSeekCompleteListener onSeekCompleteListener;
    private MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener;
    private Surface s;
    private boolean silence;
    private Uri uri;

    /* loaded from: classes5.dex */
    public interface IViewVisible {
        void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3);

        boolean onSurfaceTextureDestroyed();
    }

    public JDReactVideoView(Context context) {
        super(context);
        this.TAG = TextureVideoView.class.getSimpleName();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.isMute = false;
        this.silence = false;
        this.isScale = true;
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.1
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                OKLog.d("TextureVideoView", "prepare  " + Thread.currentThread().getName());
                JDReactVideoView.this.mCurrentState = 2;
                OKLog.d(JDReactVideoView.this.TAG, "onPrepared " + mediaPlayer.getVideoWidth() + "   " + mediaPlayer.getVideoHeight() + JDReactVideoView.this.isInPlaybackState());
                JDReactVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                JDReactVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (JDReactVideoView.this.mTargetState == 3) {
                    JDReactVideoView.this.start();
                }
                if (JDReactVideoView.this.onPreparedListener != null) {
                    JDReactVideoView.this.onPreparedListener.onPrepared(mediaPlayer);
                }
            }
        };
        this.mSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.2
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (JDReactVideoView.this.onSeekCompleteListener != null) {
                    JDReactVideoView.this.onSeekCompleteListener.onSeekComplete(mediaPlayer);
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.3
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                JDReactVideoView.this.mCurrentState = 5;
                if (JDReactVideoView.this.onCompletionListener != null) {
                    JDReactVideoView.this.onCompletionListener.onCompletion(mediaPlayer);
                }
            }
        };
        this.mVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.4
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                if (JDReactVideoView.this.onVideoSizeChangedListener != null) {
                    JDReactVideoView.this.onVideoSizeChangedListener.onVideoSizeChanged(mediaPlayer, i2, i3);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.5
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
                if (JDReactVideoView.this.onInfoListener != null) {
                    JDReactVideoView.this.onInfoListener.onInfo(mediaPlayer, i2, i3);
                    return false;
                }
                return false;
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.6
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                if (JDReactVideoView.this.onErrorListener != null) {
                    JDReactVideoView.this.onErrorListener.onError(mediaPlayer, i2, i3);
                    return false;
                }
                return false;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.7
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
                JDReactVideoView.this.mCurrentBufferPercentage = i2;
                if (JDReactVideoView.this.onBufferingUpdateListener != null) {
                    JDReactVideoView.this.onBufferingUpdateListener.onBufferingUpdate(mediaPlayer, i2);
                }
            }
        };
        this.isScreenOn = false;
        initView();
    }

    private void initView() {
        if (OKLog.D) {
            OKLog.d(this.TAG, XView2Constants.XVIEW2_ACTION_INIT);
        }
        setSurfaceTextureListener(this);
        this.mContext = getContext();
        OKLog.d("TextureVideoView", "view init  " + Thread.currentThread().getName());
    }

    private void openUri() {
        if (this.uri == null || this.s == null || this.mCurrentState == 1) {
            return;
        }
        ((AudioManager) this.mContext.getSystemService("audio")).requestAudioFocus(null, 3, 1);
        release(false);
        try {
            if (this.mediaPlayer == null) {
                this.mediaPlayer = new MediaPlayer();
            }
            if (this.silence) {
                closeVolume();
            } else {
                openVolume();
            }
            this.mediaPlayer.setSurface(this.s);
            this.mediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
            this.mediaPlayer.setOnCompletionListener(this.mCompletionListener);
            this.mediaPlayer.setOnErrorListener(this.mErrorListener);
            this.mediaPlayer.setOnInfoListener(this.mInfoListener);
            this.mediaPlayer.setOnSeekCompleteListener(this.onSeekCompleteListener);
            this.mediaPlayer.setOnPreparedListener(this.mPreparedListener);
            this.mediaPlayer.setDataSource(this.mContext, this.uri);
            if (this.isMute) {
                this.mediaPlayer.setVolume(0.0f, 0.0f);
            }
            this.mediaPlayer.prepareAsync();
            this.mCurrentState = 1;
        } catch (Exception e2) {
            this.mCurrentState = -1;
            if (OKLog.E) {
                OKLog.e(this.TAG, e2);
            }
        }
    }

    private void release(boolean z) {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
            this.mCurrentState = 0;
            if (z) {
                this.mTargetState = 0;
            }
            PlayerStateListener playerStateListener = this.mPlayerStateListener;
            if (playerStateListener != null) {
                playerStateListener.onStateChangeEventPlayer(4);
            }
        }
    }

    private void setScreenOff() {
        Context context;
        if (this.isScreenOn && (context = getContext()) != null && (context instanceof ThemedReactContext)) {
            if (OKLog.D) {
                OKLog.d("rn TextureVideoView", "setScreenOff");
            }
            this.isScreenOn = false;
            Activity currentActivity = ((ThemedReactContext) context).getCurrentActivity();
            if (currentActivity == null || currentActivity.isFinishing() || currentActivity.getWindow() == null) {
                return;
            }
            currentActivity.getWindow().clearFlags(128);
        }
    }

    private void setScreenOn() {
        Context context;
        if (this.isScreenOn || (context = getContext()) == null || !(context instanceof ThemedReactContext)) {
            return;
        }
        if (OKLog.D) {
            OKLog.d("rn TextureVideoView", "setScreenOn");
        }
        this.isScreenOn = true;
        Activity currentActivity = ((ThemedReactContext) context).getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing() || currentActivity.getWindow() == null) {
            return;
        }
        currentActivity.getWindow().addFlags(128);
    }

    public void closeVolume() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setAudioStreamType(3);
            this.mediaPlayer.setVolume(0.0f, 0.0f);
            this.silence = true;
            return;
        }
        this.silence = true;
    }

    @Override // com.jingdong.common.videoplayer.IVideoControl
    public int getBufferPercentage() {
        if (isInPlaybackState()) {
            return this.mCurrentBufferPercentage;
        }
        return -1;
    }

    @Override // com.jingdong.common.videoplayer.IVideoControl
    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return this.mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // com.jingdong.common.videoplayer.IVideoControl
    public int getDuration() {
        if (isInPlaybackState()) {
            return this.mediaPlayer.getDuration();
        }
        return -1;
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        OKLog.d(this.TAG, "handler   " + message.what);
        if (message.what != 1) {
            return false;
        }
        openUri();
        return false;
    }

    public boolean isInPlaybackState() {
        int i2;
        return (this.mediaPlayer == null || (i2 = this.mCurrentState) == 0 || i2 == -1 || i2 == 1) ? false : true;
    }

    public void isMute(boolean z) {
        this.isMute = z;
    }

    public boolean isPause() {
        return this.mCurrentState == 4;
    }

    @Override // com.jingdong.common.videoplayer.IVideoControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mediaPlayer.isPlaying();
    }

    public void isScale(boolean z) {
        this.isScale = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:112:0x0065, code lost:
        if (r1 > r6) goto L113;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onMeasure(int i2, int i3) {
        int i4;
        int defaultSize = TextureView.getDefaultSize(this.mVideoWidth, i2);
        int defaultSize2 = TextureView.getDefaultSize(this.mVideoHeight, i3);
        if (this.isScale && this.mVideoWidth > 0 && this.mVideoHeight > 0) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            int size2 = View.MeasureSpec.getSize(i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                int i5 = this.mVideoWidth;
                int i6 = i5 * size2;
                int i7 = this.mVideoHeight;
                if (i6 < size * i7) {
                    defaultSize = (i5 * size2) / i7;
                    defaultSize2 = size2;
                } else {
                    if (i5 * size2 > size * i7) {
                        defaultSize2 = (i7 * size) / i5;
                        defaultSize = size;
                    }
                    defaultSize = size;
                    defaultSize2 = size2;
                }
            } else if (mode == 1073741824) {
                int i8 = (this.mVideoHeight * size) / this.mVideoWidth;
                if (mode2 != Integer.MIN_VALUE || i8 <= size2) {
                    defaultSize2 = i8;
                    defaultSize = size;
                }
                defaultSize = size;
                defaultSize2 = size2;
            } else {
                if (mode2 == 1073741824) {
                    i4 = (this.mVideoWidth * size2) / this.mVideoHeight;
                    if (mode == Integer.MIN_VALUE) {
                    }
                } else {
                    int i9 = this.mVideoWidth;
                    int i10 = this.mVideoHeight;
                    if (mode2 != Integer.MIN_VALUE || i10 <= size2) {
                        i4 = i9;
                        size2 = i10;
                    } else {
                        i4 = (size2 * i9) / i10;
                    }
                    if (mode == Integer.MIN_VALUE && i4 > size) {
                        defaultSize2 = (i10 * size) / i9;
                        defaultSize = size;
                    }
                }
                defaultSize = i4;
                defaultSize2 = size2;
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        OKLog.d(this.TAG, "onSurfaceTextureAvailable " + i2 + "   " + i3);
        Surface surface = new Surface(surfaceTexture);
        this.s = surface;
        if (this.mCurrentState == 3) {
            MediaPlayer mediaPlayer = this.mediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.setSurface(surface);
            }
        } else if (!isInPlaybackState() && this.mTargetState != 3) {
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            if (mediaPlayer2 != null) {
                mediaPlayer2.setSurface(this.s);
            }
        } else {
            this.s = new Surface(surfaceTexture);
            openUri();
            if (this.mTargetState == 3) {
                start();
            }
        }
        IViewVisible iViewVisible = this.mIViewVisible;
        if (iViewVisible != null) {
            iViewVisible.onSurfaceTextureAvailable(surfaceTexture, i2, i3);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        IViewVisible iViewVisible = this.mIViewVisible;
        if (iViewVisible != null) {
            return iViewVisible.onSurfaceTextureDestroyed();
        }
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void openVolume() {
        if (this.mediaPlayer != null) {
            AudioManager audioManager = (AudioManager) this.mContext.getSystemService("audio");
            this.mediaPlayer.setAudioStreamType(3);
            float streamMaxVolume = audioManager.getStreamMaxVolume(3) / audioManager.getStreamMaxVolume(3);
            this.mediaPlayer.setVolume(streamMaxVolume, streamMaxVolume);
            this.silence = false;
            return;
        }
        this.silence = false;
    }

    @Override // com.jingdong.common.videoplayer.IVideoControl
    public void pause() {
        setScreenOff();
        if (isInPlaybackState()) {
            this.mediaPlayer.pause();
        }
        PlayerStateListener playerStateListener = this.mPlayerStateListener;
        if (playerStateListener != null && this.mCurrentState != 4) {
            playerStateListener.onStateChangeEventPlayer(1);
        }
        this.mCurrentState = 4;
        this.mTargetState = 4;
    }

    public void reset() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
    }

    public void resume() {
        openUri();
    }

    @Override // com.jingdong.common.videoplayer.IVideoControl
    public void seekTo(int i2) {
        if (isInPlaybackState()) {
            this.mediaPlayer.seekTo(i2);
        }
    }

    public void setIViewVisible(IViewVisible iViewVisible) {
        this.mIViewVisible = iViewVisible;
    }

    public void setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        this.onBufferingUpdateListener = onBufferingUpdateListener;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.onCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    public void setOnInfoListener(MediaPlayer.OnInfoListener onInfoListener) {
        this.onInfoListener = onInfoListener;
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.onPreparedListener = onPreparedListener;
    }

    public void setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        this.onSeekCompleteListener = onSeekCompleteListener;
    }

    public void setOnVideoSizeChangedListener(MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.onVideoSizeChangedListener = onVideoSizeChangedListener;
    }

    public void setPlayerStateListener(PlayerStateListener playerStateListener) {
        this.mPlayerStateListener = playerStateListener;
    }

    public void setPlaystatus() {
        this.mTargetState = 3;
    }

    public void setStateIdle() {
        this.mCurrentState = 0;
    }

    public void setVideoUri(Uri uri) {
        this.uri = uri;
        openUri();
    }

    public void setVolume(int i2) {
        if (this.mediaPlayer != null) {
            ((AudioManager) this.mContext.getSystemService("audio")).setStreamVolume(3, i2, 0);
            this.silence = false;
            return;
        }
        this.silence = false;
    }

    @Override // com.jingdong.common.videoplayer.IVideoControl
    public void start() {
        Activity currentActivity;
        Context context = getContext();
        if (context != null && (context instanceof ThemedReactContext) && ((currentActivity = ((ThemedReactContext) context).getCurrentActivity()) == null || currentActivity.isFinishing())) {
            return;
        }
        setScreenOn();
        if (isInPlaybackState()) {
            PlayerStateListener playerStateListener = this.mPlayerStateListener;
            if (playerStateListener != null && this.mCurrentState != 3) {
                playerStateListener.onStateChangeEventPlayer(0);
            }
            this.mCurrentState = 3;
            this.mediaPlayer.start();
            return;
        }
        this.mTargetState = 3;
    }

    public void startPlaying() {
        if (this.mTargetState == 3) {
            start();
        }
    }

    @Override // com.jingdong.common.videoplayer.IVideoControl
    public void stop() {
        setScreenOff();
        if (isInPlaybackState()) {
            this.mediaPlayer.stop();
        }
        PlayerStateListener playerStateListener = this.mPlayerStateListener;
        if (playerStateListener != null) {
            playerStateListener.onStateChangeEventPlayer(4);
        }
        this.mTargetState = 6;
    }

    public void stopPlayback() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
            this.mCurrentState = 6;
            this.mTargetState = 6;
            PlayerStateListener playerStateListener = this.mPlayerStateListener;
            if (playerStateListener != null) {
                playerStateListener.onStateChangeEventPlayer(4);
            }
        }
    }

    public JDReactVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = TextureVideoView.class.getSimpleName();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.isMute = false;
        this.silence = false;
        this.isScale = true;
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.1
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                OKLog.d("TextureVideoView", "prepare  " + Thread.currentThread().getName());
                JDReactVideoView.this.mCurrentState = 2;
                OKLog.d(JDReactVideoView.this.TAG, "onPrepared " + mediaPlayer.getVideoWidth() + "   " + mediaPlayer.getVideoHeight() + JDReactVideoView.this.isInPlaybackState());
                JDReactVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                JDReactVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (JDReactVideoView.this.mTargetState == 3) {
                    JDReactVideoView.this.start();
                }
                if (JDReactVideoView.this.onPreparedListener != null) {
                    JDReactVideoView.this.onPreparedListener.onPrepared(mediaPlayer);
                }
            }
        };
        this.mSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.2
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (JDReactVideoView.this.onSeekCompleteListener != null) {
                    JDReactVideoView.this.onSeekCompleteListener.onSeekComplete(mediaPlayer);
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.3
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                JDReactVideoView.this.mCurrentState = 5;
                if (JDReactVideoView.this.onCompletionListener != null) {
                    JDReactVideoView.this.onCompletionListener.onCompletion(mediaPlayer);
                }
            }
        };
        this.mVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.4
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                if (JDReactVideoView.this.onVideoSizeChangedListener != null) {
                    JDReactVideoView.this.onVideoSizeChangedListener.onVideoSizeChanged(mediaPlayer, i2, i3);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.5
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
                if (JDReactVideoView.this.onInfoListener != null) {
                    JDReactVideoView.this.onInfoListener.onInfo(mediaPlayer, i2, i3);
                    return false;
                }
                return false;
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.6
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                if (JDReactVideoView.this.onErrorListener != null) {
                    JDReactVideoView.this.onErrorListener.onError(mediaPlayer, i2, i3);
                    return false;
                }
                return false;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.7
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
                JDReactVideoView.this.mCurrentBufferPercentage = i2;
                if (JDReactVideoView.this.onBufferingUpdateListener != null) {
                    JDReactVideoView.this.onBufferingUpdateListener.onBufferingUpdate(mediaPlayer, i2);
                }
            }
        };
        this.isScreenOn = false;
        initView();
    }

    public JDReactVideoView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = TextureVideoView.class.getSimpleName();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.isMute = false;
        this.silence = false;
        this.isScale = true;
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.1
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                OKLog.d("TextureVideoView", "prepare  " + Thread.currentThread().getName());
                JDReactVideoView.this.mCurrentState = 2;
                OKLog.d(JDReactVideoView.this.TAG, "onPrepared " + mediaPlayer.getVideoWidth() + "   " + mediaPlayer.getVideoHeight() + JDReactVideoView.this.isInPlaybackState());
                JDReactVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                JDReactVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (JDReactVideoView.this.mTargetState == 3) {
                    JDReactVideoView.this.start();
                }
                if (JDReactVideoView.this.onPreparedListener != null) {
                    JDReactVideoView.this.onPreparedListener.onPrepared(mediaPlayer);
                }
            }
        };
        this.mSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.2
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (JDReactVideoView.this.onSeekCompleteListener != null) {
                    JDReactVideoView.this.onSeekCompleteListener.onSeekComplete(mediaPlayer);
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.3
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                JDReactVideoView.this.mCurrentState = 5;
                if (JDReactVideoView.this.onCompletionListener != null) {
                    JDReactVideoView.this.onCompletionListener.onCompletion(mediaPlayer);
                }
            }
        };
        this.mVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.4
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i22, int i3) {
                if (JDReactVideoView.this.onVideoSizeChangedListener != null) {
                    JDReactVideoView.this.onVideoSizeChangedListener.onVideoSizeChanged(mediaPlayer, i22, i3);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.5
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i22, int i3) {
                if (JDReactVideoView.this.onInfoListener != null) {
                    JDReactVideoView.this.onInfoListener.onInfo(mediaPlayer, i22, i3);
                    return false;
                }
                return false;
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.6
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i22, int i3) {
                if (JDReactVideoView.this.onErrorListener != null) {
                    JDReactVideoView.this.onErrorListener.onError(mediaPlayer, i22, i3);
                    return false;
                }
                return false;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.7
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i22) {
                JDReactVideoView.this.mCurrentBufferPercentage = i22;
                if (JDReactVideoView.this.onBufferingUpdateListener != null) {
                    JDReactVideoView.this.onBufferingUpdateListener.onBufferingUpdate(mediaPlayer, i22);
                }
            }
        };
        this.isScreenOn = false;
        initView();
    }

    @TargetApi(21)
    public JDReactVideoView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.TAG = TextureVideoView.class.getSimpleName();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.isMute = false;
        this.silence = false;
        this.isScale = true;
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.1
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                OKLog.d("TextureVideoView", "prepare  " + Thread.currentThread().getName());
                JDReactVideoView.this.mCurrentState = 2;
                OKLog.d(JDReactVideoView.this.TAG, "onPrepared " + mediaPlayer.getVideoWidth() + "   " + mediaPlayer.getVideoHeight() + JDReactVideoView.this.isInPlaybackState());
                JDReactVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                JDReactVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (JDReactVideoView.this.mTargetState == 3) {
                    JDReactVideoView.this.start();
                }
                if (JDReactVideoView.this.onPreparedListener != null) {
                    JDReactVideoView.this.onPreparedListener.onPrepared(mediaPlayer);
                }
            }
        };
        this.mSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.2
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (JDReactVideoView.this.onSeekCompleteListener != null) {
                    JDReactVideoView.this.onSeekCompleteListener.onSeekComplete(mediaPlayer);
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.3
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                JDReactVideoView.this.mCurrentState = 5;
                if (JDReactVideoView.this.onCompletionListener != null) {
                    JDReactVideoView.this.onCompletionListener.onCompletion(mediaPlayer);
                }
            }
        };
        this.mVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.4
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i22, int i32) {
                if (JDReactVideoView.this.onVideoSizeChangedListener != null) {
                    JDReactVideoView.this.onVideoSizeChangedListener.onVideoSizeChanged(mediaPlayer, i22, i32);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.5
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i22, int i32) {
                if (JDReactVideoView.this.onInfoListener != null) {
                    JDReactVideoView.this.onInfoListener.onInfo(mediaPlayer, i22, i32);
                    return false;
                }
                return false;
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.6
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i22, int i32) {
                if (JDReactVideoView.this.onErrorListener != null) {
                    JDReactVideoView.this.onErrorListener.onError(mediaPlayer, i22, i32);
                    return false;
                }
                return false;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactVideoView.7
            {
                JDReactVideoView.this = this;
            }

            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i22) {
                JDReactVideoView.this.mCurrentBufferPercentage = i22;
                if (JDReactVideoView.this.onBufferingUpdateListener != null) {
                    JDReactVideoView.this.onBufferingUpdateListener.onBufferingUpdate(mediaPlayer, i22);
                }
            }
        };
        this.isScreenOn = false;
        initView();
    }
}
