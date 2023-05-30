package com.jd.lib.flexcube.pool;

import androidx.annotation.NonNull;
import com.jd.lib.flexcube.widgets.a.c;
import com.jd.lib.flexcube.widgets.a.d;
import com.jd.lib.flexcube.widgets.a.e;
import com.jd.lib.flexcube.widgets.a.f;
import com.jd.lib.flexcube.widgets.a.g;
import com.jd.lib.flexcube.widgets.a.h;
import com.jd.lib.flexcube.widgets.a.i;
import com.jd.lib.flexcube.widgets.a.j;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes15.dex */
public class a {
    private static final a b = new a();
    private Map<String, com.jd.lib.flexcube.iwidget.a.a> a = new HashMap();

    private a() {
        c();
    }

    public static a b() {
        return b;
    }

    private void c() {
        d("100000", new j());
        d("200000", new c());
        d("300000", new e());
        d("400000", new i());
        d("800000", new h());
        d("600112", new f());
        d("10086", new g());
        d("800001", new com.jd.lib.flexcube.widgets.a.a());
        d("600001", new d());
        d("600012", new com.jd.lib.flexcube.widgets.a.b());
        d("900001", new com.jd.lib.flexcube.owidgets.a.b());
        d("custom_close", new com.jd.lib.flexcube.owidgets.a.a());
        d("custom_hotzone", new com.jd.lib.flexcube.owidgets.a.c());
    }

    public com.jd.lib.flexcube.iwidget.a.a a(String str) {
        return this.a.get(str);
    }

    public void d(@NonNull String str, @NonNull com.jd.lib.flexcube.iwidget.a.a aVar) {
        if (this.a.containsKey(str)) {
            return;
        }
        this.a.put(str, aVar);
    }
}
