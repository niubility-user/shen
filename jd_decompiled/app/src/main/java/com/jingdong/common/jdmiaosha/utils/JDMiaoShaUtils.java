package com.jingdong.common.jdmiaosha.utils;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes5.dex */
public class JDMiaoShaUtils {
    private static SparseArray<Typeface> typefaceSparseArray = new SparseArray<>();
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void changeFontType(Context context, TextView textView) {
        changeFontType(context, textView, 4099);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T convert(Object obj) {
        return obj;
    }

    public static void sendClickDataWithExt(String str, String str2, String str3) {
        sendClickDataWithExt(str, null, str2, null, str3);
    }

    public static void setRoundCorner(View view, final int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setOutlineProvider(new ViewOutlineProvider() { // from class: com.jingdong.common.jdmiaosha.utils.JDMiaoShaUtils.1
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view2, Outline outline) {
                    outline.setRoundRect(0, 0, view2.getWidth(), view2.getHeight(), i2);
                }
            });
            view.setClipToOutline(true);
        }
    }

    public static void changeFontType(Context context, TextView textView, int i2) {
        Typeface typeface = typefaceSparseArray.get(i2);
        if (typeface == null) {
            typeface = FontsUtil.getTypeFace(context, i2);
            typefaceSparseArray.put(i2, typeface);
        }
        textView.setTypeface(typeface);
    }

    public static void sendClickDataWithExt(String str, String str2, String str3, String str4, String str5) {
        Context applicationContext = JdSdk.getInstance().getApplication().getApplicationContext();
        if (applicationContext == null) {
            return;
        }
        JDMtaUtils.sendClickDataWithExt(applicationContext, str, str2, null, str3, null, str4, null, str5, null);
    }

    public static void setRoundCorner(View view, final int i2, final boolean z, final boolean z2) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setOutlineProvider(new ViewOutlineProvider() { // from class: com.jingdong.common.jdmiaosha.utils.JDMiaoShaUtils.2
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view2, Outline outline) {
                    outline.setRoundRect(0, 0, view2.getWidth() + DPIUtil.dip2px(z2 ? 12.0f : 0.0f), view2.getHeight() + DPIUtil.dip2px(z ? 12.0f : 0.0f), i2);
                }
            });
            view.setClipToOutline(true);
        }
    }
}
