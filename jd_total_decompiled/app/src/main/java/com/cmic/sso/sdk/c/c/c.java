package com.cmic.sso.sdk.c.c;

import android.net.Network;
import com.cmic.sso.sdk.auth.AuthnHelper;
import com.cmic.sso.sdk.c.b.g;
import com.google.common.net.HttpHeaders;
import com.jdpay.net.http.HTTP;
import com.tencent.mapsdk.internal.l4;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class c {
    String a;
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private final Map<String, String> f1013c;
    private final String d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f1014e;

    /* renamed from: f  reason: collision with root package name */
    private final String f1015f;

    /* renamed from: g  reason: collision with root package name */
    private Network f1016g;

    /* renamed from: h  reason: collision with root package name */
    private long f1017h;

    /* renamed from: i  reason: collision with root package name */
    private final String f1018i;

    /* renamed from: j  reason: collision with root package name */
    private int f1019j;

    /* renamed from: k  reason: collision with root package name */
    private final g f1020k;

    private c(String str, Map<String, String> map, g gVar, String str2, String str3) {
        this.f1014e = false;
        this.b = str;
        this.f1020k = gVar;
        this.f1013c = map == null ? new HashMap<>() : map;
        this.a = gVar == null ? "" : gVar.b().toString();
        this.d = str2;
        this.f1015f = str3;
        this.f1018i = gVar != null ? gVar.a() : "";
        k();
    }

    private void k() {
        this.f1013c.put(l4.f16791e, AuthnHelper.SDK_VERSION);
        this.f1013c.put(HttpHeaders.CONTENT_TYPE, HTTP.CONTENT_TYPE_JSON);
        this.f1013c.put("CMCC-EncryptType", "STD");
        this.f1013c.put("traceId", this.f1015f);
        this.f1013c.put("appid", this.f1018i);
        this.f1013c.put("connection", "Keep-Alive");
    }

    public void a(String str, String str2) {
        this.f1013c.put(str, str2);
    }

    public boolean b() {
        return this.f1014e;
    }

    public Map<String, String> c() {
        return this.f1013c;
    }

    public String d() {
        return this.a;
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.f1015f;
    }

    public Network g() {
        return this.f1016g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long h() {
        return this.f1017h;
    }

    public boolean i() {
        int i2 = this.f1019j;
        this.f1019j = i2 + 1;
        return i2 < 2;
    }

    public g j() {
        return this.f1020k;
    }

    public String a() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.f1014e = z;
    }

    public void a(Network network) {
        this.f1016g = network;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        this.f1017h = j2;
    }

    public c(String str, g gVar, String str2, String str3) {
        this(str, null, gVar, str2, str3);
    }
}
