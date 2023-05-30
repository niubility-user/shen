package com.jingdong.app.mall.home.q;

import android.text.TextUtils;
import com.jingdong.app.mall.home.b;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a {
    private static final CopyOnWriteArrayList<String> d = new CopyOnWriteArrayList<>();
    private final String a;
    private String b;

    /* renamed from: c */
    private final boolean f10590c;

    public a(String str, String str2) {
        this(str, false, str2);
    }

    public static void a() {
        try {
            d.clear();
        } catch (Exception e2) {
            f.o(e2.getMessage());
        }
    }

    private void c() {
        if (k.v()) {
            f.r0(this, this.a + " url= " + this.b);
        }
        if (TextUtils.isEmpty(this.b)) {
            return;
        }
        if (this.f10590c && d.contains(this.b)) {
            return;
        }
        if (this.f10590c) {
            d.add(this.b);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("expoLog", this.b);
            jSONObject.put("fQueryStamp", b.f8602m + "");
            f.D0("dataStreamExpo", jSONObject, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void b() {
        try {
            c();
        } catch (Exception e2) {
            f.o(e2.getMessage());
        }
    }

    public a(String str, boolean z, String str2) {
        this.a = str;
        this.f10590c = z;
        this.b = str2;
    }
}
