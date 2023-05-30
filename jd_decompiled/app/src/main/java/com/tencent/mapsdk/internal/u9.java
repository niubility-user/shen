package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import com.tencent.mapsdk.internal.ya;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes9.dex */
public class u9 extends m9 {

    /* renamed from: f  reason: collision with root package name */
    public static final int f17313f = 10;

    /* renamed from: g  reason: collision with root package name */
    private static final ya.e f17314g;

    /* renamed from: h  reason: collision with root package name */
    private static final ya.k<ya.m<Bitmap>> f17315h;
    public final AtomicInteger a = new AtomicInteger();
    public Bitmap b;

    /* renamed from: c  reason: collision with root package name */
    public byte[] f17316c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private String f17317e;

    static {
        ya.e eVar = new ya.e(256, 256, Bitmap.Config.ARGB_8888);
        f17314g = eVar;
        f17315h = ya.a(10, eVar);
    }

    public u9() {
    }

    public u9(Bitmap bitmap) {
        this.b = bitmap;
        j();
        i();
    }

    public u9(byte[] bArr) {
        this.f17316c = bArr;
        if (bArr != null) {
            a(bArr);
        }
    }

    private void i() {
        byte[] bArr;
        Bitmap bitmap = this.b;
        if (bitmap == null && (bArr = this.f17316c) != null) {
            this.d = bArr.length;
        }
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.d = Build.VERSION.SDK_INT >= 19 ? this.b.getAllocationByteCount() : this.b.getByteCount();
    }

    private void j() {
        Bitmap bitmap = this.b;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.f17317e = a7.e(this.b);
    }

    @Override // com.tencent.mapsdk.internal.m9
    public int a() {
        return this.d;
    }

    public void a(ya.e eVar) {
        f17314g.a(eVar);
    }

    @Override // com.tencent.mapsdk.internal.m9
    public void a(byte[] bArr) {
        int i2;
        h();
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        this.f17316c = bArr;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } catch (Exception e2) {
            ma.a(la.r, "BitmapData testOpts decodingByteArray exception: ", e2.fillInStackTrace());
        }
        int i3 = options.outWidth;
        if (i3 <= 0 || (i2 = options.outHeight) <= 0) {
            return;
        }
        ya.e eVar = f17314g;
        eVar.a = i3;
        eVar.b = i2;
        Bitmap bitmap = null;
        boolean z = false;
        for (int i4 = 0; !z && i4 < 20; i4++) {
            ya.m<Bitmap> a = f17315h.a();
            if (a != null) {
                bitmap = a.b();
                if (!bitmap.isRecycled() && bitmap.getWidth() == options.outWidth && bitmap.getHeight() == options.outHeight) {
                    z = true;
                }
            }
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        if (z) {
            options2.inBitmap = bitmap;
        }
        options2.inSampleSize = 1;
        options2.inMutable = true;
        try {
            this.b = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options2);
        } catch (Exception e3) {
            ma.a(la.r, "BitmapData opts decodingByteArray exception: ", e3.fillInStackTrace());
        }
        this.f17316c = null;
        j();
        i();
    }

    @Override // com.tencent.mapsdk.internal.m9
    public byte[] b() {
        byte[] bArr = this.f17316c;
        if (bArr != null) {
            return bArr;
        }
        Bitmap bitmap = this.b;
        if (bitmap != null && !bitmap.isRecycled()) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    if (this.b.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2)) {
                        byte[] byteArray = byteArrayOutputStream2.toByteArray();
                        ga.a(byteArrayOutputStream2);
                        return byteArray;
                    }
                    ga.a(byteArrayOutputStream2);
                } catch (Throwable th) {
                    th = th;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    ga.a(byteArrayOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return new byte[0];
    }

    public void c() {
        Bitmap bitmap = this.b;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        int decrementAndGet = this.a.decrementAndGet();
        qa.g(la.r).a("decrement refCount:" + decrementAndGet + " id = " + e());
    }

    public synchronized Bitmap d() {
        if (this.b == null) {
            a(this.f17316c);
        }
        Bitmap bitmap = this.b;
        if (bitmap == null || !bitmap.isRecycled()) {
            return this.b;
        }
        return null;
    }

    public String e() {
        return this.f17317e;
    }

    public void f() {
        Bitmap bitmap = this.b;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        int incrementAndGet = this.a.incrementAndGet();
        qa.g(la.r).a("increment refCount:" + incrementAndGet + " id = " + e());
    }

    public boolean g() {
        Bitmap bitmap = this.b;
        if (bitmap != null) {
            return bitmap.isRecycled();
        }
        byte[] bArr = this.f17316c;
        return bArr == null || bArr.length == 0;
    }

    public boolean h() {
        Bitmap bitmap = this.b;
        if (bitmap != null && !bitmap.isRecycled() && this.a.decrementAndGet() <= 0) {
            this.b.recycle();
            qa.g(la.r).a("recycle out");
        }
        this.f17316c = null;
        Bitmap bitmap2 = this.b;
        return bitmap2 == null || bitmap2.isRecycled();
    }

    public String toString() {
        return "BitmapData{id='" + this.f17317e + "'}";
    }
}
