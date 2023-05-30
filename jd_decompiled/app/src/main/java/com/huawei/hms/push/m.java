package com.huawei.hms.push;

import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.log.HMSLog;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.meizu.cloud.pushsdk.notification.model.AdvertisementOption;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class m {
    private int B;
    private String D;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private String f1465c;
    private String d;

    /* renamed from: l  reason: collision with root package name */
    private String f1473l;

    /* renamed from: m  reason: collision with root package name */
    private String f1474m;

    /* renamed from: n  reason: collision with root package name */
    private String f1475n;
    private String o;
    private String p;
    private String r;
    private String s;
    private String z;
    private String a = "";

    /* renamed from: e  reason: collision with root package name */
    private String f1466e = "";

    /* renamed from: f  reason: collision with root package name */
    private String f1467f = "";

    /* renamed from: g  reason: collision with root package name */
    private String f1468g = "";

    /* renamed from: h  reason: collision with root package name */
    private String f1469h = "";

    /* renamed from: i  reason: collision with root package name */
    private String f1470i = "";

    /* renamed from: j  reason: collision with root package name */
    private String f1471j = "";

    /* renamed from: k  reason: collision with root package name */
    private String f1472k = "";
    private String q = "";
    private int t = i.STYLE_DEFAULT.ordinal();
    private String u = "";
    private String v = "";
    private String w = "";
    private int x = 0;
    private int y = 0;
    private String A = "";
    private String C = "";
    private String E = "";
    private String F = "";

    public m(byte[] bArr, byte[] bArr2) {
        Charset charset = k.a;
        this.r = new String(bArr, charset);
        this.s = new String(bArr2, charset);
    }

    private JSONObject a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(RemoteMessageConst.MessageBody.MSG_CONTENT, jSONObject);
        jSONObject2.put("group", this.a);
        jSONObject2.put("tag", this.A);
        jSONObject2.put(RemoteMessageConst.Notification.AUTO_CANCEL, this.x);
        jSONObject2.put("visibility", this.y);
        jSONObject2.put(RemoteMessageConst.Notification.WHEN, this.z);
        return jSONObject2;
    }

    private JSONObject b(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("cmd", this.f1468g);
        jSONObject2.put("content", this.f1469h);
        jSONObject2.put(RemoteMessageConst.Notification.NOTIFY_ICON, this.f1470i);
        jSONObject2.put(RemoteMessageConst.Notification.NOTIFY_TITLE, this.f1471j);
        jSONObject2.put("notifySummary", this.f1472k);
        jSONObject2.put("param", jSONObject);
        return jSONObject2;
    }

    private void c(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has(AdvertisementOption.AD_PACKAGE)) {
            String string = jSONObject.getString(AdvertisementOption.AD_PACKAGE);
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(string) && string.length() < 48) {
                int length = 48 - string.length();
                for (int i2 = 0; i2 < length; i2++) {
                    sb.append("0");
                }
                sb.append(string);
                this.d = sb.toString();
                return;
            }
            this.d = string.substring(0, 48);
        }
    }

    private boolean d(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has(RemoteMessageConst.Notification.CLICK_ACTION)) {
            this.f1474m = jSONObject.getString(RemoteMessageConst.Notification.CLICK_ACTION);
        }
        if (jSONObject.has(RemoteMessageConst.Notification.INTENT_URI)) {
            this.f1465c = jSONObject.getString(RemoteMessageConst.Notification.INTENT_URI);
        }
        if (jSONObject.has("appPackageName")) {
            this.f1473l = jSONObject.getString("appPackageName");
            return true;
        }
        HMSLog.d("PushSelfShowLog", "appPackageName is null");
        return false;
    }

    private boolean e(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("msgId")) {
            Object obj = jSONObject.get("msgId");
            if (obj instanceof String) {
                this.f1466e = (String) obj;
                return true;
            } else if (obj instanceof Integer) {
                this.f1466e = String.valueOf(((Integer) obj).intValue());
                return true;
            } else {
                return true;
            }
        }
        HMSLog.i("PushSelfShowLog", "msgId == null");
        return false;
    }

    private boolean f(JSONObject jSONObject) {
        HMSLog.d("PushSelfShowLog", "enter parseNotifyParam");
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(RemoteMessageConst.MessageBody.NOTIFY_DETAIL);
            if (jSONObject2.has(DeeplinkProductDetailHelper.LAYER_STYLE)) {
                this.t = jSONObject2.getInt(DeeplinkProductDetailHelper.LAYER_STYLE);
            }
            this.u = jSONObject2.optString("bigTitle");
            this.v = jSONObject2.optString("bigContent");
            this.E = jSONObject2.optString("icon");
            return true;
        } catch (JSONException e2) {
            HMSLog.i("PushSelfShowLog", e2.toString());
            return false;
        }
    }

    private void g(JSONObject jSONObject) {
        this.a = jSONObject.optString("group");
        HMSLog.d("PushSelfShowLog", "NOTIFY_GROUP:" + this.a);
        this.x = jSONObject.optInt(RemoteMessageConst.Notification.AUTO_CANCEL, 1);
        HMSLog.d("PushSelfShowLog", "autoCancel: " + this.x);
        this.y = jSONObject.optInt("visibility", 0);
        this.z = jSONObject.optString(RemoteMessageConst.Notification.WHEN);
        this.A = jSONObject.optString("tag");
    }

    private boolean h(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("param");
            if (jSONObject2.has("autoClear")) {
                this.b = jSONObject2.getInt("autoClear");
            } else {
                this.b = 0;
            }
            if (!"app".equals(this.f1468g) && !"cosa".equals(this.f1468g)) {
                if ("url".equals(this.f1468g)) {
                    k(jSONObject2);
                    return true;
                } else if ("rp".equals(this.f1468g)) {
                    j(jSONObject2);
                    return true;
                } else {
                    return true;
                }
            }
            d(jSONObject2);
            return true;
        } catch (Exception e2) {
            HMSLog.e("PushSelfShowLog", "ParseParam error ", e2);
            return false;
        }
    }

    private boolean i(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has(RemoteMessageConst.MessageBody.PS_CONTENT)) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(RemoteMessageConst.MessageBody.PS_CONTENT);
            this.f1468g = jSONObject2.getString("cmd");
            this.f1469h = jSONObject2.optString("content");
            this.f1470i = jSONObject2.optString(RemoteMessageConst.Notification.NOTIFY_ICON);
            this.f1471j = jSONObject2.optString(RemoteMessageConst.Notification.NOTIFY_TITLE);
            this.f1472k = jSONObject2.optString("notifySummary");
            this.D = jSONObject2.optString(RemoteMessageConst.Notification.TICKER);
            if ((!jSONObject2.has(RemoteMessageConst.MessageBody.NOTIFY_DETAIL) || f(jSONObject2)) && jSONObject2.has("param")) {
                return h(jSONObject2);
            }
        }
        return false;
    }

    private boolean j(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("appPackageName")) {
            this.f1473l = jSONObject.getString("appPackageName");
        }
        if (jSONObject.has("rpt") && jSONObject.has("rpl")) {
            this.o = jSONObject.getString("rpl");
            this.p = jSONObject.getString("rpt");
            if (jSONObject.has("rpct")) {
                this.q = jSONObject.getString("rpct");
                return true;
            }
            return true;
        }
        HMSLog.d("PushSelfShowLog", "rpl or rpt is null");
        return false;
    }

    private boolean k(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("url")) {
            this.f1475n = jSONObject.getString("url");
            if (jSONObject.has("appPackageName")) {
                this.f1473l = jSONObject.getString("appPackageName");
            }
            if (jSONObject.has("rpt") && jSONObject.has("rpl")) {
                this.o = jSONObject.getString("rpl");
                this.p = jSONObject.getString("rpt");
                if (jSONObject.has("rpct")) {
                    this.q = jSONObject.getString("rpct");
                    return true;
                }
                return true;
            }
            return true;
        }
        HMSLog.d("PushSelfShowLog", "url is null");
        return false;
    }

    private JSONObject r() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(DeeplinkProductDetailHelper.LAYER_STYLE, this.t);
        jSONObject.put("bigTitle", this.u);
        jSONObject.put("bigContent", this.v);
        jSONObject.put("bigPic", this.w);
        return jSONObject;
    }

    private JSONObject v() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("autoClear", this.b);
        jSONObject.put("url", this.f1475n);
        jSONObject.put("rpl", this.o);
        jSONObject.put("rpt", this.p);
        jSONObject.put("rpct", this.q);
        jSONObject.put("appPackageName", this.f1473l);
        jSONObject.put(RemoteMessageConst.Notification.CLICK_ACTION, this.f1474m);
        jSONObject.put(RemoteMessageConst.Notification.INTENT_URI, this.f1465c);
        return jSONObject;
    }

    public String l() {
        return this.a;
    }

    public String m() {
        return this.E;
    }

    public String n() {
        return this.f1465c;
    }

    public byte[] o() {
        try {
            return a(a(b(v()), r())).toString().getBytes(k.a);
        } catch (JSONException e2) {
            HMSLog.e("PushSelfShowLog", "getMsgData failed JSONException:", e2);
            return new byte[0];
        }
    }

    public String p() {
        HMSLog.d("PushSelfShowLog", "msgId =" + this.f1466e);
        return this.f1466e;
    }

    public String q() {
        return this.A;
    }

    public int s() {
        return this.B;
    }

    public String t() {
        return this.f1472k;
    }

    public String u() {
        return this.f1471j;
    }

    public int w() {
        return this.t;
    }

    public String x() {
        return this.D;
    }

    public byte[] y() {
        return this.s.getBytes(k.a);
    }

    public boolean z() {
        try {
            if (TextUtils.isEmpty(this.r)) {
                HMSLog.d("PushSelfShowLog", "msg is null");
                return false;
            }
            JSONObject jSONObject = new JSONObject(this.r);
            g(jSONObject);
            JSONObject jSONObject2 = jSONObject.getJSONObject(RemoteMessageConst.MessageBody.MSG_CONTENT);
            if (e(jSONObject2)) {
                this.f1467f = jSONObject2.optString("dispPkgName");
                c(jSONObject2);
                this.B = jSONObject2.optInt(RemoteMessageConst.Notification.NOTIFY_ID, -1);
                this.C = jSONObject2.optString("data");
                this.F = jSONObject2.optString(RemoteMessageConst.ANALYTIC_INFO);
                return i(jSONObject2);
            }
            return false;
        } catch (JSONException unused) {
            HMSLog.d("PushSelfShowLog", "parse message exception.");
            return false;
        } catch (Exception e2) {
            HMSLog.d("PushSelfShowLog", e2.toString());
            return false;
        }
    }

    private JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("dispPkgName", this.f1467f);
        jSONObject3.put("msgId", this.f1466e);
        jSONObject3.put(AdvertisementOption.AD_PACKAGE, this.d);
        jSONObject3.put(RemoteMessageConst.Notification.NOTIFY_ID, this.B);
        jSONObject3.put(RemoteMessageConst.MessageBody.PS_CONTENT, jSONObject);
        jSONObject3.put(RemoteMessageConst.MessageBody.NOTIFY_DETAIL, jSONObject2);
        jSONObject3.put(RemoteMessageConst.Notification.TICKER, this.D);
        jSONObject3.put("data", this.C);
        return jSONObject3;
    }

    public String b() {
        return this.F;
    }

    public String d() {
        return this.f1473l;
    }

    public String g() {
        return this.v;
    }

    public int e() {
        return this.x;
    }

    public int f() {
        return this.b;
    }

    public String j() {
        return this.f1469h;
    }

    public String c() {
        return this.d;
    }

    public String k() {
        return this.f1467f;
    }

    public String h() {
        return this.u;
    }

    public String i() {
        return this.f1468g;
    }

    public String a() {
        return this.f1474m;
    }

    public void a(int i2) {
        this.B = i2;
    }
}
