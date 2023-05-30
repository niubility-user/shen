package com.jingdong.jdsdk.utils;

import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes14.dex */
public class c {
    public static Object a(String str) {
        Class<?> cls;
        Object obj = null;
        try {
            cls = Class.forName(str);
        } catch (ClassNotFoundException e2) {
            OKLog.e("ReflectionUtil", e2);
            cls = null;
        }
        if (cls != null) {
            try {
                obj = cls.newInstance();
            } catch (IllegalAccessException e3) {
                OKLog.e("ReflectionUtil", e3);
            } catch (InstantiationException e4) {
                OKLog.e("ReflectionUtil", e4);
            }
            if (obj != null) {
                return obj;
            }
            throw new NullPointerException("ReflectionUtil::newInstance Cannot make an instance of " + str);
        }
        throw new NullPointerException("ReflectionUtil::newInstance Cannot load class " + str);
    }
}
