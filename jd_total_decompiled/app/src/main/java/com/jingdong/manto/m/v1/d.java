package com.jingdong.manto.m.v1;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.jingdong.manto.h;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d {
    private static final String d = "com.jingdong.manto.m.v1.d";
    private h a;
    private e b;

    /* renamed from: c */
    private boolean f13809c;

    public d(h hVar) {
        this.a = hVar;
    }

    public void a() {
        e eVar = this.b;
        if (eVar != null) {
            eVar.a();
            this.b = null;
        }
    }

    @JavascriptInterface
    public int create(String str) {
        MantoLog.d(d, "create:" + str);
        if (this.b == null) {
            this.b = new e(this.a);
        }
        return this.b.a(str);
    }

    @JavascriptInterface
    public void postMsgToWorker(int i2, String str) {
        MantoLog.d(d, "postMsgToWorker:id:" + i2 + ", params:" + str);
        e eVar = this.b;
        if (eVar == null) {
            return;
        }
        if (this.f13809c) {
            eVar.a(i2, str);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("__cmd__", "");
            String optString2 = jSONObject.optString("__script__", "");
            if (TextUtils.equals(optString, "requireScript")) {
                this.b.b(i2, optString2);
                this.f13809c = true;
            }
        } catch (Throwable th) {
            MantoLog.e(d, th.getMessage());
        }
    }

    @JavascriptInterface
    public void terminate(int i2) {
        MantoLog.d(d, "terminate:" + i2);
        e eVar = this.b;
        if (eVar != null) {
            eVar.a(i2);
        }
    }
}
