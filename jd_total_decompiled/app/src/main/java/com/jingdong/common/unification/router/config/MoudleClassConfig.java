package com.jingdong.common.unification.router.config;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class MoudleClassConfig {
    private static HashMap<String, String> moudleClassMap = new HashMap<>(16);
    private static ArrayList<String> moudlePackageNames = new ArrayList<>(2);

    private static Class<?> forClass(String str) {
        if (JDRouterConfig.getLoader() != null) {
            try {
                return JDRouterConfig.getLoader().loadClass(str);
            } catch (Exception e2) {
                if (JDRouterConfig.D) {
                    e2.printStackTrace();
                }
            }
        } else {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e3) {
                if (JDRouterConfig.D) {
                    e3.printStackTrace();
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Class<?> getClass(String str) {
        Class<?> cls;
        Iterator<String> it;
        HashMap<String, String> hashMap = moudleClassMap;
        if (hashMap != null) {
            String str2 = hashMap.get(str);
            if (!TextUtils.isEmpty(str2)) {
                cls = forClass(str2);
                if (cls == null && !moudlePackageNames.isEmpty()) {
                    it = moudlePackageNames.iterator();
                    while (it.hasNext()) {
                        String str3 = it.next() + OrderISVUtil.MONEY_DECIMAL + str;
                        if (!TextUtils.isEmpty(str3) && (cls = forClass(str3)) != null) {
                            break;
                        }
                    }
                }
                return cls;
            }
        }
        cls = null;
        if (cls == null) {
            it = moudlePackageNames.iterator();
            while (it.hasNext()) {
            }
        }
        return cls;
    }

    public static void register(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (moudleClassMap.containsKey(str) && JDRouterConfig.D) {
            JDRouterConfig.e("JDRouterConfig", "moudle = " + str + " has been register!");
        }
        moudleClassMap.put(str, str2);
    }

    public static void registerPackage(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        moudlePackageNames.add(str);
    }
}
