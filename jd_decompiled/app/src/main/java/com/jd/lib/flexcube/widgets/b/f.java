package com.jd.lib.flexcube.widgets.b;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.jd.lib.babel.servicekit.util.FontUtil;
import com.jd.lib.flexcube.iwidget.entity.material.PaddingInfo;
import com.jd.lib.flexcube.widgets.entity.common.FontInfo;

/* loaded from: classes15.dex */
public class f {
    public static int a(Paint paint, CharSequence charSequence, int i2) {
        if (paint == null || TextUtils.isEmpty(charSequence)) {
            return 0;
        }
        paint.setTextSize(i2);
        return (int) paint.measureText(charSequence.toString());
    }

    public static int b(Paint paint, String str) {
        if (paint == null || TextUtils.isEmpty(str)) {
            return 0;
        }
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }

    public static void c(TextView textView, FontInfo fontInfo) {
        if (textView == null) {
            return;
        }
        if (fontInfo != null && !TextUtils.isEmpty(fontInfo.align)) {
            String str = fontInfo.align;
            str.hashCode();
            if (str.equals("2")) {
                textView.setGravity(17);
                return;
            } else if (!str.equals("3")) {
                textView.setGravity(19);
                return;
            } else {
                textView.setGravity(21);
                return;
            }
        }
        textView.setGravity(19);
    }

    public static void d(View view, PaddingInfo paddingInfo, float f2) {
        if (view == null) {
            return;
        }
        if (paddingInfo == null) {
            view.setPadding(0, 0, 0, 0);
            return;
        }
        Rect padding = paddingInfo.getPadding(f2);
        view.setPadding(padding.left, padding.top, padding.right, padding.bottom);
    }

    public static void e(TextView textView, FontInfo fontInfo) {
        if (textView == null) {
            return;
        }
        if (fontInfo == null) {
            textView.setPaintFlags(textView.getPaintFlags() & (-17) & (-9));
            return;
        }
        boolean equals = "1".equals(fontInfo.strickout);
        boolean equals2 = "1".equals(fontInfo.underscore);
        if (equals && equals2) {
            textView.setPaintFlags(textView.getPaintFlags() | 16 | 8);
        } else if (equals) {
            textView.setPaintFlags((textView.getPaintFlags() & (-9)) | 16);
        } else if (equals2) {
            textView.setPaintFlags((textView.getPaintFlags() & (-17)) | 8);
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & (-17) & (-9));
        }
    }

    public static void f(TextView textView, FontInfo fontInfo, float f2, boolean z) {
        if (textView == null) {
            return;
        }
        if (fontInfo == null) {
            textView.setTextSize(0, 0.0f);
            textView.setTypeface(Typeface.DEFAULT);
            textView.getPaint().setFakeBoldText(false);
            return;
        }
        textView.setTextSize(0, com.jd.lib.flexcube.iwidget.b.b.d((int) fontInfo.size, f2));
        boolean equals = "1".equals(fontInfo.bold);
        boolean equals2 = "1".equals(fontInfo.italic);
        if (z && "1".equals(fontInfo.jdFont)) {
            textView.getPaint().setFakeBoldText(false);
            FontUtil.changeTextFont(textView, equals ? 4097 : 4099);
            return;
        }
        textView.getPaint().setFakeBoldText(equals);
        if (equals2) {
            textView.setTypeface(Typeface.defaultFromStyle(2));
        } else {
            textView.setTypeface(Typeface.defaultFromStyle(0));
        }
        textView.setEllipsize(TextUtils.TruncateAt.END);
    }
}
