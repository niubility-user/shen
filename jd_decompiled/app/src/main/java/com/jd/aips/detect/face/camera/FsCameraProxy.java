package com.jd.aips.detect.face.camera;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import com.jd.aips.detect.face.FaceDetector;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes12.dex */
public class FsCameraProxy implements Camera.AutoFocusCallback {
    private final Activity a;
    private Camera b;

    /* renamed from: c  reason: collision with root package name */
    private Camera.Parameters f1620c;

    /* renamed from: f  reason: collision with root package name */
    private int f1622f;

    /* renamed from: g  reason: collision with root package name */
    private int f1623g;

    /* renamed from: h  reason: collision with root package name */
    private int f1624h;

    /* renamed from: i  reason: collision with root package name */
    private int f1625i;

    /* renamed from: l  reason: collision with root package name */
    private Camera.PreviewCallback f1628l;

    /* renamed from: m  reason: collision with root package name */
    private final OrientationEventListener f1629m;
    private byte[] o;
    private int p;
    private final Camera.CameraInfo d = new Camera.CameraInfo();

    /* renamed from: e  reason: collision with root package name */
    private int f1621e = 1;

    /* renamed from: j  reason: collision with root package name */
    private int f1626j = 0;

    /* renamed from: k  reason: collision with root package name */
    private int f1627k = 0;

    /* renamed from: n  reason: collision with root package name */
    private int f1630n = 0;

    public FsCameraProxy(Activity activity, int i2, int i3) {
        this.p = 1;
        this.a = activity;
        this.p = i3;
        this.f1629m = new OrientationEventListener(activity) { // from class: com.jd.aips.detect.face.camera.FsCameraProxy.1
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i4) {
                FsCameraProxy.a(FsCameraProxy.this, i4);
            }
        };
    }

    static void a(FsCameraProxy fsCameraProxy, int i2) {
        int i3;
        fsCameraProxy.getClass();
        if (i2 == -1) {
            return;
        }
        int i4 = ((i2 + 45) / 90) * 90;
        Camera.CameraInfo cameraInfo = fsCameraProxy.d;
        if (cameraInfo.facing == 1) {
            i3 = ((cameraInfo.orientation - i4) + R2.attr.additionBottom) % R2.attr.additionBottom;
        } else {
            i3 = (cameraInfo.orientation + i4) % R2.attr.additionBottom;
        }
        fsCameraProxy.f1630n = i3;
    }

    private void b() {
        int i2;
        int rotation = this.a.getWindowManager().getDefaultDisplay().getRotation();
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
        this.f1627k = i3;
        Camera.CameraInfo cameraInfo = this.d;
        if (cameraInfo != null) {
            if (cameraInfo.facing == 1) {
                i2 = (360 - ((cameraInfo.orientation + i3) % R2.attr.additionBottom)) % R2.attr.additionBottom;
            } else {
                i2 = ((cameraInfo.orientation - i3) + R2.attr.additionBottom) % R2.attr.additionBottom;
            }
            Camera camera = this.b;
            if (camera != null) {
                camera.setDisplayOrientation(i2);
            }
            this.f1626j = i2;
        }
    }

    public void cameraSupportInfoCallBack() {
        FaceDetector.getInstance().onError(2001, "camera exception, please check!");
    }

    public void focusOnPoint(int i2, int i3, int i4, int i5) {
        String str = "touch point (" + i2 + ", " + i3 + ")";
        Camera camera = this.b;
        if (camera == null) {
            return;
        }
        try {
            Camera.Parameters parameters = camera.getParameters();
            if (parameters.getMaxNumFocusAreas() > 0) {
                int min = Math.min(i4, i5) >> 3;
                int i6 = i2 - min;
                int i7 = i3 - min;
                int i8 = i2 + min;
                int i9 = i3 + min;
                int i10 = 1000;
                int i11 = ((i6 * 2000) / i4) - 1000;
                int i12 = ((i7 * 2000) / i5) - 1000;
                int i13 = ((i8 * 2000) / i4) - 1000;
                int i14 = ((i9 * 2000) / i5) - 1000;
                if (i11 < -1000) {
                    i11 = -1000;
                }
                if (i12 < -1000) {
                    i12 = -1000;
                }
                if (i13 > 1000) {
                    i13 = 1000;
                }
                if (i14 <= 1000) {
                    i10 = i14;
                }
                String str2 = "focus area (" + i11 + ", " + i12 + ", " + i13 + ", " + i10 + ")";
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Camera.Area(new Rect(i11, i12, i13, i10), 600));
                parameters.setFocusAreas(arrayList);
            }
            this.b.cancelAutoFocus();
            this.b.setParameters(parameters);
            this.b.autoFocus(this);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public Camera getCamera() {
        return this.b;
    }

    public int getDegrees_for_pre() {
        return this.f1626j;
    }

    public int getLatestRotation() {
        return this.f1630n;
    }

    public int getmPreviewHeight() {
        return this.f1623g;
    }

    public int getmPreviewWidth() {
        return this.f1622f;
    }

    public int getmSurFaceViewPreviewHeight() {
        return this.f1625i;
    }

    public int getmSurFaceViewPreviewWidth() {
        return this.f1624h;
    }

    public int getmdegrees() {
        return this.f1627k;
    }

    public void handleZoom(boolean z) {
        try {
            Camera.Parameters parameters = this.f1620c;
            if (parameters == null || !parameters.isZoomSupported()) {
                return;
            }
            int maxZoom = this.f1620c.getMaxZoom();
            int zoom = this.f1620c.getZoom();
            if (z && zoom < maxZoom) {
                zoom++;
            } else if (zoom > 0) {
                zoom--;
            }
            String str = "handleZoom: zoom: " + zoom;
            this.f1620c.setZoom(zoom);
            Camera camera = this.b;
            if (camera != null) {
                camera.setParameters(this.f1620c);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean isFrontCamera() {
        return this.d.facing == 1;
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public void onAutoFocus(boolean z, Camera camera) {
        String str = "onAutoFocus: " + z;
    }

    public void openCamera(int i2, int i3) {
        String str = "open camera, cameraId: " + this.f1621e;
        this.f1624h = i2;
        this.f1625i = i3;
        try {
            this.b = Camera.open(this.f1621e);
            Camera.getCameraInfo(this.f1621e, this.d);
            a();
            b();
            this.f1629m.enable();
        } catch (Exception unused) {
            cameraSupportInfoCallBack();
        }
    }

    public void releaseCamera() {
        Camera camera = this.b;
        if (camera != null) {
            try {
                camera.setPreviewCallback(null);
                this.b.setOneShotPreviewCallback(null);
                this.b.stopPreview();
                this.b.release();
                this.b = null;
            } catch (Exception unused) {
            }
        }
        this.f1629m.disable();
    }

    public void setDegrees_for_pre(int i2) {
        this.f1626j = i2;
    }

    public void setExposureCompensation0() {
        if (this.b != null) {
            this.f1620c.setExposureCompensation(0);
            this.b.setParameters(this.f1620c);
        }
    }

    public void setExposureCompensationMax() {
        Camera.Parameters parameters;
        if (this.b == null || (parameters = this.f1620c) == null) {
            return;
        }
        parameters.setExposureCompensation(parameters.getMaxExposureCompensation());
        this.b.setParameters(this.f1620c);
    }

    public void setExposureCompensationMin() {
        Camera.Parameters parameters;
        if (this.b == null || (parameters = this.f1620c) == null) {
            return;
        }
        parameters.setExposureCompensation(parameters.getMinExposureCompensation());
        this.b.setParameters(this.f1620c);
    }

    public void setOneShotPreviewCallback(Camera.PreviewCallback previewCallback) {
        Camera camera = this.b;
        if (camera != null) {
            camera.setOneShotPreviewCallback(previewCallback);
        }
    }

    public void setPreviewCallback(Camera.PreviewCallback previewCallback) {
        if (this.f1628l == null) {
            this.f1628l = previewCallback;
        }
    }

    public void startPreview(SurfaceHolder surfaceHolder) {
        Camera camera = this.b;
        if (camera != null) {
            try {
                camera.setPreviewDisplay(surfaceHolder);
            } catch (IOException unused) {
            }
            this.b.startPreview();
        }
    }

    public void stopPreview() {
        Camera camera = this.b;
        if (camera != null) {
            camera.stopPreview();
        }
    }

    public void switchCamera() {
        this.f1621e ^= 1;
        releaseCamera();
        openCamera(this.f1624h, this.f1625i);
    }

    public void takePicture(Camera.PictureCallback pictureCallback) {
        this.b.takePicture(null, null, pictureCallback);
    }

    public void startPreview(SurfaceTexture surfaceTexture) {
        try {
            Camera camera = this.b;
            if (camera != null) {
                try {
                    camera.setPreviewTexture(surfaceTexture);
                } catch (IOException unused) {
                }
                if (1 == this.p) {
                    if (this.o == null) {
                        this.o = new byte[((this.f1622f * this.f1623g) * 3) / 2];
                    }
                    this.b.addCallbackBuffer(this.o);
                    this.b.setPreviewCallbackWithBuffer(this.f1628l);
                } else {
                    this.b.setPreviewCallback(this.f1628l);
                }
                this.b.startPreview();
            }
        } catch (Exception unused2) {
            cameraSupportInfoCallBack();
        }
    }

    private void a() {
        try {
            Camera.Parameters parameters = this.b.getParameters();
            this.f1620c = parameters;
            List<String> supportedFlashModes = parameters.getSupportedFlashModes();
            if (supportedFlashModes != null && supportedFlashModes.contains(DebugKt.DEBUG_PROPERTY_VALUE_OFF)) {
                this.f1620c.setFlashMode(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
            }
            List<String> supportedFocusModes = this.f1620c.getSupportedFocusModes();
            if (supportedFocusModes != null && supportedFocusModes.contains("auto")) {
                this.f1620c.setFocusMode("auto");
            }
            if (this.f1620c.isAutoWhiteBalanceLockSupported() && this.f1620c.getAutoWhiteBalanceLock()) {
                this.f1620c.setAutoWhiteBalanceLock(false);
            }
            if (this.f1620c.isAutoExposureLockSupported() && this.f1620c.getAutoExposureLock()) {
                this.f1620c.setAutoExposureLock(false);
            }
            this.f1620c.setPreviewFormat(17);
            this.f1620c.setPictureFormat(256);
            Camera.Size a = a(this.f1620c.getSupportedPreviewSizes(), 1.3333333730697632d, 480);
            this.f1622f = a.width;
            this.f1623g = a.height;
            String str = "optimalPreviewWidth, width: " + this.f1622f + ", optimalPreviewHeight: " + this.f1623g;
            this.f1620c.setPreviewSize(this.f1622f, this.f1623g);
            this.b.setParameters(this.f1620c);
        } catch (Exception unused) {
            cameraSupportInfoCallBack();
        }
    }

    public void startPreview() {
        try {
            Camera camera = this.b;
            if (camera != null) {
                camera.startPreview();
            }
        } catch (Exception unused) {
            cameraSupportInfoCallBack();
        }
    }

    private Camera.Size a(List<Camera.Size> list, double d, int i2) {
        Camera.Size size = null;
        if (list == null) {
            return null;
        }
        double d2 = Double.MAX_VALUE;
        double d3 = Double.MAX_VALUE;
        for (Camera.Size size2 : list) {
            double d4 = size2.width;
            double d5 = size2.height;
            Double.isNaN(d4);
            Double.isNaN(d5);
            if (Math.abs((d4 / d5) - d) <= 0.1d) {
                double abs = Math.abs(size2.height - i2);
                if (abs < d3) {
                    size = size2;
                    d3 = abs;
                }
            }
        }
        if (size == null) {
            for (Camera.Size size3 : list) {
                if (Math.abs(size3.height - i2) < d2) {
                    d2 = Math.abs(size3.height - i2);
                    size = size3;
                }
            }
        }
        return size;
    }
}
