package com.jdjr.risk.device.c;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes18.dex */
public class i {
    private static List<String> a(Object obj, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            Set<String> keySet = ((HashMap) declaredField.get(obj)).keySet();
            if (!keySet.isEmpty()) {
                for (String str2 : keySet) {
                    if (str2.contains("com.jd") || str2.contains("com.jingdong")) {
                        arrayList.add(str2);
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    public static Map<String, List<String>> a() {
        HashMap hashMap = new HashMap();
        List<String> b = b();
        if (!b.isEmpty()) {
            hashMap.put("xposed", b);
        }
        List<String> c2 = c();
        if (!c2.isEmpty()) {
            hashMap.put("files", c2);
        }
        List<String> d = d();
        if (!d.isEmpty()) {
            hashMap.put("stack", d);
        }
        return hashMap;
    }

    private static List<String> b() {
        ArrayList arrayList = new ArrayList();
        try {
            Object newInstance = ClassLoader.getSystemClassLoader().loadClass("de.robv.android.xposed.XposedHelpers").newInstance();
            arrayList.addAll(a(newInstance, "fieldCache"));
            arrayList.addAll(a(newInstance, "methodCache"));
            arrayList.addAll(a(newInstance, "constructorCache"));
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x006d, code lost:
        if (r4 == null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0078, code lost:
        if (r4 == null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0083, code lost:
        if (r4 == null) goto L43;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.List<java.lang.String> c() {
        /*
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L70 java.io.FileNotFoundException -> L7b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L70 java.io.FileNotFoundException -> L7b
            r4.<init>()     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L70 java.io.FileNotFoundException -> L7b
            java.lang.String r5 = "/proc/"
            r4.append(r5)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L70 java.io.FileNotFoundException -> L7b
            int r5 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L70 java.io.FileNotFoundException -> L7b
            r4.append(r5)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L70 java.io.FileNotFoundException -> L7b
            java.lang.String r5 = "/maps"
            r4.append(r5)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L70 java.io.FileNotFoundException -> L7b
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L70 java.io.FileNotFoundException -> L7b
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L70 java.io.FileNotFoundException -> L7b
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f java.io.FileNotFoundException -> L62
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f java.io.FileNotFoundException -> L62
        L2f:
            java.lang.String r2 = r4.readLine()     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L60 java.io.FileNotFoundException -> L63
            if (r2 == 0) goto L55
            java.lang.String r5 = ".so"
            boolean r5 = r2.endsWith(r5)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L60 java.io.FileNotFoundException -> L63
            if (r5 != 0) goto L45
            java.lang.String r5 = ".jar"
            boolean r5 = r2.endsWith(r5)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L60 java.io.FileNotFoundException -> L63
            if (r5 == 0) goto L2f
        L45:
            java.lang.String r5 = " "
            int r5 = r2.lastIndexOf(r5)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L60 java.io.FileNotFoundException -> L63
            int r5 = r5 + 1
            java.lang.String r2 = r2.substring(r5)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L60 java.io.FileNotFoundException -> L63
            r0.add(r2)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L60 java.io.FileNotFoundException -> L63
            goto L2f
        L55:
            r3.close()     // Catch: java.io.IOException -> L58
        L58:
            r4.close()     // Catch: java.io.IOException -> L86
            goto L86
        L5c:
            r4 = r2
        L5d:
            r2 = r3
            goto L66
        L5f:
            r4 = r2
        L60:
            r2 = r3
            goto L71
        L62:
            r4 = r2
        L63:
            r2 = r3
            goto L7c
        L65:
            r4 = r2
        L66:
            if (r2 == 0) goto L6d
            r2.close()     // Catch: java.io.IOException -> L6c
            goto L6d
        L6c:
        L6d:
            if (r4 == 0) goto L86
            goto L58
        L70:
            r4 = r2
        L71:
            if (r2 == 0) goto L78
            r2.close()     // Catch: java.io.IOException -> L77
            goto L78
        L77:
        L78:
            if (r4 == 0) goto L86
            goto L58
        L7b:
            r4 = r2
        L7c:
            if (r2 == 0) goto L83
            r2.close()     // Catch: java.io.IOException -> L82
            goto L83
        L82:
        L83:
            if (r4 == 0) goto L86
            goto L58
        L86:
            java.util.Iterator r0 = r0.iterator()
        L8a:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto Lba
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "com.saurik.substrate"
            boolean r3 = r2.contains(r3)
            if (r3 != 0) goto Lb6
            java.lang.String r3 = "XposedBridge.jar"
            boolean r3 = r2.contains(r3)
            if (r3 != 0) goto Lb6
            java.lang.String r3 = "libsubstrate.so"
            boolean r3 = r2.contains(r3)
            if (r3 != 0) goto Lb6
            java.lang.String r3 = "libsubstrate-dvm.so"
            boolean r3 = r2.contains(r3)
            if (r3 == 0) goto L8a
        Lb6:
            r1.add(r2)
            goto L8a
        Lba:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.device.c.i.c():java.util.List");
    }

    private static List<String> d() {
        ArrayList arrayList = new ArrayList();
        try {
            throw new Exception("checkHook");
        } catch (Exception e2) {
            int i2 = 0;
            for (StackTraceElement stackTraceElement : e2.getStackTrace()) {
                boolean z = true;
                if (!("com.android.internal.os.ZygoteInit".equals(stackTraceElement.getClassName()) && (i2 = i2 + 1) == 2) && ((!"com.saurik.substrate.MS$2".equals(stackTraceElement.getClassName()) || !"invoked".equals(stackTraceElement.getMethodName())) && ((!"de.robv.android.xposed.XposedBridge".equals(stackTraceElement.getClassName()) || !"main".equals(stackTraceElement.getMethodName())) && (!"de.robv.android.xposed.XposedBridge".equals(stackTraceElement.getClassName()) || !"handleHookedMethod".equals(stackTraceElement.getMethodName()))))) {
                    z = false;
                }
                if (z) {
                    arrayList.add(stackTraceElement.toString());
                }
            }
            return arrayList;
        }
    }
}
