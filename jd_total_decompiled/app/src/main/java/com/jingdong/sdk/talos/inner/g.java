package com.jingdong.sdk.talos.inner;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.talos.inner.e;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class g extends HandlerThread {

    /* renamed from: j */
    public static String f15522j = "";

    /* renamed from: g */
    public Handler f15523g;

    /* renamed from: h */
    private com.jingdong.sdk.talos.inner.b f15524h;

    /* renamed from: i */
    public boolean f15525i;

    /* loaded from: classes10.dex */
    public final class a extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Looper looper) {
            super(looper);
            g.this = r1;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                g.c(g.this);
            } else if (i2 == 2) {
                g.d(g.this, (String) message.obj, message.arg1);
            } else if (i2 == 3) {
                g.h(g.this);
            } else if (i2 == 4) {
                g.e(g.this, (String[]) message.obj);
            }
            super.handleMessage(message);
        }
    }

    /* loaded from: classes10.dex */
    public final class b implements com.jingdong.sdk.talos.inner.a.e {
        b(g gVar) {
        }

        @Override // com.jingdong.sdk.talos.inner.a.e
        public final void a(String str) {
            if (com.jingdong.sdk.talos.a.h().v()) {
                e.d.b("NetworkEventCenter", "logReport \u6570\u636e\u540c\u6b65\u6210\u529f\uff0c".concat(String.valueOf(str)));
            }
        }

        @Override // com.jingdong.sdk.talos.inner.a.e
        public final void b(String str) {
            if (com.jingdong.sdk.talos.a.h().v()) {
                e.d.b("NetworkEventCenter", "logReport \u6570\u636e\u540c\u6b65\u5931\u8d25\uff0c".concat(String.valueOf(str)));
            }
        }
    }

    /* loaded from: classes10.dex */
    public final class c implements com.jingdong.sdk.talos.inner.a.e {
        c() {
            g.this = r1;
        }

        @Override // com.jingdong.sdk.talos.inner.a.e
        public final void a(String str) {
            if (com.jingdong.sdk.talos.a.h().v()) {
                e.d.b("NetworkEventCenter", "logConfig \u8bf7\u6c42\u7b56\u7565\u6210\u529f\uff0c".concat(String.valueOf(str)));
            }
            com.jingdong.sdk.talos.inner.c.b s = com.jingdong.sdk.talos.a.h().s();
            if (s.b) {
                s.a(str);
            } else {
                try {
                    JSONObject optJSONObject = new JSONObject(str).optJSONObject("data");
                    String optString = optJSONObject.optString("enable", "0");
                    s.a.a = TextUtils.equals(optString, "1");
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("logReport");
                    if (optJSONObject2 != null) {
                        String optString2 = optJSONObject2.optString("logDate", "");
                        String optString3 = optJSONObject2.optString("reportNet", "");
                        String optString4 = optJSONObject2.optString("logId", "");
                        com.jingdong.sdk.talos.inner.c.a aVar = s.a;
                        aVar.b = optString2;
                        aVar.d = optString4;
                        aVar.a(optString3);
                    } else {
                        com.jingdong.sdk.talos.inner.c.a aVar2 = s.a;
                        aVar2.b = "";
                        aVar2.d = "";
                    }
                    JSONObject optJSONObject3 = optJSONObject.optJSONObject("logConfig");
                    if (optJSONObject3 != null) {
                        int optInt = optJSONObject3.optInt("level", 3);
                        String optString5 = optJSONObject3.optString("reportUrl", "");
                        com.jingdong.sdk.talos.inner.c.a aVar3 = s.a;
                        aVar3.f15501i = optInt;
                        aVar3.f15502j = optString5;
                    }
                } catch (Throwable unused) {
                }
            }
            SharedPreferences.Editor edit = s.f15510c.getSharedPreferences("logx_strategyInfo", 0).edit();
            edit.putString("strategy", str);
            edit.commit();
            g.this.b(com.jingdong.sdk.talos.a.h().j() * 60 * 1000);
        }

        @Override // com.jingdong.sdk.talos.inner.a.e
        public final void b(String str) {
            if (com.jingdong.sdk.talos.a.h().v()) {
                e.d.b("NetworkEventCenter", "logConfig \u8bf7\u6c42\u7b56\u7565\u5931\u8d25\uff0c".concat(String.valueOf(str)));
            }
            g.this.b(com.jingdong.sdk.talos.a.h().j() * 60 * 1000);
        }
    }

    public g(com.jingdong.sdk.talos.inner.b bVar) {
        super("oklog-sync-thread");
        this.f15525i = false;
        this.f15524h = bVar;
    }

    static /* synthetic */ void c(g gVar) {
        if (com.jingdong.sdk.talos.a.h().v()) {
            e.d.b("NetworkEventCenter", "logConfig \u5f00\u59cb\u8bf7\u6c42\u7b56\u7565");
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", com.jingdong.sdk.talos.a.h().t());
        hashMap.put("appId", com.jingdong.sdk.talos.a.h().u());
        com.jingdong.sdk.talos.inner.a.b bVar = new com.jingdong.sdk.talos.inner.a.b(i(), "logConfig");
        bVar.h("logConfig");
        bVar.g(hashMap);
        bVar.f15488k = new c();
        bVar.f();
    }

    static /* synthetic */ void d(g gVar, String str, int i2) {
        if (com.jingdong.sdk.talos.a.h().v()) {
            e.d.b("NetworkEventCenter", "logReport \u5f00\u59cb\u6570\u636e\u540c\u6b65");
        }
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userId", com.jingdong.sdk.talos.a.h().t());
            hashMap.put("appId", com.jingdong.sdk.talos.a.h().u());
            hashMap.put("logDate", com.jingdong.sdk.talos.a.h().l());
            hashMap.put("logId", com.jingdong.sdk.talos.a.h().m());
            hashMap.put("logUrl", str);
            hashMap.put("status", String.valueOf(i2));
            com.jingdong.sdk.talos.inner.a.b bVar = new com.jingdong.sdk.talos.inner.a.b(i(), "logReport");
            bVar.h("logReport");
            bVar.g(hashMap);
            bVar.f15488k = new b(gVar);
            bVar.f();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    static /* synthetic */ void e(g gVar, String[] strArr) {
        if (com.jingdong.sdk.talos.a.h().v()) {
            e.d.c("NetworkEventCenter", "\u5f00\u59cb\u8c03\u7528\u4e3b\u52a8\u4e0a\u62a5\u65e5\u5fd7\u63a5\u53e3");
        }
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        gVar.f15524h.d(strArr, true);
    }

    static /* synthetic */ void h(g gVar) {
        if (com.jingdong.sdk.talos.a.h().z() && !TextUtils.isEmpty(com.jingdong.sdk.talos.a.h().l())) {
            gVar.f15524h.d(new String[]{com.jingdong.sdk.talos.a.h().l()}, false);
        } else if (com.jingdong.sdk.talos.a.h().v()) {
            e.d.d("NetworkEventCenter", "\u4e0d\u6ee1\u8db3\u4e0a\u4f20\u6761\u4ef6\uff0c\u653e\u5f03\u4e0a\u4f20");
        }
    }

    private static String i() {
        if (TextUtils.isEmpty(f15522j)) {
            f15522j = com.jingdong.sdk.talos.a.h().x() ? "https://api.m.jd.com" : "https://beta-api.m.jd.com";
        }
        return f15522j;
    }

    public final void a() {
        super.start();
        if (this.f15523g == null) {
            this.f15523g = new a(getLooper());
        }
    }

    public final synchronized void b(int i2) {
        Handler handler = this.f15523g;
        if (handler != null) {
            if (!handler.hasMessages(1)) {
                this.f15523g.sendEmptyMessageDelayed(1, i2);
                this.f15523g.sendEmptyMessageDelayed(3, i2 + 30000);
                this.f15525i = false;
            } else if (this.f15525i) {
                this.f15523g.removeMessages(1);
                this.f15523g.removeMessages(3);
                this.f15523g.sendEmptyMessageDelayed(1, 0L);
                this.f15523g.sendEmptyMessageDelayed(3, Final.HALF_MINUTE);
                this.f15525i = false;
            }
        }
    }

    public final void f(String str, int i2) {
        if (this.f15523g != null) {
            Message message = new Message();
            message.what = 2;
            message.obj = str;
            message.arg1 = i2;
            this.f15523g.sendMessage(message);
        }
    }

    public final void g() {
        com.jingdong.sdk.talos.a.g();
        if (e.C0748e.b()) {
            b(0);
        }
    }
}
