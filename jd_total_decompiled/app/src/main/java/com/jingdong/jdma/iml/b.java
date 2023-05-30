package com.jingdong.jdma.iml;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.WebView;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdma.JDMaInterface;
import com.jingdong.jdma.common.utils.LogUtil;
import com.jingdong.jdma.common.utils.g;
import com.jingdong.jdma.common.utils.h;
import com.jingdong.jdma.common.utils.k;
import com.jingdong.jdma.common.utils.l;
import com.jingdong.jdma.common.utils.m;
import com.jingdong.jdma.common.utils.n;
import com.jingdong.jdma.iml.a;
import com.jingdong.jdma.minterface.AppMode;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.jdma.minterface.ClickInterfaceParam;
import com.jingdong.jdma.minterface.CustomInterfaceParam;
import com.jingdong.jdma.minterface.ExposureInterfaceParam;
import com.jingdong.jdma.minterface.MaInitCommonInfo;
import com.jingdong.jdma.minterface.OrderInterfaceParam;
import com.jingdong.jdma.minterface.PropertyInterfaceParam;
import com.jingdong.jdma.minterface.PvInterfaceParam;
import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class b {
    private static com.jingdong.jdma.e.b a = null;
    private static g b = null;

    /* renamed from: c  reason: collision with root package name */
    private static MaInitCommonInfo f12773c = null;
    private static String d = "";

    /* renamed from: e  reason: collision with root package name */
    private static String f12774e;

    /* renamed from: f  reason: collision with root package name */
    private static String f12775f;

    /* renamed from: g  reason: collision with root package name */
    private static Application f12776g;

    /* renamed from: h  reason: collision with root package name */
    private static com.jingdong.jdma.g.a f12777h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class a implements a.InterfaceC0495a {
        a() {
        }

        @Override // com.jingdong.jdma.iml.a.InterfaceC0495a
        public void a(HashMap<String, String> hashMap, String str) {
            hashMap.put("apc", b.f12773c.appc);
            hashMap.put("apv", b.f12773c.appv);
            hashMap.put("bld", b.f12773c.build);
            hashMap.put("jdv", b.b.A);
            b.b(b.f12776g, hashMap, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.jdma.iml.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static class C0496b extends com.jingdong.jdma.iml.d {
        final /* synthetic */ Context b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f12778c;
        final /* synthetic */ HashMap d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0496b(HashMap hashMap, Context context, String str, HashMap hashMap2) {
            super(hashMap);
            this.b = context;
            this.f12778c = str;
            this.d = hashMap2;
        }

        @Override // com.jingdong.jdma.iml.d
        public void a(HashMap<String, String> hashMap) {
            String b = h.b(this.b);
            com.jingdong.jdma.common.utils.c.f12682l = b;
            hashMap.put("nty", b);
            int i2 = 0;
            try {
                i2 = m.a(hashMap).getBytes("utf-8").length / 1024;
                LogUtil.w("JDMA_Impl", "kbSize:" + i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (com.jingdong.jdma.common.utils.d.c().f()) {
                com.jingdong.jdma.common.utils.d.c().a(hashMap);
            } else if (i2 > 100) {
                b.c(hashMap, this.f12778c);
                LogUtil.w("JDMA_Impl", "sendDataWithSave " + i2);
                if (com.jingdong.jdma.f.c.d) {
                    com.jingdong.jdma.f.c.a().a("JDMA_Impl", "kdSize : " + i2);
                }
            } else if (!hashMap.containsKey(BaseEvent.SCENE) || !"quick".equals(hashMap.get(BaseEvent.SCENE)) || !com.jingdong.jdma.h.d.e().i().s().equals("1")) {
                if (b.a != null) {
                    b.a.a(hashMap, this.f12778c);
                }
            } else {
                b.d(this.d, this.f12778c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class c implements com.jingdong.jdma.f.b {
        final /* synthetic */ HashMap a;
        final /* synthetic */ String b;

        c(HashMap hashMap, String str) {
            this.a = hashMap;
            this.b = str;
        }

        @Override // com.jingdong.jdma.f.b
        public void a() {
        }

        @Override // com.jingdong.jdma.f.b
        public void a(int i2) {
        }

        @Override // com.jingdong.jdma.f.b
        public void a(com.jingdong.jdma.bean.d.a aVar) {
        }

        @Override // com.jingdong.jdma.f.b
        public void a(com.jingdong.jdma.bean.d.a aVar, com.jingdong.jdma.bean.b.c.a aVar2) {
            if (b.a != null) {
                b.a.a(this.a, this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class d implements com.jingdong.jdma.f.b {
        d() {
        }

        @Override // com.jingdong.jdma.f.b
        public void a() {
        }

        @Override // com.jingdong.jdma.f.b
        public void a(int i2) {
        }

        @Override // com.jingdong.jdma.f.b
        public void a(com.jingdong.jdma.bean.d.a aVar) {
        }

        @Override // com.jingdong.jdma.f.b
        public void a(com.jingdong.jdma.bean.d.a aVar, com.jingdong.jdma.bean.b.c.a aVar2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class e implements Runnable {
        final /* synthetic */ String a;

        e(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (b.a != null) {
                b.a.b(this.a, "h5");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class f {
        f() {
        }

        @JavascriptInterface
        public String JDMAGetMPageParam() {
            return b.a();
        }

        @JavascriptInterface
        public void JDMASetMPageParam(String str) {
            b.e(str);
        }
    }

    static /* synthetic */ String a() {
        return j();
    }

    public static synchronized void f() {
        synchronized (b.class) {
            g gVar = b;
            if (gVar == null) {
                return;
            }
            gVar.a = "";
            gVar.b = "";
            gVar.f12697c = "";
            gVar.a(new String[]{"mba_muid", "mba_sid", "m_source"});
        }
    }

    public static synchronized void g(String str) {
        synchronized (b.class) {
            g gVar = b;
            if (gVar == null) {
                return;
            }
            gVar.g(str);
        }
    }

    private static void h(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!TextUtils.isEmpty(jSONObject.optString("psn")) && !TextUtils.isEmpty(jSONObject.optString("psq")) && jSONObject.optString("psn").contains("|")) {
                String optString = jSONObject.optString("psn");
                long longValue = Long.valueOf(optString.substring(optString.indexOf("|") + 1)).longValue();
                long longValue2 = Long.valueOf(jSONObject.optString("psq")).longValue();
                com.jingdong.jdma.g.a aVar = f12777h;
                if (aVar != null) {
                    if (longValue > aVar.g()) {
                        f12777h.d(longValue);
                        f12777h.c(longValue2);
                        Application application = f12776g;
                        if (application != null) {
                            l.a(application).b("bigdata_open_count", "" + f12777h.g());
                        }
                    } else if (longValue == f12777h.g() && longValue2 > f12777h.f()) {
                        f12777h.c(longValue2);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static int i() {
        Application application = f12776g;
        if (application == null) {
            return 1;
        }
        String b2 = h.b(application);
        b2.hashCode();
        char c2 = '\uffff';
        switch (b2.hashCode()) {
            case R2.attr.re_pstsTabPaddingLeftRight /* 1653 */:
                if (b2.equals("2g")) {
                    c2 = 0;
                    break;
                }
                break;
            case R2.attr.right2_text /* 1684 */:
                if (b2.equals("3g")) {
                    c2 = 1;
                    break;
                }
                break;
            case R2.attr.roundwidth /* 1715 */:
                if (b2.equals("4g")) {
                    c2 = 2;
                    break;
                }
                break;
            case R2.attr.select_indicator_height /* 1746 */:
                if (b2.equals("5g")) {
                    c2 = 3;
                    break;
                }
                break;
            case 3649301:
                if (b2.equals("wifi")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return com.jingdong.jdma.h.d.e().c().a();
            case 1:
                return com.jingdong.jdma.h.d.e().c().c();
            case 2:
                return com.jingdong.jdma.h.d.e().c().e();
            case 3:
                return com.jingdong.jdma.h.d.e().c().g();
            case 4:
                return com.jingdong.jdma.h.d.e().c().i();
            default:
                return 1;
        }
    }

    private static String j() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid", com.jingdong.jdma.bean.a.b().d());
            jSONObject.put("std", com.jingdong.jdma.bean.a.b().c());
            com.jingdong.jdma.g.a aVar = f12777h;
            jSONObject.put("vts", aVar != null ? aVar.g() : 0L);
            com.jingdong.jdma.g.a aVar2 = f12777h;
            jSONObject.put("seq", aVar2 != null ? aVar2.f() : 0L);
            jSONObject.put("mode_tag", com.jingdong.jdma.common.utils.c.q);
            jSONObject.put("aid", m.a(k.a(null)));
            jSONObject.put("oaid", m.a(com.jingdong.jdma.common.utils.c.f12683m));
            MaInitCommonInfo maInitCommonInfo = f12773c;
            if (maInitCommonInfo != null) {
                jSONObject.put("installationId", m.a(maInitCommonInfo.getInstallationId()));
            }
            g gVar = b;
            if (gVar != null) {
                jSONObject.put("jdv", gVar.A);
            }
            String str = d;
            if (str != null) {
                jSONObject.put("ctp", str);
            }
            String str2 = f12774e;
            if (str2 != null) {
                jSONObject.put("par", str2);
            }
            String str3 = f12775f;
            if (str3 != null) {
                jSONObject.put("event_id", str3);
            }
            if (f12776g != null && com.jingdong.jdma.common.utils.d.c().f()) {
                jSONObject.put("debugId", com.jingdong.jdma.common.utils.d.c().a());
                jSONObject.put("debugSiteId", com.jingdong.jdma.common.utils.d.c().e());
                jSONObject.put("debugReportDomain", com.jingdong.jdma.common.utils.d.c().b());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (LogUtil.isDebug()) {
            LogUtil.d("getInternalMPageParam", "----jsonObject=" + jSONObject.toString());
        }
        return jSONObject.toString();
    }

    public static String k() {
        g gVar = b;
        return gVar != null ? gVar.f12699f : "";
    }

    public static String l() {
        g gVar = b;
        return gVar != null ? gVar.A : "";
    }

    public static long m() {
        com.jingdong.jdma.g.a aVar = f12777h;
        if (aVar != null) {
            return aVar.d();
        }
        return 0L;
    }

    public static long n() {
        com.jingdong.jdma.g.a aVar = f12777h;
        if (aVar != null) {
            return aVar.e();
        }
        return 0L;
    }

    public static String o() {
        g gVar = b;
        return (gVar == null || TextUtils.isEmpty(gVar.d)) ? "" : b.d;
    }

    public static String p() {
        g gVar = b;
        return (gVar == null || TextUtils.isEmpty(gVar.f12698e)) ? "" : b.f12698e;
    }

    public static String q() {
        g gVar = b;
        return gVar != null ? gVar.y : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(HashMap<String, String> hashMap, String str) {
        try {
            com.jingdong.jdma.f.g.a(com.jingdong.jdma.h.d.e().h(), hashMap, new d(), true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(HashMap<String, String> hashMap, String str) {
        try {
            com.jingdong.jdma.f.g.a(com.jingdong.jdma.h.d.e().h(), hashMap, new c(hashMap, str), true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str) {
        g gVar = b;
        if (gVar != null) {
            gVar.f(str);
        }
        h(str);
    }

    public static void b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("pv_sid");
            String optString2 = jSONObject.optString("pv_seq");
            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                long longValue = Long.valueOf(optString).longValue();
                long longValue2 = Long.valueOf(optString2).longValue();
                com.jingdong.jdma.g.a aVar = f12777h;
                if (aVar != null) {
                    if (longValue > aVar.e()) {
                        f12777h.b(longValue);
                        f12777h.a(longValue2);
                        l.a(context).b("open_count", "" + f12777h.e());
                    } else if (longValue == f12777h.e() && longValue2 > f12777h.d()) {
                        f12777h.a(longValue2);
                    }
                }
            }
            if (jSONObject.has("ref")) {
                d = jSONObject.optString("ref");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void g() {
        try {
            com.jingdong.jdma.e.b bVar = a;
            if (bVar != null) {
                bVar.b();
                a = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.jingdong.jdma.common.utils.c.b.remove(str);
    }

    public static void d(String str) {
        g gVar = b;
        if (gVar != null) {
            gVar.e(str);
        }
        h(str);
    }

    public static synchronized void a(Context context, String str) {
        synchronized (b.class) {
            if (context != null) {
                g gVar = b;
                if (gVar != null) {
                    gVar.h(str);
                }
            }
        }
    }

    public static void f(String str) {
        g gVar = b;
        if (gVar != null) {
            gVar.d(str);
        }
    }

    public static String a(Context context) {
        if (b == null || f12773c == null || context == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            MaInitCommonInfo maInitCommonInfo = f12773c;
            if (maInitCommonInfo != null) {
                String guid = maInitCommonInfo.getGuid();
                if (!TextUtils.isEmpty(guid)) {
                    guid = com.jingdong.jdma.a.a.b.a(guid, "this is the pinaddress key apoaffffe");
                }
                StringBuilder sb = new StringBuilder();
                sb.append("psn/");
                sb.append(guid != null ? guid : "");
                sb.append("|");
                com.jingdong.jdma.g.a aVar = f12777h;
                sb.append(aVar != null ? aVar.g() : 0L);
                stringBuffer.append(sb.toString());
                stringBuffer.append(";");
                StringBuilder sb2 = new StringBuilder();
                sb2.append("psq/");
                com.jingdong.jdma.g.a aVar2 = f12777h;
                sb2.append(aVar2 != null ? aVar2.f() : 0L);
                stringBuffer.append(sb2.toString());
                stringBuffer.append(";");
                stringBuffer.append("adk/" + b.o);
                stringBuffer.append(";");
                stringBuffer.append("ads/" + b.p);
                stringBuffer.append(";");
                StringBuilder sb3 = new StringBuilder();
                sb3.append(f12773c.site_id);
                sb3.append("|");
                sb3.append(f12773c.appv);
                sb3.append("|");
                sb3.append(f12773c.app_device);
                sb3.append(LangUtils.SINGLE_SPACE);
                String str = Build.VERSION.RELEASE;
                sb3.append(str);
                stringBuffer.append("pap/" + sb3.toString());
                stringBuffer.append(";");
                stringBuffer.append("osv/" + str);
                stringBuffer.append(";");
                StringBuilder sb4 = new StringBuilder();
                sb4.append("pv/");
                com.jingdong.jdma.g.a aVar3 = f12777h;
                sb4.append(aVar3 != null ? aVar3.e() : 0L);
                sb4.append(OrderISVUtil.MONEY_DECIMAL);
                com.jingdong.jdma.g.a aVar4 = f12777h;
                sb4.append(aVar4 != null ? aVar4.d() : 0L);
                stringBuffer.append(sb4.toString());
                stringBuffer.append(";");
                stringBuffer.append("installationId/" + f12773c.getInstallationId());
                stringBuffer.append(";");
                if (b.A.length() <= 400) {
                    stringBuffer.append("jdv/" + b.A);
                    stringBuffer.append(";");
                }
                if (d.length() <= 1000) {
                    stringBuffer.append("ref/" + d);
                    stringBuffer.append(";");
                } else if (URLUtil.isValidUrl(d) && d.contains("?")) {
                    String str2 = d;
                    stringBuffer.append("ref/" + str2.substring(0, str2.indexOf(63)));
                    stringBuffer.append(";");
                }
                if (com.jingdong.jdma.common.utils.d.c().f()) {
                    stringBuffer.append("log-debug/" + com.jingdong.jdma.common.utils.d.c().a() + "|" + com.jingdong.jdma.common.utils.d.c().e() + "|" + com.jingdong.jdma.common.utils.d.c().b());
                    stringBuffer.append(";");
                }
                if (!TextUtils.isEmpty(com.jingdong.jdma.common.utils.c.q)) {
                    stringBuffer.append("mode_tag/" + com.jingdong.jdma.common.utils.c.q);
                    stringBuffer.append(";");
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public static int h() {
        Application application = f12776g;
        if (application == null) {
            return 1;
        }
        String b2 = h.b(application);
        b2.hashCode();
        char c2 = '\uffff';
        switch (b2.hashCode()) {
            case R2.attr.re_pstsTabPaddingLeftRight /* 1653 */:
                if (b2.equals("2g")) {
                    c2 = 0;
                    break;
                }
                break;
            case R2.attr.right2_text /* 1684 */:
                if (b2.equals("3g")) {
                    c2 = 1;
                    break;
                }
                break;
            case R2.attr.roundwidth /* 1715 */:
                if (b2.equals("4g")) {
                    c2 = 2;
                    break;
                }
                break;
            case R2.attr.select_indicator_height /* 1746 */:
                if (b2.equals("5g")) {
                    c2 = 3;
                    break;
                }
                break;
            case 3649301:
                if (b2.equals("wifi")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return com.jingdong.jdma.h.d.e().c().b();
            case 1:
                return com.jingdong.jdma.h.d.e().c().d();
            case 2:
                return com.jingdong.jdma.h.d.e().c().f();
            case 3:
                return com.jingdong.jdma.h.d.e().c().h();
            case 4:
                return com.jingdong.jdma.h.d.e().c().j();
            default:
                return 1;
        }
    }

    public static void b(Context context, MaInitCommonInfo maInitCommonInfo) {
        synchronized (b.class) {
            f12773c = maInitCommonInfo;
            if (context != null && maInitCommonInfo != null) {
                com.jingdong.jdma.common.utils.c.v = maInitCommonInfo.getJDMABaseInfo();
                if (a != null) {
                    return;
                }
                com.jingdong.jdma.d.a.d().a(f12773c.getHttpDns());
                n.a().a(f12773c.getISwitchQuery());
                f12777h = new com.jingdong.jdma.g.a(context);
                com.jingdong.jdma.e.b bVar = new com.jingdong.jdma.e.b(context, f12773c);
                a = bVar;
                bVar.c();
                b = new g(context);
                b(context);
            }
        }
    }

    private static void b(Context context) {
        try {
            ProcessLifecycleOwner.get().getLifecycle().addObserver(new ApplicationObserver());
            if (!Application.class.isInstance(context)) {
                context = context.getApplicationContext();
            }
            Application application = (Application) context;
            f12776g = application;
            if (application == null || Build.VERSION.SDK_INT < 14) {
                return;
            }
            com.jingdong.jdma.iml.a aVar = new com.jingdong.jdma.iml.a(f12777h);
            aVar.a(new a());
            f12776g.registerActivityLifecycleCallbacks(aVar);
            LogUtil.d(JDMaInterface.class.getSimpleName(), "JDMAActivityLifecycleCallback class start to work.");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void b(Context context, HashMap<String, String> hashMap, String str) {
        synchronized (b.class) {
            LogUtil.d("JDMA_Impl", "submitData()");
            if (hashMap != null && !hashMap.containsKey("start_type")) {
                hashMap.put("e_seq", com.jingdong.jdma.common.utils.c.a());
            }
            com.jingdong.jdma.iml.c.a().a(new C0496b(hashMap, context, str, hashMap));
        }
    }

    public static void b(String str) {
        com.jingdong.jdma.iml.c.a().a(new e(str));
    }

    public static void a(String str, String str2, Context context) {
        g gVar;
        if (context == null || (gVar = b) == null) {
            return;
        }
        gVar.b(str, str2);
    }

    private static synchronized com.jingdong.jdma.e.a a(Context context, MaInitCommonInfo maInitCommonInfo) throws Throwable {
        com.jingdong.jdma.e.b bVar;
        synchronized (b.class) {
            if (context != null) {
                if (a == null) {
                    b(context, maInitCommonInfo);
                }
                if (a == null) {
                    LogUtil.d(JDMaInterface.class.getSimpleName(), "find some exception when get core..");
                }
                bVar = a;
            } else {
                throw new Exception("context is null");
            }
        }
        return bVar;
    }

    public static synchronized boolean a(Context context, PvInterfaceParam pvInterfaceParam) {
        String str;
        String str2;
        synchronized (b.class) {
            if (context != null && pvInterfaceParam != null) {
                MaInitCommonInfo maInitCommonInfo = f12773c;
                if (maInitCommonInfo != null) {
                    if (a == null) {
                        a(context, maInitCommonInfo);
                    }
                    if (a == null) {
                        return false;
                    }
                    f12777h.a();
                    HashMap<String, String> map = pvInterfaceParam.toMap();
                    map.put("seq", "" + f12777h.f());
                    map.put("vts", "" + f12777h.g());
                    map.put("fst", "" + f12777h.b());
                    map.put("pst", "" + f12777h.c());
                    map.put("vct", "" + f12777h.h());
                    map.put("pv_seq", f12777h.d() + "");
                    map.put("pv_sid", f12777h.e() + "");
                    map.put("apv", f12773c.appv);
                    map.put("bld", f12773c.build);
                    g gVar = b;
                    if (gVar != null && (str2 = gVar.C) != null) {
                        map.put("rpr", str2);
                        b.C = null;
                    } else if (!TextUtils.isEmpty(pvInterfaceParam.lastPage_param)) {
                        map.put("rpr", pvInterfaceParam.lastPage_param);
                        g gVar2 = b;
                        if (gVar2 != null) {
                            gVar2.C = null;
                        }
                    }
                    g gVar3 = b;
                    if (gVar3 != null && (str = gVar3.D) != null) {
                        map.put("ref_cls", str);
                        b.D = null;
                    } else if (!TextUtils.isEmpty(pvInterfaceParam.ref_event_id)) {
                        map.put("ref_cls", pvInterfaceParam.ref_event_id);
                        g gVar4 = b;
                        if (gVar4 != null) {
                            gVar4.D = null;
                        }
                    }
                    g gVar5 = b;
                    if (gVar5 != null) {
                        map.put("jda", gVar5.f12699f);
                        map.put("jdv", b.A);
                        map.put("unpl", b.y);
                        map.put("mba_muid", b.a);
                        map.put("mba_sid", b.b);
                        map.put("jda_ts", b.f12700g);
                        map.put("m_source", b.f12697c);
                        map.put("sourcetype", b.d);
                        map.put("sourcevalue", b.f12698e);
                        map.put("psn", b.f12701h);
                        map.put("psq", b.f12702i);
                        map.put("usc", b.f12704k);
                        map.put("ucp", b.f12705l);
                        map.put("umd", b.f12706m);
                        map.put("utr", b.f12707n);
                        map.put("adk", b.o);
                        map.put("ads", b.p);
                        map.put("ext", b.q);
                        map.put("open_flag", b.r);
                        map.put("wjfrom", b.s);
                        map.put("wjwxpubid", b.t);
                        map.put("wjunionid", b.u);
                        map.put("wjopenid", b.v);
                        map.put("pap", b.B);
                        if (!TextUtils.isEmpty(b.f12703j)) {
                            map.put("ref", b.f12703j);
                            b.f12703j = null;
                        } else if (!TextUtils.isEmpty(pvInterfaceParam.lastPageName)) {
                            map.put("ref", pvInterfaceParam.lastPageName);
                        } else {
                            map.put("ref", d);
                        }
                    }
                    HashMap<String, String> hashMap = pvInterfaceParam.map;
                    if (hashMap != null) {
                        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                            map.put(entry.getKey(), entry.getValue());
                        }
                    }
                    d = pvInterfaceParam.page_name;
                    f12774e = pvInterfaceParam.page_param;
                    com.jingdong.jdma.h.d.e().h(map.get("pin"));
                    b(context, map, pvInterfaceParam.getLogType());
                    return true;
                }
            }
            return false;
        }
    }

    public static synchronized boolean a(Context context, PropertyInterfaceParam propertyInterfaceParam) {
        synchronized (b.class) {
            if (context != null && propertyInterfaceParam != null) {
                MaInitCommonInfo maInitCommonInfo = f12773c;
                if (maInitCommonInfo != null) {
                    if (a == null) {
                        a(context, maInitCommonInfo);
                    }
                    if (a == null) {
                        return false;
                    }
                    HashMap<String, String> map = propertyInterfaceParam.toMap();
                    map.put("seq", "" + f12777h.f());
                    map.put("vts", "" + f12777h.g());
                    map.put("apv", f12773c.appv);
                    map.put("bld", f12773c.build);
                    g gVar = b;
                    if (gVar != null) {
                        map.put("wjfrom", gVar.s);
                        map.put("wjwxpubid", b.t);
                        map.put("wjunionid", b.u);
                        map.put("wjopenid", b.v);
                    }
                    HashMap<String, String> hashMap = propertyInterfaceParam.map;
                    if (hashMap != null) {
                        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                            map.put(entry.getKey(), entry.getValue());
                        }
                    }
                    b(context, map, propertyInterfaceParam.getLts());
                    return true;
                }
            }
            return false;
        }
    }

    public static synchronized boolean a(Context context, ClickInterfaceParam clickInterfaceParam) {
        synchronized (b.class) {
            if (context != null && clickInterfaceParam != null) {
                MaInitCommonInfo maInitCommonInfo = f12773c;
                if (maInitCommonInfo != null) {
                    if (a == null) {
                        a(context, maInitCommonInfo);
                    }
                    if (a == null) {
                        return false;
                    }
                    HashMap<String, String> map = clickInterfaceParam.toMap();
                    map.put("seq", "" + f12777h.f());
                    map.put("vts", "" + f12777h.g());
                    map.put("pv_seq", "" + f12777h.d());
                    map.put("pv_sid", "" + f12777h.e());
                    map.put("apv", f12773c.appv);
                    map.put("bld", f12773c.build);
                    g gVar = b;
                    if (gVar != null) {
                        map.put("jdv", gVar.A);
                        map.put("unpl", b.y);
                        map.put("mba_muid", b.a);
                        map.put("mba_sid", b.b);
                        map.put("m_source", b.f12697c);
                        map.put("sourcetype", b.d);
                        map.put("sourcevalue", b.f12698e);
                        map.put("jda", b.f12699f);
                        map.put("jda_ts", b.f12700g);
                        map.put("open_flag", b.r);
                        map.put("wjfrom", b.s);
                        map.put("wjwxpubid", b.t);
                        map.put("wjunionid", b.u);
                        map.put("wjopenid", b.v);
                    }
                    HashMap<String, String> hashMap = clickInterfaceParam.map;
                    if (hashMap != null) {
                        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                            map.put(entry.getKey(), entry.getValue());
                        }
                    }
                    f12775f = clickInterfaceParam.event_id;
                    com.jingdong.jdma.h.d.e().h(map.get("pin"));
                    b(context, map, clickInterfaceParam.getLogType());
                    return true;
                }
            }
            return false;
        }
    }

    public static synchronized boolean a(Context context, OrderInterfaceParam orderInterfaceParam) {
        synchronized (b.class) {
            if (context != null && orderInterfaceParam != null) {
                MaInitCommonInfo maInitCommonInfo = f12773c;
                if (maInitCommonInfo != null) {
                    if (a == null) {
                        a(context, maInitCommonInfo);
                    }
                    if (a == null) {
                        return false;
                    }
                    HashMap<String, String> map = orderInterfaceParam.toMap();
                    map.put("vts", "" + f12777h.g());
                    map.put("seq", "" + f12777h.f());
                    map.put("apv", f12773c.appv);
                    map.put("bld", f12773c.build);
                    g gVar = b;
                    if (gVar != null) {
                        map.put("sourcevalue", gVar.f12698e);
                        map.put("sourcetype", b.d);
                        map.put("mba_muid", b.a);
                        map.put("mba_sid", b.b);
                        map.put("m_source", b.f12697c);
                        map.put("jda", b.f12699f);
                        map.put("jdv", b.A);
                        map.put("unpl", b.y);
                        map.put("jda_ts", b.f12700g);
                        map.put("open_flag", b.r);
                        map.put("wjfrom", b.s);
                        map.put("wjwxpubid", b.t);
                        map.put("wjunionid", b.u);
                        map.put("wjopenid", b.v);
                    }
                    HashMap<String, String> hashMap = orderInterfaceParam.map;
                    if (hashMap != null) {
                        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                            map.put(entry.getKey(), entry.getValue());
                        }
                    }
                    b(context, map, orderInterfaceParam.getLts());
                    return true;
                }
            }
            return false;
        }
    }

    public static synchronized boolean a(Context context, CustomInterfaceParam customInterfaceParam) {
        synchronized (b.class) {
            if (context != null && customInterfaceParam != null) {
                MaInitCommonInfo maInitCommonInfo = f12773c;
                if (maInitCommonInfo != null) {
                    if (a == null) {
                        a(context, maInitCommonInfo);
                    }
                    if (a == null) {
                        return false;
                    }
                    HashMap<String, String> map = customInterfaceParam.toMap();
                    map.put("seq", f12777h.f() + "");
                    map.put("vts", f12777h.g() + "");
                    map.put("apv", f12773c.appv);
                    map.put("bld", f12773c.build);
                    g gVar = b;
                    if (gVar != null) {
                        map.put("unpl", gVar.y);
                        map.put("wjfrom", b.s);
                        map.put("wjwxpubid", b.t);
                        map.put("wjunionid", b.u);
                        map.put("wjopenid", b.v);
                    }
                    HashMap<String, String> hashMap = customInterfaceParam.map;
                    if (hashMap != null) {
                        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                            map.put(entry.getKey(), entry.getValue());
                        }
                    }
                    b(context, map, customInterfaceParam.getLts());
                    return true;
                }
            }
            return false;
        }
    }

    public static synchronized boolean a(Context context, ExposureInterfaceParam exposureInterfaceParam) {
        synchronized (b.class) {
            if (context != null && exposureInterfaceParam != null) {
                MaInitCommonInfo maInitCommonInfo = f12773c;
                if (maInitCommonInfo != null) {
                    if (a == null) {
                        a(context, maInitCommonInfo);
                    }
                    if (a == null) {
                        return false;
                    }
                    HashMap<String, String> map = exposureInterfaceParam.toMap();
                    map.put("seq", f12777h.f() + "");
                    map.put("vts", f12777h.g() + "");
                    map.put("apv", f12773c.appv);
                    map.put("bld", f12773c.build);
                    g gVar = b;
                    if (gVar != null) {
                        map.put("sourcevalue", gVar.f12698e);
                        map.put("sourcetype", b.d);
                        map.put("mba_muid", b.a);
                        map.put("mba_sid", b.b);
                        map.put("m_source", b.f12697c);
                        map.put("jda", b.f12699f);
                        map.put("jdv", b.A);
                        map.put("jda_ts", b.f12700g);
                        map.put("wjfrom", b.s);
                        map.put("wjwxpubid", b.t);
                        map.put("wjunionid", b.u);
                        map.put("wjopenid", b.v);
                    }
                    HashMap<String, String> hashMap = exposureInterfaceParam.map;
                    if (hashMap != null) {
                        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                            map.put(entry.getKey(), entry.getValue());
                        }
                    }
                    b(context, map, exposureInterfaceParam.getLogType());
                    return true;
                }
            }
            return false;
        }
    }

    public static synchronized void a(Context context, BaseEvent baseEvent) {
        synchronized (b.class) {
            if (a != null && context != null && f12773c != null && baseEvent != null) {
                HashMap<String, String> map = baseEvent.toMap();
                String str = map.get("lts");
                if (!TextUtils.isEmpty(str) && "pv".equals(str)) {
                    map.put("fst", "" + f12777h.b());
                    map.put("pst", "" + f12777h.c());
                    map.put("vct", "" + f12777h.h());
                }
                map.put("apv", f12773c.appv);
                map.put("bld", f12773c.build);
                b(context, map, str);
            }
        }
    }

    public static void a(boolean z) {
        LogUtil.debug(z);
    }

    public static long a(Context context, MaInitCommonInfo maInitCommonInfo, String str) {
        if (a == null) {
            if (maInitCommonInfo == null || context == null) {
                return 0L;
            }
            b(context, maInitCommonInfo);
        }
        if (a == null) {
            return 0L;
        }
        return r3.a(str);
    }

    public static void a(String str, String str2, String str3) {
        g gVar;
        g gVar2;
        g gVar3;
        g gVar4;
        String str4 = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                str4 = new JSONObject(str).optString("unpl");
                if (!TextUtils.isEmpty(str4) && (gVar4 = b) != null) {
                    gVar4.b(str4);
                }
            }
        } catch (JSONException unused) {
        }
        try {
            if (TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str2)) {
                str4 = new JSONObject(str2).optString("unpl");
                if (!TextUtils.isEmpty(str4) && (gVar3 = b) != null) {
                    gVar3.b(str4);
                }
            }
        } catch (JSONException unused2) {
            if (TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str2) && (gVar = b) != null) {
                gVar.b(str2);
            }
        }
        if (!TextUtils.isEmpty(str3) && (gVar2 = b) != null) {
            gVar2.c(str3);
        }
        g gVar5 = b;
        if (gVar5 != null) {
            gVar5.b();
        }
    }

    public static void a(WebView webView) {
        if (webView == null) {
            return;
        }
        if (Build.VERSION.SDK_INT <= 16) {
            try {
                try {
                    webView.removeJavascriptInterface("searchBoxJavaBridge_");
                    webView.removeJavascriptInterface("accessibility");
                    webView.removeJavascriptInterface("accessibilityTraversal");
                } catch (Throwable unused) {
                    Method method = webView.getClass().getMethod("removeJavascriptInterface", String.class);
                    if (method != null) {
                        method.invoke(webView, "searchBoxJavaBridge_");
                        method.invoke(webView, "accessibility");
                        method.invoke(webView, "accessibilityTraversal");
                    }
                }
            } catch (Throwable unused2) {
            }
        }
        webView.addJavascriptInterface(new f(), "JDMAUnifyBridge");
    }

    public static void a(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            obj.getClass().getMethod("addJavascriptInterface", Object.class, String.class).invoke(obj, new f(), "JDMAUnifyBridge");
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    public static void a(AppMode appMode) {
        String str = com.jingdong.jdma.common.utils.c.w;
        if (str == null || !str.equals(appMode.getName())) {
            com.jingdong.jdma.common.utils.c.w = appMode.getName();
            if (a == null || n.a().c()) {
                return;
            }
            a.b(0L);
        }
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.jingdong.jdma.common.utils.c.b.put(str, str2);
    }

    public static void a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                com.jingdong.jdma.common.utils.c.b.put(key, value);
            }
        }
    }

    public static void a(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                com.jingdong.jdma.common.utils.c.b.remove(str);
            }
        }
    }
}
