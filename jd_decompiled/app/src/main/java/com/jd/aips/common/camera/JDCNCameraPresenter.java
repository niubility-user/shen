package com.jd.aips.common.camera;

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
import com.jd.aips.common.utils.ScreenUtil;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes12.dex */
public class JDCNCameraPresenter extends HandlerThread implements Camera.ErrorCallback {
    private volatile Camera a;
    private Camera.Size b;

    /* renamed from: c  reason: collision with root package name */
    private Camera.PreviewCallback f1556c;
    private JDCNCameraParamCallback d;

    /* renamed from: e  reason: collision with root package name */
    private ICameraView f1557e;

    /* renamed from: f  reason: collision with root package name */
    private byte[] f1558f;

    /* renamed from: g  reason: collision with root package name */
    private volatile boolean f1559g;

    /* renamed from: h  reason: collision with root package name */
    private volatile boolean f1560h;

    /* renamed from: i  reason: collision with root package name */
    private PreviewHandler f1561i;

    /* renamed from: j  reason: collision with root package name */
    private long f1562j;

    /* renamed from: k  reason: collision with root package name */
    private SurfaceHolder f1563k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f1564l;

    /* loaded from: classes12.dex */
    public interface ICameraView {
        void cameraException(Exception exc);

        Context getMVPContext();

        void resizeSurface(Camera.Size size);
    }

    /* loaded from: classes12.dex */
    private static class PreviewHandler extends Handler {
        private WeakReference<JDCNCameraPresenter> a;

        PreviewHandler(JDCNCameraPresenter jDCNCameraPresenter, Looper looper) {
            super(looper);
            this.a = new WeakReference<>(jDCNCameraPresenter);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            JDCNCameraPresenter jDCNCameraPresenter = this.a.get();
            if (jDCNCameraPresenter != null) {
                switch (message.what) {
                    case 16:
                        jDCNCameraPresenter.b((SurfaceHolder) message.obj);
                        return;
                    case 17:
                        JDCNCameraPresenter.a(jDCNCameraPresenter);
                        return;
                    case 18:
                        jDCNCameraPresenter.a();
                        return;
                    case 19:
                        JDCNCameraPresenter.c(jDCNCameraPresenter);
                        return;
                    case 20:
                        JDCNCameraPresenter.d(jDCNCameraPresenter);
                        return;
                    case 21:
                        jDCNCameraPresenter.a((SurfaceHolder) message.obj);
                        return;
                    case 22:
                        JDCNCameraPresenter.e(jDCNCameraPresenter);
                        return;
                    case 23:
                        JDCNCameraPresenter.f(jDCNCameraPresenter);
                        return;
                    case 24:
                        JDCNCameraPresenter.g(jDCNCameraPresenter);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public JDCNCameraPresenter(ICameraView iCameraView) {
        super("IDCARD_PREVIEW");
        this.f1559g = true;
        this.f1560h = false;
        this.f1564l = false;
        this.f1557e = iCameraView;
        start();
        this.f1561i = new PreviewHandler(this, getLooper());
    }

    static void c(JDCNCameraPresenter jDCNCameraPresenter) {
        if (jDCNCameraPresenter.a == null || jDCNCameraPresenter.f1559g) {
            return;
        }
        jDCNCameraPresenter.a.startPreview();
        jDCNCameraPresenter.a();
    }

    static void d(JDCNCameraPresenter jDCNCameraPresenter) {
        if (jDCNCameraPresenter.a == null || jDCNCameraPresenter.f1559g || jDCNCameraPresenter.f1558f == null) {
            return;
        }
        jDCNCameraPresenter.a.addCallbackBuffer(jDCNCameraPresenter.f1558f);
    }

    static void e(JDCNCameraPresenter jDCNCameraPresenter) {
        if (jDCNCameraPresenter.a != null) {
            try {
                jDCNCameraPresenter.f1559g = true;
                jDCNCameraPresenter.a.setPreviewCallback(null);
                jDCNCameraPresenter.a.setPreviewCallbackWithBuffer(null);
                jDCNCameraPresenter.a.setErrorCallback(null);
                jDCNCameraPresenter.a.stopPreview();
                jDCNCameraPresenter.a.release();
                jDCNCameraPresenter.a = null;
            } catch (Exception unused) {
            }
        }
    }

    static void f(JDCNCameraPresenter jDCNCameraPresenter) {
        if (jDCNCameraPresenter.f1564l) {
            return;
        }
        if (jDCNCameraPresenter.f1557e != null && jDCNCameraPresenter.a != null) {
            for (FeatureInfo featureInfo : jDCNCameraPresenter.f1557e.getMVPContext().getPackageManager().getSystemAvailableFeatures()) {
                if ("android.hardware.camera.flash".equals(featureInfo.name)) {
                    try {
                        Camera.Parameters parameters = jDCNCameraPresenter.a.getParameters();
                        if (parameters != null) {
                            parameters.setFlashMode("torch");
                            jDCNCameraPresenter.a.setParameters(parameters);
                            jDCNCameraPresenter.a.startPreview();
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        jDCNCameraPresenter.f1564l = true;
    }

    static void g(JDCNCameraPresenter jDCNCameraPresenter) {
        if (jDCNCameraPresenter.f1564l) {
            if (jDCNCameraPresenter.a != null) {
                try {
                    Camera.Parameters parameters = jDCNCameraPresenter.a.getParameters();
                    parameters.setFlashMode(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                    jDCNCameraPresenter.a.setParameters(parameters);
                } catch (Exception unused) {
                }
            }
            jDCNCameraPresenter.f1564l = false;
        }
    }

    public void addCallbackBuffer() {
        this.f1561i.sendEmptyMessage(20);
    }

    public void closeFlash() {
        this.f1561i.sendEmptyMessage(24);
    }

    public void focus() {
        if (this.f1560h) {
            return;
        }
        this.f1561i.sendEmptyMessage(18);
    }

    @Override // android.hardware.Camera.ErrorCallback
    public void onError(int i2, Camera camera) {
        if (2 == i2) {
            try {
                if (0 == this.f1562j || System.currentTimeMillis() - this.f1562j > Final.FIVE_SECOND) {
                    if (camera != null) {
                        camera.release();
                    }
                    b(this.f1563k);
                }
                this.f1562j = System.currentTimeMillis();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void openCamera(SurfaceHolder surfaceHolder) {
        PreviewHandler previewHandler = this.f1561i;
        previewHandler.sendMessage(previewHandler.obtainMessage(16, surfaceHolder));
    }

    public void openFlash() {
        this.f1561i.sendEmptyMessage(23);
    }

    public void release() {
        this.f1561i.sendEmptyMessage(17);
    }

    public void releaseCamera() {
        this.f1561i.sendEmptyMessage(22);
    }

    public void setPreviewCallback(Camera.PreviewCallback previewCallback) {
        this.f1556c = previewCallback;
    }

    public void setPreviewSelfCallback(JDCNCameraParamCallback jDCNCameraParamCallback) {
        this.d = jDCNCameraParamCallback;
    }

    static void a(JDCNCameraPresenter jDCNCameraPresenter) {
        try {
            jDCNCameraPresenter.f1560h = true;
            if (Build.VERSION.SDK_INT >= 18) {
                jDCNCameraPresenter.quitSafely();
            } else {
                jDCNCameraPresenter.quit();
            }
            jDCNCameraPresenter.f1563k = null;
            jDCNCameraPresenter.f1557e = null;
            jDCNCameraPresenter.f1556c = null;
            jDCNCameraPresenter.d = null;
            jDCNCameraPresenter.f1558f = null;
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(SurfaceHolder surfaceHolder) {
        try {
            this.a = JDCNCameraUtils.openCamera();
            this.a.setErrorCallback(this);
            a(surfaceHolder);
        } catch (Exception e2) {
            ICameraView iCameraView = this.f1557e;
            if (iCameraView != null) {
                iCameraView.cameraException(e2);
            }
            this.a = null;
        }
    }

    private void b() {
        Camera.Size size;
        if (this.a != null) {
            this.f1559g = false;
            ICameraView iCameraView = this.f1557e;
            if (iCameraView != null) {
                int[] realScreenWidthHeight = ScreenUtil.getRealScreenWidthHeight(iCameraView.getMVPContext());
                int i2 = realScreenWidthHeight[1];
                int i3 = realScreenWidthHeight[0];
                try {
                    Camera.Parameters parameters = this.a.getParameters();
                    ICameraView iCameraView2 = this.f1557e;
                    if (iCameraView2 != null && iCameraView2.getMVPContext().getResources().getConfiguration().orientation == 1) {
                        this.a.setDisplayOrientation(90);
                        parameters.setRotation(90);
                    } else if (this.f1557e == null) {
                        return;
                    } else {
                        this.a.setDisplayOrientation(0);
                        parameters.setRotation(0);
                    }
                    parameters.setPreviewFormat(17);
                    List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
                    Iterator<Camera.Size> it = supportedPreviewSizes.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            size = it.next();
                            if (size.width == i2 && size.height == i3) {
                                break;
                            }
                        } else {
                            float f2 = i2 / i3;
                            float f3 = Float.MAX_VALUE;
                            size = null;
                            for (Camera.Size size2 : supportedPreviewSizes) {
                                float abs = Math.abs(f2 - (size2.width / size2.height));
                                if (abs < f3) {
                                    size = size2;
                                    f3 = abs;
                                }
                            }
                        }
                    }
                    if (size != null) {
                        parameters.setPreviewSize(size.width, size.height);
                    } else if (a(parameters) == null) {
                        parameters.setPreviewSize(R2.attr.lineSpacing, R2.attr.counterOverflowTextColor);
                    } else {
                        Camera.Size a = a(parameters);
                        if (a != null) {
                            parameters.setPreviewSize(a.width, a.height);
                        }
                    }
                    this.a.setParameters(parameters);
                    if (this.f1556c != null) {
                        this.a.setPreviewCallbackWithBuffer(this.f1556c);
                        Camera.Size previewSize = parameters.getPreviewSize();
                        byte[] bArr = new byte[((previewSize.width * previewSize.height) * ImageFormat.getBitsPerPixel(17)) / 8];
                        this.f1558f = bArr;
                        JDCNCameraParamCallback jDCNCameraParamCallback = this.d;
                        if (jDCNCameraParamCallback != null) {
                            jDCNCameraParamCallback.previewBufferCreated(bArr);
                        }
                        this.a.addCallbackBuffer(this.f1558f);
                        this.b = previewSize;
                    }
                    ICameraView iCameraView3 = this.f1557e;
                    if (iCameraView3 != null) {
                        iCameraView3.resizeSurface(this.b);
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    String str = "Error setting camera preview: " + e2.getMessage();
                    try {
                        Camera.Parameters parameters2 = this.a.getParameters();
                        ICameraView iCameraView4 = this.f1557e;
                        if (iCameraView4 != null && iCameraView4.getMVPContext().getResources().getConfiguration().orientation == 1) {
                            this.a.setDisplayOrientation(90);
                            parameters2.setRotation(90);
                        } else if (this.f1557e == null) {
                            return;
                        } else {
                            this.a.setDisplayOrientation(0);
                            parameters2.setRotation(0);
                        }
                        this.a.setParameters(parameters2);
                        this.a.startPreview();
                        a();
                        ICameraView iCameraView5 = this.f1557e;
                        if (iCameraView5 != null) {
                            iCameraView5.resizeSurface(this.b);
                            return;
                        }
                        return;
                    } catch (Exception unused) {
                        this.a = null;
                        ICameraView iCameraView6 = this.f1557e;
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
        ICameraView iCameraView7 = this.f1557e;
        if (iCameraView7 != null) {
            iCameraView7.cameraException(new SecurityException("\u6ca1\u76f8\u673a\u6743\u9650"));
        }
    }

    private Camera.Size a(Camera.Parameters parameters) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.a == null || this.f1559g) {
            return;
        }
        try {
            this.a.autoFocus(null);
        } catch (Exception e2) {
            String str = "takePhoto " + e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SurfaceHolder surfaceHolder) {
        this.f1563k = surfaceHolder;
        if (this.a != null) {
            this.f1559g = false;
            try {
                this.a.setPreviewDisplay(surfaceHolder);
                b();
            } catch (IOException e2) {
                ICameraView iCameraView = this.f1557e;
                if (iCameraView != null) {
                    iCameraView.cameraException(e2);
                }
            }
            if (this.a == null || this.f1559g) {
                return;
            }
            this.a.startPreview();
            a();
        }
    }
}
