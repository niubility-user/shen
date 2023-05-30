package com.jingdong.sdk.lib.puppetlayout.e;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jingdong.sdk.lib.puppetlayout.g.b;

/* loaded from: classes8.dex */
public class a {
    public static void a(@NonNull TextView textView, int i2) {
        if (textView == null || textView.getContext() == null) {
            return;
        }
        try {
            if (i2 == 4097) {
                textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/JDZhengHT-Bold.ttf"));
                b.a("FontsUtil", "changed font by JDZhengHT-Bold.ttf");
            } else if (i2 == 4098) {
                textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/JDZhengHT-Light.ttf"));
                b.a("FontsUtil", "changed font by JDZhengHT-Light.ttf");
            } else if (i2 == 4099) {
                textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/JDZhengHT-Regular.ttf"));
                b.a("FontsUtil", "changed font by JDZhengHT-Regular.ttf");
            } else {
                textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/JDZhengHT-Regular.ttf"));
                b.a("FontsUtil", "changed font by JDZhengHT-Regular.ttf");
            }
        } catch (Exception e2) {
            if (b.a) {
                e2.printStackTrace();
            }
        }
    }

    public static Typeface b(@NonNull Context context, int i2) {
        if (context == null) {
            return null;
        }
        if (i2 == 4097) {
            Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "fonts/JDZhengHT-Bold.ttf");
            b.a("FontsUtil", "get bold typeface");
            return createFromAsset;
        } else if (i2 == 4098) {
            Typeface createFromAsset2 = Typeface.createFromAsset(context.getAssets(), "fonts/JDZhengHT-Light.ttf");
            b.a("FontsUtil", "get light typeface");
            return createFromAsset2;
        } else if (i2 == 4099) {
            Typeface createFromAsset3 = Typeface.createFromAsset(context.getAssets(), "fonts/JDZhengHT-Regular.ttf");
            b.a("FontsUtil", "get ragular typeface");
            return createFromAsset3;
        } else {
            Typeface createFromAsset4 = Typeface.createFromAsset(context.getAssets(), "fonts/JDZhengHT-Regular.ttf");
            b.a("FontsUtil", "get ragular typeface");
            return createFromAsset4;
        }
    }
}
