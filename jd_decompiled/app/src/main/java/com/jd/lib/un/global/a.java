package com.jd.lib.un.global;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;

/* loaded from: classes.dex */
public interface a {
    Typeface getTypeface(Context context, int i2);

    boolean isDialogAutoElder();

    void jdRouter(String str);

    void onClickWithPageId(Context context, String str, String str2, String str3, String str4);

    Bitmap qrCode(String str);

    void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6);
}
