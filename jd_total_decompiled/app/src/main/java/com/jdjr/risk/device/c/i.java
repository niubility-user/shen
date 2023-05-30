package com.jdjr.risk.device.c;

import android.os.Process;
import com.jingdong.common.utils.LangUtils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
    */
    private static List<String> c() {
        BufferedReader bufferedReader;
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        FileReader fileReader = null;
        try {
            FileReader fileReader2 = new FileReader("/proc/" + Process.myPid() + "/maps");
            try {
                bufferedReader = new BufferedReader(fileReader2);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            try {
                                break;
                            } catch (IOException unused) {
                            }
                        } else if (readLine.endsWith(".so") || readLine.endsWith(".jar")) {
                            hashSet.add(readLine.substring(readLine.lastIndexOf(LangUtils.SINGLE_SPACE) + 1));
                        }
                    } catch (FileNotFoundException unused2) {
                        fileReader = fileReader2;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException unused3) {
                            }
                        }
                    } catch (IOException unused4) {
                        fileReader = fileReader2;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException unused5) {
                            }
                        }
                    } catch (Throwable unused6) {
                        fileReader = fileReader2;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException unused7) {
                            }
                        }
                    }
                }
                fileReader2.close();
            } catch (FileNotFoundException unused8) {
                bufferedReader = null;
            } catch (IOException unused9) {
                bufferedReader = null;
            } catch (Throwable unused10) {
                bufferedReader = null;
            }
        } catch (FileNotFoundException unused11) {
            bufferedReader = null;
        } catch (IOException unused12) {
            bufferedReader = null;
        } catch (Throwable unused13) {
            bufferedReader = null;
        }
        try {
            bufferedReader.close();
        } catch (IOException unused14) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str.contains("com.saurik.substrate") || str.contains("XposedBridge.jar") || str.contains("libsubstrate.so") || str.contains("libsubstrate-dvm.so")) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        }
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
