package com.meizu.cloud.pushsdk.f.f;

import android.content.Context;
import android.os.Build;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.meizu.cloud.pushsdk.f.g.e;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class c {
    private static final String d = "c";
    private final HashMap<String, String> a;
    private final HashMap<String, Object> b;

    /* renamed from: c  reason: collision with root package name */
    private final HashMap<String, String> f15943c;

    /* loaded from: classes14.dex */
    public static class b {
        private Context a = null;

        public b b(Context context) {
            this.a = context;
            return this;
        }

        public c c() {
            return new c(this);
        }
    }

    private c(b bVar) {
        this.a = new HashMap<>();
        this.b = new HashMap<>();
        this.f15943c = new HashMap<>();
        i();
        j();
        g();
        h();
        if (bVar.a != null) {
            e(bVar.a);
        }
        com.meizu.cloud.pushsdk.f.g.c.g(d, "Subject created successfully.", new Object[0]);
    }

    private void c(String str, String str2) {
        if (str == null || str2 == null || str.isEmpty() || str2.isEmpty()) {
            return;
        }
        this.f15943c.put(str, str2);
    }

    private void g() {
        c("dm", BaseInfo.getDeviceModel());
    }

    private void h() {
        c("df", BaseInfo.getDeviceManufacture());
    }

    private void i() {
        c("ot", "android-" + Build.VERSION.RELEASE);
    }

    private void j() {
        c("ov", BaseInfo.getOSName());
    }

    public Map<String, String> a() {
        return this.a;
    }

    public void b(Context context) {
        String d2 = e.d(context);
        if (d2 != null) {
            c("ca", d2);
        }
    }

    public Map<String, Object> d() {
        return this.b;
    }

    public void e(Context context) {
        b(context);
    }

    public Map<String, String> f() {
        return this.f15943c;
    }
}
