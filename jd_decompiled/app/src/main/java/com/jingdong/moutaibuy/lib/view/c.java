package com.jingdong.moutaibuy.lib.view;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import java.lang.ref.WeakReference;

/* loaded from: classes16.dex */
public class c extends AsyncTask<Void, Void, com.jingdong.moutaibuy.lib.h.a> {

    /* renamed from: g  reason: collision with root package name */
    private static long f14632g;
    private com.jingdong.moutaibuy.lib.c.b a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private String f14633c;
    private Bitmap d;

    /* renamed from: e  reason: collision with root package name */
    private WeakReference<ScanView> f14634e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f14635f;

    public c(com.jingdong.moutaibuy.lib.c.b bVar, ScanView scanView, boolean z, boolean z2) {
        this.a = bVar;
        this.f14634e = new WeakReference<>(scanView);
        this.f14635f = z;
        this.b = z2;
    }

    private com.jingdong.moutaibuy.lib.h.a f(MultiFormatReader multiFormatReader) {
        com.jingdong.moutaibuy.lib.c.b bVar = this.a;
        if (bVar == null) {
            return null;
        }
        byte[] bArr = bVar.a;
        int i2 = bVar.b;
        int i3 = bVar.f14585c;
        try {
            if (this.b) {
                bArr = new byte[bArr.length];
                for (int i4 = 0; i4 < i3; i4++) {
                    for (int i5 = 0; i5 < i2; i5++) {
                        bArr[(((i5 * i3) + i3) - i4) - 1] = this.a.a[(i4 * i2) + i5];
                    }
                }
                i2 = i3;
                i3 = i2;
            }
            return g(multiFormatReader, this.a, bArr, i2, i3, false);
        } catch (Exception e2) {
            int i6 = i3;
            byte[] bArr2 = bArr;
            int i7 = i2;
            e2.printStackTrace();
            if (i7 != 0 && i6 != 0) {
                try {
                    com.jingdong.moutaibuy.lib.i.c.d("\u8bc6\u522b\u5931\u8d25\u91cd\u8bd5");
                    return g(multiFormatReader, this.a, bArr2, i7, i6, true);
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
            return null;
        }
    }

    public void a() {
        if (getStatus() != AsyncTask.Status.FINISHED) {
            cancel(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    @SuppressLint({"WrongThread"})
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public com.jingdong.moutaibuy.lib.h.a doInBackground(Void... voidArr) {
        ScanView scanView = this.f14634e.get();
        if (scanView == null) {
            return null;
        }
        String str = this.f14633c;
        if (str != null) {
            return e(com.jingdong.moutaibuy.lib.i.c.h(str));
        }
        Bitmap bitmap = this.d;
        if (bitmap != null) {
            com.jingdong.moutaibuy.lib.h.a e2 = e(bitmap);
            this.d = null;
            return e2;
        }
        if (com.jingdong.moutaibuy.lib.i.c.j()) {
            com.jingdong.moutaibuy.lib.i.c.d("\u4e24\u6b21\u4efb\u52a1\u6267\u884c\u7684\u65f6\u95f4\u95f4\u9694\uff1a" + (System.currentTimeMillis() - f14632g));
            f14632g = System.currentTimeMillis();
        }
        long currentTimeMillis = System.currentTimeMillis();
        com.jingdong.moutaibuy.lib.h.a f2 = f(scanView.q);
        if (com.jingdong.moutaibuy.lib.i.c.j()) {
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (f2 != null && !TextUtils.isEmpty(f2.a)) {
                com.jingdong.moutaibuy.lib.i.c.d("\u8bc6\u522b\u6210\u529f\u65f6\u95f4\u4e3a\uff1a" + currentTimeMillis2);
            } else {
                com.jingdong.moutaibuy.lib.i.c.g("\u8bc6\u522b\u5931\u8d25\u65f6\u95f4\u4e3a\uff1a" + currentTimeMillis2);
            }
        }
        return f2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void onPostExecute(com.jingdong.moutaibuy.lib.h.a aVar) {
        ScanView scanView = this.f14634e.get();
        if (scanView == null) {
            return;
        }
        if (this.f14633c == null && this.d == null) {
            scanView.f(aVar);
            return;
        }
        this.d = null;
        scanView.e(aVar);
    }

    public c d() {
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        return this;
    }

    public com.jingdong.moutaibuy.lib.h.a e(Bitmap bitmap) {
        return new com.jingdong.moutaibuy.lib.h.a(com.jingdong.moutaibuy.lib.d.a.a(bitmap), null);
    }

    public com.jingdong.moutaibuy.lib.h.a g(MultiFormatReader multiFormatReader, com.jingdong.moutaibuy.lib.c.b bVar, byte[] bArr, int i2, int i3, boolean z) {
        Result result = null;
        try {
            if (this.f14635f) {
                try {
                    PlanarYUVLuminanceSource planarYUVLuminanceSource = new PlanarYUVLuminanceSource(bArr, i2, i3, 0, 0, i2, i3, false);
                    result = multiFormatReader.decodeWithState(new BinaryBitmap(new GlobalHistogramBinarizer(planarYUVLuminanceSource)));
                    if (result == null && (result = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(planarYUVLuminanceSource)))) != null) {
                        com.jingdong.moutaibuy.lib.i.c.d("GlobalHistogramBinarizer \u6ca1\u8bc6\u522b\u5230\uff0cHybridBinarizer \u80fd\u8bc6\u522b\u5230");
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return new com.jingdong.moutaibuy.lib.h.a((result == null || TextUtils.isEmpty(result.getText())) ? "" : result.getText(), com.jingdong.moutaibuy.lib.i.a.c(bVar));
        } finally {
            multiFormatReader.reset();
        }
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        super.onCancelled();
        this.f14634e.clear();
        this.d = null;
        this.a = null;
    }
}
