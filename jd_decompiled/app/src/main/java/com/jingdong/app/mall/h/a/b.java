package com.jingdong.app.mall.h.a;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.jd.verify.ShowCapWithCancelCallback;
import com.jd.verify.Verify;
import com.jd.verify.VerifyParamProxy;
import com.jd.verify.model.IninVerifyInfo;
import com.jingdong.app.mall.h.a.a;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes19.dex */
public class b {
    final com.jingdong.app.mall.h.a.a a;
    public ConcurrentHashMap<String, c> b = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class a implements VerifyParamProxy {
        final /* synthetic */ a.b a;

        a(b bVar, a.b bVar2) {
            this.a = bVar2;
        }

        @Override // com.jd.verify.VerifyParamProxy
        public String getVerifyParams() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("pin", this.a.getUserName());
                jSONObject.put("eid", this.a.getEid());
                return jSONObject.toString();
            } catch (Throwable unused) {
                return "";
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.h.a.b$b */
    /* loaded from: classes19.dex */
    public class RunnableC0266b implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f8441g;

        /* renamed from: h */
        final /* synthetic */ String f8442h;

        /* renamed from: i */
        final /* synthetic */ Context f8443i;

        /* renamed from: j */
        final /* synthetic */ String f8444j;

        /* renamed from: k */
        final /* synthetic */ a.b f8445k;

        /* renamed from: com.jingdong.app.mall.h.a.b$b$a */
        /* loaded from: classes19.dex */
        class a implements ShowCapWithCancelCallback {
            a() {
                RunnableC0266b.this = r1;
            }

            @Override // com.jd.verify.CallBack
            public void invalidSessiongId() {
                RunnableC0266b runnableC0266b = RunnableC0266b.this;
                b.this.e(runnableC0266b.f8441g);
                RunnableC0266b runnableC0266b2 = RunnableC0266b.this;
                c cVar = b.this.b.get(runnableC0266b2.f8441g);
                int i2 = cVar.f8447c + 1;
                cVar.f8447c = i2;
                if (i2 < 2) {
                    IGuardVerifyPlugin guardVerifyPlugin = JDHttpTookit.getEngine().getGuardVerifyPlugin();
                    if (guardVerifyPlugin instanceof com.jingdong.app.mall.h.a.a) {
                        ((com.jingdong.app.mall.h.a.a) guardVerifyPlugin).j(RunnableC0266b.this.f8442h);
                    }
                }
            }

            @Override // com.jd.verify.ShowCapCallback
            public void loadFail() {
                RunnableC0266b runnableC0266b = RunnableC0266b.this;
                b.this.e(runnableC0266b.f8441g);
            }

            @Override // com.jd.verify.ShowCapWithCancelCallback
            public void onDialogCancel() {
                RunnableC0266b runnableC0266b = RunnableC0266b.this;
                b.this.e(runnableC0266b.f8441g);
            }

            @Override // com.jd.verify.InnerCallBack
            public void onFail(String str) {
                RunnableC0266b runnableC0266b = RunnableC0266b.this;
                b.this.e(runnableC0266b.f8441g);
            }

            @Override // com.jd.verify.SSLDialogCallback
            public void onSSLError() {
            }

            @Override // com.jd.verify.InnerCallBack
            public void onSuccess(IninVerifyInfo ininVerifyInfo) {
                RunnableC0266b runnableC0266b = RunnableC0266b.this;
                b.this.e(runnableC0266b.f8441g);
                RunnableC0266b runnableC0266b2 = RunnableC0266b.this;
                if (runnableC0266b2.f8443i == null || ininVerifyInfo == null) {
                    return;
                }
                b.this.a.l(ininVerifyInfo.getVt());
                if (com.jingdong.app.mall.h.a.a.h()) {
                    String str = "\u9a8c\u8bc1\u6210\u529f\uff0c\u83b7\u53d6Token " + b.this.a.getVerifyToken();
                }
            }

            @Override // com.jd.verify.CallBack
            public void showButton(int i2) {
            }

            @Override // com.jd.verify.ShowCapCallback
            public void showCap() {
            }
        }

        RunnableC0266b(String str, String str2, Context context, String str3, a.b bVar) {
            b.this = r1;
            this.f8441g = str;
            this.f8442h = str2;
            this.f8443i = context;
            this.f8444j = str3;
            this.f8445k = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = new a();
            c cVar = b.this.b.get(this.f8441g);
            if (cVar != null) {
                cVar.a.init(this.f8444j, this.f8443i, this.f8445k.getUUID(), "zh", this.f8445k.getUserName(), aVar);
            }
        }
    }

    /* loaded from: classes19.dex */
    public static class c {
        public Verify a;
        public boolean b;

        /* renamed from: c */
        public int f8447c;
    }

    public b(com.jingdong.app.mall.h.a.a aVar) {
        this.a = aVar;
    }

    public void e(String str) {
        if (this.b.containsKey(str)) {
            this.b.get(str).b = true;
        }
    }

    public void b(String str, String str2, Context context, a.b bVar) {
        String obj = context.toString();
        if (this.b.containsKey(obj)) {
            if (!this.b.get(obj).b) {
                return;
            }
        } else {
            Verify.setLog(bVar.isDebug());
            Verify verify2 = Verify.getInstance();
            verify2.isShowToast(false);
            if (bVar.isDebug()) {
                verify2.setInternationalURL("https://beta-lightcap.m.jd.com/jcap/dist/app-light-captcha.html");
            } else {
                verify2.setInternationalURL("https://cfe.m.jd.com/privatedomain/jcap_light/app-light-captcha.html");
            }
            verify2.setParamProxy(new a(this, bVar));
            verify2.setPrivacyInfoProxy(bVar.getVerifyPrivacyInfoProxy());
            c cVar = new c();
            cVar.a = verify2;
            cVar.b = false;
            cVar.f8447c = 0;
            this.b.put(obj, cVar);
        }
        new Handler(Looper.getMainLooper()).post(new RunnableC0266b(obj, str2, context, str, bVar));
    }

    public boolean c() {
        Activity currentMyActivity = JDHttpTookit.getEngine().getAppProxy().getCurrentMyActivity();
        if (this.b.containsKey(currentMyActivity != null ? currentMyActivity.toString() : "unknown")) {
            return !this.b.get(r0).b;
        }
        return false;
    }

    public void d(Activity activity) {
        String obj = activity != null ? activity.toString() : "unknown";
        if (this.b.containsKey(obj)) {
            this.b.get(obj).a.free();
            this.b.remove(obj);
        }
    }
}
