package com.jingdong.manto.jsapi.camera.record;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Build;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class b implements Camera.PreviewCallback {
    private static volatile b v;
    private Camera a;
    private Camera.Parameters b;
    private int d;

    /* renamed from: i  reason: collision with root package name */
    private MediaRecorder f13166i;

    /* renamed from: j  reason: collision with root package name */
    private String f13167j;

    /* renamed from: k  reason: collision with root package name */
    private com.jingdong.manto.jsapi.camera.record.g.b f13168k;
    private Camera.Size r;
    private c s;
    private int t;

    /* renamed from: c  reason: collision with root package name */
    private boolean f13161c = false;

    /* renamed from: e  reason: collision with root package name */
    private int f13162e = -1;

    /* renamed from: f  reason: collision with root package name */
    private int f13163f = -1;

    /* renamed from: g  reason: collision with root package name */
    private float f13164g = -1.0f;

    /* renamed from: h  reason: collision with root package name */
    private boolean f13165h = false;

    /* renamed from: l  reason: collision with root package name */
    private int f13169l = 0;

    /* renamed from: m  reason: collision with root package name */
    private int f13170m = 90;

    /* renamed from: n  reason: collision with root package name */
    private int f13171n = 0;
    private int o = 0;
    private int p = 4000000;
    private String q = "auto";
    int u = 0;

    /* loaded from: classes15.dex */
    class a implements Camera.PictureCallback {
        final /* synthetic */ f a;

        a(f fVar) {
            this.a = fVar;
        }

        @Override // android.hardware.Camera.PictureCallback
        public void onPictureTaken(byte[] bArr, Camera camera) {
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            Matrix matrix = new Matrix();
            if (b.this.d == b.this.f13162e) {
                matrix.setRotate(b.this.t);
            } else if (b.this.d == b.this.f13163f) {
                matrix.setRotate(360 - b.this.t);
                matrix.postScale(-1.0f, 1.0f);
            }
            Bitmap createBitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, true);
            if (this.a != null) {
                if (b.this.t == 90 || b.this.t == 270) {
                    this.a.a(createBitmap, true);
                } else {
                    this.a.a(createBitmap, false);
                }
            }
            b.this.a.startPreview();
            b.this.f13161c = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.jsapi.camera.record.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class C0525b implements Camera.AutoFocusCallback {
        final /* synthetic */ String a;
        final /* synthetic */ d b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Context f13172c;
        final /* synthetic */ float d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ float f13173e;

        C0525b(String str, d dVar, Context context, float f2, float f3) {
            this.a = str;
            this.b = dVar;
            this.f13172c = context;
            this.d = f2;
            this.f13173e = f3;
        }

        @Override // android.hardware.Camera.AutoFocusCallback
        public void onAutoFocus(boolean z, Camera camera) {
            try {
                if (b.this.a == null || (!z && b.this.u <= 10)) {
                    b bVar = b.this;
                    bVar.u++;
                    bVar.a(this.f13172c, this.d, this.f13173e, this.b);
                    return;
                }
                Camera.Parameters parameters = camera.getParameters();
                parameters.setFocusMode(this.a);
                camera.setParameters(parameters);
                b.this.u = 0;
                this.b.a();
            } catch (Exception e2) {
                MantoLog.e("MantoCameraView", e2);
            }
        }
    }

    /* loaded from: classes15.dex */
    public interface c {
        void a();

        void onPreviewFrame(byte[] bArr, Camera camera);
    }

    /* loaded from: classes15.dex */
    public interface d {
        void a();
    }

    /* loaded from: classes15.dex */
    public interface e {
        void a(String str);
    }

    /* loaded from: classes15.dex */
    public interface f {
        void a(Bitmap bitmap, boolean z);
    }

    private b() {
        this.d = -1;
        d();
        this.d = this.f13162e;
        this.f13167j = "";
    }

    private static int a(int i2, int i3, int i4) {
        return i2 > i4 ? i4 : i2 < i3 ? i3 : i2;
    }

    private static Rect a(float f2, float f3, float f4, Context context) {
        int intValue = Float.valueOf(f4 * 300.0f).intValue() / 2;
        RectF rectF = new RectF(a(((int) (((f2 / MantoDensityUtils.getDMWidthPixels()) * 2000.0f) - 1000.0f)) - intValue, -1000, 1000), a(((int) (((f3 / MantoDensityUtils.getDMHeightPixels()) * 2000.0f) - 1000.0f)) - intValue, -1000, 1000), r2 + r4, r3 + r4);
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    private synchronized void a(int i2) {
        Camera camera;
        try {
            this.a = Camera.open(i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            com.jingdong.manto.jsapi.camera.record.g.b bVar = this.f13168k;
            if (bVar != null) {
                bVar.onError(e2);
            }
        }
        if (Build.VERSION.SDK_INT > 17 && (camera = this.a) != null) {
            try {
                camera.enableShutterSound(false);
            } catch (Exception e3) {
                e3.printStackTrace();
                MantoLog.e("MantoCameraView", "enable shutter sound failed," + e3.getMessage());
            }
        }
        a(this.q);
    }

    private void d() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            int i3 = cameraInfo.facing;
            if (i3 == 0) {
                this.f13162e = i3;
            } else if (i3 == 1) {
                this.f13163f = i3;
            }
        }
    }

    public static synchronized b e() {
        b bVar;
        synchronized (b.class) {
            if (v == null) {
                synchronized (b.class) {
                    if (v == null) {
                        v = new b();
                    }
                }
            }
            bVar = v;
        }
        return bVar;
    }

    public int a(Context context) {
        return com.jingdong.manto.jsapi.camera.record.c.b().a(context, this.d);
    }

    public int a(Camera.Parameters parameters, int i2) {
        for (int[] iArr : parameters.getSupportedPreviewFpsRange()) {
            if (iArr[0] == iArr[1] && iArr[0] == i2) {
                parameters.setPreviewFpsRange(iArr[0], iArr[1]);
                return iArr[0];
            }
        }
        int[] iArr2 = new int[2];
        parameters.getPreviewFpsRange(iArr2);
        return iArr2[0] == iArr2[1] ? iArr2[0] : iArr2[1] / 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        this.f13168k = null;
        Camera camera = this.a;
        if (camera != null) {
            try {
                camera.setPreviewCallback(null);
                this.a.stopPreview();
                this.f13161c = false;
                this.a.release();
                this.a = null;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a(float f2, int i2) {
        int i3;
        int i4;
        int maxZoom;
        Camera camera = this.a;
        if (camera == null) {
            return;
        }
        if (this.b == null) {
            this.b = camera.getParameters();
        }
        if (this.b.isZoomSupported() && this.b.isSmoothZoomSupported()) {
            if (i2 == 144) {
                if (this.f13165h && f2 >= 0.0f && (i3 = (int) (f2 / 40.0f)) <= this.b.getMaxZoom() && i3 >= this.f13171n && this.o != i3) {
                    this.b.setZoom(i3);
                    this.a.setParameters(this.b);
                    this.o = i3;
                }
            } else if (i2 == 145 && !this.f13165h && (i4 = (int) (f2 / 50.0f)) < this.b.getMaxZoom()) {
                int i5 = this.f13171n + i4;
                this.f13171n = i5;
                if (i5 >= 0) {
                    maxZoom = i5 > this.b.getMaxZoom() ? this.b.getMaxZoom() : 0;
                    this.b.setZoom(this.f13171n);
                    this.a.setParameters(this.b);
                }
                this.f13171n = maxZoom;
                this.b.setZoom(this.f13171n);
                this.a.setParameters(this.b);
            }
        }
    }

    public void a(Context context, float f2, float f3, d dVar) {
        Camera camera = this.a;
        if (camera == null) {
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        Rect a2 = a(f2, f3, 1.0f, context);
        this.a.cancelAutoFocus();
        if (parameters.getMaxNumFocusAreas() <= 0) {
            dVar.a();
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Camera.Area(a2, 800));
        parameters.setFocusAreas(arrayList);
        String focusMode = parameters.getFocusMode();
        try {
            parameters.setFocusMode("auto");
            this.a.setParameters(parameters);
            this.a.autoFocus(new C0525b(focusMode, dVar, context, f2, f3));
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x011c, code lost:
        if (r0 == 270) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0127, code lost:
        if (r0 == 270) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0129, code lost:
        r0 = r7.f13166i;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x013e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.view.Surface r8) {
        /*
            Method dump skipped, instructions count: 386
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.camera.record.b.a(android.view.Surface):void");
    }

    public void a(SurfaceHolder surfaceHolder, float f2) {
        Camera camera;
        if (this.f13164g < 0.0f) {
            this.f13164g = f2;
        }
        if (surfaceHolder == null || (camera = this.a) == null) {
            return;
        }
        try {
            this.b = camera.getParameters();
            com.jingdong.manto.jsapi.camera.record.c.b().a(this.a, MantoDensityUtils.getDMWidthPixels(), MantoDensityUtils.getDMHeightPixels(), this.f13170m);
            Camera.Parameters parameters = this.a.getParameters();
            this.b = parameters;
            this.r = parameters.getPreviewSize();
            if (com.jingdong.manto.jsapi.camera.record.c.b().a(this.b.getSupportedFocusModes(), "auto")) {
                this.b.setFocusMode("auto");
            }
            if (com.jingdong.manto.jsapi.camera.record.c.b().a(this.b.getSupportedPictureFormats(), 256)) {
                this.b.setPictureFormat(256);
                this.b.setJpegQuality(80);
            }
            this.a.setParameters(this.b);
            this.b = this.a.getParameters();
            this.a.setPreviewDisplay(surfaceHolder);
            this.a.setDisplayOrientation(this.f13170m);
            this.a.setPreviewCallback(this);
            a(this.b, 30);
            this.a.startPreview();
            this.f13161c = true;
        } catch (Exception e2) {
            com.jingdong.manto.jsapi.camera.record.g.b bVar = this.f13168k;
            if (bVar != null) {
                bVar.onError(e2);
            }
        }
    }

    public synchronized void a(SurfaceHolder surfaceHolder, float f2, boolean z) {
        Camera camera;
        this.d = z ? this.f13162e : this.f13163f;
        a();
        a(this.d);
        if (Build.VERSION.SDK_INT > 17 && (camera = this.a) != null) {
            try {
                camera.enableShutterSound(false);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        a(surfaceHolder, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(c cVar, boolean z) {
        this.s = cVar;
        this.d = z ? this.f13162e : this.f13163f;
        if (this.a == null) {
            a(this.d);
        }
        cVar.a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
        if (r2 != null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(com.jingdong.manto.jsapi.camera.record.b.e r4) {
        /*
            r3 = this;
            boolean r0 = r3.f13165h
            if (r0 != 0) goto L5
            return
        L5:
            android.media.MediaRecorder r0 = r3.f13166i
            if (r0 == 0) goto L4c
            r1 = 0
            r0.setOnErrorListener(r1)
            android.media.MediaRecorder r0 = r3.f13166i
            r0.setOnInfoListener(r1)
            android.media.MediaRecorder r0 = r3.f13166i
            r0.setPreviewDisplay(r1)
            r0 = 0
            android.media.MediaRecorder r2 = r3.f13166i     // Catch: java.lang.Throwable -> L22 java.lang.RuntimeException -> L24
            r2.stop()     // Catch: java.lang.Throwable -> L22 java.lang.RuntimeException -> L24
            android.media.MediaRecorder r2 = r3.f13166i
            if (r2 == 0) goto L34
            goto L31
        L22:
            r4 = move-exception
            goto L40
        L24:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L22
            r3.f13166i = r1     // Catch: java.lang.Throwable -> L22
            android.media.MediaRecorder r2 = new android.media.MediaRecorder     // Catch: java.lang.Throwable -> L22
            r2.<init>()     // Catch: java.lang.Throwable -> L22
            r3.f13166i = r2     // Catch: java.lang.Throwable -> L22
        L31:
            r2.release()
        L34:
            r3.f13166i = r1
            r3.f13165h = r0
            if (r4 == 0) goto L4c
            java.lang.String r0 = r3.f13167j
            r4.a(r0)
            goto L4c
        L40:
            android.media.MediaRecorder r2 = r3.f13166i
            if (r2 == 0) goto L47
            r2.release()
        L47:
            r3.f13166i = r1
            r3.f13165h = r0
            throw r4
        L4c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.camera.record.b.a(com.jingdong.manto.jsapi.camera.record.b$e):void");
    }

    public void a(f fVar) {
        int abs;
        try {
            if (this.a != null && this.f13161c) {
                this.f13161c = false;
                int i2 = this.f13170m;
                if (i2 == 90) {
                    abs = Math.abs(this.f13169l + i2) % R2.attr.additionBottom;
                } else if (i2 != 270) {
                    this.a.takePicture(null, null, new a(fVar));
                } else {
                    abs = Math.abs(i2 - this.f13169l);
                }
                this.t = abs;
                this.a.takePicture(null, null, new a(fVar));
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(com.jingdong.manto.jsapi.camera.record.g.b bVar) {
        this.f13168k = bVar;
    }

    public void a(String str) {
        this.q = str;
        Camera camera = this.a;
        if (camera == null) {
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (supportedFlashModes != null && supportedFlashModes.contains(str)) {
            parameters.setFlashMode(str);
        }
        this.a.setParameters(parameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.f13161c = z;
    }

    public int b(Context context) {
        return com.jingdong.manto.jsapi.camera.record.c.b().b(context, this.d);
    }

    public void b() {
        if (this.f13165h) {
            a((e) null);
        }
        this.f13165h = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i2) {
        this.p = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f13167j = str;
        File file = new File(str);
        if (file.getParentFile().exists()) {
            return;
        }
        file.getParentFile().mkdirs();
    }

    public void c() {
        Camera camera = this.a;
        if (camera != null) {
            try {
                camera.setPreviewCallback(null);
                this.a.stopPreview();
                this.a.setPreviewDisplay(null);
                this.f13161c = false;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void c(Context context) {
        this.f13170m = com.jingdong.manto.jsapi.camera.record.c.b().a(context, this.d);
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        c cVar = this.s;
        if (cVar != null) {
            cVar.onPreviewFrame(bArr, camera);
        }
    }
}
