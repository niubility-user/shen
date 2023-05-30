package com.xiaomi.mipush.sdk;

import com.jd.dynamic.DYConstants;

/* loaded from: classes11.dex */
public class p {
    private com.xiaomi.push.service.k2.a a = com.xiaomi.push.service.k2.a.China;
    private boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18417c = false;
    private boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    private boolean f18418e = false;

    public boolean a() {
        return this.d;
    }

    public boolean b() {
        return this.f18417c;
    }

    public boolean c() {
        return this.f18418e;
    }

    public boolean d() {
        return this.b;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("PushConfiguration{");
        stringBuffer.append("Region:");
        com.xiaomi.push.service.k2.a aVar = this.a;
        stringBuffer.append(aVar == null ? DYConstants.DY_NULL_STR : aVar.name());
        stringBuffer.append(",mOpenHmsPush:" + this.b);
        stringBuffer.append(",mOpenFCMPush:" + this.f18417c);
        stringBuffer.append(",mOpenCOSPush:" + this.d);
        stringBuffer.append(",mOpenFTOSPush:" + this.f18418e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
