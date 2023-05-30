package com.jingdong.common.web.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.TextureView;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes12.dex */
public class CameraView extends TextureView implements TextureView.SurfaceTextureListener {
    private String TAG;
    private int defaultCamera;
    private boolean init;
    private Camera mCamera;
    private int mCameraIndex;
    private int mOrientation;
    private SurfaceTexture mSurfaceTexture;
    private int preHeight;
    private int preWidth;
    private boolean reportErr;
    private int screenHeight;
    private int screenWidth;

    public CameraView(Context context) {
        super(context);
        this.TAG = CameraView.class.getSimpleName();
        this.preWidth = DPIUtil.dip2px(120.0f);
        this.preHeight = DPIUtil.dip2px(160.0f);
        this.mOrientation = 90;
        this.mCameraIndex = -1;
        this.defaultCamera = 1;
        this.reportErr = false;
        this.screenWidth = BaseInfo.getScreenWidth();
        this.screenHeight = BaseInfo.getScreenHeight();
        setSurfaceTextureListener(this);
        this.reportErr = SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.WEB_MEDIA_REPORT, false);
    }

    private int getDisplayOrientation(Activity activity) {
        int i2;
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation != 0) {
            if (rotation == 1) {
                i2 = 90;
            } else if (rotation == 2) {
                i2 = 180;
            } else if (rotation == 3) {
                i2 = 270;
            }
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(0, cameraInfo);
            return ((cameraInfo.orientation - i2) + R2.attr.additionBottom) % R2.attr.additionBottom;
        }
        i2 = 0;
        Camera.CameraInfo cameraInfo2 = new Camera.CameraInfo();
        Camera.getCameraInfo(0, cameraInfo2);
        return ((cameraInfo2.orientation - i2) + R2.attr.additionBottom) % R2.attr.additionBottom;
    }

    private Camera.Size getOptimalSize(@NonNull List<Camera.Size> list, int i2, int i3) {
        double d = i3;
        double d2 = i2;
        Double.isNaN(d);
        Double.isNaN(d2);
        double d3 = d / d2;
        Camera.Size size = list.size() > 0 ? list.get(0) : null;
        double d4 = Double.MAX_VALUE;
        double d5 = Double.MAX_VALUE;
        for (Camera.Size size2 : list) {
            double d6 = size2.width;
            double d7 = size2.height;
            Double.isNaN(d6);
            Double.isNaN(d7);
            if (Math.abs((d6 / d7) - d3) <= 0.5d && Math.abs(size2.width - i3) < d5) {
                int i4 = size2.height;
                int i5 = size2.width;
                if (i4 > i5) {
                    d5 = Math.abs(i5 - i3);
                    size = size2;
                }
            }
        }
        if (size == null) {
            for (Camera.Size size3 : list) {
                if (Math.abs(size3.width - i3) < d4) {
                    int i6 = size3.height;
                    int i7 = size3.width;
                    if (i6 > i7) {
                        d4 = Math.abs(i7 - i3);
                        size = size3;
                    }
                }
            }
        }
        return size;
    }

    private void openBackCamera() {
        if (this.mCamera != null) {
            return;
        }
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        boolean z = false;
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == 0) {
                try {
                    this.mCamera = Camera.open(i2);
                    this.mCameraIndex = 0;
                    return;
                } catch (RuntimeException e2) {
                    e2.printStackTrace();
                    if (this.reportErr) {
                        ExceptionReporter.reportWebViewCommonError("openCamera_err", "", "CameraView | openBackCamera 1", e2.getMessage());
                    }
                    z = true;
                }
            }
        }
        if (this.mCamera != null || z) {
            return;
        }
        try {
            this.mCamera = Camera.open();
        } catch (Exception e3) {
            if (this.reportErr) {
                ExceptionReporter.reportWebViewCommonError("openCamera_err", "", "CameraView | openBackCamera 2", e3.getMessage());
            }
        }
        if (this.reportErr) {
            ExceptionReporter.reportWebViewCommonError("openCamera_err", "", "CameraView | openFrontCamera \u65e0\u6cd5\u5f00\u542f\u540e\u7f6e\u6444\u50cf\u5934", "");
        }
    }

    private void openFrontCamera() {
        if (this.mCamera != null) {
            return;
        }
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        boolean z = false;
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == 1) {
                try {
                    this.mCamera = Camera.open(i2);
                    this.mCameraIndex = 1;
                    return;
                } catch (RuntimeException e2) {
                    if (this.reportErr) {
                        ExceptionReporter.reportWebViewCommonError("openCamera_err", "", "CameraView | openFrontCamera 1", e2.getMessage());
                    }
                    z = true;
                }
            }
        }
        if (this.mCamera != null || z) {
            return;
        }
        try {
            this.mCamera = Camera.open();
        } catch (Exception e3) {
            if (this.reportErr) {
                ExceptionReporter.reportWebViewCommonError("openCamera_err", "", "CameraView | openFrontCamera 2", e3.getMessage());
            }
        }
        if (this.reportErr) {
            ExceptionReporter.reportWebViewCommonError("openCamera_err", "", "CameraView | openFrontCamera \u65e0\u6cd5\u5f00\u542f\u524d\u7f6e\u6444\u50cf\u5934", "");
        }
    }

    private void removeSelf() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            return;
        }
        ((ViewGroup) parent).removeView(this);
    }

    private void setCameraParams() {
        Camera.Parameters parameters = this.mCamera.getParameters();
        Camera.Size optimalSize = getOptimalSize(parameters.getSupportedPreviewSizes(), this.screenWidth, this.screenHeight);
        parameters.setPreviewSize(optimalSize.width, optimalSize.height);
        parameters.setJpegQuality(70);
        this.preHeight = (int) (this.preWidth * (optimalSize.width / optimalSize.height));
        this.mCamera.setParameters(parameters);
        OKLog.d(this.TAG, "\u9884\u89c8\u5c3a\u5bf8w===" + optimalSize.width + ",h===" + optimalSize.height);
        OKLog.d(this.TAG, "\u89c6\u56fe\u5c3a\u5bf8w===" + this.preWidth + ",h===" + this.preHeight);
        this.init = true;
    }

    public void cameraInit() {
        try {
            Camera camera = this.mCamera;
            if (camera == null) {
                return;
            }
            camera.setDisplayOrientation(this.mOrientation);
            this.mCamera.setPreviewTexture(getSurfaceTexture());
            setCameraParams();
            this.mCamera.startPreview();
            this.mCamera.cancelAutoFocus();
            requestLayout();
        } catch (Exception e2) {
            if (this.reportErr) {
                ExceptionReporter.reportWebViewCommonError("openCamera_err", "", "CameraView | cameraInit", e2.getMessage());
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.init) {
            setMeasuredDimension(this.preWidth, this.preHeight);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        if (OKLog.D) {
            OKLog.e(this.TAG, "onSurfaceTextureAvailable");
        }
        if (this.defaultCamera == 1) {
            openFrontCamera();
        } else {
            openBackCamera();
        }
        cameraInit();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        release();
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void release() {
        if (OKLog.D) {
            OKLog.e(this.TAG, "onSurfaceTextureDestroyed");
        }
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                camera.setPreviewCallback(null);
                this.mCamera.stopPreview();
                this.mCamera.release();
                this.mCamera = null;
            } catch (Exception unused) {
            }
        }
    }

    public void setDefaultCamera(boolean z) {
        if (z) {
            this.defaultCamera = 1;
        } else {
            this.defaultCamera = 0;
        }
    }

    public void setOrientation(int i2) {
        this.mOrientation = i2;
    }

    public boolean switchCamera() {
        if (this.mCamera == null || this.mCameraIndex == -1) {
            return false;
        }
        release();
        if (this.mCameraIndex == 1) {
            openBackCamera();
        } else {
            openFrontCamera();
        }
        cameraInit();
        return true;
    }

    public CameraView(Context context, int i2, int i3) {
        this(context);
        this.preWidth = i2;
        this.preHeight = i3;
        if (context instanceof Activity) {
            this.mOrientation = getDisplayOrientation((Activity) context);
        }
    }
}
