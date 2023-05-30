package com.jingdong.app.mall.sunglass;

import android.app.Application;
import com.jingdong.jdsdk.mta.ExposureRvUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes4.dex */
public class MagnifierUtils {
    public static void hide() {
        if (OKLog.D) {
            try {
                Class.forName("com.jingdong.app.mall.bundle.jdmagnifier.utils.MagnifierWindowManager").getMethod(ExposureRvUtils.HIDE, new Class[0]).invoke(null, new Object[0]);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void show(Application application) {
        if (OKLog.D) {
            try {
                Class.forName("com.jingdong.app.mall.bundle.jdmagnifier.utils.MagnifierWindowManager").getMethod("show", Application.class).invoke(null, application);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
