package com.jd.dynamic.base.interfaces;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.widget.TextView;

/* loaded from: classes13.dex */
public interface IUniConfig {
    Bitmap getBitmap(String str);

    TextView getTextViewOrNull(String str, String str2);

    Typeface getTypefaceWithName(String str);

    void setTextViewProperties(String str, TextView textView);
}
