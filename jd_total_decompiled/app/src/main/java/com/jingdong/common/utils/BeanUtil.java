package com.jingdong.common.utils;

import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes6.dex */
public class BeanUtil {
    public static Object getValue(Object obj, String str) {
        Class<?> cls = obj.getClass();
        boolean z = cls.getClassLoader() != null;
        for (Method method : z ? cls.getMethods() : cls.getDeclaredMethods()) {
            String name = method.getName();
            if ((IMantoServerRequester.GET + str).equalsIgnoreCase(name) || str.equalsIgnoreCase(name)) {
                try {
                    return method.invoke(obj, null);
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
        for (Field field : z ? cls.getFields() : cls.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(str)) {
                try {
                    return field.get(obj);
                } catch (Exception e3) {
                    throw new RuntimeException(e3);
                }
            }
        }
        return null;
    }
}
