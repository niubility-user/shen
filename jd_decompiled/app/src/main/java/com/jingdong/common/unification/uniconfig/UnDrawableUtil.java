package com.jingdong.common.unification.uniconfig;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import com.jd.lib.un.business.widget.a;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.UnLog;
import java.text.DecimalFormat;

/* loaded from: classes6.dex */
public class UnDrawableUtil {
    public static Drawable createDrawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        byte[] ninePatchChunk = bitmap.getNinePatchChunk();
        if (NinePatch.isNinePatchChunk(ninePatchChunk)) {
            return new NinePatchDrawable(a.g().d().getResources(), bitmap, ninePatchChunk, new Rect(), null);
        }
        return new BitmapDrawable(a.g().d().getResources(), bitmap);
    }

    public static Bitmap ninePathDrawable2Bitmap(NinePatchDrawable ninePatchDrawable) {
        Bitmap createBitmap = Bitmap.createBitmap(ninePatchDrawable.getIntrinsicWidth(), ninePatchDrawable.getIntrinsicHeight(), ninePatchDrawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        ninePatchDrawable.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, boolean z) {
        if (bitmap == null) {
            return null;
        }
        float density = DpiUtil.getDensity(a.g().d());
        if (UnLog.D) {
            UnLog.d("DrawableInfo", "dpi:" + density);
        }
        float f2 = 1.3f;
        float f3 = ((z ? 1.3f : 1.0f) * density) / 3.0f;
        if (density < 3.0d) {
            f2 = f3;
        } else if (!z) {
            return bitmap;
        }
        if (UnLog.D) {
            UnLog.d("DrawableInfo", "scale:" + f2);
        }
        Matrix matrix = new Matrix();
        if (f2 < 1.0f) {
            try {
                f2 = Float.valueOf(new DecimalFormat("0.00").format(f2)).floatValue();
            } catch (Exception e2) {
                if (UnLog.D) {
                    e2.printStackTrace();
                }
            }
        }
        matrix.postScale(f2, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
