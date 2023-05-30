package com.jd.wireless.sdk.intelligent.assistant.audio.record;

import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;
import java.io.IOException;

/* loaded from: classes18.dex */
public class AmrRecord implements MediaRecorder.OnInfoListener, Constant {
    private static final int SPACE = 300;
    private IAudioRecordCallBack audioRecordCallBack;
    private MediaRecorder mMediaRecorder;
    private int maxDuration;
    private int minimumDuration;
    private String outputPath;
    private long startTimestamp;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean isRecord = false;
    private Runnable updateVolumRunnable = new Runnable() { // from class: com.jd.wireless.sdk.intelligent.assistant.audio.record.AmrRecord.1
        @Override // java.lang.Runnable
        public void run() {
            AmrRecord.this.updateVolum();
        }
    };

    public AmrRecord(IAudioRecordCallBack iAudioRecordCallBack, String str, byte b, byte b2) {
        this.maxDuration = 60000;
        this.minimumDuration = 0;
        this.audioRecordCallBack = iAudioRecordCallBack;
        this.outputPath = str;
        if (b > 0 && b < 60) {
            this.maxDuration = b * 1000;
        }
        if (b2 > 0 && b2 < 10) {
            this.minimumDuration = b2 * 1000;
        }
        this.mMediaRecorder = new MediaRecorder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVolum() {
        this.audioRecordCallBack.changVolum((byte) (this.mMediaRecorder.getMaxAmplitude() % 31));
        if (this.isRecord) {
            this.mHandler.postDelayed(this.updateVolumRunnable, 300L);
        }
    }

    @Override // android.media.MediaRecorder.OnInfoListener
    public void onInfo(MediaRecorder mediaRecorder, int i2, int i3) {
        if (800 == i2) {
            this.audioRecordCallBack.recordStop();
            this.isRecord = false;
        }
    }

    public void startRecord() {
        if (this.isRecord) {
            return;
        }
        this.isRecord = true;
        this.mMediaRecorder.setAudioSource(1);
        this.mMediaRecorder.setOutputFormat(4);
        this.mMediaRecorder.setAudioEncoder(2);
        this.mMediaRecorder.setOutputFile(this.outputPath);
        this.mMediaRecorder.setOnInfoListener(this);
        this.mMediaRecorder.setMaxDuration(this.maxDuration);
        try {
            this.mMediaRecorder.prepare();
            this.mMediaRecorder.start();
            this.startTimestamp = System.currentTimeMillis();
            updateVolum();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        }
    }

    public void stopRecord() {
        MediaRecorder mediaRecorder = this.mMediaRecorder;
        if (mediaRecorder != null && this.isRecord) {
            this.isRecord = false;
            try {
                mediaRecorder.stop();
            } catch (RuntimeException unused) {
                IAudioRecordCallBack iAudioRecordCallBack = this.audioRecordCallBack;
                if (iAudioRecordCallBack != null) {
                    iAudioRecordCallBack.recordError((byte) 6);
                }
            }
            this.mMediaRecorder.reset();
        }
        if (System.currentTimeMillis() - this.startTimestamp <= this.minimumDuration) {
            this.audioRecordCallBack.recordError((byte) 3);
        } else {
            this.audioRecordCallBack.recordStop();
        }
    }
}
