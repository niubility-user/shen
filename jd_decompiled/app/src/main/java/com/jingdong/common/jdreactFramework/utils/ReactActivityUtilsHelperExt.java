package com.jingdong.common.jdreactFramework.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeBasePureActivity;

/* loaded from: classes5.dex */
public class ReactActivityUtilsHelperExt extends ReactActivityUtilsHelperBase {
    public static synchronized void startJDReactActivity(Context context, Class cls, String str, Bundle bundle) {
        synchronized (ReactActivityUtilsHelperExt.class) {
            if (context != null) {
                if (!TextUtils.isEmpty(str) && JDReactNativeBasePureActivity.class.isAssignableFrom(cls)) {
                    Intent intent = new Intent(context, cls);
                    intent.putExtra("appname", str);
                    intent.putExtra(JDReactConstant.IntentConstant.MODULE_NAME, str);
                    intent.putExtra("param", bundle);
                    ReactActivityUtilsHelperBase.startClassPluginActivity(context, null, intent, str);
                }
            }
        }
    }
}
