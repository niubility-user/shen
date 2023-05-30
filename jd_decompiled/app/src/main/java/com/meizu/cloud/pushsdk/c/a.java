package com.meizu.cloud.pushsdk.c;

import android.content.Context;
import com.meizu.cloud.pushsdk.c.b;
import com.meizu.cloud.pushsdk.f.b.c;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;
import java.util.Map;

/* loaded from: classes13.dex */
public class a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.meizu.cloud.pushsdk.c.a$a */
    /* loaded from: classes13.dex */
    public class RunnableC0752a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ Context f15687g;

        RunnableC0752a(Context context) {
            this.f15687g = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.meizu.cloud.pushsdk.c.c.b.a(this.f15687g).g("POST", null, a.a(this.f15687g).toString());
        }
    }

    public static c a(Context context) {
        c cVar = new c();
        b b = b(context);
        Map<String, String> f2 = b.f();
        Map<String, Object> a = b.a();
        Map<String, Object> i2 = b.i();
        if (f2.size() > 0) {
            cVar.b(AppIconSetting.DEFAULT_LARGE_ICON, f2);
        }
        if (a.size() > 0) {
            cVar.b("ai", a);
        }
        if (i2.size() > 0) {
            cVar.b(AppIconSetting.LARGE_ICON_URL, i2);
        }
        return cVar;
    }

    private static b b(Context context) {
        b.C0753b c0753b = new b.C0753b();
        c0753b.b(context);
        return c0753b.c();
    }

    public static void c(Context context) {
        com.meizu.cloud.pushsdk.d.m.a.a().execute(new RunnableC0752a(context));
    }
}
