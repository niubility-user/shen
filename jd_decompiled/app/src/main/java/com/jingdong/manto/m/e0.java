package com.jingdong.manto.m;

import android.text.TextUtils;
import android.util.SparseArray;
import com.jingdong.manto.jsengine.IMantoWebViewJS;
import com.jingdong.manto.utils.MantoStringUtils;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class e0 {
    private String a = e0.class.getSimpleName();
    final AtomicInteger b = new AtomicInteger(0);

    /* renamed from: c  reason: collision with root package name */
    public final SparseArray<a> f13345c = new SparseArray<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public static class a {
        IMantoWebViewJS a;
        int b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(IMantoWebViewJS iMantoWebViewJS, int i2) {
            this.a = iMantoWebViewJS;
            this.b = i2;
        }
    }

    public e0() {
        this.a += "-" + e();
    }

    public String a() {
        if (h() != null) {
            return h().f13017i;
        }
        return null;
    }

    public void a(int i2, String str) {
        if (f()) {
            if (MantoStringUtils.isEmpty(str)) {
                str = "{}";
            }
            a aVar = this.f13345c.get(i2);
            if (aVar == null) {
                return;
            }
            aVar.a.evaluateJavascript(String.format("typeof JDJSBridge !== 'undefined' && JDJSBridge.invokeCallbackHandler(%d, %s)", Integer.valueOf(aVar.b), str), null);
            if (TextUtils.equals("1", com.jingdong.manto.utils.m.a("callBackRemove", "1"))) {
                this.f13345c.remove(i2);
            }
        }
    }

    public void a(String str, String str2, int i2) {
        IMantoWebViewJS g2 = g();
        if (g2 == null) {
            return;
        }
        if (MantoStringUtils.isEmpty(str2)) {
            str2 = "{}";
        }
        String valueOf = i2 == 0 ? "undefined" : String.valueOf(i2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nativeTime", System.currentTimeMillis());
        } catch (Throwable unused) {
        }
        g2.evaluateJavascript(String.format("typeof JDJSBridge !== 'undefined' && JDJSBridge.subscribeHandler(\"%s\", %s, %s, %s)", str, str2, valueOf, jSONObject.toString()), null);
    }

    public abstract void a(String str, String str2, int[] iArr);

    public String b() {
        return h() != null ? h().f13018j : "1";
    }

    public String c() {
        if (h() != null) {
            return h().f13019k;
        }
        return null;
    }

    public abstract k0 d();

    public abstract String e();

    public abstract boolean f();

    public abstract IMantoWebViewJS g();

    public abstract com.jingdong.manto.f h();
}
