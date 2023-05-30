package com.jd.dynamic.b.a;

import android.text.TextUtils;
import com.jd.dynamic.base.interfaces.IABConfig;
import java.util.Map;

/* loaded from: classes13.dex */
public class a {
    private IABConfig a;

    public a(IABConfig iABConfig) {
        this.a = iABConfig;
    }

    public String a(String str, String str2) {
        Map<String, String> c2 = c(str);
        if (c2 == null) {
            return null;
        }
        return c2.get(str2);
    }

    public String b(String str, String str2, String str3) {
        Map<String, String> c2 = c(str);
        if (c2 == null) {
            return str3;
        }
        String str4 = c2.get(str2);
        return TextUtils.isEmpty(str4) ? str3 : str4;
    }

    public Map<String, String> c(String str) {
        IABConfig iABConfig = this.a;
        if (iABConfig == null || iABConfig.getAbData() == null) {
            return null;
        }
        return this.a.getAbData().get(str);
    }
}
