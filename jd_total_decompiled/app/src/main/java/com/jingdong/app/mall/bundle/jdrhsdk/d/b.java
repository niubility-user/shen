package com.jingdong.app.mall.bundle.jdrhsdk.d;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class b {
    private static final HashMap<String, Typeface> a = new HashMap<>();

    public static Typeface a(@NonNull Context context) {
        return b(context, 4099);
    }

    public static Typeface b(@NonNull Context context, int i2) {
        Typeface typeface;
        String str;
        if (context == null) {
            return null;
        }
        try {
            if (i2 == 4097) {
                HashMap<String, Typeface> hashMap = a;
                Typeface typeface2 = hashMap.get("bold");
                if (typeface2 == null) {
                    Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "fonts/JDZhengHT-Bold.ttf");
                    hashMap.put("bold", createFromAsset);
                    typeface = createFromAsset;
                } else {
                    typeface = typeface2;
                }
                str = "get bold typeface";
            } else if (i2 != 4098) {
                HashMap<String, Typeface> hashMap2 = a;
                typeface = hashMap2.get(FontsUtil.KEY_MULTI_REGULAR);
                if (typeface == null) {
                    typeface = Typeface.createFromAsset(context.getAssets(), "fonts/JDZhengHT-Regular.ttf");
                    hashMap2.put(FontsUtil.KEY_MULTI_REGULAR, typeface);
                }
                str = "get ragular typeface";
            } else {
                HashMap<String, Typeface> hashMap3 = a;
                Typeface typeface3 = hashMap3.get(FontsUtil.KEY_MULTI_LIGHT);
                if (typeface3 == null) {
                    Typeface createFromAsset2 = Typeface.createFromAsset(context.getAssets(), "fonts/JDZhengHT-Light.ttf");
                    hashMap3.put(FontsUtil.KEY_MULTI_LIGHT, createFromAsset2);
                    typeface = createFromAsset2;
                } else {
                    typeface = typeface3;
                }
                str = "get light typeface";
            }
            d.a("FontsUtil", str);
            return typeface;
        } catch (Throwable th) {
            d.b("FontsUtil", "no typeface", th);
            return null;
        }
    }

    public static void c(@NonNull TextView textView) {
        d(textView, 4099);
    }

    public static void d(@NonNull TextView textView, int i2) {
        if (textView == null || textView.getContext() == null) {
            return;
        }
        Typeface b = b(textView.getContext(), i2);
        if (b != null) {
            textView.setTypeface(b);
        } else {
            d.a("FontsUtil", "font return null.");
        }
        if (d.a) {
            d.a("FontsUtil", i2 != 4097 ? i2 != 4098 ? "changed font by JDZhengHT-Regular.ttf" : "changed font by JDZhengHT-Light.ttf" : "changed font by JDZhengHT-Bold.ttf");
        }
    }
}
