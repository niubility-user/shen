package com.jd.aips.verify.face.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.jd.aips.common.utils.ImageUtils;
import com.jd.aips.detect.face.FaceDetector;
import com.jd.aips.detect.face.bean.FaceConfig;
import com.jd.aips.detect.face.bean.FaceDataInfo;
import com.jd.aips.detect.face.bean.FaceImageData;
import com.jd.aips.detect.face.camera.FsCameraProxy;
import com.jd.aips.detect.face.camera.FsCameraTextureView;
import com.jd.aips.verify.api.ResultData;
import com.jd.aips.verify.api.ResultDataWrapper;
import com.jd.aips.verify.config.VerificationSdk;
import com.jd.aips.verify.face.FaceVerifyConfig;
import com.jd.aips.verify.face.FaceVerifyEngine;
import com.jd.aips.verify.face.FaceVerifyParams;
import com.jd.aips.verify.face.FaceVerifySession;
import com.jd.aips.verify.face.FaceVerifyTracker;
import com.jd.aips.verify.face.api.UploadVerifyRecordApi;
import com.jd.aips.verify.face.biz.CameraChangeHelper;
import com.jd.aips.verify.face.biz.ExposureChangeHelper;
import com.jd.aips.verify.face.biz.FaceConfigHelper;
import com.jd.aips.verify.face.biz.TipTextHelper;
import com.jd.aips.verify.face.loader.FaceVerifyLoader;
import com.jd.aips.verify.face.service.AysImgIntentService;
import com.jd.aips.verify.face.utils.BrightnessTools;
import com.jd.aips.verify.identity.R;
import com.jd.aips.verify.ui.ChoiceDialog;
import com.jd.aips.verify.ui.PromptDialog;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes12.dex */
public abstract class FaceVerifyActivity extends AppCompatActivity implements FaceDetector.DetectCallback, Camera.PreviewCallback {
    protected static final int CHANGE_EXPOSURE_STATE_COMPLETE = 3;
    protected static final int CHANGE_EXPOSURE_STATE_OFF = 2;
    protected static final int CHANGE_EXPOSURE_STATE_ON = 1;
    protected static final int CHANGE_EXPOSURE_STATE_ORIGINAL = 0;
    protected static final int CHANGE_RESOLUTION_STATE_COMPLETED = 3;
    protected static final int CHANGE_RESOLUTION_STATE_DOING = 1;
    protected static final int CHANGE_RESOLUTION_STATE_DONE = 2;
    protected static final int CHANGE_RESOLUTION_STATE_ORIGINAL = 0;
    public static final String DETECT_THREAD_NAME = "FACE_DETECT";
    static final int DIALOG_ENV_RISK = 4;
    static final int DIALOG_SINGLE = 1;
    static final int DIALOG_TO_LINK = 3;
    static final int DIALOG_TRY_AGAIN = 2;
    static final int MAIN_MSG_ACTION_COUNT_DOWN = 910;
    static final int MAIN_MSG_CHANGE_EXPOSURE_COMPLETE = 300;
    static final int MAIN_MSG_CHANGE_RESOLUTION_COMPLETE = 200;
    static final int MAIN_MSG_CHANGE_RESOLUTION_COUNT_DOWN = 920;
    static final int MAIN_MSG_DETECTED = 700;
    static final int MAIN_MSG_DETECT_ACTION_BREAK = 120;
    static final int MAIN_MSG_DETECT_ACTION_CHANGE = 110;
    static final int MAIN_MSG_DETECT_COLORFUL_BREAK = 121;
    static final int MAIN_MSG_DETECT_COUNT_DOWN_START = 900;
    static final int MAIN_MSG_DETECT_COUNT_DOWN_TICK = 901;
    static final int MAIN_MSG_DETECT_INIT_FAILED = 100;
    static final int MAIN_MSG_DETECT_SUCCESS = 190;
    static final int MAIN_MSG_DETECT_TIPS_TEXT = 130;
    static final int MAIN_MSG_REQ_VERIFY = 800;
    static final int MAIN_MSG_VERIFY_FINISH = 820;
    static final int MAIN_MSG_VERIFY_SUCCESS = 810;
    protected static final int VERIFY_STATE_CHANGE_EXPOSURE = 8;
    protected static final int VERIFY_STATE_CHANGE_RESOLUTION = 7;
    protected static final int VERIFY_STATE_COLORFUL = 6;
    protected static final int VERIFY_STATE_COMPLETED = 30;
    protected static final int VERIFY_STATE_DETECTED_FACE = 5;
    protected static final int VERIFY_STATE_DETECT_CONFIG = 2;
    protected static final int VERIFY_STATE_DETECT_FACE = 3;
    protected static final int VERIFY_STATE_DETECT_FACE_ACTION = 4;
    protected static final int VERIFY_STATE_DETECT_FAILED = 98;
    protected static final int VERIFY_STATE_IDLE = 100;
    protected static final int VERIFY_STATE_INITIAL = 1;
    protected static final int VERIFY_STATE_ORIGINAL = 0;
    protected static final int VERIFY_STATE_PAUSE = 99;
    protected static final int VERIFY_STATE_REQUEST = 20;
    protected static final int VERIFY_STATE_VERIFIED = 21;
    public static final String WORK_THREAD_NAME = "WORK";
    static HandlerThread detectThread;
    protected BrightnessTools brightnessTools;
    protected CameraChangeHelper cameraChangeHelper;
    protected FsCameraTextureView cameraTextureView;
    protected ConstraintLayout contentView;
    protected volatile Dialog dialog;
    protected FaceVerifyEngine engine;
    protected ExposureChangeHelper exposureChangeHelper;
    protected FsCameraProxy mCameraProxy;
    protected View mainContentView;
    protected LinearLayout previewContainer;
    protected ImageView previewCover;
    protected FaceVerifySession session;
    protected TextView tipView;
    protected FaceVerifyTracker verifyTracker;
    protected FaceDetector faceDetector = FaceDetector.getInstance();
    protected int sceneConfig = 1;
    protected int totalDetectTime = 10;
    protected boolean changeResolutionEnable = false;
    protected int hookTime = 2;
    protected boolean delayFrameEnable = false;
    protected int delayFrameSize = 5;
    protected int delayFrameCount = 0;
    protected boolean changeExposureEnable = false;
    protected int faceActionsRulesPosition = 0;
    protected volatile int totalStep = 1;
    protected volatile int detectFaceCurrentStep = 0;
    protected boolean reportEnable = false;
    protected int reportInterval = 3;
    protected volatile int reportCount = 0;
    protected volatile long lastReportTime = -1;
    protected AtomicInteger verifyState = new AtomicInteger(0);
    protected AtomicInteger changeResolutionState = new AtomicInteger(0);
    protected AtomicInteger changeExposureState = new AtomicInteger(0);
    protected volatile byte[] lastPreviewFrameData = null;
    protected volatile int currentActionType = 1000;
    Handler mainHandler = new Handler(Looper.getMainLooper(), new MainHandlerCallback());
    final int DETECT_MSG_INITIAL_DETECTOR = 100;
    final int DETECT_MSG_CONFIG_DETECTOR = 200;
    final int DETECT_MSG_DETECT_FRAME = 300;
    final int DETECT_MSG_CHANGE_EXPOSURE_ON = 400;
    final int DETECT_MSG_CHANGE_EXPOSURE_OFF = 401;
    Handler detectHandler = null;
    final int WORK_MSG_REPORT = 100;
    HandlerThread workThread = null;
    Handler workHandler = null;
    LoaderManager.LoaderCallbacks<Bundle> verifyCallback = new LoaderManager.LoaderCallbacks<Bundle>() { // from class: com.jd.aips.verify.face.activity.FaceVerifyActivity.3
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<Bundle> onCreateLoader(int i2, Bundle bundle) {
            return new FaceVerifyLoader(FaceVerifyActivity.this.getApplicationContext(), FaceVerifyActivity.this.session);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public /* bridge */ /* synthetic */ void onLoadFinished(Loader<Bundle> loader, Bundle bundle) {
            onLoadFinished2((Loader) loader, bundle);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<Bundle> loader) {
        }

        /* renamed from: onLoadFinished  reason: avoid collision after fix types in other method */
        public void onLoadFinished2(Loader loader, Bundle bundle) {
            if (FaceVerifyActivity.this.verifyState.compareAndSet(20, 21)) {
                FaceVerifyActivity.this.processVerifyResult(bundle);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class DetectHandlerCallback implements Handler.Callback {
        DetectHandlerCallback() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i2 = FaceVerifyActivity.this.verifyState.get();
            int i3 = message.what;
            boolean z = true;
            if (i3 != 100) {
                if (i3 != 200) {
                    if (i3 == 300) {
                        Object obj = message.obj;
                        if (obj != null) {
                            byte[] bArr = (byte[]) obj;
                            int i4 = message.arg1;
                            int i5 = message.arg2;
                            if (i2 != 3 && i2 != 4 && i2 != 6) {
                                if (i2 == 8 && FaceVerifyActivity.this.changeExposureState.get() == 1) {
                                    FaceVerifyActivity.this.faceDetector.detect(bArr, i4, i5);
                                } else if (i2 == 7 && FaceVerifyActivity.this.changeResolutionState.get() == 2) {
                                    boolean checkSizeChange = FaceVerifyActivity.this.cameraChangeHelper.checkSizeChange(bArr);
                                    FaceVerifyActivity faceVerifyActivity = FaceVerifyActivity.this;
                                    if (faceVerifyActivity.delayFrameEnable) {
                                        int i6 = faceVerifyActivity.delayFrameCount + 1;
                                        faceVerifyActivity.delayFrameCount = i6;
                                        if (i6 < faceVerifyActivity.delayFrameSize) {
                                            z = false;
                                        }
                                    }
                                    if (checkSizeChange && z) {
                                        int i7 = faceVerifyActivity.delayFrameCount;
                                        faceVerifyActivity.changeResolutionState.set(3);
                                        FaceVerifyActivity faceVerifyActivity2 = FaceVerifyActivity.this;
                                        boolean cacheResizeImg = faceVerifyActivity2.session.cacheResizeImg(bArr, i4, i5, faceVerifyActivity2.mCameraProxy.getDegrees_for_pre());
                                        FaceVerifyActivity.this.verifyTracker.trackChangeResolutionSuccess("change resolution for hook is success: " + cacheResizeImg);
                                        Handler handler = FaceVerifyActivity.this.mainHandler;
                                        handler.sendMessage(handler.obtainMessage(200));
                                    }
                                }
                            } else {
                                FaceVerifyActivity.this.session.cacheFaceInfos(FaceVerifyActivity.this.faceDetector.detect(bArr, i4, i5));
                            }
                            FaceVerifyActivity.this.postDetected(bArr, i4, i5);
                        }
                    } else if (i3 != 400) {
                        if (i3 == 401 && i2 == 8 && FaceVerifyActivity.this.changeExposureState.compareAndSet(1, 2)) {
                            FaceVerifyActivity.this.faceDetector.setConcatControl(0);
                        }
                    } else if (i2 == 8 && FaceVerifyActivity.this.changeExposureState.compareAndSet(0, 1)) {
                        FaceVerifyActivity.this.faceDetector.setConcatControl(1);
                        FaceVerifyActivity.this.verifyTracker.trackChangeExposureBegin();
                    }
                } else if (FaceVerifyActivity.this.verifyState.compareAndSet(2, 3)) {
                    FaceVerifyActivity faceVerifyActivity3 = FaceVerifyActivity.this;
                    FaceConfig buildFaceConfig = FaceConfigHelper.buildFaceConfig((FaceVerifyConfig) ((FaceVerifyParams) faceVerifyActivity3.session.verifyParams).verifyConfig, faceVerifyActivity3.mCameraProxy.getDegrees_for_pre(), FaceVerifyActivity.this.faceActionsRulesPosition, false);
                    FaceVerifyActivity faceVerifyActivity4 = FaceVerifyActivity.this;
                    faceVerifyActivity4.faceDetector.resume(buildFaceConfig, faceVerifyActivity4.session.verifyToken);
                }
            } else if (i2 == 0) {
                FaceVerifyActivity faceVerifyActivity5 = FaceVerifyActivity.this;
                boolean init = faceVerifyActivity5.faceDetector.init(faceVerifyActivity5.getApplicationContext());
                if (FaceVerifyActivity.this.verifyState.compareAndSet(0, 1) && !init) {
                    Handler handler2 = FaceVerifyActivity.this.mainHandler;
                    handler2.sendMessage(handler2.obtainMessage(100));
                }
            }
            return false;
        }
    }

    /* loaded from: classes12.dex */
    class MainHandlerCallback implements Handler.Callback {
        private volatile long detectStartTime = -1;

        MainHandlerCallback() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            Object obj;
            switch (message.what) {
                case 100:
                    FaceVerifyActivity.this.verifySoInitFail();
                    return false;
                case 110:
                    FaceVerifyActivity faceVerifyActivity = FaceVerifyActivity.this;
                    faceVerifyActivity.refreshActionViews(faceVerifyActivity.currentActionType);
                    return false;
                case 120:
                case 121:
                case 190:
                    FaceVerifyActivity.this.performDetect(message);
                    return false;
                case 130:
                    Object obj2 = message.obj;
                    if (obj2 != null) {
                        String obj3 = obj2.toString();
                        if (TextUtils.isEmpty(obj3)) {
                            return false;
                        }
                        FaceVerifyActivity.this.tipView.setText(obj3);
                        return false;
                    }
                    return false;
                case 200:
                    FaceVerifyActivity.this.cancelChangeResolutionCountDown();
                    if (FaceVerifyActivity.this.verifyState.compareAndSet(7, 5)) {
                        FaceVerifyActivity faceVerifyActivity2 = FaceVerifyActivity.this;
                        if (faceVerifyActivity2.changeExposureEnable) {
                            faceVerifyActivity2.performChangeExposure();
                            return false;
                        }
                        faceVerifyActivity2.resetResolution();
                        FaceVerifyActivity.this.mainHandler.sendEmptyMessage(800);
                        return false;
                    }
                    FaceVerifyActivity.this.resetResolution();
                    return false;
                case 300:
                    FaceVerifyActivity.this.resetExposure();
                    if (FaceVerifyActivity.this.verifyState.compareAndSet(8, 5)) {
                        FaceVerifyActivity faceVerifyActivity3 = FaceVerifyActivity.this;
                        if (faceVerifyActivity3.changeResolutionEnable) {
                            faceVerifyActivity3.resetResolution();
                        }
                        FaceVerifyActivity.this.mainHandler.sendEmptyMessage(800);
                        return false;
                    }
                    return false;
                case 700:
                    Camera camera = FaceVerifyActivity.this.mCameraProxy.getCamera();
                    if (camera == null || (obj = message.obj) == null) {
                        return false;
                    }
                    camera.addCallbackBuffer((byte[]) obj);
                    return false;
                case 800:
                case 810:
                case 820:
                    FaceVerifyActivity.this.performVerify(message);
                    return false;
                case 900:
                    FaceVerifyActivity faceVerifyActivity4 = FaceVerifyActivity.this;
                    faceVerifyActivity4.refreshCountDownView(faceVerifyActivity4.totalDetectTime);
                    this.detectStartTime = SystemClock.elapsedRealtime();
                    FaceVerifyActivity.this.mainHandler.sendEmptyMessageDelayed(901, 1000L);
                    return false;
                case 901:
                    if (this.detectStartTime > 0) {
                        int elapsedRealtime = (int) ((SystemClock.elapsedRealtime() - this.detectStartTime) / 1000);
                        FaceVerifyActivity faceVerifyActivity5 = FaceVerifyActivity.this;
                        int i2 = faceVerifyActivity5.totalDetectTime;
                        if (elapsedRealtime <= i2) {
                            faceVerifyActivity5.refreshCountDownView(i2 - elapsedRealtime);
                            FaceVerifyActivity.this.mainHandler.sendEmptyMessageDelayed(901, 1000L);
                            return false;
                        }
                        this.detectStartTime = -1L;
                        FaceVerifyActivity.this.verifyState.set(98);
                        FaceVerifyActivity.this.performOnStopDetected();
                        if (FaceVerifyActivity.this.currentActionType != 1000) {
                            FaceVerifyActivity faceVerifyActivity6 = FaceVerifyActivity.this;
                            faceVerifyActivity6.verifyTracker.trackActionFail(faceVerifyActivity6.currentActionType);
                        }
                        FaceVerifyActivity.this.verifyTracker.trackDetectTimeout();
                        FaceVerifyActivity faceVerifyActivity7 = FaceVerifyActivity.this;
                        faceVerifyActivity7.showDialog(faceVerifyActivity7.getDialogStyleForRetryCount(), "\u5237\u8138\u8d85\u65f6\uff0c\u8bf7\u518d\u8bd5\u4e00\u6b21");
                        return false;
                    }
                    return false;
                case 910:
                    if (FaceVerifyActivity.this.currentActionType != 1000) {
                        FaceVerifyActivity faceVerifyActivity8 = FaceVerifyActivity.this;
                        faceVerifyActivity8.verifyTracker.trackActionFail(faceVerifyActivity8.currentActionType);
                        return false;
                    }
                    return false;
                case 920:
                    if (FaceVerifyActivity.this.verifyState.get() != 7 || FaceVerifyActivity.this.changeResolutionState.get() == 3) {
                        return false;
                    }
                    FaceVerifyActivity.this.verifyTracker.trackChangeResolutionTimeout("\u8d85\u65f6");
                    FaceVerifyActivity.this.changeResolutionState.set(3);
                    Handler handler = FaceVerifyActivity.this.mainHandler;
                    handler.sendMessage(handler.obtainMessage(200));
                    return false;
                default:
                    FaceVerifyActivity.this.performOthers(message);
                    return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class WorkHandlerCallback implements Handler.Callback {
        WorkHandlerCallback() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            byte[] bArr;
            if (message.what == 100 && (bArr = (byte[]) message.obj) != null) {
                FaceVerifyActivity.this.reportCount++;
                FaceVerifyActivity.this.doReport(bArr, message.arg1, message.arg2);
                return false;
            }
            return false;
        }
    }

    private boolean isDetecting() {
        int i2 = this.verifyState.get();
        return i2 == 3 || i2 == 4 || i2 == 6 || i2 == 7 || i2 == 8;
    }

    private void processError(int i2, String str) {
        if (i2 == 1160 || i2 == 1159 || i2 == 1161 || i2 == 50 || i2 == 1187 || i2 == 1154 || i2 == 108 || i2 == 1166 || i2 == 1145 || i2 == 1143 || i2 == 1144 || i2 == 1186) {
            showDialog(1, str);
        } else if (i2 == 1183) {
            showDialog(4, str);
        } else if (i2 == 1103) {
            showDialog(3, str);
        } else {
            showDialog(getDialogStyleForRetryCount(), str);
        }
    }

    private void showJumpDialog(String str) {
        this.dialog = new ChoiceDialog(this, str, "\u66f4\u591a\u4fe1\u606f", new ChoiceDialog.ChoiceDelegate() { // from class: com.jd.aips.verify.face.activity.FaceVerifyActivity.5
            @Override // com.jd.aips.verify.ui.ChoiceDialog.ChoiceDelegate
            public void onNegative() {
                FaceVerifyActivity.this.onDialogDismiss(false);
                FaceVerifyActivity.this.verifyUserCancel();
            }

            @Override // com.jd.aips.verify.ui.ChoiceDialog.ChoiceDelegate
            public void onPositive() {
                FaceVerifyActivity.this.onDialogDismiss(false);
                FaceVerifyActivity.this.verifyState.set(100);
                FaceVerifyActivity faceVerifyActivity = FaceVerifyActivity.this;
                VerifyWebActivity.intentTo(faceVerifyActivity, ((FaceVerifyConfig) ((FaceVerifyParams) faceVerifyActivity.session.verifyParams).verifyConfig).verificationSdk.config.new_guide_link);
                FaceVerifyActivity.this.verifyTracker.trackClickMore();
            }
        });
        this.dialog.show();
    }

    private void showPromptDialog(String str) {
        this.dialog = new PromptDialog(this, str, new PromptDialog.PromptDelegate() { // from class: com.jd.aips.verify.face.activity.FaceVerifyActivity.7
            @Override // com.jd.aips.verify.ui.PromptDialog.PromptDelegate
            public void onConfirm() {
                FaceVerifyActivity.this.onDialogDismiss(false);
                FaceVerifyActivity.this.verifyFail("\u6821\u9a8c\u5931\u8d25\uff0c\u8bf7\u8fd4\u56de\u4e0a\u5c42\u91cd\u8bd5");
            }
        });
        this.dialog.show();
    }

    private void showRetryDialog(String str) {
        this.dialog = new ChoiceDialog(this, str, "\u518d\u8bd5\u4e00\u6b21", new ChoiceDialog.ChoiceDelegate() { // from class: com.jd.aips.verify.face.activity.FaceVerifyActivity.4
            @Override // com.jd.aips.verify.ui.ChoiceDialog.ChoiceDelegate
            public void onNegative() {
                FaceVerifyActivity.this.onDialogDismiss(false);
                FaceVerifyActivity.this.verifyUserCancel();
            }

            @Override // com.jd.aips.verify.ui.ChoiceDialog.ChoiceDelegate
            public void onPositive() {
                FaceVerifyActivity.this.onDialogDismiss(true);
                FaceVerifyActivity.this.verifyTracker.trackTryAgain();
                FaceVerifyActivity.this.performDetectTryAgain();
            }
        });
        this.dialog.show();
    }

    private void showWarnDialog(String str) {
        this.dialog = new PromptDialog(this, str, new PromptDialog.PromptDelegate() { // from class: com.jd.aips.verify.face.activity.FaceVerifyActivity.6
            @Override // com.jd.aips.verify.ui.PromptDialog.PromptDelegate
            public void onConfirm() {
                FaceVerifyActivity.this.onDialogDismiss(false);
                FaceVerifyActivity.this.verifyEnvironmentRisk();
            }
        });
        this.dialog.show();
    }

    private void startHandlers() {
        startDetectHandler();
        startWorkHandler();
    }

    private void toComplete() {
        this.verifyState.set(30);
        this.faceDetector.release();
        finish();
    }

    void cancelActionCountDown() {
        Handler handler = this.mainHandler;
        if (handler != null) {
            handler.removeMessages(910);
        }
    }

    void cancelChangeResolutionCountDown() {
        Handler handler = this.mainHandler;
        if (handler != null) {
            handler.removeMessages(920);
        }
    }

    void cancelDetectCountDown() {
        Handler handler = this.mainHandler;
        if (handler != null) {
            handler.removeMessages(901);
        }
    }

    void cancelReport() {
        Handler handler = this.workHandler;
        if (handler != null) {
            handler.removeMessages(100);
        }
    }

    void destroyVerify() {
        getSupportLoaderManager().destroyLoader(getLoaderId());
    }

    protected void doReport(byte[] bArr, int i2, int i3) {
        if (this.session != null) {
            UploadVerifyRecordApi.uploadThumbnailFlowRecord(getApplicationContext(), this.session, this.reportCount, bArr, i2, i3, this.mCameraProxy.getDegrees_for_pre(), "");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0020 A[ExcHandler: Exception -> 0x0020, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int getActionDetectTime(int r1, com.jd.aips.verify.config.FaceSdk.Config r2) {
        /*
            r0 = this;
            switch(r1) {
                case 1002: goto L19;
                case 1003: goto L12;
                case 1004: goto Lb;
                case 1005: goto L4;
                default: goto L3;
            }
        L3:
            goto L20
        L4:
            java.lang.String r1 = r2.face_action_timeout_1005     // Catch: java.lang.Exception -> L20
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Exception -> L20
            goto L22
        Lb:
            java.lang.String r1 = r2.face_action_timeout_1004     // Catch: java.lang.Exception -> L20
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Exception -> L20
            goto L22
        L12:
            java.lang.String r1 = r2.face_action_timeout_1003     // Catch: java.lang.Exception -> L20
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Exception -> L20
            goto L22
        L19:
            java.lang.String r1 = r2.face_action_timeout_1002     // Catch: java.lang.Exception -> L20
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Exception -> L20
            goto L22
        L20:
            r1 = 10
        L22:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.verify.face.activity.FaceVerifyActivity.getActionDetectTime(int, com.jd.aips.verify.config.FaceSdk$Config):int");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getDialogStyleForRetryCount() {
        return this.session.validateRetry() ? 2 : 1;
    }

    protected abstract int getLoaderId();

    /* JADX INFO: Access modifiers changed from: protected */
    public void hideCover() {
        if (this.previewCover.getVisibility() == 0) {
            this.previewCover.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void hideDetectView() {
        this.tipView.setVisibility(4);
        this.tipView.setText("");
    }

    protected void hideLoadingView() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean initParams() {
        FaceVerifyEngine faceVerifyEngine = FaceVerifyEngine.getInstance();
        this.engine = faceVerifyEngine;
        FaceVerifySession session = faceVerifyEngine.getSession();
        this.session = session;
        if (session != null && session.verifyParams != 0) {
            this.verifyTracker = (FaceVerifyTracker) session.verifyTracker;
            session.resetAllCaches();
            this.faceDetector.setDetectCallback(this);
            VerificationSdk.Config config = ((FaceVerifyConfig) ((FaceVerifyParams) this.session.verifyParams).verifyConfig).verificationSdk.config;
            int i2 = config.scene_config;
            this.sceneConfig = i2;
            if (i2 == 0) {
                this.sceneConfig = 1;
            }
            int i3 = config.sdk_face_detection_timeout;
            this.totalDetectTime = i3;
            if (i3 == 0) {
                this.totalDetectTime = 10;
            }
            boolean z = config.sdk_verification_report_enable;
            this.reportEnable = z;
            if (z) {
                int i4 = config.sdk_verification_report_thumbnail_interval;
                this.reportInterval = i4;
                if (i4 <= 0) {
                    this.reportInterval = 3;
                }
            }
            boolean z2 = config.resolutionImageFlag;
            this.changeResolutionEnable = z2;
            if (z2) {
                int i5 = config.hook_time;
                this.hookTime = i5;
                if (i5 == 0) {
                    this.hookTime = 2;
                }
                this.cameraChangeHelper = new CameraChangeHelper();
                boolean z3 = config.delay_frame_flag;
                this.delayFrameEnable = z3;
                if (z3) {
                    int i6 = config.delay_frame_size;
                    this.delayFrameSize = i6;
                    if (i6 == 0) {
                        this.delayFrameSize = 5;
                    }
                }
            }
            boolean z4 = config.face_exposure_flag;
            this.changeExposureEnable = z4;
            if (z4) {
                this.exposureChangeHelper = new ExposureChangeHelper(this, config.face_exposure_time, config.face_exposure_time_interval, config.face_exposure_rule);
            }
            this.brightnessTools = new BrightnessTools();
            return true;
        }
        this.engine.callbackFinishSDK(5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1a\u53c2\u6570\u4e3a\u7a7a\uff01");
        return false;
    }

    protected abstract void initViews();

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        verifyUserCancel();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (!initParams()) {
            finish();
            return;
        }
        initViews();
        FsCameraProxy cameraProxy = this.cameraTextureView.getCameraProxy();
        this.mCameraProxy = cameraProxy;
        cameraProxy.setPreviewCallback(this);
        startHandlers();
        this.verifyTracker.trackEnter();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.verifyState.set(30);
        FaceVerifySession faceVerifySession = this.session;
        if (faceVerifySession != null && faceVerifySession.verifyParams != 0) {
            FsCameraProxy fsCameraProxy = this.mCameraProxy;
            if (fsCameraProxy != null) {
                fsCameraProxy.releaseCamera();
            }
            this.mainHandler.removeCallbacksAndMessages(null);
            this.engine.callbackFinishSDK(this.session.getResultCode(), this.session.getResultMessage());
            this.session.resetAllCaches();
        }
        quitDetectHandler();
        quitWorkHandler();
        super.onDestroy();
    }

    @Override // com.jd.aips.detect.face.FaceDetector.DetectCallback
    public void onDetectCallback(int i2, @Nullable FaceImageData[] faceImageDataArr, int i3, @Nullable FaceDataInfo[] faceDataInfoArr) {
        if (isDetecting()) {
            int i4 = this.verifyState.get();
            if (i2 == 1001) {
                this.verifyState.set(5);
                this.session.cacheDetectResult(faceImageDataArr, faceDataInfoArr);
                this.session.cacheFrameInfo(this.faceDetector.getFrameInfo());
                if (this.currentActionType != 1000) {
                    this.verifyTracker.trackActionEnd(this.currentActionType);
                }
                this.currentActionType = 1000;
                this.verifyTracker.trackDetectSuccess();
                this.mainHandler.sendEmptyMessage(190);
            } else if (i2 == 1004) {
                this.session.cacheFrameInfo(this.faceDetector.getFrameInfo());
                if (this.currentActionType == 1000 && this.verifyState.compareAndSet(3, 4)) {
                    this.verifyTracker.trackSilenceEnd();
                } else if (this.currentActionType != 1000 && i4 == 4) {
                    this.verifyTracker.trackActionEnd(this.currentActionType);
                }
                if (i3 != 1000 && i3 != this.currentActionType) {
                    this.verifyTracker.trackActionBegin(i3);
                    this.currentActionType = i3;
                    this.session.resetFaceInfosCache();
                    this.mainHandler.sendEmptyMessage(110);
                }
            } else {
                if (i2 == 1099 || i2 == 1098) {
                    String str = null;
                    if (i2 == 1099 && faceImageDataArr != null && faceImageDataArr.length > 0) {
                        FaceImageData faceImageData = faceImageDataArr[0];
                        this.session.cacheExposureDetectResult(faceImageData);
                        str = String.format("{%d, %d}", Integer.valueOf(faceImageData.width), Integer.valueOf(faceImageData.height));
                    }
                    this.changeExposureState.set(3);
                    Handler handler = this.mainHandler;
                    handler.sendMessage(handler.obtainMessage(300));
                    if (str == null) {
                        str = "{0, 0}";
                    }
                    this.verifyTracker.trackChangeExposureEnd(str, this.exposureChangeHelper.getExposureRule(), this.exposureChangeHelper.getExposureCompensationValues());
                } else if (i4 == 4 && i2 == 1015) {
                    this.verifyState.set(98);
                    this.verifyTracker.trackActionBreak(this.currentActionType);
                    Handler handler2 = this.mainHandler;
                    handler2.sendMessage(handler2.obtainMessage(120));
                } else if (i4 == 6 && (i2 == 1005 || i2 == 1009 || i2 == 1015)) {
                    this.verifyState.set(98);
                    boolean z = i2 == 1009;
                    this.verifyTracker.trackChangeColorBreak(z);
                    String string = getString(z ? R.string.fcvf_prompt_face_too_far : R.string.fcvf_prompt_face_out);
                    Handler handler3 = this.mainHandler;
                    handler3.sendMessage(handler3.obtainMessage(121, string));
                }
            }
            if (i4 == 4 && this.currentActionType != 1000 && i2 == 1011) {
                String tipTextByActionType = TipTextHelper.getTipTextByActionType(this.currentActionType);
                if (TextUtils.isEmpty(tipTextByActionType)) {
                    return;
                }
                Handler handler4 = this.mainHandler;
                handler4.sendMessage(handler4.obtainMessage(130, tipTextByActionType));
                return;
            }
            String silentTipTextByCallbackType = TipTextHelper.getSilentTipTextByCallbackType(i2);
            if (TextUtils.isEmpty(silentTipTextByCallbackType)) {
                return;
            }
            Handler handler5 = this.mainHandler;
            handler5.sendMessage(handler5.obtainMessage(130, silentTipTextByCallbackType));
        }
    }

    @Override // com.jd.aips.detect.face.FaceDetector.DetectCallback
    public void onDetectError(int i2, String str) {
        if (i2 == 2001) {
            verifyPermissionsFinish();
        } else if (i2 != 2002) {
            verifySoInitFail();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDialogDismiss(boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        if (this.verifyState.get() != 30) {
            this.verifyState.set(99);
        }
        this.previewContainer.removeView(this.cameraTextureView);
        this.brightnessTools.resetBrightness(this);
        resetVerify();
        super.onPause();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    @Override // android.hardware.Camera.PreviewCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onPreviewFrame(byte[] r9, android.hardware.Camera r10) {
        /*
            r8 = this;
            java.util.concurrent.atomic.AtomicInteger r0 = r8.verifyState
            int r0 = r0.get()
            java.util.concurrent.atomic.AtomicInteger r1 = r8.verifyState
            r2 = 1
            r3 = 2
            boolean r1 = r1.compareAndSet(r2, r3)
            r3 = 0
            if (r1 == 0) goto L19
            android.os.Handler r0 = r8.detectHandler
            r1 = 200(0xc8, float:2.8E-43)
            r0.sendEmptyMessage(r1)
            goto L67
        L19:
            r1 = 3
            if (r0 < r1) goto L67
            r1 = 20
            if (r0 >= r1) goto L67
            r8.lastPreviewFrameData = r9
            android.hardware.Camera$Parameters r0 = r10.getParameters()     // Catch: java.lang.Exception -> L67
            android.hardware.Camera$Size r0 = r0.getPreviewSize()     // Catch: java.lang.Exception -> L67
            int r1 = r0.width     // Catch: java.lang.Exception -> L67
            int r0 = r0.height     // Catch: java.lang.Exception -> L67
            boolean r4 = r8.isDetecting()     // Catch: java.lang.Exception -> L67
            if (r4 == 0) goto L67
            r8.toDetect(r9, r1, r0)     // Catch: java.lang.Exception -> L65
            boolean r3 = r8.reportEnable     // Catch: java.lang.Exception -> L65
            if (r3 == 0) goto L68
            long r3 = r8.lastReportTime     // Catch: java.lang.Exception -> L65
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L53
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Exception -> L65
            long r5 = r8.lastReportTime     // Catch: java.lang.Exception -> L65
            long r3 = r3 - r5
            int r5 = r8.reportInterval     // Catch: java.lang.Exception -> L65
            int r5 = r5 * 1000
            long r5 = (long) r5     // Catch: java.lang.Exception -> L65
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L68
        L53:
            android.os.Handler r3 = r8.workHandler     // Catch: java.lang.Exception -> L65
            r4 = 100
            android.os.Message r0 = r3.obtainMessage(r4, r1, r0, r9)     // Catch: java.lang.Exception -> L65
            r3.sendMessage(r0)     // Catch: java.lang.Exception -> L65
            long r0 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Exception -> L65
            r8.lastReportTime = r0     // Catch: java.lang.Exception -> L65
            goto L68
        L65:
            goto L68
        L67:
            r2 = 0
        L68:
            if (r2 != 0) goto L6d
            r10.addCallbackBuffer(r9)
        L6d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.verify.face.activity.FaceVerifyActivity.onPreviewFrame(byte[], android.hardware.Camera):void");
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (this.cameraTextureView != null) {
            for (int i3 : iArr) {
                if (i3 == -1) {
                    verifyPermissionsFinish();
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            int i2 = this.verifyState.get();
            if (i2 == 30) {
                toComplete();
                return;
            }
            this.previewContainer.addView(this.cameraTextureView);
            this.brightnessTools.increaseBrightness(this);
            this.session.setFailedLighting(true);
            if (i2 == 0) {
                performDetectInitAndStart();
            } else if (i2 == 100) {
                performDetectTryAgain();
            } else {
                hideLoadingView();
                if (this.session.validateRetry()) {
                    showDialog(2, getString(R.string.fcvf_prompt_paused));
                } else {
                    verifyFail("\u6821\u9a8c\u5931\u8d25\uff0c\u8bf7\u8fd4\u56de\u4e0a\u5c42\u91cd\u8bd5");
                }
            }
        } catch (Exception unused) {
            verifyPermissionsFinish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void performChangeExposure() {
        if (this.verifyState.compareAndSet(5, 8) && this.changeExposureState.get() == 0) {
            this.exposureChangeHelper.changeExposure(this.mCameraProxy, new ExposureChangeHelper.ExposureChangeCallback() { // from class: com.jd.aips.verify.face.activity.FaceVerifyActivity.2
                @Override // com.jd.aips.verify.face.biz.ExposureChangeHelper.ExposureChangeCallback
                public void onCompleted() {
                    if (FaceVerifyActivity.this.verifyState.get() == 8 && FaceVerifyActivity.this.changeExposureState.get() == 1) {
                        Handler handler = FaceVerifyActivity.this.detectHandler;
                        handler.sendMessage(handler.obtainMessage(401));
                    }
                }
            });
            Handler handler = this.detectHandler;
            handler.sendMessage(handler.obtainMessage(400));
            return;
        }
        Handler handler2 = this.mainHandler;
        handler2.sendMessage(handler2.obtainMessage(300));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void performChangeResolution() {
        if (this.verifyState.compareAndSet(5, 7)) {
            boolean z = false;
            if (this.changeResolutionState.compareAndSet(0, 1)) {
                resetChangeResolutionCountDown();
                final int i2 = this.mCameraProxy.getmPreviewWidth();
                final int i3 = this.mCameraProxy.getmPreviewHeight();
                this.verifyTracker.trackChangeResolution(i2, i3);
                try {
                    z = this.cameraChangeHelper.changeCameraPreviewSize(this.mCameraProxy.getCamera(), this, (FaceVerifyConfig) ((FaceVerifyParams) this.session.verifyParams).verifyConfig, i2, i3, new CameraChangeHelper.CameraPreviewChangeInter() { // from class: com.jd.aips.verify.face.activity.FaceVerifyActivity.1
                        @Override // com.jd.aips.verify.face.biz.CameraChangeHelper.CameraPreviewChangeInter
                        public void previewChangeCallback(List<Camera.Size> list, int i4, int i5, String str) {
                            FaceVerifyActivity.this.changeResolutionState.compareAndSet(1, 2);
                            FaceVerifyActivity.this.verifyTracker.trackerSupportList(list, str);
                            FaceVerifyActivity.this.verifyTracker.trackChangeResolutionFinish(i2, i3, i4, i5);
                        }
                    });
                    if (!z) {
                        this.verifyTracker.trackChangeResolutionFailed();
                    }
                } catch (Exception e2) {
                    this.verifyTracker.trackChangeResolutionException(i2, i3, e2);
                }
                if (z) {
                    return;
                }
                this.changeResolutionState.compareAndSet(1, 3);
                Handler handler = this.mainHandler;
                handler.sendMessage(handler.obtainMessage(200));
            }
        }
    }

    protected abstract void performDetect(Message message);

    void performDetectInitAndStart() {
        this.session.resetResult();
        showDetectView();
        this.delayFrameCount = 0;
        this.changeResolutionState.set(0);
        this.changeExposureState.set(0);
        this.currentActionType = 1000;
        resetProgressView();
        resetDetectCountDown();
        if (this.reportEnable) {
            resetReport();
        }
        if (this.verifyState.get() == 0) {
            Handler handler = this.detectHandler;
            handler.sendMessage(handler.obtainMessage(100));
            return;
        }
        this.verifyState.set(1);
    }

    void performDetectTryAgain() {
        resetVerify();
        this.session.resetDetectCaches();
        if (this.faceActionsRulesPosition < ((FaceVerifyConfig) ((FaceVerifyParams) this.session.verifyParams).verifyConfig).faceSdk.config.face_action_rules.size() - 1) {
            this.faceActionsRulesPosition++;
        }
        performDetectInitAndStart();
        Camera camera = this.mCameraProxy.getCamera();
        if (camera == null || this.lastPreviewFrameData == null) {
            return;
        }
        camera.addCallbackBuffer(this.lastPreviewFrameData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void performOnStopDetected() {
        cancelReport();
        cancelDetectCountDown();
        cancelActionCountDown();
        hideDetectView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void performOthers(Message message) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void performTaskMainHandler(int i2, long j2, Object obj) {
        Message obtainMessage = this.mainHandler.obtainMessage(i2);
        obtainMessage.obj = obj;
        this.mainHandler.sendMessageDelayed(obtainMessage, j2);
    }

    protected abstract void performVerify(Message message);

    /* JADX INFO: Access modifiers changed from: protected */
    public void postDetected(@NonNull byte[] bArr, int i2, int i3) {
        Handler handler = this.mainHandler;
        handler.sendMessage(handler.obtainMessage(700, bArr));
    }

    void processVerifyResult(Bundle bundle) {
        String str;
        int i2;
        hideLoadingView();
        this.session.resetResult();
        ResultDataWrapper resultDataWrapper = (ResultDataWrapper) bundle.getSerializable("data");
        if (resultDataWrapper != null) {
            ResultData data = resultDataWrapper.getData();
            if (data != null) {
                str = data.promptMsg;
                String childToken = data.getChildToken();
                if (!TextUtils.isEmpty(childToken)) {
                    this.session.updateVerifyToken(childToken);
                }
                this.session.verifyId = data.getVerifyId();
            } else {
                str = resultDataWrapper.msg;
            }
            i2 = resultDataWrapper.getCode();
            if (i2 == 0) {
                this.verifyTracker.trackVerifySuccess();
                this.mainHandler.sendEmptyMessage(810);
                return;
            }
        } else {
            str = "";
            i2 = -1;
        }
        if (i2 == -1) {
            i2 = bundle.getInt("code", -1);
        }
        this.verifyTracker.trackVerifyFailed(i2);
        if (TextUtils.isEmpty(str)) {
            str = bundle.getString("msg", getString(R.string.fcvf_prompt_system_exception));
        }
        processError(i2, str);
    }

    void quitDetectHandler() {
        Handler handler = this.detectHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.detectHandler = null;
        }
    }

    void quitWorkHandler() {
        Handler handler = this.workHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        HandlerThread handlerThread = this.workThread;
        if (handlerThread != null) {
            try {
                if (Build.VERSION.SDK_INT >= 18) {
                    handlerThread.quitSafely();
                } else {
                    handlerThread.quit();
                }
            } catch (Throwable unused) {
            }
            this.workThread = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void refreshActionViews(int i2) {
        resetActionCountDown(getActionDetectTime(i2, ((FaceVerifyConfig) ((FaceVerifyParams) this.session.verifyParams).verifyConfig).faceSdk.config));
        this.detectFaceCurrentStep++;
        refreshProgressView(this.detectFaceCurrentStep, this.totalStep);
    }

    protected void refreshCountDownView(int i2) {
    }

    protected void refreshProgressView(int i2, int i3) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestVerify() {
        if (this.verifyState.compareAndSet(5, 20)) {
            getSupportLoaderManager().restartLoader(getLoaderId(), null, this.verifyCallback);
        }
    }

    void resetActionCountDown(int i2) {
        Handler handler = this.mainHandler;
        if (handler != null) {
            handler.removeMessages(910);
            this.mainHandler.sendEmptyMessageDelayed(910, i2 * 1000);
        }
    }

    void resetChangeResolutionCountDown() {
        Handler handler = this.mainHandler;
        if (handler != null) {
            handler.removeMessages(920);
            this.mainHandler.sendEmptyMessageDelayed(920, this.hookTime * 1000);
        }
    }

    void resetDetectCountDown() {
        Handler handler = this.mainHandler;
        if (handler != null) {
            handler.removeMessages(901);
            Handler handler2 = this.mainHandler;
            handler2.sendMessage(handler2.obtainMessage(900));
        }
    }

    void resetExposure() {
        if (this.changeExposureState.get() != 0) {
            this.exposureChangeHelper.cancel();
            this.exposureChangeHelper.resumeExposure(this.mCameraProxy);
        }
        this.changeExposureState.set(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resetProgressView() {
        try {
            P p = this.session.verifyParams;
            this.totalStep = ((FaceVerifyConfig) ((FaceVerifyParams) p).verifyConfig).verificationSdk.config.sdk_face_identify_strategy == 1 ? 1 : ((FaceVerifyConfig) ((FaceVerifyParams) p).verifyConfig).faceSdk.config.face_action_rules.get(this.faceActionsRulesPosition).face_actions.size() + 1;
        } catch (Exception unused) {
            this.totalStep = 1;
        }
        this.detectFaceCurrentStep = 0;
    }

    void resetReport() {
        this.reportCount = 0;
        this.lastReportTime = -1L;
    }

    void resetResolution() {
        if (this.changeResolutionState.get() != 0) {
            this.cameraChangeHelper.resumePreviewSize(this.mCameraProxy.getCamera(), this, this.mCameraProxy.getmPreviewWidth(), this.mCameraProxy.getmPreviewHeight());
        }
        this.changeResolutionState.set(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetVerify() {
        hideLoadingView();
        hideCover();
        int i2 = this.verifyState.get();
        if (i2 == 7) {
            resetResolution();
        } else if (i2 == 8) {
            resetExposure();
        }
        destroyVerify();
        this.mainHandler.removeCallbacksAndMessages(null);
        this.detectHandler.removeCallbacksAndMessages(null);
        this.workHandler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showCover() {
        Bitmap rotateBitmap;
        if (this.previewCover.getVisibility() == 0 || (rotateBitmap = ImageUtils.rotateBitmap(ImageUtils.nv21ToBitmap(this, this.lastPreviewFrameData, this.mCameraProxy.getmPreviewWidth(), this.mCameraProxy.getmPreviewHeight()), -this.mCameraProxy.getDegrees_for_pre())) == null) {
            return;
        }
        this.previewCover.setImageBitmap(rotateBitmap);
        this.previewCover.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showDetectView() {
        this.tipView.setVisibility(0);
        this.tipView.setText(R.string.fcvf_prompt_face_screen);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showDialog(int i2, String str) {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
            this.dialog = null;
        }
        if (i2 == 2) {
            showRetryDialog(str);
        } else if (i2 == 3) {
            showJumpDialog(str);
        } else if (i2 != 4) {
            showPromptDialog(str);
        } else {
            showWarnDialog(str);
        }
    }

    protected void showLoadingView() {
    }

    void startDetectHandler() {
        if (detectThread == null) {
            HandlerThread handlerThread = new HandlerThread(DETECT_THREAD_NAME);
            detectThread = handlerThread;
            handlerThread.start();
        }
        if (this.detectHandler == null) {
            this.detectHandler = new Handler(detectThread.getLooper(), new DetectHandlerCallback());
        }
    }

    void startWorkHandler() {
        if (this.workThread == null) {
            this.workThread = new HandlerThread(WORK_THREAD_NAME);
        }
        this.workThread.start();
        if (this.workHandler == null) {
            this.workHandler = new Handler(this.workThread.getLooper(), new WorkHandlerCallback());
        }
    }

    protected void toDetect(byte[] bArr, int i2, int i3) {
        if (this.detectHandler.hasMessages(300)) {
            this.detectHandler.removeMessages(300);
        }
        Handler handler = this.detectHandler;
        handler.sendMessage(handler.obtainMessage(300, i2, i3, bArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void uploadActionImages() {
        startService(new Intent(this, AysImgIntentService.class));
    }

    void verifyEnvironmentRisk() {
        if (this.verifyState.get() != 30) {
            this.session.setResult(6, "\u73af\u5883\u5b58\u5728\u98ce\u9669");
            toComplete();
        }
    }

    void verifyFail(String str) {
        if (this.verifyState.get() != 30) {
            this.session.setResult(1, str);
            toComplete();
        }
    }

    void verifyPermissionsFinish() {
        if (this.verifyState.get() != 30) {
            this.session.setResult(4, "\u6ca1\u6709\u76f8\u673a\u6743\u9650");
            toComplete();
        }
    }

    protected void verifySoInitFail() {
        this.verifyTracker.trackAndroidSoInitFail(8, "\u6a21\u578b\u6216so\u5e93\u6ca1\u6709\u6210\u529f\u52a0\u8f7d");
        if (this.verifyState.get() != 30) {
            this.session.setResult(8, "\u6a21\u578b\u6216so\u5e93\u6ca1\u6709\u6210\u529f\u52a0\u8f7d");
            toComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void verifySuccessFinish() {
        if (this.verifyState.get() != 30) {
            this.session.setResult(0, "\u4eba\u8138\u6838\u9a8c\u6210\u529f");
            toComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void verifyUserCancel() {
        this.verifyTracker.trackUserCancel();
        if (this.verifyState.get() != 30) {
            this.session.setResult(3, "\u7528\u6237\u53d6\u6d88\u6838\u9a8c");
            toComplete();
        }
    }
}
