package com.jingdong.manto.m;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.jingdong.manto.jsapi.auth.tools.AuthorizeManager;
import com.jingdong.manto.jsengine.IMantoWebViewJS;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.ui.auth.MantoAuthDialog;
import com.jingdong.manto.utils.MantoStringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class k0 {
    private String a;
    private boolean b;

    /* renamed from: c */
    private e0 f13423c;
    private IMantoWebViewJS d;

    /* renamed from: e */
    private Map<String, c0> f13424e;

    /* renamed from: f */
    public Handler f13425f;

    /* renamed from: g */
    private ConcurrentHashMap<Integer, String> f13426g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ String f13427c;

        a(String str, String str2, String str3) {
            k0.this = r1;
            this.a = str;
            this.b = str2;
            this.f13427c = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            k0.this.f13423c.a(this.a, this.b, k0.c(this.f13427c));
        }
    }

    /* loaded from: classes15.dex */
    public class b implements MantoAuthDialog.Callback {
        final /* synthetic */ c0 a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ JSONObject f13428c;
        final /* synthetic */ int d;

        /* renamed from: e */
        final /* synthetic */ String f13429e;

        b(c0 c0Var, String str, JSONObject jSONObject, int i2, String str2) {
            k0.this = r1;
            this.a = c0Var;
            this.b = str;
            this.f13428c = jSONObject;
            this.d = i2;
            this.f13429e = str2;
        }

        @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
        public void onAccept() {
            e0 e0Var;
            JSONObject jSONObject;
            int i2;
            String jsApiName;
            d0 d0Var = (d0) this.a;
            if (TextUtils.isEmpty(this.b)) {
                e0Var = k0.this.f13423c;
                jSONObject = this.f13428c;
                i2 = this.d;
                jsApiName = d0Var.getJsApiName();
            } else {
                e0Var = k0.this.f13423c;
                jSONObject = this.f13428c;
                i2 = this.d;
                jsApiName = this.b;
            }
            d0Var.exec(e0Var, jSONObject, i2, jsApiName);
        }

        @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
        public void onCancel() {
            k0.this.f13423c.a(this.d, this.a.putErrMsg("fail:auth canceled", null, this.f13429e));
        }

        @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
        public void onReject() {
            k0.this.f13423c.a(this.d, this.a.putErrMsg("fail:auth denied", null, this.f13429e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes15.dex */
    public class c implements Runnable {
        String a;
        String b;

        /* renamed from: c */
        int f13431c;

        c(String str, String str2, int i2) {
            k0.this = r1;
            this.a = str;
            this.b = str2;
            this.f13431c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (k0.this.b) {
                k0.this.a(this.a, this.b, this.f13431c, false);
            }
        }
    }

    public k0(Context context, com.jingdong.manto.q.n nVar, IMantoWebViewJS iMantoWebViewJS) {
        this(nVar, iMantoWebViewJS, h0.c());
        this.a += "-WebView";
    }

    public k0(com.jingdong.manto.h hVar, IMantoWebViewJS iMantoWebViewJS) {
        this(hVar, iMantoWebViewJS, h0.b());
        this.a += "-Service";
    }

    public k0(e0 e0Var, IMantoWebViewJS iMantoWebViewJS, Map<String, c0> map) {
        this.a = k0.class.getSimpleName();
        this.f13426g = new ConcurrentHashMap<>();
        this.f13423c = e0Var;
        this.d = iMantoWebViewJS;
        this.f13424e = map;
        this.b = true;
        HandlerThread handlerThread = new HandlerThread("MantoAsyncJSThread");
        handlerThread.start();
        this.f13425f = new Handler(handlerThread.getLooper());
    }

    public k0(com.jingdong.manto.q.n nVar, IMantoWebViewJS iMantoWebViewJS) {
        this(nVar, iMantoWebViewJS, h0.a());
        this.a += "-PageView";
    }

    private static String a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("errMsg", str + ":" + str2);
        return new JSONObject(hashMap).toString();
    }

    public String a(String str, String str2, int i2, boolean z) {
        return a(str, str2, i2, z, (String) null);
    }

    private String a(String str, String str2, int i2, boolean z, String str3) {
        if (this.f13423c.a() == null || !this.f13423c.f()) {
            return a(str, "fail:interrupted");
        }
        c0 c0Var = this.f13424e.get(str);
        JSONObject b2 = b(str2);
        String jsApiName = !TextUtils.isEmpty(str3) ? str3 : c0Var.getJsApiName();
        String str4 = null;
        if (b2 == null) {
            str4 = c0Var.putErrMsg("fail: invalidate data", null, jsApiName);
        } else if (z) {
            l0 l0Var = (l0) c0Var;
            e0 e0Var = this.f13423c;
            if (e0Var instanceof com.jingdong.manto.h) {
                l0Var.a = Thread.currentThread().getId();
                str4 = l0Var.a((com.jingdong.manto.h) e0Var, b2);
            } else {
                str4 = l0Var.a((com.jingdong.manto.q.n) e0Var, b2);
            }
        } else {
            AuthorizeManager.checkAndDoAuthorsize(this.f13423c, c0Var, new b(c0Var, str3, b2, i2, jsApiName));
        }
        if (z) {
            if (!MantoStringUtils.isEmpty(str4)) {
                return str4;
            }
        } else if (str4 != null) {
            this.f13423c.a(i2, str4);
        }
        return "";
    }

    private JSONObject b(String str) {
        if (MantoStringUtils.isEmpty(str)) {
            str = "{}";
        }
        try {
            return new JSONObject(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static int[] c(String str) {
        int[] iArr = new int[0];
        try {
            JSONArray jSONArray = new JSONArray(str);
            int[] iArr2 = new int[jSONArray.length()];
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                iArr2[i2] = jSONArray.getInt(i2);
            }
            return iArr2;
        } catch (Exception unused) {
            return iArr;
        }
    }

    public String a(String str, String str2, int i2, String str3) {
        c0 c0Var = this.f13424e.get(str);
        if (c0Var == null) {
            this.f13423c.a(i2, a(str, "fail:not supported"));
            return a(str, "fail:not supported");
        }
        return a(str, str2, i2, c0Var instanceof l0, str3);
    }

    public final void a() {
        this.f13425f.getLooper().quit();
        this.b = false;
        this.f13426g.clear();
    }

    @JavascriptInterface
    public String invokeHandler(String str, String str2, int i2) {
        int i3;
        if (i2 == 0) {
            i3 = 0;
        } else {
            try {
                int incrementAndGet = this.f13423c.b.incrementAndGet();
                this.f13423c.f13345c.put(incrementAndGet, new e0.a(this.d, i2));
                i3 = incrementAndGet;
            } catch (Exception e2) {
                e2.printStackTrace();
                return "";
            }
        }
        c0 c0Var = this.f13424e.get(str);
        if (c0Var == null) {
            this.f13423c.a(i3, a(str, "fail:not supported"));
            return a(str, "fail:not supported");
        }
        e0 e0Var = this.f13423c;
        if (e0Var != null && e0Var.h() != null && this.f13423c.h().b(str)) {
            this.f13423c.a(i3, a(str, "fail:no permission"));
            return a(str, "fail:no permission");
        } else if (c0Var instanceof l0) {
            return a(str, str2, i3, true);
        } else {
            this.f13425f.post(new c(str, str2, i3));
            return "";
        }
    }

    @JavascriptInterface
    public boolean isDebugPackage() {
        return true;
    }

    @JavascriptInterface
    public void publishHandler(String str, String str2, String str3) {
        this.f13425f.post(new a(str, str2, str3));
    }

    @JavascriptInterface
    public String retrieveEvent(int i2) {
        return "";
    }
}
