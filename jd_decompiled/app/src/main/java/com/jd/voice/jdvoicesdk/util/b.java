package com.jd.voice.jdvoicesdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;

/* loaded from: classes18.dex */
public class b {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f7242c;

    public b(Context context) {
        b(context);
    }

    private void a(Context context, NetworkInfo networkInfo) {
        String lowerCase;
        if (networkInfo.getExtraInfo() != null && (lowerCase = networkInfo.getExtraInfo().toLowerCase()) != null) {
            if (!lowerCase.startsWith("cmwap") && !lowerCase.startsWith("uniwap") && !lowerCase.startsWith("3gwap")) {
                if (lowerCase.startsWith("ctwap")) {
                    this.a = lowerCase;
                    this.b = "10.0.0.200";
                    return;
                } else if (lowerCase.startsWith("cmnet") || lowerCase.startsWith("uninet") || lowerCase.startsWith("ctnet") || lowerCase.startsWith("3gnet")) {
                    this.a = lowerCase;
                    return;
                }
            } else {
                this.a = lowerCase;
                this.b = "10.0.0.172";
                return;
            }
        }
        String defaultHost = Proxy.getDefaultHost();
        int defaultPort = Proxy.getDefaultPort();
        if (defaultHost == null || defaultHost.length() <= 0) {
            return;
        }
        this.b = defaultHost;
        if ("10.0.0.172".equals(defaultHost.trim()) || "10.0.0.200".equals(this.b.trim())) {
            return;
        }
        Integer.toString(defaultPort);
    }

    private void b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            if ("wifi".equals(activeNetworkInfo.getTypeName().toLowerCase())) {
                this.f7242c = "wifi";
                return;
            }
            a(context, activeNetworkInfo);
            this.f7242c = this.a;
        }
    }

    public String c() {
        return this.f7242c;
    }
}
