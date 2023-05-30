package com.jd.lib.push.c;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.lib.push.c.k;
import com.jd.lib.push.utils.PushMessageUtils;
import com.jingdong.jdpush_new.d.Brands;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes16.dex */
public class f extends com.jd.lib.push.c.a {

    /* renamed from: e */
    boolean f5664e;

    /* renamed from: f */
    boolean f5665f;

    /* loaded from: classes16.dex */
    public class a implements com.hihonor.push.sdk.a<String> {
        a() {
            f.this = r1;
        }

        @Override // com.hihonor.push.sdk.a
        /* renamed from: a */
        public void onSuccess(String str) {
            com.jingdong.jdpush_new.j.g.a("honor get token: " + str);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            e.g();
            com.jd.lib.push.a.b(f.this.a, str);
        }

        @Override // com.hihonor.push.sdk.a
        public void onFailure(int i2, String str) {
            com.jingdong.jdpush_new.j.g.c("honor get token fail, errorCode " + i2 + " , errorString: " + str);
        }
    }

    /* loaded from: classes16.dex */
    public class b implements com.hihonor.push.sdk.a<Void> {
        final /* synthetic */ String a;
        final /* synthetic */ Context b;

        /* renamed from: c */
        final /* synthetic */ int f5666c;

        b(String str, Context context, int i2) {
            this.a = str;
            this.b = context;
            this.f5666c = i2;
        }

        @Override // com.hihonor.push.sdk.a
        /* renamed from: a */
        public void onSuccess(Void r3) {
            com.jingdong.jdpush_new.j.g.h("delete honor token success : " + this.a);
            PushMessageUtils.saveMIRegId(this.b, "", this.f5666c);
        }

        @Override // com.hihonor.push.sdk.a
        public void onFailure(int i2, String str) {
            com.jingdong.jdpush_new.j.g.h("delete honor token fail : code" + i2 + ",msg: " + str);
        }
    }

    public f() {
        super(12);
        this.f5664e = true;
        this.f5665f = true;
    }

    public static void g() {
        com.jingdong.jdpush_new.j.g.h("try delete honor token");
        Context applicationContext = JdSdk.getInstance().getApplicationContext();
        String mIRegId = PushMessageUtils.getMIRegId(applicationContext, 12);
        if (!TextUtils.isEmpty(mIRegId)) {
            com.hihonor.push.sdk.b.c().e(applicationContext, false);
            com.hihonor.push.sdk.b.c().b(new b(mIRegId, applicationContext, 12));
            return;
        }
        com.jingdong.jdpush_new.j.g.a("honor token not found");
    }

    @Override // com.jd.lib.push.c.a
    public void b(Context context) {
        e(context, 0);
    }

    @Override // com.jd.lib.push.c.a
    public String c() {
        return com.jingdong.jdpush_new.j.c.e(this.f5659c, "com.hihonor.android.pushagent");
    }

    @Override // com.jd.lib.push.c.a
    public void e(Context context, int i2) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("package", jd.wjlogin_sdk.util.f.f19954c);
            bundle.putString("class", Constants.MAINACTIVITY_FULLNAME);
            bundle.putInt("badgenumber", i2);
            try {
                if (this.f5665f) {
                    context.getContentResolver().call(Uri.parse("content://com.hihonor.android.launcher.settings/badge/"), "change_badge", (String) null, bundle);
                    com.jingdong.jdpush_new.j.g.i("PushChannel", "\u8363\u8000\u6e05\u9664\u89d2\u6807" + bundle.toString());
                }
            } catch (Exception e2) {
                com.jingdong.jdpush_new.j.g.f("\u8363\u8000\u6e05\u9664\u89d2\u6807\u65f6\u51fa\u9519", e2);
                this.f5665f = false;
            }
            try {
                if (this.f5664e && Brands.a(BaseInfo.getDeviceBrand()) == 10 && com.jd.lib.push.utils.d.i()) {
                    context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", (String) null, bundle);
                    com.jingdong.jdpush_new.j.g.i("PushChannel", "\u534e\u4e3a\u6e05\u9664\u89d2\u6807" + bundle.toString());
                }
            } catch (Exception e3) {
                com.jingdong.jdpush_new.j.g.f("\u8363\u8000\u6e05\u9664\u89d2\u6807\u65f6\u51fa\u9519", e3);
                this.f5664e = false;
            }
        } catch (Throwable th) {
            com.jingdong.jdpush_new.j.g.f("\u8363\u8000\u6e05\u9664\u89d2\u6807\u65f6\u51fa\u9519", th);
            this.d = false;
        }
    }

    @Override // com.jd.lib.push.c.a
    public void f() {
        boolean z;
        com.jingdong.jdpush_new.j.g.b("PushChannel", "--------->\u8363\u8000\u8bbe\u5907\u63a8\u9001\u901a\u9053\u5f00\u59cb\u521d\u59cb\u5316");
        k kVar = this.b;
        boolean z2 = true;
        if (kVar != null) {
            k.a b2 = kVar.b();
            boolean b3 = b2.b();
            z2 = b2.a();
            z = b3;
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
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        com.hihonor.push.sdk.b.c().e(this.f5659c, false);
        com.hihonor.push.sdk.b.c().d(new a());
    }
}
