package com.jingdong.app.mall.mylib.CouponUnit.Dynamic;

import android.text.TextUtils;
import com.jd.dynamic.base.CommFunction;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class FunctionManager {
    private static Map<String, Class<? extends CommFunction>> functions;

    static {
        HashMap hashMap = new HashMap();
        functions = hashMap;
        hashMap.put("CouponEvent", CouponControl.class);
    }

    public static boolean contains(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return functions.containsKey(str);
    }

    public static CommFunction get(String str) {
        Class<? extends CommFunction> cls;
        if (TextUtils.isEmpty(str) || (cls = functions.get(str)) == null) {
            return null;
        }
        try {
            return cls.newInstance();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
