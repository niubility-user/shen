package com.jingdong.manto.jsapi.refact.rec;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.jingdong.manto.R;
import com.jingdong.manto.jsapi.refact.rec.MantoConfirmView;
import com.jingdong.manto.jsapi.refact.rec.MantoRecorderView;
import com.jingdong.manto.jsapi.refact.rec.RecorderCtrlView;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoPermission;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class MantoVideoRecorderActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String VIDEO_PARAM = "__params";
    private View bottomContainer;
    private ImageView cameraBtn;
    private MantoConfirmView confirmView;
    private RecorderCtrlView ctrlView;
    private ImageView flashBtn;
    private OrientationEventListener mOrientationListener;
    private MantoVideoPlayerView playerView;
    private MantoRecorderView recorderView;
    private View topContainer;
    private final int DURATION = 200;
    private int curOrientation = 0;
    private float curRotate = 0.0f;
    private boolean isRecordSuccess = false;
    private int currentFlashStatus = 0;
    private int recordMaxTime = 10;

    private void checkPermissions() {
        String[] strArr = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
        if (MantoPermission.hasPermissions(strArr)) {
            preparedData();
        } else {
            MantoPermission.requestPermissions(this, strArr, new IPermission.PermissionCallBack() { // from class: com.jingdong.manto.jsapi.refact.rec.MantoVideoRecorderActivity.7
                @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
                public void onDenied() {
                    MantoVideoRecorderActivity.this.finish();
                }

                @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
                public void onGranted() {
                    MantoVideoRecorderActivity.this.preparedData();
                }
            });
        }
    }

    private void closeLight() {
        this.recorderView.turnOffLight();
        this.flashBtn.setImageResource(R.drawable.manto_video_flash_close);
    }

    private final void initUI() {
        this.topContainer = findViewById(R.id.topContainer);
        this.bottomContainer = findViewById(R.id.bottomContainer);
        this.recorderView = (MantoRecorderView) findViewById(R.id.recorderView);
        this.playerView = (MantoVideoPlayerView) findViewById(R.id.playerView);
        this.confirmView = (MantoConfirmView) findViewById(R.id.confirmView);
        this.recorderView.setVisibility(4);
        this.playerView.setVisibility(4);
        this.ctrlView = (RecorderCtrlView) findViewById(R.id.ctrlView);
        this.confirmView.setVisibility(4);
        this.ctrlView.setVisibility(4);
        this.topContainer.setVisibility(4);
        ImageView imageView = (ImageView) this.topContainer.findViewById(R.id.backTv);
        this.flashBtn = (ImageView) findViewById(R.id.flashBtn);
        int i2 = R.id.cameraBtn;
        this.cameraBtn = (ImageView) findViewById(i2);
        this.topContainer.findViewById(i2).setOnClickListener(this);
        imageView.setOnClickListener(this);
        imageView.setColorFilter(-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playerRecordedVideo() {
        this.isRecordSuccess = true;
        this.playerView.setVisibility(0);
        closeLight();
        this.playerView.play(this.recorderView.getRecordFile() != null ? this.recorderView.getRecordFile().getPath() : "", new MediaPlayer.OnErrorListener() { // from class: com.jingdong.manto.jsapi.refact.rec.MantoVideoRecorderActivity.5
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                Toast.makeText(MantoVideoRecorderActivity.this.getApplicationContext(), R.string.manto_record_play_err, 0).show();
                return true;
            }
        });
        this.recorderView.setVisibility(4);
        this.confirmView.showMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void preparedData() {
        if (getIntent() == null) {
            finish();
            return;
        }
        VideoParam videoParam = (VideoParam) getIntent().getParcelableExtra(VIDEO_PARAM);
        this.recorderView.setVisibility(4);
        if (videoParam != null) {
            int i2 = videoParam.maxTime;
            this.recordMaxTime = i2;
            this.ctrlView.setOriginalRecordTime(i2);
            this.recorderView.setRecordMaxTime(this.recordMaxTime * 1000);
            this.recorderView.initSurface(videoParam.camera);
            if (!TextUtils.isEmpty(videoParam.recordFilePath)) {
                this.recorderView.setRecordFile(new File(videoParam.recordFilePath));
            }
        } else {
            this.ctrlView.setOriginalRecordTime(this.recordMaxTime);
            this.recorderView.setRecordMaxTime(this.recordMaxTime * 1000);
            this.recorderView.initSurface(0);
        }
        this.flashBtn.setOnClickListener(this);
        this.cameraBtn.setOnClickListener(this);
        this.recorderView.setVisibility(0);
        this.ctrlView.setVisibility(0);
        this.topContainer.setVisibility(0);
        this.ctrlView.setCtrlListener(new RecorderCtrlView.CtrlListener() { // from class: com.jingdong.manto.jsapi.refact.rec.MantoVideoRecorderActivity.1
            @Override // com.jingdong.manto.jsapi.refact.rec.RecorderCtrlView.CtrlListener
            public void onProgress(long j2) {
            }

            @Override // com.jingdong.manto.jsapi.refact.rec.RecorderCtrlView.CtrlListener
            public void onRecordTimeTooShort() {
                Toast.makeText(MantoVideoRecorderActivity.this.getApplicationContext(), R.string.manto_record_too_short, 0).show();
                MantoVideoRecorderActivity.this.recorderView.cancelRecord();
                MantoVideoRecorderActivity.this.resetUI();
            }

            @Override // com.jingdong.manto.jsapi.refact.rec.RecorderCtrlView.CtrlListener
            public void onStart() {
                MantoVideoRecorderActivity.this.startRecord();
            }

            @Override // com.jingdong.manto.jsapi.refact.rec.RecorderCtrlView.CtrlListener
            public void onStop(long j2) {
                MantoVideoRecorderActivity.this.recorderView.stop();
                MantoVideoRecorderActivity.this.ctrlView.setOriginalRecordTime(MantoVideoRecorderActivity.this.recordMaxTime);
                MantoVideoRecorderActivity.this.ctrlView.setVisibility(4);
            }
        });
        this.confirmView.setActionCallBack(new MantoConfirmView.ActionCallBack() { // from class: com.jingdong.manto.jsapi.refact.rec.MantoVideoRecorderActivity.2
            @Override // com.jingdong.manto.jsapi.refact.rec.MantoConfirmView.ActionCallBack
            public void onCancel() {
                MantoVideoRecorderActivity.this.recorderView.cancelRecord();
                MantoVideoRecorderActivity.this.resetUI();
            }

            @Override // com.jingdong.manto.jsapi.refact.rec.MantoConfirmView.ActionCallBack
            public void onConfirm() {
                File recordFile = MantoVideoRecorderActivity.this.recorderView.getRecordFile();
                if (recordFile == null || !recordFile.exists()) {
                    MantoVideoRecorderActivity.this.setResult(0);
                } else {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse(recordFile.getPath()));
                    ArrayList<String> arrayList = new ArrayList<>(1);
                    arrayList.add(recordFile.getPath());
                    intent.putStringArrayListExtra("select_media_list", arrayList);
                    MantoVideoRecorderActivity.this.setResult(-1, intent);
                }
                MantoVideoRecorderActivity.this.finish();
            }
        });
        this.mOrientationListener = new OrientationEventListener(this, 3) { // from class: com.jingdong.manto.jsapi.refact.rec.MantoVideoRecorderActivity.3
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i3) {
                int i4;
                if (i3 == -1) {
                    return;
                }
                if (i3 > 350 || i3 < 10) {
                    i4 = 0;
                } else if (i3 > 80 && i3 < 100) {
                    i4 = 90;
                } else if (i3 > 170 && i3 < 190) {
                    i4 = 180;
                } else if (i3 <= 260 || i3 >= 280) {
                    return;
                } else {
                    i4 = 270;
                }
                MantoVideoRecorderActivity.this.rotateViews(i4);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetUI() {
        this.cameraBtn.clearAnimation();
        this.ctrlView.clearAnimation();
        this.ctrlView.reset();
        this.playerView.reset();
        closeLight();
        this.flashBtn.setVisibility(0);
        this.flashBtn.setImageResource(R.drawable.manto_video_flash_close);
        this.topContainer.setTranslationY(0.0f);
        this.bottomContainer.setTranslationY(0.0f);
        this.ctrlView.setVisibility(0);
        this.recorderView.setVisibility(0);
        this.confirmView.setVisibility(4);
        this.playerView.setVisibility(4);
        this.isRecordSuccess = false;
        if (this.recorderView.getCameraPosition() == 1) {
            this.flashBtn.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rotateViews(int i2) {
        int i3 = this.curOrientation;
        if (i2 == i3) {
            return;
        }
        float f2 = this.curRotate;
        int i4 = -(i2 - i3);
        if (i4 == 270) {
            i4 = -90;
        } else if (i4 == -270) {
            i4 = 90;
        }
        float f3 = i4 + f2;
        this.curOrientation = i2;
        this.curRotate = f3;
        MantoLog.e("better", String.format("orientation:%s, angle:%s, start:%s, end:%s", Integer.valueOf(i2), Integer.valueOf(i4), Float.valueOf(f2), Float.valueOf(f3)));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.cameraBtn, "Rotation", f2, f3);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.flashBtn, "Rotation", f2, f3);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.ctrlView, "Rotation", f2, f3);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3);
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startRecord() {
        this.topContainer.animate().setDuration(200L).translationY(-this.topContainer.getHeight()).start();
        this.bottomContainer.animate().setDuration(200L).translationY(this.bottomContainer.getHeight()).start();
        this.recorderView.record(this.curOrientation, new MantoRecorderView.OnRecordListener() { // from class: com.jingdong.manto.jsapi.refact.rec.MantoVideoRecorderActivity.4
            @Override // com.jingdong.manto.jsapi.refact.rec.MantoRecorderView.OnRecordListener
            public void onRecordFailed(Exception exc) {
                MantoLog.e("better", exc.toString());
                Toast.makeText(MantoVideoRecorderActivity.this.getApplicationContext(), R.string.manto_record_failed, 0).show();
                MantoVideoRecorderActivity.this.isRecordSuccess = false;
                MantoVideoRecorderActivity.this.resetUI();
                MantoVideoRecorderActivity.this.recorderView.cancelRecord();
            }

            @Override // com.jingdong.manto.jsapi.refact.rec.MantoRecorderView.OnRecordListener
            public void onRecordFinish() {
                MantoVideoRecorderActivity.this.playerRecordedVideo();
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ImageView imageView;
        int i2;
        int id = view.getId();
        if (id == R.id.backTv) {
            finish();
            return;
        }
        if (id == R.id.cameraBtn) {
            closeLight();
            this.recorderView.changeCameraFacing();
            if (this.recorderView.getCameraPosition() == 1) {
                this.flashBtn.setVisibility(4);
                return;
            }
            this.flashBtn.setVisibility(0);
        } else if (id != R.id.flashBtn) {
            return;
        } else {
            if (this.recorderView.toggleFlash(this.currentFlashStatus)) {
                imageView = this.flashBtn;
                i2 = R.drawable.manto_video_flash_open;
                imageView.setImageResource(i2);
            }
        }
        imageView = this.flashBtn;
        i2 = R.drawable.manto_video_flash_close;
        imageView.setImageResource(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.manto_video_recorder_ui);
        initUI();
        checkPermissions();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        closeLight();
        RecorderCtrlView recorderCtrlView = this.ctrlView;
        if (recorderCtrlView != null) {
            recorderCtrlView.reset();
            this.ctrlView = null;
        }
        MantoRecorderView mantoRecorderView = this.recorderView;
        if (mantoRecorderView != null) {
            mantoRecorderView.stopRecord();
            this.recorderView.stopPreview();
            this.recorderView.release();
        }
        MantoVideoPlayerView mantoVideoPlayerView = this.playerView;
        if (mantoVideoPlayerView != null) {
            mantoVideoPlayerView.reset();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        MantoRecorderView mantoRecorderView;
        super.onPause();
        closeLight();
        OrientationEventListener orientationEventListener = this.mOrientationListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
        RecorderCtrlView recorderCtrlView = this.ctrlView;
        if (recorderCtrlView != null) {
            recorderCtrlView.reset();
        }
        if (!this.isRecordSuccess && (mantoRecorderView = this.recorderView) != null) {
            mantoRecorderView.cancelRecord();
            this.recorderView.stopPreview();
        }
        MantoVideoPlayerView mantoVideoPlayerView = this.playerView;
        if (mantoVideoPlayerView != null) {
            mantoVideoPlayerView.pause();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        OrientationEventListener orientationEventListener = this.mOrientationListener;
        if (orientationEventListener != null && orientationEventListener.canDetectOrientation()) {
            this.mOrientationListener.enable();
        }
        try {
            if (this.recorderView.getCameraPosition() == 1) {
                this.recorderView.post(new Runnable() { // from class: com.jingdong.manto.jsapi.refact.rec.MantoVideoRecorderActivity.6
                    @Override // java.lang.Runnable
                    public void run() {
                        MantoVideoRecorderActivity.this.recorderView.changeCameraFacing();
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.recorderView != null && !this.isRecordSuccess) {
            resetUI();
            this.recorderView.startPreview();
        }
        MantoVideoPlayerView mantoVideoPlayerView = this.playerView;
        if (mantoVideoPlayerView != null) {
            mantoVideoPlayerView.resume();
        }
    }
}
