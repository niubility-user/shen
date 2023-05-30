package com.jingdong.manto.jsapi.refact.rec;

import android.content.Context;
import android.media.MediaRecorder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.PcmRecord;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.manto.R;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.n;
import java.io.File;
import java.io.IOException;

/* loaded from: classes15.dex */
public class MantoRecorderView extends FrameLayout implements MediaRecorder.OnErrorListener {
    private static final String VIDEO_DIR = "video";
    private int cameraId;
    private final CameraManager cameraManager;
    private boolean isRecord;
    private MediaRecorder mMediaRecorder;
    private File mRecordFile;
    private int mRecordMaxTime;
    private SurfaceHolder mSurfaceHolder;
    private final SurfaceView mSurfaceView;
    private int orientation;
    private OnRecordListener recordListener;

    /* loaded from: classes15.dex */
    public interface OnRecordListener {
        void onRecordFailed(Exception exc);

        void onRecordFinish();
    }

    public MantoRecorderView(@NonNull Context context) {
        this(context, null);
    }

    public MantoRecorderView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MantoRecorderView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isRecord = false;
        this.cameraId = 0;
        LayoutInflater.from(context).inflate(R.layout.manto_video_recorder_view, this);
        this.cameraManager = new CameraManager(context);
        this.mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
    }

    private void createRecordDir() {
        if (this.mRecordFile != null) {
            return;
        }
        File file = new File(n.b);
        File file2 = new File(file, "video");
        if (file2.exists() ? false : file2.mkdirs()) {
            file = file2;
        }
        try {
            this.mRecordFile = new File(file, System.currentTimeMillis() + PreDownLoadManager.VIDEO_SKU_SUFFIX);
            MantoLog.d("better", "recordPath:" + this.mRecordFile.getPath());
        } catch (Exception unused) {
        }
    }

    private void initRecord() {
        if (this.mMediaRecorder == null) {
            this.mMediaRecorder = new MediaRecorder();
        }
        this.mMediaRecorder.reset();
        if (this.cameraManager.getCamera() != null) {
            this.cameraManager.unLock();
            this.mMediaRecorder.setCamera(this.cameraManager.getCamera());
        }
        this.mMediaRecorder.setOnErrorListener(this);
        this.mMediaRecorder.setPreviewDisplay(this.mSurfaceHolder.getSurface());
        try {
            this.mMediaRecorder.setVideoSource(1);
            this.mMediaRecorder.setAudioSource(1);
            this.mMediaRecorder.setAudioEncodingBitRate(131072);
            this.mMediaRecorder.setAudioChannels(2);
            this.mMediaRecorder.setAudioSamplingRate(PcmRecord.DEFAULT_SAMPLE_RATE);
            try {
                this.mMediaRecorder.setOutputFormat(2);
            } catch (Exception unused) {
                this.mMediaRecorder.setOutputFormat(0);
            }
            this.mMediaRecorder.setAudioEncoder(3);
            this.mMediaRecorder.setVideoEncoder(2);
            this.mMediaRecorder.setVideoSize(this.cameraManager.getVideoWidth(), this.cameraManager.getVideoHeight());
            this.mMediaRecorder.setVideoEncodingBitRate(MantoVideoUtil.getVideoBitRate(this.cameraManager.getVideoWidth(), this.cameraManager.getVideoHeight()));
            this.mMediaRecorder.setMaxDuration(this.mRecordMaxTime + 500);
            int frameRate = MantoVideoUtil.getFrameRate();
            if (frameRate != -1) {
                this.mMediaRecorder.setVideoFrameRate(frameRate);
            }
            this.mMediaRecorder.setOrientationHint(MantoVideoUtil.getRotationDegree(this.orientation, this.cameraManager.getCameraID()));
            this.mMediaRecorder.setOutputFile(this.mRecordFile.getAbsolutePath());
            this.mMediaRecorder.prepare();
            try {
                this.mMediaRecorder.start();
                this.isRecord = true;
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e3) {
            throw e3;
        }
    }

    private void releaseRecord() {
        MediaRecorder mediaRecorder = this.mMediaRecorder;
        if (mediaRecorder != null) {
            mediaRecorder.setOnErrorListener(null);
            try {
                this.mMediaRecorder.release();
            } catch (Exception unused) {
            }
        }
        this.mMediaRecorder = null;
    }

    public void cancelRecord() {
        stopRecord();
        File file = this.mRecordFile;
        if (file == null || !file.exists()) {
            return;
        }
        MantoLog.d("better", "delete file: " + this.mRecordFile.delete());
    }

    public void changeCameraFacing() {
        this.cameraManager.changeCameraFacing();
        this.cameraId = this.cameraManager.getCameraID();
    }

    public int getCameraPosition() {
        return this.cameraManager.getCameraID();
    }

    public File getRecordFile() {
        return this.mRecordFile;
    }

    public void initSurface(int i2) {
        this.cameraId = i2;
        SurfaceHolder holder = this.mSurfaceView.getHolder();
        this.mSurfaceHolder = holder;
        holder.addCallback(new SurfaceHolder.Callback() { // from class: com.jingdong.manto.jsapi.refact.rec.MantoRecorderView.1
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i3, int i4, int i5) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    MantoRecorderView.this.cameraManager.initCamera(surfaceHolder, MantoRecorderView.this.cameraId);
                    MantoLog.d("better", "SurfaceHolder created.");
                    String str = "cameraId:" + MantoRecorderView.this.cameraId;
                } catch (IOException unused) {
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                MantoLog.d("better", "SurfaceHolder destory");
                MantoRecorderView.this.cameraManager.freeCameraResource();
            }
        });
    }

    @Override // android.media.MediaRecorder.OnErrorListener
    public void onError(MediaRecorder mediaRecorder, int i2, int i3) {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.reset();
            } catch (IllegalStateException | Exception unused) {
                return;
            }
        }
        OnRecordListener onRecordListener = this.recordListener;
        if (onRecordListener != null) {
            onRecordListener.onRecordFailed(new Exception("error"));
        }
    }

    public void record(int i2, OnRecordListener onRecordListener) {
        this.orientation = i2;
        this.recordListener = onRecordListener;
        createRecordDir();
        try {
            initRecord();
        } catch (Exception e2) {
            if (this.recordListener != null) {
                this.recordListener.onRecordFailed(e2);
            }
        }
    }

    public void release() {
        releaseRecord();
        this.isRecord = false;
        this.cameraManager.freeCameraResource();
    }

    public void setRecordFile(File file) {
        this.mRecordFile = file;
    }

    public void setRecordMaxTime(int i2) {
        this.mRecordMaxTime = i2;
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        this.mSurfaceView.setVisibility(i2);
        super.setVisibility(i2);
    }

    public void startPreview() {
        this.cameraManager.startPreview();
    }

    public void stop() {
        stopRecord();
        stopPreview();
        OnRecordListener onRecordListener = this.recordListener;
        if (onRecordListener != null) {
            onRecordListener.onRecordFinish();
        }
    }

    public void stopPreview() {
        this.cameraManager.stopPreview();
    }

    public void stopRecord() {
        MediaRecorder mediaRecorder = this.mMediaRecorder;
        if (mediaRecorder == null || !this.isRecord) {
            return;
        }
        this.isRecord = false;
        mediaRecorder.setOnErrorListener(null);
        this.mMediaRecorder.setPreviewDisplay(null);
        try {
            this.mMediaRecorder.stop();
        } catch (Exception e2) {
            MantoLog.e("better", "stopRecord: " + e2);
        }
    }

    public boolean toggleFlash(int i2) {
        return this.cameraManager.openOrCloseLight(i2);
    }

    public void turnOffLight() {
        this.cameraManager.turnOff();
    }
}
