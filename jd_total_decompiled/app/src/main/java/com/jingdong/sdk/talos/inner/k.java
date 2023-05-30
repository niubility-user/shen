package com.jingdong.sdk.talos.inner;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.talos.inner.a.c;
import com.jingdong.sdk.talos.inner.e;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class k implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    private boolean f15529g = true;

    /* renamed from: h  reason: collision with root package name */
    j f15530h;

    /* renamed from: i  reason: collision with root package name */
    b f15531i;

    /* renamed from: j  reason: collision with root package name */
    private g f15532j;

    /* loaded from: classes10.dex */
    final class a implements com.jingdong.sdk.talos.inner.a.d {
        final /* synthetic */ File a;

        a(File file) {
            this.a = file;
        }

        @Override // com.jingdong.sdk.talos.inner.a.e
        public final void a(String str) {
            String str2;
            if (!k.this.f15530h.f15528e) {
                try {
                    str2 = new JSONObject(str).optString("Url", "");
                } catch (Exception unused) {
                    str2 = "";
                }
                if (TextUtils.isEmpty(str2)) {
                    k.this.f15532j.f("", 3);
                } else {
                    k.this.f15532j.f(str2, 1);
                }
            }
            k.c(k.this, this.a);
            if (com.jingdong.sdk.talos.a.h().v()) {
                e.d.b("SendLogRunnable", "\u6587\u4ef6\u4e0a\u4f20\u6210\u529f:".concat(String.valueOf(str)));
            }
        }

        @Override // com.jingdong.sdk.talos.inner.a.e
        public final void b(String str) {
            if (!k.this.f15530h.f15528e) {
                k.this.f15532j.f("", 0);
            }
            k.c(k.this, this.a);
            if (com.jingdong.sdk.talos.a.h().v()) {
                e.d.b("SendLogRunnable", "\u6587\u4ef6\u4e0a\u4f20\u5931\u8d25:".concat(String.valueOf(str)));
            }
        }

        @Override // com.jingdong.sdk.talos.inner.a.d
        public final void c(String str) {
            if (!k.this.f15530h.f15528e) {
                k.this.f15532j.f("", 2);
            }
            k.c(k.this, this.a);
            if (com.jingdong.sdk.talos.a.h().v()) {
                e.d.b("SendLogRunnable", "\u6587\u4ef6\u4e0a\u4f20\u53d1\u751fIO\u5f02\u5e38".concat(String.valueOf(str)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface b {
        void a();
    }

    public k(g gVar) {
        this.f15532j = gVar;
    }

    private static String b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(PairKey.APP_KEY, com.jingdong.sdk.talos.a.h().u());
            jSONObject.put("client", "android");
            jSONObject.put("accountId", com.jingdong.sdk.talos.a.h().t());
            jSONObject.put("machineId", com.jingdong.sdk.talos.a.h().e());
            jSONObject.put(HybridSDK.APP_VERSION, e.f.c());
            jSONObject.put(HybridSDK.OS_VERSION, BaseInfo.getAndroidVersion());
            jSONObject.put("deviceBrand", BaseInfo.getDeviceManufacture());
            jSONObject.put("deviceModel", BaseInfo.getDeviceModel());
            jSONObject.put(Configuration.PARTNER, com.jingdong.sdk.talos.a.h().q());
            jSONObject.put("logDate", com.jingdong.sdk.talos.a.h().l());
            jSONObject.put("logId", com.jingdong.sdk.talos.a.h().m());
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    static /* synthetic */ void c(k kVar, File file) {
        kVar.e();
        if (file.getName().contains(".copy")) {
            file.delete();
        }
    }

    private void e() {
        b bVar = this.f15531i;
        if (bVar != null) {
            bVar.a();
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        j jVar = this.f15530h;
        if (jVar == null || TextUtils.isEmpty(jVar.b)) {
            e();
        } else if (TextUtils.isEmpty(this.f15530h.f15527c)) {
            e();
        } else {
            File file = new File(this.f15530h.f15527c);
            e.d.b("SendLogRunnable", "\u5f00\u59cb\u4e0a\u62a5\u6587\u4ef6: " + file.getAbsolutePath());
            c cVar = new c();
            cVar.c(com.jingdong.sdk.talos.a.h().r());
            String b2 = b();
            if (this.f15529g) {
                b2 = e.b.a(e.b.b(b2.getBytes()));
            }
            try {
                b2 = URLEncoder.encode(b2, "utf-8");
            } catch (UnsupportedEncodingException unused) {
            }
            cVar.g("appInfo", b2);
            boolean z = this.f15529g;
            if (z) {
                cVar.g("ef", z ? "1" : "0");
            }
            cVar.f15491l.add(file);
            cVar.f15488k = new a(file);
            cVar.f();
        }
    }
}
