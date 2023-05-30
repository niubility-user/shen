package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import java.util.UUID;

/* loaded from: classes12.dex */
public class j {
    private static j b;
    private Context a;

    /* loaded from: classes12.dex */
    private static class a extends e0 {
        String a;
        String b;

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // com.huawei.hms.hatool.e0
        public String a() {
            return z.d(this.a, this.b);
        }

        @Override // com.huawei.hms.hatool.e0
        public String a(String str) {
            return com.huawei.secure.android.common.encrypt.b.b.b(str);
        }

        @Override // com.huawei.hms.hatool.e0
        public String b() {
            return z.g(this.a, this.b);
        }

        @Override // com.huawei.hms.hatool.e0
        public String c() {
            return z.j(this.a, this.b);
        }

        @Override // com.huawei.hms.hatool.e0
        public int d() {
            return (z.k(this.a, this.b) ? 4 : 0) | 0 | (z.e(this.a, this.b) ? 2 : 0) | (z.h(this.a, this.b) ? 1 : 0);
        }
    }

    public static j a() {
        j jVar;
        synchronized (j.class) {
            if (b == null) {
                b = new j();
            }
            jVar = b;
        }
        return jVar;
    }

    public String a(String str, String str2) {
        return i0.a(this.a, str, str2);
    }

    public String a(boolean z) {
        if (z) {
            String e2 = q0.e();
            if (TextUtils.isEmpty(e2)) {
                e2 = d.a(this.a, "global_v2", "uuid", "");
                if (TextUtils.isEmpty(e2)) {
                    e2 = UUID.randomUUID().toString().replace("-", "");
                    d.b(this.a, "global_v2", "uuid", e2);
                }
                q0.h(e2);
            }
            return e2;
        }
        return "";
    }

    public void a(Context context) {
        if (this.a == null) {
            this.a = context;
        }
    }

    public String b(String str, String str2) {
        return i0.b(this.a, str, str2);
    }

    public i c(String str, String str2) {
        return new a(str, str2).a(this.a);
    }

    public String d(String str, String str2) {
        return f1.b(str, str2);
    }

    public Pair<String, String> e(String str, String str2) {
        if (z.f(str, str2)) {
            String p = s.c().b().p();
            String q = s.c().b().q();
            if (TextUtils.isEmpty(p) || TextUtils.isEmpty(q)) {
                Pair<String, String> e2 = x0.e(this.a);
                s.c().b().k((String) e2.first);
                s.c().b().l((String) e2.second);
                return e2;
            }
            return new Pair<>(p, q);
        }
        return new Pair<>("", "");
    }

    public String f(String str, String str2) {
        return f1.a(str, str2);
    }
}
