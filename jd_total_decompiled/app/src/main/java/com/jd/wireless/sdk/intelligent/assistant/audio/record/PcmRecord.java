package com.jd.wireless.sdk.intelligent.assistant.audio.record;

import android.media.AudioRecord;
import java.io.File;
import java.io.FileNotFoundException;
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
    */
    public void run() {
        AudioRecord audioRecord;
        try {
            try {
                try {
                    this.audioRecord = new AudioRecord(1, this.sampleRate, 16, 2, this.recBufSize);
                    this.outputStream = new FileOutputStream(this.pcmFile);
                    if (this.audioRecord.getState() == 0) {
                        this.audioRecordCallBack.recordError((byte) 4);
                        FileOutputStream fileOutputStream = this.outputStream;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused) {
                                this.audioRecordCallBack.recordError((byte) 2);
                            }
                        }
                        AudioRecord audioRecord2 = this.audioRecord;
                        if (audioRecord2 != null) {
                            try {
                                audioRecord2.stop();
                            } catch (Exception unused2) {
                            }
                            this.audioRecord.release();
                            return;
                        }
                        return;
                    }
                    this.startTimestamp = System.currentTimeMillis();
                    this.audioRecord.startRecording();
                    this.isRecord = true;
                    while (this.isRecord) {
                        int read = this.audioRecord.read(this.buffer, 0, this.recBufSize);
                        this.bufferReadLength = read;
                        if (read > 0) {
                            this.outputStream.write(this.buffer, 0, read);
                            calculateVolume(this.buffer);
                            if (System.currentTimeMillis() - this.startTimestamp >= this.maxDuration) {
                                break;
                            }
                        }
                    }
                    FileOutputStream fileOutputStream2 = this.outputStream;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException unused3) {
                            this.audioRecordCallBack.recordError((byte) 2);
                        }
                    }
                    audioRecord = this.audioRecord;
                } catch (FileNotFoundException unused4) {
                    this.audioRecordCallBack.recordError((byte) 1);
                    FileOutputStream fileOutputStream3 = this.outputStream;
                    if (fileOutputStream3 != null) {
                        try {
                            fileOutputStream3.close();
                        } catch (IOException unused5) {
                            this.audioRecordCallBack.recordError((byte) 2);
                        }
                    }
                    audioRecord = this.audioRecord;
                } catch (IllegalArgumentException unused6) {
                    FileOutputStream fileOutputStream4 = this.outputStream;
                    if (fileOutputStream4 != null) {
                        try {
                            fileOutputStream4.close();
                        } catch (IOException unused7) {
                            this.audioRecordCallBack.recordError((byte) 2);
                        }
                    }
                    audioRecord = this.audioRecord;
                }
            } catch (IOException unused8) {
                this.audioRecordCallBack.recordError((byte) 2);
                FileOutputStream fileOutputStream5 = this.outputStream;
                if (fileOutputStream5 != null) {
                    try {
                        fileOutputStream5.close();
                    } catch (IOException unused9) {
                        this.audioRecordCallBack.recordError((byte) 2);
                    }
                }
                audioRecord = this.audioRecord;
            }
            this.audioRecord.release();
            if (this.stopTimestamp - this.startTimestamp <= this.minimumDuration) {
                this.audioRecordCallBack.recordError((byte) 3);
            } else {
                this.audioRecordCallBack.recordStop();
            }
        } catch (Throwable th) {
            FileOutputStream fileOutputStream6 = this.outputStream;
            if (fileOutputStream6 != null) {
                try {
                    fileOutputStream6.close();
                } catch (IOException unused10) {
                    this.audioRecordCallBack.recordError((byte) 2);
                }
            }
            AudioRecord audioRecord3 = this.audioRecord;
            if (audioRecord3 != null) {
                try {
                    audioRecord3.stop();
                } catch (Exception unused11) {
                }
                this.audioRecord.release();
            }
            throw th;
        }
    }

    public void stopRecord() {
        this.stopTimestamp = System.currentTimeMillis();
        this.isRecord = false;
    }
}
