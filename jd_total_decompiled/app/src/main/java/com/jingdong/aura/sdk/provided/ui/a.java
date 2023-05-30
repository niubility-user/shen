package com.jingdong.aura.sdk.provided.ui;

import android.content.Context;
import android.widget.Toast;

/* loaded from: classes4.dex */
public final class a {
    static IToastUtils a = new IToastUtils() { // from class: com.jingdong.aura.sdk.provided.ui.a.1
        @Override // com.jingdong.aura.sdk.provided.ui.IToastUtils
        public final void shortToast(Context context, String str) {
            try {
                Toast.makeText(context, str, 0).show();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    };

    public static void a(Context context, String str) {
        try {
            a.shortToast(context, str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void a(IToastUtils iToastUtils) {
        if (iToastUtils == null) {
            return;
        }
        a = iToastUtils;
    }
}
