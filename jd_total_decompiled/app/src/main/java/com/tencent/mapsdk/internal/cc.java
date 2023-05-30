package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.opengl.GLUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.tencentmap.mapsdk.maps.TencentMapContext;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class cc {
    public static final int a = 1;
    public static final int b = 2;

    public static float a(float f2, float f3) {
        return (int) ((f2 * f3) + 0.5f);
    }

    public static float a(TencentMapContext tencentMapContext, String str, int i2, float f2) {
        mc mcVar = new mc(tencentMapContext);
        mcVar.setTextSize(a(i2, f2));
        mcVar.setTypeface(Typeface.DEFAULT_BOLD);
        return mcVar.measureText(str);
    }

    private static int a(GL10 gl10) {
        int[] iArr = new int[1];
        gl10.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    public static int a(GL10 gl10, int i2, int i3, int i4, int i5, IntBuffer intBuffer) {
        int b2 = b(gl10);
        gl10.glTexImage2D(R2.color.c_FF0017, 0, R2.dimen.jdreact_base_ui_jd_tip_page_button_paddingLeft, i2, i3, 0, R2.dimen.jdreact_base_ui_jd_tip_page_button_paddingLeft, R2.dimen.HugersTextSize, null);
        gl10.glTexSubImage2D(R2.color.c_FF0017, 0, 0, 0, i4, i5, R2.dimen.jdreact_base_ui_jd_tip_page_button_paddingLeft, R2.dimen.HugersTextSize, intBuffer);
        return b2;
    }

    public static int a(GL10 gl10, Bitmap bitmap) {
        int b2 = b(gl10);
        GLUtils.texImage2D(R2.color.c_FF0017, 0, bitmap, 0);
        return b2;
    }

    private static Bitmap a(Bitmap bitmap) {
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
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i3, i2, false);
        if (createScaledBitmap != bitmap) {
            bitmap.recycle();
        }
        return createScaledBitmap;
    }

    public static Bitmap a(TencentMapContext tencentMapContext, Bitmap bitmap, String str, int i2, int i3) {
        return a(tencentMapContext, bitmap, str, i2, i3, 0, 2.0f);
    }

    public static Bitmap a(TencentMapContext tencentMapContext, Bitmap bitmap, String str, int i2, int i3, int i4, float f2) {
        return a(tencentMapContext, bitmap, str, i2, i3, i4, 2, f2);
    }

    public static Bitmap a(TencentMapContext tencentMapContext, Bitmap bitmap, String str, int i2, int i3, int i4, int i5, float f2) {
        mc mcVar = new mc(tencentMapContext);
        mcVar.setColor(i2);
        mcVar.setTextSize(a(i3, f2));
        mcVar.setTypeface(Typeface.DEFAULT_BOLD);
        float f3 = mcVar.getFontMetrics().bottom - mcVar.getFontMetrics().top;
        float f4 = i4 * 2;
        int ceil = (int) Math.ceil(Math.max(bitmap.getWidth(), mcVar.measureText(str)) + f4);
        int ceil2 = (int) Math.ceil(Math.max(bitmap.getHeight(), f3) + f4);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, ceil, ceil2, false);
        Bitmap createBitmap = Bitmap.createBitmap(ceil, ceil2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.setDensity(0);
        canvas.drawBitmap(createScaledBitmap, 0.0f, 0.0f, new Paint());
        if (i5 == 1) {
            float measureText = (ceil - mcVar.measureText(str.substring(0, 1))) / 2.0f;
            float length = (((ceil2 - (str.length() * f3)) / 2.0f) - mcVar.getFontMetrics().top) + 5.0f;
            for (int i6 = 0; i6 < str.length(); i6++) {
                canvas.drawText(String.valueOf(str.charAt(i6)), measureText, length, mcVar);
                length += f3;
            }
        } else if (i5 == 2) {
            float f5 = ceil2;
            float f6 = (f5 - ((f5 - f3) / 2.0f)) - mcVar.getFontMetrics().bottom;
            mcVar.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(str, (float) (ceil / 2), f6, mcVar);
        }
        return createBitmap;
    }

    public static Bitmap a(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream, null, a());
    }

    public static Bitmap a(String str, float f2, int i2, int i3, int i4, Typeface typeface) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i2);
        paint.setTextSize(f2);
        if (typeface != null) {
            paint.setTypeface(typeface);
        }
        Paint paint2 = new Paint(paint);
        paint2.setColor(i3);
        paint2.setStrokeWidth(paint.getStrokeWidth() + 2.0f);
        Paint.FontMetrics fontMetrics = paint2.getFontMetrics();
        float f3 = fontMetrics.bottom - fontMetrics.top;
        if (i4 == 1) {
            int ceil = (int) Math.ceil(((f3 + 2.0f) * str.length()) + 4.0f);
            float f4 = 0.0f - fontMetrics.top;
            Bitmap createBitmap = Bitmap.createBitmap((int) Math.ceil(paint2.measureText(str.substring(0, 1)) + 4.0f), ceil, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            for (int i5 = 0; i5 < str.length(); i5++) {
                char charAt = str.charAt(i5);
                canvas.drawText(String.valueOf(charAt), 2.0f, f4, paint2);
                canvas.drawText(String.valueOf(charAt), 2.0f, f4, paint);
                f4 += f3;
            }
            return createBitmap;
        } else if (i4 != 2) {
            return null;
        } else {
            int ceil2 = (int) Math.ceil(paint2.measureText(str) + 4.0f);
            int ceil3 = (int) Math.ceil(f3);
            Bitmap createBitmap2 = Bitmap.createBitmap(ceil2, ceil3, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap2);
            float f5 = (float) (ceil2 / 2);
            float f6 = (float) ((ceil3 / 2) + 1);
            paint2.setTextAlign(Paint.Align.CENTER);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas2.drawText(str, f5, f6, paint2);
            canvas2.drawText(str, f5, f6, paint);
            return createBitmap2;
        }
    }

    public static Bitmap a(byte[] bArr) {
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, a());
    }

    private static BitmapFactory.Options a() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return options;
    }

    public static void a(GL10 gl10, int i2) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(4);
        allocateDirect.order(ByteOrder.nativeOrder());
        IntBuffer asIntBuffer = allocateDirect.asIntBuffer();
        asIntBuffer.put(i2);
        asIntBuffer.position(0);
        gl10.glDeleteTextures(1, asIntBuffer);
    }

    private static int b(GL10 gl10) {
        int a2 = a(gl10);
        gl10.glBindTexture(R2.color.c_FF0017, a2);
        gl10.glTexParameterf(R2.color.c_FF0017, R2.drawable.main_bottom_tab_personal_normal, 9729.0f);
        gl10.glTexParameterf(R2.color.c_FF0017, 10240, 9729.0f);
        gl10.glTexParameterf(R2.color.c_FF0017, R2.drawable.main_bottom_tab_personal_normal_dark, 33071.0f);
        gl10.glTexParameterf(R2.color.c_FF0017, R2.drawable.main_bottom_tab_search_focus, 33071.0f);
        return a2;
    }

    public static Bitmap b(Bitmap bitmap) {
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
        try {
            Bitmap createBitmap = Bitmap.createBitmap(i3, i2, Bitmap.Config.ARGB_8888);
            createBitmap.setDensity(0);
            Canvas canvas = new Canvas(createBitmap);
            canvas.setDensity(0);
            createBitmap.eraseColor(0);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
            return createBitmap;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Bitmap b(InputStream inputStream) {
        return a(a(inputStream));
    }

    public static Bitmap b(byte[] bArr) {
        return a(a(bArr));
    }
}
