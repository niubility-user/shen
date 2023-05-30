package com.jingdong.common.unification.router.config;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;

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

    /* JADX WARN: Removed duplicated region for block: B:39:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Class<?> getClass(java.lang.String r4) {
        /*
            java.util.HashMap<java.lang.String, java.lang.String> r0 = com.jingdong.common.unification.router.config.MoudleClassConfig.moudleClassMap
            if (r0 == 0) goto L15
            java.lang.Object r0 = r0.get(r4)
            java.lang.String r0 = (java.lang.String) r0
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L15
            java.lang.Class r0 = forClass(r0)
            goto L16
        L15:
            r0 = 0
        L16:
            if (r0 != 0) goto L52
            java.util.ArrayList<java.lang.String> r1 = com.jingdong.common.unification.router.config.MoudleClassConfig.moudlePackageNames
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L52
            java.util.ArrayList<java.lang.String> r1 = com.jingdong.common.unification.router.config.MoudleClassConfig.moudlePackageNames
            java.util.Iterator r1 = r1.iterator()
        L26:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L52
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.String r2 = "."
            r3.append(r2)
            r3.append(r4)
            java.lang.String r2 = r3.toString()
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L26
            java.lang.Class r0 = forClass(r2)
            if (r0 == 0) goto L26
        L52:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.router.config.MoudleClassConfig.getClass(java.lang.String):java.lang.Class");
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
