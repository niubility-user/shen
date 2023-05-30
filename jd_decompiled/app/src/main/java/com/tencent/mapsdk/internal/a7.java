package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.tencent.mapsdk.core.utils.cache.MemoryCache;
import com.tencent.mapsdk.internal.l9;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes9.dex */
public class a7 {
    private static final int a = 2048;
    private static final String b = "BitmapUtil";

    /* renamed from: c */
    public static Paint f16232c;
    private static byte[] d;

    /* renamed from: e */
    public static a f16233e;

    /* loaded from: classes9.dex */
    public static class a {
        private static final AtomicInteger b = new AtomicInteger();
        private final MemoryCache<u9> a;

        /* renamed from: com.tencent.mapsdk.internal.a7$a$a */
        /* loaded from: classes9.dex */
        public class C0784a implements l9.b<u9> {
            public C0784a() {
                a.this = r1;
            }

            @Override // com.tencent.mapsdk.internal.l9.b
            public boolean a(u9 u9Var) {
                if (u9Var != null) {
                    String e2 = u9Var.e();
                    if (!u9Var.h() || e2 == null) {
                        return true;
                    }
                    a.this.a.b(e2);
                    return true;
                }
                return true;
            }
        }

        public a(Context context, int i2) {
            File b2 = lc.b(context).b();
            if (x9.c("4.5.10", "4.4.6", 3)) {
                fa.b(new File(b2, "BitmapDescriptorCache"), new File(b2, "bitmaps"));
            }
            this.a = (MemoryCache) p9.b(u9.class, new MemoryCache.a().a(i2).a(new C0784a()));
            c();
        }

        public synchronized Bitmap a(String str) {
            u9 b2 = this.a.g().b(str, u9.class);
            if (b2 != null) {
                return b2.d();
            }
            return null;
        }

        public synchronized void a() {
            AtomicInteger atomicInteger = b;
            if (atomicInteger.get() <= 0 || atomicInteger.decrementAndGet() == 0) {
                this.a.g().b();
            }
        }

        public synchronized void a(String str, Bitmap bitmap) {
            a(str, new u9(bitmap));
        }

        public synchronized void a(String str, u9 u9Var) {
            ma.a(la.r, "putCache:" + str + " id:" + u9Var.e() + " bitmapData:" + u9Var);
            u9 a = this.a.a(str, u9.class);
            if (a != null) {
                ma.a(la.r, "getCache:" + str + " id:" + a.e() + " recycle:" + a.g() + " bitmapData:" + a);
            }
            if (a == null || a.g() || TextUtils.isEmpty(a.e()) || !a.e().equals(u9Var.e())) {
                this.a.g().b(str, (String) u9Var);
                return;
            }
            ma.a(la.r, "same bitmap!!" + str);
            a.f();
        }

        public int b() {
            return b.get();
        }

        public synchronized boolean b(String str) {
            boolean z;
            u9 a = this.a.a(str, u9.class);
            if (a == null || !a.h()) {
                z = false;
            } else {
                ma.a(la.r, "remove:" + str + " bitmapData:" + a);
                this.a.b(str);
                z = true;
            }
            return z;
        }

        public void c() {
            AtomicInteger atomicInteger = b;
            if (atomicInteger.get() < 0) {
                atomicInteger.set(0);
            }
            atomicInteger.incrementAndGet();
        }
    }

    static {
        Paint paint = new Paint();
        f16232c = paint;
        paint.setAntiAlias(true);
    }

    private static Bitmap a(int i2, int i3, Bitmap.Config config) {
        try {
            try {
                return Bitmap.createBitmap(i2, i3, config);
            } catch (OutOfMemoryError unused) {
                return null;
            }
        } catch (OutOfMemoryError unused2) {
            return Bitmap.createBitmap(i2, i3, config);
        }
    }

    public static Bitmap a(Context context, int i2) {
        try {
            return BitmapFactory.decodeResource(context.getResources(), i2);
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x005a, code lost:
        if (r4 == null) goto L68;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap a(android.content.Context r3, java.lang.String r4) {
        /*
            r0 = 0
            if (r4 == 0) goto L66
            java.lang.String r1 = r4.trim()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            int r1 = r1.length()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            if (r1 != 0) goto Le
            goto L66
        Le:
            java.lang.String r1 = r4.trim()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r2 = 0
            char r1 = r1.charAt(r2)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r2 = 47
            if (r1 == r2) goto L34
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r1.<init>()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.io.File r3 = r3.getFilesDir()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r1.append(r3)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.lang.String r3 = "/"
            r1.append(r3)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r1.append(r4)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
        L2f:
            java.lang.String r3 = r1.toString()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            goto L44
        L34:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r1.<init>()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.io.File r3 = r3.getFilesDir()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r1.append(r3)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r1.append(r4)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            goto L2f
        L44:
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r4)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L5e
        L4d:
            r4.close()     // Catch: java.lang.Exception -> L5d
            goto L5d
        L51:
            r3 = move-exception
            goto L57
        L53:
            r3 = move-exception
            goto L60
        L55:
            r3 = move-exception
            r4 = r0
        L57:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L5e
            if (r4 == 0) goto L5d
            goto L4d
        L5d:
            return r0
        L5e:
            r3 = move-exception
            r0 = r4
        L60:
            if (r0 == 0) goto L65
            r0.close()     // Catch: java.lang.Exception -> L65
        L65:
            throw r3
        L66:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.a7.a(android.content.Context, java.lang.String):android.graphics.Bitmap");
    }

    public static Bitmap a(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() / b7.x()), (int) (bitmap.getHeight() / b7.x()), true);
            } catch (OutOfMemoryError unused) {
                return null;
            }
        }
        return bitmap;
    }

    public static Bitmap a(Bitmap bitmap, float f2) {
        if (bitmap == null || f2 == 1.0f) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f2, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap a(Bitmap bitmap, int i2) {
        if (bitmap != null && !bitmap.isRecycled()) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            matrix.setRotate(90.0f, width / 2.0f, height / 2.0f);
            try {
                return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            } catch (OutOfMemoryError unused) {
                try {
                    return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                } catch (OutOfMemoryError unused2) {
                }
            }
        }
        return null;
    }

    public static Bitmap a(Bitmap bitmap, int i2, int i3) {
        Bitmap a2;
        if (bitmap == null || bitmap.isRecycled() || i2 < 1 || i3 < 1 || (a2 = a(i2, i3, Bitmap.Config.ARGB_8888)) == null) {
            return null;
        }
        a2.setDensity(bitmap.getDensity());
        Canvas canvas = new Canvas(a2);
        a2.eraseColor(0);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, f16232c);
        return a2;
    }

    public static Bitmap a(Bitmap bitmap, Context context, int i2, int i3) {
        int min;
        int min2;
        Bitmap a2;
        if (bitmap == null || bitmap.isRecycled() || i2 < 1 || i3 < 1 || (a2 = a((min = Math.min(i2, 2048)), (min2 = Math.min(i3, 2048)), Bitmap.Config.ARGB_8888)) == null) {
            return null;
        }
        a2.eraseColor(0);
        Canvas canvas = new Canvas(a2);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        bitmapDrawable.setBounds(0, 0, min, min2);
        bitmapDrawable.draw(canvas);
        return a2;
    }

    public static Bitmap a(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable instanceof NinePatchDrawable) {
            Bitmap a2 = a(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            if (a2 == null) {
                return null;
            }
            Canvas canvas = new Canvas(a2);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return a2;
        }
        return null;
    }

    public static Bitmap a(View view) {
        Bitmap c2;
        if (view != null) {
            try {
                synchronized (view) {
                    view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                    view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                    view.setDrawingCacheEnabled(true);
                    view.buildDrawingCache();
                    c2 = c(view.getDrawingCache());
                    view.destroyDrawingCache();
                }
                return c2;
            } catch (Throwable th) {
                String str = "BitmapUtil.convertToBitmap errorDetail:" + Log.getStackTraceString(th);
                return null;
            }
        }
        return null;
    }

    public static final Bitmap a(String str) {
        try {
            return BitmapFactory.decodeFile(str);
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    public static Bitmap a(String str, int i2) {
        return a(str, i2, Integer.MIN_VALUE, (Typeface) null);
    }

    public static Bitmap a(String str, int i2, int i3, int i4, int i5, Typeface typeface) {
        TextPaint textPaint;
        StaticLayout staticLayout;
        int width;
        int height;
        Rect rect = new Rect();
        TextPaint textPaint2 = new TextPaint(65);
        textPaint2.setStyle(Paint.Style.FILL);
        textPaint2.setColor(i3);
        textPaint2.setTextSize(i2);
        textPaint2.clearShadowLayer();
        if (i4 != 0) {
            TextPaint textPaint3 = new TextPaint(65);
            textPaint3.setStyle(Paint.Style.STROKE);
            textPaint3.setStrokeWidth(i4);
            textPaint3.setColor(i5);
            textPaint3.clearShadowLayer();
            textPaint = textPaint3;
        } else {
            textPaint = null;
        }
        if (typeface != null) {
            textPaint2.setTypeface(typeface);
            if (textPaint != null) {
                textPaint.setTypeface(typeface);
            }
        }
        textPaint2.getTextBounds(str, 0, str.length(), rect);
        if (textPaint != null) {
            textPaint.getTextBounds(str, 0, str.length(), rect);
            staticLayout = new StaticLayout(str, textPaint, Math.round(rect.width()), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        } else {
            staticLayout = null;
        }
        StaticLayout staticLayout2 = new StaticLayout(str, textPaint2, Math.round(rect.width()), Layout.Alignment.ALIGN_CENTER, 0.0f, 0.0f, false);
        if (staticLayout != null) {
            width = staticLayout.getWidth();
            height = staticLayout.getHeight();
        } else {
            width = staticLayout2.getWidth();
            height = staticLayout2.getHeight();
        }
        Bitmap a2 = a(width, height, Bitmap.Config.ARGB_8888);
        if (a2 == null) {
            return null;
        }
        Canvas canvas = new Canvas(a2);
        canvas.save();
        canvas.translate(0.0f, 0.0f);
        if (staticLayout != null) {
            staticLayout.draw(canvas);
        }
        staticLayout2.draw(canvas);
        canvas.restore();
        return a2;
    }

    public static Bitmap a(String str, int i2, int i3, Typeface typeface) {
        return a(str, i2, i3, 0, 0, typeface);
    }

    public static Bitmap a(int[] iArr, int i2, int i3, Bitmap.Config config) {
        return Bitmap.createBitmap(iArr, i2, i3, config);
    }

    public static boolean a(Bitmap bitmap, String str) {
        return a(bitmap, str, Bitmap.CompressFormat.PNG);
    }

    public static boolean a(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat) {
        if (bitmap == null || bitmap.isRecycled() || TextUtils.isEmpty(str)) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                try {
                    bitmap.compress(compressFormat, 100, fileOutputStream2);
                    ga.a(fileOutputStream2);
                    return true;
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    ga.a(fileOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    ga.a(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static byte[] a() {
        Bitmap a2;
        if (d == null && (a2 = a(256, 256, Bitmap.Config.ARGB_8888)) != null) {
            new Canvas(a2).drawARGB(0, 255, 255, 255);
            d = f(a2);
        }
        return d;
    }

    public static byte[] a(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap b(Context context, String str) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        r0 = null;
        Bitmap bitmap = null;
        try {
            inputStream = context.getAssets().open(str);
            try {
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception unused) {
                ga.a((Closeable) inputStream);
                return null;
            } catch (OutOfMemoryError unused2) {
            } catch (Throwable th) {
                th = th;
                inputStream2 = inputStream;
                ga.a((Closeable) inputStream2);
                throw th;
            }
        } catch (Exception unused3) {
            inputStream = null;
        } catch (OutOfMemoryError unused4) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
        ga.a((Closeable) inputStream);
        return bitmap;
    }

    public static Drawable b(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0087: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:60:0x0087 */
    public static Bitmap c(Context context, String str) {
        InputStream inputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        r0 = null;
        r0 = null;
        Bitmap bitmap = null;
        try {
            try {
                inputStream = ga.c(lc.b(context).a() + str);
                if (inputStream == null) {
                    try {
                        if (ic.a() != null) {
                            inputStream = ic.a(context, ic.a() + str);
                        } else if (ic.b() != null) {
                            inputStream = ga.c(ic.b() + str);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        ga.a((Closeable) inputStream);
                        return bitmap;
                    } catch (OutOfMemoryError unused) {
                    }
                }
                if (inputStream == null) {
                    inputStream = ic.a(context, b7.f16301m + str);
                }
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e3) {
                e = e3;
                inputStream = null;
            } catch (OutOfMemoryError unused2) {
                inputStream = null;
            } catch (Throwable th) {
                th = th;
                ga.a(closeable2);
                throw th;
            }
            ga.a((Closeable) inputStream);
            return bitmap;
        } catch (Throwable th2) {
            th = th2;
            closeable2 = closeable;
            ga.a(closeable2);
            throw th;
        }
    }

    public static Bitmap c(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        return a(bitmap, bitmap.getWidth(), bitmap.getHeight());
    }

    public static final Bitmap d(Bitmap bitmap) {
        try {
            return Bitmap.createBitmap(bitmap);
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    public static String e(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        ByteBuffer allocate = ByteBuffer.allocate(Build.VERSION.SDK_INT >= 19 ? bitmap.getAllocationByteCount() : bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(allocate);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        sb.append(DYConstants.DY_REGEX_AT);
        sb.append(width);
        sb.append(JshopConst.JSHOP_PROMOTIO_X);
        sb.append(height);
        sb.append(DYConstants.DY_REGEX_AT);
        sb.append(va.a(allocate.array()));
        allocate.clear();
        return sb.toString();
    }

    public static byte[] f(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2);
                byte[] byteArray = byteArrayOutputStream2.toByteArray();
                ga.a(byteArrayOutputStream2);
                return byteArray;
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
}
