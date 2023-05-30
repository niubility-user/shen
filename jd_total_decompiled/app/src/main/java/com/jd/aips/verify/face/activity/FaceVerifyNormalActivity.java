package com.jd.aips.verify.face.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.aips.common.utils.ImageUtils;
import com.jd.aips.common.utils.SystemBarTintManager;
import com.jd.aips.detect.face.camera.FsCameraTextureView;
import com.jd.aips.verify.config.VerificationSdk;
import com.jd.aips.verify.face.FaceVerifyConfig;
import com.jd.aips.verify.face.FaceVerifyParams;
import com.jd.aips.verify.face.api.UploadVerifyRecordApi;
import com.jd.aips.verify.face.bean.ColorfulImage;
import com.jd.aips.verify.face.biz.ColorChangeHelper;
import com.jd.aips.verify.face.biz.VerityFaceNormalAnim;
import com.jd.aips.verify.face.view.CircleProgress;
import com.jd.aips.verify.identity.R;
import com.jd.aips.widget.LoadingView;
import com.jingdong.sdk.platform.business.personal.R2;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes12.dex */
public class FaceVerifyNormalActivity extends FaceVerifyActivity {
    protected static final int CHANGE_COLOR_STATE_CHANGING = 2;
    protected static final int CHANGE_COLOR_STATE_COMPLETED = 3;
    protected static final int CHANGE_COLOR_STATE_ORIGINAL = 0;
    protected static final int CHANGE_COLOR_STATE_READY = 1;
    private static final int DETECT_LOADER_CODE = 4097;
    static final int MAIN_MSG_BG_COLORFUL_CHANGE = 610;
    static final int MAIN_MSG_BG_COLORFUL_COMPLETE = 620;
    static final int MAIN_MSG_BG_COLORFUL_PERFORM = 600;
    private RelativeLayout animationContainer;
    private ImageView animationView;
    private ImageView backButton;
    private View bottomView;
    private ColorChangeHelper colorChangeHelper;
    private TextView countDownView;
    private TextView faqView;
    private TextView jumpView;
    private LoadingView loadingView;
    private int navigationBarColor;
    private CircleProgress progressView;
    private int statusBarColor;
    SystemBarTintManager systemBarTintManager;
    private TextView titleView;
    private VerityFaceNormalAnim verifyFaceNormalAnim;
    private boolean faqEnable = false;
    private boolean jumpEnable = false;
    private boolean colorfulEnable = false;
    protected AtomicInteger changeColorState = new AtomicInteger(0);

    private void performChangeColor(String str, int i2) {
        SystemBarTintManager systemBarTintManager;
        try {
            String str2 = "setColorfulCircleProgress = " + i2;
            int parseColor = Color.parseColor(str);
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 21) {
                Window window = getWindow();
                window.setStatusBarColor(parseColor);
                window.setNavigationBarColor(parseColor);
            } else if (i3 >= 19 && (systemBarTintManager = this.systemBarTintManager) != null) {
                systemBarTintManager.setTintColor(parseColor);
            }
            this.contentView.setBackgroundColor(parseColor);
            this.mainContentView.setBackgroundColor(parseColor);
            this.bottomView.setBackgroundColor(parseColor);
            this.progressView.setGradientColors(new int[]{Color.parseColor("#FF333333"), Color.parseColor("#FF333333")});
            CircleProgress circleProgress = this.progressView;
            circleProgress.setValue((circleProgress.getMaxValue() * i2) / 100.0f);
        } catch (Exception unused) {
        }
    }

    private void performColorful() {
        if (this.changeColorState.compareAndSet(0, 1)) {
            resetProgressView();
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 21 && i2 >= 19 && this.systemBarTintManager == null) {
                SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
                this.systemBarTintManager = systemBarTintManager;
                systemBarTintManager.setStatusBarTintEnabled(true);
                this.systemBarTintManager.setNavigationBarTintEnabled(true);
            }
            performColorfulPrepare();
            if (this.colorChangeHelper.toPrepare(new ColorChangeHelper.ColorChangeCallback() { // from class: com.jd.aips.verify.face.activity.FaceVerifyNormalActivity.4
                {
                    FaceVerifyNormalActivity.this = this;
                }

                @Override // com.jd.aips.verify.face.biz.ColorChangeHelper.ColorChangeCallback
                public void onChange(String str, int i3) {
                    Message obtainMessage = FaceVerifyNormalActivity.this.mainHandler.obtainMessage(610, str);
                    obtainMessage.arg1 = i3;
                    FaceVerifyNormalActivity.this.mainHandler.sendMessage(obtainMessage);
                }

                @Override // com.jd.aips.verify.face.biz.ColorChangeHelper.ColorChangeCallback
                public void onCompleted() {
                    Handler handler = FaceVerifyNormalActivity.this.mainHandler;
                    handler.sendMessage(handler.obtainMessage(620));
                }
            })) {
                if (this.changeColorState.compareAndSet(1, 2)) {
                    this.colorChangeHelper.toChangeColor();
                    return;
                }
                return;
            }
            Handler handler = this.mainHandler;
            handler.sendMessage(handler.obtainMessage(620));
            return;
        }
        Handler handler2 = this.mainHandler;
        handler2.sendMessage(handler2.obtainMessage(620));
    }

    private void performColorfulPrepare() {
        this.verifyFaceNormalAnim.stopAnimation(this.animationView);
        this.animationContainer.setVisibility(4);
        this.progressView.setValue(0.0f);
        this.bottomView.setVisibility(4);
        this.tipView.setText(R.string.fcvf_prompt_keep_face_in);
        this.tipView.setVisibility(0);
    }

    private void resetColorful() {
        if (this.changeColorState.get() != 0) {
            this.colorChangeHelper.cancel();
            resumeColorfulView();
        }
        this.changeColorState.set(0);
    }

    private void resumeColorfulView() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.setStatusBarColor(this.statusBarColor);
            window.setNavigationBarColor(this.navigationBarColor);
        } else {
            SystemBarTintManager systemBarTintManager = this.systemBarTintManager;
            if (systemBarTintManager != null) {
                systemBarTintManager.setStatusBarTintEnabled(false);
                this.systemBarTintManager.setNavigationBarTintEnabled(false);
            }
        }
        this.contentView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        this.mainContentView.setBackgroundResource(R.drawable.aips_fcvf_bg_main);
        this.bottomView.setBackgroundResource(R.drawable.aips_fcvf_bg_bottom);
        this.tipView.setText("");
        this.progressView.setGradientColors(new int[]{Color.parseColor("#FF4EE4A4"), Color.parseColor("#FF1DB270")});
        CircleProgress circleProgress = this.progressView;
        circleProgress.setValue(circleProgress.getMaxValue());
        this.bottomView.setVisibility(0);
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void doReport(byte[] bArr, int i2, int i3) {
        if (this.session != null) {
            ColorChangeHelper colorChangeHelper = this.colorChangeHelper;
            UploadVerifyRecordApi.uploadThumbnailFlowRecord(getApplicationContext(), this.session, this.reportCount, bArr, i2, i3, this.mCameraProxy.getDegrees_for_pre(), colorChangeHelper != null ? colorChangeHelper.getCurrentColor() : "");
        }
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected int getLoaderId() {
        return 4097;
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void hideDetectView() {
        super.hideDetectView();
        this.countDownView.setVisibility(4);
        this.animationContainer.setVisibility(4);
        this.verifyFaceNormalAnim.stopAnimation(this.animationView);
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void hideLoadingView() {
        this.loadingView.setVisibility(8);
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public boolean initParams() {
        boolean initParams = super.initParams();
        if (initParams) {
            FaceVerifyConfig faceVerifyConfig = (FaceVerifyConfig) ((FaceVerifyParams) this.session.verifyParams).verifyConfig;
            VerificationSdk.Config config = faceVerifyConfig.verificationSdk.config;
            this.faqEnable = config.faqFlag;
            this.jumpEnable = config.jump_other_verification_flag;
            boolean z = config.facedazzle_flag;
            this.colorfulEnable = z;
            if (z) {
                this.colorChangeHelper = new ColorChangeHelper(faceVerifyConfig.faceDazzleSdk.config);
            }
        }
        return initParams;
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void initViews() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        Window window = getWindow();
        window.addFlags(128);
        if (Build.VERSION.SDK_INT >= 21) {
            this.statusBarColor = window.getStatusBarColor();
            this.navigationBarColor = window.getNavigationBarColor();
        }
        setContentView(R.layout.fcvf_activity_face_verify_normal);
        this.contentView = (ConstraintLayout) findViewById(R.id.fcvf_content);
        this.backButton = (ImageView) findViewById(R.id.fcvf_back);
        final VerificationSdk.Config config = ((FaceVerifyConfig) ((FaceVerifyParams) this.session.verifyParams).verifyConfig).verificationSdk.config;
        this.titleView = (TextView) findViewById(R.id.fcvf_title);
        if (config.detect_area_flag && !TextUtils.isEmpty(config.detect_text)) {
            this.titleView.setText(config.detect_text);
        }
        this.countDownView = (TextView) findViewById(R.id.fcvf_countdown);
        this.mainContentView = findViewById(R.id.fcvf_content_main);
        CircleProgress circleProgress = (CircleProgress) findViewById(R.id.fcvf_circle_progress);
        this.progressView = circleProgress;
        circleProgress.reset();
        LoadingView loadingView = (LoadingView) findViewById(R.id.fcvf_loading);
        this.loadingView = loadingView;
        loadingView.setText(R.string.fcvf_prompt_detecting);
        this.animationContainer = (RelativeLayout) findViewById(R.id.fcvf_animation_container);
        this.animationView = (ImageView) findViewById(R.id.fcvf_animation_view);
        this.tipView = (TextView) findViewById(R.id.fcvf_tip);
        this.bottomView = findViewById(R.id.fcvf_content_bottom);
        this.previewCover = (ImageView) findViewById(R.id.fcvf_preview_cover);
        this.previewContainer = (LinearLayout) findViewById(R.id.fcvf_preview_container);
        this.cameraTextureView = new FsCameraTextureView(this);
        this.cameraTextureView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        TextView textView = (TextView) findViewById(R.id.fcvf_faq);
        this.faqView = textView;
        if (this.faqEnable) {
            textView.setText(config.faqText);
            this.faqView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        TextView textView2 = (TextView) findViewById(R.id.fcvf_jump);
        this.jumpView = textView2;
        if (this.jumpEnable) {
            textView2.setText(config.jump_text);
            this.jumpView.setTextColor(Color.parseColor(config.jump_colour));
            this.jumpView.setVisibility(0);
        } else {
            textView2.setVisibility(8);
        }
        View findViewById = findViewById(R.id.fcvf_divider);
        if (this.faqEnable && this.jumpEnable) {
            findViewById.setVisibility(0);
        } else {
            findViewById.setVisibility(8);
        }
        this.backButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.aips.verify.face.activity.FaceVerifyNormalActivity.1
            {
                FaceVerifyNormalActivity.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FaceVerifyNormalActivity.this.verifyUserCancel();
            }
        });
        this.faqView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.aips.verify.face.activity.FaceVerifyNormalActivity.2
            {
                FaceVerifyNormalActivity.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(config.faqUrl) || TextUtils.isEmpty(config.faqText)) {
                    return;
                }
                VerifyWebActivity.intentTo(FaceVerifyNormalActivity.this, config.faqUrl);
                FaceVerifyNormalActivity.this.verifyTracker.trackClickFaq();
            }
        });
        this.jumpView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.aips.verify.face.activity.FaceVerifyNormalActivity.3
            {
                FaceVerifyNormalActivity.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FaceVerifyNormalActivity.this.verifyTracker.trackUserCancel();
                FaceVerifyNormalActivity.this.engine.callbackFinishSDK(7, "\u7528\u6237\u70b9\u51fb\u8df3\u8f6c");
                FaceVerifyNormalActivity.this.finish();
            }
        });
        this.verifyFaceNormalAnim = new VerityFaceNormalAnim();
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void performDetect(Message message) {
        int i2 = message.what;
        if (i2 == 120) {
            performOnStopDetected();
            this.session.resetDetectCaches();
            showDialog(getDialogStyleForRetryCount(), getString(R.string.fcvf_prompt_face_out));
        } else if (i2 != 121) {
            if (i2 == 190 && this.verifyState.get() == 5) {
                performOnStopDetected();
                CircleProgress circleProgress = this.progressView;
                circleProgress.setValue(circleProgress.getMaxValue());
                if (this.colorfulEnable) {
                    this.mainHandler.sendEmptyMessage(600);
                    return;
                }
                showLoadingView();
                showCover();
                if (this.changeResolutionEnable) {
                    performChangeResolution();
                } else if (this.changeExposureEnable) {
                    performChangeExposure();
                } else {
                    this.mainHandler.sendEmptyMessage(800);
                }
            }
        } else {
            resetColorful();
            performOnStopDetected();
            this.session.resetDetectCaches();
            showDialog(getDialogStyleForRetryCount(), (String) message.obj);
        }
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void performOthers(Message message) {
        int i2 = message.what;
        if (i2 == 600) {
            if (this.verifyState.compareAndSet(5, 6)) {
                this.verifyTracker.trackChangeColorBegin();
                performColorful();
            }
        } else if (i2 != 610) {
            if (i2 == 620 && this.verifyState.compareAndSet(6, 5)) {
                this.changeColorState.set(3);
                resumeColorfulView();
                showCover();
                showLoadingView();
                this.changeColorState.set(0);
                if (this.changeResolutionEnable) {
                    performChangeResolution();
                } else if (this.changeExposureEnable) {
                    performChangeExposure();
                } else {
                    this.mainHandler.sendEmptyMessage(800);
                }
            }
        } else if (this.verifyState.get() == 6 && this.changeColorState.get() == 2) {
            performChangeColor((String) message.obj, message.arg1);
        }
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void performVerify(Message message) {
        int i2 = message.what;
        if (i2 != 800) {
            if (i2 == 810) {
                performTaskMainHandler(R2.attr.endYear, 1500L, null);
            } else if (i2 != 820) {
            } else {
                verifySuccessFinish();
            }
        } else if (this.verifyState.get() == 5) {
            if (this.sceneConfig == 2) {
                verifySuccessFinish();
                return;
            }
            hideDetectView();
            requestVerify();
            uploadActionImages();
        }
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void postDetected(@NonNull byte[] bArr, int i2, int i3) {
        if (this.verifyState.get() == 6 && this.changeColorState.get() == 2 && this.colorChangeHelper.needCapture()) {
            String currentColor = this.colorChangeHelper.getCurrentColor();
            if (!TextUtils.isEmpty(currentColor) && bArr != null) {
                this.session.cacheColorfulImage(new ColorfulImage(currentColor, ImageUtils.yuv2JpegRotaingWithoutMirror(bArr, i2, i3, 80, 512, 384, this.mCameraProxy.getDegrees_for_pre())));
            }
            this.colorChangeHelper.toChangeColor();
        }
        super.postDetected(bArr, i2, i3);
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void refreshActionViews(int i2) {
        super.refreshActionViews(i2);
        this.verifyFaceNormalAnim.startFaceAnimation(this.animationView, i2);
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void refreshCountDownView(int i2) {
        String format = new DecimalFormat("00").format(i2);
        this.countDownView.setText(format + "s");
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void refreshProgressView(int i2, int i3) {
        this.progressView.setValue((this.progressView.getMaxValue() * i2) / i3);
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void resetProgressView() {
        super.resetProgressView();
        this.progressView.reset();
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void resetVerify() {
        if (this.verifyState.get() == 6) {
            resetColorful();
        }
        super.resetVerify();
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    public void showDetectView() {
        super.showDetectView();
        this.countDownView.setVisibility(0);
        this.animationContainer.setVisibility(0);
        this.verifyFaceNormalAnim.resetAnimation(this.animationView);
    }

    @Override // com.jd.aips.verify.face.activity.FaceVerifyActivity
    protected void showLoadingView() {
        this.loadingView.setVisibility(0);
    }
}
