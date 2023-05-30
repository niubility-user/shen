package com.jd.fireeye.security.fireeye;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.hardware.Sensor;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.fireeye.b.m;
import com.jd.fireeye.b.n;
import com.jd.fireeye.b.o;
import com.jd.fireeye.b.p;
import com.jd.fireeye.b.q;
import com.jd.fireeye.network.NetworkException;
import com.jd.fireeye.security.FireEyeInit;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jd.lib.productdetail.core.entitys.NoStockRecommendHead;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.connect.common.Constants;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class a {

    /* renamed from: k  reason: collision with root package name */
    private static String f2640k = "";

    /* renamed from: l  reason: collision with root package name */
    private static String f2641l = "";

    /* renamed from: m  reason: collision with root package name */
    private static final a f2642m = new a();

    /* renamed from: n  reason: collision with root package name */
    public static int f2643n = 100;
    private static l o;

    /* renamed from: c  reason: collision with root package name */
    private boolean f2644c;
    private long d;

    /* renamed from: e  reason: collision with root package name */
    private long f2645e;

    /* renamed from: f  reason: collision with root package name */
    private long f2646f;

    /* renamed from: g  reason: collision with root package name */
    private long f2647g;

    /* renamed from: h  reason: collision with root package name */
    private long f2648h;
    private Hashtable<String, Boolean> a = new Hashtable<>();
    private String b = "";

    /* renamed from: i  reason: collision with root package name */
    private boolean f2649i = false;

    /* renamed from: j  reason: collision with root package name */
    private Handler f2650j = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.fireeye.security.fireeye.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public class C0083a extends com.jd.fireeye.network.d {
        C0083a(a aVar, String str) {
            super(str);
        }

        @Override // com.jd.fireeye.network.d
        protected String a() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appkey", com.jd.fireeye.security.a.a());
                jSONObject.put("osversion", com.jd.fireeye.b.a.a());
                jSONObject.put("client", "android");
                jSONObject.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand());
                return jSONObject.toString();
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    /* loaded from: classes13.dex */
    class c implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ JSONObject b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ FireEyeCallback f2652c;

        c(boolean z, JSONObject jSONObject, FireEyeCallback fireEyeCallback) {
            this.a = z;
            this.b = jSONObject;
            this.f2652c = fireEyeCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean a = o.a("pnSwitch", false);
            if (!this.a || a) {
                a.this.d(this.b, this.f2652c);
            } else if (com.jd.fireeye.security.a.r()) {
                JSONObject g2 = com.jd.fireeye.security.a.g();
                try {
                    g2.put("reason", "\u624b\u673a\u53f7\u8865\u62a5\u4e8b\u4ef6\uff0c\u4f46\u5f00\u5173\u5173\u95ed");
                    g2.put("eventNumber", this.b.optString("eventNumber"));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_EventUpload_Failed", g2.toString(), "", "", "", "", "", "", null);
            }
        }
    }

    /* loaded from: classes13.dex */
    class d implements SwitchCallback {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ FireEyeCallback b;

        d(JSONObject jSONObject, FireEyeCallback fireEyeCallback) {
            this.a = jSONObject;
            this.b = fireEyeCallback;
        }

        @Override // com.jd.fireeye.security.fireeye.SwitchCallback
        public void onFail() {
            a.this.a(this.a, this.b, false, false);
        }

        @Override // com.jd.fireeye.security.fireeye.SwitchCallback
        public void onSuccess(boolean z, boolean z2) {
            a.this.a(this.a, this.b, z, z2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class e implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ boolean b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ JSONObject f2654c;
        final /* synthetic */ FireEyeCallback d;

        e(boolean z, boolean z2, JSONObject jSONObject, FireEyeCallback fireEyeCallback) {
            this.a = z;
            this.b = z2;
            this.f2654c = jSONObject;
            this.d = fireEyeCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.f2649i) {
                return;
            }
            a.this.f2649i = true;
            a aVar = a.this;
            aVar.b = aVar.a(this.a);
            if (!this.b) {
                try {
                    this.f2654c.put("phoneNumber", "");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            a.this.c(this.f2654c, this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class f implements FireEyeInit.b {
        final /* synthetic */ Handler a;
        final /* synthetic */ boolean b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ boolean f2656c;
        final /* synthetic */ JSONObject d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ FireEyeCallback f2657e;

        /* renamed from: com.jd.fireeye.security.fireeye.a$f$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        class RunnableC0084a implements Runnable {
            RunnableC0084a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = f.this;
                a aVar = a.this;
                aVar.b = aVar.a(fVar.b);
                f fVar2 = f.this;
                if (!fVar2.f2656c) {
                    try {
                        fVar2.d.put("phoneNumber", "");
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                f fVar3 = f.this;
                a.this.c(fVar3.d, fVar3.f2657e);
            }
        }

        f(Handler handler, boolean z, boolean z2, JSONObject jSONObject, FireEyeCallback fireEyeCallback) {
            this.a = handler;
            this.b = z;
            this.f2656c = z2;
            this.d = jSONObject;
            this.f2657e = fireEyeCallback;
        }

        @Override // com.jd.fireeye.security.FireEyeInit.b
        public void a(int i2) {
            if (a.this.f2649i) {
                return;
            }
            a.this.f2649i = true;
            this.a.post(new RunnableC0084a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class g implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ boolean b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ JSONObject f2659c;
        final /* synthetic */ FireEyeCallback d;

        g(boolean z, boolean z2, JSONObject jSONObject, FireEyeCallback fireEyeCallback) {
            this.a = z;
            this.b = z2;
            this.f2659c = jSONObject;
            this.d = fireEyeCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            aVar.b = aVar.a(this.a);
            if (!this.b) {
                try {
                    this.f2659c.put("phoneNumber", "");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            a.this.c(this.f2659c, this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class h extends com.jd.fireeye.network.d {

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ JSONObject f2661k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h(String str, JSONObject jSONObject) {
            super(str);
            this.f2661k = jSONObject;
        }

        @Override // com.jd.fireeye.network.d
        protected String a() {
            try {
                return a.this.a(this.f2661k).toString();
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class i extends com.jd.fireeye.network.f {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ FireEyeCallback b;

        i(JSONObject jSONObject, FireEyeCallback fireEyeCallback) {
            this.a = jSONObject;
            this.b = fireEyeCallback;
        }

        @Override // com.jd.fireeye.network.f
        public void a(com.jd.fireeye.network.e eVar) {
            a.this.a(eVar, this.a, this.b, true);
        }

        @Override // com.jd.fireeye.network.f
        public void a(NetworkException networkException) {
            FireEyeCallback fireEyeCallback = this.b;
            if (fireEyeCallback != null) {
                fireEyeCallback.onFail();
            }
            if (com.jd.fireeye.security.a.r()) {
                JSONObject g2 = com.jd.fireeye.security.a.g();
                try {
                    g2.put("duration", String.valueOf(System.currentTimeMillis() - a.this.d));
                    g2.put("reason", networkException);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Activation_Failed", g2.toString(), "", "", "", "", "", "", null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class j extends com.jd.fireeye.network.d {

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ JSONObject f2664k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(String str, JSONObject jSONObject) {
            super(str);
            this.f2664k = jSONObject;
        }

        @Override // com.jd.fireeye.network.d
        protected String a() {
            try {
                return a.this.b(this.f2664k).toString();
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class k extends com.jd.fireeye.network.f {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ FireEyeCallback b;

        k(JSONObject jSONObject, FireEyeCallback fireEyeCallback) {
            this.a = jSONObject;
            this.b = fireEyeCallback;
        }

        @Override // com.jd.fireeye.network.f
        public void a(com.jd.fireeye.network.e eVar) {
            a.this.a(eVar, this.a, this.b, false);
        }

        @Override // com.jd.fireeye.network.f
        public void a(NetworkException networkException) {
            FireEyeCallback fireEyeCallback = this.b;
            if (fireEyeCallback != null) {
                fireEyeCallback.onFail();
            }
            if (com.jd.fireeye.security.a.r()) {
                JSONObject g2 = com.jd.fireeye.security.a.g();
                try {
                    g2.put("reason", networkException);
                    g2.put("eventNumber", this.a.optString("eventNumber"));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_EventUpload_Failed", g2.toString(), "", "", "", "", "", "", null);
            }
        }
    }

    /* loaded from: classes13.dex */
    public interface l {
        void a(int i2);
    }

    private a() {
    }

    private String c() {
        return "";
    }

    private String d() {
        return com.jd.fireeye.security.a.q() ? "http://beta-firevent.jd.com/eventcollection" : "https://firevent.jd.com/eventcollection";
    }

    private String e() {
        return com.jd.fireeye.security.a.q() ? "https://beta-cpaact.m.jd.com/activate" : "https://fireactive.jd.com/activate";
    }

    private String f() {
        return com.jd.fireeye.security.a.q() ? "https://beta-cpaact.m.jd.com/activeSdkConfig" : "https://fireactive.jd.com/activeSdkConfig";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(JSONObject jSONObject, FireEyeCallback fireEyeCallback) {
        if (jSONObject == null) {
            com.jd.fireeye.b.f.a("JDMob.Security.FireEye", "event param is NULL!");
            if (com.jd.fireeye.security.a.r()) {
                JSONObject g2 = com.jd.fireeye.security.a.g();
                try {
                    g2.put("reason", "eventparam\u4e3a\u7a7a");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Activation_Failed", g2.toString(), "", "", "", "", "", "", null);
                return;
            }
            return;
        }
        f2640k = jSONObject.optString("phoneNumber", "");
        String a = com.jd.fireeye.security.a.a();
        h hVar = new h(e(), jSONObject);
        hVar.a((com.jd.fireeye.network.f) new i(jSONObject, fireEyeCallback));
        hVar.a(60000);
        hVar.a("ActiveRequest." + a + OrderISVUtil.MONEY_DECIMAL + System.currentTimeMillis());
        hVar.l();
        if (com.jd.fireeye.security.a.r()) {
            JSONObject g3 = com.jd.fireeye.security.a.g();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.f2647g = currentTimeMillis;
                g3.put("timeStamp", String.valueOf(currentTimeMillis));
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Activation_Start_Request", g3.toString(), "", "", "", "", "", "", null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(JSONObject jSONObject, FireEyeCallback fireEyeCallback) {
        if (jSONObject == null) {
            com.jd.fireeye.b.f.a("JDMob.Security.FireEye", "event param is NULL!");
            if (com.jd.fireeye.security.a.r()) {
                JSONObject g2 = com.jd.fireeye.security.a.g();
                try {
                    g2.put("requestEvent eventparam", "eventparam\u4e3a\u7a7a");
                    g2.put("eventNumber", jSONObject.optString("eventNumber"));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_EventUpload_Failed", g2.toString(), "", "", "", "", "", "", null);
                return;
            }
            return;
        }
        f2640k = jSONObject.optString("phoneNumber", "");
        String a = com.jd.fireeye.security.a.a();
        j jVar = new j(d(), jSONObject);
        jVar.a((com.jd.fireeye.network.f) new k(jSONObject, fireEyeCallback));
        jVar.a(60000);
        jVar.a("EventRequest." + a + OrderISVUtil.MONEY_DECIMAL + System.currentTimeMillis());
        jVar.l();
        if (com.jd.fireeye.security.a.r()) {
            JSONObject g3 = com.jd.fireeye.security.a.g();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.f2646f = currentTimeMillis;
                g3.put("timeStamp", String.valueOf(currentTimeMillis));
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_EventUpload_Start_Request", g3.toString(), "", "", "", "", "", "", null);
        }
    }

    public static a b() {
        return f2642m;
    }

    public void b(JSONObject jSONObject, FireEyeCallback fireEyeCallback) {
        if (jSONObject == null) {
            com.jd.fireeye.b.f.a("JDMob.Security.FireEye", "event param is NULL!");
            if (com.jd.fireeye.security.a.r()) {
                JSONObject g2 = com.jd.fireeye.security.a.g();
                try {
                    g2.put("reportFireEyeEvent eventparam", "eventparam\u4e3a\u7a7a");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_EventUpload_Failed", g2.toString(), "", "", "", "", "", "", null);
                return;
            }
            return;
        }
        this.f2645e = System.currentTimeMillis();
        com.jd.fireeye.security.a.a();
        String optString = jSONObject.optString("devicecode");
        if (!TextUtils.isEmpty(optString)) {
            com.jd.fireeye.security.a.c(optString);
        }
        this.f2644c = jSONObject.optBoolean("isAgreePrivacy");
        if (com.jd.fireeye.security.a.r()) {
            if (this.f2644c) {
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Privacy_Authed", com.jd.fireeye.security.a.g().toString(), "", "", "", "", "", "", null);
            } else {
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Privacy_UnAuth", com.jd.fireeye.security.a.g().toString(), "", "", "", "", "", "", null);
            }
        }
        if (!this.f2644c && !TextUtils.isEmpty(o.a("eventUuid", ""))) {
            if (com.jd.fireeye.security.a.r()) {
                JSONObject g3 = com.jd.fireeye.security.a.g();
                try {
                    g3.put("reason", "\u9690\u79c1\u534f\u8bae\u672a\u6388\u6743\u5e76\u4e14\u5df2\u6709eventUuid");
                    g3.put("eventNumber", jSONObject.optString("eventNumber"));
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_EventUpload_Failed", g3.toString(), "", "", "", "", "", "", null);
                return;
            }
            return;
        }
        boolean optBoolean = jSONObject.optBoolean("isPhoneEvent", false);
        if (optBoolean) {
            Boolean bool = this.a.get("hasSendpnRequest");
            if (bool != null && bool.booleanValue()) {
                if (com.jd.fireeye.security.a.r()) {
                    JSONObject g4 = com.jd.fireeye.security.a.g();
                    try {
                        g4.put("reason", "\u672c\u6b21\u542f\u52a8\u5df2\u4e0a\u62a5");
                        g4.put("eventNumber", jSONObject.optString("eventNumber"));
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                    com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_EventUpload_Failed", g4.toString(), "", "", "", "", "", "", null);
                    return;
                }
                return;
            }
            this.a.put("hasSendpnRequest", Boolean.TRUE);
        }
        Handler handler = this.f2650j;
        if (handler == null) {
            if (com.jd.fireeye.security.a.r()) {
                JSONObject g5 = com.jd.fireeye.security.a.g();
                try {
                    g5.put("reason", "handler \u672a\u521b\u5efa");
                    g5.put("eventNumber", jSONObject.optString("eventNumber"));
                } catch (JSONException e5) {
                    e5.printStackTrace();
                }
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_EventUpload_Failed", g5.toString(), "", "", "", "", "", "", null);
                return;
            }
            return;
        }
        handler.post(new c(optBoolean, jSONObject, fireEyeCallback));
    }

    public void a(JSONObject jSONObject, FireEyeCallback fireEyeCallback) {
        if (jSONObject == null) {
            com.jd.fireeye.b.f.a("JDMob.Security.FireEye", "event param is NULL!");
            if (com.jd.fireeye.security.a.r()) {
                JSONObject g2 = com.jd.fireeye.security.a.g();
                try {
                    g2.put("reason", "eventparam\u4e3a\u7a7a");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Activation_Failed", g2.toString(), "", "", "", "", "", "", null);
                return;
            }
            return;
        }
        this.f2644c = jSONObject.optBoolean("isAgreePrivacy");
        if (com.jd.fireeye.security.a.r()) {
            if (this.f2644c) {
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Privacy_Authed", com.jd.fireeye.security.a.g().toString(), "", "", "", "", "", "", null);
            } else {
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Privacy_UnAuth", com.jd.fireeye.security.a.g().toString(), "", "", "", "", "", "", null);
            }
        }
        if (this.f2644c) {
            String a = com.jd.fireeye.security.a.a();
            String optString = jSONObject.optString("devicecode");
            if (!TextUtils.isEmpty(optString)) {
                com.jd.fireeye.security.a.c(optString);
            }
            f2643n = 101;
            l lVar = o;
            if (lVar != null) {
                lVar.a(101);
            }
            String optString2 = jSONObject.optString("oaId");
            if (!TextUtils.isEmpty(optString2)) {
                com.jd.fireeye.security.a.b(optString2);
            }
            Boolean bool = this.a.get(a);
            if (bool == null || !bool.booleanValue()) {
                boolean z = false;
                if (!o.a(a, false)) {
                    this.a.put(a, Boolean.TRUE);
                    if (com.jd.fireeye.security.a.r()) {
                        JSONObject g3 = com.jd.fireeye.security.a.g();
                        long currentTimeMillis = System.currentTimeMillis();
                        this.d = currentTimeMillis;
                        try {
                            g3.put("timeStamp", String.valueOf(currentTimeMillis));
                        } catch (JSONException e3) {
                            e3.printStackTrace();
                        }
                        com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Activation_Upload", g3.toString(), "", "", "", "", "", "", null);
                    }
                    if (jSONObject.has("clipSwitch")) {
                        com.jd.fireeye.security.a.a(jSONObject.optBoolean("clipSwitch"));
                    }
                    if (o.a("hasGetSwitch", false)) {
                        boolean a2 = o.a("yodaIdSwitch", false);
                        boolean a3 = o.a("pnSwitch", false);
                        if (!a2 || com.jd.fireeye.security.a.p()) {
                            z = a2;
                        } else if (com.jd.fireeye.security.a.r()) {
                            JSONObject g4 = com.jd.fireeye.security.a.g();
                            try {
                                g4.put("status", false);
                                g4.put("reason", "\u521d\u59cb\u5316\u540c\u610f\u6fc0\u6d3b\u4e0d\u540c\u610f");
                                g4.put("isFirst", false);
                            } catch (JSONException e4) {
                                e4.printStackTrace();
                            }
                            com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_PasteBoard_Status", g4.toString(), "", "", "", "", "", "", null);
                        }
                        a(jSONObject, fireEyeCallback, z, a3);
                    } else if (com.jd.fireeye.security.a.o()) {
                        a(true, com.jd.fireeye.security.a.p(), (SwitchCallback) new d(jSONObject, fireEyeCallback));
                    } else {
                        a(jSONObject, fireEyeCallback, false, false);
                    }
                } else if (com.jd.fireeye.security.a.r()) {
                    com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Activation_Already", com.jd.fireeye.security.a.g().toString(), "", "", "", "", "", "", null);
                }
            }
        }
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        o.b("eventUuid", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject b(JSONObject jSONObject) {
        return com.jd.fireeye.b.b.b(b(jSONObject, false).toString());
    }

    private JSONObject b(JSONObject jSONObject, boolean z) {
        String a;
        try {
            if (com.jd.fireeye.b.f.a) {
                com.jd.fireeye.b.f.b("JDMob.Security.FireEye", String.format("active json param: \n%s", com.jd.fireeye.b.g.a(jSONObject.toString())));
            }
            JSONObject jSONObject2 = new JSONObject();
            a(jSONObject2, z);
            try {
                jSONObject2.put("appkey", com.jd.fireeye.security.a.a());
                jSONObject2.put("phoneNumber", jSONObject.optString("phoneNumber", ""));
                jSONObject2.put("gisinfo", jSONObject.optString("gisinfo"));
                jSONObject2.put("isFromOpenApp", jSONObject.optBoolean("isFromOpenApp"));
                if (!TextUtils.isEmpty(jSONObject.optString("pin"))) {
                    jSONObject2.put("pin", jSONObject.optString("pin"));
                }
                if (z) {
                    jSONObject2.put("eventUuid", o.a("eventUuid", ""));
                    jSONObject2.put("smallActiveUuid", o.a("smallActiveUuid", ""));
                    jSONObject2.put("rcode", a(com.jd.fireeye.security.a.a, z));
                    if (com.jd.fireeye.security.a.e() != null) {
                        jSONObject2.put("h5Info", com.jd.fireeye.security.a.e());
                    } else {
                        jSONObject2.put("h5Info", "");
                    }
                } else {
                    if (TextUtils.equals(com.jd.fireeye.security.a.a(), "a0adb0fc36e4e800c68ae764b344258f")) {
                        a = o.a("smallActiveUuid", "");
                    } else {
                        a = o.a("activeUuid", "");
                    }
                    if (com.jd.fireeye.security.a.r() && TextUtils.isEmpty(a)) {
                        JSONObject g2 = com.jd.fireeye.security.a.g();
                        g2.put("eventNumber", jSONObject.optString("eventNumber"));
                        com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_EventUpload_Without_EventUUID", g2.toString(), "", "", "", "", "", "", null);
                    }
                    jSONObject2.put("activeUuid", a);
                    if (!this.f2644c) {
                        jSONObject2.put("eventNumber", "noAgree");
                    } else {
                        jSONObject2.put("eventNumber", jSONObject.optString("eventNumber"));
                        if (!TextUtils.isEmpty(jSONObject.optString("eventNumber")) && jSONObject.optString("eventNumber").equals("powerOn")) {
                            com.jd.fireeye.b.g.a(jSONObject2, com.jd.fireeye.b.e.a(com.jd.fireeye.security.a.a));
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (z && com.jd.fireeye.security.a.r()) {
                b(com.jd.fireeye.security.a.a, jSONObject);
            }
            return jSONObject2;
        } catch (Exception e3) {
            e3.printStackTrace();
            return new JSONObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class b extends com.jd.fireeye.network.f {
        final /* synthetic */ boolean a;
        final /* synthetic */ boolean b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ SwitchCallback f2651c;

        b(boolean z, boolean z2, SwitchCallback switchCallback) {
            this.a = z;
            this.b = z2;
            this.f2651c = switchCallback;
        }

        @Override // com.jd.fireeye.network.f
        public void a(com.jd.fireeye.network.e eVar) {
            JSONObject a;
            String str;
            JSONObject g2;
            boolean z;
            boolean z2;
            try {
                if (com.jd.fireeye.security.a.r()) {
                    com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_PasteBoard_Network_End", "", "", "", "", "", "", "", null);
                }
                a = eVar.a();
                if (a != null) {
                    str = a.optString("code");
                    if (com.jd.fireeye.b.f.a) {
                        com.jd.fireeye.b.f.b("JDMob.Security.FireEye", String.format("fire switch response json: \n%s", com.jd.fireeye.b.g.a(a.toString())));
                    }
                } else {
                    str = "";
                }
                g2 = com.jd.fireeye.security.a.g();
            } catch (Exception unused) {
                return;
            }
            if (TextUtils.equals("0", str)) {
                if (com.jd.fireeye.b.f.a) {
                    com.jd.fireeye.b.f.b("JDMob.Security.FireEye", com.jd.fireeye.security.a.a() + " report success");
                }
                if (a != null) {
                    JSONObject optJSONObject = a.optJSONObject("data");
                    if (optJSONObject != null) {
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("sdkSwitchInfo");
                        if (optJSONObject2 != null) {
                            String optString = optJSONObject2.optString("H5TimeOut");
                            if (!TextUtils.isEmpty(optString)) {
                                com.jd.fireeye.security.a.a(optString);
                            }
                            com.jd.fireeye.security.a.c(optJSONObject2.optBoolean("openappSwitch", false));
                        }
                        z2 = optJSONObject.optBoolean("yodaIdSwitch");
                        if (z2 && !this.a) {
                            if (this.b) {
                                g2.put("reason", "\u6fc0\u6d3b\u7528\u6237\u4e0d\u540c\u610f");
                            } else {
                                g2.put("reason", "\u521d\u59cb\u5316\u7528\u6237\u4e0d\u540c\u610f");
                            }
                            z2 = false;
                        }
                        z = optJSONObject.optBoolean("pnSwitch");
                        o.b("hasGetSwitch", true);
                        o.b("yodaIdSwitch", z2);
                        o.b("pnSwitch", z);
                        if (com.jd.fireeye.security.a.r()) {
                            g2.put("status", z2);
                            g2.put("isFirst", !this.b);
                            com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_PasteBoard_Status", g2.toString(), "", "", "", "", "", "", null);
                            JSONObject g3 = com.jd.fireeye.security.a.g();
                            try {
                                long currentTimeMillis = System.currentTimeMillis();
                                g3.put("timeStamp", currentTimeMillis);
                                g3.put("duration", currentTimeMillis - a.this.f2648h);
                                g3.put("isFirst", !this.b);
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                            com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_PasteBoard_Success", g3.toString(), "", "", "", "", "", "", null);
                        }
                    } else {
                        if (com.jd.fireeye.security.a.r()) {
                            JSONObject g4 = com.jd.fireeye.security.a.g();
                            try {
                                g4.put("isFirst", !this.b);
                                g4.put("reason", "data is null");
                            } catch (JSONException e3) {
                                e3.printStackTrace();
                            }
                            com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_PasteBoard_Failed", g4.toString(), "", "", "", "", "", "", null);
                        }
                        z = false;
                        z2 = false;
                    }
                    return;
                }
                if (com.jd.fireeye.security.a.r()) {
                    JSONObject g5 = com.jd.fireeye.security.a.g();
                    try {
                        g5.put("isFirst", !this.b);
                        g5.put("reason", "response is null ");
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                    com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_PasteBoard_Failed", g5.toString(), "", "", "", "", "", "", null);
                }
                z = false;
                z2 = false;
            } else {
                if (com.jd.fireeye.security.a.r()) {
                    JSONObject g6 = com.jd.fireeye.security.a.g();
                    try {
                        g6.put("isFirst", !this.b);
                        g6.put("reason", str);
                    } catch (JSONException e5) {
                        e5.printStackTrace();
                    }
                    com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_PasteBoard_Failed", g6.toString(), "", "", "", "", "", "", null);
                }
                z = false;
                z2 = false;
            }
            boolean a2 = o.a(com.jd.fireeye.security.a.a(), false);
            if (0 != com.jd.fireeye.security.a.f() && !a2 && !this.b) {
                FireEyeInit.getH5Info(com.jd.fireeye.security.a.a, com.jd.fireeye.security.a.q());
            }
            SwitchCallback switchCallback = this.f2651c;
            if (switchCallback != null) {
                switchCallback.onSuccess(z2, z);
            }
        }

        @Override // com.jd.fireeye.network.f
        public void a(NetworkException networkException) {
            o.b("hasGetSwitch", false);
            o.b("yodaIdSwitch", false);
            o.b("pnSwitch", false);
            boolean a = o.a(com.jd.fireeye.security.a.a(), false);
            if (0 != com.jd.fireeye.security.a.f() && !a && !this.b) {
                FireEyeInit.getH5Info(com.jd.fireeye.security.a.a, com.jd.fireeye.security.a.q());
            }
            SwitchCallback switchCallback = this.f2651c;
            if (switchCallback != null) {
                switchCallback.onFail();
            }
            if (com.jd.fireeye.security.a.r()) {
                JSONObject g2 = com.jd.fireeye.security.a.g();
                try {
                    g2.put("status", false);
                    g2.put("reason", "\u83b7\u53d6\u5931\u8d25");
                    g2.put("isFirst", !this.b);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_PasteBoard_Status", g2.toString(), "", "", "", "", "", "", null);
                JSONObject g3 = com.jd.fireeye.security.a.g();
                try {
                    g3.put("isFirst", !this.b);
                    g3.put("reason", networkException);
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_PasteBoard_Failed", g3.toString(), "", "", "", "", "", "", null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject, FireEyeCallback fireEyeCallback, boolean z, boolean z2) {
        if (this.f2650j == null) {
            if (com.jd.fireeye.security.a.r()) {
                JSONObject g2 = com.jd.fireeye.security.a.g();
                try {
                    g2.put("duration", String.valueOf(System.currentTimeMillis() - this.d));
                    g2.put("reason", "handler \u672a\u521b\u5efa");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Activation_Failed", g2.toString(), "", "", "", "", "", "", null);
            }
        } else if (FireEyeInit.needGetH5Info()) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new e(z, z2, jSONObject, fireEyeCallback), com.jd.fireeye.security.a.f());
            FireEyeInit.setOnFinishGetH5InfoObserver(new f(handler, z, z2, jSONObject, fireEyeCallback));
        } else {
            this.f2650j.post(new g(z, z2, jSONObject, fireEyeCallback));
        }
    }

    public void a(boolean z, boolean z2, SwitchCallback switchCallback) {
        String a = com.jd.fireeye.security.a.a();
        C0083a c0083a = new C0083a(this, f());
        c0083a.a((com.jd.fireeye.network.f) new b(z2, z, switchCallback));
        c0083a.a(60000);
        c0083a.a("SwitchRequest." + a + OrderISVUtil.MONEY_DECIMAL + System.currentTimeMillis());
        c0083a.l();
        if (com.jd.fireeye.security.a.r()) {
            com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_PasteBoard_Network_Start", "", "", "", "", "", "", "", null);
            JSONObject g2 = com.jd.fireeye.security.a.g();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.f2648h = currentTimeMillis;
                g2.put("timeStamp", currentTimeMillis);
                g2.put("isFirst", !z);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_PasteBoard_Start_Request", g2.toString(), "", "", "", "", "", "", null);
        }
    }

    private void b(Context context, JSONObject jSONObject) {
        JSONObject g2 = com.jd.fireeye.security.a.g();
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(com.jd.fireeye.security.a.m())) {
            arrayList.add(Configuration.UNION_ID);
        }
        if (TextUtils.isEmpty(com.jd.fireeye.security.a.l())) {
            arrayList.add(Configuration.SUB_UNION_ID);
        }
        if (TextUtils.isEmpty(com.jd.fireeye.security.a.d())) {
            arrayList.add("devicecode");
        }
        if (TextUtils.isEmpty("4.4.4")) {
            arrayList.add("sdkverison");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.a.a())) {
            arrayList.add("osversion");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.a.b(context))) {
            arrayList.add("appversion");
        }
        if (TextUtils.isEmpty("android")) {
            arrayList.add("clientos");
        }
        if (TextUtils.isEmpty(BaseInfo.getDeviceBrand())) {
            arrayList.add(JDNetCacheManager.BRAND_BIZKEY);
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.j.a())) {
            arrayList.add("originalsubunionId");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.security.a.i())) {
            arrayList.add(Configuration.PARTNER);
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.i.b(context))) {
            arrayList.add("networkinfo");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.security.a.c())) {
            arrayList.add("installtionid");
        }
        if (TextUtils.isEmpty(p.a(context))) {
            arrayList.add("androidId");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.l.k(context))) {
            arrayList.add("ua");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.security.a.h())) {
            arrayList.add("oaId");
        }
        if (0 == BaseInfo.getAppFirstInstallTime()) {
            arrayList.add("appInstallTime");
        }
        if (0 == BaseInfo.getAppLastUpdateTime()) {
            arrayList.add("appUpdateTime");
        }
        if (com.jd.fireeye.security.a.a != null) {
            HashMap a = com.jd.fireeye.security.b.a(context);
            if (a.get("referrer") == null) {
                arrayList.add("smartChannelId");
            }
            if (a.get("clicktime") == null) {
                arrayList.add("smartClickTime");
            }
            if (a.get("installtime") == null) {
                arrayList.add("smartInstallTime");
            }
        }
        if (TextUtils.isEmpty(com.jd.fireeye.security.a.a())) {
            arrayList.add("appkey");
        }
        if (TextUtils.isEmpty(jSONObject.optString("gisinfo"))) {
            arrayList.add("gisinfo");
        }
        if (TextUtils.isEmpty(jSONObject.optString("pin"))) {
            arrayList.add("pin");
        }
        if (com.jd.fireeye.b.a.a(context) == 0) {
            arrayList.add(HybridSDK.APP_VERSION_CODE);
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.l.g(context))) {
            arrayList.add("screen");
        }
        if (TextUtils.isEmpty(p.e(context))) {
            arrayList.add("uuid");
        }
        if (TextUtils.isEmpty(BaseInfo.getOSVersionTags())) {
            arrayList.add("tags");
        }
        if (TextUtils.isEmpty(BaseInfo.getBoard())) {
            arrayList.add("board");
        }
        if (TextUtils.isEmpty(BaseInfo.getBootloaderVersion())) {
            arrayList.add("bootloader");
        }
        if (TextUtils.isEmpty(BaseInfo.getOSName())) {
            arrayList.add(ViewProps.DISPLAY);
        }
        if (TextUtils.isEmpty(BaseInfo.getOSFingerprint())) {
            arrayList.add("fingerprint");
        }
        if (TextUtils.isEmpty(BaseInfo.getHardwareName())) {
            arrayList.add("hardware");
        }
        if (BaseInfo.getAndroidSDKVersion() == 0) {
            arrayList.add("sdkLevel");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.l.h()) || TextUtils.equals(com.jd.fireeye.b.l.h(), "unknow")) {
            arrayList.add("sdCid");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.l.b(context)) || TextUtils.equals(com.jd.fireeye.b.l.b(context), "unknow")) {
            arrayList.add("freeDiskSpace");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.l.i(context)) || TextUtils.equals(com.jd.fireeye.b.l.i(context), "unknow")) {
            arrayList.add("totalDiskSpace");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.l.j(context))) {
            arrayList.add("memSize");
        }
        if (TextUtils.isEmpty(p.b(context))) {
            arrayList.add("btMac");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.l.c())) {
            arrayList.add("maxCpuFrequency");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.l.d())) {
            arrayList.add("minCpuFrequency");
        }
        if (TextUtils.equals(com.jd.fireeye.b.l.e(), "unknow")) {
            arrayList.add("cpuType");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.i.a(context))) {
            arrayList.add("carrierName");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.l.g())) {
            arrayList.add("ipAddress");
        }
        if (TextUtils.isEmpty(BaseInfo.getDeviceModel())) {
            arrayList.add(CustomThemeConstance.NAVI_MODEL);
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.l.e(context))) {
            arrayList.add("isoCountryCode");
        }
        if (TextUtils.isEmpty(BaseInfo.getAppPackageName())) {
            arrayList.add("appBundleIdentifier");
        }
        if (TextUtils.isEmpty(com.jd.fireeye.b.l.b())) {
            arrayList.add("cpuFrequency");
        }
        if (arrayList.isEmpty()) {
            return;
        }
        try {
            g2.put("devinfo", arrayList);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_DeviceInfo_Failed", g2.toString(), "", "", "", "", "", "", null);
    }

    private void a(String str) {
        o.b(com.jd.fireeye.security.a.a(), true);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (TextUtils.equals(com.jd.fireeye.security.a.a(), "a0adb0fc36e4e800c68ae764b344258f")) {
            o.b("smallActiveUuid", str);
        } else {
            o.b("activeUuid", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.jd.fireeye.network.e eVar, JSONObject jSONObject, FireEyeCallback fireEyeCallback, boolean z) {
        String str;
        try {
            JSONObject a = eVar.a();
            boolean z2 = true;
            if (a != null) {
                str = a.optString("code");
                if (com.jd.fireeye.b.f.a) {
                    com.jd.fireeye.b.f.b("JDMob.Security.FireEye", String.format("fire report response json: \n%s", com.jd.fireeye.b.g.a(a.toString())));
                }
            } else {
                str = "";
            }
            if (TextUtils.equals("0", str) && a != null) {
                if (com.jd.fireeye.b.f.a) {
                    com.jd.fireeye.b.f.b("JDMob.Security.FireEye", com.jd.fireeye.security.a.a() + " report success");
                }
                if (z) {
                    a(a.optString("activeUuid"));
                    if (!TextUtils.isEmpty(a.optString("touchstone_expids"))) {
                        o.b("touchstone_expids", a.optString("touchstone_expids"));
                    }
                    a.put("openappSwitch", com.jd.fireeye.security.a.s());
                } else if (!this.f2644c) {
                    b(a.optString("eventUuid"));
                }
                if (!TextUtils.isEmpty(f2640k)) {
                    String str2 = com.jd.fireeye.security.a.a() + "pn";
                    f2641l = str2;
                    String a2 = o.a(str2, "");
                    String a3 = q.a(f2640k);
                    String[] split = a2.split(DYConstants.DY_REGEX_COMMA);
                    int i2 = 0;
                    while (true) {
                        if (i2 >= split.length) {
                            z2 = false;
                            break;
                        } else if (TextUtils.equals(split[i2], a3)) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z2) {
                        o.b(f2641l, a2 + DYConstants.DY_REGEX_COMMA + a3);
                    }
                }
                if (fireEyeCallback != null) {
                    if (fireEyeCallback instanceof DeepLinkFireEyeCallback) {
                        ((DeepLinkFireEyeCallback) fireEyeCallback).onSuccess(a);
                    } else {
                        fireEyeCallback.onSuccess();
                    }
                }
                if (com.jd.fireeye.security.a.r()) {
                    JSONObject g2 = com.jd.fireeye.security.a.g();
                    if (z) {
                        if (jSONObject.optBoolean("isFromOpenApp", false)) {
                            com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_DeepLink_OpenApp_Failed", com.jd.fireeye.security.a.g().toString(), "", "", "", "", "", "", null);
                        }
                        if (a.has("openapp")) {
                            String optString = a.optString("openapp");
                            if (!TextUtils.isEmpty(optString)) {
                                JSONObject g3 = com.jd.fireeye.security.a.g();
                                g3.put("url", optString);
                                if (optString.contains("://")) {
                                    com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_DeepLink_Valid", g3.toString(), "", "", "", "", "", "", null);
                                } else {
                                    com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_DeepLink_Invalid", g3.toString(), "", "", "", "", "", "", null);
                                }
                            } else {
                                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_DeepLink_Null", com.jd.fireeye.security.a.g().toString(), "", "", "", "", "", "", null);
                            }
                        } else {
                            com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_DeepLink_Get_Failed", com.jd.fireeye.security.a.g().toString(), "", "", "", "", "", "", null);
                        }
                        long currentTimeMillis = System.currentTimeMillis();
                        long j2 = currentTimeMillis - this.f2647g;
                        g2.put("timeStamp", String.valueOf(currentTimeMillis));
                        g2.put("duration", String.valueOf(currentTimeMillis - this.d));
                        g2.put("netDuration", String.valueOf(j2));
                        com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Activation_Success", g2.toString(), "", "", "", "", "", "", null);
                        return;
                    }
                    long currentTimeMillis2 = System.currentTimeMillis();
                    long j3 = currentTimeMillis2 - this.f2645e;
                    g2.put("timeStamp", String.valueOf(currentTimeMillis2));
                    g2.put("duration", String.valueOf(j3));
                    g2.put("eventNumber", jSONObject.optString("eventNumber"));
                    com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_EventUpload_Success", g2.toString(), "", "", "", "", "", "", null);
                    return;
                }
                return;
            }
            if (com.jd.fireeye.b.f.a) {
                com.jd.fireeye.b.f.a("JDMob.Security.FireEye", com.jd.fireeye.security.a.a() + " report failed");
            }
            if (fireEyeCallback != null) {
                fireEyeCallback.onFail();
            }
            if (com.jd.fireeye.security.a.r()) {
                JSONObject g4 = com.jd.fireeye.security.a.g();
                if (z) {
                    g4.put("duration", String.valueOf(System.currentTimeMillis() - this.d));
                    g4.put("code", str);
                    g4.put("msg", a);
                    com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Activation_API_Failed", g4.toString(), "", "", "", "", "", "", null);
                    return;
                }
                g4.put("code", str);
                g4.put("msg", a);
                g4.put("eventNumber", jSONObject.optString("eventNumber"));
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_EventUpload_API_Failed", g4.toString(), "", "", "", "", "", "", null);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject a(JSONObject jSONObject) {
        return com.jd.fireeye.b.b.b(b(jSONObject, true).toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(boolean z) {
        String charSequence;
        if (z && this.f2644c && FireEyeInit.isAppForeground()) {
            try {
                ClipboardManager clipboardManager = (ClipboardManager) com.jd.fireeye.security.a.a.getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD);
                if (clipboardManager != null) {
                    ClipData primaryClip = clipboardManager.getPrimaryClip();
                    if (primaryClip == null) {
                        return "-1";
                    }
                    if (primaryClip.getItemCount() > 0) {
                        for (int i2 = 0; i2 < primaryClip.getItemCount(); i2++) {
                            ClipData.Item itemAt = primaryClip.getItemAt(i2);
                            if (itemAt != null && itemAt.getText() != null && (charSequence = itemAt.getText().toString()) != null && charSequence.length() == 34 && charSequence.charAt(0) == charSequence.charAt(33)) {
                                return charSequence;
                            }
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    private void a(JSONObject jSONObject, boolean z) throws JSONException {
        jSONObject.put(Configuration.UNION_ID, com.jd.fireeye.security.a.m());
        jSONObject.put(Configuration.SUB_UNION_ID, com.jd.fireeye.security.a.l());
        String d2 = com.jd.fireeye.security.a.d();
        if (TextUtils.isEmpty(d2)) {
            jSONObject.put("devicecode", p.a());
        } else {
            jSONObject.put("devicecode", d2);
        }
        jSONObject.put("sdkverison", "4.4.4");
        jSONObject.put("osversion", com.jd.fireeye.b.a.a());
        jSONObject.put("appversion", com.jd.fireeye.b.a.b(com.jd.fireeye.security.a.a));
        jSONObject.put("clientos", "android");
        jSONObject.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand());
        jSONObject.put("idfa", "");
        jSONObject.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
        jSONObject.put("currenttime", q.a());
        jSONObject.put("originalsubunionId", com.jd.fireeye.b.j.a());
        jSONObject.put("imei", "");
        jSONObject.put(Constant.KEY_MAC, c());
        jSONObject.put("imsi", "");
        jSONObject.put("imeiAndMeid", "");
        jSONObject.put(Configuration.PARTNER, com.jd.fireeye.security.a.i());
        jSONObject.put("networkinfo", com.jd.fireeye.b.i.b(com.jd.fireeye.security.a.a));
        jSONObject.put("installtionid", com.jd.fireeye.security.a.c());
        String a = p.a(com.jd.fireeye.security.a.a);
        if (TextUtils.isEmpty(a)) {
            jSONObject.put("androidId", p.a());
        } else {
            jSONObject.put("androidId", a);
        }
        jSONObject.put("ua", com.jd.fireeye.b.l.k(com.jd.fireeye.security.a.a));
        jSONObject.put("oaId", com.jd.fireeye.security.a.h());
        if (z) {
            jSONObject.put("yodaId", this.b);
        } else {
            jSONObject.put("yodaId", "");
        }
        jSONObject.put("appInstallTime", BaseInfo.getAppFirstInstallTime());
        jSONObject.put("appUpdateTime", BaseInfo.getAppLastUpdateTime());
        Context context = com.jd.fireeye.security.a.a;
        if (context != null) {
            HashMap a2 = com.jd.fireeye.security.b.a(context);
            jSONObject.put("smartChannelId", a2.get("referrer"));
            jSONObject.put("smartClickTime", a2.get("clicktime"));
            jSONObject.put("smartInstallTime", a2.get("installtime"));
        }
    }

    @SuppressLint({"NewApi"})
    private JSONObject a(Context context, boolean z) throws Exception {
        JSONObject jSONObject = new JSONObject();
        String e2 = p.e(context);
        jSONObject.put("deviceId", "");
        jSONObject.put("client", "android");
        jSONObject.put(HybridSDK.APP_VERSION, com.jd.fireeye.b.a.b(context));
        jSONObject.put(HybridSDK.OS_VERSION, com.jd.fireeye.b.a.a());
        jSONObject.put(HybridSDK.APP_VERSION_CODE, com.jd.fireeye.b.a.a(context) + "");
        jSONObject.put("screen", com.jd.fireeye.b.l.g(context));
        jSONObject.put("uuid", e2);
        jSONObject.put("androidId", p.a(com.jd.fireeye.security.a.a));
        jSONObject.put("openudid", "");
        jSONObject.put("networkInfo", com.jd.fireeye.b.i.b(context));
        jSONObject.put("isQEmuDriverExist", BaseInfo.isQEmuDriverFile());
        jSONObject.put("isPipeExist", BaseInfo.checkPipes());
        jSONObject.put("tags", TextUtils.isEmpty(BaseInfo.getOSVersionTags()) ? "" : BaseInfo.getOSVersionTags());
        jSONObject.put("board", BaseInfo.getBoard());
        jSONObject.put("bootloader", BaseInfo.getBootloaderVersion());
        jSONObject.put(NoStockRecommendHead.DEVICE, "");
        jSONObject.put(ViewProps.DISPLAY, BaseInfo.getOSName());
        jSONObject.put("fingerprint", BaseInfo.getOSFingerprint());
        jSONObject.put("hardware", BaseInfo.getHardwareName());
        jSONObject.put("sdkLevel", BaseInfo.getAndroidSDKVersion());
        jSONObject.put("sdCid", com.jd.fireeye.b.l.h());
        jSONObject.put("freeDiskSpace", com.jd.fireeye.b.l.b(context));
        jSONObject.put("totalDiskSpace", com.jd.fireeye.b.l.i(context));
        jSONObject.put("memSize", com.jd.fireeye.b.l.j(context));
        jSONObject.put("btMac", p.b(context));
        jSONObject.put("imei", "");
        jSONObject.put("wifiMac", c());
        jSONObject.put("imsi", "");
        jSONObject.put("imeiAndMeid", "");
        jSONObject.put("maxCpuFrequency", com.jd.fireeye.b.l.c());
        jSONObject.put("minCpuFrequency", com.jd.fireeye.b.l.d());
        jSONObject.put("cpuType", com.jd.fireeye.b.l.e());
        jSONObject.put("carrierName", com.jd.fireeye.b.i.a(context));
        jSONObject.put("phoneNumber", com.jd.fireeye.b.l.f(context));
        StringBuilder sb = new StringBuilder();
        List<Sensor> h2 = com.jd.fireeye.b.l.h(context);
        if (h2 != null && h2.size() > 0) {
            for (int i2 = 0; i2 < h2.size() && i2 < 10; i2++) {
                Sensor sensor = h2.get(i2);
                sb.append(sensor.getName() + DYConstants.DY_REGEX_COMMA + sensor.getResolution() + DYConstants.DY_REGEX_COMMA + sensor.getVendor());
                sb.append(DYConstants.DY_REGEX_AT);
            }
        }
        if (sb.length() > 0) {
            jSONObject.put("sensors", sb.deleteCharAt(sb.length() - 1).toString());
        }
        jSONObject.put("ipAddress", com.jd.fireeye.b.l.g());
        jSONObject.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
        jSONObject.put("mobileCountryCode", com.jd.fireeye.b.l.c(context));
        jSONObject.put("mobileNetworkCode", com.jd.fireeye.b.l.d(context));
        jSONObject.put("isoCountryCode", com.jd.fireeye.b.l.e(context));
        jSONObject.put("appBundleIdentifier", BaseInfo.getAppPackageName());
        jSONObject.put(Constants.PARAM_PLATFORM, BaseInfo.getDeviceModel());
        jSONObject.put("deviceName", "");
        jSONObject.put("currentTime", q.a());
        jSONObject.put("serial", com.jd.fireeye.b.l.i());
        jSONObject.put("simSerialNumber", "");
        jSONObject.put("physicalCpu", com.jd.fireeye.b.l.f());
        jSONObject.put("isRoot", com.jd.fireeye.b.l.j());
        jSONObject.put("rootConfirm", n.d());
        jSONObject.put("rootSuspicious", n.c(context));
        jSONObject.put("cpuFrequency", com.jd.fireeye.b.l.b());
        jSONObject.put("imeiPermission", false);
        jSONObject.put("oaId", com.jd.fireeye.security.a.h());
        a(context, jSONObject);
        com.jd.fireeye.b.g.a(jSONObject, com.jd.fireeye.b.h.b().a(), m.a(context), com.jd.fireeye.b.e.a(context));
        return jSONObject;
    }

    private void a(Context context, JSONObject jSONObject) {
        if (jSONObject == null || context == null) {
            return;
        }
        try {
            com.jd.fireeye.a.a.a(new com.jd.fireeye.a.b().a().a(context).a(false).a());
            jSONObject.put("vapp", com.jd.fireeye.a.a.f());
            jSONObject.put("slan", q.b("ro.product.locale"));
            jSONObject.put("ulan", q.b("persist.sys.locale"));
            jSONObject.put("lockAwakeReceiver", com.jd.fireeye.a.a.a());
            jSONObject.put("lach", com.jd.fireeye.a.a.e());
            jSONObject.put("batteryVoltage", com.jd.fireeye.a.a.c());
            jSONObject.put("batteryHealth", com.jd.fireeye.a.a.b());
            jSONObject.put("wifiEnable", q.a(context));
            jSONObject.put("emulator", com.jd.fireeye.a.a.k());
            jSONObject.put("isHooked", com.jd.fireeye.a.a.l());
            jSONObject.put("isMoreOpen", com.jd.fireeye.a.a.o());
            jSONObject.put("isDebug", com.jd.fireeye.a.a.j());
            jSONObject.put("isCloudEnv", com.jd.fireeye.a.a.i());
            jSONObject.put("isModifier", com.jd.fireeye.a.a.n());
            jSONObject.put("isMalicious", com.jd.fireeye.a.a.m());
            jSONObject.put("ifPad", com.jd.fireeye.a.a.h());
            jSONObject.put("sensorsList", com.jd.fireeye.a.a.g());
            jSONObject.put("currentVolume", com.jd.fireeye.a.a.d());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static boolean a() {
        return f2643n != 100;
    }

    public static void a(l lVar) {
        o = lVar;
    }
}
