package com.jingdong.app.mall.g;

import android.os.Build;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.threadpool.utils.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class g {
    static List<String> a(String str) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str) && str.length() > 4) {
            try {
                String substring = str.substring(1, str.length() - 1);
                for (String str2 : substring.split(DYConstants.DY_REGEX_COMMA)) {
                    if (!TextUtils.isEmpty(substring) && substring.length() > 2) {
                        arrayList.add(str2.substring(1, str2.length() - 1));
                    }
                }
            } catch (Exception e2) {
                LogUtil.e("getConfigList::", LogUtil.extractThrowableInfo(e2));
            }
        }
        return arrayList;
    }

    public static boolean b(Throwable th) {
        if (!c() || th == null) {
            return false;
        }
        while (th.getCause() != null) {
            th = th.getCause();
        }
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace != null) {
            for (StackTraceElement stackTraceElement : stackTrace) {
                sb.append(stackTraceElement.toString());
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
        if (th.getMessage() != null && (th instanceof NullPointerException) && th.getMessage().contains("Attempt to invoke interface method 'int java.util.List.size()' on a null object reference") && sb.toString().contains("android.widget.AbsListView$UpdateBottomFlagTask.isSuperFloatViewServiceRunning")) {
            LogUtil.e("hit vivo 3max crash!!!!");
            return true;
        }
        return false;
    }

    public static boolean c() {
        boolean z;
        String config = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "hookVivoAsyncTask", "devices");
        String config2 = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "hookVivoAsyncTask", "sdkVersions");
        String[] strArr = new String[2];
        strArr[0] = "devices";
        String str = DYConstants.DY_NULL_STR;
        strArr[1] = config == null ? DYConstants.DY_NULL_STR : config;
        LogUtil.e(strArr);
        String[] strArr2 = new String[2];
        strArr2[0] = "sdkInts";
        if (config2 != null) {
            str = config2;
        }
        strArr2[1] = str;
        LogUtil.e(strArr2);
        List<String> a = a(config);
        List<String> a2 = a(config2);
        LogUtil.i("isVivo3MaxHookNeeded:: devices::" + a, "model:" + BaseInfo.getDeviceModel());
        StringBuilder sb = new StringBuilder();
        sb.append("sdkInt:");
        int i2 = Build.VERSION.SDK_INT;
        sb.append(i2);
        LogUtil.i("isVivo3MaxHookNeeded:: sdkInts::" + a2, sb.toString());
        boolean isEmpty = a.isEmpty();
        boolean isEmpty2 = a2.isEmpty();
        if (isEmpty && isEmpty2) {
            z = d();
        } else if (isEmpty2) {
            z = a.contains(BaseInfo.getDeviceModel());
        } else if (isEmpty) {
            z = a2.contains(String.valueOf(i2));
        } else {
            z = a.contains(BaseInfo.getDeviceModel()) && a2.contains(String.valueOf(i2));
        }
        LogUtil.i("isVivo3MaxHookNeeded:: result::" + z);
        return z;
    }

    static boolean d() {
        if (BaseInfo.getDeviceBrand().trim().toLowerCase().equals("vivo")) {
            String lowerCase = BaseInfo.getDeviceModel().trim().toLowerCase();
            if (lowerCase.contains("vivo v3max") || lowerCase.contains("vivo y55")) {
                int i2 = Build.VERSION.SDK_INT;
                return i2 == 22 || i2 == 23;
            }
            return false;
        }
        return false;
    }
}
