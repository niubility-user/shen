package com.jingdong.app.mall.home.floor.ctrl.t;

import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class m {
    private static final AtomicBoolean a = new AtomicBoolean(false);
    private static final AtomicBoolean b = new AtomicBoolean(false);

    static {
        new AtomicBoolean(false);
    }

    public static void a(HomeWebFloorViewEntity homeWebFloorViewEntity) {
        if (homeWebFloorViewEntity != null) {
            b.set(true);
            com.jingdong.app.mall.home.r.c.a.y("Home_AutoXVIEWLoad", "", homeWebFloorViewEntity.srvJson);
            com.jingdong.app.mall.home.o.a.f.r0(m.class, "sendExpo: Home_AutoXVIEWLoad");
        }
    }

    public static void b(JSONArray jSONArray) {
        if (jSONArray != null) {
            AtomicBoolean atomicBoolean = a;
            if (atomicBoolean.get()) {
                return;
            }
            atomicBoolean.set(true);
            String jSONArray2 = jSONArray.toString();
            if (TextUtils.isEmpty(jSONArray2) || jSONArray2.length() <= 10) {
                return;
            }
            com.jingdong.app.mall.home.r.c.a.y("Home_AutoXVIEWApply", "", jSONArray2);
            com.jingdong.app.mall.home.o.a.f.r0(m.class, "sendExpo: Home_AutoXVIEWApply: " + jSONArray2);
        }
    }

    public static void c(HomeWebFloorViewEntity homeWebFloorViewEntity, String str) {
        d(homeWebFloorViewEntity, str, null);
    }

    public static void d(HomeWebFloorViewEntity homeWebFloorViewEntity, String str, String str2) {
        if (b.get()) {
            f(homeWebFloorViewEntity, str, str2);
        }
    }

    public static void e(HomeWebFloorViewEntity homeWebFloorViewEntity, String str) {
        f(homeWebFloorViewEntity, str, null);
    }

    public static void f(HomeWebFloorViewEntity homeWebFloorViewEntity, String str, String str2) {
        if (homeWebFloorViewEntity == null || !homeWebFloorViewEntity.canBlock.get()) {
            return;
        }
        homeWebFloorViewEntity.canBlock.set(false);
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(homeWebFloorViewEntity.srvJson);
        c2.put("reason", str);
        if (TextUtils.isEmpty(str2)) {
            str2 = "-100";
        }
        c2.put("reasontype", str2);
        com.jingdong.app.mall.home.r.c.a.y("Home_AutoXVIEWBlock", "", c2.toString());
        com.jingdong.app.mall.home.o.a.f.r0(m.class, "sendExpo: Home_AutoXVIEWBlock _ " + str);
    }
}
