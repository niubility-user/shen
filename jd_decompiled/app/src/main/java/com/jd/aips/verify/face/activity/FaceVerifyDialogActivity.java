package com.jd.aips.verify.face.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.aips.detect.face.camera.FsCameraTextureView;
import com.jd.aips.verify.face.FaceVerifyConfig;
import com.jd.aips.verify.face.FaceVerifyParams;
import com.jd.aips.verify.face.biz.VerityFaceDialogAnim;
import com.jd.aips.verify.identity.R;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes12.dex */
public class FaceVerifyDialogActivity extends FaceVerifyActivity {
    private static final int DETECT_LOADER_CODE = 4098;
    static final int MAIN_MSG_SHOW_CLOSE_BUTTON = 10;
    private ImageView animationView;
    private int buttonShowTime = 3;
    private ImageView closeButton;
    private VerityFaceDialogAnim loadingAnimUtils;
    private ImageView loadingView;
    private View progressView;
    private TextView stepView;

    private void setTipsAndStepText(String str, String str2, int i2) {
        if (!TextUtils.isEmpty(str)) {
            this.tipView.setText(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            this.stepView.setText(str2);
        }
        if (i2 >= 0) {
            this.stepView.setVisibility(i2);
        }
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected int getLoaderId() {
        return 4098;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void hideCover() {
        if (this.loadingView.getVisibility() == 0) {
            this.loadingView.setVisibility(4);
        }
        super.hideCover();
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void hideLoadingView() {
        this.loadingAnimUtils.animationStopVerify(this.animationView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public boolean initParams() {
        boolean initParams = super.initParams();
        if (initParams) {
            int i2 = ((FaceVerifyConfig) ((FaceVerifyParams) this.session.verifyParams).verifyConfig).verificationSdk.config.nosense_timeout;
            this.buttonShowTime = i2;
            if (i2 == 0) {
                this.buttonShowTime = 3;
            }
        }
        return initParams;
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void initViews() {
        requestWindowFeature(1);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        getWindow().addFlags(128);
        setContentView(R.layout.fcvf_activity_face_verify_dialog);
        this.contentView = (ConstraintLayout) findViewById(R.id.fcvf_content);
        this.closeButton = (ImageView) findViewById(R.id.fcvf_close);
        this.mainContentView = findViewById(R.id.fcvf_content_main);
        this.progressView = findViewById(R.id.fcvf_progress);
        this.loadingView = (ImageView) findViewById(R.id.fcvf_loading);
        this.animationView = (ImageView) findViewById(R.id.fcvf_animation_view);
        this.tipView = (TextView) findViewById(R.id.fcvf_tip);
        this.stepView = (TextView) findViewById(R.id.fcvf_step);
        this.previewCover = (ImageView) findViewById(R.id.fcvf_preview_cover);
        this.previewContainer = (LinearLayout) findViewById(R.id.fcvf_preview_container);
        this.cameraTextureView = new FsCameraTextureView(this);
        this.cameraTextureView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.aips.verify.face.activity.FaceVerifyDialogActivity.1
            {
                FaceVerifyDialogActivity.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FaceVerifyDialogActivity.this.verifyTracker.trackUserCancel();
                FaceVerifyDialogActivity.this.verifyUserCancel();
            }
        });
        Handler handler = this.mainHandler;
        handler.sendMessageDelayed(handler.obtainMessage(10), this.buttonShowTime * 1000);
        this.contentView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.aips.verify.face.activity.FaceVerifyDialogActivity.2
            {
                FaceVerifyDialogActivity.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FaceVerifyDialogActivity.this.closeButton.setVisibility(0);
            }
        });
        this.loadingAnimUtils = new VerityFaceDialogAnim();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void onDialogDismiss(boolean z) {
        super.onDialogDismiss(z);
        if (z) {
            this.mainContentView.setVisibility(0);
            this.closeButton.setVisibility(0);
        }
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void performDetect(Message message) {
        int i2 = message.what;
        if (i2 != 120) {
            if (i2 == 190 && this.verifyState.get() == 5) {
                performOnStopDetected();
                this.progressView.setVisibility(8);
                showLoadingView();
                showCover();
                if (this.changeResolutionEnable) {
                    performChangeResolution();
                    return;
                } else if (this.changeExposureEnable) {
                    performChangeExposure();
                    return;
                } else {
                    this.mainHandler.sendEmptyMessage(800);
                    return;
                }
            }
            return;
        }
        performOnStopDetected();
        this.session.resetDetectCaches();
        showDialog(getDialogStyleForRetryCount(), getString(R.string.fcvf_prompt_face_out));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void performOthers(Message message) {
        super.performOthers(message);
        if (message.what != 10) {
            return;
        }
        this.closeButton.setVisibility(0);
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void performVerify(Message message) {
        int i2 = message.what;
        if (i2 != 800) {
            if (i2 == 810) {
                setTipsAndStepText("\u5237\u8138\u8ba4\u8bc1\u6210\u529f", "", 4);
                this.loadingAnimUtils.animationReqVeritySuccess(this.animationView);
                performTaskMainHandler(R2.attr.endYear, 1500L, null);
            } else if (i2 != 820) {
            } else {
                this.loadingAnimUtils.animationStopVerify(this.animationView);
                verifySuccessFinish();
            }
        } else if (this.verifyState.get() == 5) {
            if (this.sceneConfig == 2) {
                verifySuccessFinish();
                return;
            }
            this.progressView.setVisibility(8);
            hideDetectView();
            this.loadingAnimUtils.animationReqVerityProcess(this.animationView);
            requestVerify();
            uploadActionImages();
        }
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void refreshProgressView(int i2, int i3) {
        setTipsAndStepText("", String.format("\u5237\u8138\u8ba4\u8bc1\u4e2d(%d/%d)", Integer.valueOf(i2 + 1), Integer.valueOf(i3)), 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void resetProgressView() {
        super.resetProgressView();
        setTipsAndStepText("\u8bf7\u4fdd\u6301\u4e0d\u52a8", String.format("\u5237\u8138\u8ba4\u8bc1\u4e2d(%d/%d)", Integer.valueOf(this.detectFaceCurrentStep), Integer.valueOf(this.totalStep)), 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void resetVerify() {
        this.mainContentView.setVisibility(0);
        this.progressView.setVisibility(0);
        this.closeButton.setVisibility(0);
        super.resetVerify();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void showCover() {
        super.showCover();
        if (this.loadingView.getVisibility() != 0) {
            this.loadingView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void showDialog(int i2, String str) {
        this.mainHandler.removeMessages(10);
        this.mainContentView.setVisibility(4);
        this.closeButton.setVisibility(4);
        super.showDialog(i2, str);
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void showLoadingView() {
        this.loadingAnimUtils.animationReqVerityProcess(this.animationView);
    }
}
