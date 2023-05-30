package com.jingdong.jdma.bean;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.jdma.common.utils.c;
import com.jingdong.jdma.common.utils.h;
import com.jingdong.jdma.common.utils.i;
import com.jingdong.jdma.common.utils.k;
import com.jingdong.jdma.common.utils.m;
import com.jingdong.jdma.h.d;
import com.jingdong.jdma.minterface.MaInitCommonInfo;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class a {

    /* renamed from: j */
    private static a f12628j;
    private String a = "";
    private String b = "android";

    /* renamed from: c */
    private String f12629c;
    private String d;

    /* renamed from: e */
    private String f12630e;

    /* renamed from: f */
    private String f12631f;

    /* renamed from: g */
    private String f12632g;

    /* renamed from: h */
    private Context f12633h;

    /* renamed from: i */
    private MaInitCommonInfo f12634i;

    private a() {
    }

    public static a b() {
        if (f12628j == null) {
            synchronized (a.class) {
                if (f12628j == null) {
                    f12628j = new a();
                }
            }
        }
        return f12628j;
    }

    public void a(Context context, MaInitCommonInfo maInitCommonInfo) {
        Context applicationContext = context.getApplicationContext();
        this.f12633h = applicationContext;
        if (applicationContext == null) {
            this.f12633h = context;
        }
        this.f12634i = maInitCommonInfo;
        this.f12629c = maInitCommonInfo.site_id;
        this.f12630e = maInitCommonInfo.channel;
        this.f12631f = maInitCommonInfo.proj_id;
        this.d = maInitCommonInfo.app_device;
        this.f12632g = maInitCommonInfo.installationId;
        this.a = maInitCommonInfo.getGuid();
    }

    public String c() {
        return this.f12629c;
    }

    public String d() {
        return this.a;
    }

    public JSONObject a() {
        String guid = this.f12634i.getGuid();
        this.a = guid;
        if (!TextUtils.isEmpty(guid)) {
            d.e().k(this.a);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            String str = "" + System.currentTimeMillis();
            String a = com.jingdong.jdma.a.a.d.a(str + "5YT%aC89$22OI@pQ");
            Context context = this.f12633h;
            if (context != null) {
                String b = h.b(context);
                c.f12682l = b;
                jSONObject.put("net", b);
            }
            jSONObject.put("imi", "");
            jSONObject.put("uid", m.a(this.a));
            jSONObject.put("osp", m.a(this.b));
            jSONObject.put("jvr", "6.3.38");
            jSONObject.put("ver", "6.3.17");
            jSONObject.put("std", m.a(this.f12629c));
            jSONObject.put("clt", m.a("app"));
            jSONObject.put("report_ts", str);
            jSONObject.put("scr", m.a(k.b(this.f12633h)));
            jSONObject.put("token", a);
            jSONObject.put("app_device", m.a(this.d));
            jSONObject.put("chf", m.a(this.f12630e));
            jSONObject.put("proj_id", m.a(this.f12631f));
            jSONObject.put("aid", m.a(k.a(this.f12633h)));
            jSONObject.put("oaid", m.a(c.f12683m));
            jSONObject.put("installationId", m.a(this.f12632g));
            jSONObject.put("mct", m.a(k.a()));
            jSONObject.put("dvc", m.a(k.c()));
            jSONObject.put("osv", m.a(i.b()));
            jSONObject.put("machineType", m.a(k.b()));
            jSONObject.put("osv_int", i.a() + "");
            jSONObject.put("ims", "");
            jSONObject.put("imsi", "");
            ConcurrentHashMap<String, Object> concurrentHashMap = c.b;
            if (concurrentHashMap.size() != 0) {
                JSONObject jSONObject2 = new JSONObject();
                for (Map.Entry<String, Object> entry : concurrentHashMap.entrySet()) {
                    if (!TextUtils.isEmpty(entry.getKey()) && entry.getValue() != null) {
                        String key = entry.getKey();
                        if (entry.getValue() instanceof String) {
                            jSONObject2.put(key, m.a((String) entry.getValue()));
                        } else {
                            jSONObject2.put(key, entry.getValue());
                        }
                    }
                }
                jSONObject.put("std_param", jSONObject2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void a(String str) {
        this.f12629c = str;
    }
}
