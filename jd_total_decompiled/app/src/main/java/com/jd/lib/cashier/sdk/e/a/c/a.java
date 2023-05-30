package com.jd.lib.cashier.sdk.e.a.c;

import android.content.Context;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.utils.p;
import java.util.Map;

/* loaded from: classes14.dex */
public class a extends com.jd.lib.cashier.sdk.core.aac.a {
    public void c(FragmentActivity fragmentActivity, Map<String, String> map) {
        p.f(fragmentActivity, map);
    }

    public void d(Context context, String str, String str2) {
        if (TextUtils.equals(str, "1")) {
            if (!TextUtils.isEmpty(str2)) {
                a(context, str2);
            } else {
                b(context);
            }
        }
    }
}
