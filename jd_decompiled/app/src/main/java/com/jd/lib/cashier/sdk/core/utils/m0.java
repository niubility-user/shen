package com.jd.lib.cashier.sdk.core.utils;

import android.graphics.Typeface;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.font.IFont;
import java.util.regex.Pattern;

/* loaded from: classes14.dex */
public class m0 {
    private static Typeface a;
    private static Typeface b;

    /* renamed from: c  reason: collision with root package name */
    private static Typeface f3097c;

    static {
        Pattern.compile("\\d+");
    }

    public static void a(TextView textView, byte b2) {
        if (textView == null || b2 <= 0) {
            return;
        }
        try {
            if (b2 == 1) {
                textView.setTypeface(b((byte) 1));
            } else if (b2 == 2) {
                textView.setTypeface(b((byte) 2));
            } else if (b2 == 3) {
                textView.setTypeface(b((byte) 3));
            } else if (b2 != 4) {
            } else {
                textView.setTypeface(Typeface.DEFAULT);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static Typeface b(byte b2) {
        IFont font = DependInitializer.getFont();
        if (b2 == 1) {
            if (a == null && font != null) {
                a = font.getTypeFace((byte) 1);
            }
            return a;
        } else if (b2 == 2) {
            if (b == null && font != null) {
                b = font.getTypeFace((byte) 2);
            }
            return b;
        } else if (b2 != 3) {
            return null;
        } else {
            if (f3097c == null && font != null) {
                f3097c = font.getTypeFace((byte) 3);
            }
            return f3097c;
        }
    }
}
