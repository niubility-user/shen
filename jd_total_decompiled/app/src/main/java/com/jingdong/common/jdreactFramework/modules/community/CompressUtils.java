package com.jingdong.common.jdreactFramework.modules.community;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import com.jingdong.corelib.utils.Log;
import java.io.ByteArrayOutputStream;

/* loaded from: classes5.dex */
public class CompressUtils {
    private static final String TAG = "CompressUtil";

    public static byte[] compress(String str, int i2, int i3, int i4, int i5, long j2) throws Exception {
        int i6;
        Bitmap bitmap;
        int i7 = i3;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i8 = options.outWidth;
        int i9 = options.outHeight;
        if (Log.D) {
            Log.d(TAG, "\u56fe\u7247\u539f\u59cb\u5bbd\u9ad8: width: " + i8 + " height: " + i9);
        }
        Bitmap bitmap2 = null;
        if (i8 > i7 || i9 > i7) {
            if (i8 > i9) {
                i9 = (i9 * i7) / i8;
            } else {
                int i10 = (i8 * i7) / i9;
                i9 = i7;
                i7 = i10;
            }
            if (Log.D) {
                Log.d(TAG, "\u56fe\u7247\u5c3a\u5bf8\u538b\u7f29: width: " + i7 + " height: " + i9);
            }
            Bitmap createBitmap = Bitmap.createBitmap(i7, i9, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Rect rect = new Rect(0, 0, i7, i9);
            options.inJustDecodeBounds = false;
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            canvas.drawBitmap(decodeFile, (Rect) null, rect, (Paint) null);
            if (!decodeFile.isRecycled()) {
                decodeFile.recycle();
            }
            i6 = i7;
            bitmap2 = createBitmap;
        } else {
            i6 = i8;
        }
        int i11 = i9;
        if (bitmap2 == null) {
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(str, options);
        } else {
            bitmap = bitmap2;
        }
        if (i2 != 0) {
            Matrix matrix = new Matrix();
            matrix.setRotate(i2);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, i6, i11, matrix, true);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (Log.D) {
            Log.d(TAG, "\u56fe\u7247\u8d28\u91cf\u538b\u7f29: quality= 100 size= " + ((byteArrayOutputStream.toByteArray().length / 1024.0f) / 1024.0f));
        }
        for (int i12 = i4; byteArrayOutputStream.toByteArray().length > j2 && i12 >= i5; i12 -= 10) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i12, byteArrayOutputStream);
            if (Log.D) {
                Log.d(TAG, "\u56fe\u7247\u8d28\u91cf\u538b\u7f29: quality= " + i12 + " size= " + ((byteArrayOutputStream.toByteArray().length / 1024.0f) / 1024.0f));
            }
        }
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
