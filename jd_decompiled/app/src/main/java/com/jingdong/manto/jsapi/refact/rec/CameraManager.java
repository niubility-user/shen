package com.jingdong.manto.jsapi.refact.rec;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.widget.Toast;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.manto.R;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getPicSize(int r13, int r14, java.util.List<android.hardware.Camera.Size> r15) {
        /*
            r12 = this;
            if (r15 == 0) goto L9c
            int r0 = r15.size()
            if (r0 != 0) goto La
            goto L9c
        La:
            float r0 = (float) r13
            r1 = 0
            float r0 = r0 + r1
            float r14 = (float) r14
            float r0 = r0 / r14
            int r14 = r13 * 3
            int r2 = r14 / 2
            r3 = 4
            int r14 = r14 / r3
            java.util.Iterator r15 = r15.iterator()
            r4 = 0
            r5 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r5 = r4
            r6 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r7 = 2139095039(0x7f7fffff, float:3.4028235E38)
        L24:
            boolean r8 = r15.hasNext()
            if (r8 == 0) goto L55
            java.lang.Object r8 = r15.next()
            android.hardware.Camera$Size r8 = (android.hardware.Camera.Size) r8
            int r9 = r8.height
            float r9 = (float) r9
            float r9 = r9 + r1
            int r10 = r8.width
            float r10 = (float) r10
            float r9 = r9 / r10
            float r9 = r9 - r0
            float r9 = java.lang.Math.abs(r9)
            int r10 = r8.height
            if (r10 > r2) goto L4a
            if (r10 < r14) goto L4a
            int r10 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r10 >= 0) goto L24
            r4 = r8
            r7 = r9
            goto L24
        L4a:
            int r11 = r13 * 2
            if (r10 >= r11) goto L24
            int r10 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r10 >= 0) goto L24
            r5 = r8
            r6 = r9
            goto L24
        L55:
            if (r4 == 0) goto L58
            goto L59
        L58:
            r4 = r5
        L59:
            if (r4 == 0) goto L64
            int r13 = r4.height
            r12.mPicHeight = r13
            int r13 = r4.width
        L61:
            r12.mPicWidth = r13
            goto L9c
        L64:
            int r13 = r12.cameraID
            r14 = 5
            boolean r13 = android.media.CamcorderProfile.hasProfile(r13, r14)
            if (r13 == 0) goto L74
            int r13 = r12.cameraID
        L6f:
            android.media.CamcorderProfile r13 = android.media.CamcorderProfile.get(r13, r14)
            goto L93
        L74:
            int r13 = r12.cameraID
            r14 = 6
            boolean r13 = android.media.CamcorderProfile.hasProfile(r13, r14)
            if (r13 == 0) goto L82
            android.media.CamcorderProfile r13 = android.media.CamcorderProfile.get(r14)
            goto L93
        L82:
            int r13 = r12.cameraID
            boolean r13 = android.media.CamcorderProfile.hasProfile(r13, r3)
            if (r13 == 0) goto L8f
            android.media.CamcorderProfile r13 = android.media.CamcorderProfile.get(r3)
            goto L93
        L8f:
            int r13 = r12.cameraID
            r14 = 7
            goto L6f
        L93:
            if (r13 == 0) goto L9c
            int r14 = r13.videoFrameHeight
            r12.mPicHeight = r14
            int r13 = r13.videoFrameWidth
            goto L61
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.refact.rec.CameraManager.getPicSize(int, int, java.util.List):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getVideoSize(int r11, int r12, java.util.List<android.hardware.Camera.Size> r13) {
        /*
            r10 = this;
            if (r13 == 0) goto L97
            int r0 = r13.size()
            if (r0 != 0) goto La
            goto L97
        La:
            float r0 = (float) r11
            r1 = 0
            float r0 = r0 + r1
            float r12 = (float) r12
            float r0 = r0 / r12
            int r12 = r11 / 2
            java.util.Iterator r13 = r13.iterator()
            r2 = 0
            r3 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r3 = r2
            r4 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r5 = 2139095039(0x7f7fffff, float:3.4028235E38)
        L20:
            boolean r6 = r13.hasNext()
            if (r6 == 0) goto L53
            java.lang.Object r6 = r13.next()
            android.hardware.Camera$Size r6 = (android.hardware.Camera.Size) r6
            int r7 = r6.height
            float r7 = (float) r7
            float r7 = r7 + r1
            int r8 = r6.width
            float r8 = (float) r8
            float r7 = r7 / r8
            float r7 = r7 - r0
            float r7 = java.lang.Math.abs(r7)
            int r8 = r6.height
            if (r8 > r11) goto L46
            if (r8 <= r12) goto L46
            int r8 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r8 >= 0) goto L20
            r2 = r6
            r5 = r7
            goto L20
        L46:
            int r9 = r11 * 3
            int r9 = r9 / 2
            if (r8 >= r9) goto L20
            int r8 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r8 >= 0) goto L20
            r3 = r6
            r4 = r7
            goto L20
        L53:
            if (r2 == 0) goto L56
            goto L57
        L56:
            r2 = r3
        L57:
            if (r2 == 0) goto L62
            int r11 = r2.height
            r10.mHeight = r11
            int r11 = r2.width
        L5f:
            r10.mWidth = r11
            goto L97
        L62:
            int r11 = r10.cameraID
            r12 = 5
            boolean r11 = android.media.CamcorderProfile.hasProfile(r11, r12)
            if (r11 == 0) goto L72
            int r11 = r10.cameraID
        L6d:
            android.media.CamcorderProfile r11 = android.media.CamcorderProfile.get(r11, r12)
            goto L8e
        L72:
            int r11 = r10.cameraID
            r12 = 4
            boolean r11 = android.media.CamcorderProfile.hasProfile(r11, r12)
            if (r11 == 0) goto L80
        L7b:
            android.media.CamcorderProfile r11 = android.media.CamcorderProfile.get(r12)
            goto L8e
        L80:
            int r11 = r10.cameraID
            r12 = 7
            boolean r11 = android.media.CamcorderProfile.hasProfile(r11, r12)
            if (r11 == 0) goto L8a
            goto L7b
        L8a:
            int r11 = r10.cameraID
            r12 = 0
            goto L6d
        L8e:
            if (r11 == 0) goto L97
            int r12 = r11.videoFrameHeight
            r10.mHeight = r12
            int r11 = r11.videoFrameWidth
            goto L5f
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.refact.rec.CameraManager.getVideoSize(int, int, java.util.List):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void initCamera(int r5) {
        /*
            r4 = this;
            android.hardware.Camera r0 = r4.mCamera
            if (r0 == 0) goto L7
            r4.freeCameraResource()
        L7:
            monitor-enter(r4)
            r0 = 1
            r1 = 0
            if (r5 != r0) goto L24
            boolean r5 = r4.checkCameraFacing(r0)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            if (r5 == 0) goto L1b
            android.hardware.Camera r5 = android.hardware.Camera.open(r0)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r4.mCamera = r5     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r4.cameraID = r0     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            goto L55
        L1b:
            android.content.Context r5 = r4.mContext     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            java.lang.String r0 = "\u6ca1\u6709\u524d\u7f00\u6444\u50cf\u5934\uff0c\u8bf7\u68c0\u67e5"
        L1f:
            android.widget.Toast r5 = android.widget.Toast.makeText(r5, r0, r1)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            goto L38
        L24:
            boolean r5 = r4.checkCameraFacing(r1)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            if (r5 == 0) goto L33
            android.hardware.Camera r5 = android.hardware.Camera.open(r1)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r4.mCamera = r5     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r4.cameraID = r1     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            goto L55
        L33:
            android.content.Context r5 = r4.mContext     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            java.lang.String r0 = "\u6ca1\u6709\u540e\u7f00\u6444\u50cf\u5934\uff0c\u8bf7\u68c0\u67e5"
            goto L1f
        L38:
            r5.show()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            goto L55
        L3c:
            r5 = move-exception
            goto La6
        L3e:
            android.hardware.Camera r5 = r4.mCamera     // Catch: java.lang.Throwable -> L3c
            if (r5 == 0) goto L4a
            android.hardware.Camera r5 = r4.mCamera     // Catch: java.lang.Throwable -> L3c
            r5.release()     // Catch: java.lang.Throwable -> L3c
            r5 = 0
            r4.mCamera = r5     // Catch: java.lang.Throwable -> L3c
        L4a:
            android.content.Context r5 = r4.mContext     // Catch: java.lang.Throwable -> L3c
            java.lang.String r0 = "\u6444\u50cf\u5934\u6253\u5f00\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u6743\u9650"
            android.widget.Toast r5 = android.widget.Toast.makeText(r5, r0, r1)     // Catch: java.lang.Throwable -> L3c
            r5.show()     // Catch: java.lang.Throwable -> L3c
        L55:
            android.hardware.Camera r5 = r4.mCamera     // Catch: java.lang.Throwable -> L3c
            if (r5 != 0) goto L5b
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3c
            return
        L5b:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3c
            int r5 = com.jingdong.manto.utils.MantoDensityUtils.getDMWidthPixels()
            int r0 = com.jingdong.manto.utils.MantoDensityUtils.getDMHeightPixels()
            android.hardware.Camera$Parameters r2 = r4.getParameters()
            if (r2 != 0) goto L6b
            return
        L6b:
            java.util.List r3 = r2.getSupportedPreviewSizes()
            r4.getPreviewSize(r5, r0, r3)
            java.util.List r3 = r2.getSupportedVideoSizes()
            r4.getVideoSize(r5, r0, r3)
            java.util.List r2 = r2.getSupportedPictureSizes()
            r4.getPicSize(r5, r0, r2)
            r4.setCameraParams()
            int r5 = android.os.Build.VERSION.SDK_INT
            r0 = 17
            if (r5 < r0) goto L8e
            android.hardware.Camera r5 = r4.mCamera
            r5.enableShutterSound(r1)
        L8e:
            android.content.Context r5 = r4.mContext
            android.app.Activity r5 = (android.app.Activity) r5
            int r0 = r4.cameraID
            android.hardware.Camera r1 = r4.mCamera
            r4.setCameraDisplayOrientation(r5, r0, r1)
            android.hardware.Camera r5 = r4.mCamera     // Catch: java.lang.Exception -> La5
            android.view.SurfaceHolder r0 = r4.mSurfaceHolder     // Catch: java.lang.Exception -> La5
            r5.setPreviewDisplay(r0)     // Catch: java.lang.Exception -> La5
            android.hardware.Camera r5 = r4.mCamera     // Catch: java.lang.Exception -> La5
            r5.startPreview()     // Catch: java.lang.Exception -> La5
        La5:
            return
        La6:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3c
            goto La9
        La8:
            throw r5
        La9:
            goto La8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.refact.rec.CameraManager.initCamera(int):void");
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
