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
import com.jingdong.common.unification.video.VideoUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicReference;
import tv.danmaku.ijk.media.alpha.util.MediaUtil;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
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
    */
    private void doExtractDecodeEditEncodeMux(MediaExtractor mediaExtractor, MediaExtractor mediaExtractor2, MediaCodec mediaCodec, MediaCodec mediaCodec2, MediaCodec mediaCodec3, MediaCodec mediaCodec4, MediaMuxer mediaMuxer, InputSurface inputSurface, OutputSurfaceWithFilter outputSurfaceWithFilter) {
        ByteBuffer[] byteBufferArr;
        ByteBuffer[] byteBufferArr2;
        ByteBuffer[] byteBufferArr3;
        MediaCodec.BufferInfo bufferInfo;
        MediaCodec.BufferInfo bufferInfo2;
        MediaCodec.BufferInfo bufferInfo3;
        ByteBuffer[] byteBufferArr4;
        MediaCodec.BufferInfo bufferInfo4;
        ByteBuffer[] byteBufferArr5;
        ByteBuffer[] byteBufferArr6;
        ByteBuffer[] byteBufferArr7;
        MediaCodec.BufferInfo bufferInfo5;
        MediaCodec.BufferInfo bufferInfo6;
        int i2;
        int i3;
        int i4;
        ByteBuffer[] byteBufferArr8;
        MediaCodec.BufferInfo bufferInfo7;
        MediaCodec.BufferInfo bufferInfo8;
        long j2;
        int i5;
        MediaCodec.BufferInfo bufferInfo9;
        MediaCodec.BufferInfo bufferInfo10;
        int i6;
        int i7;
        MediaCodec.BufferInfo bufferInfo11;
        boolean z;
        int i8;
        MediaMuxer mediaMuxer2;
        int i9;
        long j3;
        MediaFormat mediaFormat;
        MediaCodec.BufferInfo bufferInfo12;
        MediaCodec.BufferInfo bufferInfo13;
        MediaFormat mediaFormat2;
        int i10;
        boolean z2;
        long j4;
        boolean z3;
        int dequeueInputBuffer;
        MediaCodec.BufferInfo bufferInfo14;
        int dequeueInputBuffer2;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        MediaCodec mediaCodec5 = mediaCodec;
        MediaCodec mediaCodec6 = mediaCodec2;
        MediaCodec mediaCodec7 = mediaCodec3;
        if (this.mCopyVideo) {
            ByteBuffer[] inputBuffers = mediaCodec.getInputBuffers();
            byteBufferArr = mediaCodec.getOutputBuffers();
            byteBufferArr2 = mediaCodec2.getOutputBuffers();
            byteBufferArr3 = inputBuffers;
            bufferInfo = new MediaCodec.BufferInfo();
            bufferInfo2 = new MediaCodec.BufferInfo();
        } else {
            byteBufferArr = null;
            byteBufferArr2 = null;
            byteBufferArr3 = null;
            bufferInfo = null;
            bufferInfo2 = null;
        }
        if (this.mCopyAudio) {
            ByteBuffer[] inputBuffers2 = mediaCodec3.getInputBuffers();
            byteBufferArr4 = mediaCodec3.getOutputBuffers();
            ByteBuffer[] inputBuffers3 = mediaCodec4.getInputBuffers();
            byteBufferArr5 = mediaCodec4.getOutputBuffers();
            MediaCodec.BufferInfo bufferInfo15 = new MediaCodec.BufferInfo();
            byteBufferArr6 = inputBuffers2;
            bufferInfo3 = new MediaCodec.BufferInfo();
            byteBufferArr7 = inputBuffers3;
            bufferInfo4 = bufferInfo15;
        } else {
            bufferInfo3 = null;
            byteBufferArr4 = null;
            bufferInfo4 = null;
            byteBufferArr5 = null;
            byteBufferArr6 = null;
            byteBufferArr7 = null;
        }
        MediaFormat mediaFormat3 = null;
        ByteBuffer[] byteBufferArr9 = byteBufferArr2;
        ByteBuffer[] byteBufferArr10 = byteBufferArr4;
        ByteBuffer[] byteBufferArr11 = byteBufferArr5;
        long j5 = 0;
        ByteBuffer[] byteBufferArr12 = byteBufferArr;
        int i17 = -1;
        int i18 = -1;
        int i19 = -1;
        boolean z4 = false;
        boolean z5 = false;
        int i20 = 0;
        boolean z6 = false;
        int i21 = 0;
        boolean z7 = false;
        int i22 = 0;
        int i23 = 0;
        boolean z8 = false;
        int i24 = 0;
        boolean z9 = false;
        int i25 = 0;
        boolean z10 = false;
        MediaFormat mediaFormat4 = null;
        while (true) {
            boolean z11 = this.mCopyVideo;
            if ((!z11 || z4) && (!this.mCopyAudio || z5)) {
                return;
            }
            if (UnLog.D) {
                bufferInfo5 = bufferInfo3;
                bufferInfo6 = bufferInfo4;
                UnLog.d(TAG, String.format("loop: V(%b){extracted:%d(done:%b) decoded:%d(done:%b) encoded:%d(done:%b)} A(%b){extracted:%d(done:%b) decoded:%d(done:%b) encoded:%d(done:%b) pending:%d} muxing:%b(V:%d,A:%d)", Boolean.valueOf(z11), Integer.valueOf(i20), Boolean.valueOf(z6), Integer.valueOf(i21), Boolean.valueOf(z7), Integer.valueOf(i22), Boolean.valueOf(z4), Boolean.valueOf(this.mCopyAudio), Integer.valueOf(i23), Boolean.valueOf(z8), Integer.valueOf(i24), Boolean.valueOf(z9), Integer.valueOf(i25), Boolean.valueOf(z5), Integer.valueOf(i19), Boolean.valueOf(z10), Integer.valueOf(i18), Integer.valueOf(i17)));
            } else {
                bufferInfo5 = bufferInfo3;
                bufferInfo6 = bufferInfo4;
            }
            if (this.mCopyVideo && !z6 && (mediaFormat4 == null || z10)) {
                int dequeueInputBuffer3 = mediaCodec5.dequeueInputBuffer(10000L);
                if (dequeueInputBuffer3 < 0) {
                    if (UnLog.D) {
                        UnLog.d(TAG, "no video decoder input buffer");
                    }
                } else {
                    if (UnLog.D) {
                        UnLog.d(TAG, "video decoder: returned input buffer: " + dequeueInputBuffer3);
                    }
                    int readSampleData = mediaExtractor.readSampleData(byteBufferArr3[dequeueInputBuffer3], 0);
                    int i26 = i17;
                    long sampleTime = mediaExtractor.getSampleTime();
                    if (UnLog.D) {
                        String str = TAG;
                        i13 = i18;
                        StringBuilder sb = new StringBuilder();
                        i14 = i19;
                        sb.append("video extractor: returned buffer of size ");
                        sb.append(readSampleData);
                        UnLog.d(str, sb.toString());
                        UnLog.d(str, "video extractor: returned buffer for time " + sampleTime);
                    } else {
                        i13 = i18;
                        i14 = i19;
                    }
                    if (readSampleData >= 0) {
                        MediaCodec.BufferInfo bufferInfo16 = bufferInfo5;
                        i16 = readSampleData;
                        bufferInfo7 = bufferInfo16;
                        byteBufferArr8 = byteBufferArr3;
                        j2 = 10000;
                        i2 = i26;
                        i15 = dequeueInputBuffer3;
                        bufferInfo8 = bufferInfo6;
                        i3 = i13;
                        i4 = i14;
                        mediaCodec.queueInputBuffer(dequeueInputBuffer3, 0, i16, sampleTime, mediaExtractor.getSampleFlags());
                    } else {
                        i15 = dequeueInputBuffer3;
                        byteBufferArr8 = byteBufferArr3;
                        i2 = i26;
                        bufferInfo7 = bufferInfo5;
                        bufferInfo8 = bufferInfo6;
                        i3 = i13;
                        i4 = i14;
                        j2 = 10000;
                        i16 = readSampleData;
                    }
                    mediaExtractor.advance();
                    if (i16 < 0) {
                        if (UnLog.D) {
                            UnLog.d(TAG, "video extractor: EOS");
                        }
                        mediaCodec.queueInputBuffer(i15, 0, 0, 0L, 4);
                        z6 = true;
                    }
                    i20++;
                    if (this.mCopyAudio && !z8 && (mediaFormat3 == null || z10)) {
                        dequeueInputBuffer2 = mediaCodec7.dequeueInputBuffer(j2);
                        if (dequeueInputBuffer2 >= 0) {
                            if (UnLog.D) {
                                UnLog.d(TAG, "no audio decoder input buffer");
                            }
                        } else {
                            if (UnLog.D) {
                                UnLog.d(TAG, "audio decoder: returned input buffer: " + dequeueInputBuffer2);
                            }
                            int readSampleData2 = mediaExtractor2.readSampleData(byteBufferArr6[dequeueInputBuffer2], 0);
                            long sampleTime2 = mediaExtractor2.getSampleTime();
                            if (UnLog.D) {
                                String str2 = TAG;
                                UnLog.d(str2, "audio extractor: returned buffer of size " + readSampleData2);
                                UnLog.d(str2, "audio extractor: returned buffer for time " + sampleTime2);
                            }
                            if (readSampleData2 >= 0) {
                                i11 = readSampleData2;
                                i12 = dequeueInputBuffer2;
                                mediaCodec3.queueInputBuffer(dequeueInputBuffer2, 0, readSampleData2, sampleTime2, mediaExtractor2.getSampleFlags());
                            } else {
                                i11 = readSampleData2;
                                i12 = dequeueInputBuffer2;
                            }
                            mediaExtractor2.advance();
                            if (i11 < 0) {
                                if (UnLog.D) {
                                    UnLog.d(TAG, "audio extractor: EOS");
                                }
                                mediaCodec3.queueInputBuffer(i12, 0, 0, 0L, 4);
                                z8 = true;
                            }
                            i23++;
                        }
                    }
                    if (this.mCopyVideo || z7 || !(mediaFormat4 == null || z10)) {
                        i5 = -1;
                    } else {
                        int dequeueOutputBuffer = mediaCodec5.dequeueOutputBuffer(bufferInfo, 10000L);
                        i5 = -1;
                        if (dequeueOutputBuffer == -1) {
                            if (UnLog.D) {
                                UnLog.d(TAG, "no video decoder output buffer");
                            }
                        } else if (dequeueOutputBuffer == -3) {
                            if (UnLog.D) {
                                UnLog.d(TAG, "video decoder: output buffers changed");
                            }
                            byteBufferArr12 = mediaCodec.getOutputBuffers();
                        } else if (dequeueOutputBuffer == -2) {
                            MediaFormat outputFormat = mediaCodec.getOutputFormat();
                            if (UnLog.D) {
                                UnLog.d(TAG, "video decoder: output format changed: " + outputFormat);
                            }
                        } else {
                            if (UnLog.D) {
                                String str3 = TAG;
                                UnLog.d(str3, "video decoder: returned output buffer: " + dequeueOutputBuffer);
                                UnLog.d(str3, "video decoder: returned buffer of size " + bufferInfo.size);
                                UnLog.d(str3, "video decoder: returned buffer of flags " + bufferInfo.flags);
                            }
                            ByteBuffer byteBuffer = byteBufferArr12[dequeueOutputBuffer];
                            if ((bufferInfo.flags & 2) != 0) {
                                if (UnLog.D) {
                                    UnLog.d(TAG, "video decoder: codec config buffer");
                                }
                                mediaCodec5.releaseOutputBuffer(dequeueOutputBuffer, false);
                            } else {
                                if (UnLog.D) {
                                    UnLog.d(TAG, "video decoder: returned buffer for time " + bufferInfo.presentationTimeUs);
                                }
                                boolean z12 = bufferInfo.size != 0;
                                mediaCodec5.releaseOutputBuffer(dequeueOutputBuffer, z12);
                                if (z12) {
                                    if (UnLog.D) {
                                        UnLog.d(TAG, "output surface: await new image");
                                    }
                                    outputSurfaceWithFilter.awaitNewImage();
                                    if (UnLog.D) {
                                        UnLog.d(TAG, "output surface: draw image");
                                    }
                                    outputSurfaceWithFilter.drawImage();
                                    inputSurface.setPresentationTime(bufferInfo.presentationTimeUs * 1000);
                                    if (UnLog.D) {
                                        UnLog.d(TAG, "input surface: swap buffers");
                                    }
                                    inputSurface.swapBuffers();
                                    if (UnLog.D) {
                                        UnLog.d(TAG, "video encoder: notified of new frame");
                                    }
                                }
                                if ((bufferInfo.flags & 4) != 0) {
                                    if (UnLog.D) {
                                        UnLog.d(TAG, "video decoder: EOS");
                                    }
                                    mediaCodec2.signalEndOfInputStream();
                                    z7 = true;
                                }
                                i21++;
                            }
                        }
                    }
                    if (this.mCopyAudio || z9) {
                        bufferInfo9 = bufferInfo;
                        bufferInfo10 = bufferInfo8;
                        i6 = i4;
                    } else {
                        i6 = i4;
                        if (i6 == i5 && (mediaFormat3 == null || z10)) {
                            bufferInfo10 = bufferInfo8;
                            int dequeueOutputBuffer2 = mediaCodec7.dequeueOutputBuffer(bufferInfo10, 10000L);
                            if (dequeueOutputBuffer2 == i5) {
                                if (UnLog.D) {
                                    UnLog.d(TAG, "no audio decoder output buffer");
                                }
                            } else {
                                if (dequeueOutputBuffer2 == -3) {
                                    if (UnLog.D) {
                                        UnLog.d(TAG, "audio decoder: output buffers changed");
                                    }
                                    i7 = i6;
                                    byteBufferArr10 = mediaCodec3.getOutputBuffers();
                                    bufferInfo9 = bufferInfo;
                                } else if (dequeueOutputBuffer2 == -2) {
                                    MediaFormat outputFormat2 = mediaCodec3.getOutputFormat();
                                    if (UnLog.D) {
                                        UnLog.d(TAG, "audio decoder: output format changed: " + outputFormat2);
                                    }
                                } else {
                                    if (UnLog.D) {
                                        UnLog.d(TAG, "audio decoder: returned output buffer: " + dequeueOutputBuffer2);
                                    }
                                    if (UnLog.D) {
                                        UnLog.d(TAG, "audio decoder: returned buffer of size " + bufferInfo10.size);
                                    }
                                    ByteBuffer byteBuffer2 = byteBufferArr10[dequeueOutputBuffer2];
                                    if ((bufferInfo10.flags & 2) != 0) {
                                        if (UnLog.D) {
                                            UnLog.d(TAG, "audio decoder: codec config buffer");
                                        }
                                        mediaCodec7.releaseOutputBuffer(dequeueOutputBuffer2, false);
                                    } else {
                                        if (UnLog.D) {
                                            String str4 = TAG;
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append("audio decoder: returned buffer for time ");
                                            bufferInfo9 = bufferInfo;
                                            sb2.append(bufferInfo10.presentationTimeUs);
                                            UnLog.d(str4, sb2.toString());
                                        } else {
                                            bufferInfo9 = bufferInfo;
                                        }
                                        if (UnLog.D) {
                                            UnLog.d(TAG, "audio decoder: output buffer is now pending: " + i6);
                                        }
                                        i24++;
                                        i7 = dequeueOutputBuffer2;
                                    }
                                }
                                if (this.mCopyAudio && i7 != i5) {
                                    if (UnLog.D) {
                                        UnLog.d(TAG, "audio decoder: attempting to process pending buffer: " + i7);
                                    }
                                    dequeueInputBuffer = mediaCodec4.dequeueInputBuffer(10000L);
                                    if (dequeueInputBuffer != i5) {
                                        if (UnLog.D) {
                                            UnLog.d(TAG, "no audio encoder input buffer");
                                        }
                                    } else {
                                        if (UnLog.D) {
                                            UnLog.d(TAG, "audio encoder: returned input buffer: " + dequeueInputBuffer);
                                        }
                                        ByteBuffer byteBuffer3 = byteBufferArr7[dequeueInputBuffer];
                                        int i27 = bufferInfo10.size;
                                        long j6 = bufferInfo10.presentationTimeUs;
                                        if (UnLog.D) {
                                            UnLog.d(TAG, "audio decoder: processing pending buffer: " + i7);
                                        }
                                        if (UnLog.D) {
                                            String str5 = TAG;
                                            UnLog.d(str5, "audio decoder: pending buffer of size " + i27);
                                            UnLog.d(str5, "audio decoder: pending buffer for time " + j6);
                                        }
                                        if (i27 >= 0) {
                                            ByteBuffer duplicate = byteBufferArr10[i7].duplicate();
                                            duplicate.position(bufferInfo10.offset);
                                            byteBuffer3.position(0);
                                            int limit = byteBuffer3.limit();
                                            if (i27 <= limit) {
                                                limit = i27;
                                            }
                                            duplicate.limit(bufferInfo10.offset + limit);
                                            byteBuffer3.put(duplicate);
                                            bufferInfo14 = bufferInfo10;
                                            i8 = -1;
                                            mediaCodec4.queueInputBuffer(dequeueInputBuffer, 0, i27, j6, bufferInfo10.flags);
                                        } else {
                                            bufferInfo14 = bufferInfo10;
                                            i8 = -1;
                                        }
                                        z = false;
                                        mediaCodec7.releaseOutputBuffer(i7, false);
                                        bufferInfo11 = bufferInfo14;
                                        if ((bufferInfo11.flags & 4) != 0) {
                                            if (UnLog.D) {
                                                UnLog.d(TAG, "audio decoder: EOS");
                                            }
                                            i19 = -1;
                                            z9 = true;
                                        } else {
                                            i19 = -1;
                                        }
                                        if (this.mCopyVideo || z4 || !(mediaFormat4 == null || z10)) {
                                            mediaMuxer2 = mediaMuxer;
                                            i9 = i3;
                                            j3 = 10000;
                                        } else {
                                            int dequeueOutputBuffer3 = mediaCodec6.dequeueOutputBuffer(bufferInfo2, 10000L);
                                            if (dequeueOutputBuffer3 == i8) {
                                                if (UnLog.D) {
                                                    UnLog.d(TAG, "no video encoder output buffer");
                                                }
                                            } else {
                                                if (dequeueOutputBuffer3 == -3) {
                                                    if (UnLog.D) {
                                                        UnLog.d(TAG, "video encoder: output buffers changed");
                                                    }
                                                    j3 = 10000;
                                                    byteBufferArr9 = mediaCodec2.getOutputBuffers();
                                                    mediaFormat = mediaFormat4;
                                                } else if (dequeueOutputBuffer3 == -2) {
                                                    if (UnLog.D) {
                                                        UnLog.d(TAG, "video encoder: output format changed");
                                                    }
                                                    mediaFormat = mediaCodec2.getOutputFormat();
                                                    j3 = 10000;
                                                } else {
                                                    if (UnLog.D) {
                                                        String str6 = TAG;
                                                        UnLog.d(str6, "video encoder: returned output buffer: " + dequeueOutputBuffer3);
                                                        UnLog.d(str6, "video encoder: returned buffer of size " + bufferInfo2.size);
                                                    }
                                                    ByteBuffer byteBuffer4 = byteBufferArr9[dequeueOutputBuffer3];
                                                    if ((bufferInfo2.flags & 2) != 0) {
                                                        if (UnLog.D) {
                                                            UnLog.d(TAG, "video encoder: codec config buffer");
                                                        }
                                                        mediaCodec6.releaseOutputBuffer(dequeueOutputBuffer3, z);
                                                    } else {
                                                        if (UnLog.D) {
                                                            UnLog.d(TAG, "video encoder: returned buffer for time " + bufferInfo2.presentationTimeUs);
                                                        }
                                                        if (bufferInfo2.size != 0) {
                                                            mediaMuxer2 = mediaMuxer;
                                                            i9 = i3;
                                                            j3 = 10000;
                                                            mediaMuxer2.writeSampleData(i9, byteBuffer4, bufferInfo2);
                                                        } else {
                                                            mediaMuxer2 = mediaMuxer;
                                                            i9 = i3;
                                                            j3 = 10000;
                                                        }
                                                        if ((bufferInfo2.flags & 4) != 0) {
                                                            if (UnLog.D) {
                                                                UnLog.d(TAG, "video encoder: EOS");
                                                            }
                                                            z4 = true;
                                                        }
                                                        mediaCodec6.releaseOutputBuffer(dequeueOutputBuffer3, z);
                                                        i22++;
                                                    }
                                                }
                                                i9 = i3;
                                                mediaMuxer2 = mediaMuxer;
                                                if (this.mCopyAudio || z5 || !(mediaFormat3 == null || z10)) {
                                                    bufferInfo12 = bufferInfo11;
                                                    bufferInfo13 = bufferInfo7;
                                                } else {
                                                    bufferInfo13 = bufferInfo7;
                                                    int dequeueOutputBuffer4 = mediaCodec4.dequeueOutputBuffer(bufferInfo13, j3);
                                                    if (dequeueOutputBuffer4 != -1) {
                                                        if (dequeueOutputBuffer4 == -3) {
                                                            if (UnLog.D) {
                                                                UnLog.d(TAG, "audio encoder: output buffers changed");
                                                            }
                                                            bufferInfo12 = bufferInfo11;
                                                            byteBufferArr11 = mediaCodec4.getOutputBuffers();
                                                            mediaFormat2 = mediaFormat3;
                                                        } else if (dequeueOutputBuffer4 == -2) {
                                                            if (UnLog.D) {
                                                                UnLog.d(TAG, "audio encoder: output format changed");
                                                            }
                                                            mediaFormat2 = mediaCodec4.getOutputFormat();
                                                            bufferInfo12 = bufferInfo11;
                                                        } else {
                                                            if (UnLog.D) {
                                                                String str7 = TAG;
                                                                UnLog.d(str7, "audio encoder: returned output buffer: " + dequeueOutputBuffer4);
                                                                UnLog.d(str7, "audio encoder: returned buffer of size " + bufferInfo13.size);
                                                            }
                                                            ByteBuffer byteBuffer5 = byteBufferArr11[dequeueOutputBuffer4];
                                                            if ((bufferInfo13.flags & 2) != 0) {
                                                                if (UnLog.D) {
                                                                    UnLog.d(TAG, "audio encoder: codec config buffer");
                                                                }
                                                                mediaCodec4.releaseOutputBuffer(dequeueOutputBuffer4, false);
                                                            } else {
                                                                if (UnLog.D) {
                                                                    String str8 = TAG;
                                                                    StringBuilder sb3 = new StringBuilder();
                                                                    bufferInfo12 = bufferInfo11;
                                                                    sb3.append("audio encoder: returned buffer for time ");
                                                                    sb3.append(bufferInfo13.presentationTimeUs);
                                                                    UnLog.d(str8, sb3.toString());
                                                                } else {
                                                                    bufferInfo12 = bufferInfo11;
                                                                }
                                                                if (j5 == 0) {
                                                                    j4 = bufferInfo13.presentationTimeUs;
                                                                } else {
                                                                    if (j5 > bufferInfo13.presentationTimeUs) {
                                                                        bufferInfo13.presentationTimeUs = j5 + 1;
                                                                    }
                                                                    j4 = bufferInfo13.presentationTimeUs;
                                                                }
                                                                j5 = j4;
                                                                if (bufferInfo13.size != 0) {
                                                                    i10 = i2;
                                                                    mediaMuxer2.writeSampleData(i10, byteBuffer5, bufferInfo13);
                                                                } else {
                                                                    i10 = i2;
                                                                }
                                                                if ((bufferInfo13.flags & 4) != 0) {
                                                                    if (UnLog.D) {
                                                                        UnLog.d(TAG, "audio encoder: EOS");
                                                                    }
                                                                    z3 = false;
                                                                    z5 = true;
                                                                } else {
                                                                    z3 = false;
                                                                }
                                                                mediaCodec4.releaseOutputBuffer(dequeueOutputBuffer4, z3);
                                                                i25++;
                                                                mediaFormat2 = mediaFormat3;
                                                                if (!z10 || ((this.mCopyAudio && mediaFormat2 == null) || ((z2 = this.mCopyVideo) && mediaFormat == null))) {
                                                                    mediaCodec6 = mediaCodec2;
                                                                    mediaCodec7 = mediaCodec3;
                                                                    mediaFormat4 = mediaFormat;
                                                                    bufferInfo3 = bufferInfo13;
                                                                    mediaFormat3 = mediaFormat2;
                                                                    bufferInfo = bufferInfo9;
                                                                    byteBufferArr3 = byteBufferArr8;
                                                                    bufferInfo4 = bufferInfo12;
                                                                } else {
                                                                    if (z2) {
                                                                        UnLog.d(TAG, "muxer: adding video track.");
                                                                        i9 = mediaMuxer2.addTrack(mediaFormat);
                                                                    }
                                                                    if (this.mCopyAudio) {
                                                                        UnLog.d(TAG, "muxer: adding audio track.");
                                                                        i10 = mediaMuxer2.addTrack(mediaFormat2);
                                                                    }
                                                                    UnLog.d(TAG, "muxer: starting");
                                                                    mediaMuxer.start();
                                                                    mediaCodec6 = mediaCodec2;
                                                                    mediaCodec7 = mediaCodec3;
                                                                    mediaFormat4 = mediaFormat;
                                                                    bufferInfo3 = bufferInfo13;
                                                                    mediaFormat3 = mediaFormat2;
                                                                    bufferInfo = bufferInfo9;
                                                                    byteBufferArr3 = byteBufferArr8;
                                                                    bufferInfo4 = bufferInfo12;
                                                                    z10 = true;
                                                                }
                                                                mediaCodec5 = mediaCodec;
                                                                i18 = i9;
                                                                i17 = i10;
                                                            }
                                                        }
                                                        i10 = i2;
                                                        if (z10) {
                                                        }
                                                        mediaCodec6 = mediaCodec2;
                                                        mediaCodec7 = mediaCodec3;
                                                        mediaFormat4 = mediaFormat;
                                                        bufferInfo3 = bufferInfo13;
                                                        mediaFormat3 = mediaFormat2;
                                                        bufferInfo = bufferInfo9;
                                                        byteBufferArr3 = byteBufferArr8;
                                                        bufferInfo4 = bufferInfo12;
                                                        mediaCodec5 = mediaCodec;
                                                        i18 = i9;
                                                        i17 = i10;
                                                    } else if (UnLog.D) {
                                                        UnLog.d(TAG, "no audio encoder output buffer");
                                                    }
                                                    bufferInfo12 = bufferInfo11;
                                                }
                                                i10 = i2;
                                                mediaFormat2 = mediaFormat3;
                                                if (z10) {
                                                }
                                                mediaCodec6 = mediaCodec2;
                                                mediaCodec7 = mediaCodec3;
                                                mediaFormat4 = mediaFormat;
                                                bufferInfo3 = bufferInfo13;
                                                mediaFormat3 = mediaFormat2;
                                                bufferInfo = bufferInfo9;
                                                byteBufferArr3 = byteBufferArr8;
                                                bufferInfo4 = bufferInfo12;
                                                mediaCodec5 = mediaCodec;
                                                i18 = i9;
                                                i17 = i10;
                                            }
                                            j3 = 10000;
                                            i9 = i3;
                                            mediaMuxer2 = mediaMuxer;
                                        }
                                        mediaFormat = mediaFormat4;
                                        if (this.mCopyAudio) {
                                        }
                                        bufferInfo12 = bufferInfo11;
                                        bufferInfo13 = bufferInfo7;
                                        i10 = i2;
                                        mediaFormat2 = mediaFormat3;
                                        if (z10) {
                                        }
                                        mediaCodec6 = mediaCodec2;
                                        mediaCodec7 = mediaCodec3;
                                        mediaFormat4 = mediaFormat;
                                        bufferInfo3 = bufferInfo13;
                                        mediaFormat3 = mediaFormat2;
                                        bufferInfo = bufferInfo9;
                                        byteBufferArr3 = byteBufferArr8;
                                        bufferInfo4 = bufferInfo12;
                                        mediaCodec5 = mediaCodec;
                                        i18 = i9;
                                        i17 = i10;
                                    }
                                }
                                bufferInfo11 = bufferInfo10;
                                z = false;
                                i8 = -1;
                                i19 = i7;
                                if (this.mCopyVideo) {
                                }
                                mediaMuxer2 = mediaMuxer;
                                i9 = i3;
                                j3 = 10000;
                                mediaFormat = mediaFormat4;
                                if (this.mCopyAudio) {
                                }
                                bufferInfo12 = bufferInfo11;
                                bufferInfo13 = bufferInfo7;
                                i10 = i2;
                                mediaFormat2 = mediaFormat3;
                                if (z10) {
                                }
                                mediaCodec6 = mediaCodec2;
                                mediaCodec7 = mediaCodec3;
                                mediaFormat4 = mediaFormat;
                                bufferInfo3 = bufferInfo13;
                                mediaFormat3 = mediaFormat2;
                                bufferInfo = bufferInfo9;
                                byteBufferArr3 = byteBufferArr8;
                                bufferInfo4 = bufferInfo12;
                                mediaCodec5 = mediaCodec;
                                i18 = i9;
                                i17 = i10;
                            }
                            bufferInfo9 = bufferInfo;
                        } else {
                            bufferInfo9 = bufferInfo;
                            bufferInfo10 = bufferInfo8;
                        }
                    }
                    i7 = i6;
                    if (this.mCopyAudio) {
                        if (UnLog.D) {
                        }
                        dequeueInputBuffer = mediaCodec4.dequeueInputBuffer(10000L);
                        if (dequeueInputBuffer != i5) {
                        }
                    }
                    bufferInfo11 = bufferInfo10;
                    z = false;
                    i8 = -1;
                    i19 = i7;
                    if (this.mCopyVideo) {
                    }
                    mediaMuxer2 = mediaMuxer;
                    i9 = i3;
                    j3 = 10000;
                    mediaFormat = mediaFormat4;
                    if (this.mCopyAudio) {
                    }
                    bufferInfo12 = bufferInfo11;
                    bufferInfo13 = bufferInfo7;
                    i10 = i2;
                    mediaFormat2 = mediaFormat3;
                    if (z10) {
                    }
                    mediaCodec6 = mediaCodec2;
                    mediaCodec7 = mediaCodec3;
                    mediaFormat4 = mediaFormat;
                    bufferInfo3 = bufferInfo13;
                    mediaFormat3 = mediaFormat2;
                    bufferInfo = bufferInfo9;
                    byteBufferArr3 = byteBufferArr8;
                    bufferInfo4 = bufferInfo12;
                    mediaCodec5 = mediaCodec;
                    i18 = i9;
                    i17 = i10;
                }
            }
            i2 = i17;
            i3 = i18;
            i4 = i19;
            byteBufferArr8 = byteBufferArr3;
            bufferInfo7 = bufferInfo5;
            bufferInfo8 = bufferInfo6;
            j2 = 10000;
            if (this.mCopyAudio) {
                dequeueInputBuffer2 = mediaCodec7.dequeueInputBuffer(j2);
                if (dequeueInputBuffer2 >= 0) {
                }
            }
            if (this.mCopyVideo) {
            }
            i5 = -1;
            if (this.mCopyAudio) {
            }
            bufferInfo9 = bufferInfo;
            bufferInfo10 = bufferInfo8;
            i6 = i4;
            i7 = i6;
            if (this.mCopyAudio) {
            }
            bufferInfo11 = bufferInfo10;
            z = false;
            i8 = -1;
            i19 = i7;
            if (this.mCopyVideo) {
            }
            mediaMuxer2 = mediaMuxer;
            i9 = i3;
            j3 = 10000;
            mediaFormat = mediaFormat4;
            if (this.mCopyAudio) {
            }
            bufferInfo12 = bufferInfo11;
            bufferInfo13 = bufferInfo7;
            i10 = i2;
            mediaFormat2 = mediaFormat3;
            if (z10) {
            }
            mediaCodec6 = mediaCodec2;
            mediaCodec7 = mediaCodec3;
            mediaFormat4 = mediaFormat;
            bufferInfo3 = bufferInfo13;
            mediaFormat3 = mediaFormat2;
            bufferInfo = bufferInfo9;
            byteBufferArr3 = byteBufferArr8;
            bufferInfo4 = bufferInfo12;
            mediaCodec5 = mediaCodec;
            i18 = i9;
            i17 = i10;
        }
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
    */
    public void extractDecodeEditEncodeMux() throws Exception {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        Throwable th;
        MediaExtractor mediaExtractor;
        OutputSurfaceWithFilter outputSurfaceWithFilter;
        MediaCodec mediaCodec;
        MediaCodec mediaCodec2;
        MediaExtractor mediaExtractor2;
        MediaCodec mediaCodec3;
        MediaCodec mediaCodec4;
        MediaMuxer mediaMuxer;
        InputSurface inputSurface;
        String str11;
        MediaFormat trackFormat;
        String str12;
        int i2;
        String str13;
        String str14;
        InputSurface inputSurface2;
        String str15;
        OutputSurfaceWithFilter outputSurfaceWithFilter2;
        MediaExtractor mediaExtractor3;
        MediaCodec createVideoDecoder;
        MediaExtractor mediaExtractor4;
        String str16;
        MediaCodecInfo selectCodec = selectCodec("video/avc");
        if (selectCodec == null) {
            if (UnLog.E) {
                UnLog.e(TAG, "Unable to find an appropriate codec for video/avc");
                return;
            }
            return;
        }
        MediaCodecInfo selectCodec2 = selectCodec(OUTPUT_AUDIO_MIME_TYPE);
        if (selectCodec2 == null) {
            if (UnLog.E) {
                UnLog.e(TAG, "Unable to find an appropriate codec for audio/mp4a-latm");
                return;
            }
            return;
        }
        Exception exc = null;
        try {
            if (this.mCopyVideo) {
                try {
                    MediaExtractor createExtractor = createExtractor();
                    str11 = "error while releasing videoExtractor";
                    try {
                        trackFormat = createExtractor.getTrackFormat(getAndSelectVideoTrackIndex(createExtractor));
                        mediaExtractor2 = createExtractor;
                    } catch (Throwable th2) {
                        th = th2;
                        mediaExtractor2 = createExtractor;
                    }
                    try {
                        int i3 = this.mVideoOrientation;
                        str12 = "error while releasing audioExtractor";
                        if (i3 == 90 || i3 == 270) {
                            try {
                                int i4 = this.mHeight;
                                this.mHeight = this.mWidth;
                                this.mWidth = i4;
                            } catch (Throwable th3) {
                                th = th3;
                                str2 = "error while releasing videoEncoder";
                                str9 = "error while releasing inputSurface";
                                str7 = "error while releasing muxer";
                                str3 = "error while releasing audioEncoder";
                                str4 = "error while releasing audioDecoder";
                                mediaExtractor = null;
                                outputSurfaceWithFilter = null;
                                mediaCodec2 = null;
                                mediaCodec3 = null;
                                mediaCodec4 = null;
                                mediaMuxer = null;
                                inputSurface = null;
                                str5 = "releasing extractor, decoder, encoder, and muxer";
                                str8 = str11;
                                str = str12;
                                str10 = "error while releasing videoDecoder";
                                str6 = "error while releasing outputSurface";
                                mediaCodec = inputSurface;
                                th = th;
                                if (UnLog.D) {
                                    UnLog.d(TAG, str5);
                                }
                                if (mediaExtractor2 != null) {
                                    try {
                                        mediaExtractor2.release();
                                    } catch (Exception e2) {
                                        UnLog.e(TAG, str8, e2);
                                        exc = e2;
                                    }
                                }
                                if (mediaExtractor != null) {
                                    try {
                                        mediaExtractor.release();
                                    } catch (Exception e3) {
                                        UnLog.e(TAG, str, e3);
                                        if (exc == null) {
                                            exc = e3;
                                        }
                                    }
                                }
                                if (mediaCodec != null) {
                                    try {
                                        mediaCodec.stop();
                                        mediaCodec.release();
                                    } catch (Exception e4) {
                                        UnLog.e(TAG, str10, e4);
                                        if (exc == null) {
                                            exc = e4;
                                        }
                                    }
                                }
                                if (outputSurfaceWithFilter != null) {
                                    try {
                                        outputSurfaceWithFilter.release();
                                    } catch (Exception e5) {
                                        UnLog.e(TAG, str6, e5);
                                        if (exc == null) {
                                            exc = e5;
                                        }
                                    }
                                }
                                if (mediaCodec3 != null) {
                                    try {
                                        mediaCodec3.stop();
                                        mediaCodec3.release();
                                    } catch (Exception e6) {
                                        UnLog.e(TAG, str2, e6);
                                        if (exc == null) {
                                            exc = e6;
                                        }
                                    }
                                }
                                if (mediaCodec2 != null) {
                                    try {
                                        mediaCodec2.stop();
                                        mediaCodec2.release();
                                    } catch (Exception e7) {
                                        UnLog.e(TAG, str4, e7);
                                        if (exc == null) {
                                            exc = e7;
                                        }
                                    }
                                }
                                if (mediaCodec4 != null) {
                                    try {
                                        mediaCodec4.stop();
                                        mediaCodec4.release();
                                    } catch (Exception e8) {
                                        UnLog.e(TAG, str3, e8);
                                        if (exc == null) {
                                            exc = e8;
                                        }
                                    }
                                }
                                if (mediaMuxer != null) {
                                    try {
                                        mediaMuxer.stop();
                                        mediaMuxer.release();
                                    } catch (Exception e9) {
                                        UnLog.e(TAG, str7, e9);
                                        if (exc == null) {
                                        }
                                    }
                                }
                                if (inputSurface != null) {
                                    try {
                                        inputSurface.release();
                                    } catch (Exception e10) {
                                        UnLog.e(TAG, str9, e10);
                                    }
                                }
                                throw th;
                            }
                        }
                        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", this.mWidth, this.mHeight);
                        createVideoFormat.setInteger("color-format", OUTPUT_VIDEO_COLOR_FORMAT);
                        try {
                            i2 = trackFormat.getInteger(IjkMediaMeta.IJKM_KEY_BITRATE);
                        } catch (Exception e11) {
                            if (UnLog.E) {
                                UnLog.e(TAG, e11.toString());
                            }
                            i2 = -1;
                        }
                        if (i2 <= 0) {
                            i2 = VideoUtil.getVideoBitRate(this.mWidth, this.mHeight);
                        }
                        createVideoFormat.setInteger(IjkMediaMeta.IJKM_KEY_BITRATE, i2);
                        str13 = "error while releasing videoDecoder";
                        try {
                            createVideoFormat.setInteger("frame-rate", 15);
                            createVideoFormat.setInteger("i-frame-interval", 1);
                            if (UnLog.D) {
                                String str17 = TAG;
                                StringBuilder sb = new StringBuilder();
                                str14 = "error while releasing outputSurface";
                                try {
                                    sb.append("video format: ");
                                    sb.append(createVideoFormat);
                                    UnLog.d(str17, sb.toString());
                                } catch (Throwable th4) {
                                    th = th4;
                                    str2 = "error while releasing videoEncoder";
                                    str9 = "error while releasing inputSurface";
                                    str7 = "error while releasing muxer";
                                    str3 = "error while releasing audioEncoder";
                                    str4 = "error while releasing audioDecoder";
                                    mediaExtractor = null;
                                    outputSurfaceWithFilter = null;
                                    mediaCodec = null;
                                    mediaCodec2 = null;
                                    mediaCodec3 = null;
                                    mediaCodec4 = null;
                                    mediaMuxer = mediaCodec4;
                                    inputSurface = mediaMuxer;
                                    str5 = "releasing extractor, decoder, encoder, and muxer";
                                    str8 = str11;
                                    str = str12;
                                    str10 = str13;
                                    str6 = str14;
                                    if (UnLog.D) {
                                    }
                                    if (mediaExtractor2 != null) {
                                    }
                                    if (mediaExtractor != null) {
                                    }
                                    if (mediaCodec != null) {
                                    }
                                    if (outputSurfaceWithFilter != null) {
                                    }
                                    if (mediaCodec3 != null) {
                                    }
                                    if (mediaCodec2 != null) {
                                    }
                                    if (mediaCodec4 != null) {
                                    }
                                    if (mediaMuxer != null) {
                                    }
                                    if (inputSurface != null) {
                                    }
                                    throw th;
                                }
                            } else {
                                str14 = "error while releasing outputSurface";
                            }
                            if (UnLog.D) {
                                UnLog.d(TAG, "video mVideoOrientation: " + this.mVideoOrientation);
                            }
                            if (UnLog.D) {
                                UnLog.d(TAG, "video bitRate: " + i2);
                            }
                            AtomicReference<Surface> atomicReference = new AtomicReference<>();
                            MediaCodec createVideoEncoder = createVideoEncoder(selectCodec, createVideoFormat, atomicReference);
                            try {
                                createVideoEncoder.setVideoScalingMode(1);
                                inputSurface2 = new InputSurface(atomicReference.get());
                                try {
                                    inputSurface2.makeCurrent();
                                    mediaCodec3 = createVideoEncoder;
                                } catch (Throwable th5) {
                                    th = th5;
                                    mediaCodec3 = createVideoEncoder;
                                }
                            } catch (Throwable th6) {
                                mediaCodec3 = createVideoEncoder;
                                th = th6;
                                str2 = "error while releasing videoEncoder";
                                str9 = "error while releasing inputSurface";
                                str7 = "error while releasing muxer";
                                str3 = "error while releasing audioEncoder";
                                str4 = "error while releasing audioDecoder";
                                mediaExtractor = null;
                                outputSurfaceWithFilter = null;
                                mediaCodec = null;
                                mediaCodec2 = null;
                                mediaCodec4 = null;
                                mediaMuxer = mediaCodec4;
                                inputSurface = mediaMuxer;
                                str5 = "releasing extractor, decoder, encoder, and muxer";
                                str8 = str11;
                                str = str12;
                                str10 = str13;
                                str6 = str14;
                                if (UnLog.D) {
                                }
                                if (mediaExtractor2 != null) {
                                }
                                if (mediaExtractor != null) {
                                }
                                if (mediaCodec != null) {
                                }
                                if (outputSurfaceWithFilter != null) {
                                }
                                if (mediaCodec3 != null) {
                                }
                                if (mediaCodec2 != null) {
                                }
                                if (mediaCodec4 != null) {
                                }
                                if (mediaMuxer != null) {
                                }
                                if (inputSurface != null) {
                                }
                                throw th;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                            str2 = "error while releasing videoEncoder";
                            str9 = "error while releasing inputSurface";
                            str7 = "error while releasing muxer";
                            str3 = "error while releasing audioEncoder";
                            str4 = "error while releasing audioDecoder";
                            mediaExtractor = null;
                            outputSurfaceWithFilter = null;
                            mediaCodec = null;
                            mediaCodec2 = null;
                            mediaCodec3 = null;
                            mediaCodec4 = null;
                            mediaMuxer = null;
                            inputSurface = null;
                            str5 = "releasing extractor, decoder, encoder, and muxer";
                            str8 = str11;
                            str = str12;
                            str10 = str13;
                            str6 = "error while releasing outputSurface";
                            th = th;
                            if (UnLog.D) {
                            }
                            if (mediaExtractor2 != null) {
                            }
                            if (mediaExtractor != null) {
                            }
                            if (mediaCodec != null) {
                            }
                            if (outputSurfaceWithFilter != null) {
                            }
                            if (mediaCodec3 != null) {
                            }
                            if (mediaCodec2 != null) {
                            }
                            if (mediaCodec4 != null) {
                            }
                            if (mediaMuxer != null) {
                            }
                            if (inputSurface != null) {
                            }
                            throw th;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        str = "error while releasing audioExtractor";
                        str2 = "error while releasing videoEncoder";
                        str9 = "error while releasing inputSurface";
                        str7 = "error while releasing muxer";
                        str3 = "error while releasing audioEncoder";
                        str4 = "error while releasing audioDecoder";
                        mediaExtractor = null;
                        outputSurfaceWithFilter = null;
                        mediaCodec2 = null;
                        mediaCodec3 = null;
                        mediaCodec4 = null;
                        mediaMuxer = null;
                        inputSurface = null;
                        str5 = "releasing extractor, decoder, encoder, and muxer";
                        str8 = str11;
                        str10 = "error while releasing videoDecoder";
                        str6 = "error while releasing outputSurface";
                        mediaCodec = inputSurface;
                        th = th;
                        if (UnLog.D) {
                        }
                        if (mediaExtractor2 != null) {
                        }
                        if (mediaExtractor != null) {
                        }
                        if (mediaCodec != null) {
                        }
                        if (outputSurfaceWithFilter != null) {
                        }
                        if (mediaCodec3 != null) {
                        }
                        if (mediaCodec2 != null) {
                        }
                        if (mediaCodec4 != null) {
                        }
                        if (mediaMuxer != null) {
                        }
                        if (inputSurface != null) {
                        }
                        throw th;
                    }
                    try {
                        inputSurface = inputSurface2;
                    } catch (Throwable th9) {
                        th = th9;
                        inputSurface = inputSurface2;
                        th = th;
                        str2 = "error while releasing videoEncoder";
                        str9 = "error while releasing inputSurface";
                        str7 = "error while releasing muxer";
                        str3 = "error while releasing audioEncoder";
                        str4 = "error while releasing audioDecoder";
                        mediaExtractor = null;
                        outputSurfaceWithFilter = null;
                        mediaCodec = null;
                        mediaCodec2 = null;
                        mediaCodec4 = null;
                        mediaMuxer = null;
                        str5 = "releasing extractor, decoder, encoder, and muxer";
                        str8 = str11;
                        str = str12;
                        str10 = str13;
                        str6 = str14;
                        if (UnLog.D) {
                        }
                        if (mediaExtractor2 != null) {
                        }
                        if (mediaExtractor != null) {
                        }
                        if (mediaCodec != null) {
                        }
                        if (outputSurfaceWithFilter != null) {
                        }
                        if (mediaCodec3 != null) {
                        }
                        if (mediaCodec2 != null) {
                        }
                        if (mediaCodec4 != null) {
                        }
                        if (mediaMuxer != null) {
                        }
                        if (inputSurface != null) {
                        }
                        throw th;
                    }
                    try {
                        str15 = "error while releasing videoEncoder";
                        try {
                            outputSurfaceWithFilter = new OutputSurfaceWithFilter(this.mAppContext, this.mFilterType, this.mFilterProgress, this.mWidth, this.mHeight, this.mVideoOrientation);
                        } catch (Throwable th10) {
                            th = th10;
                            str9 = "error while releasing inputSurface";
                            str7 = "error while releasing muxer";
                            str3 = "error while releasing audioEncoder";
                            str4 = "error while releasing audioDecoder";
                            mediaExtractor = null;
                            outputSurfaceWithFilter = null;
                            mediaCodec = null;
                        }
                        try {
                            outputSurfaceWithFilter2 = outputSurfaceWithFilter;
                            mediaExtractor3 = mediaExtractor2;
                            createVideoDecoder = createVideoDecoder(trackFormat, outputSurfaceWithFilter.getSurface());
                        } catch (Throwable th11) {
                            th = th11;
                            str9 = "error while releasing inputSurface";
                            str7 = "error while releasing muxer";
                            str3 = "error while releasing audioEncoder";
                            str4 = "error while releasing audioDecoder";
                            mediaExtractor = null;
                            mediaCodec = null;
                            mediaCodec2 = mediaCodec;
                            mediaCodec4 = mediaCodec2;
                            mediaMuxer = mediaCodec4;
                            str5 = "releasing extractor, decoder, encoder, and muxer";
                            str8 = str11;
                            str = str12;
                            str10 = str13;
                            str6 = str14;
                            str2 = str15;
                            if (UnLog.D) {
                            }
                            if (mediaExtractor2 != null) {
                            }
                            if (mediaExtractor != null) {
                            }
                            if (mediaCodec != null) {
                            }
                            if (outputSurfaceWithFilter != null) {
                            }
                            if (mediaCodec3 != null) {
                            }
                            if (mediaCodec2 != null) {
                            }
                            if (mediaCodec4 != null) {
                            }
                            if (mediaMuxer != null) {
                            }
                            if (inputSurface != null) {
                            }
                            throw th;
                        }
                    } catch (Throwable th12) {
                        th = th12;
                        th = th;
                        str2 = "error while releasing videoEncoder";
                        str9 = "error while releasing inputSurface";
                        str7 = "error while releasing muxer";
                        str3 = "error while releasing audioEncoder";
                        str4 = "error while releasing audioDecoder";
                        mediaExtractor = null;
                        outputSurfaceWithFilter = null;
                        mediaCodec = null;
                        mediaCodec2 = null;
                        mediaCodec4 = null;
                        mediaMuxer = null;
                        str5 = "releasing extractor, decoder, encoder, and muxer";
                        str8 = str11;
                        str = str12;
                        str10 = str13;
                        str6 = str14;
                        if (UnLog.D) {
                        }
                        if (mediaExtractor2 != null) {
                        }
                        if (mediaExtractor != null) {
                        }
                        if (mediaCodec != null) {
                        }
                        if (outputSurfaceWithFilter != null) {
                        }
                        if (mediaCodec3 != null) {
                        }
                        if (mediaCodec2 != null) {
                        }
                        if (mediaCodec4 != null) {
                        }
                        if (mediaMuxer != null) {
                        }
                        if (inputSurface != null) {
                        }
                        throw th;
                    }
                } catch (Throwable th13) {
                    str = "error while releasing audioExtractor";
                    str2 = "error while releasing videoEncoder";
                    str7 = "error while releasing muxer";
                    str3 = "error while releasing audioEncoder";
                    str4 = "error while releasing audioDecoder";
                    mediaExtractor = null;
                    outputSurfaceWithFilter = null;
                    mediaCodec2 = null;
                    mediaExtractor2 = null;
                    mediaCodec3 = null;
                    mediaCodec4 = null;
                    mediaMuxer = null;
                    inputSurface = null;
                    str5 = "releasing extractor, decoder, encoder, and muxer";
                    str8 = "error while releasing videoExtractor";
                    str6 = "error while releasing outputSurface";
                    str9 = "error while releasing inputSurface";
                    th = th13;
                    str10 = "error while releasing videoDecoder";
                    mediaCodec = null;
                }
            } else {
                str11 = "error while releasing videoExtractor";
                str12 = "error while releasing audioExtractor";
                str13 = "error while releasing videoDecoder";
                str14 = "error while releasing outputSurface";
                str15 = "error while releasing videoEncoder";
                createVideoDecoder = null;
                mediaCodec3 = null;
                mediaExtractor3 = null;
                outputSurfaceWithFilter2 = null;
                inputSurface = null;
            }
            try {
                if (this.mCopyAudio) {
                    try {
                        mediaExtractor = createExtractor();
                    } catch (Throwable th14) {
                        th = th14;
                        str9 = "error while releasing inputSurface";
                        str7 = "error while releasing muxer";
                        str3 = "error while releasing audioEncoder";
                        str4 = "error while releasing audioDecoder";
                        mediaExtractor = null;
                        mediaCodec2 = null;
                    }
                    try {
                        int andSelectAudioTrackIndex = getAndSelectAudioTrackIndex(mediaExtractor);
                        if (andSelectAudioTrackIndex == -1) {
                            this.mCopyAudio = false;
                            mediaExtractor4 = mediaExtractor;
                            mediaCodec2 = null;
                            mediaCodec4 = null;
                        } else {
                            MediaFormat trackFormat2 = mediaExtractor.getTrackFormat(andSelectAudioTrackIndex);
                            MediaFormat createAudioFormat = MediaFormat.createAudioFormat(OUTPUT_AUDIO_MIME_TYPE, trackFormat2.getInteger("sample-rate"), trackFormat2.getInteger("channel-count"));
                            createAudioFormat.setInteger(IjkMediaMeta.IJKM_KEY_BITRATE, OUTPUT_AUDIO_BIT_RATE);
                            createAudioFormat.setInteger("aac-profile", 2);
                            MediaCodec createAudioEncoder = createAudioEncoder(selectCodec2, createAudioFormat);
                            try {
                                mediaCodec2 = createAudioDecoder(trackFormat2);
                                mediaExtractor4 = mediaExtractor;
                                mediaCodec4 = createAudioEncoder;
                            } catch (Throwable th15) {
                                th = th15;
                                mediaCodec4 = createAudioEncoder;
                                str9 = "error while releasing inputSurface";
                                str7 = "error while releasing muxer";
                                str3 = "error while releasing audioEncoder";
                                str4 = "error while releasing audioDecoder";
                                mediaCodec2 = null;
                                mediaMuxer = null;
                                str5 = "releasing extractor, decoder, encoder, and muxer";
                                str8 = str11;
                                mediaCodec = createVideoDecoder;
                                str = str12;
                                str10 = str13;
                                str6 = str14;
                                mediaExtractor2 = mediaExtractor3;
                                outputSurfaceWithFilter = outputSurfaceWithFilter2;
                                str2 = str15;
                                if (UnLog.D) {
                                }
                                if (mediaExtractor2 != null) {
                                }
                                if (mediaExtractor != null) {
                                }
                                if (mediaCodec != null) {
                                }
                                if (outputSurfaceWithFilter != null) {
                                }
                                if (mediaCodec3 != null) {
                                }
                                if (mediaCodec2 != null) {
                                }
                                if (mediaCodec4 != null) {
                                }
                                if (mediaMuxer != null) {
                                }
                                if (inputSurface != null) {
                                }
                                throw th;
                            }
                        }
                    } catch (Throwable th16) {
                        th = th16;
                        str9 = "error while releasing inputSurface";
                        str7 = "error while releasing muxer";
                        str3 = "error while releasing audioEncoder";
                        str4 = "error while releasing audioDecoder";
                        mediaCodec2 = null;
                        mediaCodec4 = mediaCodec2;
                        mediaMuxer = mediaCodec4;
                        str5 = "releasing extractor, decoder, encoder, and muxer";
                        str8 = str11;
                        mediaCodec = createVideoDecoder;
                        str = str12;
                        str10 = str13;
                        str6 = str14;
                        mediaExtractor2 = mediaExtractor3;
                        outputSurfaceWithFilter = outputSurfaceWithFilter2;
                        str2 = str15;
                        if (UnLog.D) {
                        }
                        if (mediaExtractor2 != null) {
                        }
                        if (mediaExtractor != null) {
                        }
                        if (mediaCodec != null) {
                        }
                        if (outputSurfaceWithFilter != null) {
                        }
                        if (mediaCodec3 != null) {
                        }
                        if (mediaCodec2 != null) {
                        }
                        if (mediaCodec4 != null) {
                        }
                        if (mediaMuxer != null) {
                        }
                        if (inputSurface != null) {
                        }
                        throw th;
                    }
                } else {
                    mediaCodec2 = null;
                    mediaExtractor4 = null;
                    mediaCodec4 = null;
                }
                try {
                    mediaMuxer = createMuxer();
                    str = str12;
                    str10 = str13;
                    str6 = str14;
                    str8 = str11;
                    str16 = str15;
                    str5 = "releasing extractor, decoder, encoder, and muxer";
                } catch (Throwable th17) {
                    str9 = "error while releasing inputSurface";
                    str7 = "error while releasing muxer";
                    str3 = "error while releasing audioEncoder";
                    str4 = "error while releasing audioDecoder";
                    str5 = "releasing extractor, decoder, encoder, and muxer";
                    str8 = str11;
                    str = str12;
                    str10 = str13;
                    str6 = str14;
                    str2 = str15;
                    th = th17;
                    mediaMuxer = null;
                }
                try {
                    doExtractDecodeEditEncodeMux(mediaExtractor3, mediaExtractor4, createVideoDecoder, mediaCodec3, mediaCodec2, mediaCodec4, mediaMuxer, inputSurface, outputSurfaceWithFilter2);
                    if (UnLog.D) {
                        UnLog.d(TAG, str5);
                    }
                    if (mediaExtractor3 != null) {
                        try {
                            mediaExtractor3.release();
                        } catch (Exception e12) {
                            UnLog.e(TAG, str8, e12);
                            exc = e12;
                        }
                    }
                    if (mediaExtractor4 != null) {
                        try {
                            mediaExtractor4.release();
                        } catch (Exception e13) {
                            UnLog.e(TAG, str, e13);
                            if (exc == null) {
                                exc = e13;
                            }
                        }
                    }
                    if (createVideoDecoder != null) {
                        try {
                            createVideoDecoder.stop();
                            createVideoDecoder.release();
                        } catch (Exception e14) {
                            UnLog.e(TAG, str10, e14);
                            if (exc == null) {
                                exc = e14;
                            }
                        }
                    }
                    if (outputSurfaceWithFilter2 != null) {
                        try {
                            outputSurfaceWithFilter2.release();
                        } catch (Exception e15) {
                            UnLog.e(TAG, str6, e15);
                            if (exc == null) {
                                exc = e15;
                            }
                        }
                    }
                    if (mediaCodec3 != null) {
                        try {
                            mediaCodec3.stop();
                            mediaCodec3.release();
                        } catch (Exception e16) {
                            UnLog.e(TAG, str16, e16);
                            if (exc == null) {
                                exc = e16;
                            }
                        }
                    }
                    if (mediaCodec2 != null) {
                        try {
                            mediaCodec2.stop();
                            mediaCodec2.release();
                        } catch (Exception e17) {
                            UnLog.e(TAG, "error while releasing audioDecoder", e17);
                            if (exc == null) {
                                exc = e17;
                            }
                        }
                    }
                    if (mediaCodec4 != null) {
                        try {
                            mediaCodec4.stop();
                            mediaCodec4.release();
                        } catch (Exception e18) {
                            UnLog.e(TAG, "error while releasing audioEncoder", e18);
                            if (exc == null) {
                                exc = e18;
                            }
                        }
                    }
                    if (mediaMuxer != null) {
                        try {
                            mediaMuxer.stop();
                            mediaMuxer.release();
                        } catch (Exception e19) {
                            UnLog.e(TAG, "error while releasing muxer", e19);
                            if (exc == null) {
                                exc = e19;
                            }
                        }
                    }
                    if (inputSurface != null) {
                        try {
                            inputSurface.release();
                        } catch (Exception e20) {
                            UnLog.e(TAG, "error while releasing inputSurface", e20);
                            if (exc == null) {
                                exc = e20;
                            }
                        }
                    }
                    if (exc != null) {
                        throw exc;
                    }
                } catch (Throwable th18) {
                    str9 = "error while releasing inputSurface";
                    str7 = "error while releasing muxer";
                    str3 = "error while releasing audioEncoder";
                    str4 = "error while releasing audioDecoder";
                    str2 = str16;
                    th = th18;
                    mediaCodec = createVideoDecoder;
                    mediaExtractor2 = mediaExtractor3;
                    outputSurfaceWithFilter = outputSurfaceWithFilter2;
                    mediaExtractor = mediaExtractor4;
                    if (UnLog.D) {
                    }
                    if (mediaExtractor2 != null) {
                    }
                    if (mediaExtractor != null) {
                    }
                    if (mediaCodec != null) {
                    }
                    if (outputSurfaceWithFilter != null) {
                    }
                    if (mediaCodec3 != null) {
                    }
                    if (mediaCodec2 != null) {
                    }
                    if (mediaCodec4 != null) {
                    }
                    if (mediaMuxer != null) {
                    }
                    if (inputSurface != null) {
                    }
                    throw th;
                }
            } catch (Throwable th19) {
                str9 = "error while releasing inputSurface";
                str7 = "error while releasing muxer";
                str3 = "error while releasing audioEncoder";
                str4 = "error while releasing audioDecoder";
                str5 = "releasing extractor, decoder, encoder, and muxer";
                str8 = str11;
                str = str12;
                str10 = str13;
                str6 = str14;
                str2 = str15;
                th = th19;
                mediaExtractor = null;
                mediaCodec2 = null;
                mediaCodec4 = null;
                mediaMuxer = null;
                mediaCodec = createVideoDecoder;
                mediaExtractor2 = mediaExtractor3;
                outputSurfaceWithFilter = outputSurfaceWithFilter2;
            }
        } catch (Throwable th20) {
            str = "error while releasing audioExtractor";
            str2 = "error while releasing videoEncoder";
            str3 = "error while releasing audioEncoder";
            str4 = "error while releasing audioDecoder";
            str5 = "releasing extractor, decoder, encoder, and muxer";
            str6 = "error while releasing outputSurface";
            str7 = "error while releasing muxer";
            str8 = "error while releasing videoExtractor";
            str9 = "error while releasing inputSurface";
            str10 = "error while releasing videoDecoder";
            th = th20;
            mediaExtractor = null;
            outputSurfaceWithFilter = null;
            mediaCodec = null;
            mediaCodec2 = null;
            mediaExtractor2 = null;
            mediaCodec3 = null;
            mediaCodec4 = null;
            mediaMuxer = null;
            inputSurface = null;
        }
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
