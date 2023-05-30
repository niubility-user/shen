package com.tencent.mapsdk.internal;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.map.tools.net.NetUtil;
import com.tencent.map.tools.net.exception.NetErrorException;
import com.tencent.map.tools.net.exception.NetUnavailableException;
import com.tencent.mapsdk.vector.VectorMap;

/* loaded from: classes9.dex */
public class yi extends hb {
    private Context b;
    private String d;

    /* renamed from: f  reason: collision with root package name */
    private VectorMap f17521f;

    /* renamed from: g  reason: collision with root package name */
    private v6 f17522g;

    /* renamed from: c  reason: collision with root package name */
    private mb f17519c = new mb();

    /* renamed from: e  reason: collision with root package name */
    private String f17520e = "";

    public yi(ej ejVar) {
        this.d = "UNKNOW";
        Context context = ejVar.getContext();
        this.b = context;
        this.d = context.getClass().getSimpleName();
        this.b = this.b.getApplicationContext();
        this.f17521f = ejVar.getMap();
        this.f17522g = ejVar.A().w();
    }

    @Override // com.tencent.mapsdk.internal.hb
    public byte[] e(String str) {
        ma.a(la.f16819f, "download url : " + str);
        if (this.b == null || e7.b(str) || !this.f17519c.b(str)) {
            return null;
        }
        if (this.f17521f != null && e7.b(this.f17520e) && !e7.b(this.f17521f.y())) {
            this.f17520e = "&eng_ver=" + this.f17521f.y();
        }
        String g2 = g(str);
        ma.a(la.f16819f, "rectify url : " + g2);
        try {
            NetResponse doGet = NetManager.getInstance().builder().url(g2).userAgent(NetUtil.STR_UserAgent).doGet();
            if (doGet == null) {
                return null;
            }
            if (!g2.contains("qt=rtt")) {
                this.f17519c.a(g2);
            }
            return doGet.data;
        } catch (Exception e2) {
            if (g2.contains("/mvd_map")) {
                int i2 = -1;
                if (e2 instanceof NetUnavailableException) {
                    i2 = ((NetUnavailableException) e2).errorCode;
                } else if (e2 instanceof NetErrorException) {
                    i2 = ((NetErrorException) e2).statusCode;
                }
                this.f17522g.l().a(System.currentTimeMillis(), g2.substring(g2.indexOf(63) + 1), i2);
            }
            return null;
        }
    }

    public String g(@NonNull String str) {
        h3 h3Var = (h3) l2.a(h3.class);
        String indoorMapUrl = ((v2) ((j3) l2.a(j3.class)).d()).getIndoorMapUrl();
        String indoorMapUrl2 = ((u2) h3Var.d()).getIndoorMapUrl();
        Uri parse = Uri.parse(str);
        Uri parse2 = Uri.parse(indoorMapUrl);
        Uri parse3 = Uri.parse(indoorMapUrl2);
        String f2 = e7.f(parse.getAuthority());
        String f3 = e7.f(parse.getPath());
        String f4 = e7.f(parse2.getPath());
        String f5 = e7.f(parse3.getPath());
        if (f2.equals(parse2.getAuthority()) && (f3.startsWith(f4) || f3.startsWith(f5))) {
            str = parse3.buildUpon().scheme(h3Var.e() ? "https" : parse.getScheme()).encodedPath(f3.replace(f4, f5)).encodedQuery(parse.getQuery()).appendQueryParameter("type", "1").toString();
        }
        q3 q3Var = (q3) l2.a(q3.class);
        if (q3Var == null || str.endsWith(".jpg") || str.startsWith(q3Var.h())) {
            return str;
        }
        return str + this.f17520e + b7.d(this.d);
    }
}
