package tv.danmaku.ijk.media.alpha.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.SurfaceHolder;
import android.view.TextureView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.List;
import tv.danmaku.ijk.media.alpha.AlphaConfig;
import tv.danmaku.ijk.media.alpha.IAlphaListener;
import tv.danmaku.ijk.media.alpha.IAlphaVideoView;
import tv.danmaku.ijk.media.alpha.IFetchResource;
import tv.danmaku.ijk.media.alpha.OnResourceClickListener;
import tv.danmaku.ijk.media.alpha.download.AlphaDownloadManager;
import tv.danmaku.ijk.media.alpha.download.DownloadOptions;
import tv.danmaku.ijk.media.alpha.file.FileContainer;
import tv.danmaku.ijk.media.alpha.file.IFileContainer;
import tv.danmaku.ijk.media.alpha.mask.MaskConfig;
import tv.danmaku.ijk.media.alpha.player.AlphaPlayer;
import tv.danmaku.ijk.media.alpha.plugin.AlphaPluginManager;
import tv.danmaku.ijk.media.alpha.scale.ScaleTypeUtil;
import tv.danmaku.ijk.media.example.widget.media.IMediaController;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.ext.mta.AlphaPlayerMtaReport;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.utils.DebugLog;
import tv.danmaku.ijk.media.utils.PlayerOSUtil;
import tv.danmaku.ijk.media.utils.PlayerStringUtils;

/* loaded from: classes11.dex */
public class AlphaVideoView extends IAlphaVideoView implements TextureView.SurfaceTextureListener {
    private static final String TAG = AlphaVideoView.class.getSimpleName();
    private IAlphaListener alphaListener;
    private IAlphaVideoView.AlphaOptions alphaOptions;
    private AlphaPlayer alphaPlayer;
    private final IAlphaListener alphaProxyListener;
    private IFileContainer curFile;
    private InnerTextureView innerTextureView;
    private IFileContainer lastFile;
    private boolean mIsAttached;
    private AlphaPlayerMtaReport mtaReport;
    private boolean needPrepareTextureView;
    private boolean onSizeChangedCalled;
    private String playUrl;
    private IPlayerControl.PlayerOptions playerOptions;
    private final ScaleTypeUtil scaleTypeUtil;
    private SurfaceTexture surface;
    private final Handler uiHandler;

    public AlphaVideoView(@NonNull Context context) {
        this(context, null);
    }

    /* renamed from: a */
    public /* synthetic */ void b() {
        removeAllViews();
    }

    /* renamed from: c */
    public /* synthetic */ void d() {
        InnerTextureView innerTextureView = this.innerTextureView;
        if (innerTextureView != null) {
            innerTextureView.setSurfaceTextureListener(null);
            this.innerTextureView = null;
        }
        removeAllViews();
    }

    private void createPlayerMtaReport() {
        IAlphaVideoView.AlphaOptions alphaOptions = this.alphaOptions;
        if (alphaOptions != null && alphaOptions.isEnableMta() && this.mtaReport == null) {
            this.mtaReport = new AlphaPlayerMtaReport(getContext(), this.alphaPlayer, this.alphaOptions, this.playUrl);
        }
    }

    /* renamed from: e */
    public /* synthetic */ void f() {
        removeAllViews();
        InnerTextureView innerTextureView = new InnerTextureView(getContext());
        this.innerTextureView = innerTextureView;
        innerTextureView.setPlayer(this.alphaPlayer);
        this.innerTextureView.setOpaque(false);
        this.innerTextureView.setSurfaceTextureListener(this);
        InnerTextureView innerTextureView2 = this.innerTextureView;
        innerTextureView2.setLayoutParams(this.scaleTypeUtil.getLayoutParams(innerTextureView2));
        addView(this.innerTextureView);
    }

    /* renamed from: g */
    public /* synthetic */ void h(String str) {
        if (TextUtils.isEmpty(str) || getContext() == null || !this.mIsAttached) {
            return;
        }
        setVideoURI(Uri.parse(str));
    }

    public void hide() {
        IFileContainer iFileContainer = this.lastFile;
        if (iFileContainer != null) {
            try {
                iFileContainer.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.widget.c
            {
                AlphaVideoView.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                AlphaVideoView.this.b();
            }
        });
    }

    private void init() {
        hide();
        AlphaPlayer alphaPlayer = new AlphaPlayer(this);
        this.alphaPlayer = alphaPlayer;
        alphaPlayer.setAlphaListener(this.alphaProxyListener);
    }

    public void notifyPlayState(int i2, long j2, int[] iArr, int[] iArr2) {
        if (this.mOnPlayerStateListeners.isEmpty()) {
            return;
        }
        for (IPlayerControl.OnPlayerStateListener onPlayerStateListener : this.mOnPlayerStateListeners) {
            if (i2 == 0) {
                onPlayerStateListener.onCreatePlayer();
            } else if (i2 == 1) {
                onPlayerStateListener.onPrepared(j2);
            } else if (i2 == 2) {
                onPlayerStateListener.onCompletion();
            } else if (i2 != 3) {
                if (i2 == 4 && iArr2 != null && iArr2.length > 1) {
                    onPlayerStateListener.onInfo(iArr2[0], iArr2[1]);
                }
            } else if (iArr != null && iArr.length > 1) {
                onPlayerStateListener.onError(iArr[0], iArr[1]);
            }
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void addSurfaceHolderCallback(SurfaceHolder.Callback callback) {
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void forceRelase(boolean z) {
        release();
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaVideoView
    protected IAlphaVideoView.AlphaOptions getAlphaOptions() {
        return this.alphaOptions;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        return 0;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        return 0;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        return 0;
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public IjkMediaPlayer getIjkMediaPlayer() {
        return null;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public String getOriginUrl() {
        return null;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public IPlayerControl.PlayerOptions getPlayerOptions() {
        return this.playerOptions;
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaVideoView
    public Pair<Integer, Integer> getRealSize() {
        return this.scaleTypeUtil.getRealSize();
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaVideoView
    public SurfaceTexture getSurfaceTexture() {
        InnerTextureView innerTextureView = this.innerTextureView;
        if (innerTextureView != null) {
            return innerTextureView.getSurfaceTexture();
        }
        return this.surface;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public long getTcpSpeed() {
        return 0L;
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void initRenders() {
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        AlphaPlayer alphaPlayer = this.alphaPlayer;
        if (alphaPlayer != null) {
            return alphaPlayer.isRunning();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        IFileContainer iFileContainer;
        super.onAttachedToWindow();
        this.mIsAttached = true;
        AlphaPlayer alphaPlayer = this.alphaPlayer;
        if (alphaPlayer != null) {
            alphaPlayer.setDetachedFromWindow(false);
            if (this.alphaPlayer.getPlayLoop() <= 0 || (iFileContainer = this.lastFile) == null) {
                return;
            }
            this.curFile = iFileContainer;
            start();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIsAttached = false;
        if (PlayerOSUtil.belowKitKat()) {
            release();
        }
        AlphaPlayer alphaPlayer = this.alphaPlayer;
        if (alphaPlayer != null) {
            alphaPlayer.setDetachedFromWindow(true);
            this.alphaPlayer.onSurfaceTextureDestroyed();
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        DebugLog.i(TAG, "onSizeChanged width = " + i2 + ", height =" + i3);
        ScaleTypeUtil scaleTypeUtil = this.scaleTypeUtil;
        scaleTypeUtil.layoutWidth = i2;
        scaleTypeUtil.layoutHeight = i3;
        this.onSizeChangedCalled = true;
        if (this.needPrepareTextureView) {
            this.needPrepareTextureView = false;
            prepareTextureView();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i2, int i3) {
        AlphaPlayer alphaPlayer = this.alphaPlayer;
        if (alphaPlayer == null) {
            return;
        }
        alphaPlayer.onSurfaceTextureAvailable(i2, i3);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
        AlphaPlayer alphaPlayer = this.alphaPlayer;
        if (alphaPlayer != null) {
            alphaPlayer.onSurfaceTextureDestroyed();
        }
        this.uiHandler.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.widget.d
            {
                AlphaVideoView.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                AlphaVideoView.this.d();
            }
        });
        return !PlayerOSUtil.belowKitKat();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i2, int i3) {
        AlphaPlayer alphaPlayer = this.alphaPlayer;
        if (alphaPlayer != null) {
            alphaPlayer.onSurfaceTextureSizeChanged(i2, i3);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public void pause() {
        if (this.alphaPlayer.isPause()) {
            return;
        }
        this.alphaPlayer.pausePlay();
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaVideoView
    public void prepareTextureView() {
        if (this.onSizeChangedCalled) {
            this.uiHandler.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.widget.a
                {
                    AlphaVideoView.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    AlphaVideoView.this.f();
                }
            });
            return;
        }
        DebugLog.e(TAG, "onSizeChanged not called");
        this.needPrepareTextureView = true;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void release() {
        suspend();
        SurfaceTexture surfaceTexture = this.surface;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.surface = null;
        }
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void releaseInThread(boolean z) {
        release();
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void resume() {
        if (this.alphaPlayer.isPause()) {
            this.alphaPlayer.resumePlay();
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i2) {
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaVideoView
    public void setAlphaListener(IAlphaListener iAlphaListener) {
        this.alphaListener = iAlphaListener;
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaVideoView
    public void setAlphaOption(IAlphaVideoView.AlphaOptions alphaOptions) {
        if (alphaOptions == null) {
            return;
        }
        this.alphaOptions = alphaOptions;
        AlphaPlayer alphaPlayer = this.alphaPlayer;
        if (alphaPlayer != null) {
            alphaPlayer.setPlayLoop(alphaOptions.isLoop() ? Integer.MAX_VALUE : 0);
            this.scaleTypeUtil.setScaleType(this.alphaOptions.getScaleType());
        }
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void setAspectRatio(int i2) {
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaVideoView
    public void setEventListener(IMediaPlayer.OnPlayerEventListener onPlayerEventListener) {
        List<IMediaPlayer.OnPlayerEventListener> list = this.mOnPlayerEventListeners;
        if (list != null) {
            list.add(onPlayerEventListener);
        }
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaVideoView
    public void setFetchResources(IFetchResource iFetchResource) {
        AlphaPluginManager alphaPluginManager;
        AlphaPlayer alphaPlayer = this.alphaPlayer;
        if (alphaPlayer == null || (alphaPluginManager = alphaPlayer.pluginManager) == null || alphaPluginManager.getMaskAlphaPlugin() == null) {
            return;
        }
        this.alphaPlayer.pluginManager.getMixAlphaPlugin().setResourceRequest(iFetchResource);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setMediaController(IMediaController iMediaController) {
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaVideoView
    protected void setMute(boolean z) {
        AlphaPlayer alphaPlayer = this.alphaPlayer;
        if (alphaPlayer != null) {
            alphaPlayer.setMute(z);
        }
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaVideoView
    public void setOnResourceClickListener(OnResourceClickListener onResourceClickListener) {
        AlphaPluginManager alphaPluginManager;
        AlphaPlayer alphaPlayer = this.alphaPlayer;
        if (alphaPlayer == null || (alphaPluginManager = alphaPlayer.pluginManager) == null || alphaPluginManager.getMaskAlphaPlugin() == null) {
            return;
        }
        this.alphaPlayer.pluginManager.getMixAlphaPlugin().setResourceClickListener(onResourceClickListener);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setPlayerOptions(IPlayerControl.PlayerOptions playerOptions) {
        if (playerOptions == null) {
            return;
        }
        this.playerOptions = playerOptions;
        setAlphaOption(playerOptions.getAlphaOptions());
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setVideoPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setVideoURI(Uri.parse(str));
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setVideoURI(Uri uri) {
        if (uri == null) {
            return;
        }
        try {
            if (PlayerStringUtils.isLocalFile(uri)) {
                this.curFile = new FileContainer(new File(uri.toString()));
                start();
            } else {
                AlphaDownloadManager.getInstance().loadAnimByOption(new DownloadOptions(uri.toString()).enableMta(true), new AlphaDownloadManager.OnAlphaResManagerCallback() { // from class: tv.danmaku.ijk.media.alpha.widget.b
                    {
                        AlphaVideoView.this = this;
                    }

                    @Override // tv.danmaku.ijk.media.alpha.download.AlphaDownloadManager.OnAlphaResManagerCallback
                    public final void onResLoaded(String str) {
                        AlphaVideoView.this.h(str);
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void setVolume(float f2) {
        if (f2 == 0.0f) {
            setMute(true);
        } else {
            setMute(false);
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public void start() {
        AlphaPlayer alphaPlayer;
        if (getVisibility() != 0 || (alphaPlayer = this.alphaPlayer) == null) {
            return;
        }
        if (alphaPlayer.isPause()) {
            resume();
        } else if (this.alphaPlayer.isRunning()) {
        } else {
            IFileContainer iFileContainer = this.curFile;
            this.lastFile = iFileContainer;
            this.alphaPlayer.startPlay(iFileContainer);
            createPlayerMtaReport();
        }
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaVideoView
    protected void supportMask(boolean z, boolean z2) {
        AlphaPlayer alphaPlayer = this.alphaPlayer;
        if (alphaPlayer == null) {
            return;
        }
        alphaPlayer.setSupportMaskBoolean(z);
        this.alphaPlayer.setMaskEdgeBlurBoolean(z2);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void suspend() {
        AlphaPlayer alphaPlayer = this.alphaPlayer;
        if (alphaPlayer == null) {
            return;
        }
        alphaPlayer.stopPlay();
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaVideoView
    protected void updateMaskConfig(MaskConfig maskConfig) {
        AlphaPlayer alphaPlayer = this.alphaPlayer;
        if (alphaPlayer == null) {
            return;
        }
        alphaPlayer.updateMaskConfig(maskConfig);
    }

    public AlphaVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AlphaVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.uiHandler = new Handler(Looper.getMainLooper());
        this.mIsAttached = false;
        this.scaleTypeUtil = new ScaleTypeUtil();
        this.onSizeChangedCalled = false;
        this.needPrepareTextureView = false;
        this.alphaProxyListener = new IAlphaListener() { // from class: tv.danmaku.ijk.media.alpha.widget.AlphaVideoView.1
            {
                AlphaVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
            public void onFailed(int i3, String str) {
                AlphaVideoView.this.notifyPlayState(3, 0L, new int[]{i3, 0}, null);
                if (AlphaVideoView.this.alphaListener != null) {
                    AlphaVideoView.this.alphaListener.onFailed(i3, str);
                }
            }

            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
            public void onVideoComplete() {
                if (AlphaVideoView.this.alphaOptions == null || !AlphaVideoView.this.alphaOptions.isStayLastFrame()) {
                    AlphaVideoView.this.hide();
                }
                AlphaVideoView.this.notifyPlayState(2, 0L, null, null);
                if (AlphaVideoView.this.alphaListener != null) {
                    AlphaVideoView.this.alphaListener.onVideoComplete();
                }
            }

            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
            public boolean onVideoConfigReady(AlphaConfig alphaConfig) {
                if (alphaConfig != null) {
                    AlphaVideoView.this.scaleTypeUtil.videoWidth = alphaConfig.width;
                    AlphaVideoView.this.scaleTypeUtil.videoHeight = alphaConfig.height;
                }
                AlphaVideoView.this.notifyPlayState(0, 0L, null, null);
                if (AlphaVideoView.this.alphaListener != null) {
                    AlphaVideoView.this.alphaListener.onVideoConfigReady(alphaConfig);
                    return true;
                }
                return true;
            }

            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
            public void onVideoDestroy() {
                AlphaVideoView.this.hide();
                if (AlphaVideoView.this.alphaListener != null) {
                    AlphaVideoView.this.alphaListener.onVideoDestroy();
                }
            }

            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
            public void onVideoRender(int i3, AlphaConfig alphaConfig) {
                if (i3 == 0) {
                    AlphaVideoView.this.notifyPlayState(4, 0L, null, new int[]{3, 0});
                }
                if (AlphaVideoView.this.alphaListener != null) {
                    AlphaVideoView.this.alphaListener.onVideoRender(i3, alphaConfig);
                }
            }

            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
            public void onVideoStart() {
                AlphaVideoView.this.notifyPlayState(1, 0L, null, null);
                if (AlphaVideoView.this.alphaListener != null) {
                    AlphaVideoView.this.alphaListener.onVideoStart();
                }
            }
        };
        init();
    }

    @TargetApi(21)
    public AlphaVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.uiHandler = new Handler(Looper.getMainLooper());
        this.mIsAttached = false;
        this.scaleTypeUtil = new ScaleTypeUtil();
        this.onSizeChangedCalled = false;
        this.needPrepareTextureView = false;
        this.alphaProxyListener = new IAlphaListener() { // from class: tv.danmaku.ijk.media.alpha.widget.AlphaVideoView.1
            {
                AlphaVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
            public void onFailed(int i32, String str) {
                AlphaVideoView.this.notifyPlayState(3, 0L, new int[]{i32, 0}, null);
                if (AlphaVideoView.this.alphaListener != null) {
                    AlphaVideoView.this.alphaListener.onFailed(i32, str);
                }
            }

            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
            public void onVideoComplete() {
                if (AlphaVideoView.this.alphaOptions == null || !AlphaVideoView.this.alphaOptions.isStayLastFrame()) {
                    AlphaVideoView.this.hide();
                }
                AlphaVideoView.this.notifyPlayState(2, 0L, null, null);
                if (AlphaVideoView.this.alphaListener != null) {
                    AlphaVideoView.this.alphaListener.onVideoComplete();
                }
            }

            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
            public boolean onVideoConfigReady(AlphaConfig alphaConfig) {
                if (alphaConfig != null) {
                    AlphaVideoView.this.scaleTypeUtil.videoWidth = alphaConfig.width;
                    AlphaVideoView.this.scaleTypeUtil.videoHeight = alphaConfig.height;
                }
                AlphaVideoView.this.notifyPlayState(0, 0L, null, null);
                if (AlphaVideoView.this.alphaListener != null) {
                    AlphaVideoView.this.alphaListener.onVideoConfigReady(alphaConfig);
                    return true;
                }
                return true;
            }

            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
            public void onVideoDestroy() {
                AlphaVideoView.this.hide();
                if (AlphaVideoView.this.alphaListener != null) {
                    AlphaVideoView.this.alphaListener.onVideoDestroy();
                }
            }

            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
            public void onVideoRender(int i32, AlphaConfig alphaConfig) {
                if (i32 == 0) {
                    AlphaVideoView.this.notifyPlayState(4, 0L, null, new int[]{3, 0});
                }
                if (AlphaVideoView.this.alphaListener != null) {
                    AlphaVideoView.this.alphaListener.onVideoRender(i32, alphaConfig);
                }
            }

            @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
            public void onVideoStart() {
                AlphaVideoView.this.notifyPlayState(1, 0L, null, null);
                if (AlphaVideoView.this.alphaListener != null) {
                    AlphaVideoView.this.alphaListener.onVideoStart();
                }
            }
        };
        init();
    }
}
