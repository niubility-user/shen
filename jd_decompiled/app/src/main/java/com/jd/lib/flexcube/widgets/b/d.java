package com.jd.lib.flexcube.widgets.b;

import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.lib.babel.servicekit.util.FontUtil;
import com.jd.lib.flexcube.widgets.entity.common.FontInfo;

/* loaded from: classes15.dex */
public class d {
    public static void a(LinearLayout linearLayout, FontInfo fontInfo) {
        if (linearLayout == null) {
            return;
        }
        if (fontInfo == null) {
            linearLayout.setGravity(80);
            return;
        }
        String str = fontInfo.align;
        str.hashCode();
        if (str.equals("2")) {
            linearLayout.setGravity(81);
        } else if (!str.equals("3")) {
            linearLayout.setGravity(80);
        } else {
            linearLayout.setGravity(85);
        }
    }

    public static void b(LinearLayout linearLayout, FontInfo fontInfo, boolean z) {
        if (!z) {
            a(linearLayout, fontInfo);
        } else if (linearLayout == null) {
        } else {
            if (fontInfo == null) {
                linearLayout.setGravity(80);
                return;
            }
            String str = fontInfo.align;
            str.hashCode();
            if (str.equals("2")) {
                linearLayout.setGravity(17);
            } else if (!str.equals("3")) {
                linearLayout.setGravity(16);
            } else {
                linearLayout.setGravity(21);
            }
        }
    }

    public static void c(TextView textView, FontInfo fontInfo) {
        if (textView == null) {
            return;
        }
        if (fontInfo == null) {
            textView.setTypeface(Typeface.DEFAULT);
            textView.getPaint().setFakeBoldText(false);
            return;
        }
        boolean equals = "1".equals(fontInfo.bold);
        boolean equals2 = "1".equals(fontInfo.italic);
        if ("1".equals(fontInfo.jdFont)) {
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
    }
}
