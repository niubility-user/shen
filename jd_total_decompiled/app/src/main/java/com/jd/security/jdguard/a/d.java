package com.jd.security.jdguard.a;

import java.net.URI;
import java.util.Map;

/* loaded from: classes17.dex */
public abstract class d implements f {
    private String a = null;
    private Map<String, String> b;

    @Override // com.jd.security.jdguard.a.f
    public String b() {
        String str = this.a;
        if (str != null && str.length() > 0) {
            return this.a;
        }
        if (e()) {
            this.a = "POST";
        } else {
            this.a = "GET";
        }
        return this.a;
    }

    @Override // com.jd.security.jdguard.a.f
    public URI c() {
        return d();
    }

    protected abstract URI d();

    protected abstract boolean e();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(Map<String, String> map) {
        this.b = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(String str) {
        this.a = str;
    }

    @Override // com.jd.security.jdguard.a.f
    public String getPath() {
        URI d = d();
        if (d == null) {
            return null;
        }
        return d.getRawPath();
    }
}
