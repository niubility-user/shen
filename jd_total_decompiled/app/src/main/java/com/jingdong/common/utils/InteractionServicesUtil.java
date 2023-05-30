package com.jingdong.common.utils;

import android.app.Activity;
import android.media.AudioRecord;
import android.os.Handler;
import android.os.Message;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.PcmRecord;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes6.dex */
public class InteractionServicesUtil {
    public static final int SOUND_SERVICE = 1001;
    private static final String TAG = "InteractionServicesUtil";
    private static InteractionServicesUtil mInstance;
    private static int[] mSampleRates = {8000, R2.drawable.security_key_lower_icon_dark, R2.style.label_04_style, PcmRecord.DEFAULT_SAMPLE_RATE};
    private int mSoundBufferSize;
    private SoundThread mSoundThread = null;
    private SoundListener mSoundListener = null;
    private double mSoundDb = 0.0d;
    public Handler mSoundHandle = new Handler() { // from class: com.jingdong.common.utils.InteractionServicesUtil.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            InteractionServicesUtil.this.mSoundDb = Double.parseDouble(message.obj.toString());
        }
    };

    /* loaded from: classes6.dex */
    public interface SoundListener {
        void onEnd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public class SoundThread extends Thread {
        private int mInterval;
        private Handler mhandle;
        private boolean isRun = false;
        private AudioRecord audioRecord = findAudioRecord();

        public SoundThread(Handler handler, int i2) {
            this.mhandle = handler;
            this.mInterval = i2;
        }

        public AudioRecord findAudioRecord() {
            int i2;
            short[] sArr;
            AudioRecord audioRecord;
            for (int i3 : InteractionServicesUtil.mSampleRates) {
                short[] sArr2 = {2, 3};
                for (int i4 = 0; i4 < 2; i4++) {
                    short s = sArr2[i4];
                    short[] sArr3 = {1, 16, 12};
                    int i5 = 0;
                    for (int i6 = 3; i5 < i6; i6 = 3) {
                        short s2 = sArr3[i5];
                        try {
                            InteractionServicesUtil.this.mSoundBufferSize = AudioRecord.getMinBufferSize(i3, s2, s);
                        } catch (Exception unused) {
                        }
                        if (InteractionServicesUtil.this.mSoundBufferSize != -2) {
                            i2 = i5;
                            sArr = sArr3;
                            try {
                                audioRecord = new AudioRecord(1, i3, s2, s, InteractionServicesUtil.this.mSoundBufferSize);
                            } catch (Exception unused2) {
                                continue;
                            }
                            if (audioRecord.getState() == 1) {
                                return audioRecord;
                            }
                            i5 = i2 + 1;
                            sArr3 = sArr;
                        }
                        i2 = i5;
                        sArr = sArr3;
                        i5 = i2 + 1;
                        sArr3 = sArr;
                    }
                }
            }
            return null;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            AudioRecord audioRecord = this.audioRecord;
            if (audioRecord == null) {
                if (OKLog.D) {
                    OKLog.d(InteractionServicesUtil.TAG, " AudioRecord is null! ");
                    return;
                }
                return;
            }
            audioRecord.startRecording();
            int i2 = InteractionServicesUtil.this.mSoundBufferSize;
            short[] sArr = new short[i2];
            while (this.isRun) {
                int read = this.audioRecord.read(sArr, 0, InteractionServicesUtil.this.mSoundBufferSize);
                long j2 = 0;
                for (int i3 = 0; i3 < i2; i3++) {
                    j2 += sArr[i3] * sArr[i3];
                }
                double d = j2;
                double d2 = read;
                Double.isNaN(d);
                Double.isNaN(d2);
                double d3 = d / d2;
                double log10 = Math.log10(d3) * 10.0d;
                if (d3 < 0.0d || log10 < 0.0d) {
                    log10 = 0.0d;
                }
                this.mhandle.sendMessage(this.mhandle.obtainMessage(1, Double.valueOf(log10)));
                try {
                    Thread.sleep(this.mInterval);
                } catch (InterruptedException unused) {
                }
            }
            this.audioRecord.stop();
            this.audioRecord.release();
            this.audioRecord = null;
        }

        public void setRun(boolean z) {
            this.isRun = z;
        }

        @Override // java.lang.Thread
        public void start() {
            this.isRun = true;
            super.start();
        }
    }

    public static InteractionServicesUtil getInstance() {
        if (mInstance == null) {
            mInstance = new InteractionServicesUtil();
        }
        return mInstance;
    }

    private double getSoundService() {
        return this.mSoundDb;
    }

    private void startSoundService() {
        startSoundService(150);
    }

    private void stopSoundService() {
        SoundThread soundThread = this.mSoundThread;
        if (soundThread != null) {
            soundThread.setRun(false);
            this.mSoundThread.interrupt();
            this.mSoundThread = null;
        }
    }

    public Object getData(int i2) {
        if (i2 == 1001) {
            return Double.valueOf(getSoundService());
        }
        return null;
    }

    public void setListener(SoundListener soundListener) {
        this.mSoundListener = soundListener;
    }

    public void startService(int i2) {
        if (i2 == 1001) {
            startSoundService();
        }
    }

    public void stopService(int i2) {
        if (i2 == 1001) {
            stopSoundService();
        }
    }

    private void startSoundService(final int i2) {
        if (this.mSoundThread != null) {
            stopSoundService();
        }
        if (i2 <= 0) {
            i2 = 150;
        }
        Activity activity = (Activity) BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (activity == null || !PermissionHelper.hasGrantedRecordAudio(activity, PermissionHelper.generateBundle("jdwebview", InteractionServicesUtil.class.getSimpleName(), "startSoundServiceUtil"), new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.utils.InteractionServicesUtil.1
            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onCanceled() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onDenied() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onGranted() {
                InteractionServicesUtil interactionServicesUtil = InteractionServicesUtil.this;
                InteractionServicesUtil interactionServicesUtil2 = InteractionServicesUtil.this;
                interactionServicesUtil.mSoundThread = new SoundThread(interactionServicesUtil2.mSoundHandle, i2);
                InteractionServicesUtil.this.mSoundThread.start();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onIgnored() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onOpenSetting() {
            }
        })) {
            return;
        }
        SoundThread soundThread = new SoundThread(this.mSoundHandle, i2);
        this.mSoundThread = soundThread;
        soundThread.start();
    }

    public void startService(int i2, int i3) {
        if (i2 == 1001) {
            startSoundService(i3);
        }
    }
}
