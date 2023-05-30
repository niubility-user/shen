package com.jingdong.service;

import android.text.TextUtils;
import java.lang.reflect.Constructor;

/* loaded from: classes10.dex */
public class ServiceInitUtils {
    private static final String TAG = "ServiceInitUtils";

    private static Object getInstance(String str) {
        try {
            return getclass(str).newInstance();
        } catch (Exception unused) {
            return null;
        }
    }

    private static Class getclass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static BaseService initAdvice(PointServiceEnum pointServiceEnum) {
        String serviceByPointNmae = ServiceBind.getServiceByPointNmae(pointServiceEnum);
        if (!TextUtils.isEmpty(serviceByPointNmae)) {
            Object serviceInitUtils = getInstance(serviceByPointNmae);
            if (serviceInitUtils instanceof BaseService) {
                return (BaseService) serviceInitUtils;
            }
        }
        ServiceUtls.asset(pointServiceEnum);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Object getInstance(String str, Class cls, Object obj) {
        Class[] clsArr = {cls};
        Object[] objArr = {obj};
        try {
            Constructor declaredConstructor = getclass(str).getDeclaredConstructor(clsArr);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static BaseService initAdvice(PointServiceEnum pointServiceEnum, Point point2) {
        String serviceByPointNmae = ServiceBind.getServiceByPointNmae(pointServiceEnum);
        if (!TextUtils.isEmpty(serviceByPointNmae)) {
            Object serviceInitUtils = getInstance(serviceByPointNmae, Point.class, point2);
            if (serviceInitUtils instanceof BaseService) {
                return (BaseService) serviceInitUtils;
            }
        }
        ServiceUtls.asset(pointServiceEnum);
        return null;
    }
}
