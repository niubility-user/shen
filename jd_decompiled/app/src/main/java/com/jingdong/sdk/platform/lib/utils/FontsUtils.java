package com.jingdong.sdk.platform.lib.utils;

import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jingdong.sdk.platform.lib.openapi.OpenApiHelper;

/* loaded from: classes10.dex */
public class FontsUtils {
    public static final int MULTI_BOLD = 4097;
    public static final int MULTI_LIGHT = 4098;
    public static final int MULTI_REGULAR = 4099;

    public static void changeTextFont(@NonNull TextView textView, int i2) {
        if (OpenApiHelper.getIFontsUtil() != null) {
            OpenApiHelper.getIFontsUtil().changeTextFont(textView, getFontStyle(i2));
        }
    }

    private static int getFontStyle(int i2) {
        if (OpenApiHelper.getIFontsUtil() == null) {
            return 0;
        }
        switch (i2) {
            case 4097:
                return OpenApiHelper.getIFontsUtil().getMultiBold();
            case 4098:
                return OpenApiHelper.getIFontsUtil().getMultiLight();
            case 4099:
                return OpenApiHelper.getIFontsUtil().getMultiRegular();
            default:
                return 0;
        }
    }
}
