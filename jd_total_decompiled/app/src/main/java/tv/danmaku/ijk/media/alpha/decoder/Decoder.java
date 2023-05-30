package tv.danmaku.ijk.media.alpha.decoder;

import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import tv.danmaku.ijk.media.alpha.AlphaConfig;
import tv.danmaku.ijk.media.alpha.AlphaConfigManager;
import tv.danmaku.ijk.media.alpha.IAlphaListener;
import tv.danmaku.ijk.media.alpha.file.IFileContainer;
import tv.danmaku.ijk.media.alpha.player.AlphaPlayer;
import tv.danmaku.ijk.media.alpha.render.TransparentRender;
import tv.danmaku.ijk.media.alpha.util.SpeedControlUtil;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public abstract class Decoder implements IAlphaListener {
    private static final String TAG = "Decoder";
    protected boolean isPause;
    protected boolean isRunning;
    protected boolean isStopReq;
    protected AlphaPlayer player;
    protected TransparentRender render;
    private int surfaceHeight;
    private int surfaceWidth;
    protected int playLoop = 1;
    protected HandlerHolder renderThread = new HandlerHolder(null, null);
    protected HandlerHolder decoderThread = new HandlerHolder(null, null);
    protected SpeedControlUtil speedControlUtil = new SpeedControlUtil();

    /* loaded from: classes11.dex */
    public static class HandlerHolder {
        public Handler handler;
        public HandlerThread thread;

        public HandlerHolder(HandlerThread handlerThread, Handler handler) {
            this.thread = handlerThread;
            this.handler = handler;
        }
    }

    public Decoder(AlphaPlayer alphaPlayer) {
        this.player = alphaPlayer;
    }

    public static boolean createThread(HandlerHolder handlerHolder, String str) {
        if (handlerHolder == null) {
            return false;
        }
        HandlerThread handlerThread = handlerHolder.thread;
        if (handlerThread == null || !handlerThread.isAlive()) {
            HandlerThread handlerThread2 = new HandlerThread(str);
            handlerHolder.thread = handlerThread2;
            handlerThread2.start();
            handlerHolder.handler = new Handler(handlerHolder.thread.getLooper());
            return true;
        }
        return false;
    }

    public static HandlerThread quitSafely(HandlerThread handlerThread) {
        if (handlerThread == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            handlerThread.quitSafely();
        } else {
            handlerThread.quit();
        }
        return null;
    }

    public abstract void destroy();

    public void destroyThread() {
        AlphaPlayer alphaPlayer = this.player;
        if (alphaPlayer == null || !alphaPlayer.isDetachedFromWindow()) {
            return;
        }
        Handler handler = this.renderThread.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.decoderThread.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        HandlerHolder handlerHolder = this.renderThread;
        handlerHolder.thread = quitSafely(handlerHolder.thread);
        HandlerHolder handlerHolder2 = this.decoderThread;
        handlerHolder2.thread = quitSafely(handlerHolder2.thread);
        this.renderThread.handler = null;
        this.decoderThread.handler = null;
    }

    public TransparentRender getRender() {
        return this.render;
    }

    public HandlerHolder getRenderThread() {
        return this.renderThread;
    }

    public boolean isPause() {
        return this.isPause;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
    public void onFailed(int i2, String str) {
        AlphaPlayer alphaPlayer = this.player;
        if (alphaPlayer == null) {
            return;
        }
        alphaPlayer.onFailed(i2, str);
    }

    public void onSurfaceSizeChanged(int i2, int i3) {
        this.surfaceWidth = i2;
        this.surfaceHeight = i3;
        TransparentRender transparentRender = this.render;
        if (transparentRender == null) {
            return;
        }
        transparentRender.updateViewPort(i2, i3);
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
    public void onVideoComplete() {
        AlphaPlayer alphaPlayer = this.player;
        if (alphaPlayer == null) {
            return;
        }
        alphaPlayer.onVideoComplete();
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
    public boolean onVideoConfigReady(AlphaConfig alphaConfig) {
        AlphaPlayer alphaPlayer = this.player;
        if (alphaPlayer == null) {
            return false;
        }
        return alphaPlayer.onVideoConfigReady(alphaConfig);
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
    public void onVideoDestroy() {
        AlphaPlayer alphaPlayer = this.player;
        if (alphaPlayer == null) {
            return;
        }
        alphaPlayer.onVideoDestroy();
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
    public void onVideoRender(int i2, AlphaConfig alphaConfig) {
        AlphaPlayer alphaPlayer = this.player;
        if (alphaPlayer == null) {
            return;
        }
        alphaPlayer.onVideoRender(i2, alphaConfig);
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaListener
    public void onVideoStart() {
        DebugLog.i(TAG, "onVideoStart");
        AlphaPlayer alphaPlayer = this.player;
        if (alphaPlayer == null) {
            return;
        }
        alphaPlayer.onVideoStart();
    }

    public void pause() {
        this.isRunning = false;
        this.isPause = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void preparePlay() {
        AlphaPlayer alphaPlayer;
        AlphaConfigManager alphaConfigManager;
        AlphaConfig alphaConfig;
        DebugLog.i(TAG, "preparePlay");
        TransparentRender transparentRender = this.render;
        if (transparentRender != null && (alphaPlayer = this.player) != null && (alphaConfigManager = alphaPlayer.configManager) != null && (alphaConfig = alphaConfigManager.config) != null) {
            transparentRender.setAnimConfig(alphaConfig);
        }
        this.player.pluginManager.onRenderCreate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean prepareRender() {
        if (this.render == null) {
            DebugLog.i(TAG, "prepareRender");
            SurfaceTexture surfaceTexture = this.player.getAlphaPlayerView().getSurfaceTexture();
            if (surfaceTexture != null) {
                TransparentRender transparentRender = new TransparentRender(surfaceTexture);
                this.render = transparentRender;
                transparentRender.updateViewPort(this.surfaceWidth, this.surfaceHeight);
            }
        }
        TransparentRender transparentRender2 = this.render;
        if (transparentRender2 != null) {
            transparentRender2.createTexture();
        }
        return this.render != null;
    }

    public boolean prepareThread() {
        return createThread(this.renderThread, "alpha_render_thread") && createThread(this.decoderThread, "alpha_decoder_thread");
    }

    public void resume() {
        this.speedControlUtil.reset();
        this.isRunning = true;
        this.isPause = false;
    }

    public void setFps(int i2) {
        this.speedControlUtil.setFixedPlaybackRate(i2);
    }

    public void setPlayLoop(int i2) {
        this.playLoop = i2;
    }

    public abstract void start(IFileContainer iFileContainer);

    public void stop() {
        this.isStopReq = true;
    }
}
