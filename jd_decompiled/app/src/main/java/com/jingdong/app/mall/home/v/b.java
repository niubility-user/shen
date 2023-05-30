package com.jingdong.app.mall.home.v;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;

/* loaded from: classes4.dex */
public class b {
    private static int a;

    /* loaded from: classes4.dex */
    public interface a {
        void a();

        void b();

        void c();
    }

    public static int a() {
        return a;
    }

    public static void b(JDJSONObject jDJSONObject, boolean z) {
        a = jDJSONObject.optInt("appType", 0);
        com.jingdong.app.mall.home.v.d.a.j(jDJSONObject, z);
    }

    public static void c(BaseActivity baseActivity, a aVar) {
        com.jingdong.app.mall.home.state.dark.a.j(aVar);
        com.jingdong.app.mall.home.state.old.a.j(aVar);
        com.jingdong.app.mall.home.v.d.a.i(baseActivity, aVar);
    }
}
