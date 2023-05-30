package tv.danmaku.ijk.media.alpha.player;

import android.os.Handler;
import android.os.Looper;
import tv.danmaku.ijk.media.alpha.AlphaConfig;
import tv.danmaku.ijk.media.alpha.AlphaConfigManager;
import tv.danmaku.ijk.media.alpha.AlphaPlayerConstant;
import tv.danmaku.ijk.media.alpha.IAlphaListener;
import tv.danmaku.ijk.media.alpha.decoder.Decoder;
import tv.danmaku.ijk.media.alpha.decoder.HardDecoder;
import tv.danmaku.ijk.media.alpha.file.IFileContainer;
import tv.danmaku.ijk.media.alpha.mask.MaskConfig;
import tv.danmaku.ijk.media.alpha.plugin.AlphaPluginManager;
import tv.danmaku.ijk.media.alpha.widget.AlphaVideoView;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public class AlphaPlayer implements IAlphaListener {
    private static final String TAG = "AlphaPlayer";
    private AlphaVideoView alphaPlayerView;
    private IAlphaListener alphaProxyListener;
    private AudioPlayer audioPlayer;
    private Decoder decoder;
    private IAlphaListener.OnEventListener eventListener;
    private Runnable startRunnable;
    protected Handler uiThread = new Handler(Looper.myLooper());
    private boolean isDetachedFromWindow = false;
    private boolean isSurfaceAvailable = false;
    private boolean isStartRunning = false;
    private int playLoop = 0;
    private int fps = 24;
    private boolean supportMaskBoolean = false;
    private boolean maskEdgeBlurBoolean = false;
    private boolean enableVersion1 = false;
    private boolean isMute = false;
    public AlphaPluginManager pluginManager = new AlphaPluginManager(this);
    public AlphaConfigManager configManager = new AlphaConfigManager(this);

    public AlphaPlayer(AlphaVideoView alphaVideoView) {
        this.alphaPlayerView = alphaVideoView;
    }

    /* renamed from: c */
    public /* synthetic */ void d(int i2, String str) {
        IAlphaListener.OnEventListener onEventListener = this.eventListener;
        if (onEventListener != null) {
            onEventListener.onEvent(i2);
        }
        IAlphaListener iAlphaListener = this.alphaProxyListener;
        if (iAlphaListener != null) {
            iAlphaListener.onFailed(i2, str);
        }
    }

    /* renamed from: e */
    public /* synthetic */ void f() {
        IAlphaListener.OnEventListener onEventListener = this.eventListener;
        if (onEventListener != null) {
            onEventListener.onEvent(2);
        }
        IAlphaListener iAlphaListener = this.alphaProxyListener;
        if (iAlphaListener != null) {
            iAlphaListener.onVideoComplete();
        }
    }

    /* renamed from: g */
    public /* synthetic */ void h(AlphaConfig alphaConfig) {
        DebugLog.i(TAG, "onVideoConfigReady");
        IAlphaListener.OnEventListener onEventListener = this.eventListener;
        if (onEventListener != null) {
            onEventListener.onEvent(0);
        }
        IAlphaListener iAlphaListener = this.alphaProxyListener;
        if (iAlphaListener != null) {
            iAlphaListener.onVideoConfigReady(alphaConfig);
        }
    }

    /* renamed from: i */
    public /* synthetic */ void j() {
        IAlphaListener.OnEventListener onEventListener = this.eventListener;
        if (onEventListener != null) {
            onEventListener.onEvent(3);
        }
        IAlphaListener iAlphaListener = this.alphaProxyListener;
        if (iAlphaListener != null) {
            iAlphaListener.onVideoDestroy();
        }
    }

    /* renamed from: innerStartPlay */
    public void b(final IFileContainer iFileContainer) {
        DebugLog.d(TAG, "innerStartPlay");
        synchronized (this) {
            if (this.isSurfaceAvailable) {
                this.isStartRunning = false;
                Decoder decoder = this.decoder;
                if (decoder != null) {
                    decoder.start(iFileContainer);
                }
                AudioPlayer audioPlayer = this.audioPlayer;
                if (audioPlayer != null) {
                    audioPlayer.start(iFileContainer);
                }
            } else {
                this.startRunnable = new Runnable() { // from class: tv.danmaku.ijk.media.alpha.player.f
                    {
                        AlphaPlayer.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        AlphaPlayer.this.b(iFileContainer);
                    }
                };
                this.alphaPlayerView.prepareTextureView();
            }
        }
    }

    /* renamed from: k */
    public /* synthetic */ void l(int i2, AlphaConfig alphaConfig) {
        IAlphaListener iAlphaListener = this.alphaProxyListener;
        if (iAlphaListener != null) {
            iAlphaListener.onVideoRender(i2, alphaConfig);
        }
    }

    /* renamed from: m */
    public /* synthetic */ void n() {
        DebugLog.i(TAG, "onVideoStart");
        IAlphaListener.OnEventListener onEventListener = this.eventListener;
        if (onEventListener != null) {
            onEventListener.onEvent(1);
        }
        IAlphaListener iAlphaListener = this.alphaProxyListener;
        if (iAlphaListener != null) {
            iAlphaListener.onVideoStart();
        }
    }

    /* renamed from: o */
    public /* synthetic */ void p(IFileContainer iFileContainer) {
        int parseConfig = this.configManager.parseConfig(iFileContainer, this.fps);
        if (parseConfig != 0) {
            this.isStartRunning = false;
            this.decoder.onFailed(parseConfig, AlphaPlayerConstant.getErrorMsg(parseConfig));
            this.decoder.onVideoComplete();
            return;
        }
        String str = TAG;
        DebugLog.i(str, "parse = " + this.configManager.config);
        AlphaConfig alphaConfig = this.configManager.config;
        if (alphaConfig != null && this.decoder.onVideoConfigReady(alphaConfig)) {
            a(iFileContainer);
        } else {
            DebugLog.i(str, "onVideoConfigReady return false");
        }
    }

    private void prepareDecoder() {
        if (this.decoder == null) {
            HardDecoder hardDecoder = new HardDecoder(this);
            this.decoder = hardDecoder;
            hardDecoder.setFps(this.fps);
        }
        if (this.audioPlayer == null) {
            this.audioPlayer = new AudioPlayer(this);
        }
        setPlayLoop(this.playLoop);
    }

    public AlphaVideoView getAlphaPlayerView() {
        return this.alphaPlayerView;
    }

    public Decoder getDecoder() {
        return this.decoder;
    }

    public int getPlayLoop() {
        return this.playLoop;
    }

    public boolean isDetachedFromWindow() {
        return this.isDetachedFromWindow;
    }

    public boolean isMaskEdgeBlurBoolean() {
        return this.maskEdgeBlurBoolean;
    }

    public boolean isMute() {
        AudioPlayer audioPlayer = this.audioPlayer;
        return audioPlayer == null || audioPlayer.isMute();
    }

    public boolean isPause() {
        Decoder decoder = this.decoder;
        if (decoder != null) {
            return decoder.isPause();
        }
        return false;
    }

    public boolean isRunning() {
        Decoder decoder = this.decoder;
        if (decoder != null) {
            return decoder.isRunning();
        }
        return this.isStartRunning;
    }

    public boolean isSupportMaskBoolean() {
        return this.supportMaskBoolean;
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
    public void onFailed(final int i2, final String str) {
        this.uiThread.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.player.b
            {
                AlphaPlayer.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                AlphaPlayer.this.d(i2, str);
            }
        });
    }

    public void onSurfaceTextureAvailable(int i2, int i3) {
        this.isSurfaceAvailable = true;
        Runnable runnable = this.startRunnable;
        if (runnable != null) {
            runnable.run();
            this.startRunnable = null;
        }
    }

    public void onSurfaceTextureDestroyed() {
        this.isSurfaceAvailable = false;
        this.isStartRunning = false;
        Decoder decoder = this.decoder;
        if (decoder != null) {
            decoder.destroy();
        }
        AudioPlayer audioPlayer = this.audioPlayer;
        if (audioPlayer != null) {
            audioPlayer.destroy();
        }
    }

    public void onSurfaceTextureSizeChanged(int i2, int i3) {
        Decoder decoder = this.decoder;
        if (decoder == null) {
            return;
        }
        decoder.onSurfaceSizeChanged(i2, i3);
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
    public void onVideoComplete() {
        DebugLog.i(TAG, "onVideoComplete");
        this.uiThread.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.player.d
            {
                AlphaPlayer.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                AlphaPlayer.this.f();
            }
        });
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
    public boolean onVideoConfigReady(final AlphaConfig alphaConfig) {
        this.uiThread.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.player.c
            {
                AlphaPlayer.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                AlphaPlayer.this.h(alphaConfig);
            }
        });
        return true;
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
    public void onVideoDestroy() {
        DebugLog.i(TAG, "onVideoDestroy");
        this.uiThread.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.player.e
            {
                AlphaPlayer.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                AlphaPlayer.this.j();
            }
        });
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
    public void onVideoRender(final int i2, final AlphaConfig alphaConfig) {
        this.uiThread.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.player.a
            {
                AlphaPlayer.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                AlphaPlayer.this.l(i2, alphaConfig);
            }
        });
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
    public void onVideoStart() {
        this.uiThread.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.player.g
            {
                AlphaPlayer.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                AlphaPlayer.this.n();
            }
        });
    }

    public void pausePlay() {
        Decoder decoder = this.decoder;
        if (decoder != null) {
            decoder.pause();
        }
        AudioPlayer audioPlayer = this.audioPlayer;
        if (audioPlayer != null) {
            audioPlayer.pause();
        }
    }

    public void resumePlay() {
        Decoder decoder = this.decoder;
        if (decoder != null) {
            decoder.resume();
        }
        AudioPlayer audioPlayer = this.audioPlayer;
        if (audioPlayer != null) {
            audioPlayer.resume();
        }
    }

    public void setAlphaListener(IAlphaListener iAlphaListener) {
        this.alphaProxyListener = iAlphaListener;
    }

    public void setDetachedFromWindow(boolean z) {
        this.isDetachedFromWindow = z;
    }

    public void setEventListener(IAlphaListener.OnEventListener onEventListener) {
        this.eventListener = onEventListener;
    }

    public void setFps(int i2) {
        this.fps = i2;
    }

    public void setMaskEdgeBlurBoolean(boolean z) {
        this.maskEdgeBlurBoolean = z;
    }

    public void setMute(boolean z) {
        AudioPlayer audioPlayer = this.audioPlayer;
        if (audioPlayer != null) {
            audioPlayer.setMute(z);
        }
    }

    public void setPlayLoop(int i2) {
        this.playLoop = i2;
        Decoder decoder = this.decoder;
        if (decoder != null) {
            decoder.setPlayLoop(i2);
        }
        AudioPlayer audioPlayer = this.audioPlayer;
        if (audioPlayer != null) {
            audioPlayer.setPlayLoop(i2);
        }
    }

    public void setSupportMaskBoolean(boolean z) {
        this.supportMaskBoolean = z;
    }

    public void startPlay(final IFileContainer iFileContainer) {
        this.isStartRunning = true;
        prepareDecoder();
        Decoder decoder = this.decoder;
        if (decoder != null && decoder.prepareThread()) {
            Decoder decoder2 = this.decoder;
            if (decoder2 == null || decoder2.getRenderThread() == null || this.decoder.getRenderThread().handler == null) {
                return;
            }
            this.decoder.getRenderThread().handler.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.player.h
                {
                    AlphaPlayer.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    AlphaPlayer.this.p(iFileContainer);
                }
            });
            return;
        }
        this.isStartRunning = false;
        Decoder decoder3 = this.decoder;
        if (decoder3 != null) {
            decoder3.onFailed(10003, AlphaPlayerConstant.ERR_MSG.DETAIL_CREATE_THREAD);
            this.decoder.onVideoComplete();
        }
    }

    public void stopPlay() {
        Decoder decoder = this.decoder;
        if (decoder != null) {
            decoder.stop();
        }
        AudioPlayer audioPlayer = this.audioPlayer;
        if (audioPlayer != null) {
            audioPlayer.stop();
        }
    }

    public void updateMaskConfig(MaskConfig maskConfig) {
        AlphaConfig alphaConfig = this.configManager.config;
        if (alphaConfig == null || maskConfig == null) {
            return;
        }
        if (alphaConfig.maskConfig == null) {
            alphaConfig.maskConfig = new MaskConfig();
        }
        this.configManager.config.maskConfig.safeSetMaskBitmapAndReleasePre(maskConfig.getAlphaMaskBitmap());
        this.configManager.config.maskConfig.setMaskPositionPair(maskConfig.getMaskPositionPair());
        this.configManager.config.maskConfig.setMaskTexPair(maskConfig.getMaskTexPair());
    }
}
