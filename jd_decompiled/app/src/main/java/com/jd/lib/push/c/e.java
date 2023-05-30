package com.jd.lib.push.c;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.push.HmsMessaging;
import com.jd.lib.push.c.k;
import com.jd.lib.push.utils.PushMessageUtils;
import com.jingdong.jdpush_new.d.Brands;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.threadpool.ThreadManager;

/* loaded from: classes16.dex */
public class e extends com.jd.lib.push.c.a {

    /* loaded from: classes16.dex */
    public class a implements Runnable {
        a() {
            e.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                com.jingdong.jdpush_new.j.g.d("PushChannel", "\u534e\u4e3a\u901a\u9053\u521d\u59cb\u5316");
                com.jingdong.jdpush_new.mta.b.b().l(2000);
                Context applicationContext = JdSdk.getInstance().getApplicationContext();
                try {
                    g.e.a.d.e(applicationContext);
                } catch (Exception e2) {
                    if (OKLog.E) {
                        com.jingdong.jdpush_new.j.g.e("PushChannel", "\u534e\u4e3a\u901a\u9053AGConnectInstance.initialize()\u9519\u8bef", e2);
                    }
                }
                String string = g.e.a.h.a.b(applicationContext).getString("client/app_id");
                com.jingdong.jdpush_new.mta.b.b().l(2100);
                String token = HmsInstanceId.getInstance(applicationContext).getToken(string, HmsMessaging.DEFAULT_TOKEN_SCOPE);
                com.jingdong.jdpush_new.mta.b.b().l(2200);
                f.g();
                e.this.a(token);
            } catch (Throwable th) {
                com.jingdong.jdpush_new.j.g.e("PushChannel", "\u534e\u4e3a\u901a\u9053\u83b7\u53d6Token\u5931\u8d25", th);
                com.jingdong.jdpush_new.mta.b.b().i(e.this.a, th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b extends Thread {

        /* renamed from: g */
        final /* synthetic */ Context f5661g;

        /* renamed from: h */
        final /* synthetic */ String f5662h;

        /* renamed from: i */
        final /* synthetic */ int f5663i;

        b(Context context, String str, int i2) {
            this.f5661g = context;
            this.f5662h = str;
            this.f5663i = i2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                HmsInstanceId.getInstance(this.f5661g).deleteToken(g.e.a.h.a.b(this.f5661g).getString("client/app_id"), HmsMessaging.DEFAULT_TOKEN_SCOPE);
                com.jingdong.jdpush_new.j.g.i("PushChannel", "hms token deleted successfully : " + this.f5662h);
                PushMessageUtils.saveMIRegId(this.f5661g, "", this.f5663i);
            } catch (Exception e2) {
                com.jingdong.jdpush_new.j.g.d("PushChannel", "delete hms Token failed." + e2);
            }
        }
    }

    public e() {
        super(2);
    }

    public static void g() {
        try {
            com.jingdong.jdpush_new.j.g.h("try delete hms token");
            Context applicationContext = JdSdk.getInstance().getApplicationContext();
            String mIRegId = PushMessageUtils.getMIRegId(applicationContext, 2);
            if (!TextUtils.isEmpty(mIRegId)) {
                new b(applicationContext, mIRegId, 2).start();
            } else {
                com.jingdong.jdpush_new.j.g.a("hms token not found");
            }
        } catch (Exception e2) {
            com.jingdong.jdpush_new.j.g.e("PushChannel", "delete hms Token \u5f02\u5e38", e2);
        }
    }

    @Override // com.jd.lib.push.c.a
    public void b(Context context) {
        e(context, 0);
    }

    @Override // com.jd.lib.push.c.a
    public String c() {
        return com.jingdong.jdpush_new.j.c.e(this.f5659c, "com.huawei.android.pushagent");
    }

    @Override // com.jd.lib.push.c.a
    public void e(Context context, int i2) {
        try {
            if (this.d) {
                Bundle bundle = new Bundle();
                bundle.putString("package", jd.wjlogin_sdk.util.f.f19954c);
                bundle.putString("class", Constants.MAINACTIVITY_FULLNAME);
                bundle.putInt("badgenumber", i2);
                try {
                    context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", (String) null, bundle);
                    com.jingdong.jdpush_new.j.g.i("PushChannel", "\u534e\u4e3a\u6e05\u9664\u89d2\u6807" + bundle.toString());
                } catch (Exception e2) {
                    com.jingdong.jdpush_new.j.g.c("\u534e\u4e3a\u6e05\u9664\u89d2\u6807\u65f6\u51fa\u9519" + e2);
                }
                try {
                    if (Brands.a(BaseInfo.getDeviceBrand()) == 10 && com.jd.lib.push.utils.d.h()) {
                        context.getContentResolver().call(Uri.parse("content://com.hihonor.android.launcher.settings/badge/"), "change_badge", (String) null, bundle);
                        com.jingdong.jdpush_new.j.g.i("PushChannel", "\u8363\u8000\u6e05\u9664\u89d2\u6807" + bundle.toString());
                    }
                } catch (Exception e3) {
                    com.jingdong.jdpush_new.j.g.c("\u8363\u8000\u6e05\u9664\u89d2\u6807\u65f6\u51fa\u9519" + e3);
                }
            }
        } catch (Throwable th) {
            com.jingdong.jdpush_new.j.g.f("\u534e\u4e3a\u6e05\u9664\u89d2\u6807\u65f6\u51fa\u9519", th);
        }
    }

    @Override // com.jd.lib.push.c.a
    public void f() {
        boolean z;
        com.jingdong.jdpush_new.j.g.b("PushChannel", "--------->\u534e\u4e3a\u8bbe\u5907\u63a8\u9001\u901a\u9053\u5f00\u59cb\u521d\u59cb\u5316");
        k kVar = this.b;
        boolean z2 = true;
        if (kVar != null) {
            k.a a2 = kVar.a();
            boolean b2 = a2.b();
            z2 = a2.a();
            z = b2;
        } else {
            z = true;
        }
        com.jingdong.jdpush_new.mta.b.b().l(100300);
        if (z2) {
            com.jingdong.jdpush_new.mta.b.b().l(100320);
            g.h();
        }
        if (z) {
            com.jingdong.jdpush_new.mta.b.b().l(260);
            h();
        }
    }

    public void h() {
        ThreadManager.light().post(new a());
    }
}
