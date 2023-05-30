package com.jingdong.manto.jsapi.refact.rec;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.os.Build;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.widget.Toast;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.manto.R;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes15.dex */
public class CameraManager {
    private static final String TAG = "CameraManager";
    private boolean isLightOpen;
    private volatile Camera mCamera;
    private Context mContext;
    private SurfaceHolder mSurfaceHolder;
    private boolean mWaitForTakePhoto;
    private int cameraID = 0;
    private int mSupportWidth = -1;
    private int mSupportHeight = -1;
    private int mPicWidth = -1;
    private int mPicHeight = -1;
    private int mWidth = 640;
    private int mHeight = 480;

    public CameraManager(Context context) {
        this.mContext = context;
    }

    private boolean checkCameraFacing(int i2) {
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i3 = 0; i3 < numberOfCameras; i3++) {
            Camera.getCameraInfo(i3, cameraInfo);
            if (i2 == cameraInfo.facing) {
                return true;
            }
        }
        return false;
    }

    private String getAutoFocusMode(Camera.Parameters parameters) {
        if (parameters != null) {
            List<String> supportedFocusModes = parameters.getSupportedFocusModes();
            if (isSupported(supportedFocusModes, "continuous-picture")) {
                return "continuous-picture";
            }
            if (isSupported(supportedFocusModes, "continuous-video")) {
                return "continuous-video";
            }
            if (isSupported(supportedFocusModes, "auto")) {
                return "auto";
            }
            return null;
        }
        return null;
    }

    private Camera.Parameters getParameters() {
        if (this.mCamera == null) {
            return null;
        }
        try {
            return this.mCamera.getParameters();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void getPicSize(int i2, int i3, List<Camera.Size> list) {
        int i4;
        CamcorderProfile camcorderProfile;
        int i5;
        if (list == null || list.size() == 0) {
            return;
        }
        float f2 = (i2 + 0.0f) / i3;
        int i6 = i2 * 3;
        int i7 = i6 / 2;
        int i8 = i6 / 4;
        Camera.Size size = null;
        Camera.Size size2 = null;
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MAX_VALUE;
        for (Camera.Size size3 : list) {
            float abs = Math.abs(((size3.height + 0.0f) / size3.width) - f2);
            int i9 = size3.height;
            if (i9 > i7 || i9 < i8) {
                if (i9 < i2 * 2 && abs < f3) {
                    size2 = size3;
                    f3 = abs;
                }
            } else if (abs < f4) {
                size = size3;
                f4 = abs;
            }
        }
        if (size == null) {
            size = size2;
        }
        if (size != null) {
            this.mPicHeight = size.height;
            i5 = size.width;
        } else {
            int i10 = 5;
            if (CamcorderProfile.hasProfile(this.cameraID, 5)) {
                i4 = this.cameraID;
            } else {
                if (CamcorderProfile.hasProfile(this.cameraID, 6)) {
                    camcorderProfile = CamcorderProfile.get(6);
                } else if (CamcorderProfile.hasProfile(this.cameraID, 4)) {
                    camcorderProfile = CamcorderProfile.get(4);
                } else {
                    i4 = this.cameraID;
                    i10 = 7;
                }
                if (camcorderProfile != null) {
                    return;
                }
                this.mPicHeight = camcorderProfile.videoFrameHeight;
                i5 = camcorderProfile.videoFrameWidth;
            }
            camcorderProfile = CamcorderProfile.get(i4, i10);
            if (camcorderProfile != null) {
            }
        }
        this.mPicWidth = i5;
    }

    private void getPreviewSize(int i2, int i3, List<Camera.Size> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        float f2 = (i2 + 0.0f) / i3;
        int i4 = (i2 * 3) / 2;
        int i5 = i2 / 2;
        Camera.Size size = null;
        Camera.Size size2 = null;
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MAX_VALUE;
        for (Camera.Size size3 : list) {
            float abs = Math.abs(((size3.height + 0.0f) / size3.width) - f2);
            int i6 = size3.height;
            if (i6 > i4 || i6 <= i5) {
                if (abs < f4) {
                    size2 = size3;
                    f4 = abs;
                }
            } else if (abs < f3) {
                size = size3;
                f3 = abs;
            }
        }
        if (size == null) {
            size = size2;
        }
        if (size != null) {
            this.mSupportHeight = size.height;
            this.mSupportWidth = size.width;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void getVideoSize(int i2, int i3, List<Camera.Size> list) {
        int i4;
        CamcorderProfile camcorderProfile;
        int i5;
        if (list == null || list.size() == 0) {
            return;
        }
        float f2 = (i2 + 0.0f) / i3;
        int i6 = i2 / 2;
        Camera.Size size = null;
        Camera.Size size2 = null;
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MAX_VALUE;
        for (Camera.Size size3 : list) {
            float abs = Math.abs(((size3.height + 0.0f) / size3.width) - f2);
            int i7 = size3.height;
            if (i7 > i2 || i7 <= i6) {
                if (i7 < (i2 * 3) / 2 && abs < f3) {
                    size2 = size3;
                    f3 = abs;
                }
            } else if (abs < f4) {
                size = size3;
                f4 = abs;
            }
        }
        if (size == null) {
            size = size2;
        }
        if (size != null) {
            this.mHeight = size.height;
            i5 = size.width;
        } else {
            int i8 = 5;
            if (CamcorderProfile.hasProfile(this.cameraID, 5)) {
                i4 = this.cameraID;
            } else {
                int i9 = 4;
                if (!CamcorderProfile.hasProfile(this.cameraID, 4)) {
                    i9 = 7;
                    if (!CamcorderProfile.hasProfile(this.cameraID, 7)) {
                        i4 = this.cameraID;
                        i8 = 0;
                    }
                }
                camcorderProfile = CamcorderProfile.get(i9);
                if (camcorderProfile != null) {
                    return;
                }
                this.mHeight = camcorderProfile.videoFrameHeight;
                i5 = camcorderProfile.videoFrameWidth;
            }
            camcorderProfile = CamcorderProfile.get(i4, i8);
            if (camcorderProfile != null) {
            }
        }
        this.mWidth = i5;
    }

    private boolean isSupported(List<String> list, String str) {
        return list != null && list.contains(str);
    }

    private void setCameraDisplayOrientation(Activity activity, int i2, Camera camera) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i2, cameraInfo);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int i3 = 0;
        if (rotation != 0) {
            if (rotation == 1) {
                i3 = 90;
            } else if (rotation == 2) {
                i3 = 180;
            } else if (rotation == 3) {
                i3 = 270;
            }
        }
        camera.setDisplayOrientation((cameraInfo.facing == 1 ? 360 - ((cameraInfo.orientation + i3) % R2.attr.additionBottom) : (cameraInfo.orientation - i3) + R2.attr.additionBottom) % R2.attr.additionBottom);
    }

    private void setCameraParams() {
        Camera.Parameters parameters;
        int i2;
        int i3;
        if (this.mCamera == null || (parameters = getParameters()) == null) {
            return;
        }
        String autoFocusMode = getAutoFocusMode(parameters);
        if (autoFocusMode != null) {
            parameters.setFocusMode(autoFocusMode);
        }
        parameters.set(MBaseKeyNames.KEY_ORIENTATION, "portrait");
        parameters.setPictureFormat(256);
        parameters.setJpegQuality(100);
        setParameters(parameters);
        if ((this.mSupportWidth != -1 && this.mSupportHeight != -1) || (this.mPicWidth != -1 && this.mPicHeight != -1)) {
            Camera.Parameters parameters2 = getParameters();
            if (parameters2 == null) {
                return;
            }
            int i4 = this.mSupportWidth;
            if (i4 != -1 && (i3 = this.mSupportHeight) != -1) {
                parameters2.setPreviewSize(i4, i3);
            }
            int i5 = this.mPicWidth;
            if (i5 != -1 && (i2 = this.mPicHeight) != -1) {
                parameters2.setPictureSize(i5, i2);
            }
            setParameters(parameters2);
        }
        this.mCamera.cancelAutoFocus();
    }

    private void setParameters(Camera.Parameters parameters) {
        if (this.mCamera == null) {
            return;
        }
        try {
            this.mCamera.setParameters(parameters);
        } catch (Exception unused) {
        }
    }

    public synchronized void changeCameraFacing() {
        int i2;
        Context context;
        int i3;
        try {
            i2 = this.cameraID;
        } catch (Exception unused) {
        }
        if (i2 == 1) {
            if (checkCameraFacing(0)) {
                initCamera(0);
            } else {
                context = this.mContext;
                i3 = R.string.manto_record_no_camera;
            }
        } else if (i2 == 0) {
            if (checkCameraFacing(1)) {
                initCamera(1);
            } else {
                context = this.mContext;
                i3 = R.string.manto_record_no_f_camera;
            }
        }
        Toast.makeText(context, i3, 0).show();
    }

    public void freeCameraResource() {
        try {
            if (this.mCamera != null) {
                synchronized (this) {
                    if (this.mCamera != null) {
                        this.mCamera.setPreviewCallback(null);
                        stopPreview();
                        lock();
                        this.mCamera.release();
                        this.mCamera = null;
                    }
                }
            }
        } catch (Exception unused) {
            this.mCamera = null;
        }
    }

    public synchronized Camera getCamera() {
        return this.mCamera;
    }

    public synchronized int getCameraID() {
        return this.cameraID;
    }

    public synchronized int getVideoHeight() {
        return this.mHeight;
    }

    public synchronized int getVideoWidth() {
        return this.mWidth;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0059 A[Catch: all -> 0x003c, DONT_GENERATE, TryCatch #2 {, blocks: (B:8:0x000c, B:10:0x0012, B:25:0x0055, B:27:0x0059, B:29:0x005b, B:11:0x001b, B:12:0x001f, B:17:0x0038, B:13:0x0024, B:15:0x002a, B:16:0x0033, B:21:0x003e, B:23:0x0042, B:24:0x004a), top: B:44:0x000a, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005b A[Catch: all -> 0x003c, DONT_GENERATE, TRY_LEAVE, TryCatch #2 {, blocks: (B:8:0x000c, B:10:0x0012, B:25:0x0055, B:27:0x0059, B:29:0x005b, B:11:0x001b, B:12:0x001f, B:17:0x0038, B:13:0x0024, B:15:0x002a, B:16:0x0033, B:21:0x003e, B:23:0x0042, B:24:0x004a), top: B:44:0x000a, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void initCamera(int i2) {
        Context context;
        String str;
        if (this.mCamera != null) {
            freeCameraResource();
        }
        synchronized (this) {
            try {
            } catch (Exception unused) {
                if (this.mCamera != null) {
                    this.mCamera.release();
                    this.mCamera = null;
                }
                Toast.makeText(this.mContext, "\u6444\u50cf\u5934\u6253\u5f00\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u6743\u9650", 0).show();
            }
            if (i2 == 1) {
                if (checkCameraFacing(1)) {
                    this.mCamera = Camera.open(1);
                    this.cameraID = 1;
                    if (this.mCamera == null) {
                        return;
                    }
                    int dMWidthPixels = MantoDensityUtils.getDMWidthPixels();
                    int dMHeightPixels = MantoDensityUtils.getDMHeightPixels();
                    Camera.Parameters parameters = getParameters();
                    if (parameters == null) {
                        return;
                    }
                    getPreviewSize(dMWidthPixels, dMHeightPixels, parameters.getSupportedPreviewSizes());
                    getVideoSize(dMWidthPixels, dMHeightPixels, parameters.getSupportedVideoSizes());
                    getPicSize(dMWidthPixels, dMHeightPixels, parameters.getSupportedPictureSizes());
                    setCameraParams();
                    if (Build.VERSION.SDK_INT >= 17) {
                        this.mCamera.enableShutterSound(false);
                    }
                    setCameraDisplayOrientation((Activity) this.mContext, this.cameraID, this.mCamera);
                    try {
                        this.mCamera.setPreviewDisplay(this.mSurfaceHolder);
                        this.mCamera.startPreview();
                        return;
                    } catch (Exception unused2) {
                        return;
                    }
                }
                context = this.mContext;
                str = "\u6ca1\u6709\u524d\u7f00\u6444\u50cf\u5934\uff0c\u8bf7\u68c0\u67e5";
            } else if (checkCameraFacing(0)) {
                this.mCamera = Camera.open(0);
                this.cameraID = 0;
                if (this.mCamera == null) {
                }
            } else {
                context = this.mContext;
                str = "\u6ca1\u6709\u540e\u7f00\u6444\u50cf\u5934\uff0c\u8bf7\u68c0\u67e5";
            }
            Toast.makeText(context, str, 0).show();
            if (this.mCamera == null) {
            }
        }
    }

    public void initCamera(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = surfaceHolder;
        if (surfaceHolder == null) {
            return;
        }
        initCamera(0);
    }

    public void initCamera(SurfaceHolder surfaceHolder, int i2) {
        this.mSurfaceHolder = surfaceHolder;
        initCamera(i2);
    }

    public void lock() {
        if (this.mCamera == null) {
            return;
        }
        try {
            this.mCamera.lock();
        } catch (Exception unused) {
        }
    }

    public synchronized boolean openOrCloseLight(int i2) {
        if (this.mCamera != null) {
            Camera.Parameters parameters = getParameters();
            if (parameters == null) {
                return false;
            }
            String flashMode = parameters.getFlashMode();
            if (TextUtils.isEmpty(flashMode)) {
                return this.isLightOpen;
            }
            if (TextUtils.equals(flashMode, DebugKt.DEBUG_PROPERTY_VALUE_OFF)) {
                parameters.setFlashMode(i2 == 0 ? "torch" : "on");
                this.isLightOpen = true;
            } else if (TextUtils.equals(flashMode, "torch") || TextUtils.equals(flashMode, "on")) {
                parameters.setFlashMode(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                this.isLightOpen = false;
            }
            setParameters(parameters);
        }
        return this.isLightOpen;
    }

    public void startPreview() {
        try {
            if (this.mCamera != null) {
                this.mCamera.startPreview();
            } else {
                initCamera(this.mSurfaceHolder);
            }
        } catch (Exception unused) {
        }
    }

    public void stopPreview() {
        try {
            if (this.mCamera != null) {
                this.mCamera.stopPreview();
            }
        } catch (Exception e2) {
            MantoLog.e("better", "stopPreview: " + e2);
        }
    }

    public synchronized void turnOff() {
        if (this.isLightOpen && this.mCamera != null) {
            Camera.Parameters parameters = getParameters();
            if (parameters == null) {
                return;
            }
            parameters.setFlashMode(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
            this.isLightOpen = false;
            setParameters(parameters);
        }
    }

    public void unLock() {
        if (this.mCamera == null) {
            return;
        }
        try {
            this.mCamera.unlock();
        } catch (Exception unused) {
        }
    }
}
