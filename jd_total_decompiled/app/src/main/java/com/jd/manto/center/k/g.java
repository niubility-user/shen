package com.jd.manto.center.k;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.res.ResourcesCompat;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes17.dex */
public class g {
    public static Drawable a(int i2) {
        Context applicationContext;
        if (i2 == 0 || (applicationContext = JdSdk.getInstance().getApplicationContext()) == null || applicationContext.getResources() == null) {
            return null;
        }
        try {
            return ResourcesCompat.getDrawable(applicationContext.getResources(), i2, null);
        } catch (Exception e2) {
            if (OKLog.D) {
                throw new RuntimeException(e2);
            }
            return null;
        }
    }
}
