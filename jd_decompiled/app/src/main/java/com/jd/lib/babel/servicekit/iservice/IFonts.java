package com.jd.lib.babel.servicekit.iservice;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

/* loaded from: classes13.dex */
public interface IFonts {
    public static final int MULTI_BOLD = 4097;
    public static final int MULTI_LIGHT = 4098;
    public static final int MULTI_REGULAR = 4099;

    void changeTextFont(@NonNull TextView textView, int i2);

    Typeface getTypeFace(@NonNull Context context, int i2);

    void setImageIcon(ImageView imageView, String str, String str2);

    void setImageIcon(ImageView imageView, String str, String str2, @ColorInt int i2);
}
