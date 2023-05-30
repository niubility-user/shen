package com.huawei.hms.common.internal;

/* loaded from: classes12.dex */
public class ResolveClientBean {
    private final int a;
    private final AnyClient b;

    /* renamed from: c  reason: collision with root package name */
    private int f1274c;

    public ResolveClientBean(AnyClient anyClient, int i2) {
        this.b = anyClient;
        this.a = Objects.hashCode(anyClient);
        this.f1274c = i2;
    }

    public void clientReconnect() {
        this.b.connect(this.f1274c, true);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ResolveClientBean)) {
            return false;
        }
        return this.b.equals(((ResolveClientBean) obj).b);
    }

    public AnyClient getClient() {
        return this.b;
    }

    public int hashCode() {
        return this.a;
    }
}
