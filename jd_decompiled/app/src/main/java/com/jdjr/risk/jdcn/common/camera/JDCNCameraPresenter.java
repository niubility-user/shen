package com.jdjr.risk.jdcn.common.camera;

import android.content.Context;
import android.content.pm.FeatureInfo;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.SurfaceHolder;
import com.jdjr.risk.jdcn.common.utils.JDCNLogUtils;
import com.jdjr.risk.jdcn.common.utils.JDCNScreenUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes18.dex */
public class JDCNCameraPresenter extends HandlerThread implements Camera.ErrorCallback {
    private static final int MSG_ADD_CALLBACK_BUFFER = 20;
    private static final int MSG_BIND_SURFACE = 21;
    private static final int MSG_CLOSE_FLASH = 24;
    private static final int MSG_FOCUS = 18;
    private static final int MSG_OPEN_CAMERA = 16;
    private static final int MSG_OPEN_FLASH = 23;
    private static final int MSG_RELEASE = 17;
    private static final int MSG_RELEASE_CAMERA = 22;
    private static final int MSG_START_PREVIEW = 19;
    private static String TAG = JDCNCameraSurfaceView.class.getName();
    private volatile Camera mCamera;
    private volatile boolean mCameraReleased;
    private ICameraView mCameraView;
    private boolean mFlashStatus;
    private long mOnErrorTime;
    private byte[] mPreviewBuffer;
    private Camera.PreviewCallback mPreviewCallback;
    private PreviewHandler mPreviewHandler;
    private JDCNCameraParamCallback mPreviewSelfCallback;
    private Camera.Size mPreviewSize;
    private volatile boolean mReleased;
    private SurfaceHolder mSurfaceHolder;

    /* loaded from: classes18.dex */
    public interface ICameraView {
        void cameraException(Exception exc);

        Context getMVPContext();

        void resizeSurface(Camera.Size size);
    }

    /* loaded from: classes18.dex */
    static class PreviewHandler extends Handler {
        private WeakReference<JDCNCameraPresenter> mHost;

        PreviewHandler(JDCNCameraPresenter jDCNCameraPresenter, Looper looper) {
            super(looper);
            this.mHost = new WeakReference<>(jDCNCameraPresenter);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            JDCNCameraPresenter jDCNCameraPresenter = this.mHost.get();
            if (jDCNCameraPresenter != null) {
                switch (message.what) {
                    case 16:
                        jDCNCameraPresenter._openCamera((SurfaceHolder) message.obj);
                        return;
                    case 17:
                        jDCNCameraPresenter._release();
                        return;
                    case 18:
                        jDCNCameraPresenter._focus();
                        return;
                    case 19:
                        jDCNCameraPresenter._startPreview();
                        return;
                    case 20:
                        jDCNCameraPresenter._addCallbackBuffer();
                        return;
                    case 21:
                        jDCNCameraPresenter._bindSurface((SurfaceHolder) message.obj);
                        return;
                    case 22:
                        jDCNCameraPresenter._releaseCamera();
                        return;
                    case 23:
                        jDCNCameraPresenter._openFlash();
                        return;
                    case 24:
                        jDCNCameraPresenter._closeFlash();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public JDCNCameraPresenter(ICameraView iCameraView) {
        super("IDCARD_PREVIEW");
        this.mCameraReleased = true;
        this.mReleased = false;
        this.mFlashStatus = false;
        this.mCameraView = iCameraView;
        start();
        this.mPreviewHandler = new PreviewHandler(this, getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _addCallbackBuffer() {
        if (this.mCamera == null || this.mCameraReleased || this.mPreviewBuffer == null) {
            return;
        }
        this.mCamera.addCallbackBuffer(this.mPreviewBuffer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _bindSurface(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = surfaceHolder;
        if (this.mCamera != null) {
            this.mCameraReleased = false;
            try {
                this.mCamera.setPreviewDisplay(surfaceHolder);
                caculateCameraSize();
            } catch (IOException e2) {
                ICameraView iCameraView = this.mCameraView;
                if (iCameraView != null) {
                    iCameraView.cameraException(e2);
                }
            }
            _startPreview();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _closeFlash() {
        if (this.mFlashStatus) {
            if (this.mCamera != null) {
                try {
                    Camera.Parameters parameters = this.mCamera.getParameters();
                    parameters.setFlashMode(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                    this.mCamera.setParameters(parameters);
                } catch (Exception unused) {
                }
            }
            this.mFlashStatus = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _focus() {
        if (this.mCamera == null || this.mCameraReleased) {
            return;
        }
        try {
            JDCNLogUtils.d("sensor", "focus!!!!!!!!!!!!!!!");
            this.mCamera.autoFocus(null);
        } catch (Exception e2) {
            JDCNLogUtils.d(TAG, "takePhoto ".concat(String.valueOf(e2)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _openCamera(SurfaceHolder surfaceHolder) {
        try {
            this.mCamera = JDCNCameraUtils.openCamera();
            this.mCamera.setErrorCallback(this);
            _bindSurface(surfaceHolder);
        } catch (Exception e2) {
            ICameraView iCameraView = this.mCameraView;
            if (iCameraView != null) {
                iCameraView.cameraException(e2);
            }
            this.mCamera = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _openFlash() {
        if (this.mFlashStatus) {
            return;
        }
        if (this.mCameraView != null && this.mCamera != null) {
            for (FeatureInfo featureInfo : this.mCameraView.getMVPContext().getPackageManager().getSystemAvailableFeatures()) {
                if ("android.hardware.camera.flash".equals(featureInfo.name)) {
                    try {
                        Camera.Parameters parameters = this.mCamera.getParameters();
                        if (parameters != null) {
                            parameters.setFlashMode("torch");
                            this.mCamera.setParameters(parameters);
                            this.mCamera.startPreview();
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        this.mFlashStatus = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _release() {
        try {
            this.mReleased = true;
            if (Build.VERSION.SDK_INT >= 18) {
                quitSafely();
            } else {
                quit();
            }
            this.mSurfaceHolder = null;
            this.mCameraView = null;
            this.mPreviewCallback = null;
            this.mPreviewSelfCallback = null;
            this.mPreviewBuffer = null;
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _releaseCamera() {
        if (this.mCamera != null) {
            try {
                this.mCameraReleased = true;
                this.mCamera.setPreviewCallback(null);
                this.mCamera.setPreviewCallbackWithBuffer(null);
                this.mCamera.setErrorCallback(null);
                this.mCamera.stopPreview();
                this.mCamera.release();
                this.mCamera = null;
                JDCNLogUtils.d("jdcn_camera", "_releaseCamera \u6b63\u5e38");
                return;
            } catch (Exception unused) {
                return;
            }
        }
        JDCNLogUtils.d("jdcn_camera", "_releaseCamera mCamera == null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _startPreview() {
        if (this.mCamera == null || this.mCameraReleased) {
            return;
        }
        this.mCamera.startPreview();
        _focus();
    }

    private void caculateCameraSize() {
        if (this.mCamera != null) {
            this.mCameraReleased = false;
            ICameraView iCameraView = this.mCameraView;
            if (iCameraView != null) {
                int[] realScreenWidthHeight = JDCNScreenUtils.getRealScreenWidthHeight(iCameraView.getMVPContext());
                int i2 = realScreenWidthHeight[1];
                int i3 = realScreenWidthHeight[0];
                try {
                    Camera.Parameters parameters = this.mCamera.getParameters();
                    ICameraView iCameraView2 = this.mCameraView;
                    if (iCameraView2 != null && iCameraView2.getMVPContext().getResources().getConfiguration().orientation == 1) {
                        this.mCamera.setDisplayOrientation(90);
                        parameters.setRotation(90);
                    } else if (this.mCameraView == null) {
                        return;
                    } else {
                        this.mCamera.setDisplayOrientation(0);
                        parameters.setRotation(0);
                    }
                    parameters.setPreviewFormat(17);
                    Camera.Size closelyPreSize = getCloselyPreSize(i2, i3, parameters.getSupportedPreviewSizes());
                    if (closelyPreSize != null) {
                        parameters.setPreviewSize(closelyPreSize.width, closelyPreSize.height);
                    } else if (getPreviewSize(parameters) == null) {
                        parameters.setPreviewSize(R2.attr.lineSpacing, R2.attr.counterOverflowTextColor);
                    } else {
                        Camera.Size previewSize = getPreviewSize(parameters);
                        if (previewSize != null) {
                            parameters.setPreviewSize(previewSize.width, previewSize.height);
                        }
                    }
                    this.mCamera.setParameters(parameters);
                    if (this.mPreviewCallback != null) {
                        this.mCamera.setPreviewCallbackWithBuffer(this.mPreviewCallback);
                        Camera.Size previewSize2 = parameters.getPreviewSize();
                        byte[] bArr = new byte[((previewSize2.width * previewSize2.height) * ImageFormat.getBitsPerPixel(17)) / 8];
                        this.mPreviewBuffer = bArr;
                        JDCNCameraParamCallback jDCNCameraParamCallback = this.mPreviewSelfCallback;
                        if (jDCNCameraParamCallback != null) {
                            jDCNCameraParamCallback.previewBufferCreated(bArr);
                        }
                        this.mCamera.addCallbackBuffer(this.mPreviewBuffer);
                        this.mPreviewSize = previewSize2;
                    }
                    ICameraView iCameraView3 = this.mCameraView;
                    if (iCameraView3 != null) {
                        iCameraView3.resizeSurface(this.mPreviewSize);
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    JDCNLogUtils.d(TAG, "Error setting camera preview: " + e2.getMessage());
                    try {
                        Camera.Parameters parameters2 = this.mCamera.getParameters();
                        ICameraView iCameraView4 = this.mCameraView;
                        if (iCameraView4 != null && iCameraView4.getMVPContext().getResources().getConfiguration().orientation == 1) {
                            this.mCamera.setDisplayOrientation(90);
                            parameters2.setRotation(90);
                        } else if (this.mCameraView == null) {
                            return;
                        } else {
                            this.mCamera.setDisplayOrientation(0);
                            parameters2.setRotation(0);
                        }
                        this.mCamera.setParameters(parameters2);
                        this.mCamera.startPreview();
                        _focus();
                        ICameraView iCameraView5 = this.mCameraView;
                        if (iCameraView5 != null) {
                            iCameraView5.resizeSurface(this.mPreviewSize);
                            return;
                        }
                        return;
                    } catch (Exception unused) {
                        this.mCamera = null;
                        ICameraView iCameraView6 = this.mCameraView;
                        if (iCameraView6 != null) {
                            iCameraView6.cameraException(new SecurityException("\u6ca1\u76f8\u673a\u6743\u9650"));
                            return;
                        }
                        return;
                    }
                }
            }
            return;
        }
        ICameraView iCameraView7 = this.mCameraView;
        if (iCameraView7 != null) {
            iCameraView7.cameraException(new SecurityException("\u6ca1\u76f8\u673a\u6743\u9650"));
        }
    }

    private Camera.Size getCloselyPreSize(int i2, int i3, List<Camera.Size> list) {
        for (Camera.Size size : list) {
            if (size.width == i2 && size.height == i3) {
                return size;
            }
        }
        float f2 = i2 / i3;
        float f3 = Float.MAX_VALUE;
        Camera.Size size2 = null;
        for (Camera.Size size3 : list) {
            float abs = Math.abs(f2 - (size3.width / size3.height));
            if (abs < f3) {
                size2 = size3;
                f3 = abs;
            }
        }
        return size2;
    }

    private Camera.Size getPreviewSize(Camera.Parameters parameters) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        for (int i2 = 0; i2 < supportedPreviewSizes.size(); i2++) {
            Camera.Size size = supportedPreviewSizes.get(i2);
            int i3 = size.width;
            if (i3 / size.height >= 1.4d && 1200 <= i3 && i3 <= 2600) {
                return size;
            }
        }
        for (int i4 = 0; i4 < supportedPreviewSizes.size(); i4++) {
            Camera.Size size2 = supportedPreviewSizes.get(i4);
            int i5 = size2.width;
            if (i5 / size2.height >= 1.4d && 800 <= i5 && i5 <= 2600) {
                return size2;
            }
        }
        return null;
    }

    private void startPreview() {
        this.mPreviewHandler.sendEmptyMessage(19);
    }

    public void addCallbackBuffer() {
        this.mPreviewHandler.sendEmptyMessage(20);
    }

    public void closeFlash() {
        this.mPreviewHandler.sendEmptyMessage(24);
    }

    public void focus() {
        if (this.mReleased) {
            return;
        }
        this.mPreviewHandler.sendEmptyMessage(18);
    }

    @Override // android.hardware.Camera.ErrorCallback
    public void onError(int i2, Camera camera) {
        if (2 == i2) {
            try {
                if (0 == this.mOnErrorTime || System.currentTimeMillis() - this.mOnErrorTime > Final.FIVE_SECOND) {
                    if (camera != null) {
                        camera.release();
                    }
                    _openCamera(this.mSurfaceHolder);
                }
                this.mOnErrorTime = System.currentTimeMillis();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void openCamera(SurfaceHolder surfaceHolder) {
        PreviewHandler previewHandler = this.mPreviewHandler;
        previewHandler.sendMessage(previewHandler.obtainMessage(16, surfaceHolder));
    }

    public void openFlash() {
        this.mPreviewHandler.sendEmptyMessage(23);
    }

    public void release() {
        this.mPreviewHandler.sendEmptyMessage(17);
    }

    public void releaseCamera() {
        this.mPreviewHandler.sendEmptyMessage(22);
    }

    public void setPreviewCallback(Camera.PreviewCallback previewCallback) {
        this.mPreviewCallback = previewCallback;
    }

    public void setPreviewSelfCallback(JDCNCameraParamCallback jDCNCameraParamCallback) {
        this.mPreviewSelfCallback = jDCNCameraParamCallback;
    }
}
