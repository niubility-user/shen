package com.jingdong.common.jdreactFramework.utils.video;

import android.content.Intent;
import android.graphics.Point;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.facebook.react.common.ReactConstants;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.PcmRecord;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.video.LoopVideoViewHolder;
import com.jingdong.common.jdreactFramework.utils.video.TapRecordView;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;

/* loaded from: classes5.dex */
public class VideoRecordActivity extends BaseActivity implements MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener {
    public static final String KEY_MAX_RECORD_TIME = "max_record_time";
    private static final int MAX_RECORD_TIME_DEFAULT = 10000;
    private static final int MIN_RECORD_TIME = 1000;
    public static final String RECORD_VIDEO_PATH = "video_path";
    private LoopVideoViewHolder loopVideoView = null;
    private MediaRecorder mMediaRecorder;
    private TapRecordView mTapRecordView;
    private File mVideoFile;
    private VideoRecordView mVideoRecordView;
    private FrameLayout recordParentContentLayout;
    private RecordProgressView recordProgressView;
    private View recordTitleLayout;
    private TextView recordTitleText;
    private File rootFileDir;
    private View titleBackView;
    private View titleChangeCameraView;
    private String videoParentPath;
    static final String TAG = VideoRecordActivity.class.getSimpleName();
    static int MAX_RECORD_TIME = 10000;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity$11  reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$jdreactFramework$utils$video$VideoRecordActivity$ErrorType;

        static {
            int[] iArr = new int[ErrorType.values().length];
            $SwitchMap$com$jingdong$common$jdreactFramework$utils$video$VideoRecordActivity$ErrorType = iArr;
            try {
                iArr[ErrorType.ERROR_CREATE_FILE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$jdreactFramework$utils$video$VideoRecordActivity$ErrorType[ErrorType.ERROR_TIME_TOO_SHORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$jdreactFramework$utils$video$VideoRecordActivity$ErrorType[ErrorType.ERROR_START_RECORD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$common$jdreactFramework$utils$video$VideoRecordActivity$ErrorType[ErrorType.ERROR_STOP_RECORD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$jingdong$common$jdreactFramework$utils$video$VideoRecordActivity$ErrorType[ErrorType.ERROR_RECORDER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public enum ErrorType {
        ERROR_CREATE_FILE,
        ERROR_START_RECORD,
        ERROR_STOP_RECORD,
        ERROR_RECORDER,
        ERROR_TIME_TOO_SHORT
    }

    private void changeCamera() {
        this.titleChangeCameraView.setClickable(false);
        this.mVideoRecordView.changeCamera();
        post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity.3
            @Override // java.lang.Runnable
            public void run() {
                VideoRecordActivity.this.titleChangeCameraView.setClickable(true);
            }
        }, 1000);
    }

    private void checkDiskSize() {
        try {
            long dataDiskFreeSize = FileUtils.getDataDiskFreeSize(true);
            if (OKLog.D) {
                OKLog.d(TAG, "diskSize:" + dataDiskFreeSize);
            }
            if (dataDiskFreeSize < 50) {
                final JDDialog createJdDialogWithStyle1 = JDDialogFactory.getInstance().createJdDialogWithStyle1(this, getString(R.string.jdreact_record_video_sd_full_tips), getString(R.string.jdreact_record_video_cancel));
                createJdDialogWithStyle1.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        createJdDialogWithStyle1.dismiss();
                        VideoRecordActivity.this.finish();
                    }
                });
                createJdDialogWithStyle1.show();
            } else if (dataDiskFreeSize < 100) {
                final JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(this, getString(R.string.jdreact_record_video_sd_low_title), getString(R.string.jdreact_record_video_sd_low_tips), getString(R.string.jdreact_record_video_cancel), getString(R.string.jdreact_record_video_next_step));
                createJdDialogWithStyle6.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        createJdDialogWithStyle6.dismiss();
                        VideoRecordActivity.this.finish();
                    }
                });
                createJdDialogWithStyle6.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity.8
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        createJdDialogWithStyle6.dismiss();
                        VideoRecordActivity.this.checkIsWifi();
                    }
                });
                createJdDialogWithStyle6.show();
            } else {
                checkIsWifi();
            }
        } catch (Throwable th) {
            JLog.e(TAG, th.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsWifi() {
        if ("wifi".equals(NetUtils.getNetworkType())) {
            return;
        }
        final JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(this, getString(R.string.jdreact_record_video_wifi_title), getString(R.string.jdreact_record_video_wifi_tips), getString(R.string.jdreact_record_video_cancel), getString(R.string.jdreact_record_video_next_step));
        createJdDialogWithStyle6.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                createJdDialogWithStyle6.dismiss();
                VideoRecordActivity.this.finish();
            }
        });
        createJdDialogWithStyle6.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                createJdDialogWithStyle6.dismiss();
            }
        });
        createJdDialogWithStyle6.show();
    }

    private void checkPermissions() {
        if (PermissionHelper.hasGrantedPermissions(this, PermissionHelper.generateBundle(ReactConstants.TAG, VideoRecordActivity.class.getSimpleName(), "checkPermission"), new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"}, true, new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity.1
            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onCanceled() {
                VideoRecordActivity.this.finish();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onDenied() {
                VideoRecordActivity.this.finish();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onGranted() {
                VideoRecordActivity.this.initView();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onIgnored() {
                VideoRecordActivity.this.finish();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onOpenSetting() {
                VideoRecordActivity.this.finish();
            }
        })) {
            initView();
        }
    }

    private void deleteVideoFile() {
        File file = this.mVideoFile;
        if (file == null || !file.exists()) {
            return;
        }
        this.mVideoFile.delete();
    }

    private void error(ErrorType errorType) {
        String string;
        if (errorType == null) {
            errorType = ErrorType.ERROR_STOP_RECORD;
        }
        int i2 = AnonymousClass11.$SwitchMap$com$jingdong$common$jdreactFramework$utils$video$VideoRecordActivity$ErrorType[errorType.ordinal()];
        if (i2 == 1) {
            string = getString(R.string.jdreact_record_video_error_file);
        } else if (i2 != 2) {
            string = getString(R.string.jdreact_record_video_error_fail_tips);
        } else {
            string = getString(R.string.jdreact_record_video_time_too_short);
        }
        ToastUtils.shortToast(string);
        if (OKLog.D) {
            OKLog.d(TAG, "error:" + errorType);
        }
        setStartRecordUi();
        deleteVideoFile();
    }

    private void hideRecordUi() {
        this.recordTitleLayout.setVisibility(8);
        this.recordProgressView.setVisibility(8);
        this.mTapRecordView.setVisibility(8);
    }

    private void hideVideoUi() {
        findViewById(R.id.video_record_back).setVisibility(8);
        findViewById(R.id.video_record_ok).setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initView() {
        setContentView(R.layout.jdreact_activity_video_record);
        this.recordParentContentLayout = (FrameLayout) findViewById(R.id.record_parent_content_layout);
        showRecordView();
        this.mTapRecordView = (TapRecordView) findViewById(R.id.tap_record);
        this.recordTitleLayout = findViewById(R.id.record_title_layout);
        this.recordProgressView = (RecordProgressView) findViewById(R.id.record_progress_view);
        this.recordTitleText = (TextView) findViewById(R.id.record_title_text);
        this.titleChangeCameraView = findViewById(R.id.title_change_camera);
        this.titleBackView = findViewById(R.id.title_back);
        this.mTapRecordView.setTapRecordListener(new TapRecordView.TapRecordListener() { // from class: com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity.2
            @Override // com.jingdong.common.jdreactFramework.utils.video.TapRecordView.TapRecordListener
            public void onFinishRecord(long j2) {
                if (OKLog.D) {
                    OKLog.d(VideoRecordActivity.TAG, "onFinishRecord time:" + j2);
                }
                VideoRecordActivity.this.stopRecord(j2);
            }

            @Override // com.jingdong.common.jdreactFramework.utils.video.TapRecordView.TapRecordListener
            public void onRecordingTime(long j2) {
                long j3 = j2 / 1000;
                if (j3 == 0) {
                    VideoRecordActivity.this.recordTitleText.setTextColor(-56284);
                }
                VideoRecordActivity.this.recordProgressView.setProgress((((float) j2) + 0.0f) / VideoRecordActivity.MAX_RECORD_TIME);
                VideoRecordActivity.this.recordTitleText.setText(j3 + "s");
            }

            @Override // com.jingdong.common.jdreactFramework.utils.video.TapRecordView.TapRecordListener
            public void onStartRecord() {
                VideoRecordActivity.this.startRecord();
            }
        });
        checkDiskSize();
    }

    private void recordOk() {
        File file = this.mVideoFile;
        if (file != null && file.exists() && this.mVideoFile.length() > 0) {
            Intent intent = new Intent();
            intent.putExtra(RECORD_VIDEO_PATH, this.mVideoFile.getAbsolutePath());
            setResult(-1, intent);
        } else {
            setResult(0);
        }
        finish();
    }

    private void releaseLoopVideoView() {
        LoopVideoViewHolder loopVideoViewHolder = this.loopVideoView;
        if (loopVideoViewHolder != null) {
            loopVideoViewHolder.release();
            this.loopVideoView.getView();
            this.loopVideoView = null;
            post(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: INVOKE 
                  (r2v0 'this' com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity A[IMMUTABLE_TYPE, THIS])
                  (wrap: java.lang.Runnable : 0x0012: CONSTRUCTOR 
                  (r2v0 'this' com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity A[IMMUTABLE_TYPE, THIS])
                  (r0 I:android.view.View A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity, android.view.View):void (m), WRAPPED] (LINE:5) call: com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity.5.<init>(com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity, android.view.View):void type: CONSTRUCTOR)
                  (500 int)
                 type: VIRTUAL call: com.jingdong.common.BaseActivity.post(java.lang.Runnable, int):void A[MD:(java.lang.Runnable, int):void (m)] (LINE:5) in method: com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity.releaseLoopVideoView():void, file: classes5.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 23 more
                */
            /*
                this = this;
                com.jingdong.common.jdreactFramework.utils.video.LoopVideoViewHolder r0 = r2.loopVideoView
                if (r0 == 0) goto L1a
                r0.release()
                com.jingdong.common.jdreactFramework.utils.video.LoopVideoViewHolder r0 = r2.loopVideoView
                android.view.View r0 = r0.getView()
                r1 = 0
                r2.loopVideoView = r1
                com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity$5 r1 = new com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity$5
                r1.<init>()
                r0 = 500(0x1f4, float:7.0E-43)
                r2.post(r1, r0)
            L1a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity.releaseLoopVideoView():void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void releaseRecorder() {
            MediaRecorder mediaRecorder = this.mMediaRecorder;
            if (mediaRecorder != null) {
                mediaRecorder.release();
                this.mMediaRecorder = null;
            }
            VideoRecordView videoRecordView = this.mVideoRecordView;
            if (videoRecordView != null) {
                videoRecordView.freeCamera();
                this.mVideoRecordView.setVisibility(8);
                this.recordParentContentLayout.removeView(this.mVideoRecordView);
                this.mVideoRecordView = null;
            }
        }

        private void resetRecordViewLayout(int i2, int i3) {
            VideoRecordView videoRecordView = this.mVideoRecordView;
            if (videoRecordView == null) {
                return;
            }
            int width = videoRecordView.getWidth();
            int height = this.mVideoRecordView.getHeight();
            if (width == 0 || height == 0 || i2 == 0 || i3 == 0) {
                return;
            }
            float f2 = height;
            float f3 = (i2 + 0.0f) / i3;
            if ((f2 + 0.0f) / width > 0.15f + f3) {
                ViewGroup.LayoutParams layoutParams = this.mVideoRecordView.getLayoutParams();
                if (layoutParams.width > 0) {
                    return;
                }
                layoutParams.width = (int) (f2 / f3);
                this.mVideoRecordView.requestLayout();
            }
        }

        private void setInRecordUi() {
            this.titleChangeCameraView.setVisibility(8);
            this.titleBackView.setClickable(false);
        }

        private void setStartRecordUi() {
            this.titleChangeCameraView.setVisibility(0);
            this.titleBackView.setClickable(true);
            this.recordTitleText.setTextColor(getResources().getColor(17170443));
            this.recordProgressView.setProgress(0.0f);
            this.recordTitleText.setText(R.string.jdreact_record_video_title_tips);
        }

        private void showLoopVideoView() {
            hideRecordUi();
            LoopVideoViewHolder loopVideoViewHolder = new LoopVideoViewHolder(this);
            this.loopVideoView = loopVideoViewHolder;
            this.recordParentContentLayout.addView(loopVideoViewHolder.getView(), 0, new FrameLayout.LayoutParams(-1, -1));
            this.loopVideoView.setVideoDisplayListener(new LoopVideoViewHolder.VideoDisplayListener() { // from class: com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity.4
                @Override // com.jingdong.common.jdreactFramework.utils.video.LoopVideoViewHolder.VideoDisplayListener
                public void onDisplay() {
                    VideoRecordActivity.this.releaseRecorder();
                    VideoRecordActivity.this.showVideoUi();
                }
            });
            this.loopVideoView.setVideoPath("file://" + this.mVideoFile.getAbsolutePath());
        }

        private void showRecordUi() {
            this.recordTitleLayout.setVisibility(0);
            this.recordProgressView.setVisibility(0);
            this.mTapRecordView.setVisibility(0);
        }

        private void showRecordView() {
            this.mVideoRecordView = new VideoRecordView(this);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            this.recordParentContentLayout.addView(this.mVideoRecordView, 0, layoutParams);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showVideoUi() {
            findViewById(R.id.video_record_back).setVisibility(0);
            findViewById(R.id.video_record_ok).setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void startRecord() {
            CamcorderProfile camcorderProfile;
            setInRecordUi();
            if (OKLog.D) {
                OKLog.d(TAG, "startRecord a");
            }
            MediaRecorder mediaRecorder = this.mMediaRecorder;
            if (mediaRecorder != null) {
                mediaRecorder.reset();
            } else {
                this.mMediaRecorder = new MediaRecorder();
            }
            this.mMediaRecorder.reset();
            this.mMediaRecorder.setOnInfoListener(this);
            this.mMediaRecorder.setOnErrorListener(this);
            Camera camera = this.mVideoRecordView.getCamera();
            if (camera == null) {
                if (OKLog.D) {
                    OKLog.d(TAG, "initRecorder error: camera null");
                }
                error(ErrorType.ERROR_START_RECORD);
                return;
            }
            try {
                camera.unlock();
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
            this.mMediaRecorder.setCamera(camera);
            this.mMediaRecorder.setVideoSource(1);
            this.mMediaRecorder.setAudioSource(1);
            this.mMediaRecorder.setOutputFormat(2);
            this.mMediaRecorder.setAudioEncoder(3);
            this.mMediaRecorder.setVideoEncoder(2);
            this.mMediaRecorder.setAudioEncodingBitRate(PcmRecord.DEFAULT_SAMPLE_RATE);
            int cameraId = this.mVideoRecordView.getCameraId();
            if (CamcorderProfile.hasProfile(cameraId, 5)) {
                camcorderProfile = CamcorderProfile.get(cameraId, 5);
            } else if (CamcorderProfile.hasProfile(cameraId, 4)) {
                camcorderProfile = CamcorderProfile.get(4);
            } else if (CamcorderProfile.hasProfile(cameraId, 7)) {
                camcorderProfile = CamcorderProfile.get(7);
            } else {
                camcorderProfile = CamcorderProfile.get(cameraId, 0);
            }
            Point videoSize = this.mVideoRecordView.getVideoSize();
            if (videoSize != null) {
                this.mMediaRecorder.setVideoSize(videoSize.x, videoSize.y);
            } else {
                this.mMediaRecorder.setVideoSize(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
                resetRecordViewLayout(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
            }
            int i2 = camcorderProfile.videoBitRate;
            if (i2 > 2097152) {
                this.mMediaRecorder.setVideoEncodingBitRate(2097152);
            } else {
                this.mMediaRecorder.setVideoEncodingBitRate(i2);
            }
            this.mMediaRecorder.setVideoFrameRate(camcorderProfile.videoFrameRate);
            this.mMediaRecorder.setOrientationHint(this.mVideoRecordView.isBackCamera() ? 90 : 270);
            try {
                File file = new File(this.videoParentPath + "record_" + System.currentTimeMillis() + PreDownLoadManager.VIDEO_SKU_SUFFIX);
                this.mVideoFile = file;
                if (file.exists()) {
                    this.mVideoFile.delete();
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "startRecord b");
                }
                try {
                    this.mMediaRecorder.setOutputFile(this.mVideoFile.getPath());
                    this.mMediaRecorder.prepare();
                    this.mMediaRecorder.start();
                } catch (Exception e3) {
                    String str = TAG;
                    OKLog.e(str, e3);
                    if (OKLog.D) {
                        OKLog.d(str, "startRecord exception:" + e3.getMessage());
                    }
                    error(ErrorType.ERROR_START_RECORD);
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "startRecord c");
                }
            } catch (Exception e4) {
                OKLog.e(TAG, e4);
                error(ErrorType.ERROR_CREATE_FILE);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stopRecord(long j2) {
            File file;
            if (OKLog.D) {
                OKLog.d(TAG, "stopRecord ");
            }
            MediaRecorder mediaRecorder = this.mMediaRecorder;
            ErrorType errorType = null;
            if (mediaRecorder != null) {
                try {
                    mediaRecorder.setOnInfoListener(null);
                    this.mMediaRecorder.setOnErrorListener(null);
                    this.mMediaRecorder.stop();
                } catch (Exception e2) {
                    OKLog.e(TAG, e2);
                    errorType = ErrorType.ERROR_STOP_RECORD;
                }
            }
            if (j2 < 1000) {
                errorType = ErrorType.ERROR_TIME_TOO_SHORT;
            }
            if (errorType == null && (file = this.mVideoFile) != null && file.length() > 0) {
                showLoopVideoView();
                if (OKLog.D) {
                    OKLog.d(TAG, "record video filepath:" + this.mVideoFile.getPath() + "  size:" + (this.mVideoFile.length() / 1024) + "kB");
                    return;
                }
                return;
            }
            error(errorType);
        }

        @Override // com.jingdong.common.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity
        public void onBackPressed() {
            deleteVideoFile();
            if (this.loopVideoView == null) {
                super.onBackPressed();
                return;
            }
            showRecordView();
            showRecordUi();
            setStartRecordUi();
            hideVideoUi();
            releaseLoopVideoView();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
        public void onCreate(Bundle bundle) {
            this.statusBarTintEnable = false;
            super.onCreate(bundle);
            setUseBasePV(false);
            MAX_RECORD_TIME = getIntent().getIntExtra(KEY_MAX_RECORD_TIME, 10000);
            try {
                File externalCacheDir = PermissionHelper.getExternalCacheDir();
                this.rootFileDir = externalCacheDir;
                if (externalCacheDir == null) {
                    this.rootFileDir = PermissionHelper.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
                }
            } catch (Throwable th) {
                JLog.e(TAG, th.toString());
            }
            if (this.rootFileDir == null) {
                this.rootFileDir = getFilesDir();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(this.rootFileDir);
            String str = File.separator;
            sb.append(str);
            sb.append("videoRecord");
            sb.append(str);
            this.videoParentPath = sb.toString();
            File file = new File(this.videoParentPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            checkPermissions();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
        public void onDestroy() {
            super.onDestroy();
            releaseRecorder();
            LoopVideoViewHolder loopVideoViewHolder = this.loopVideoView;
            if (loopVideoViewHolder != null) {
                loopVideoViewHolder.release();
            }
        }

        @Override // android.media.MediaRecorder.OnErrorListener
        public void onError(MediaRecorder mediaRecorder, int i2, int i3) {
            if (OKLog.D) {
                OKLog.d(TAG, "MediaRecorder.OnErrorListener  onError:" + i2 + "   " + i3);
            }
            if (mediaRecorder != null) {
                try {
                    mediaRecorder.reset();
                } catch (Exception e2) {
                    OKLog.e(TAG, e2);
                }
            }
            error(ErrorType.ERROR_RECORDER);
        }

        @Override // android.media.MediaRecorder.OnInfoListener
        public void onInfo(MediaRecorder mediaRecorder, int i2, int i3) {
            if (OKLog.D) {
                OKLog.d(TAG, "MediaRecorder.OnInfoListener  onInfo:" + i2 + "   " + i3);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
        public void onPause() {
            super.onPause();
            LoopVideoViewHolder loopVideoViewHolder = this.loopVideoView;
            if (loopVideoViewHolder != null) {
                loopVideoViewHolder.pause();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
        public void onResume() {
            super.onResume();
            LoopVideoViewHolder loopVideoViewHolder = this.loopVideoView;
            if (loopVideoViewHolder != null) {
                loopVideoViewHolder.resume();
            }
        }

        public void recordOnClickEvent(View view) {
            int id = view.getId();
            if (id != R.id.title_back && id != R.id.video_record_back) {
                if (id == R.id.title_change_camera) {
                    changeCamera();
                    return;
                } else if (id == R.id.video_record_ok) {
                    recordOk();
                    return;
                } else {
                    return;
                }
            }
            onBackPressed();
        }
    }
