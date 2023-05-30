package com.jingdong.app.mall.bundle.debugFinder;

import android.content.Context;

/* loaded from: classes19.dex */
public class DebugFinderInjector {
    public static void findHere(Context context) {
        initDebugFinder(context);
    }

    private static void initDebugFinder(Context context) {
        try {
            Class<?> cls = Class.forName("com.jingdong.app.mall.bundle.debugFinder.DebugFinder");
            cls.getMethod("findHere", Context.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(new Object(), new Object[0]), context);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
