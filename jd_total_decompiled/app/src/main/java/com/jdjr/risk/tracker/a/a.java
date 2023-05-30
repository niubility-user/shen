package com.jdjr.risk.tracker.a;

import android.os.Build;
import com.jd.dynamic.DYConstants;

/* loaded from: classes18.dex */
public class a {
    public static String a() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            if (Build.VERSION.SDK_INT < 21) {
                String str = Build.CPU_ABI;
                if (str != null && str.length() > 0) {
                    stringBuffer.append(str);
                }
                String str2 = Build.CPU_ABI2;
                if (str2 != null && str2.length() > 0) {
                    stringBuffer.append(DYConstants.DY_REGEX_COMMA);
                    stringBuffer.append(str2);
                }
            } else {
                String[] strArr = Build.SUPPORTED_ABIS;
                if (strArr != null && strArr.length > 0) {
                    for (String str3 : strArr) {
                        stringBuffer.append(str3 + DYConstants.DY_REGEX_COMMA);
                    }
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }
            }
        } catch (Exception unused) {
        }
        return stringBuffer.toString();
    }
}
