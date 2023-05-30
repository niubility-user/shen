package com.jdjr.generalKeyboard.common;

import android.content.Context;
import android.graphics.Typeface;

/* loaded from: classes18.dex */
public class TextFontUtils {
    public static Typeface getUDC1BoldFont(Context context) {
        try {
            return Typeface.createFromAsset(context.getAssets(), "font/JR-UDC-Bold.otf");
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
