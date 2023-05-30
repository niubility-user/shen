package com.meizu.cloud.pushsdk.c;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.f.g.e;
import com.meizu.cloud.pushsdk.notification.model.BrightRemindSetting;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes13.dex */
public class b {
    private static final String d = "b";
    private final HashMap<String, String> a;
    private final HashMap<String, Object> b;

    /* renamed from: c  reason: collision with root package name */
    private final HashMap<String, Object> f15688c;

    /* renamed from: com.meizu.cloud.pushsdk.c.b$b  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static class C0753b {
        private Context a = null;

        public C0753b b(Context context) {
            this.a = context;
            return this;
        }

        public b c() {
            return new b(this);
        }
    }

    private b(C0753b c0753b) {
        this.a = new HashMap<>();
        this.b = new HashMap<>();
        this.f15688c = new HashMap<>();
        k();
        if (c0753b.a != null) {
            l(c0753b.a);
            j(c0753b.a);
            g(c0753b.a);
            b(c0753b.a);
        }
        DebugLogger.i(d, "Subject created successfully.");
    }

    private void b(Context context) {
        d("pn", context.getPackageName());
        d("pv", MzSystemUtils.getAppVersionName(context));
        d("pvc", Integer.valueOf(MzSystemUtils.getAppVersionCode(context)));
        d("st", Integer.valueOf(!TextUtils.isEmpty(MzSystemUtils.findReceiver(context, "com.meizu.ups.push.intent.MESSAGE", context.getPackageName())) ? 1 : 0));
    }

    private void c(String str, int i2, int i3) {
        this.a.put(str, i2 + OrderISVUtil.MONEY_DECIMAL + i3);
    }

    private void d(String str, Object obj) {
        if (TextUtils.isEmpty(str) || obj == null) {
            return;
        }
        if ((obj instanceof String) && ((String) obj).isEmpty()) {
            return;
        }
        this.b.put(str, obj);
    }

    private void e(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.a.put(str, str2);
    }

    private void g(Context context) {
        h("nt", MzSystemUtils.getNetWorkType(context));
    }

    private void h(String str, Object obj) {
        if (TextUtils.isEmpty(str) || obj == null) {
            return;
        }
        if ((obj instanceof String) && ((String) obj).isEmpty()) {
            return;
        }
        this.f15688c.put(str, obj);
    }

    private void k() {
        e(BrightRemindSetting.BRIGHT_REMIND, BaseInfo.getDeviceBrand());
        e("dc", BaseInfo.getDeviceModel());
        e("ot", Build.VERSION.RELEASE);
        e("ov", BaseInfo.getOSName());
        e("ll", MzSystemUtils.getCurrentLanguage());
    }

    private void l(Context context) {
        e("op", e.i(context));
    }

    public Map<String, Object> a() {
        return this.b;
    }

    public Map<String, String> f() {
        return this.a;
    }

    public Map<String, Object> i() {
        return this.f15688c;
    }

    public void j(Context context) {
        Point g2 = e.g(context);
        if (g2 == null) {
            DebugLogger.e(d, "screen information not available.");
        } else {
            c("ss", g2.x, g2.y);
        }
    }
}
