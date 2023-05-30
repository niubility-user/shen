package tv.danmaku.ijk.media.alpha.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import tv.danmaku.ijk.media.alpha.mix.Src;

/* loaded from: classes11.dex */
public class BitmapUtil {
    public static Bitmap crateEmptyBitmap() {
        Bitmap createBitmap = Bitmap.createBitmap(16, 16, Bitmap.Config.ARGB_8888);
        createBitmap.eraseColor(0);
        return createBitmap;
    }

    public static Bitmap createTxtBitmap(Src src) {
        int w = src.getW();
        int h2 = src.getH();
        Bitmap createBitmap = Bitmap.createBitmap(w, h2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Rect rect = new Rect(0, 0, w, h2);
        Rect rect2 = new Rect();
        TextPaint textPaint = new TextPaint();
        float f2 = h2;
        float f3 = 0.8f;
        textPaint.setTextSize(f2 * 0.8f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        if (src.getStyle() == Src.Style.BOLD) {
            textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
        }
        textPaint.setColor(src.getColor());
        String txt = src.getTxt();
        while (f3 > 0.1f) {
            textPaint.getTextBounds(txt, 0, txt.length(), rect2);
            if (rect2.width() <= rect.width()) {
                break;
            }
            f3 -= 0.1f;
            textPaint.setTextSize(f2 * f3);
        }
        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        canvas.drawText(txt, rect.centerY(), (rect.centerY() - (fontMetricsInt.top / 2)) - (fontMetricsInt.bottom / 2), textPaint);
        return createBitmap;
    }
}
