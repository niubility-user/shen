package g.j.b.a;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.xiaomi.push.t0;

/* loaded from: classes11.dex */
public class a {
    private String a;
    private boolean b;

    /* renamed from: c */
    private boolean f19662c;
    private boolean d;

    /* renamed from: e */
    private long f19663e;

    /* renamed from: f */
    private long f19664f;

    /* renamed from: g */
    private long f19665g;

    /* renamed from: g.j.b.a.a$a */
    /* loaded from: classes11.dex */
    public static class C0842a {
        private int a = -1;
        private int b = -1;

        /* renamed from: c */
        private int f19666c = -1;
        private String d = null;

        /* renamed from: e */
        private long f19667e = -1;

        /* renamed from: f */
        private long f19668f = -1;

        /* renamed from: g */
        private long f19669g = -1;

        public a h(Context context) {
            return new a(context, this);
        }

        public C0842a i(String str) {
            this.d = str;
            return this;
        }

        public C0842a j(boolean z) {
            this.a = z ? 1 : 0;
            return this;
        }

        public C0842a k(long j2) {
            this.f19668f = j2;
            return this;
        }

        public C0842a l(boolean z) {
            this.b = z ? 1 : 0;
            return this;
        }

        public C0842a m(long j2) {
            this.f19667e = j2;
            return this;
        }

        public C0842a n(long j2) {
            this.f19669g = j2;
            return this;
        }

        public C0842a o(boolean z) {
            this.f19666c = z ? 1 : 0;
            return this;
        }
    }

    private a(Context context, C0842a c0842a) {
        this.b = true;
        this.f19662c = false;
        this.d = false;
        this.f19663e = 1048576L;
        this.f19664f = TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC;
        this.f19665g = TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC;
        if (c0842a.a == 0) {
            this.b = false;
        } else {
            int unused = c0842a.a;
            this.b = true;
        }
        this.a = !TextUtils.isEmpty(c0842a.d) ? c0842a.d : t0.b(context);
        this.f19663e = c0842a.f19667e > -1 ? c0842a.f19667e : 1048576L;
        if (c0842a.f19668f > -1) {
            this.f19664f = c0842a.f19668f;
        } else {
            this.f19664f = TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC;
        }
        if (c0842a.f19669g > -1) {
            this.f19665g = c0842a.f19669g;
        } else {
            this.f19665g = TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC;
        }
        if (c0842a.b != 0 && c0842a.b == 1) {
            this.f19662c = true;
        } else {
            this.f19662c = false;
        }
        if (c0842a.f19666c != 0 && c0842a.f19666c == 1) {
            this.d = true;
        } else {
            this.d = false;
        }
    }

    public static a a(Context context) {
        C0842a b = b();
        b.j(true);
        b.i(t0.b(context));
        b.m(1048576L);
        b.l(false);
        b.k(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC);
        b.o(false);
        b.n(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC);
        return b.h(context);
    }

    public static C0842a b() {
        return new C0842a();
    }

    public long c() {
        return this.f19664f;
    }

    public long d() {
        return this.f19663e;
    }

    public long e() {
        return this.f19665g;
    }

    public boolean f() {
        return this.b;
    }

    public boolean g() {
        return this.f19662c;
    }

    public boolean h() {
        return this.d;
    }

    public String toString() {
        return "Config{mEventEncrypted=" + this.b + ", mAESKey='" + this.a + "', mMaxFileLength=" + this.f19663e + ", mEventUploadSwitchOpen=" + this.f19662c + ", mPerfUploadSwitchOpen=" + this.d + ", mEventUploadFrequency=" + this.f19664f + ", mPerfUploadFrequency=" + this.f19665g + '}';
    }
}
