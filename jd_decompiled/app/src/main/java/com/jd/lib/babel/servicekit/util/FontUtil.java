package com.jd.lib.babel.servicekit.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.servicekit.BabelServer;
import com.jd.lib.babel.servicekit.iservice.IFonts;

/* loaded from: classes13.dex */
public class FontUtil {
    public static void changeTextFont(@NonNull TextView textView, int i2) {
        IFonts iFonts = (IFonts) BabelServer.get().getService(IFonts.class);
        if (iFonts != null) {
            iFonts.changeTextFont(textView, i2);
        }
    }

    public static Typeface getTypeFace(@NonNull Context context, int i2) {
        IFonts iFonts = (IFonts) BabelServer.get().getService(IFonts.class);
        if (iFonts != null) {
            return iFonts.getTypeFace(context, i2);
        }
        return null;
    }

    public static void changeTextFont(@NonNull TextView textView) {
        changeTextFont(textView, 4099);
    }

    public static Typeface getTypeFace(@NonNull Context context) {
        return getTypeFace(context, 4099);
    }
}
