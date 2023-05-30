package tv.danmaku.ijk.media.alpha.decoder;

import android.graphics.SurfaceTexture;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Handler;
import android.view.Surface;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.alpha.AlphaConfig;
import tv.danmaku.ijk.media.alpha.decoder.Decoder;
import tv.danmaku.ijk.media.alpha.file.IFileContainer;
import tv.danmaku.ijk.media.alpha.player.AlphaPlayer;
import tv.danmaku.ijk.media.alpha.render.TransparentRender;
import tv.danmaku.ijk.media.alpha.util.MediaUtil;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public class HardDecoder extends Decoder implements SurfaceTexture.OnFrameAvailableListener {
    private static final String TAG = HardDecoder.class.getSimpleName();
    private final MediaCodec.BufferInfo bufferInfo;
    private MediaCodec decoder;
    private MediaExtractor extractor;
    private SurfaceTexture glTexture;
    private boolean needDestroy;

    public HardDecoder(AlphaPlayer alphaPlayer) {
        super(alphaPlayer);
        this.bufferInfo = new MediaCodec.BufferInfo();
        this.decoder = null;
        this.extractor = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b() {
        TransparentRender transparentRender = this.render;
        if (transparentRender != null) {
            transparentRender.destroy();
            this.render = null;
        }
        this.player.pluginManager.onDestroy();
        onVideoDestroy();
        destroyThread();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d() {
        try {
            SurfaceTexture surfaceTexture = this.glTexture;
            if (surfaceTexture == null) {
                return;
            }
            surfaceTexture.updateTexImage();
            TransparentRender transparentRender = this.render;
            if (transparentRender == null) {
                return;
            }
            transparentRender.renderFrame(this.player.configManager.config);
            this.player.pluginManager.onRendering();
            this.render.swapBuffers();
        } catch (Throwable th) {
            DebugLog.e(TAG, "render exception=" + th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyInner() {
        Handler handler = this.renderThread.handler;
        if (handler == null) {
            return;
        }
        handler.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.decoder.b
            @Override // java.lang.Runnable
            public final void run() {
                HardDecoder.this.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release(final MediaCodec mediaCodec, final MediaExtractor mediaExtractor) {
        Handler handler;
        Decoder.HandlerHolder handlerHolder = this.renderThread;
        if (handlerHolder == null || (handler = handlerHolder.handler) == null) {
            return;
        }
        handler.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.decoder.HardDecoder.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    MediaCodec mediaCodec2 = mediaCodec;
                    if (mediaCodec2 != null) {
                        mediaCodec2.stop();
                        mediaCodec.release();
                    }
                    MediaExtractor mediaExtractor2 = mediaExtractor;
                    if (mediaExtractor2 != null) {
                        mediaExtractor2.release();
                    }
                    if (HardDecoder.this.glTexture != null) {
                        HardDecoder.this.glTexture.release();
                        HardDecoder.this.glTexture = null;
                    }
                    HardDecoder.this.speedControlUtil.reset();
                    HardDecoder.this.player.pluginManager.onRelease();
                    TransparentRender transparentRender = HardDecoder.this.render;
                    if (transparentRender != null) {
                        transparentRender.releaseTexture();
                    }
                } catch (Throwable th) {
                    DebugLog.e(HardDecoder.TAG, "release e=" + th);
                }
                HardDecoder hardDecoder = HardDecoder.this;
                hardDecoder.isRunning = false;
                hardDecoder.onVideoComplete();
                if (HardDecoder.this.needDestroy) {
                    HardDecoder.this.destroyInner();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDecode(MediaExtractor mediaExtractor, MediaCodec mediaCodec) {
        int i2;
        ByteBuffer[] inputBuffers = mediaCodec.getInputBuffers();
        boolean z = false;
        boolean z2 = false;
        int i3 = 0;
        while (!z) {
            if (this.isStopReq) {
                DebugLog.i(TAG, "stop decode");
                release(mediaCodec, mediaExtractor);
                return;
            } else if (!this.isPause) {
                int i4 = 1;
                if (!z2) {
                    int dequeueInputBuffer = mediaCodec.dequeueInputBuffer(10000L);
                    if (dequeueInputBuffer >= 0) {
                        int readSampleData = mediaExtractor.readSampleData(inputBuffers[dequeueInputBuffer], 0);
                        if (readSampleData < 0) {
                            mediaCodec.queueInputBuffer(dequeueInputBuffer, 0, 0, 0L, 4);
                            DebugLog.d(TAG, "decode EOS");
                            z2 = true;
                        } else {
                            mediaCodec.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, mediaExtractor.getSampleTime(), 0);
                            mediaExtractor.advance();
                        }
                    } else {
                        DebugLog.d(TAG, "input buffer not available");
                    }
                }
                if (z) {
                    continue;
                } else {
                    int dequeueOutputBuffer = mediaCodec.dequeueOutputBuffer(this.bufferInfo, 10000L);
                    if (dequeueOutputBuffer == -1) {
                        DebugLog.d(TAG, "no output from decoder available");
                    } else if (dequeueOutputBuffer == -3) {
                        DebugLog.d(TAG, "decoder output buffers changed");
                    } else if (dequeueOutputBuffer == -2) {
                        DebugLog.i(TAG, "decoder output format changed: " + mediaCodec.getOutputFormat());
                    } else if (dequeueOutputBuffer >= 0) {
                        if ((this.bufferInfo.flags & 4) != 0) {
                            i2 = this.playLoop - 1;
                            this.playLoop = i2;
                            this.player.setPlayLoop(i2);
                            z = this.playLoop <= 0;
                        } else {
                            i2 = 0;
                        }
                        boolean z3 = !z;
                        if (z3) {
                            this.speedControlUtil.preRender(this.bufferInfo.presentationTimeUs);
                        }
                        mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, z3);
                        if (i3 == 0) {
                            onVideoStart();
                        }
                        this.player.pluginManager.onDecoding(i3);
                        onVideoRender(i3, this.player.configManager.config);
                        int i5 = i3 + 1;
                        if (i2 > 0) {
                            this.player.pluginManager.onLoopStart();
                            mediaExtractor.seekTo(0L, 2);
                            mediaCodec.flush();
                            this.speedControlUtil.reset();
                            z2 = false;
                        } else {
                            i4 = i5;
                        }
                        if (z) {
                            release(mediaCodec, mediaExtractor);
                        }
                        i3 = i4;
                    } else {
                        throw new RuntimeException("unexpected result from decoder.dequeueOutputBuffer: " + dequeueOutputBuffer);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startPlay  reason: merged with bridge method [inline-methods] */
    public void f(IFileContainer iFileContainer) {
        int i2;
        try {
            if (prepareRender()) {
                try {
                    MediaUtil mediaUtil = MediaUtil.INSTANCE;
                    MediaExtractor extractor = mediaUtil.getExtractor(iFileContainer);
                    this.extractor = extractor;
                    int selectTrack = mediaUtil.selectTrack(extractor, MediaUtil.TRACK_VIDEO);
                    if (selectTrack >= 0) {
                        this.extractor.selectTrack(selectTrack);
                        MediaFormat trackFormat = this.extractor.getTrackFormat(selectTrack);
                        if (trackFormat != null) {
                            if (mediaUtil.checkIsHevc(trackFormat) && ((i2 = Build.VERSION.SDK_INT) < 21 || !mediaUtil.isDeviceSupportHevc())) {
                                onFailed(10008, "0x8 hevc not supportsdk:" + i2 + ", support hevc:" + mediaUtil.isDeviceSupportHevc());
                                release(null, null);
                                return;
                            }
                            int integer = trackFormat.getInteger("width");
                            int integer2 = trackFormat.getInteger("height");
                            String str = TAG;
                            DebugLog.i(str, "Video size is " + integer + " x " + integer2);
                            preparePlay();
                            if (this.render != null) {
                                SurfaceTexture surfaceTexture = new SurfaceTexture(this.render.getExternalTexture());
                                this.glTexture = surfaceTexture;
                                surfaceTexture.setOnFrameAvailableListener(this);
                                this.glTexture.setDefaultBufferSize(integer, integer2);
                                this.render.clearFrame();
                            }
                            try {
                                String string = trackFormat.getString(IMediaFormat.KEY_MIME);
                                DebugLog.i(str, "Video MIME is " + string);
                                MediaCodec createDecoderByType = MediaCodec.createDecoderByType(string);
                                this.decoder = createDecoderByType;
                                createDecoderByType.configure(trackFormat, new Surface(this.glTexture), (MediaCrypto) null, 0);
                                this.decoder.start();
                                Handler handler = this.decoderThread.handler;
                                if (handler == null) {
                                    return;
                                }
                                handler.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.decoder.HardDecoder.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        try {
                                            HardDecoder hardDecoder = HardDecoder.this;
                                            hardDecoder.startDecode(hardDecoder.extractor, HardDecoder.this.decoder);
                                        } catch (Throwable th) {
                                            th.printStackTrace();
                                            DebugLog.e(HardDecoder.TAG, "MediaCodec  exception e=" + th);
                                            HardDecoder.this.onFailed(10002, "0x2 MediaCodec exception e=" + th);
                                            HardDecoder hardDecoder2 = HardDecoder.this;
                                            hardDecoder2.release(hardDecoder2.decoder, HardDecoder.this.extractor);
                                        }
                                    }
                                });
                                return;
                            } catch (Throwable th) {
                                DebugLog.e(TAG, "MediaCodec configure exception e=" + th);
                                onFailed(10002, "0x2 MediaCodec exception e=" + th);
                                release(this.decoder, this.extractor);
                                return;
                            }
                        }
                        throw new RuntimeException("format is null");
                    }
                    throw new RuntimeException("No video track found");
                } catch (Throwable th2) {
                    onFailed(10001, "${Constant.ERROR_MSG_CREATE_RENDER} e=" + th2);
                    release(this.decoder, this.extractor);
                    return;
                }
            }
            throw new RuntimeException("render create fail");
        } catch (Throwable th3) {
            onFailed(10004, "${Constant.ERROR_MSG_CREATE_RENDER} e=" + th3);
            release(null, null);
        }
    }

    @Override // tv.danmaku.ijk.media.alpha.decoder.Decoder
    public void destroy() {
        this.needDestroy = true;
        if (this.isRunning) {
            stop();
        } else {
            destroyInner();
        }
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        Handler handler;
        if (this.isStopReq || (handler = this.renderThread.handler) == null) {
            return;
        }
        handler.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.decoder.c
            @Override // java.lang.Runnable
            public final void run() {
                HardDecoder.this.d();
            }
        });
    }

    @Override // tv.danmaku.ijk.media.alpha.decoder.Decoder, tv.danmaku.ijk.media.alpha.IAlphaListener
    public boolean onVideoConfigReady(AlphaConfig alphaConfig) {
        return super.onVideoConfigReady(alphaConfig);
    }

    @Override // tv.danmaku.ijk.media.alpha.decoder.Decoder
    public void start(final IFileContainer iFileContainer) {
        this.isStopReq = false;
        this.needDestroy = false;
        this.isRunning = true;
        Handler handler = this.renderThread.handler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.decoder.a
                @Override // java.lang.Runnable
                public final void run() {
                    HardDecoder.this.f(iFileContainer);
                }
            });
        }
    }
}
