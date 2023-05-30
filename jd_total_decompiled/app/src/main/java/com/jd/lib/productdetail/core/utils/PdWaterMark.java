package com.jd.lib.productdetail.core.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import com.jd.dynamic.DYConstants;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes15.dex */
public class PdWaterMark {
    public static Bitmap createWaterBitmap(Bitmap bitmap, Bitmap bitmap2, String str) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (Log.D) {
            Log.d("PdWaterMark", width + DYConstants.DY_REGEX_COMMA + height + DYConstants.DY_REGEX_COMMA + width2 + DYConstants.DY_REGEX_COMMA + height2);
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, width2, height2, true);
        Paint paint = new Paint(1);
        Typeface create = Typeface.create(Typeface.DEFAULT, 1);
        canvas.drawBitmap(createScaledBitmap, (width - width2) / 2, (height - height2) / 2, (Paint) null);
        if (str != null) {
            paint.setTextSize(35.0f);
            paint.setColor(Color.rgb(255, 127, 0));
            paint.setTypeface(create);
            canvas.drawText(str, (float) (width - 350), (float) (height - 30), paint);
        }
        canvas.save();
        canvas.restore();
        return createBitmap;
    }
}
