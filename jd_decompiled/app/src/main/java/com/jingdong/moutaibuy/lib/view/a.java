package com.jingdong.moutaibuy.lib.view;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import java.util.Collections;

/* loaded from: classes16.dex */
class a extends SurfaceView implements SurfaceHolder.Callback {

    /* renamed from: g  reason: collision with root package name */
    private Camera f14618g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f14619h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f14620i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f14621j;

    /* renamed from: k  reason: collision with root package name */
    private float f14622k;

    /* renamed from: l  reason: collision with root package name */
    private com.jingdong.moutaibuy.lib.c.a f14623l;

    /* renamed from: m  reason: collision with root package name */
    private b f14624m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.moutaibuy.lib.view.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class C0708a implements Camera.AutoFocusCallback {
        C0708a() {
        }

        @Override // android.hardware.Camera.AutoFocusCallback
        public void onAutoFocus(boolean z, Camera camera) {
            if (z) {
                com.jingdong.moutaibuy.lib.i.c.d("\u5bf9\u7126\u6d4b\u5149\u6210\u529f");
            } else {
                com.jingdong.moutaibuy.lib.i.c.g("\u5bf9\u7126\u6d4b\u5149\u5931\u8d25");
            }
            a.this.j();
        }
    }

    /* loaded from: classes16.dex */
    public interface b {
        void a();
    }

    public a(Context context) {
        super(context);
        this.f14619h = false;
        this.f14620i = false;
        this.f14621j = false;
        this.f14622k = 1.0f;
        getHolder().addCallback(this);
    }

    private void d(float f2, float f3, int i2, int i3) {
        boolean z;
        try {
            Camera.Parameters parameters = this.f14618g.getParameters();
            Camera.Size previewSize = parameters.getPreviewSize();
            boolean z2 = true;
            if (parameters.getMaxNumFocusAreas() > 0) {
                com.jingdong.moutaibuy.lib.i.c.d("\u652f\u6301\u8bbe\u7f6e\u5bf9\u7126\u533a\u57df");
                Rect b2 = com.jingdong.moutaibuy.lib.i.c.b(1.0f, f2, f3, i2, i3, previewSize.width, previewSize.height);
                com.jingdong.moutaibuy.lib.i.c.l("\u5bf9\u7126\u533a\u57df", b2);
                parameters.setFocusAreas(Collections.singletonList(new Camera.Area(b2, 1000)));
                parameters.setFocusMode("macro");
                z = true;
            } else {
                com.jingdong.moutaibuy.lib.i.c.d("\u4e0d\u652f\u6301\u8bbe\u7f6e\u5bf9\u7126\u533a\u57df");
                z = false;
            }
            if (parameters.getMaxNumMeteringAreas() > 0) {
                com.jingdong.moutaibuy.lib.i.c.d("\u652f\u6301\u8bbe\u7f6e\u6d4b\u5149\u533a\u57df");
                Rect b3 = com.jingdong.moutaibuy.lib.i.c.b(1.5f, f2, f3, i2, i3, previewSize.width, previewSize.height);
                com.jingdong.moutaibuy.lib.i.c.l("\u6d4b\u5149\u533a\u57df", b3);
                parameters.setMeteringAreas(Collections.singletonList(new Camera.Area(b3, 1000)));
            } else {
                com.jingdong.moutaibuy.lib.i.c.d("\u4e0d\u652f\u6301\u8bbe\u7f6e\u6d4b\u5149\u533a\u57df");
                z2 = z;
            }
            if (z2) {
                this.f14618g.cancelAutoFocus();
                this.f14618g.setParameters(parameters);
                this.f14618g.autoFocus(new C0708a());
                return;
            }
            this.f14621j = false;
        } catch (Exception e2) {
            e2.printStackTrace();
            com.jingdong.moutaibuy.lib.i.c.g("\u5bf9\u7126\u6d4b\u5149\u5931\u8d25\uff1a" + e2.getMessage());
            j();
        }
    }

    private static void e(boolean z, Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        if (parameters.isZoomSupported()) {
            int zoom = parameters.getZoom();
            if (z && zoom < parameters.getMaxZoom()) {
                com.jingdong.moutaibuy.lib.i.c.d("\u653e\u5927");
                zoom++;
            } else if (!z && zoom > 0) {
                com.jingdong.moutaibuy.lib.i.c.d("\u7f29\u5c0f");
                zoom--;
            } else {
                com.jingdong.moutaibuy.lib.i.c.d("\u65e2\u4e0d\u653e\u5927\u4e5f\u4e0d\u7f29\u5c0f");
            }
            parameters.setZoom(zoom);
            camera.setParameters(parameters);
            return;
        }
        com.jingdong.moutaibuy.lib.i.c.d("\u4e0d\u652f\u6301\u7f29\u653e");
    }

    private void i() {
        if (this.f14618g != null) {
            try {
                this.f14619h = true;
                SurfaceHolder holder = getHolder();
                holder.setKeepScreenOn(true);
                this.f14618g.setPreviewDisplay(holder);
                this.f14623l.i(this.f14618g);
                this.f14618g.startPreview();
                b bVar = this.f14624m;
                if (bVar != null) {
                    bVar.a();
                }
                j();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.f14621j = false;
        Camera camera = this.f14618g;
        if (camera == null) {
            return;
        }
        try {
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFocusMode("continuous-picture");
            this.f14618g.setParameters(parameters);
            this.f14618g.cancelAutoFocus();
        } catch (Exception unused) {
            com.jingdong.moutaibuy.lib.i.c.g("\u8fde\u7eed\u5bf9\u7126\u5931\u8d25");
        }
    }

    public int b() {
        com.jingdong.moutaibuy.lib.c.a aVar = this.f14623l;
        if (aVar != null) {
            return aVar.c();
        }
        return 0;
    }

    public Camera.Size c() {
        return this.f14623l.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f() {
        return this.f14618g != null && this.f14619h && this.f14620i;
    }

    public void g(Camera camera) {
        this.f14618g = camera;
        if (camera != null) {
            com.jingdong.moutaibuy.lib.c.a aVar = new com.jingdong.moutaibuy.lib.c.a(getContext());
            this.f14623l = aVar;
            aVar.g(this.f14618g);
            if (this.f14619h) {
                requestLayout();
            } else {
                i();
            }
        }
    }

    public void h(b bVar) {
        this.f14624m = bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k() {
        Camera camera = this.f14618g;
        if (camera != null) {
            try {
                this.f14619h = false;
                camera.cancelAutoFocus();
                this.f14618g.setOneShotPreviewCallback(null);
                this.f14618g.stopPreview();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onMeasure(int i2, int i3) {
        double d;
        double d2;
        int defaultSize = SurfaceView.getDefaultSize(getSuggestedMinimumWidth(), i2);
        int defaultSize2 = SurfaceView.getDefaultSize(getSuggestedMinimumHeight(), i3);
        com.jingdong.moutaibuy.lib.c.a aVar = this.f14623l;
        if (aVar != null) {
            if (aVar.b() != null) {
                Point b2 = this.f14623l.b();
                float f2 = defaultSize;
                float f3 = defaultSize2;
                float f4 = b2.x;
                float f5 = b2.y;
                float f6 = (f4 * 1.0f) / f5;
                if ((f2 * 1.0f) / f3 < f6) {
                    defaultSize = (int) ((f3 / ((f5 * 1.0f) / f4)) + 0.5f);
                } else {
                    defaultSize2 = (int) ((f2 / f6) + 0.5f);
                }
            }
            if (defaultSize >= defaultSize2) {
                d = defaultSize;
                d2 = defaultSize2;
                Double.isNaN(d);
                Double.isNaN(d2);
            } else {
                d = defaultSize2;
                d2 = defaultSize;
                Double.isNaN(d);
                Double.isNaN(d2);
            }
            Camera.Size a = com.jingdong.moutaibuy.lib.i.a.a(this.f14623l.f(), d / d2);
            if (a != null) {
                this.f14623l.j(a);
            }
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(defaultSize, 1073741824), View.MeasureSpec.makeMeasureSpec(defaultSize2, 1073741824));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!f()) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent.getPointerCount() == 1 && (motionEvent.getAction() & 255) == 1) {
            if (this.f14621j) {
                return true;
            }
            this.f14621j = true;
            com.jingdong.moutaibuy.lib.i.c.d("\u624b\u6307\u89e6\u6478\u89e6\u53d1\u5bf9\u7126\u6d4b\u5149");
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (com.jingdong.moutaibuy.lib.i.c.k(getContext())) {
                y = x;
                x = y;
            }
            int f2 = com.jingdong.moutaibuy.lib.i.c.f(getContext(), 120.0f);
            d(x, y, f2, f2);
        }
        if (motionEvent.getPointerCount() == 2) {
            int action = motionEvent.getAction() & 255;
            if (action == 2) {
                float a = com.jingdong.moutaibuy.lib.i.c.a(motionEvent);
                float f3 = this.f14622k;
                if (a > f3) {
                    e(true, this.f14618g);
                } else if (a < f3) {
                    e(false, this.f14618g);
                }
            } else if (action == 5) {
                this.f14622k = com.jingdong.moutaibuy.lib.i.c.a(motionEvent);
            }
        }
        return true;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        if (surfaceHolder.getSurface() == null) {
            return;
        }
        k();
        i();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.f14620i = true;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.f14620i = false;
        k();
    }
}
