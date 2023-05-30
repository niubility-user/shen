package com.jingdong.common.recommend;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.FloatRange;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes6.dex */
public class RecommendImageUtils {
    public static void downloadAnddisplay(String str, final View view) {
        if (TextUtils.isEmpty(str) || view == null) {
            return;
        }
        try {
            JDImageLoader.getBitmap(str, null, new ImageRequestListener<Bitmap>() { // from class: com.jingdong.common.recommend.RecommendImageUtils.1
                @Override // com.jd.mobile.image.ImageRequestListener
                public void onCancel() {
                }

                @Override // com.jd.mobile.image.ImageRequestListener
                public void onFailure(Throwable th) {
                }

                @Override // com.jd.mobile.image.ImageRequestListener
                public void onSuccess(final Bitmap bitmap) {
                    view.post(new Runnable() { // from class: com.jingdong.common.recommend.RecommendImageUtils.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RecommendImageUtils.setBackgroundCompatiable(view, new BitmapDrawable(bitmap));
                        }
                    });
                }
            });
        } catch (Exception unused) {
        }
    }

    public static Bitmap getScaleBitmap(Bitmap bitmap, float f2) {
        if (bitmap == null || bitmap.getHeight() == f2) {
            return bitmap;
        }
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            float f3 = width;
            float f4 = height;
            matrix.setRectToRect(new RectF(0.0f, 0.0f, f3, f4), new RectF(0.0f, 0.0f, (f3 * f2) / f4, f2), Matrix.ScaleToFit.FILL);
            return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static byte[] getXNinePatchChunk(Bitmap bitmap, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        if (bitmap == null) {
            return null;
        }
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i2 = (int) (width * f2);
            if (width >= 10 && height >= 10 && i2 < width) {
                ByteBuffer order = ByteBuffer.allocate(84).order(ByteOrder.nativeOrder());
                Integer num = 1;
                order.put(num.byteValue());
                Integer num2 = 2;
                order.put(num2.byteValue());
                Integer num3 = 2;
                order.put(num3.byteValue());
                Integer num4 = 9;
                order.put(num4.byteValue());
                order.putInt(0);
                order.putInt(0);
                order.putInt(0);
                order.putInt(0);
                order.putInt(0);
                order.putInt(0);
                order.putInt(0);
                order.putInt(i2);
                order.putInt(i2 + 1);
                order.putInt(0);
                order.putInt(height);
                for (int i3 = 0; i3 < 9; i3++) {
                    order.putInt(1);
                }
                byte[] array = order.array();
                if (NinePatch.isNinePatchChunk(array)) {
                    return array;
                }
                return null;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static void setBackgroundCompatiable(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }
}
