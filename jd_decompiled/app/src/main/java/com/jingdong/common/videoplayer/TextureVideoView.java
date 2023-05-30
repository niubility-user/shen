package com.jingdong.common.videoplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import com.jingdong.common.unification.video.controller.ItemPlayerController;
import com.jingdong.common.unification.video.player.VideoPlayUtil;
import java.util.HashMap;
import java.util.Map;
import tv.danmaku.ijk.media.ext.identify.PlayerNetHeaderUtil;

/* loaded from: classes6.dex */
public class TextureVideoView extends TextureView implements TextureView.SurfaceTextureListener, IVideoControl, Handler.Callback {
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
    private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener;
    MediaPlayer.OnCompletionListener mCompletionListener;
    private Context mContext;
    private int mCurrentBufferPercentage;
    private int mCurrentState;
    MediaPlayer.OnErrorListener mErrorListener;
    private Map<String, String> mHeaders;
    MediaPlayer.OnInfoListener mInfoListener;
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
    private Uri uri;

    public TextureVideoView(Context context) {
        super(context);
        this.TAG = TextureVideoView.class.getSimpleName();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.isMute = false;
        this.isScale = true;
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                TextureVideoView.this.mCurrentState = 2;
                TextureVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                TextureVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (TextureVideoView.this.mTargetState == 3) {
                    TextureVideoView.this.start();
                }
                if (TextureVideoView.this.onPreparedListener != null) {
                    TextureVideoView.this.onPreparedListener.onPrepared(mediaPlayer);
                }
                TextureVideoView textureVideoView = TextureVideoView.this;
                textureVideoView.measure(textureVideoView.mVideoWidth, TextureVideoView.this.mVideoHeight);
                TextureVideoView.this.requestLayout();
            }
        };
        this.mSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.2
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (TextureVideoView.this.onSeekCompleteListener != null) {
                    TextureVideoView.this.onSeekCompleteListener.onSeekComplete(mediaPlayer);
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                TextureVideoView.this.mCurrentState = 5;
                if (TextureVideoView.this.onCompletionListener != null) {
                    TextureVideoView.this.onCompletionListener.onCompletion(mediaPlayer);
                }
            }
        };
        this.mVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.4
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                TextureVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                TextureVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                if (TextureVideoView.this.isScale) {
                    TextureVideoView.this.updateTextureViewSizeCenter();
                } else {
                    TextureVideoView.this.updateTextureViewSizeCenterCrop();
                }
                if (TextureVideoView.this.onVideoSizeChangedListener != null) {
                    TextureVideoView.this.onVideoSizeChangedListener.onVideoSizeChanged(mediaPlayer, i2, i3);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.5
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
                if (TextureVideoView.this.onInfoListener != null) {
                    TextureVideoView.this.onInfoListener.onInfo(mediaPlayer, i2, i3);
                    return false;
                }
                return false;
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.6
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                if (TextureVideoView.this.onErrorListener != null) {
                    TextureVideoView.this.onErrorListener.onError(mediaPlayer, i2, i3);
                    return false;
                }
                return false;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.7
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
                TextureVideoView.this.mCurrentBufferPercentage = i2;
                if (TextureVideoView.this.onBufferingUpdateListener != null) {
                    TextureVideoView.this.onBufferingUpdateListener.onBufferingUpdate(mediaPlayer, i2);
                }
            }
        };
        initView();
    }

    private void addUAHeader() {
        if (PlayerNetHeaderUtil.canUse()) {
            if (this.mHeaders == null) {
                this.mHeaders = new HashMap();
            }
            this.mHeaders.put("User-Agent", PlayerNetHeaderUtil.generateUA());
            this.mHeaders.put("Referer", PlayerNetHeaderUtil.generateReferer(PlayerNetHeaderUtil.PlayerType.MEDIA_BJ));
        }
    }

    private void initView() {
        setSurfaceTextureListener(this);
        this.mContext = getContext();
    }

    private boolean isInPlaybackState() {
        int i2;
        return (this.mediaPlayer == null || (i2 = this.mCurrentState) == 0 || i2 == -1 || i2 == 1) ? false : true;
    }

    private void openUri() {
        if (this.uri == null || this.s == null || this.mCurrentState == 1) {
            return;
        }
        if (ItemPlayerController.getController().isAbandonAudioFocus()) {
            VideoPlayUtil.muteAudioFocus(this.mContext, !this.isMute);
        }
        release(false);
        try {
            if (this.mediaPlayer == null) {
                this.mediaPlayer = new MediaPlayer();
            }
            this.mediaPlayer.setSurface(this.s);
            this.mediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
            this.mediaPlayer.setOnCompletionListener(this.mCompletionListener);
            this.mediaPlayer.setOnErrorListener(this.mErrorListener);
            this.mediaPlayer.setOnInfoListener(this.mInfoListener);
            this.mediaPlayer.setOnSeekCompleteListener(this.mSeekCompleteListener);
            this.mediaPlayer.setOnPreparedListener(this.mPreparedListener);
            Map<String, String> map = this.mHeaders;
            if (map != null) {
                this.mediaPlayer.setDataSource(this.mContext, this.uri, map);
            } else {
                this.mediaPlayer.setDataSource(this.mContext, this.uri);
            }
            this.mediaPlayer.setOnVideoSizeChangedListener(this.mVideoSizeChangedListener);
            if (this.isMute) {
                this.mediaPlayer.setVolume(0.0f, 0.0f);
            }
            this.mediaPlayer.prepareAsync();
            this.mCurrentState = 1;
        } catch (Throwable unused) {
            this.mCurrentState = -1;
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
        }
        Map<String, String> map = this.mHeaders;
        if (map != null) {
            map.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTextureViewSizeCenter() {
        float width = getWidth() / this.mVideoWidth;
        float height = getHeight() / this.mVideoHeight;
        Matrix matrix = new Matrix();
        matrix.preTranslate((getWidth() - this.mVideoWidth) / 2, (getHeight() - this.mVideoHeight) / 2);
        matrix.preScale(this.mVideoWidth / getWidth(), this.mVideoHeight / getHeight());
        if (width >= height) {
            matrix.postScale(height, height, getWidth() / 2, getHeight() / 2);
        } else {
            matrix.postScale(width, width, getWidth() / 2, getHeight() / 2);
        }
        setTransform(matrix);
        postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTextureViewSizeCenterCrop() {
        Matrix matrix = new Matrix();
        float max = Math.max(getWidth() / this.mVideoWidth, getHeight() / this.mVideoHeight);
        matrix.preTranslate((getWidth() - this.mVideoWidth) / 2, (getHeight() - this.mVideoHeight) / 2);
        matrix.preScale(this.mVideoWidth / getWidth(), this.mVideoHeight / getHeight());
        matrix.postScale(max, max, getWidth() / 2, getHeight() / 2);
        setTransform(matrix);
        postInvalidate();
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
        if (message.what != 1) {
            return false;
        }
        openUri();
        return false;
    }

    public void isMute(boolean z) {
        this.isMute = z;
    }

    @Override // com.jingdong.common.videoplayer.IVideoControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mediaPlayer.isPlaying();
    }

    public void isScale(boolean z) {
        this.isScale = z;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        this.s = new Surface(surfaceTexture);
        if (this.mTargetState == 3) {
            openUri();
            start();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        stopPlayback();
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // com.jingdong.common.videoplayer.IVideoControl
    public void pause() {
        if (isInPlaybackState()) {
            this.mediaPlayer.pause();
        }
        this.mCurrentState = 4;
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

    public void setVideoUri(Uri uri) {
        this.uri = uri;
        addUAHeader();
        openUri();
    }

    @Override // com.jingdong.common.videoplayer.IVideoControl
    public void start() {
        if (isInPlaybackState()) {
            this.mCurrentState = 3;
            this.mediaPlayer.start();
            return;
        }
        this.mTargetState = 3;
    }

    @Override // com.jingdong.common.videoplayer.IVideoControl
    public void stop() {
        if (isInPlaybackState()) {
            this.mediaPlayer.stop();
        }
        this.mTargetState = 6;
    }

    public void stopPlayback() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
            this.mTargetState = 6;
        }
    }

    public TextureVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = TextureVideoView.class.getSimpleName();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.isMute = false;
        this.isScale = true;
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                TextureVideoView.this.mCurrentState = 2;
                TextureVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                TextureVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (TextureVideoView.this.mTargetState == 3) {
                    TextureVideoView.this.start();
                }
                if (TextureVideoView.this.onPreparedListener != null) {
                    TextureVideoView.this.onPreparedListener.onPrepared(mediaPlayer);
                }
                TextureVideoView textureVideoView = TextureVideoView.this;
                textureVideoView.measure(textureVideoView.mVideoWidth, TextureVideoView.this.mVideoHeight);
                TextureVideoView.this.requestLayout();
            }
        };
        this.mSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.2
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (TextureVideoView.this.onSeekCompleteListener != null) {
                    TextureVideoView.this.onSeekCompleteListener.onSeekComplete(mediaPlayer);
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                TextureVideoView.this.mCurrentState = 5;
                if (TextureVideoView.this.onCompletionListener != null) {
                    TextureVideoView.this.onCompletionListener.onCompletion(mediaPlayer);
                }
            }
        };
        this.mVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.4
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                TextureVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                TextureVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                if (TextureVideoView.this.isScale) {
                    TextureVideoView.this.updateTextureViewSizeCenter();
                } else {
                    TextureVideoView.this.updateTextureViewSizeCenterCrop();
                }
                if (TextureVideoView.this.onVideoSizeChangedListener != null) {
                    TextureVideoView.this.onVideoSizeChangedListener.onVideoSizeChanged(mediaPlayer, i2, i3);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.5
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
                if (TextureVideoView.this.onInfoListener != null) {
                    TextureVideoView.this.onInfoListener.onInfo(mediaPlayer, i2, i3);
                    return false;
                }
                return false;
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.6
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                if (TextureVideoView.this.onErrorListener != null) {
                    TextureVideoView.this.onErrorListener.onError(mediaPlayer, i2, i3);
                    return false;
                }
                return false;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.7
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
                TextureVideoView.this.mCurrentBufferPercentage = i2;
                if (TextureVideoView.this.onBufferingUpdateListener != null) {
                    TextureVideoView.this.onBufferingUpdateListener.onBufferingUpdate(mediaPlayer, i2);
                }
            }
        };
        initView();
    }

    public TextureVideoView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = TextureVideoView.class.getSimpleName();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.isMute = false;
        this.isScale = true;
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                TextureVideoView.this.mCurrentState = 2;
                TextureVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                TextureVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (TextureVideoView.this.mTargetState == 3) {
                    TextureVideoView.this.start();
                }
                if (TextureVideoView.this.onPreparedListener != null) {
                    TextureVideoView.this.onPreparedListener.onPrepared(mediaPlayer);
                }
                TextureVideoView textureVideoView = TextureVideoView.this;
                textureVideoView.measure(textureVideoView.mVideoWidth, TextureVideoView.this.mVideoHeight);
                TextureVideoView.this.requestLayout();
            }
        };
        this.mSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.2
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (TextureVideoView.this.onSeekCompleteListener != null) {
                    TextureVideoView.this.onSeekCompleteListener.onSeekComplete(mediaPlayer);
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                TextureVideoView.this.mCurrentState = 5;
                if (TextureVideoView.this.onCompletionListener != null) {
                    TextureVideoView.this.onCompletionListener.onCompletion(mediaPlayer);
                }
            }
        };
        this.mVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.4
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i22, int i3) {
                TextureVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                TextureVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                if (TextureVideoView.this.isScale) {
                    TextureVideoView.this.updateTextureViewSizeCenter();
                } else {
                    TextureVideoView.this.updateTextureViewSizeCenterCrop();
                }
                if (TextureVideoView.this.onVideoSizeChangedListener != null) {
                    TextureVideoView.this.onVideoSizeChangedListener.onVideoSizeChanged(mediaPlayer, i22, i3);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.5
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i22, int i3) {
                if (TextureVideoView.this.onInfoListener != null) {
                    TextureVideoView.this.onInfoListener.onInfo(mediaPlayer, i22, i3);
                    return false;
                }
                return false;
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.6
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i22, int i3) {
                if (TextureVideoView.this.onErrorListener != null) {
                    TextureVideoView.this.onErrorListener.onError(mediaPlayer, i22, i3);
                    return false;
                }
                return false;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.7
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i22) {
                TextureVideoView.this.mCurrentBufferPercentage = i22;
                if (TextureVideoView.this.onBufferingUpdateListener != null) {
                    TextureVideoView.this.onBufferingUpdateListener.onBufferingUpdate(mediaPlayer, i22);
                }
            }
        };
        initView();
    }

    @TargetApi(21)
    public TextureVideoView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.TAG = TextureVideoView.class.getSimpleName();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.isMute = false;
        this.isScale = true;
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                TextureVideoView.this.mCurrentState = 2;
                TextureVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                TextureVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (TextureVideoView.this.mTargetState == 3) {
                    TextureVideoView.this.start();
                }
                if (TextureVideoView.this.onPreparedListener != null) {
                    TextureVideoView.this.onPreparedListener.onPrepared(mediaPlayer);
                }
                TextureVideoView textureVideoView = TextureVideoView.this;
                textureVideoView.measure(textureVideoView.mVideoWidth, TextureVideoView.this.mVideoHeight);
                TextureVideoView.this.requestLayout();
            }
        };
        this.mSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.2
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (TextureVideoView.this.onSeekCompleteListener != null) {
                    TextureVideoView.this.onSeekCompleteListener.onSeekComplete(mediaPlayer);
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                TextureVideoView.this.mCurrentState = 5;
                if (TextureVideoView.this.onCompletionListener != null) {
                    TextureVideoView.this.onCompletionListener.onCompletion(mediaPlayer);
                }
            }
        };
        this.mVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.4
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i22, int i32) {
                TextureVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                TextureVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                if (TextureVideoView.this.isScale) {
                    TextureVideoView.this.updateTextureViewSizeCenter();
                } else {
                    TextureVideoView.this.updateTextureViewSizeCenterCrop();
                }
                if (TextureVideoView.this.onVideoSizeChangedListener != null) {
                    TextureVideoView.this.onVideoSizeChangedListener.onVideoSizeChanged(mediaPlayer, i22, i32);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.5
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i22, int i32) {
                if (TextureVideoView.this.onInfoListener != null) {
                    TextureVideoView.this.onInfoListener.onInfo(mediaPlayer, i22, i32);
                    return false;
                }
                return false;
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.6
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i22, int i32) {
                if (TextureVideoView.this.onErrorListener != null) {
                    TextureVideoView.this.onErrorListener.onError(mediaPlayer, i22, i32);
                    return false;
                }
                return false;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.jingdong.common.videoplayer.TextureVideoView.7
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i22) {
                TextureVideoView.this.mCurrentBufferPercentage = i22;
                if (TextureVideoView.this.onBufferingUpdateListener != null) {
                    TextureVideoView.this.onBufferingUpdateListener.onBufferingUpdate(mediaPlayer, i22);
                }
            }
        };
        initView();
    }
}
