package com.jingdong.app.mall.n;

import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public class c {
    public static boolean a;

    public static void a() {
        EventBus.getDefault().post(new b("FUNC_finish"));
    }

    public static void b(boolean z) {
        b bVar = new b("FUNC_removeAllRecords");
        bVar.b.putBoolean("PARAM_removeAllRecords", z);
        EventBus.getDefault().post(bVar);
    }

    public static void c() {
        EventBus.getDefault().post(new b("FUNC_toHomeActivity"));
    }

    public static void d() {
        EventBus.getDefault().post(new b("PARAM_toPersonal"));
    }

    public static void e() {
        EventBus.getDefault().post(new b("FUNC_toShoppingCart"));
    }
}
