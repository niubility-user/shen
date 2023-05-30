package com.tencent.open.b;

import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.Locale;

/* loaded from: classes9.dex */
public class d {
    private static String a;
    private static String b;

    public static String a(Context context) {
        if (TextUtils.isEmpty(a)) {
            if (context == null) {
                return "";
            }
            a = "";
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                a = windowManager.getDefaultDisplay().getWidth() + JshopConst.JSHOP_PROMOTIO_X + windowManager.getDefaultDisplay().getHeight();
            }
            return a;
        }
        return a;
    }

    public static String a() {
        return Locale.getDefault().getLanguage();
    }
}
