package com.jingdong.sdk.jdshare.utils;

import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.RequiresApi;

/* loaded from: classes7.dex */
public class h {
    private static Paint a;

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(api = 21)
    /* loaded from: classes7.dex */
    public static class a extends ViewOutlineProvider {
        private Rect a = new Rect();
        private int b;

        a() {
        }

        boolean a(boolean z, Rect rect, int i2) {
            int i3 = rect.left * (z ? 1 : -i2);
            int i4 = rect.top * (z ? 1 : -i2);
            int i5 = rect.right * (z ? -1 : i2);
            int i6 = rect.bottom * (z ? -1 : i2);
            boolean z2 = false;
            Rect rect2 = this.a;
            if (rect2.left != i3) {
                rect2.left = i3;
                z2 = true;
            }
            if (rect2.top != i4) {
                rect2.top = i4;
                z2 = true;
            }
            if (rect2.right != i5) {
                rect2.right = i5;
                z2 = true;
            }
            if (rect2.bottom != i6) {
                rect2.bottom = i6;
                z2 = true;
            }
            if (this.b != i2) {
                this.b = i2;
                return true;
            }
            return z2;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            Rect rect = this.a;
            outline.setRoundRect(rect.left, rect.top, view.getWidth() + this.a.right, view.getHeight() + this.a.bottom, this.b);
        }
    }

    static {
        Paint paint = new Paint();
        a = paint;
        paint.setTextSize(20.0f);
    }

    public static void a(View view, int i2) {
        d(view, new Rect(0, 0, 0, 0), i2);
    }

    public static String b(int i2, String str) {
        double measureText = a.measureText("\u5bbd");
        double d = i2;
        Double.isNaN(d);
        Double.isNaN(measureText);
        return c(a, (int) (measureText * (d + 0.5d)), str, 0);
    }

    private static String c(Paint paint, int i2, String str, int i3) {
        if (i2 <= 10 || i3 > 20 || TextUtils.isEmpty(str)) {
            return "";
        }
        if (paint == null) {
            return str;
        }
        float measureText = paint.measureText(str);
        if (measureText < i2) {
            return str;
        }
        int length = str.length();
        int i4 = length - 1;
        if (i4 <= 0) {
            return "";
        }
        if (measureText > (i2 << 1)) {
            i4 = length - (length >> 2);
        }
        return c(paint, i2, str.substring(0, i4), i3 + 1);
    }

    private static void d(View view, Rect rect, int i2) {
        a aVar;
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        ViewOutlineProvider outlineProvider = view.getOutlineProvider();
        if (outlineProvider instanceof a) {
            aVar = (a) outlineProvider;
        } else {
            aVar = new a();
        }
        boolean a2 = aVar.a(false, rect, i2);
        if (!view.getClipToOutline()) {
            view.setClipToOutline(true);
        }
        if (a2) {
            view.setOutlineProvider(aVar);
        }
    }
}
