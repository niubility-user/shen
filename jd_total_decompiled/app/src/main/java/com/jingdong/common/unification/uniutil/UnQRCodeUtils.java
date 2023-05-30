package com.jingdong.common.unification.uniutil;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.Hashtable;

/* loaded from: classes6.dex */
public class UnQRCodeUtils {
    public static Bitmap addLogo(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        if (width2 == 0 || height2 == 0) {
            return bitmap;
        }
        float f2 = ((width * 1.0f) / 5.0f) / width2;
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas.scale(f2, f2, width / 2, height / 2);
            canvas.drawBitmap(bitmap2, (width - width2) / 2, (height - height2) / 2, (Paint) null);
            canvas.save();
            canvas.restore();
            return createBitmap;
        } catch (Exception e2) {
            e2.getStackTrace();
            return null;
        }
    }

    public static Bitmap createQRCode(String str) {
        Bitmap bitmap = null;
        try {
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, DPIUtil.getWidthByDesignValue720(JdSdk.getInstance().getApplicationContext(), 256), DPIUtil.getWidthByDesignValue720(JdSdk.getInstance().getApplicationContext(), 256), hashtable);
            int width = encode.getWidth();
            int height = encode.getHeight();
            int[] iArr = new int[width * height];
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < height; i4++) {
                for (int i5 = 0; i5 < width; i5++) {
                    if (encode.get(i5, i4)) {
                        if (i2 == 0 && i3 == 0) {
                            i3 = i4;
                            i2 = i5;
                        }
                        iArr[(i4 * width) + i5] = -16777216;
                    } else {
                        iArr[(i4 * width) + i5] = -1;
                    }
                }
            }
            int i6 = width - (i2 * 2);
            int i7 = height - (i3 * 2);
            int[] iArr2 = new int[i6 * i7];
            for (int i8 = 0; i8 < i7; i8++) {
                for (int i9 = 0; i9 < i6; i9++) {
                    iArr2[(i8 * i6) + i9] = iArr[((i8 + i3) * width) + i9 + i2];
                }
            }
            bitmap = Bitmap.createBitmap(i6, i7, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(iArr2, 0, i6, 0, 0, i6, i7);
            return bitmap;
        } catch (Exception e2) {
            if (OKLog.E) {
                e2.printStackTrace();
                return bitmap;
            }
            return bitmap;
        }
    }
}
