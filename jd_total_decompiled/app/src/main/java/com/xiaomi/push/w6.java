package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import com.xiaomi.push.service.XMPushService;
import java.io.File;

/* loaded from: classes11.dex */
public class w6 implements XMPushService.n {
    private static boolean d;
    private Context a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private int f19287c;

    public w6(Context context) {
        this.a = context;
    }

    private String a(String str) {
        return "com.xiaomi.xmsf".equals(str) ? "1000271" : this.a.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, null);
    }

    private void b(Context context) {
        this.b = com.xiaomi.push.service.b0.d(context).m(h7.TinyDataUploadSwitch.a(), true);
        int a = com.xiaomi.push.service.b0.d(context).a(h7.TinyDataUploadFrequency.a(), R2.dimen.rvc_ui_jd_layer2_lin_height);
        this.f19287c = a;
        this.f19287c = Math.max(60, a);
    }

    public static void c(boolean z) {
        d = z;
    }

    private boolean d() {
        return Math.abs((System.currentTimeMillis() / 1000) - this.a.getSharedPreferences("mipush_extra", 4).getLong("last_tiny_data_upload_timestamp", -1L)) > ((long) this.f19287c);
    }

    private boolean e(b7 b7Var) {
        if (!j0.p(this.a) || b7Var == null || TextUtils.isEmpty(a(this.a.getPackageName())) || !new File(this.a.getFilesDir(), "tiny_data.data").exists() || d) {
            return false;
        }
        return !com.xiaomi.push.service.b0.d(this.a).m(h7.ScreenOnOrChargingTinyDataUploadSwitch.a(), false) || z6.j(this.a) || z6.p(this.a);
    }

    @Override // com.xiaomi.push.service.XMPushService.n
    public void a() {
        b(this.a);
        if (this.b && d()) {
            g.j.a.a.a.c.o("TinyData TinyDataCacheProcessor.pingFollowUpAction ts:" + System.currentTimeMillis());
            b7 b = a7.a(this.a).b();
            if (e(b)) {
                d = true;
                x6.b(this.a, b);
                return;
            }
            g.j.a.a.a.c.o("TinyData TinyDataCacheProcessor.pingFollowUpAction !canUpload(uploader) ts:" + System.currentTimeMillis());
        }
    }
}
