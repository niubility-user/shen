package com.jingdong.sdk.platform.lib.openapi.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import androidx.annotation.NonNull;

/* loaded from: classes10.dex */
public interface IFontsUtil {
    void changeTextFont(@NonNull TextView textView);

    void changeTextFont(@NonNull TextView textView, int i2);

    int getMultiBold();

    int getMultiLight();

    int getMultiRegular();

    Typeface getTypeFace(@NonNull Context context);

    Typeface getTypeFace(@NonNull Context context, int i2);
}
