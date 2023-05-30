package com.jingdong.common.unification.uniconfig;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import com.jingdong.common.UnLog;
import com.jingdong.common.utils.LangUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class UnSpannaleUtils {
    public static Bitmap bitmapOpt(Bitmap bitmap, int i2) {
        float floatValue = Float.valueOf(i2).floatValue() / Float.valueOf(bitmap.getHeight()).floatValue();
        UnLog.d("iconWidth", i2 + "    /////      " + bitmap.getHeight() + "      " + floatValue);
        Matrix matrix = new Matrix();
        matrix.postScale(floatValue, floatValue);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static SpannableString getImageSpan(Context context, List<Bitmap> list, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < list.size(); i2++) {
            sb.append(1);
            sb.append(LangUtils.SINGLE_SPACE);
        }
        sb.append(str);
        SpannableString spannableString = new SpannableString(sb.toString());
        Iterator<Bitmap> it = list.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            JDImageSpan jDImageSpan = new JDImageSpan(context, it.next(), 3);
            jDImageSpan.setIncludeFontPadding(false);
            int i4 = i3 * 2;
            spannableString.setSpan(jDImageSpan, i4, i4 + 1, 17);
            i3++;
        }
        return spannableString;
    }

    /* loaded from: classes6.dex */
    private static class JDImageSpan extends ImageSpan {
        public static final int ALIGN_CENTER = 3;
        private boolean includeFontPadding;

        public JDImageSpan(Drawable drawable) {
            super(drawable);
            this.includeFontPadding = true;
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
            int i7;
            int i8;
            int i9;
            Drawable drawable = getDrawable();
            canvas.save();
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            if (this.includeFontPadding) {
                int i10 = fontMetricsInt.ascent;
                i7 = fontMetricsInt.top + i5;
                int i11 = fontMetricsInt.descent;
                i8 = fontMetricsInt.bottom;
            } else {
                i7 = fontMetricsInt.ascent + i5;
                i8 = fontMetricsInt.descent;
            }
            int i12 = i5 + i8;
            int i13 = i12 - i7;
            int i14 = drawable.getBounds().bottom - drawable.getBounds().top;
            int i15 = ((ImageSpan) this).mVerticalAlignment;
            if (i15 != 1) {
                i9 = i15 != 3 ? (i7 + i13) - i14 : i7 + ((i13 - i14) / 2);
            } else {
                i9 = ((i7 + i13) - i14) - i12;
            }
            canvas.translate(f2, i9);
            drawable.draw(canvas);
            canvas.restore();
        }

        public void setIncludeFontPadding(boolean z) {
            this.includeFontPadding = z;
        }

        public JDImageSpan(Context context, Bitmap bitmap) {
            super(context, bitmap);
            this.includeFontPadding = true;
        }

        public JDImageSpan(Context context, Bitmap bitmap, int i2) {
            super(context, bitmap, i2);
            this.includeFontPadding = true;
        }

        public JDImageSpan(Drawable drawable, int i2) {
            super(drawable, i2);
            this.includeFontPadding = true;
        }
    }
}
