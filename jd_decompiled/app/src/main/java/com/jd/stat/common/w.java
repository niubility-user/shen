package com.jd.stat.common;

import android.text.TextUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class w {
    private static boolean a;

    public static void a() {
        a = d();
    }

    public static boolean b() {
        if (a) {
            a = false;
            return true;
        }
        return false;
    }

    public static JSONObject c() {
        Object obj;
        try {
            try {
                Constructor<?> declaredConstructor = Class.forName("de.robv.android.xposed.XposedHelpers", true, ClassLoader.getSystemClassLoader()).getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                obj = declaredConstructor.newInstance(new Object[0]);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
                obj = null;
            }
            JSONObject jSONObject = new JSONObject();
            if (obj != null) {
                try {
                    jSONObject.put("fieldCache", a(obj, "fieldCache"));
                    jSONObject.put("methodCache", a(obj, "methodCache"));
                    jSONObject.put("constructorCache", a(obj, "constructorCache"));
                } catch (Exception unused2) {
                }
            }
            return jSONObject;
        } catch (Throwable unused3) {
            return new JSONObject();
        }
    }

    private static boolean d() {
        JSONObject c2 = c();
        if (c2.optJSONArray("fieldCache") == null || c2.optJSONArray("fieldCache").length() <= 0) {
            if (c2.optJSONArray("methodCache") == null || c2.optJSONArray("methodCache").length() <= 0) {
                return c2.optJSONArray("constructorCache") != null && c2.optJSONArray("constructorCache").length() > 0;
            }
            return true;
        }
        return true;
    }

    private static JSONArray a(Object obj, String str) {
        JSONArray jSONArray = new JSONArray();
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            Set keySet = ((HashMap) declaredField.get(obj)).keySet();
            if (!keySet.isEmpty()) {
                Iterator it = keySet.iterator();
                while (it.hasNext()) {
                    String lowerCase = ((String) it.next()).toLowerCase();
                    if (!lowerCase.contains("jingdong") && !lowerCase.contains("jd.lib")) {
                        Set<String> A = com.jd.stat.security.d.a().A();
                        if (A != null && !A.isEmpty()) {
                            for (String str2 : A) {
                                if (!TextUtils.isEmpty(str2)) {
                                    if (lowerCase.matches(".*(" + str2 + ").*")) {
                                        jSONArray.put(lowerCase);
                                    }
                                }
                            }
                        }
                    } else {
                        jSONArray.put(lowerCase);
                    }
                }
                int min = Math.min(com.jd.stat.security.d.a().M(), jSONArray.length());
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < min; i2++) {
                    jSONArray2.put(jSONArray.optString(i2));
                }
                return jSONArray2;
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return jSONArray;
    }
}
