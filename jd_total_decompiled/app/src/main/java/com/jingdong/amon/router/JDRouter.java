package com.jingdong.amon.router;

import android.app.Application;
import android.content.Context;
import com.jingdong.amon.router.module.DynamicLetter;
import com.jingdong.amon.router.module.Letter;
import com.jingdong.amon.router.module.MethodLetter;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDRouter {
    public static void addDynamicLetterInfo(String str, Class cls) {
        com.jingdong.amon.router.a.b.a(str, cls);
    }

    public static void addDynamicRouterInfo(String str, DynamicLetter dynamicLetter) {
        com.jingdong.amon.router.a.b.a(str, dynamicLetter);
    }

    public static Letter build(Context context, String str) {
        return a.a().a(context, str);
    }

    public static MethodLetter buildMethod(String str, String str2) {
        return a.a().a(str, str2);
    }

    public static void clearAllDynamicRouter() {
        com.jingdong.amon.router.a.b.a();
    }

    public static void clearDynamicRouterInfo(String str) {
        com.jingdong.amon.router.a.b.b(str);
    }

    public static DynamicLetter getDynamicLetterInfo(String str) {
        return com.jingdong.amon.router.a.b.a(str);
    }

    public static Map<String, DynamicLetter> getDynamicRouterMap() {
        return com.jingdong.amon.router.a.b.b();
    }

    public static boolean getGlobalUseIntercept() {
        return com.jingdong.amon.router.a.a.a();
    }

    public static <T> T getRegisteredService(Class<T> cls) {
        return (T) a.a().a(cls);
    }

    public static Class getRoutelass(String str) {
        return a.a().b(str);
    }

    public static <T> T getService(Class<T> cls, String str) {
        return (T) a.a().a((Class) cls, str);
    }

    public static Object getService(String str) {
        return a.a().a(str);
    }

    public static void init(Application application) {
        a.a(application, "", "");
    }

    public static void init(Application application, String str, String str2) {
        a.a(application, str, str2);
    }

    public static Object navigation(Letter letter) {
        return a.a().a(letter);
    }

    public static Object navigation(MethodLetter methodLetter) {
        return a.a().a(methodLetter);
    }

    public static <T> void registerService(Class<T> cls, T t) {
        a.a().a((Class<Class<T>>) cls, (Class<T>) t);
    }

    public static void setGlobalUseIntercept(boolean z) {
        com.jingdong.amon.router.a.a.a(z);
    }
}
