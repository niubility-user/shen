package com.jingdong.jdreact.plugin.audio;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Handler;
import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class ReactAudioRecordUtil {
    private static final int STATE_ERROR = 2;
    private static final int STATE_IDLE = 0;
    private static final int STATE_RECORDING = 1;
    public static final String TAG = "ReactAudioRecordUtil";
    private static ReactAudioRecordUtil instance;
    private String filePath;
    private AudioUpdateListener mAudioListener;
    private MediaRecorder mRecorder;
    private RecorderConfig recorderConfig;
    private long startTime;
    private Handler mHandler = new Handler();
    private int curState = 0;
    private final String AUDIO_DIR = "audioRecord";
    private Runnable mUpdateAudioStateTimer = new Runnable() { // from class: com.jingdong.jdreact.plugin.audio.ReactAudioRecordUtil.1
        @Override // java.lang.Runnable
        public void run() {
            if (ReactAudioRecordUtil.this.mRecorder != null) {
                try {
                    double maxAmplitude = ReactAudioRecordUtil.this.mRecorder.getMaxAmplitude();
                    double d = ReactAudioRecordUtil.this.recorderConfig.dbBase;
                    Double.isNaN(maxAmplitude);
                    Double.isNaN(d);
                    double d2 = maxAmplitude / d;
                    if (d2 > 1.0d) {
                        double log10 = Math.log10(d2) * 20.0d;
                        if (ReactAudioRecordUtil.this.mAudioListener != null) {
                            ReactAudioRecordUtil.this.mAudioListener.onUpdate(log10, System.currentTimeMillis() - ReactAudioRecordUtil.this.startTime);
                        }
                    }
                } catch (Exception e2) {
                    JLog.e(ReactAudioRecordUtil.TAG, e2.toString());
                }
                ReactAudioRecordUtil.this.mHandler.postDelayed(ReactAudioRecordUtil.this.mUpdateAudioStateTimer, ReactAudioRecordUtil.this.recorderConfig.interval);
            }
        }
    };

    /* loaded from: classes13.dex */
    public interface AudioUpdateListener {
        public static final int EXCEPTION_INIT_RECORDER = 0;
        public static final int EXCEPTION_NO_PERMISSION = 3;
        public static final int EXCEPTION_START_RECORD = 1;
        public static final int EXCEPTION_STOP_RECORD = 2;

        void onException(int i2, String str);

        void onStop(String str);

        void onUpdate(double d, long j2);
    }

    /* loaded from: classes13.dex */
    public static class RecorderConfig {
        public int encodeFormat = 1;
        public int outputFormat = 0;
        public String fileSuffix = ".mp3";
        public int interval = 100;
        public int sampleRate = -1;
        public int bitRate = -1;
        public int maxDuration = 10000;
        public int dbBase = 1;
    }

    private ReactAudioRecordUtil() {
    }

    private void checkPermission(Activity activity, HashMap hashMap) {
        if (activity == null) {
            return;
        }
        hashMap.put("permissions", Arrays.asList("android.permission.RECORD_AUDIO"));
        JDReactHelper.newInstance().requestPermission(activity, hashMap, new JDCallback() { // from class: com.jingdong.jdreact.plugin.audio.ReactAudioRecordUtil.2
            @Override // com.jingdong.common.jdreactFramework.JDCallback
            public void invoke(Object... objArr) {
                if (ReactAudioRecordUtil.this.initRecorder()) {
                    ReactAudioRecordUtil.this.start();
                } else if (ReactAudioRecordUtil.this.mAudioListener != null) {
                    ReactAudioRecordUtil.this.mAudioListener.onException(3, "no permission");
                }
            }
        }, new JDCallback() { // from class: com.jingdong.jdreact.plugin.audio.ReactAudioRecordUtil.3
            @Override // com.jingdong.common.jdreactFramework.JDCallback
            public void invoke(Object... objArr) {
                if (ReactAudioRecordUtil.this.mAudioListener != null) {
                    ReactAudioRecordUtil.this.mAudioListener.onException(3, "no permission");
                }
            }
        });
    }

    private void dealException(int i2, String str) {
        JLog.d(TAG, "dealException:" + i2 + "   " + str);
        AudioUpdateListener audioUpdateListener = this.mAudioListener;
        if (audioUpdateListener != null) {
            audioUpdateListener.onException(i2, str);
        }
        this.mHandler.removeCallbacks(this.mUpdateAudioStateTimer);
        this.mRecorder = null;
        this.mAudioListener = null;
        this.startTime = 0L;
        this.filePath = "";
        this.curState = 2;
    }

    public static File getExternalCacheDir() {
        File externalCacheDir = JDReactHelper.newInstance().getApplication().getExternalCacheDir();
        if (externalCacheDir != null) {
            if (!externalCacheDir.exists() || !externalCacheDir.isDirectory()) {
                externalCacheDir.mkdirs();
            }
        } else {
            externalCacheDir = JDReactHelper.newInstance().getApplication().getFilesDir();
            if (externalCacheDir != null && (!externalCacheDir.exists() || !externalCacheDir.isDirectory())) {
                externalCacheDir.mkdirs();
            }
        }
        return externalCacheDir;
    }

    public static ReactAudioRecordUtil getInstance() {
        if (instance == null) {
            instance = new ReactAudioRecordUtil();
        }
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initRecorder() {
        this.mRecorder = new MediaRecorder();
        if (this.recorderConfig == null) {
            this.recorderConfig = new RecorderConfig();
        }
        try {
            this.mRecorder.setAudioSource(1);
            this.mRecorder.setOutputFormat(this.recorderConfig.outputFormat);
            this.mRecorder.setAudioEncoder(this.recorderConfig.encodeFormat);
            int i2 = this.recorderConfig.sampleRate;
            if (i2 != -1) {
                this.mRecorder.setAudioSamplingRate(i2);
            }
            int i3 = this.recorderConfig.bitRate;
            if (i3 != -1) {
                this.mRecorder.setAudioEncodingBitRate(i3);
            }
            File externalCacheDir = getExternalCacheDir();
            if (externalCacheDir == null) {
                return false;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(externalCacheDir);
            String str = File.separator;
            sb.append(str);
            sb.append("audioRecord");
            sb.append(str);
            File file = new File(sb.toString());
            if (!file.exists()) {
                file.mkdirs();
            }
            String str2 = file + str + System.currentTimeMillis() + this.recorderConfig.fileSuffix;
            this.filePath = str2;
            this.mRecorder.setOutputFile(str2);
            this.mRecorder.setMaxDuration(this.recorderConfig.maxDuration);
            this.mRecorder.prepare();
            JLog.d(TAG, "initRecorder success");
            return true;
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            dealException(0, e2.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void start() {
        MediaRecorder mediaRecorder = this.mRecorder;
        if (mediaRecorder != null) {
            try {
                mediaRecorder.start();
                this.curState = 1;
                this.startTime = System.currentTimeMillis();
                this.mHandler.post(this.mUpdateAudioStateTimer);
                JLog.d(TAG, "recorder start");
            } catch (Exception e2) {
                JLog.e(TAG, e2.toString());
                dealException(1, e2.getMessage());
            }
        }
    }

    public void delAudioFile(String str) {
        JLog.d(TAG, "delAudioFile:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public void free() {
        JLog.d(TAG, "free  MediaRecorder");
        try {
            MediaRecorder mediaRecorder = this.mRecorder;
            if (mediaRecorder != null) {
                mediaRecorder.release();
            }
            this.mHandler.removeCallbacks(this.mUpdateAudioStateTimer);
            this.mRecorder = null;
            this.mAudioListener = null;
            this.startTime = 0L;
            this.filePath = "";
            this.curState = 0;
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
        }
    }

    public boolean isRecording() {
        return 1 == this.curState;
    }

    public void setRecorderConfig(RecorderConfig recorderConfig) {
        if (recorderConfig != null) {
            this.recorderConfig = recorderConfig;
        }
    }

    public void startRecord(HashMap hashMap, AudioUpdateListener audioUpdateListener) {
        JLog.d(TAG, "startRecord");
        this.mAudioListener = audioUpdateListener;
        if (JDReactHelper.newInstance().getCurrentMyActivity() instanceof Activity) {
            checkPermission(JDReactHelper.newInstance().getCurrentMyActivity(), hashMap);
        }
    }

    public void stopRecord() {
        JLog.d(TAG, "stopRecord");
        if (this.mRecorder != null) {
            if (isRecording()) {
                try {
                    this.mRecorder.stop();
                    AudioUpdateListener audioUpdateListener = this.mAudioListener;
                    if (audioUpdateListener != null) {
                        audioUpdateListener.onStop(this.filePath);
                    }
                } catch (Exception e2) {
                    JLog.e(TAG, e2.toString());
                    dealException(2, e2.getMessage());
                }
            }
            try {
                this.mRecorder.reset();
                this.mRecorder.release();
            } catch (Exception e3) {
                JLog.e(TAG, e3.toString());
            }
        }
        this.curState = 0;
        this.mRecorder = null;
        this.mAudioListener = null;
        this.startTime = 0L;
        this.filePath = "";
        this.mHandler.removeCallbacks(this.mUpdateAudioStateTimer);
    }
}
