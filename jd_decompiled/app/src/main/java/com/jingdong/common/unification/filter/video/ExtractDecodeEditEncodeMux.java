package com.jingdong.common.unification.filter.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Environment;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.view.Surface;
import com.jingdong.common.UnLog;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.common.unification.filter.FilterTools;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import tv.danmaku.ijk.media.alpha.util.MediaUtil;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

@TargetApi(18)
/* loaded from: classes6.dex */
public class ExtractDecodeEditEncodeMux {
    private static final int OUTPUT_AUDIO_AAC_PROFILE = 2;
    private static final int OUTPUT_AUDIO_BIT_RATE = 92160;
    private static final int OUTPUT_AUDIO_CHANNEL_COUNT = 1;
    private static final String OUTPUT_AUDIO_MIME_TYPE = "audio/mp4a-latm";
    private static final int OUTPUT_AUDIO_SAMPLE_RATE_HZ = 16000;
    private static final File OUTPUT_FILENAME_DIR = Environment.getExternalStorageDirectory();
    private static final int OUTPUT_VIDEO_COLOR_FORMAT = 2130708361;
    private static final int OUTPUT_VIDEO_FRAME_RATE = 15;
    private static final int OUTPUT_VIDEO_IFRAME_INTERVAL = 1;
    private static final String OUTPUT_VIDEO_MIME_TYPE = "video/avc";
    private static final String TAG = "ExtractDecodeEditEncodeMux";
    private static final int TIMEOUT_USEC = 10000;
    private HandlerThread handlerThread;
    Context mAppContext;
    private boolean mCopyAudio;
    private boolean mCopyVideo;
    private int mFilterProgress;
    private String mOutputFile;
    private String mPath;
    private int mOutPutVideoRate = 2000000;
    private int mWidth = -1;
    private int mHeight = -1;
    private int mVideoOrientation = 0;
    private FilterTools.FilterType mFilterType = FilterTools.FilterType.NO_FILTER;

    /* loaded from: classes6.dex */
    public interface ResultListener {
        void onResult(boolean z, String str, String str2);
    }

    public ExtractDecodeEditEncodeMux(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    private MediaCodec createAudioDecoder(MediaFormat mediaFormat) throws IOException {
        MediaCodec createDecoderByType = MediaCodec.createDecoderByType(getMimeTypeFor(mediaFormat));
        createDecoderByType.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 0);
        createDecoderByType.start();
        return createDecoderByType;
    }

    private MediaCodec createAudioEncoder(MediaCodecInfo mediaCodecInfo, MediaFormat mediaFormat) throws IOException {
        MediaCodec createByCodecName = MediaCodec.createByCodecName(mediaCodecInfo.getName());
        createByCodecName.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 1);
        createByCodecName.start();
        return createByCodecName;
    }

    private MediaExtractor createExtractor() throws IOException {
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaExtractor.setDataSource(this.mPath);
        return mediaExtractor;
    }

    private MediaMuxer createMuxer() throws IOException {
        if (TextUtils.isEmpty(this.mOutputFile)) {
            setOutputFile();
        }
        return new MediaMuxer(this.mOutputFile, 0);
    }

    private MediaCodec createVideoDecoder(MediaFormat mediaFormat, Surface surface) throws IOException {
        MediaCodec createDecoderByType = MediaCodec.createDecoderByType(getMimeTypeFor(mediaFormat));
        createDecoderByType.configure(mediaFormat, surface, (MediaCrypto) null, 0);
        createDecoderByType.start();
        return createDecoderByType;
    }

    private MediaCodec createVideoEncoder(MediaCodecInfo mediaCodecInfo, MediaFormat mediaFormat, AtomicReference<Surface> atomicReference) throws IOException {
        MediaCodec createByCodecName = MediaCodec.createByCodecName(mediaCodecInfo.getName());
        createByCodecName.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 1);
        atomicReference.set(createByCodecName.createInputSurface());
        createByCodecName.start();
        return createByCodecName;
    }

    /* JADX WARN: Removed duplicated region for block: B:181:0x051a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0520  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x053e  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x060d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:265:0x06fa A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:324:0x07fc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0250  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void doExtractDecodeEditEncodeMux(android.media.MediaExtractor r61, android.media.MediaExtractor r62, android.media.MediaCodec r63, android.media.MediaCodec r64, android.media.MediaCodec r65, android.media.MediaCodec r66, android.media.MediaMuxer r67, com.jingdong.common.unification.filter.video.InputSurface r68, com.jingdong.common.unification.filter.video.OutputSurfaceWithFilter r69) {
        /*
            Method dump skipped, instructions count: 2133
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.filter.video.ExtractDecodeEditEncodeMux.doExtractDecodeEditEncodeMux(android.media.MediaExtractor, android.media.MediaExtractor, android.media.MediaCodec, android.media.MediaCodec, android.media.MediaCodec, android.media.MediaCodec, android.media.MediaMuxer, com.jingdong.common.unification.filter.video.InputSurface, com.jingdong.common.unification.filter.video.OutputSurfaceWithFilter):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x046a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0457 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:284:0x04a1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:304:0x04c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0446 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:312:0x048e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:324:0x04b4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0437 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:332:0x047b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void extractDecodeEditEncodeMux() throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.filter.video.ExtractDecodeEditEncodeMux.extractDecodeEditEncodeMux():void");
    }

    private int getAndSelectAudioTrackIndex(MediaExtractor mediaExtractor) {
        for (int i2 = 0; i2 < mediaExtractor.getTrackCount(); i2++) {
            if (UnLog.D) {
                UnLog.d(TAG, "format for track " + i2 + " is " + getMimeTypeFor(mediaExtractor.getTrackFormat(i2)));
            }
            if (isAudioFormat(mediaExtractor.getTrackFormat(i2))) {
                mediaExtractor.selectTrack(i2);
                return i2;
            }
        }
        return -1;
    }

    private int getAndSelectVideoTrackIndex(MediaExtractor mediaExtractor) {
        for (int i2 = 0; i2 < mediaExtractor.getTrackCount(); i2++) {
            if (UnLog.D) {
                UnLog.d(TAG, "format for track " + i2 + " is " + getMimeTypeFor(mediaExtractor.getTrackFormat(i2)));
            }
            if (isVideoFormat(mediaExtractor.getTrackFormat(i2))) {
                mediaExtractor.selectTrack(i2);
                return i2;
            }
        }
        return -1;
    }

    private static String getMimeTypeFor(MediaFormat mediaFormat) {
        return mediaFormat.getString(IMediaFormat.KEY_MIME);
    }

    private static boolean isAudioFormat(MediaFormat mediaFormat) {
        return getMimeTypeFor(mediaFormat).startsWith(MediaUtil.TRACK_AUDIO);
    }

    private static boolean isVideoFormat(MediaFormat mediaFormat) {
        return getMimeTypeFor(mediaFormat).startsWith(MediaUtil.TRACK_VIDEO);
    }

    private static MediaCodecInfo selectCodec(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i2 = 0; i2 < codecCount; i2++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i2);
            if (codecInfoAt.isEncoder()) {
                for (String str2 : codecInfoAt.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str)) {
                        return codecInfoAt;
                    }
                }
            }
        }
        return null;
    }

    private void setCopyAudio() {
        this.mCopyAudio = true;
    }

    private void setCopyVideo() {
        this.mCopyVideo = true;
    }

    private void setOutputFile() {
        StringBuilder sb = new StringBuilder();
        sb.append(OUTPUT_FILENAME_DIR.getAbsolutePath());
        sb.append("/cts-media-");
        sb.append(getClass().getSimpleName());
        sb.append('-');
        if (this.mCopyVideo) {
            sb.append('-');
            sb.append("video");
            sb.append('-');
            sb.append(this.mWidth);
            sb.append('x');
            sb.append(this.mHeight);
        }
        if (this.mCopyAudio) {
            sb.append('-');
            sb.append("audio");
        }
        sb.append(PreDownLoadManager.VIDEO_SKU_SUFFIX);
        this.mOutputFile = sb.toString();
    }

    private void setSize(int i2, int i3) {
        if ((i2 % 16 != 0 || i3 % 16 != 0) && UnLog.W) {
            UnLog.w(TAG, "WARNING: width or height not multiple of 16");
        }
        this.mWidth = i2;
        this.mHeight = i3;
    }

    public void setFilterProgress(int i2) {
        this.mFilterProgress = i2;
    }

    public void setFilterType(FilterTools.FilterType filterType) {
        this.mFilterType = filterType;
    }

    public void setHandlerThread(HandlerThread handlerThread) {
        this.handlerThread = handlerThread;
    }

    public void setSource(String str) {
        this.mPath = str;
    }

    public void setVideoOrientation(int i2) {
        this.mVideoOrientation = i2;
    }

    public void stopThread() {
        HandlerThread handlerThread = this.handlerThread;
        if (handlerThread != null) {
            handlerThread.getLooper().quit();
            this.handlerThread = null;
        }
    }

    public void testExtractDecodeEditEncodeMuxAudioVideo(final ResultListener resultListener, int i2, int i3) {
        setSize(i2, i3);
        setCopyAudio();
        setCopyVideo();
        HandlerThread handlerThread = new HandlerThread("completethread") { // from class: com.jingdong.common.unification.filter.video.ExtractDecodeEditEncodeMux.1
            @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    ExtractDecodeEditEncodeMux.this.extractDecodeEditEncodeMux();
                    resultListener.onResult(true, ExtractDecodeEditEncodeMux.this.mOutputFile, "");
                } catch (Throwable th) {
                    if (UnLog.E) {
                        UnLog.e(ExtractDecodeEditEncodeMux.TAG, th.toString());
                    }
                    resultListener.onResult(false, ExtractDecodeEditEncodeMux.this.mOutputFile, "");
                }
            }
        };
        this.handlerThread = handlerThread;
        handlerThread.start();
    }

    public void setOutputFile(String str) {
        this.mOutputFile = str;
    }
}
