package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.mapsdk.engine.jni.models.IconImageInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes9.dex */
public class w {

    /* renamed from: i  reason: collision with root package name */
    private static final int f17408i = -16711681;

    /* renamed from: j  reason: collision with root package name */
    private static final int f17409j = 16711680;

    /* renamed from: k  reason: collision with root package name */
    private static final int f17410k = -256;

    /* renamed from: l  reason: collision with root package name */
    private static final int f17411l = 255;
    private WeakReference<Context> a;
    private e1 b;

    /* renamed from: c  reason: collision with root package name */
    private ce f17412c;
    private float d;

    /* renamed from: e  reason: collision with root package name */
    private yd f17413e;

    /* renamed from: f  reason: collision with root package name */
    private v6 f17414f;

    /* renamed from: g  reason: collision with root package name */
    private Lock f17415g = new ReentrantLock();

    /* renamed from: h  reason: collision with root package name */
    private Lock f17416h = new ReentrantLock();

    public w(Context context, e1 e1Var, ce ceVar, yd ydVar) {
        this.d = 1.0f;
        this.a = new WeakReference<>(context);
        this.b = e1Var;
        this.f17412c = ceVar;
        this.f17413e = ydVar;
        this.d = f7.d(context);
        if (e1Var == null || e1Var.j() == null) {
            return;
        }
        this.f17414f = ((xi) e1Var.j()).A().w();
    }

    private int a(int i2) {
        return (i2 & f17408i & (-256)) | ((i2 & 255) << 16) | ((f17409j & i2) >> 16);
    }

    private Bitmap a(String str) {
        String[] split;
        String substring = str.substring(14);
        if (!e7.b(substring) && (split = substring.split(", ")) != null && split.length >= 5) {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            long parseLong = Long.parseLong(split[2]);
            long parseLong2 = Long.parseLong(split[3]);
            float parseFloat = Float.parseFloat(split[4]);
            if (parseInt >= 0 && parseInt2 >= 0) {
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setStrokeWidth(parseFloat);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(a((int) parseLong2));
                Bitmap createBitmap = Bitmap.createBitmap(parseInt, parseInt2, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(a((int) parseLong));
                RectF rectF = new RectF();
                rectF.left = 0.0f;
                rectF.top = 0.0f;
                rectF.right = parseInt;
                rectF.bottom = parseInt2;
                canvas.drawRoundRect(rectF, parseInt >> 3, parseInt2 >> 3, paint);
                return createBitmap;
            }
        }
        return null;
    }

    private Bitmap a(String str, Bitmap.Config config, boolean z) {
        WeakReference<Context> weakReference;
        if (new File(str).exists() && (weakReference = this.a) != null && weakReference.get() != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = config;
            if (z) {
                options.inDensity = 320;
                options.inTargetDensity = this.a.get().getResources().getDisplayMetrics().densityDpi;
            }
            try {
                return BitmapFactory.decodeFile(str, options);
            } catch (OutOfMemoryError unused) {
            }
        }
        return null;
    }

    private boolean a(File file, String str, byte[] bArr, Lock lock) {
        if (file != null && !e7.b(str) && bArr != null && bArr.length != 0) {
            String str2 = str + CartConstant.KEY_YB_INFO_LINK + Arrays.hashCode(bArr);
            try {
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, str2);
                if (file2.exists()) {
                    file2.delete();
                }
                file2.createNewFile();
                fa.b(file2, bArr);
                try {
                    if (!va.a(bArr).equals(va.a(file2))) {
                        return false;
                    }
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
                File file3 = new File(file, str);
                File file4 = new File(file, str + ".bak");
                if (file4.exists() && !file4.delete()) {
                    file4.deleteOnExit();
                    return false;
                }
                lock.lock();
                if (file3.exists() && !file3.renameTo(file4)) {
                    if (!file2.delete()) {
                        file2.deleteOnExit();
                    }
                    lock.unlock();
                    return false;
                } else if (file2.renameTo(file3)) {
                    if (!file4.delete()) {
                        file4.deleteOnExit();
                    }
                    lock.unlock();
                    return true;
                } else {
                    file4.renameTo(file3);
                    if (!file2.delete()) {
                        file2.deleteOnExit();
                    }
                    lock.unlock();
                    return false;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public e1 a() {
        return this.b;
    }

    public boolean a(String str, byte[] bArr) {
        ce ceVar = this.f17412c;
        if (ceVar == null) {
            return false;
        }
        return a(new File(ceVar.j()), str, bArr, this.f17415g);
    }

    public IconImageInfo b(String str) {
        v6 v6Var;
        v6 v6Var2;
        v6 v6Var3;
        if (this.f17413e == null) {
            return null;
        }
        IconImageInfo iconImageInfo = new IconImageInfo();
        iconImageInfo.anchorPointX1 = 0.5f;
        iconImageInfo.anchorPointY1 = 0.5f;
        if (str != null && str.startsWith("drawRoundRect")) {
            iconImageInfo.scale = 1.0f;
            Bitmap a = a(str);
            iconImageInfo.bitmap = a;
            if (a == null && (v6Var3 = this.f17414f) != null) {
                v6Var3.l().a(System.currentTimeMillis(), str);
            }
            return iconImageInfo;
        }
        try {
            this.f17416h.lock();
            this.f17413e.a(str, iconImageInfo);
            this.f17416h.unlock();
            if (iconImageInfo.bitmap == null && str != null) {
                str.equals(yd.a);
            }
            if (iconImageInfo.bitmap == null && (v6Var2 = this.f17414f) != null) {
                v6Var2.l().a(System.currentTimeMillis(), str);
            }
            return iconImageInfo;
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iconImageInfo.bitmap == null && (v6Var = this.f17414f) != null) {
                v6Var.l().a(System.currentTimeMillis(), str);
            }
            return null;
        }
    }

    public void b() {
        this.f17415g.lock();
    }

    public boolean b(String str, byte[] bArr) {
        ce ceVar = this.f17412c;
        if (ceVar == null) {
            return false;
        }
        return a(new File(ceVar.f()), str, bArr, this.f17416h);
    }

    public IconImageInfo c(String str) {
        IconImageInfo iconImageInfo = new IconImageInfo();
        iconImageInfo.scale = this.d;
        iconImageInfo.anchorPointX1 = 0.5f;
        iconImageInfo.anchorPointY1 = 0.5f;
        iconImageInfo.bitmap = a(str, Bitmap.Config.RGB_565, false);
        return iconImageInfo;
    }

    public void c() {
        this.b.f().g("");
    }

    public void d() {
        this.f17415g.unlock();
    }
}
