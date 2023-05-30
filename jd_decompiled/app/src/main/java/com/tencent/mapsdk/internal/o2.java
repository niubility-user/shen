package com.tencent.mapsdk.internal;

import com.tencent.map.tools.net.http.HttpProxyRule;
import java.util.List;

/* loaded from: classes9.dex */
public class o2 implements n2 {
    private boolean a;
    private List<HttpProxyRule> b;

    @Override // com.tencent.mapsdk.internal.n2
    public List<HttpProxyRule> a() {
        return this.b;
    }

    public void a(m2 m2Var) {
        this.a = m2Var.a;
        this.b = m2Var.b;
    }

    public void a(List<HttpProxyRule> list) {
        this.b = list;
    }

    public void a(boolean z) {
        this.a = z;
    }

    @Override // com.tencent.mapsdk.internal.n2
    public boolean b() {
        return this.a;
    }
}
