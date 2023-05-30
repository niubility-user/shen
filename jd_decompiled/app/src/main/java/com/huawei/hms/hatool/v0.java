package com.huawei.hms.hatool;

import android.content.Context;
import android.util.Pair;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public class v0 implements g {
    private Context a = q0.i();
    private String b;

    /* renamed from: c */
    private String f1418c;
    private String d;

    public v0(String str, String str2, String str3) {
        this.b = str;
        this.f1418c = str2;
        this.d = str3;
    }

    private void a(String str, List<b1> list) {
        Pair<String, String> a = n1.a(str);
        new u(list, (String) a.first, (String) a.second, this.d).a();
    }

    @Override // java.lang.Runnable
    public void run() {
        Map<String, List<b1>> a;
        v.c("hmsSdk", "eventReportTask is running");
        boolean a2 = c0.a(this.a);
        if (a2) {
            v.c("hmsSdk", "workKey is refresh,begin report all data");
            this.f1418c = "alltype";
        }
        try {
            try {
                a = c1.a(this.a, this.b, this.f1418c);
            } catch (IllegalArgumentException e2) {
                v.e("hmsSdk", "readEventRecords handData IllegalArgumentException:" + e2.getMessage());
                if ("alltype".equals(this.f1418c)) {
                    d.a(this.a, "stat_v2_1", new String[0]);
                    d.a(this.a, "cached_v2_1", new String[0]);
                } else {
                    String a3 = n1.a(this.b, this.f1418c);
                    d.a(this.a, "stat_v2_1", a3);
                    d.a(this.a, "cached_v2_1", a3);
                }
            } catch (Exception e3) {
                v.e("hmsSdk", "readEventRecords handData Exception:" + e3.getMessage());
                if ("alltype".equals(this.f1418c)) {
                    d.a(this.a, "stat_v2_1", new String[0]);
                    d.a(this.a, "cached_v2_1", new String[0]);
                } else {
                    String a4 = n1.a(this.b, this.f1418c);
                    d.a(this.a, "stat_v2_1", a4);
                    d.a(this.a, "cached_v2_1", a4);
                }
            }
            if (a.size() == 0) {
                v.b("hmsSdk", "no have events to report: tag:%s : type:%s", this.b, this.f1418c);
                if ("alltype".equals(this.f1418c)) {
                    d.a(this.a, "stat_v2_1", new String[0]);
                    d.a(this.a, "cached_v2_1", new String[0]);
                    return;
                }
                String a5 = n1.a(this.b, this.f1418c);
                d.a(this.a, "stat_v2_1", a5);
                d.a(this.a, "cached_v2_1", a5);
                return;
            }
            for (Map.Entry<String, List<b1>> entry : a.entrySet()) {
                a(entry.getKey(), entry.getValue());
            }
            if ("alltype".equals(this.f1418c)) {
                d.a(this.a, "stat_v2_1", new String[0]);
                d.a(this.a, "cached_v2_1", new String[0]);
            } else {
                String a6 = n1.a(this.b, this.f1418c);
                d.a(this.a, "stat_v2_1", a6);
                d.a(this.a, "cached_v2_1", a6);
            }
            if (a2) {
                v.c("hmsSdk", "refresh local key");
                o0.d().b();
            }
        } catch (Throwable th) {
            if ("alltype".equals(this.f1418c)) {
                d.a(this.a, "stat_v2_1", new String[0]);
                d.a(this.a, "cached_v2_1", new String[0]);
            } else {
                String a7 = n1.a(this.b, this.f1418c);
                d.a(this.a, "stat_v2_1", a7);
                d.a(this.a, "cached_v2_1", a7);
            }
            throw th;
        }
    }
}
