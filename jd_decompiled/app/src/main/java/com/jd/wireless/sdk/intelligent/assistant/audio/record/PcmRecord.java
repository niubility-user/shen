package com.jd.wireless.sdk.intelligent.assistant.audio.record;

import android.media.AudioRecord;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;

/* loaded from: classes18.dex */
public class PcmRecord implements Runnable, Constant {
    public static final int DEFAULT_SAMPLE_RATE = 44100;
    private static final String TAG = PcmRecord.class.getSimpleName();
    private AudioRecord audioRecord;
    private IAudioRecordCallBack audioRecordCallBack;
    private byte[] buffer;
    private int bufferReadLength;
    private boolean isRecord = false;
    private int maxDuration;
    private int minimumDuration;
    private String outputPath;
    private FileOutputStream outputStream;
    private File pcmFile;
    private int recBufSize;
    private int sampleRate;
    private long startTimestamp;
    private long stopTimestamp;

    public PcmRecord(int i2, byte b, String str, byte b2, IAudioRecordCallBack iAudioRecordCallBack) {
        this.maxDuration = 60000;
        this.minimumDuration = 0;
        this.sampleRate = i2;
        if (b > 0 && b < 60) {
            this.maxDuration = b * 1000;
        }
        if (iAudioRecordCallBack != null) {
            this.audioRecordCallBack = iAudioRecordCallBack;
            this.outputPath = str;
            File file = new File(this.outputPath);
            this.pcmFile = file;
            try {
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
                this.audioRecordCallBack.recordError((byte) 1);
            }
            if (b2 > 0 && b2 < 10) {
                this.minimumDuration = b2 * 1000;
            }
            int minBufferSize = AudioRecord.getMinBufferSize(this.sampleRate, 16, 2);
            this.recBufSize = minBufferSize;
            if (minBufferSize <= 0) {
                this.sampleRate = DEFAULT_SAMPLE_RATE;
                this.recBufSize = AudioRecord.getMinBufferSize(DEFAULT_SAMPLE_RATE, 16, 2);
            }
            this.buffer = new byte[this.recBufSize];
            return;
        }
        throw new IllegalArgumentException();
    }

    private void calculateVolume(byte[] bArr) {
        if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            int i2 = 0;
            for (int i3 = 0; i3 < bArr.length - 1; i3 = i3 + 1 + 1) {
                i2 += Math.abs(((bArr[i3] & 255) << 8) | bArr[i3]);
            }
            this.audioRecordCallBack.changVolum((byte) ((i2 / (bArr.length / 2)) % 30));
        }
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x008d, code lost:
        if (r0 != null) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a2, code lost:
        if (r0 == null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00a4, code lost:
        r0.stop();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c1, code lost:
        if (r0 == null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00d8, code lost:
        if (r0 == null) goto L62;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.wireless.sdk.intelligent.assistant.audio.record.PcmRecord.run():void");
    }

    public void stopRecord() {
        this.stopTimestamp = System.currentTimeMillis();
        this.isRecord = false;
    }
}
