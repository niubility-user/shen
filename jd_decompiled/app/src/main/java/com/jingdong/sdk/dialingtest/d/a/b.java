package com.jingdong.sdk.dialingtest.d.a;

import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class b {
    private String a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    public int f14761c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public int f14762e;

    /* renamed from: f  reason: collision with root package name */
    private int f14763f;

    /* renamed from: g  reason: collision with root package name */
    public int f14764g;

    /* renamed from: h  reason: collision with root package name */
    public List<a> f14765h;

    /* renamed from: i  reason: collision with root package name */
    public int f14766i;

    /* renamed from: j  reason: collision with root package name */
    public boolean f14767j = false;

    /* loaded from: classes7.dex */
    public static class a {
        public String a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        String f14768c;
        int d;

        /* renamed from: e  reason: collision with root package name */
        int f14769e;

        /* renamed from: f  reason: collision with root package name */
        public Map<String, String> f14770f;

        /* renamed from: g  reason: collision with root package name */
        int f14771g;

        a() {
        }

        public boolean a() {
            return this.f14771g == 1;
        }

        boolean b(JSONObject jSONObject) {
            if (jSONObject == null) {
                return false;
            }
            String optString = jSONObject.optString("host", "");
            this.a = optString;
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            this.b = jSONObject.optString("method", DataCompassUtils.MODULE_TYPE_HEAD);
            this.f14768c = jSONObject.optString("body", "");
            this.f14769e = jSONObject.optInt("isSetHeaders", 0);
            this.d = jSONObject.optInt("isReportBody", 0);
            this.f14771g = jSONObject.optInt("isReportCertificate", 0);
            JSONObject optJSONObject = jSONObject.optJSONObject("headers");
            if (optJSONObject != null) {
                this.f14770f = new HashMap();
                Iterator<String> keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    this.f14770f.put(obj, optJSONObject.optString(obj));
                }
                return true;
            }
            return true;
        }

        public boolean c() {
            return this.d == 1;
        }

        public boolean d() {
            return this.f14769e == 1;
        }

        public String toString() {
            return "HttpHostModel{host='" + this.a + "', method='" + this.b + "', body='" + this.f14768c + "', isReportBody=" + this.d + ", isSetHeaders=" + this.f14769e + ", headers=" + this.f14770f + ", isReportCertificate=" + this.f14771g + '}';
        }
    }

    public boolean a() {
        return "debug".equals(this.a);
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.a = jSONObject.optString("mode", "common");
            this.b = jSONObject.optInt("expireTime", RemoteMessageConst.DEFAULT_TTL);
            this.f14761c = jSONObject.optInt("delay", 5);
            int optInt = jSONObject.optInt("repeat", 1);
            this.f14762e = optInt;
            this.f14766i = optInt;
            this.d = jSONObject.optInt("interval", 5);
            this.f14763f = jSONObject.optInt("ldnsSwitch", 0);
            this.f14764g = jSONObject.optInt("timeout", 10);
            JSONArray optJSONArray = jSONObject.optJSONArray("hosts");
            if (optJSONArray != null && optJSONArray.length() != 0) {
                this.f14765h = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                    a aVar = new a();
                    if (aVar.b(jSONObject2)) {
                        this.f14765h.add(aVar);
                    }
                }
                return this.f14765h.size() != 0;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean c() {
        if (!com.jingdong.sdk.dialingtest.c.e.b.d("dialing_last_http_test")) {
            com.jingdong.sdk.dialingtest.c.e.b.e("dialing_last_http_test", System.currentTimeMillis());
            return true;
        }
        long a2 = com.jingdong.sdk.dialingtest.c.e.b.a("dialing_last_http_test", 0L);
        if (a2 == 0) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - a2 <= this.b * 1000) {
            return false;
        }
        com.jingdong.sdk.dialingtest.c.e.b.e("dialing_last_http_test", currentTimeMillis);
        return true;
    }

    public boolean d() {
        return this.f14763f == 1;
    }

    public String toString() {
        return "HttpModel{mode='" + this.a + "', expireTime=" + this.b + ", delay=" + this.f14761c + ", interval=" + this.d + ", repeat=" + this.f14762e + ", ldnsSwitch=" + this.f14763f + ", timeout=" + this.f14764g + ", hosts=" + this.f14765h + '}';
    }
}
