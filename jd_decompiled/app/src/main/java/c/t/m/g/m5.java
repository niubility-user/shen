package c.t.m.g;

import android.content.Context;
import android.text.TextUtils;
import com.jdpay.system.SystemInfo;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes.dex */
public class m5 {
    public b a;

    /* loaded from: classes.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ String f546g;

        /* renamed from: h  reason: collision with root package name */
        public final /* synthetic */ Context f547h;

        public a(String str, Context context) {
            this.f546g = str;
            this.f547h = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if ("ASUS".equals(this.f546g)) {
                    new d5(this.f547h).a(m5.this.a);
                } else if ("HUAWEI".equals(this.f546g)) {
                    new v5(this.f547h).a(m5.this.a);
                } else if ("OPPO".equals(this.f546g)) {
                    new j(this.f547h).a(m5.this.a);
                } else if (SystemInfo.ROM_1JIA.equals(this.f546g)) {
                    new c.t.m.g.b(this.f547h).a(m5.this.a);
                } else if ("ZTE".equals(this.f546g)) {
                    new h0(this.f547h).b(m5.this.a);
                } else if ("FERRMEOS".equals(this.f546g) || m5.this.f()) {
                    new h0(this.f547h).b(m5.this.a);
                } else if ("SSUI".equals(this.f546g) || m5.this.g()) {
                    new h0(this.f547h).b(m5.this.a);
                }
            } catch (Exception e2) {
                new StringBuilder("getIDFromNewThead error: ").append(e2);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(String str, boolean z);
    }

    public m5(b bVar) {
        this.a = bVar;
    }

    public final String b() {
        return BaseInfo.getDeviceManufacture().toUpperCase();
    }

    public final String c(String str) {
        if (str == null) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(IMantoServerRequester.GET, String.class, String.class).invoke(cls, str, "unknown");
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00df, code lost:
        if (g() == false) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(android.content.Context r3) {
        /*
            r2 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "getManufacturer ===> "
            r0.<init>(r1)
            java.lang.String r1 = r2.b()
            r0.append(r1)
            java.lang.String r0 = r2.b()
            java.lang.String r0 = r0.toUpperCase()
            java.lang.String r1 = "ASUS"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto Le1
            java.lang.String r1 = "HUAWEI"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto Le1
            java.lang.String r1 = "LENOVO"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L3a
            c.t.m.g.d6 r0 = new c.t.m.g.d6
            r0.<init>(r3)
        L33:
            c.t.m.g.m5$b r3 = r2.a
            r0.a(r3)
            goto Le4
        L3a:
            java.lang.String r1 = "MOTOLORA"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L48
            c.t.m.g.d6 r0 = new c.t.m.g.d6
            r0.<init>(r3)
            goto L33
        L48:
            java.lang.String r1 = "MEIZU"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L5c
            c.t.m.g.k6 r0 = new c.t.m.g.k6
            r0.<init>(r3)
            c.t.m.g.m5$b r3 = r2.a
            r0.b(r3)
            goto Le4
        L5c:
            java.lang.String r1 = "NUBIA"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L6f
            c.t.m.g.s6 r0 = new c.t.m.g.s6
            r0.<init>(r3)
            java.lang.String r3 = r0.a()
            goto Le5
        L6f:
            java.lang.String r1 = "OPPO"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto Le1
            java.lang.String r1 = "SAMSUNG"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L83
            r2.h()
            goto Le4
        L83:
            java.lang.String r1 = "VIVO"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L95
            c.t.m.g.r r0 = new c.t.m.g.r
            r0.<init>(r3)
            java.lang.String r3 = r0.a()
            goto Le5
        L95:
            java.lang.String r1 = "XIAOMI"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto La7
            c.t.m.g.a0 r0 = new c.t.m.g.a0
            r0.<init>(r3)
        La2:
            java.lang.String r3 = r0.a()
            goto Le5
        La7:
            java.lang.String r1 = "BLACKSHARK"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto Lb5
            c.t.m.g.a0 r0 = new c.t.m.g.a0
            r0.<init>(r3)
            goto La2
        Lb5:
            java.lang.String r1 = "ONEPLUS"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto Le1
            java.lang.String r1 = "ZTE"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto Le1
            java.lang.String r1 = "FERRMEOS"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto Le1
            boolean r1 = r2.f()
            if (r1 != 0) goto Le1
            java.lang.String r1 = "SSUI"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto Le1
            boolean r1 = r2.g()
            if (r1 == 0) goto Le4
        Le1:
            r2.e(r3, r0)
        Le4:
            r3 = 0
        Le5:
            if (r3 == 0) goto Le9
            r0 = 1
            goto Lea
        Le9:
            r0 = 0
        Lea:
            c.t.m.g.m5$b r1 = r2.a
            if (r1 == 0) goto Lf1
            r1.a(r3, r0)
        Lf1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.m5.d(android.content.Context):void");
    }

    public final void e(Context context, String str) {
        new Thread(new a(str, context)).start();
    }

    public boolean f() {
        String c2 = c("ro.build.freeme.label");
        return !TextUtils.isEmpty(c2) && c2.equalsIgnoreCase("FREEMEOS");
    }

    public boolean g() {
        String c2 = c("ro.ssui.product");
        return (TextUtils.isEmpty(c2) || c2.equalsIgnoreCase("unknown")) ? false : true;
    }

    public final void h() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(null, false);
        }
    }
}
