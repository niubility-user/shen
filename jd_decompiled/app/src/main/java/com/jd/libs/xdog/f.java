package com.jd.libs.xdog;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import com.jd.libs.xdog.ui.XDogInfoView;
import com.jd.libs.xdog.ui.XDogPanelView;
import com.jd.libs.xdog.ui.XDogWebView;
import com.jingdong.common.model.datetime.JDDateTimePickerDialog;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class f {
    public static Class<? extends e> s;
    private static volatile f t;
    public static boolean u;
    public static boolean v;
    public static boolean w;
    public static boolean x;

    /* renamed from: c  reason: collision with root package name */
    private XDogWebView f6196c;
    private Object d;

    /* renamed from: n  reason: collision with root package name */
    private XDogInfoView f6206n;
    private XDogPanelView o;
    private JSONObject p;
    private String q;
    private boolean a = false;
    private boolean b = false;

    /* renamed from: e  reason: collision with root package name */
    private final Map<String, Map<String, String>> f6197e = new HashMap();

    /* renamed from: f  reason: collision with root package name */
    private final Map<String, String> f6198f = new HashMap();

    /* renamed from: g  reason: collision with root package name */
    private final Map<String, String> f6199g = new HashMap();

    /* renamed from: h  reason: collision with root package name */
    private final List<JSONObject> f6200h = new ArrayList();

    /* renamed from: i  reason: collision with root package name */
    private final List<JSONObject> f6201i = new ArrayList();

    /* renamed from: j  reason: collision with root package name */
    private final List<JSONObject> f6202j = new ArrayList();

    /* renamed from: k  reason: collision with root package name */
    private final Map<String, String> f6203k = new HashMap();

    /* renamed from: l  reason: collision with root package name */
    private JSONObject f6204l = new JSONObject();

    /* renamed from: m  reason: collision with root package name */
    private JSONObject f6205m = new JSONObject();
    private final List<String> r = new ArrayList();

    public static f b() {
        if (t == null) {
            synchronized (f.class) {
                if (t == null) {
                    t = new f();
                }
            }
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void g(String str, Context context, ViewGroup viewGroup) {
        if (v && !this.r.contains(str)) {
            this.r.add(str);
            XDogInfoView xDogInfoView = new XDogInfoView(context, str);
            this.f6206n = xDogInfoView;
            viewGroup.addView(xDogInfoView);
            s(str, context);
        }
        if (u && this.o == null) {
            this.o = new XDogPanelView(context);
            ((ViewGroup) viewGroup.getRootView()).addView(this.o);
        }
    }

    private void l(XDogWebView xDogWebView) {
        if (this.f6200h.size() > 0) {
            Iterator<JSONObject> it = this.f6200h.iterator();
            while (it.hasNext()) {
                com.jd.libs.xdog.g.e.a(xDogWebView, "dogHybridLogCallBack", "0", it.next(), "");
            }
        }
        JSONObject jSONObject = this.f6204l;
        if (jSONObject != null) {
            com.jd.libs.xdog.g.e.a(xDogWebView, "dogBeforeLoadCallBack", "0", jSONObject, "");
        }
        JSONObject jSONObject2 = this.p;
        if (jSONObject2 != null) {
            com.jd.libs.xdog.g.e.a(xDogWebView, "dogSystemCallBack", "0", jSONObject2, "");
        }
        if (this.f6201i.size() > 0) {
            Iterator<JSONObject> it2 = this.f6201i.iterator();
            while (it2.hasNext()) {
                com.jd.libs.xdog.g.e.a(xDogWebView, "dogHitStatusOfPackageCallBack", "0", it2.next(), "");
            }
        }
        if (this.f6202j.size() > 0) {
            Iterator<JSONObject> it3 = this.f6202j.iterator();
            while (it3.hasNext()) {
                com.jd.libs.xdog.g.e.a(xDogWebView, "dogHitStatusOfSourcesInPackagereCallBack", "0", it3.next(), "");
            }
        }
    }

    private void s(String str, Context context) {
        try {
            Map<String, String> map = this.f6197e.get(str);
            if (map == null) {
                map = new HashMap<>();
            }
            map.put("\u5185\u5b58\u72b6\u6001", "\r\n" + ("App\u4f7f\u7528\u5185\u5b58: " + com.jd.libs.xdog.g.c.b() + "\r\n\u5269\u4f59\u5185\u5b58: " + com.jd.libs.xdog.g.c.a(context) + "\r\n\u5185\u5b58\u4e34\u754c\u503c: " + com.jd.libs.xdog.g.c.d(context) + "\r\n\u624b\u673a\u603b\u5185\u5b58: " + com.jd.libs.xdog.g.c.c(context) + "\r\n\u662f\u5426\u4f4e\u5185\u5b58\u8fd0\u884c: " + com.jd.libs.xdog.g.c.e(context)));
            this.f6197e.put(str, map);
            XDogInfoView xDogInfoView = this.f6206n;
            if (xDogInfoView != null) {
                xDogInfoView.g(this.f6197e);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("hybridDoor", u);
            jSONObject.put("performanceDoor", v);
            jSONObject.put("phoneVersion", Build.VERSION.RELEASE);
            jSONObject.put("appVersion", this.q);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public Map<String, Map<String, String>> c() {
        return this.f6197e;
    }

    public Object d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONArray optJSONArray = ((JSONObject) this.d).optJSONArray("packList");
        int i2 = 0;
        while (true) {
            if (i2 >= (optJSONArray != null ? optJSONArray.length() : 0)) {
                return null;
            }
            try {
                JSONObject jSONObject = (JSONObject) optJSONArray.get(i2);
                if (str.equals(jSONObject.optString("appid"))) {
                    return jSONObject;
                }
                i2++;
            } catch (JSONException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    public void e() {
        XDogPanelView xDogPanelView = this.o;
        if (xDogPanelView != null) {
            xDogPanelView.e();
        }
    }

    public void h() {
        this.r.clear();
        this.f6200h.clear();
        this.f6201i.clear();
        this.f6202j.clear();
        this.f6198f.clear();
        this.f6199g.clear();
        this.f6197e.clear();
        this.f6204l = new JSONObject();
        this.f6205m = new JSONObject();
        x = false;
        this.a = false;
        this.b = false;
        this.f6206n = null;
        this.o = null;
    }

    public void i(Class<? extends e> cls) {
        s = cls;
    }

    public void j(final ViewGroup viewGroup, final Context context, final String str) {
        if ((!u && !v) || viewGroup == null || context == null) {
            return;
        }
        this.q = com.jd.libs.xdog.g.e.c(context);
        viewGroup.post(new Runnable() { // from class: com.jd.libs.xdog.a
            @Override // java.lang.Runnable
            public final void run() {
                f.this.g(str, context, viewGroup);
            }
        });
    }

    public void k(Object obj) {
        this.d = obj;
    }

    public void m(String str, Integer num, String str2, Integer num2) {
        if (u) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("appid", str);
                jSONObject.put("loadType", num);
                jSONObject.put("name", str2);
                jSONObject.put("type", num2);
                if (this.f6203k.containsKey(str)) {
                    jSONObject.put("map", new JSONArray(this.f6203k.get(str)));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (!x) {
                this.f6201i.add(jSONObject);
            }
            XDogPanelView xDogPanelView = this.o;
            if (xDogPanelView == null || !x) {
                return;
            }
            xDogPanelView.o(jSONObject, "dogHitStatusOfPackageCallBack");
        }
    }

    public void n(String str, String str2, String str3) {
        if (u) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("core_version", str);
                jSONObject.put("web_url", str2);
                jSONObject.put("user_agent", str3);
                jSONObject.put("client_version", this.q);
                jSONObject.put("os_version", Build.VERSION.RELEASE);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            boolean z = x;
            if (!z) {
                this.p = jSONObject;
            }
            XDogPanelView xDogPanelView = this.o;
            if (xDogPanelView == null || !z) {
                return;
            }
            xDogPanelView.o(jSONObject, "dogSystemCallBack");
        }
    }

    public void o(String str, String str2) {
        if (!u || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.f6203k.put(str, str2);
    }

    public synchronized void p(String str) {
        if (u && !TextUtils.isEmpty(str)) {
            String d = com.jd.libs.xdog.g.e.d(System.currentTimeMillis());
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            if (str.contains("\u5c5e\u4e8e\u516c\u5171\u79bb\u7ebf\u5305\u6587\u4ef6")) {
                jSONArray.put("key_public_project");
            }
            if (str.contains("\u5c5e\u4e8e\u9879\u76ee\u5185\u6587\u4ef6")) {
                jSONArray.put("key_in_project");
            }
            if (str.contains("\u5c5e\u4e8e\u5168\u5c40\u516c\u5171\u8d44\u6e90\u6587\u4ef6")) {
                jSONArray.put("key_global_public_project");
            }
            try {
                jSONObject.put(JDDateTimePickerDialog.SELECT_DATE_MODE, d);
                jSONObject.put("log", str);
                jSONObject.put("type", 0);
                jSONObject.put("keys", jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (!x) {
                this.f6200h.add(jSONObject);
            }
            XDogPanelView xDogPanelView = this.o;
            if (xDogPanelView != null && x) {
                xDogPanelView.o(jSONObject, "dogHybridLogCallBack");
            }
        }
    }

    public synchronized void q(XDogWebView xDogWebView) {
        this.f6196c = xDogWebView;
        if (this.a && !this.b) {
            l(xDogWebView);
            com.jd.libs.xdog.g.e.a(xDogWebView, "dogUploadSBLogCallBack", "0", 1, "");
            this.b = true;
        }
    }

    public synchronized void r(String str) {
        if (u && !TextUtils.isEmpty(str)) {
            String d = com.jd.libs.xdog.g.e.d(System.currentTimeMillis());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(JDDateTimePickerDialog.SELECT_DATE_MODE, d);
                jSONObject.put("log", str);
                jSONObject.put("type", 1);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (!x) {
                this.f6200h.add(jSONObject);
            }
            XDogPanelView xDogPanelView = this.o;
            if (xDogPanelView != null && x) {
                xDogPanelView.o(jSONObject, "dogHybridLogCallBack");
            }
        }
    }

    public void t(String str, Integer num, String str2) {
        if (u) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("appid", str);
                jSONObject.put("resourceType", num);
                jSONObject.put("resUrl", str2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (!x) {
                this.f6202j.add(jSONObject);
            }
            XDogPanelView xDogPanelView = this.o;
            if (xDogPanelView == null || !x) {
                return;
            }
            xDogPanelView.o(jSONObject, "dogHitStatusOfSourcesInPackagereCallBack");
        }
    }

    public synchronized void u(String str, String str2, String str3, String str4) {
        if (v && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            Map<String, String> map = this.f6197e.get(str);
            if (map == null) {
                map = new HashMap<>();
            }
            if ("data".equals(str2)) {
                try {
                    if ("loadTime".equals(str3)) {
                        this.a = true;
                        if (!this.b && x) {
                            l(this.f6196c);
                            com.jd.libs.xdog.g.e.a(this.f6196c, "dogUploadSBLogCallBack", "0", 1, "");
                            this.b = true;
                        }
                    }
                    if ("loadTime".equals(str3) && !TextUtils.isEmpty(map.get("loadTime"))) {
                        return;
                    }
                    int parseInt = Integer.parseInt(String.valueOf(Math.round(Double.parseDouble(str4))));
                    if (parseInt != 0) {
                        map.put(str3, parseInt + "ms");
                    }
                    try {
                        this.f6205m.put("type", "timing");
                        this.f6205m.put(str3.toLowerCase(), parseInt);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    Log.println(3, "XDogCyber", "hybrid-cyber>>" + this.f6205m.toString());
                } catch (NumberFormatException unused) {
                }
            } else if ("text".equals(str2)) {
                map.put(str3, str4);
            }
            this.f6197e.put(str, map);
            XDogInfoView xDogInfoView = this.f6206n;
            if (xDogInfoView != null) {
                xDogInfoView.g(this.f6197e);
            }
        }
    }

    public void v(String str, String str2, String str3) {
        if (!u || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        if ("prefetch".equals(str)) {
            this.f6198f.put(str2, str3);
            try {
                this.f6204l.put("prefetch", new JSONObject(this.f6198f));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (HttpDnsConfig.PREDOWNLOAD_PARAMS.equals(str)) {
            this.f6199g.put(str2, str3);
            try {
                this.f6204l.put(HttpDnsConfig.PREDOWNLOAD_PARAMS, new JSONObject(this.f6199g));
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        XDogPanelView xDogPanelView = this.o;
        if (xDogPanelView == null || !x) {
            return;
        }
        xDogPanelView.o(this.f6204l, "dogBeforeLoadCallBack");
    }

    public synchronized void w(String str, String str2) {
        if (!v || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            Map<String, String> map = this.f6197e.get(str);
            if (map == null) {
                map = new HashMap<>();
            }
            map.put("URL\u5404\u9636\u6bb5\u8017\u65f6", "\r\n" + ("TCP\u5b8c\u6210\u63e1\u624b: " + com.jd.libs.xdog.g.e.f(jSONObject, "connectStart", "connectEnd") + "\r\n\u57df\u540d\u89e3\u6790: " + com.jd.libs.xdog.g.e.f(jSONObject, "domainLookupStart", "domainLookupEnd") + "\r\n\u91cd\u5b9a\u5411: " + com.jd.libs.xdog.g.e.f(jSONObject, "redirectStart", "redirectEnd") + "\r\n\u5185\u5bb9\u52a0\u8f7d\u5b8c\u6210: " + com.jd.libs.xdog.g.e.f(jSONObject, "requestStart", "responseEnd") + "\r\n\u5185\u5bb9\u54cd\u5e94\u65f6\u957f: " + com.jd.libs.xdog.g.e.f(jSONObject, "responseStart", "responseEnd") + "\r\n\u5378\u8f7d\u9875\u9762: " + com.jd.libs.xdog.g.e.f(jSONObject, "unloadEventStart", "unloadEventEnd") + "\r\nDNS\u7f13\u5b58: " + com.jd.libs.xdog.g.e.f(jSONObject, "domainLookupStart", "domainLookupEnd") + "\r\n\u6267\u884conload\u51fd\u6570: " + com.jd.libs.xdog.g.e.f(jSONObject, "loadEventStart", "loadEventEnd") + "\r\n\u8bfb\u53d6\u7b2c\u4e00\u4e2a\u5b57\u8282: " + com.jd.libs.xdog.g.e.f(jSONObject, "navigationStart", "responseStart") + "\r\n\u89e3\u6790DOM\u6811\u7ed3\u6784: " + com.jd.libs.xdog.g.e.f(jSONObject, "responseEnd", "domComplete") + "\r\n\u9875\u9762\u52a0\u8f7d\u5b8c\u6210: " + com.jd.libs.xdog.g.e.f(jSONObject, "navigationStart", "loadEventEnd") + "\r\nhttps\u94fe\u63a5: " + com.jd.libs.xdog.g.e.f(jSONObject, "secureConnectionStart", "requestStart")));
            this.f6197e.put(str, map);
            XDogInfoView xDogInfoView = this.f6206n;
            if (xDogInfoView != null) {
                xDogInfoView.g(this.f6197e);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public synchronized void x(String str, Map<String, String> map) {
        if (!v || TextUtils.isEmpty(str) || map == null) {
            return;
        }
        try {
            if (!"0".equals(com.jd.libs.xdog.g.e.e(map, "initStart", WebPerfManager.INIT_FINISH, true))) {
                u(str, "text", "\u7ec4\u4ef6\u521d\u59cb\u5316\u8017\u65f6", com.jd.libs.xdog.g.e.e(map, "initStart", WebPerfManager.INIT_FINISH, true));
            }
            if (!"0".equals(com.jd.libs.xdog.g.e.e(map, "gentokenStart", WebPerfManager.GENTOKEN_FINISH, true))) {
                u(str, "text", "\u767b\u5f55\u6253\u901a\u8017\u65f6", com.jd.libs.xdog.g.e.e(map, "gentokenStart", WebPerfManager.GENTOKEN_FINISH, true));
            }
            if (!"0".equals(com.jd.libs.xdog.g.e.e(map, "pageStart", "pageFinish", false))) {
                u(str, "data", "loadTime", com.jd.libs.xdog.g.e.e(map, "pageStart", "pageFinish", false));
            }
            if (!"0".equals(com.jd.libs.xdog.g.e.e(map, "pageStart", WebPerfManager.RENDER, true))) {
                u(str, "text", "\u9996\u5c4f\u8017\u65f6", com.jd.libs.xdog.g.e.e(map, "pageStart", WebPerfManager.RENDER, true));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
