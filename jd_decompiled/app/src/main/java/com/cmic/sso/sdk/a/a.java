package com.cmic.sso.sdk.a;

/* loaded from: classes.dex */
public class a implements Cloneable {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f953c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f954e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f955f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f956g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f957h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f958i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f959j;

    /* renamed from: k  reason: collision with root package name */
    private int f960k;

    /* renamed from: l  reason: collision with root package name */
    private int f961l;

    /* renamed from: com.cmic.sso.sdk.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0016a {
        private final a a = new a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0016a a(String str) {
            this.a.a = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0016a b(String str) {
            this.a.b = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0016a c(String str) {
            this.a.f953c = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0016a d(String str) {
            this.a.d = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0016a e(boolean z) {
            this.a.f958i = z;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0016a f(boolean z) {
            this.a.f959j = z;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0016a a(boolean z) {
            this.a.f954e = z;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0016a b(boolean z) {
            this.a.f955f = z;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0016a c(boolean z) {
            this.a.f956g = z;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0016a d(boolean z) {
            this.a.f957h = z;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0016a a(int i2) {
            this.a.f960k = i2;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0016a b(int i2) {
            this.a.f961l = i2;
            return this;
        }

        public a a() {
            return this.a;
        }
    }

    public boolean g() {
        return this.f956g;
    }

    public boolean h() {
        return this.f957h;
    }

    public boolean i() {
        return this.f958i;
    }

    public boolean j() {
        return this.f959j;
    }

    public int k() {
        return this.f960k;
    }

    public int l() {
        return this.f961l;
    }

    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public a clone() throws CloneNotSupportedException {
        return (a) super.clone();
    }

    public String toString() {
        return "UmcConfigBean{mHttpsGetTokenHost='" + this.a + "', mHttpsGetPhoneScripHost='" + this.b + "', mConfigHost='" + this.f953c + "', mLogHost='" + this.d + "', mCloseCtccWork=" + this.f954e + ", mCloseCuccWort=" + this.f955f + ", mCloseM008Business=" + this.f956g + ", mCloseGetPhoneIpv4=" + this.f957h + ", mCloseGetPhoneIpv6=" + this.f958i + ", mCloseLog=" + this.f959j + ", mMaxFailedLogTimes=" + this.f960k + ", mLogSuspendTime=" + this.f961l + '}';
    }

    private a() {
        this.a = "rcs.cmpassport.com";
        this.b = "rcs.cmpassport.com";
        this.f953c = "config2.cmpassport.com";
        this.d = "log2.cmpassport.com:9443";
        this.f954e = false;
        this.f955f = false;
        this.f956g = false;
        this.f957h = false;
        this.f958i = false;
        this.f959j = false;
        this.f960k = 3;
        this.f961l = 1;
    }

    public boolean e() {
        return this.f954e;
    }

    public boolean f() {
        return this.f955f;
    }

    public String c() {
        return this.f953c;
    }

    public String d() {
        return this.d;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}
