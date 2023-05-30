package com.jingdong.app.mall.log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.jd.dynamic.DYConstants;

/* loaded from: classes4.dex */
public class c {
    private String a(Activity activity) {
        return activity == null ? "" : activity.toString().replace("com.jingdong.app.mall.", "").replace("com.jd.lib.", "");
    }

    public void b(Activity activity) {
        f d = e.b().d();
        if (d != null) {
            StringBuilder sb = new StringBuilder();
            Intent intent = activity.getIntent();
            if (intent != null && intent.getExtras() != null) {
                Bundle extras = intent.getExtras();
                for (String str : extras.keySet()) {
                    Object obj = extras.get(str);
                    sb.append(str);
                    sb.append("=>");
                    sb.append(obj);
                    sb.append(DYConstants.DY_REGEX_COMMA);
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(a(activity));
            sb2.append("[create]");
            sb2.append(sb.length() == 0 ? "" : "( " + ((Object) sb) + " )");
            d.c(sb2.toString());
        }
    }

    public void c(Activity activity) {
        f d = e.b().d();
        if (d != null) {
            d.c(a(activity) + "[destroy]");
        }
    }

    public void d(Activity activity) {
        f d = e.b().d();
        if (d != null) {
            d.c(a(activity) + "[pause]");
        }
    }

    public void e(Activity activity) {
        f d = e.b().d();
        if (d != null) {
            d.c(a(activity) + "[resume]");
        }
    }

    public void f(Activity activity) {
        f d = e.b().d();
        if (d != null) {
            d.c(a(activity) + "[saveState]");
        }
    }

    public void g(Activity activity) {
        f d = e.b().d();
        if (d != null) {
            d.c(a(activity) + "[start]");
        }
    }

    public void h(Activity activity) {
        f d = e.b().d();
        if (d != null) {
            d.c(a(activity) + "[stop]");
        }
    }
}
