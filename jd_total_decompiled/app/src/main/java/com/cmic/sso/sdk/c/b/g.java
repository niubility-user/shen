package com.cmic.sso.sdk.c.b;

import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class g {
    public abstract String a();

    protected abstract String a_(String str);

    public abstract JSONObject b();

    public String v(String str) {
        return com.cmic.sso.sdk.e.d.a(a_(str)).toLowerCase();
    }
}
