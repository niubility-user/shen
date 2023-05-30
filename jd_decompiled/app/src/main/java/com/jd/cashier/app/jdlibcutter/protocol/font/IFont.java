package com.jd.cashier.app.jdlibcutter.protocol.font;

import android.graphics.Typeface;
import android.widget.TextView;

/* loaded from: classes13.dex */
public interface IFont {
    public static final byte MULTI_BOLD = 1;
    public static final byte MULTI_DEFAULT = 4;
    public static final byte MULTI_LIGHT = 2;
    public static final byte MULTI_REGULAR = 3;

    void changeTextFont(TextView textView, byte b);

    Typeface getTypeFace(byte b);
}
