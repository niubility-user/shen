package com.vivo.push.b;

import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;

/* loaded from: classes11.dex */
public final class p extends com.vivo.push.o {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private byte[] f18251c;
    private long d;

    /* renamed from: e  reason: collision with root package name */
    private InsideNotificationItem f18252e;

    public p(String str, long j2, InsideNotificationItem insideNotificationItem) {
        super(5);
        this.a = str;
        this.d = j2;
        this.f18252e = insideNotificationItem;
    }

    @Override // com.vivo.push.o
    protected final void c(com.vivo.push.a aVar) {
        aVar.a("package_name", this.a);
        aVar.a("notify_id", this.d);
        aVar.a("notification_v1", com.vivo.push.util.q.b(this.f18252e));
        aVar.a("open_pkg_name", this.b);
        aVar.a("open_pkg_name_encode", this.f18251c);
    }

    public final String d() {
        return this.a;
    }

    public final long e() {
        return this.d;
    }

    public final InsideNotificationItem f() {
        return this.f18252e;
    }

    @Override // com.vivo.push.o
    public final String toString() {
        return "OnNotificationClickCommand";
    }

    @Override // com.vivo.push.o
    protected final void d(com.vivo.push.a aVar) {
        this.a = aVar.a("package_name");
        this.d = aVar.b("notify_id", -1L);
        this.b = aVar.a("open_pkg_name");
        this.f18251c = aVar.b("open_pkg_name_encode");
        String a = aVar.a("notification_v1");
        if (!TextUtils.isEmpty(a)) {
            this.f18252e = com.vivo.push.util.q.a(a);
        }
        InsideNotificationItem insideNotificationItem = this.f18252e;
        if (insideNotificationItem != null) {
            insideNotificationItem.setMsgId(this.d);
        }
    }

    public p() {
        super(5);
    }
}
