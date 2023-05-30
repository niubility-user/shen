package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.opengl.GLUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class ac {
    public static final long a = 20;
    private static final int b = 0;

    /* renamed from: c  reason: collision with root package name */
    private static final int f16247c = 1;
    private static final int d = 2;

    /* renamed from: e  reason: collision with root package name */
    public static int f16248e;

    static {
        long maxMemory = Runtime.getRuntime().maxMemory();
        f16248e = maxMemory <= 16777216 ? 1 : maxMemory >= 67108864 ? 2 : 0;
    }

    public static double a(double d2, double d3, long j2, long j3) {
        double d4 = j3;
        double d5 = j2;
        Double.isNaN(d4);
        Double.isNaN(d5);
        return ((d3 - d2) * (d4 / d5)) + d2;
    }

    public static int a(float f2) {
        int i2 = 1;
        while (true) {
            int i3 = i2 + 1;
            int i4 = 2 << i2;
            if (i4 >= ((int) Math.ceil(f2))) {
                return i4;
            }
            i2 = i3;
        }
    }

    public static int a(GL10 gl10, Bitmap bitmap) {
        int[] iArr = new int[1];
        gl10.glGenTextures(1, iArr, 0);
        int i2 = iArr[0];
        gl10.glBindTexture(R2.color.c_FF0017, i2);
        gl10.glTexParameterf(R2.color.c_FF0017, R2.drawable.main_bottom_tab_personal_normal, 9729.0f);
        gl10.glTexParameterf(R2.color.c_FF0017, 10240, 9729.0f);
        GLUtils.texImage2D(R2.color.c_FF0017, 0, bitmap, 0);
        return i2;
    }

    public static Bitmap a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = 2;
        int i3 = 2;
        while (i3 < width) {
            i3 <<= 1;
        }
        while (i2 < height) {
            i2 <<= 1;
        }
        Paint paint = new Paint();
        paint.setAntiAlias(false);
        Bitmap createBitmap = Bitmap.createBitmap(i3, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        createBitmap.eraseColor(0);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    public static FloatBuffer a(int i2) {
        ByteBuffer allocateDirect;
        ByteBuffer.allocate(0);
        int i3 = i2 * 4;
        try {
            allocateDirect = ByteBuffer.allocateDirect(i3);
        } catch (OutOfMemoryError e2) {
            allocateDirect = ByteBuffer.allocateDirect(i3);
            e2.printStackTrace();
        }
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.rewind();
        return asFloatBuffer;
    }

    public static FloatBuffer a(float[] fArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.rewind();
        return asFloatBuffer;
    }

    public static FloatBuffer a(float[] fArr, FloatBuffer floatBuffer) {
        int length = fArr.length;
        if (floatBuffer == null || floatBuffer.capacity() != length) {
            floatBuffer = a(length);
        }
        floatBuffer.put(fArr);
        floatBuffer.rewind();
        return floatBuffer;
    }

    public static ShortBuffer a(short[] sArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(sArr.length * 2);
        allocateDirect.order(ByteOrder.nativeOrder());
        ShortBuffer asShortBuffer = allocateDirect.asShortBuffer();
        asShortBuffer.put(sArr);
        asShortBuffer.rewind();
        return asShortBuffer;
    }

    public static ShortBuffer a(short[] sArr, ShortBuffer shortBuffer) {
        int length = sArr.length;
        if (shortBuffer == null || shortBuffer.capacity() != length) {
            shortBuffer = b(length);
        }
        shortBuffer.put(sArr);
        shortBuffer.rewind();
        return shortBuffer;
    }

    public static boolean a() {
        return f16248e == 1;
    }

    public static double b(double d2, double d3, long j2, long j3) {
        double d4 = j3;
        double d5 = j2;
        Double.isNaN(d4);
        Double.isNaN(d5);
        double d6 = d4 / d5;
        return ((d3 - d2) * d6 * d6) + d2;
    }

    public static ShortBuffer b(int i2) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i2 * 2);
        allocateDirect.order(ByteOrder.nativeOrder());
        ShortBuffer asShortBuffer = allocateDirect.asShortBuffer();
        asShortBuffer.rewind();
        return asShortBuffer;
    }

    public static double c(double d2, double d3, long j2, long j3) {
        double d4 = j3;
        double d5 = j2;
        Double.isNaN(d4);
        Double.isNaN(d5);
        double d6 = d4 / d5;
        double d7 = d6 * d6;
        double d8 = d7 * d7;
        return ((d3 - d2) * d8 * d8) + d2;
    }

    public static float[] c(int i2) {
        return new float[]{((i2 >> 16) & 255) / 255.0f, ((i2 >> 8) & 255) / 255.0f, (i2 & 255) / 255.0f, ((i2 >> 24) & 255) / 255.0f};
    }

    public static double d(double d2, double d3, long j2, long j3) {
        double d4 = j3;
        double d5 = j2;
        Double.isNaN(d4);
        Double.isNaN(d5);
        double d6 = d4 / d5;
        return ((d3 - d2) * d6 * d6 * d6 * d6) + d2;
    }

    public static double e(double d2, double d3, long j2, long j3) {
        double d4 = j3;
        double d5 = j2;
        Double.isNaN(d4);
        Double.isNaN(d5);
        double d6 = (d4 / d5) - 1.0d;
        return ((d3 - d2) * (1.0d - (d6 * d6))) + d2;
    }

    public static double f(double d2, double d3, long j2, long j3) {
        double d4 = j3;
        double d5 = j2;
        Double.isNaN(d4);
        Double.isNaN(d5);
        double d6 = (d4 / d5) - 1.0d;
        double d7 = d6 * d6;
        double d8 = d7 * d7;
        return ((d3 - d2) * (1.0d - (d8 * d8))) + d2;
    }

    public static double g(double d2, double d3, long j2, long j3) {
        double d4 = j3;
        double d5 = j2;
        Double.isNaN(d4);
        Double.isNaN(d5);
        double d6 = (d4 / d5) - 1.0d;
        return ((d3 - d2) * (1.0d - (((d6 * d6) * d6) * d6))) + d2;
    }

    public static double h(double d2, double d3, long j2, long j3) {
        double d4 = j3;
        double d5 = j2 - 1;
        Double.isNaN(d4);
        Double.isNaN(d5);
        double d6 = d4 / d5;
        double d7 = d6 * d6;
        return ((d2 - d3) * (1.0d - (d7 * d7))) + d3;
    }
}
