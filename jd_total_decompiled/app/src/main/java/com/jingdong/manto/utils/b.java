package com.jingdong.manto.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.LruCache;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IImageLoader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/* loaded from: classes16.dex */
public class b {
    private static b b = new b();
    private LruCache<String, Bitmap> a = new LruCache<>(50);

    /* loaded from: classes16.dex */
    public class a implements Runnable {
        final /* synthetic */ d a;

        a(b bVar, d dVar) {
            this.a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a();
        }
    }

    /* renamed from: com.jingdong.manto.utils.b$b */
    /* loaded from: classes16.dex */
    public class CallableC0689b implements Callable<Bitmap> {
        final /* synthetic */ String a;
        final /* synthetic */ com.jingdong.manto.f b;

        /* renamed from: c */
        final /* synthetic */ d f14300c;

        /* renamed from: com.jingdong.manto.utils.b$b$a */
        /* loaded from: classes16.dex */
        public class a implements Runnable {
            a() {
                CallableC0689b.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                CallableC0689b.this.f14300c.a();
            }
        }

        /* renamed from: com.jingdong.manto.utils.b$b$b */
        /* loaded from: classes16.dex */
        public class RunnableC0690b implements Runnable {
            RunnableC0690b() {
                CallableC0689b.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                CallableC0689b.this.f14300c.a();
            }
        }

        CallableC0689b(String str, com.jingdong.manto.f fVar, d dVar) {
            b.this = r1;
            this.a = str;
            this.b = fVar;
            this.f14300c = dVar;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Bitmap call() {
            if (!this.a.startsWith("jdfile://")) {
                Bitmap a2 = com.jingdong.manto.sdk.b.a(com.jingdong.manto.pkg.b.g.d(this.b, this.a));
                if (this.f14300c != null && a2 != null) {
                    b.this.a.put(this.a, a2);
                    MantoThreadUtils.runOnUIThread(new RunnableC0690b());
                }
                return a2;
            }
            com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(this.b.f13019k, this.a);
            if (g2 != null) {
                try {
                    Bitmap a3 = com.jingdong.manto.sdk.b.a(new FileInputStream(g2.b));
                    if (this.f14300c != null && a3 != null) {
                        b.this.a.put(this.a, a3);
                        MantoThreadUtils.runOnUIThread(new a());
                    }
                    return a3;
                } catch (Throwable unused) {
                    return null;
                }
            }
            return null;
        }
    }

    /* loaded from: classes16.dex */
    public class c implements Callable<Bitmap> {
        final /* synthetic */ String a;
        final /* synthetic */ long b;

        /* renamed from: c */
        final /* synthetic */ com.jingdong.manto.f f14301c;

        /* loaded from: classes16.dex */
        public class a implements IImageLoader.ImageLoaderCallback {
            final /* synthetic */ List a;
            final /* synthetic */ CountDownLatch b;

            a(List list, CountDownLatch countDownLatch) {
                c.this = r1;
                this.a = list;
                this.b = countDownLatch;
            }

            @Override // com.jingdong.manto.sdk.api.IImageLoader.ImageLoaderCallback
            public void onFail() {
                this.b.countDown();
            }

            @Override // com.jingdong.manto.sdk.api.IImageLoader.ImageLoaderCallback
            public void onSuccess(Bitmap bitmap) {
                if (bitmap != null) {
                    b.this.a.put(c.this.a, bitmap);
                }
                this.a.add(bitmap);
                this.b.countDown();
            }
        }

        c(String str, long j2, com.jingdong.manto.f fVar) {
            b.this = r1;
            this.a = str;
            this.b = j2;
            this.f14301c = fVar;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Bitmap call() {
            com.jingdong.manto.f fVar;
            if (this.a.startsWith("data:image")) {
                b bVar = b.this;
                String str = this.a;
                Bitmap a2 = bVar.a(str.substring(str.indexOf("base64,") + 7));
                if (a2 != null) {
                    b.this.a.put(this.a, a2);
                }
                return a2;
            }
            if (this.a.startsWith("http")) {
                IImageLoader iImageLoader = (IImageLoader) MantoSdkManager.instanceOf(IImageLoader.class);
                if (iImageLoader == null) {
                    return null;
                }
                CountDownLatch countDownLatch = new CountDownLatch(1);
                ArrayList arrayList = new ArrayList(1);
                iImageLoader.loadImage(com.jingdong.manto.c.a(), this.a, new a(arrayList, countDownLatch));
                try {
                    countDownLatch.await(this.b, TimeUnit.SECONDS);
                } catch (Throwable unused) {
                }
                if (arrayList.size() > 0) {
                    return (Bitmap) arrayList.get(0);
                }
            } else if (!this.a.startsWith("jdfile://") || (fVar = this.f14301c) == null) {
                Bitmap a3 = com.jingdong.manto.sdk.b.a(com.jingdong.manto.pkg.b.g.d(this.f14301c, this.a));
                if (a3 != null) {
                    b.this.a.put(this.a, a3);
                }
                return a3;
            } else {
                com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(fVar.f13019k, this.a);
                if (g2 != null) {
                    try {
                        Bitmap a4 = com.jingdong.manto.sdk.b.a(new FileInputStream(g2.b));
                        if (a4 != null) {
                            b.this.a.put(this.a, a4);
                        }
                        return a4;
                    } catch (Throwable unused2) {
                    }
                }
            }
            return null;
        }
    }

    /* loaded from: classes16.dex */
    public interface d {
        void a();
    }

    private b() {
    }

    public static b a() {
        return b;
    }

    public static byte[] a(byte[] bArr) {
        return a(bArr, bArr.length);
    }

    public static byte[] a(byte[] bArr, int i2) {
        int i3;
        int i4 = (i2 / 4) * 3;
        if (i4 == 0) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[i4];
        int i5 = 0;
        while (true) {
            byte b2 = bArr[i2 - 1];
            if (b2 != 10 && b2 != 13 && b2 != 32 && b2 != 9) {
                if (b2 != 61) {
                    break;
                }
                i5++;
            }
            i2--;
        }
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < i2; i9++) {
            byte b3 = bArr[i9];
            if (b3 != 10 && b3 != 13 && b3 != 32 && b3 != 9) {
                if (b3 >= 65 && b3 <= 90) {
                    i3 = b3 - 65;
                } else if (b3 >= 97 && b3 <= 122) {
                    i3 = b3 - 71;
                } else if (b3 >= 48 && b3 <= 57) {
                    i3 = b3 + 4;
                } else if (b3 == 43) {
                    i3 = 62;
                } else if (b3 != 47) {
                    return null;
                } else {
                    i3 = 63;
                }
                i7 = (i7 << 6) | ((byte) i3);
                if (i8 % 4 == 3) {
                    int i10 = i6 + 1;
                    bArr2[i6] = (byte) (i7 >> 16);
                    int i11 = i10 + 1;
                    bArr2[i10] = (byte) (i7 >> 8);
                    bArr2[i11] = (byte) i7;
                    i6 = i11 + 1;
                }
                i8++;
            }
        }
        if (i5 > 0) {
            int i12 = i7 << (i5 * 6);
            int i13 = i6 + 1;
            bArr2[i6] = (byte) (i12 >> 16);
            if (i5 == 1) {
                i6 = i13 + 1;
                bArr2[i13] = (byte) (i12 >> 8);
            } else {
                i6 = i13;
            }
        }
        byte[] bArr3 = new byte[i6];
        System.arraycopy(bArr2, 0, bArr3, 0, i6);
        return bArr3;
    }

    public Bitmap a(com.jingdong.manto.f fVar, String str) {
        return a(fVar, str, 5L);
    }

    public Bitmap a(com.jingdong.manto.f fVar, String str, long j2) {
        Bitmap bitmap = this.a.get(str);
        if (bitmap == null || bitmap.isRecycled()) {
            FutureTask futureTask = new FutureTask(new c(str, j2, fVar));
            com.jingdong.manto.b.d().networkIO().execute(futureTask);
            try {
                return (Bitmap) futureTask.get();
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
        return bitmap;
    }

    public Bitmap a(com.jingdong.manto.f fVar, String str, Rect rect, d dVar) {
        Bitmap bitmap = this.a.get(str);
        if (bitmap == null || bitmap.isRecycled()) {
            Bitmap a2 = a(fVar, str, dVar);
            if (a2 != null) {
                try {
                    Bitmap createBitmap = Bitmap.createBitmap(a2, rect.left, rect.top, rect.right, rect.bottom);
                    if (createBitmap != null) {
                        this.a.put(str, createBitmap);
                    }
                    return createBitmap;
                } catch (OutOfMemoryError unused) {
                    return a2;
                }
            }
            return null;
        }
        return bitmap;
    }

    public Bitmap a(com.jingdong.manto.f fVar, String str, d dVar) {
        Bitmap bitmap = this.a.get(str);
        if (bitmap != null && !bitmap.isRecycled()) {
            if (dVar != null) {
                MantoThreadUtils.runOnUIThread(new a(this, dVar));
            }
            return bitmap;
        }
        FutureTask futureTask = new FutureTask(new CallableC0689b(str, fVar, dVar));
        com.jingdong.manto.b.d().networkIO().execute(futureTask);
        try {
            return (Bitmap) futureTask.get();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public Bitmap a(String str) {
        Bitmap bitmap = this.a.get(str);
        if (bitmap == null || bitmap.isRecycled()) {
            try {
                byte[] a2 = a(str.getBytes());
                return BitmapFactory.decodeByteArray(a2, 0, a2.length);
            } catch (Throwable th) {
                String str2 = "error in processing base64 image, error=" + th;
                return null;
            }
        }
        return bitmap;
    }
}
