package com.jingdong.sdk.dialingtest.c.c;

import android.text.TextUtils;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class c {
    public int a;
    public Map<String, List<String>> b;

    /* renamed from: c  reason: collision with root package name */
    public String f14729c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f14730e;

    /* renamed from: f  reason: collision with root package name */
    public String f14731f;

    /* renamed from: g  reason: collision with root package name */
    public String f14732g = "0";

    /* renamed from: h  reason: collision with root package name */
    public List<X509Certificate> f14733h;

    /* renamed from: i  reason: collision with root package name */
    public String f14734i;

    /* renamed from: j  reason: collision with root package name */
    public String f14735j;

    /* renamed from: k  reason: collision with root package name */
    public String f14736k;

    /* renamed from: l  reason: collision with root package name */
    public String f14737l;

    public JSONObject a() {
        if (TextUtils.isEmpty(this.d)) {
            return null;
        }
        try {
            return new JSONObject(this.d);
        } catch (Exception e2) {
            com.jingdong.sdk.dialingtest.c.e.a.a("DtHttpResponse", e2.getMessage());
            return null;
        }
    }

    public String b() {
        return this.d;
    }
}
