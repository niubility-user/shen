package com.jingdong.manto.jsapi.camera;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.facebook.react.modules.appstate.AppStateModule;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.R;
import com.jingdong.manto.jsapi.camera.record.MantoCameraView;
import com.jingdong.manto.jsapi.refact.JsApiScanCode;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.YuvUtil;
import com.jingdong.manto.utils.o;
import com.jingdong.manto.utils.x;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class MantoCameraViewContainer extends RelativeLayout implements n.f0, n.d0, n.e0, n.g0, n.c0 {
    byte[] A;
    private Camera.Size B;
    private ImageView a;
    private n b;

    /* renamed from: c  reason: collision with root package name */
    private String f13129c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private String f13130e;

    /* renamed from: f  reason: collision with root package name */
    private String f13131f;

    /* renamed from: g  reason: collision with root package name */
    private String f13132g;

    /* renamed from: h  reason: collision with root package name */
    private int f13133h;

    /* renamed from: i  reason: collision with root package name */
    private int f13134i;

    /* renamed from: j  reason: collision with root package name */
    private int f13135j;

    /* renamed from: k  reason: collision with root package name */
    private int f13136k;

    /* renamed from: l  reason: collision with root package name */
    private com.jingdong.manto.jsapi.camera.a f13137l;

    /* renamed from: m  reason: collision with root package name */
    public MantoCameraView f13138m;

    /* renamed from: n  reason: collision with root package name */
    private String f13139n;
    private String o;
    private String p;
    private int q;
    private long r;
    private String s;
    private Context t;
    private Handler u;
    private boolean v;
    private volatile boolean w;
    int x;
    int y;
    byte[] z;

    /* loaded from: classes15.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoCameraViewContainer.this.j();
        }
    }

    /* loaded from: classes15.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoCameraViewContainer.this.g();
        }
    }

    /* loaded from: classes15.dex */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoCameraViewContainer.this.o();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class d implements com.jingdong.manto.jsapi.camera.record.g.a {
        d() {
        }

        @Override // com.jingdong.manto.jsapi.camera.record.g.a
        public void a(Bitmap bitmap) {
            MantoCameraViewContainer mantoCameraViewContainer;
            String str;
            String str2 = null;
            int i2 = -1;
            if (bitmap == null) {
                mantoCameraViewContainer = MantoCameraViewContainer.this;
                str = "bitmap is null";
            } else {
                MantoCameraViewContainer mantoCameraViewContainer2 = MantoCameraViewContainer.this;
                if (mantoCameraViewContainer2.a(bitmap, mantoCameraViewContainer2.p)) {
                    mantoCameraViewContainer = MantoCameraViewContainer.this;
                    str2 = mantoCameraViewContainer.a(mantoCameraViewContainer.p);
                    i2 = 0;
                    str = "";
                } else {
                    mantoCameraViewContainer = MantoCameraViewContainer.this;
                    str = "save fail";
                }
            }
            MantoCameraViewContainer.a(mantoCameraViewContainer, i2, str2, str);
        }

        @Override // com.jingdong.manto.jsapi.camera.record.g.a
        public void a(String str) {
            MantoCameraViewContainer mantoCameraViewContainer = MantoCameraViewContainer.this;
            mantoCameraViewContainer.a(x.a(mantoCameraViewContainer.f13139n, MantoCameraViewContainer.this.f13133h, MantoCameraViewContainer.this.f13134i), MantoCameraViewContainer.this.o);
            MantoCameraViewContainer mantoCameraViewContainer2 = MantoCameraViewContainer.this;
            mantoCameraViewContainer2.a(0, "", mantoCameraViewContainer2.o, MantoCameraViewContainer.this.f13139n);
        }

        @Override // com.jingdong.manto.jsapi.camera.record.g.a
        public void onPreviewFrame(byte[] bArr, Camera camera) {
            if (!MantoCameraViewContainer.this.w || MantoCameraViewContainer.this.f13137l == null || bArr == null) {
                return;
            }
            MantoCameraViewContainer.this.a(bArr, camera);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class e implements com.jingdong.manto.jsapi.camera.record.g.b {

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(MantoCameraViewContainer.this.t, R.string.manto_open_error_camera, 0).show();
            }
        }

        e() {
        }

        @Override // com.jingdong.manto.jsapi.camera.record.g.b
        public void onError(Throwable th) {
            MantoCameraViewContainer.this.f13138m.post(new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class f implements Runnable {
        final /* synthetic */ Camera a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13140c;
        final /* synthetic */ byte[] d;

        f(Camera camera, int i2, int i3, byte[] bArr) {
            this.a = camera;
            this.b = i2;
            this.f13140c = i3;
            this.d = bArr;
        }

        @Override // java.lang.Runnable
        @TargetApi(17)
        public void run() {
            if (MantoCameraViewContainer.this.w) {
                if (MantoCameraViewContainer.this.B == null) {
                    MantoCameraViewContainer.this.B = this.a.getParameters().getPreviewSize();
                }
                int i2 = MantoCameraViewContainer.this.B.width;
                int i3 = MantoCameraViewContainer.this.B.height;
                try {
                    int i4 = this.b;
                    int i5 = this.f13140c;
                    if (i5 != 270) {
                        i5 = i4;
                    }
                    byte[] bArr = this.d;
                    MantoCameraViewContainer mantoCameraViewContainer = MantoCameraViewContainer.this;
                    YuvUtil.yuvCompress(bArr, i2, i3, mantoCameraViewContainer.A, mantoCameraViewContainer.y, mantoCameraViewContainer.x, 0, i4, i5 == 270);
                    MantoCameraViewContainer mantoCameraViewContainer2 = MantoCameraViewContainer.this;
                    YuvUtil.yuvI420ToARGB(mantoCameraViewContainer2.A, mantoCameraViewContainer2.x, mantoCameraViewContainer2.y, 0, mantoCameraViewContainer2.z);
                    com.jingdong.manto.jsapi.camera.a aVar = MantoCameraViewContainer.this.f13137l;
                    MantoCameraViewContainer mantoCameraViewContainer3 = MantoCameraViewContainer.this;
                    aVar.a("", mantoCameraViewContainer3.z, mantoCameraViewContainer3.x, mantoCameraViewContainer3.y);
                } catch (Exception e2) {
                    try {
                        MantoCameraViewContainer.this.f13137l.a("fail: " + e2.getMessage(), (byte[]) null, -1, -1);
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                    MantoLog.e("MantoCameraView", e2);
                }
            }
        }
    }

    public MantoCameraViewContainer(Context context) {
        this(context, null);
    }

    public MantoCameraViewContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MantoCameraViewContainer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f13129c = "normal";
        this.f13130e = ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID;
        this.f13131f = "auto";
        this.f13132g = "high";
        this.f13133h = R2.attr.counterOverflowTextColor;
        this.f13134i = R2.attr.lineSpacing;
        this.f13135j = R2.attr.counterOverflowTextColor;
        this.f13136k = R2.attr.lineSpacing;
        this.q = -1;
        this.r = -1L;
        this.v = false;
        this.w = false;
        this.x = R2.attr.counterOverflowTextColor;
        this.y = R2.attr.lineSpacing;
        this.z = new byte[3686400];
        this.A = new byte[1382400];
        a(context);
    }

    private void a(int i2, String str) {
        com.jingdong.manto.jsapi.camera.a aVar = this.f13137l;
        if (aVar != null) {
            aVar.a(i2, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, String str, String str2, String str3) {
        com.jingdong.manto.jsapi.camera.a aVar = this.f13137l;
        if (aVar != null) {
            aVar.a(i2, a(str2), a(str3), str);
        }
        p();
    }

    private void a(Context context) {
        this.t = context;
        LayoutInflater.from(context).inflate(R.layout.manto_ui_camera_container, this);
    }

    static void a(MantoCameraViewContainer mantoCameraViewContainer, int i2, String str, String str2) {
        com.jingdong.manto.jsapi.camera.a aVar = mantoCameraViewContainer.f13137l;
        if (aVar != null) {
            aVar.a(i2, str, str2, mantoCameraViewContainer.f13135j, mantoCameraViewContainer.f13136k);
        }
        mantoCameraViewContainer.c();
        mantoCameraViewContainer.q = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(byte[] bArr, Camera camera) {
        getBackgroundHandler().post(new f(camera, com.jingdong.manto.jsapi.camera.record.b.e().a(getContext()), com.jingdong.manto.jsapi.camera.record.b.e().b(getContext()), bArr));
    }

    private void c() {
        this.p = o.d + String.format("%s%d.%s", "capture", Long.valueOf(System.currentTimeMillis()), "jpg");
    }

    private void d() {
        String str = "Manto_" + System.currentTimeMillis();
        this.f13139n = o.d + str + PreDownLoadManager.VIDEO_SKU_SUFFIX;
        this.o = o.d + str + ".jpg";
        MantoCameraView mantoCameraView = this.f13138m;
        if (mantoCameraView != null) {
            mantoCameraView.setVideoFileFullPath(this.f13139n);
        }
    }

    private void e() {
        if (!j.a().b()) {
            Toast.makeText(this.t, R.string.manto_open_error_camera, 0).show();
            return;
        }
        MantoCameraView mantoCameraView = this.f13138m;
        if (mantoCameraView == null || mantoCameraView.getParent() != this) {
            ImageView imageView = this.a;
            if (imageView == null) {
                ImageView imageView2 = new ImageView(this.t);
                this.a = imageView2;
                imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
                addView(this.a, new RelativeLayout.LayoutParams(-1, -1));
            } else {
                imageView.setImageBitmap(null);
            }
            f();
            addView(this.f13138m);
            this.f13138m.setVideoFileFullPath(this.f13139n);
            this.f13138m.setUseBackCamera(ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID.endsWith(this.f13130e));
            this.q = 1;
        }
    }

    private void f() {
        MantoCameraView mantoCameraView = new MantoCameraView(this.t);
        this.f13138m = mantoCameraView;
        mantoCameraView.setCameraListener(new d());
        this.f13138m.setErrorListener(new e());
    }

    private Handler getBackgroundHandler() {
        if (this.u == null) {
            HandlerThread handlerThread = new HandlerThread(AppStateModule.APP_STATE_BACKGROUND);
            handlerThread.start();
            this.u = new Handler(handlerThread.getLooper());
        }
        return this.u;
    }

    private void i() {
        if (this.q == 2) {
            HashMap hashMap = new HashMap();
            hashMap.put("cameraId", Integer.valueOf(this.d));
            hashMap.put("errMsg", "stop on record");
            String jSONObject = new JSONObject(hashMap).toString();
            com.jingdong.manto.m.d a2 = new i().a(this.b);
            a2.f13315c = jSONObject;
            a2.a();
        }
        k();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("cameraId", Integer.valueOf(this.d));
        hashMap2.put("errMsg", "stop on pause");
        String jSONObject2 = new JSONObject(hashMap2).toString();
        com.jingdong.manto.m.d a3 = new h().a(this.b);
        a3.f13315c = jSONObject2;
        a3.a();
        this.B = null;
        com.jingdong.manto.jsapi.camera.record.b.e().b();
    }

    private void l() {
        if (TextUtils.isEmpty(this.f13129c)) {
            return;
        }
        this.f13129c.equals(JsApiScanCode.JSAPI_NAME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void o() {
        String str;
        MantoCameraView mantoCameraView = this.f13138m;
        if (mantoCameraView == null) {
            str = "camera is null";
        } else if (this.q == 2) {
            mantoCameraView.e();
            this.q = -1;
            return;
        } else {
            str = "is not recording";
        }
        a(-1, str, (String) null, (String) null);
    }

    final String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        com.jingdong.manto.t.d a2 = com.jingdong.manto.t.c.a(this.s, str, true);
        if (a2 != null) {
            return a2.a;
        }
        return null;
    }

    public final void a() {
        String str;
        j a2 = j.a();
        if (!(a2.a && a2.b)) {
            Toast.makeText(this.t, R.string.manto_open_error_camera, 0).show();
            str = "no camera&record permission";
        } else if (this.q != 2) {
            d();
            this.r = SystemClock.elapsedRealtime();
            this.f13138m.f();
            this.q = 2;
            a(0, "");
            return;
        } else {
            str = "is recording";
        }
        a(-1, str);
    }

    public final void a(int i2, int i3, int i4, int i5) {
        if (i4 <= 0 || i5 <= 0) {
            return;
        }
        new Rect(i2, i3, i4 + i2, i5 + i3);
    }

    public final void a(String str, boolean z) {
        MantoCameraView mantoCameraView;
        if (MantoStringUtils.isEquals(this.f13130e, str) || MantoStringUtils.isEquals(this.f13129c, "scanMode") || this.q == 2) {
            return;
        }
        this.f13130e = str;
        if (z || (mantoCameraView = this.f13138m) == null) {
            return;
        }
        mantoCameraView.a(ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID.equals(str));
    }

    public final boolean a(int i2, int i3) {
        if (this.f13133h == i2 && this.f13134i == i3) {
            return false;
        }
        this.f13133h = i2;
        this.f13134i = i3;
        return true;
    }

    final boolean a(Bitmap bitmap, String str) {
        int i2;
        int i3;
        if (bitmap != null && !bitmap.isRecycled()) {
            try {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                if (width != 0 && height != 0) {
                    if ("normal".equals(this.f13132g)) {
                        i2 = (height * 2) / 3;
                        i3 = (width * 2) / 3;
                    } else if ("low".equals(this.f13132g)) {
                        i2 = height / 2;
                        i3 = width / 2;
                    }
                    bitmap = com.jingdong.manto.sdk.b.a(bitmap, i2, i3, false, true);
                }
                this.f13135j = bitmap.getWidth();
                this.f13136k = bitmap.getHeight();
                com.jingdong.manto.sdk.b.a(bitmap, "high".equals(this.f13132g) ? 90 : 75, Bitmap.CompressFormat.JPEG, str, true);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public final void b() {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.r;
        if (elapsedRealtime < 1500) {
            postDelayed(new c(), 1500 - elapsedRealtime);
        } else {
            o();
        }
    }

    public final void g() {
        if (j.a().b()) {
            d();
            c();
            e();
            q();
        }
    }

    public int getCameraId() {
        return this.d;
    }

    public boolean h() {
        return this.v;
    }

    public void j() {
        i();
    }

    public final void k() {
        synchronized (MantoCameraViewContainer.class) {
            MantoCameraView mantoCameraView = this.f13138m;
            if (mantoCameraView != null) {
                removeView(mantoCameraView);
                this.q = -1;
            }
        }
    }

    public final void m() {
        this.w = true;
    }

    public final void n() {
        this.w = false;
    }

    @Override // com.jingdong.manto.q.n.d0
    public void onBackground() {
        MantoThreadUtils.runOnUIThread(new a());
    }

    @Override // com.jingdong.manto.q.n.c0
    public void onDestroy() {
        i();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Handler handler = this.u;
        if (handler != null) {
            int i2 = Build.VERSION.SDK_INT;
            Looper looper = handler.getLooper();
            if (i2 >= 18) {
                looper.quitSafely();
            } else {
                looper.quit();
            }
            this.u = null;
        }
    }

    @Override // com.jingdong.manto.q.n.e0
    public void onForeground() {
        MantoThreadUtils.runOnUIThread(new b());
    }

    @Override // com.jingdong.manto.q.n.g0
    public void onReady() {
    }

    public final void p() {
        MantoCameraView mantoCameraView = this.f13138m;
        if (mantoCameraView == null || this.f13131f == null) {
            return;
        }
        int i2 = this.q;
        if (i2 != 2 && i2 != 4) {
            if (mantoCameraView.getFlashMode() == 1) {
                this.f13138m.setFlashMode(2);
            }
            if (this.f13131f.equals("auto")) {
                this.f13138m.setFlashMode(3);
            }
        }
        if (this.f13131f.equals("on")) {
            this.f13138m.setFlashMode(1);
        } else {
            this.f13138m.setFlashMode(2);
        }
    }

    public final void q() {
        MantoCameraView mantoCameraView = this.f13138m;
        if (mantoCameraView == null) {
            return;
        }
        mantoCameraView.setUseBackCamera(ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID.endsWith(this.f13130e));
        l();
        p();
    }

    public void setAppUniqueId(String str) {
        this.s = str;
    }

    public void setCameraId(int i2) {
        this.d = i2;
    }

    public void setFlash(String str) {
        if (MantoStringUtils.isEquals(this.f13131f, str)) {
            return;
        }
        this.f13131f = str;
    }

    public void setFrontIsHide(boolean z) {
        MantoCameraView mantoCameraView = this.f13138m;
        if (mantoCameraView == null || z == this.v) {
            return;
        }
        this.v = z;
        if (z) {
            mantoCameraView.c();
        } else {
            mantoCameraView.d();
        }
    }

    public void setMode(String str) {
        this.f13129c = str;
    }

    public void setNeedOutput(boolean z) {
    }

    public void setOperateCallback(com.jingdong.manto.jsapi.camera.a aVar) {
        this.f13137l = aVar;
    }

    public void setPage(n nVar) {
        this.b = nVar;
    }

    public void setQuality(String str) {
        if (MantoStringUtils.isEquals(this.f13132g, str)) {
            return;
        }
        this.f13132g = str;
    }

    public void setScanFreq(int i2) {
    }

    public void setZoom(float f2) {
        com.jingdong.manto.jsapi.camera.record.b.e().a(f2, this.q == 2 ? 144 : R2.anim.message_center_dialog_out);
    }
}
