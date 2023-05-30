package com.huawei.hms.hatool;

/* loaded from: classes12.dex */
public class m1 {
    private static m1 b = new m1();
    private a a = new a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class a {
        String a;
        String b;

        /* renamed from: c  reason: collision with root package name */
        long f1398c = 0;

        a() {
        }

        void a(long j2) {
            m1.this.a.f1398c = j2;
        }

        void a(String str) {
            m1.this.a.b = str;
        }

        void b(String str) {
            m1.this.a.a = str;
        }
    }

    public static m1 d() {
        return b;
    }

    public String a() {
        return this.a.b;
    }

    public void a(String str, String str2) {
        long b2 = b();
        String b3 = w0.b(str, str2);
        if (b3 == null || b3.isEmpty()) {
            v.e("WorkKeyHandler", "get rsa pubkey config error");
            return;
        }
        if (b2 == 0) {
            b2 = System.currentTimeMillis();
        } else if (System.currentTimeMillis() - b2 <= 43200000) {
            return;
        }
        String d = com.huawei.secure.android.common.encrypt.d.b.d(16);
        String a2 = h0.a(b3, d);
        this.a.a(b2);
        this.a.b(d);
        this.a.a(a2);
    }

    public long b() {
        return this.a.f1398c;
    }

    public String c() {
        return this.a.a;
    }
}
