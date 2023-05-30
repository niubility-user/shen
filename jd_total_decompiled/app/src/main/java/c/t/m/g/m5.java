package c.t.m.g;

import android.content.Context;
import android.text.TextUtils;
import com.jdpay.system.SystemInfo;
import com.jingdong.common.permission.RomUtil;
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
    */
    public void d(Context context) {
        String str;
        b bVar;
        a0 a0Var;
        d6 d6Var;
        new StringBuilder("getManufacturer ===> ").append(b());
        String upperCase = b().toUpperCase();
        if (!"ASUS".equals(upperCase) && !"HUAWEI".equals(upperCase)) {
            if (RomUtil.ROM_LENOVO.equals(upperCase)) {
                d6Var = new d6(context);
            } else if ("MOTOLORA".equals(upperCase)) {
                d6Var = new d6(context);
            } else {
                if (!"MEIZU".equals(upperCase)) {
                    if ("NUBIA".equals(upperCase)) {
                        str = new s6(context).a();
                    } else if (!"OPPO".equals(upperCase)) {
                        if (RomUtil.ROM_SAMSUNG.equals(upperCase)) {
                            h();
                        } else if ("VIVO".equals(upperCase)) {
                            str = new r(context).a();
                        } else {
                            if ("XIAOMI".equals(upperCase)) {
                                a0Var = new a0(context);
                            } else if ("BLACKSHARK".equals(upperCase)) {
                                a0Var = new a0(context);
                            } else if (!SystemInfo.ROM_1JIA.equals(upperCase)) {
                                if (!"ZTE".equals(upperCase)) {
                                    if (!"FERRMEOS".equals(upperCase)) {
                                        if (!f()) {
                                            if (!"SSUI".equals(upperCase)) {
                                            }
                                        }
                                    }
                                }
                            }
                            str = a0Var.a();
                        }
                    }
                    boolean z = str == null;
                    bVar = this.a;
                    if (bVar == null) {
                        bVar.a(str, z);
                        return;
                    }
                    return;
                }
                new k6(context).b(this.a);
                str = null;
                if (str == null) {
                }
                bVar = this.a;
                if (bVar == null) {
                }
            }
            d6Var.a(this.a);
            str = null;
            if (str == null) {
            }
            bVar = this.a;
            if (bVar == null) {
            }
        }
        e(context, upperCase);
        str = null;
        if (str == null) {
        }
        bVar = this.a;
        if (bVar == null) {
        }
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
