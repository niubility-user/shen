package com.huawei.hms.framework.network.grs.e;

import android.content.Context;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.g.d;
import com.huawei.hms.framework.network.grs.g.h;
import com.huawei.hms.framework.network.grs.h.e;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes12.dex */
public class a {

    /* renamed from: f */
    private static final String f1297f = "a";
    private final Map<String, Map<String, Map<String, String>>> a = new ConcurrentHashMap(16);
    private final Map<String, Long> b = new ConcurrentHashMap(16);

    /* renamed from: c */
    private final c f1298c;
    private final c d;

    /* renamed from: e */
    private final h f1299e;

    public a(c cVar, c cVar2, h hVar) {
        this.d = cVar2;
        this.f1298c = cVar;
        this.f1299e = hVar;
        hVar.a(this);
    }

    private void a(GrsBaseInfo grsBaseInfo, b bVar, Context context, String str) {
        Long l2 = this.b.get(grsBaseInfo.getGrsParasKey(true, true, context));
        if (e.a(l2)) {
            bVar.a(2);
            return;
        }
        if (e.a(l2, 300000L)) {
            this.f1299e.a(new com.huawei.hms.framework.network.grs.g.k.c(grsBaseInfo, context), null, str, this.d);
        }
        bVar.a(1);
    }

    private void a(GrsBaseInfo grsBaseInfo, String str, Context context) {
        if (e.a(this.b.get(str), 300000L)) {
            this.f1299e.a(new com.huawei.hms.framework.network.grs.g.k.c(grsBaseInfo, context), null, null, this.d);
        }
    }

    public c a() {
        return this.f1298c;
    }

    public Map<String, String> a(GrsBaseInfo grsBaseInfo, String str, b bVar, Context context) {
        Map<String, Map<String, String>> map = this.a.get(grsBaseInfo.getGrsParasKey(true, true, context));
        if (map == null || map.isEmpty()) {
            return new HashMap();
        }
        a(grsBaseInfo, bVar, context, str);
        return map.get(str);
    }

    public void a(GrsBaseInfo grsBaseInfo, Context context) {
        String grsParasKey = grsBaseInfo.getGrsParasKey(true, true, context);
        this.f1298c.b(grsParasKey + "time", "0");
        this.b.remove(grsParasKey + "time");
        this.a.remove(grsParasKey);
        this.f1299e.a(grsParasKey);
    }

    public void a(GrsBaseInfo grsBaseInfo, d dVar, Context context, com.huawei.hms.framework.network.grs.g.k.c cVar) {
        if (dVar.f() == 2) {
            Logger.w(f1297f, "update cache from server failed");
        } else if (cVar.d().size() != 0) {
            this.f1298c.b("geoipCountryCode", dVar.j());
            this.f1298c.b("geoipCountryCodetime", dVar.a());
        } else {
            String grsParasKey = grsBaseInfo.getGrsParasKey(true, true, context);
            if (dVar.m()) {
                this.a.put(grsParasKey, com.huawei.hms.framework.network.grs.a.a(this.f1298c.a(grsParasKey, "")));
            } else {
                this.f1298c.b(grsParasKey, dVar.j());
                this.a.put(grsParasKey, com.huawei.hms.framework.network.grs.a.a(dVar.j()));
            }
            if (!TextUtils.isEmpty(dVar.e())) {
                this.f1298c.b(grsParasKey + HttpHeaders.ETAG, dVar.e());
            }
            this.f1298c.b(grsParasKey + "time", dVar.a());
            this.b.put(grsParasKey, Long.valueOf(Long.parseLong(dVar.a())));
        }
    }

    public h b() {
        return this.f1299e;
    }

    public void b(GrsBaseInfo grsBaseInfo, Context context) {
        String grsParasKey = grsBaseInfo.getGrsParasKey(true, true, context);
        String a = this.f1298c.a(grsParasKey, "");
        String a2 = this.f1298c.a(grsParasKey + "time", "0");
        long j2 = 0;
        if (!TextUtils.isEmpty(a2) && a2.matches("\\d+")) {
            try {
                j2 = Long.parseLong(a2);
            } catch (NumberFormatException e2) {
                Logger.w(f1297f, "convert urlParamKey from String to Long catch NumberFormatException.", e2);
            }
        }
        this.a.put(grsParasKey, com.huawei.hms.framework.network.grs.a.a(a));
        this.b.put(grsParasKey, Long.valueOf(j2));
        a(grsBaseInfo, grsParasKey, context);
    }

    public c c() {
        return this.d;
    }
}
