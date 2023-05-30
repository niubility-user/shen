package com.jingdong.app.mall.utils.pay;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;

/* loaded from: classes4.dex */
public class a {
    public static synchronized void a(Context context, Bundle bundle) {
        synchronized (a.class) {
            if (context != null && bundle != null) {
                String string = bundle.getString("isRiskUser");
                String string2 = bundle.getString("businessTag");
                int i2 = bundle.getInt("requestCode", 404);
                if (!TextUtils.equals(string, "1") && !TextUtils.equals(string, DYConstants.DY_TRUE)) {
                    if (TextUtils.equals(string2, "1")) {
                        b.a(2048, context, bundle, i2);
                    } else {
                        b.a(1024, context, bundle, i2);
                    }
                }
                b.a(8192, context, bundle, i2);
            }
        }
    }
}
