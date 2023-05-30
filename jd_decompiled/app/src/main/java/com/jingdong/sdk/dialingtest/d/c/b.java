package com.jingdong.sdk.dialingtest.d.c;

import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class b {
    private String a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    public int f14799c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public int f14800e;

    /* renamed from: f  reason: collision with root package name */
    private int f14801f;

    /* renamed from: g  reason: collision with root package name */
    public int f14802g;

    /* renamed from: h  reason: collision with root package name */
    public int f14803h;

    /* renamed from: i  reason: collision with root package name */
    public int f14804i;

    /* renamed from: j  reason: collision with root package name */
    public List<a> f14805j;

    /* renamed from: k  reason: collision with root package name */
    public int f14806k;

    /* loaded from: classes7.dex */
    public static class a {
        public String a;
        public String b;

        public boolean a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return false;
            }
            String optString = jSONObject.optString("type", "domain");
            this.a = optString;
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            this.b = jSONObject.optString("host", "");
            return !TextUtils.isEmpty(r4);
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
            int optInt = jSONObject.optInt("expireTime", RemoteMessageConst.DEFAULT_TTL);
            this.b = optInt;
            if (optInt <= 0) {
                this.b = RemoteMessageConst.DEFAULT_TTL;
            }
            int optInt2 = jSONObject.optInt("delay", 5);
            this.f14799c = optInt2;
            if (optInt2 < 0) {
                this.f14799c = 5;
            }
            int optInt3 = jSONObject.optInt("repeat", 1);
            this.d = optInt3;
            if (this.f14799c < 0 || optInt3 > 10) {
                this.d = 1;
            }
            this.f14806k = this.d;
            int optInt4 = jSONObject.optInt("interval", 5);
            this.f14800e = optInt4;
            if (optInt4 < 0) {
                this.f14800e = 5;
            }
            int optInt5 = jSONObject.optInt("ldnsSwitch", 0);
            this.f14801f = optInt5;
            if (optInt5 != 0 && optInt5 != 1) {
                this.f14801f = 0;
            }
            int optInt6 = jSONObject.optInt("maxJumpNum", 30);
            this.f14802g = optInt6;
            if (optInt6 < 1 || optInt6 > 80) {
                this.f14802g = 30;
            }
            int optInt7 = jSONObject.optInt("packetNum", 4);
            this.f14803h = optInt7;
            if (optInt7 < 1 || optInt7 > 20) {
                this.f14803h = 4;
            }
            int optInt8 = jSONObject.optInt("timeout", 3);
            this.f14804i = optInt8;
            if (optInt8 < 1 || optInt8 > 10) {
                this.f14804i = 3;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("hosts");
            if (optJSONArray != null && optJSONArray.length() != 0) {
                this.f14805j = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                    a aVar = new a();
                    if (aVar.a(jSONObject2)) {
                        this.f14805j.add(aVar);
                    }
                }
                return this.f14805j.size() != 0;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean c() {
        if (!com.jingdong.sdk.dialingtest.c.e.b.d("dialing_last_traceroute_test")) {
            com.jingdong.sdk.dialingtest.c.e.b.e("dialing_last_traceroute_test", System.currentTimeMillis());
            return true;
        }
        long a2 = com.jingdong.sdk.dialingtest.c.e.b.a("dialing_last_traceroute_test", 0L);
        if (a2 == 0) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - a2 <= this.b * 1000) {
            return false;
        }
        com.jingdong.sdk.dialingtest.c.e.b.e("dialing_last_traceroute_test", currentTimeMillis);
        return true;
    }

    public boolean d() {
        return this.f14801f == 1;
    }
}
