package com.jingdong.jdpush_new.connect;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;

/* loaded from: classes12.dex */
public class d {
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f12800c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class b {
        static d a = new d();
    }

    public static d c() {
        return b.a;
    }

    private void e() {
        try {
            JDJSONObject parseObject = JDJSON.parseObject(com.jd.lib.push.utils.d.d());
            int optInt = parseObject.optInt("heartInterval", 120);
            this.a = optInt;
            if (optInt < 5) {
                this.a = 5;
            }
            int optInt2 = parseObject.optInt("checkHeartInterval", 3);
            this.b = optInt2;
            if (optInt2 < 2) {
                this.b = 2;
            }
            int optInt3 = parseObject.optInt("bgDisconnectDelay", this.f12800c);
            this.f12800c = optInt3;
            if (optInt3 < 0) {
                this.f12800c = 0;
            }
        } catch (Exception e2) {
            com.jingdong.jdpush_new.j.g.g(e2);
        }
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    public int d() {
        return this.f12800c;
    }

    private d() {
        this.a = 120;
        this.b = 3;
        this.f12800c = 210;
        e();
    }
}
