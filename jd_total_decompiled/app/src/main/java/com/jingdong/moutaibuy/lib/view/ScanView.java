package com.jingdong.moutaibuy.lib.view;

import android.content.Context;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.google.zxing.MultiFormatReader;
import com.jingdong.moutaibuy.lib.R;
import com.jingdong.moutaibuy.lib.i.d;
import com.jingdong.moutaibuy.lib.view.a;

/* loaded from: classes16.dex */
public abstract class ScanView extends RelativeLayout implements Camera.PreviewCallback {

    /* renamed from: g */
    protected Camera f14610g;

    /* renamed from: h */
    protected com.jingdong.moutaibuy.lib.view.a f14611h;

    /* renamed from: i */
    protected com.jingdong.moutaibuy.lib.e.b f14612i;

    /* renamed from: j */
    protected boolean f14613j;

    /* renamed from: k */
    protected boolean f14614k;

    /* renamed from: l */
    protected boolean f14615l;

    /* renamed from: m */
    protected c f14616m;

    /* renamed from: n */
    protected int f14617n;
    private long o;
    protected d p;
    protected MultiFormatReader q;
    private long r;
    private int s;

    /* loaded from: classes16.dex */
    public class a implements a.b {
        a() {
            ScanView.this = r1;
        }

        @Override // com.jingdong.moutaibuy.lib.view.a.b
        public void a() {
            ScanView.this.h();
        }
    }

    public ScanView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private int b(int i2) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i3 = 0; i3 < Camera.getNumberOfCameras(); i3++) {
            try {
                Camera.getCameraInfo(i3, cameraInfo);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (cameraInfo.facing == i2) {
                return i3;
            }
        }
        return -1;
    }

    private void c(Context context, AttributeSet attributeSet) {
        com.jingdong.moutaibuy.lib.view.a aVar = new com.jingdong.moutaibuy.lib.view.a(context);
        this.f14611h = aVar;
        aVar.h(new a());
        this.f14611h.setId(R.id.qrcode_scanner_camera_preview);
        addView(this.f14611h);
    }

    public void h() {
        if (this.f14611h.f()) {
            try {
                this.f14610g.setOneShotPreviewCallback(this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void m(int i2) {
        try {
            this.f14617n = i2;
            Camera open = Camera.open(i2);
            this.f14610g = open;
            this.f14611h.g(open);
        } catch (Exception e2) {
            e2.printStackTrace();
            com.jingdong.moutaibuy.lib.e.b bVar = this.f14612i;
            if (bVar != null) {
                bVar.h();
            }
        }
    }

    public void d() {
        q();
        this.f14612i = null;
    }

    public void e(com.jingdong.moutaibuy.lib.h.a aVar) {
        com.jingdong.moutaibuy.lib.e.b bVar = this.f14612i;
        if (bVar != null) {
            bVar.a(aVar == null ? null : aVar.a, null);
        }
    }

    public void f(com.jingdong.moutaibuy.lib.h.a aVar) {
        try {
            if (this.f14615l) {
                if (this.f14613j) {
                    if (aVar != null && !TextUtils.isEmpty(aVar.a) && !TextUtils.isEmpty(aVar.b)) {
                        com.jingdong.moutaibuy.lib.e.b bVar = this.f14612i;
                        if (bVar != null) {
                            bVar.a(aVar.a, aVar.b);
                        }
                    }
                    h();
                } else {
                    if (aVar != null && !TextUtils.isEmpty(aVar.b)) {
                        com.jingdong.moutaibuy.lib.e.b bVar2 = this.f14612i;
                        if (bVar2 != null) {
                            bVar2.a(aVar.a, aVar.b);
                        }
                    }
                    h();
                }
                if (!this.f14614k || this.f14610g == null) {
                    return;
                }
                h();
                if (System.currentTimeMillis() - this.r < this.s || this.f14612i == null || aVar == null || TextUtils.isEmpty(aVar.b)) {
                    return;
                }
                this.f14612i.c(aVar.b);
                this.r = System.currentTimeMillis();
            }
        } catch (Exception unused) {
            h();
        }
    }

    public void g() {
        h();
    }

    public void i(com.jingdong.moutaibuy.lib.e.b bVar) {
        this.f14612i = bVar;
    }

    protected abstract void j();

    public void k() {
        l(this.f14617n);
    }

    public void l(int i2) {
        if (this.f14610g != null || Camera.getNumberOfCameras() == 0) {
            return;
        }
        int b = b(i2);
        if (b != -1) {
            m(b);
            return;
        }
        if (i2 == 0) {
            b = b(1);
        } else if (i2 == 1) {
            b = b(0);
        }
        if (b != -1) {
            m(b);
        }
    }

    public void n(int i2) {
        this.s = i2;
        this.f14613j = false;
        this.f14614k = true;
        this.f14615l = true;
        h();
    }

    public void o() {
        this.f14613j = false;
        this.f14614k = false;
        this.f14615l = true;
        h();
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (com.jingdong.moutaibuy.lib.i.c.j()) {
            com.jingdong.moutaibuy.lib.i.c.d("\u4e24\u6b21 onPreviewFrame \u65f6\u95f4\u95f4\u9694\uff1a" + (System.currentTimeMillis() - this.o));
            this.o = System.currentTimeMillis();
        }
        c cVar = this.f14616m;
        if (cVar == null || !(cVar.getStatus() == AsyncTask.Status.PENDING || this.f14616m.getStatus() == AsyncTask.Status.RUNNING)) {
            Camera.Size c2 = this.f14611h.c();
            c cVar2 = new c(new com.jingdong.moutaibuy.lib.c.b(bArr, c2.width, c2.height, this.f14611h.b()), this, this.f14613j, com.jingdong.moutaibuy.lib.i.c.k(getContext()));
            cVar2.d();
            this.f14616m = cVar2;
        }
    }

    public void p() {
        this.f14613j = true;
        this.f14614k = false;
        this.f14615l = true;
        h();
    }

    public void q() {
        try {
            if (this.f14610g != null) {
                this.f14611h.k();
                this.f14611h.g(null);
                this.f14610g.release();
                this.f14610g = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void r() {
        this.f14613j = false;
        this.f14614k = false;
        this.s = 0;
        this.f14615l = false;
        c cVar = this.f14616m;
        if (cVar != null) {
            cVar.a();
            this.f14616m = null;
        }
        Camera camera = this.f14610g;
        if (camera != null) {
            try {
                camera.setOneShotPreviewCallback(null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public ScanView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f14613j = false;
        this.f14614k = false;
        this.f14615l = false;
        this.f14617n = 0;
        this.o = 0L;
        this.p = d.HIGH_FREQUENCY;
        this.r = 0L;
        this.s = 0;
        c(context, attributeSet);
        j();
    }
}
