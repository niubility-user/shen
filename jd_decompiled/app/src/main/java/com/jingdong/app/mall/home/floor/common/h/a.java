package com.jingdong.app.mall.home.floor.common.h;

import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.widget.HomeRecycleView;
import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes4.dex */
public class a {
    private static boolean a;

    public static com.jingdong.app.mall.home.r.c.b a(View view, com.jingdong.app.mall.home.r.c.b bVar) {
        b d;
        return (!a || view == null || (d = d(view)) == null) ? bVar : d.a(bVar);
    }

    public static String b(View view, String str) {
        b d;
        return (!a || view == null || (d = d(view)) == null) ? str : d.b(str);
    }

    public static void c(View view, JumpEntity jumpEntity) {
        b d;
        if (!a || view == null || (d = d(view)) == null) {
            return;
        }
        d.c(jumpEntity);
    }

    private static b d(View view) {
        try {
            if (view instanceof c) {
                return ((c) view).getClickInfo();
            }
            if (view != null && !(view instanceof HomeRecycleView)) {
                return d((View) view.getParent());
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void e(JDJSONObject jDJSONObject) {
        a = jDJSONObject.optInt("ccSwitch", 1) != 0;
    }

    public static void f(String str, String str2) {
        if (a) {
            com.jingdong.app.mall.home.r.c.a.y(str, "", str2);
        }
    }
}
