package tv.danmaku.ijk.media.alpha.player;

import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.os.Build;
import android.os.Handler;
import com.jingdong.sdk.platform.business.personal.R2;
import tv.danmaku.ijk.media.alpha.decoder.Decoder;
import tv.danmaku.ijk.media.alpha.file.IFileContainer;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public class AudioPlayer {
    private static final String TAG = "AudioPlayer";
    private AudioTrack audioTrack;
    private final Decoder.HandlerHolder decodeThread = new Decoder.HandlerHolder(null, null);
    private MediaCodec decoder;
    private MediaExtractor extractor;
    private boolean isMute;
    private boolean isPause;
    private boolean isRunning;
    private boolean isStopReq;
    private boolean needDestroy;
    private int playLoop;
    private final AlphaPlayer player;

    public AudioPlayer(AlphaPlayer alphaPlayer) {
        this.player = alphaPlayer;
    }

    private void destroyInner() {
        if (this.player.isDetachedFromWindow()) {
            Handler handler = this.decodeThread.handler;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            Decoder.HandlerHolder handlerHolder = this.decodeThread;
            handlerHolder.thread = Decoder.quitSafely(handlerHolder.thread);
        }
    }

    private int getChannelConfig(int i2) {
        switch (i2) {
            case 1:
                return 2;
            case 2:
                return 12;
            case 3:
                return 28;
            case 4:
                return 204;
            case 5:
                return 220;
            case 6:
                return 252;
            case 7:
                return R2.attr.liftOnScroll;
            default:
                throw new RuntimeException("Unsupport channel count: " + i2);
        }
    }

    private boolean prepareThread() {
        return Decoder.createThread(this.decodeThread, "alpha_audio_thread");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release() {
        try {
            MediaCodec mediaCodec = this.decoder;
            if (mediaCodec != null) {
                mediaCodec.stop();
                this.decoder.release();
                this.decoder = null;
            }
            MediaExtractor mediaExtractor = this.extractor;
            if (mediaExtractor != null) {
                mediaExtractor.release();
                this.extractor = null;
            }
            AudioTrack audioTrack = this.audioTrack;
            if (audioTrack != null) {
                audioTrack.pause();
                this.audioTrack.flush();
                this.audioTrack.stop();
                this.audioTrack.release();
                this.audioTrack = null;
            }
        } catch (Throwable th) {
            DebugLog.e(TAG, "release error: " + th.getMessage());
        }
        this.isRunning = false;
        if (this.needDestroy) {
            destroyInner();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x011f, code lost:
        r6 = r18.playLoop - 1;
        r18.playLoop = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0124, code lost:
        if (r6 <= 0) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0132, code lost:
        release();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void startPlay(tv.danmaku.ijk.media.alpha.file.IFileContainer r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.alpha.player.AudioPlayer.startPlay(tv.danmaku.ijk.media.alpha.file.IFileContainer):void");
    }

    public void destroy() {
        this.needDestroy = true;
        if (this.isRunning) {
            stop();
        } else {
            destroyInner();
        }
    }

    public boolean isMute() {
        return this.isMute;
    }

    public void pause() {
        this.isPause = true;
    }

    public void resume() {
        this.isPause = false;
    }

    public void setMute(boolean z) {
        this.isMute = z;
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack != null) {
            if (Build.VERSION.SDK_INT < 21) {
                if (z) {
                    audioTrack.setStereoVolume(0.0f, 0.0f);
                } else {
                    audioTrack.setStereoVolume(1.0f, 1.0f);
                }
            } else if (z) {
                audioTrack.setVolume(0.0f);
            } else {
                audioTrack.setVolume(1.0f);
            }
        }
    }

    public void setPlayLoop(int i2) {
        this.playLoop = i2;
    }

    public void start(final IFileContainer iFileContainer) {
        this.isStopReq = false;
        this.needDestroy = false;
        if (prepareThread()) {
            if (this.isRunning) {
                stop();
            }
            this.isRunning = true;
            Handler handler = this.decodeThread.handler;
            if (handler == null) {
                return;
            }
            handler.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.player.AudioPlayer.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AudioPlayer.this.startPlay(iFileContainer);
                    } catch (Throwable th) {
                        DebugLog.e(AudioPlayer.TAG, "Audio exception=", th);
                        AudioPlayer.this.release();
                    }
                }
            });
        }
    }

    public void stop() {
        this.isStopReq = true;
    }
}
