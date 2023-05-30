package com.jingdong.app.mall.home.floor.ctrl.t;

import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.ChannelInfo;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class b extends com.jingdong.app.mall.home.r.e.b {

    /* renamed from: f  reason: collision with root package name */
    private static final String f9513f = "b";

    /* renamed from: g  reason: collision with root package name */
    private static long f9514g = Math.max(com.jingdong.app.mall.home.o.a.f.N("bk_first_last_interval", 0), 0L);

    /* renamed from: h  reason: collision with root package name */
    private static long f9515h = Math.max(com.jingdong.app.mall.home.o.a.f.N("bk_last_interval", 0), 0L);

    /* renamed from: i  reason: collision with root package name */
    private static long f9516i = Math.max(com.jingdong.app.mall.home.o.a.f.N("bk_request_interval", 0), 0L);

    /* renamed from: j  reason: collision with root package name */
    private static long f9517j = Math.max(com.jingdong.app.mall.home.o.a.f.N("bk_launch_interval", 0), 0L);

    /* renamed from: k  reason: collision with root package name */
    private static volatile boolean f9518k;

    /* renamed from: l  reason: collision with root package name */
    private static com.jingdong.app.mall.home.r.c.b f9519l;
    private final AtomicBoolean a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f9520c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f9521e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements f.d {
        final /* synthetic */ boolean a;

        a(boolean z) {
            this.a = z;
        }

        @Override // com.jingdong.app.mall.home.o.a.f.d
        public void onEnd(JDJSONObject jDJSONObject) {
            if (jDJSONObject == null) {
                b.this.l();
                return;
            }
            b.this.g(jDJSONObject.optJSONObject("clientConfig"));
            if (this.a) {
                b.this.k(null);
                return;
            }
            JDJSONObject optJSONObject = jDJSONObject.optJSONObject("webView");
            if (optJSONObject != null) {
                b.this.h(optJSONObject);
            } else {
                b.this.l();
            }
        }

        @Override // com.jingdong.app.mall.home.o.a.f.d
        public void onError(HttpError httpError) {
            b.this.l();
        }

        @Override // com.jingdong.app.mall.home.o.a.f.d
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    static {
        f9518k = com.jingdong.app.mall.home.o.a.f.M("bk_has_config_flag", 0) == 1;
        s("init: \u5ba2\u6237\u7aef\u8bf7\u6c42\u5468\u671f " + f9516i + "   \u521d\u672b\u6b21\u4fe1\u606f\u65f6\u95f4\u5dee " + f9514g + "   \u672b\u6b21\u4fe1\u606f\u6709\u6548\u65f6\u95f4\u5dee " + f9515h + "   \u542f\u52a8XView\u663e\u793a\u65f6\u95f4\u5dee " + f9517j + "   \u672c\u5730\u662f\u5426\u6709\u914d\u7f6e\u4fe1\u606f " + f9518k);
    }

    public b(JDJSONObject jDJSONObject) {
        super(jDJSONObject);
        this.a = new AtomicBoolean(false);
        this.f9521e = "";
    }

    public static void c(com.jingdong.app.mall.home.floor.ctrl.t.a aVar) {
        f9519l = aVar == null ? null : aVar.a();
    }

    public static void d(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null) {
            return;
        }
        f9519l = com.jingdong.app.mall.home.r.c.b.c("");
        String optString = jSONObject.optString("pay");
        com.jingdong.app.mall.home.r.c.b bVar = f9519l;
        if (TextUtils.isEmpty(optString)) {
            optString = "2";
        }
        bVar.a("pay", optString);
        String optString2 = jSONObject.optString("openApp");
        f9519l.a("opentype", TextUtils.isEmpty(optString2) ? "2" : optString2);
        f9519l.a("source", jSONObject2.optString("source"));
    }

    private String e(com.jingdong.app.mall.home.floor.ctrl.t.a aVar, ChannelInfo channelInfo, ChannelInfo channelInfo2) {
        JSONObject jSONObject = new JSONObject();
        int i2 = 1;
        try {
            if (aVar != null) {
                aVar.c(jSONObject);
                jSONObject.put("hasConfig", 1);
            } else {
                if (channelInfo != null && channelInfo.getInfo() != null) {
                    jSONObject.put("first", channelInfo.getInfo());
                }
                if (channelInfo2 != null && channelInfo2.getInfo() != null) {
                    jSONObject.put("last", channelInfo2.getInfo());
                }
                if (!f9518k) {
                    i2 = 0;
                }
                jSONObject.put("hasConfig", i2);
            }
            com.jingdong.app.mall.home.o.a.f.h0(jSONObject);
            com.jingdong.app.mall.home.o.a.f.y(jSONObject);
            com.jingdong.app.mall.home.o.a.f.A(jSONObject);
            jSONObject.put("fQueryStamp", com.jingdong.app.mall.home.b.f8602m + "");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static boolean f() {
        return f9518k;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        if (!f9518k) {
            f9518k = true;
            com.jingdong.app.mall.home.o.a.f.y0("bk_has_config_flag", 1);
        }
        long max = Math.max(com.jingdong.app.mall.home.r.e.b.getJsonLong(jDJSONObject, "firstUpdateTime", 0L), 0L) * 1000;
        CommonBase.putLongToPreference(ChannelInfo.BABEL_CHANNEL_INTERVAL, max);
        ChannelInfo.setBabelInterval(max);
        long max2 = Math.max(com.jingdong.app.mall.home.r.e.b.getJsonLong(jDJSONObject, "clientRequestTime", 0L), 0L) * 1000;
        if (f9516i != max2) {
            f9516i = max2;
            com.jingdong.app.mall.home.o.a.f.z0("bk_request_interval", max2);
        }
        long max3 = Math.max(com.jingdong.app.mall.home.r.e.b.getJsonLong(jDJSONObject, "firstTimeSpan", 0L), 0L) * 1000;
        if (f9514g != max3) {
            f9514g = max3;
            com.jingdong.app.mall.home.o.a.f.z0("bk_first_last_interval", max3);
        }
        long max4 = Math.max(com.jingdong.app.mall.home.r.e.b.getJsonLong(jDJSONObject, "lastTimeSpan", 0L), 0L) * 1000;
        if (f9515h != max4) {
            f9515h = max4;
            com.jingdong.app.mall.home.o.a.f.z0("bk_last_interval", max4);
        }
        long max5 = Math.max(com.jingdong.app.mall.home.r.e.b.getJsonLong(jDJSONObject, "startXViewTimeSpan", 0L), 0L) * 1000;
        if (f9517j != max5) {
            f9517j = max5;
            com.jingdong.app.mall.home.o.a.f.z0("bk_launch_interval", max5);
        }
        s("update: firstUpdateTime = " + max + "   \u5ba2\u6237\u7aef\u8bf7\u6c42\u5468\u671f " + f9516i + "   \u521d\u672b\u6b21\u4fe1\u606f\u65f6\u95f4\u5dee " + f9514g + "   \u672b\u6b21\u4fe1\u606f\u6709\u6548\u65f6\u95f4\u5dee " + f9515h + "   \u542f\u52a8XView\u663e\u793a\u65f6\u95f4\u5dee " + f9517j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(JDJSONObject jDJSONObject) {
        String str;
        this.srcJson = jDJSONObject;
        this.b = getJsonString("closeLog");
        this.f9520c = getJsonString("expoLog");
        this.d = getJsonString("clkLog");
        String jsonString = getJsonString("xviewId");
        JumpEntity jumpEntity = (JumpEntity) getObject("jump", JumpEntity.class);
        if (jumpEntity != null) {
            this.f9521e = jumpEntity.getSrvJson();
            str = JDJSON.parseObject(jumpEntity.params).optString("url");
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(jsonString)) {
            s(" url \u4e0d\u7b26");
            l();
            return;
        }
        k(new d(str, jsonString));
    }

    public static boolean i(ChannelInfo channelInfo, ChannelInfo channelInfo2, long j2, long j3) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - j3 < f9517j) {
            s(" \u542f\u52a8XView\u5c55\u793a\u5468\u671f \u4e0d\u7b26");
            return false;
        } else if (elapsedRealtime - j2 < f9516i) {
            s(" \u5ba2\u6237\u7aef\u8bf7\u6c42\u5468\u671f \u4e0d\u7b26");
            return false;
        } else {
            long saveTime = channelInfo2.getSaveTime();
            if (elapsedRealtime - saveTime > f9515h) {
                s(" \u672b\u6b21\u4fe1\u606f\u6709\u6548\u65f6\u95f4\u5dee \u4e0d\u7b26");
                return false;
            }
            boolean z = saveTime - channelInfo.getSaveTime() < f9514g;
            if (!z) {
                s(" \u521d\u672b\u6b21\u4fe1\u606f\u65f6\u95f4\u5dee \u4e0d\u7b26");
            }
            d(channelInfo.getInfo(), channelInfo2.getInfo());
            return z;
        }
    }

    public static boolean j(ChannelInfo channelInfo, ChannelInfo channelInfo2, ChannelInfo channelInfo3) {
        if (channelInfo == null || channelInfo2 == null) {
            s(" \u672a\u76d1\u6d4b\u5230\u901a\u5929\u5854\u4fe1\u606f");
            return false;
        } else if (channelInfo2 == channelInfo3) {
            s(" \u672b\u6b21\u4fe1\u606f\u672a\u53d8\u5316");
            return false;
        } else {
            JDHomeFragment z0 = JDHomeFragment.z0();
            s("firstInfo: " + channelInfo.toString(), " \r\nLastInfo: " + channelInfo2.toString());
            JSONObject info = channelInfo.getInfo();
            if (z0 != null && info != null) {
                long A0 = z0.A0();
                if (TextUtils.equals(info.optString("openApp"), "1") || (A0 > 0 && channelInfo.getSaveTime() - A0 <= Final.FIVE_SECOND)) {
                    return true;
                }
                s(" \u521d\u6b21\u4fe1\u606f\u975eopenApp\u6253\u5f00 \u4e0d\u7b26 openAppTime" + A0 + " \u65f6\u95f4\u5dee" + (channelInfo.getSaveTime() - A0));
                return false;
            }
            s(" \u521d\u6b21\u4fe1\u606f\u4e0d\u7b26\u5408\u89c4\u8303");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void m() {
        com.jingdong.app.mall.home.r.c.b bVar = f9519l;
        if (bVar != null) {
            com.jingdong.app.mall.home.r.c.a.y("Home_BackXviewBlock", "", bVar.toString());
        }
    }

    private void n(boolean z) {
        com.jingdong.app.mall.home.r.c.b bVar = f9519l;
        if (bVar != null) {
            com.jingdong.app.mall.home.r.c.a.y(z ? "Home_BackXviewConfig" : "Home_BackXviewApply", "", bVar.toString());
        }
    }

    public static void s(Object... objArr) {
        com.jingdong.app.mall.home.o.a.k.f(objArr);
        if (OKLog.D) {
            com.jingdong.app.mall.home.o.a.f.r0(f9513f, objArr);
        }
    }

    abstract void k(d dVar);

    abstract void l();

    public void o() {
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(this.f9521e);
        new com.jingdong.app.mall.home.q.a("\u56de\u9000\u5e72\u9884XView\u70b9\u51fb", this.d).b();
        c2.put("clickloc", "-100");
        c2.put("jumptype", "-100");
        com.jingdong.app.mall.home.r.c.a.s("Home_BackXviewClick", "", c2.toString());
    }

    public void p(int i2, String str) {
        boolean z = true;
        if (i2 == 1) {
            return;
        }
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(str);
        boolean z2 = !TextUtils.isEmpty(c2.optString("url"));
        if (i2 != 3 && i2 != 2) {
            z = false;
        }
        if (z2) {
            new com.jingdong.app.mall.home.q.a("\u56de\u9000\u5e72\u9884XView\u70b9\u51fb", this.d).b();
        }
        c2.put("jumptype", z2 ? "0" : "1");
        c2.put("clickloc", z ? "0" : "1");
        com.jingdong.app.mall.home.r.c.a.s("Home_BackXviewClick", "", c2.toString());
    }

    public void q() {
        new com.jingdong.app.mall.home.q.a("\u56de\u9000\u5e72\u9884XView\u5173\u95ed", this.b).b();
        com.jingdong.app.mall.home.r.c.a.s("Home_BackXviewClose", "", this.f9521e);
    }

    public void r() {
        if (this.a.getAndSet(true)) {
            return;
        }
        new com.jingdong.app.mall.home.q.a("\u56de\u9000\u5e72\u9884XView\u663e\u793a", this.f9520c).b();
        com.jingdong.app.mall.home.r.c.a.y("Home_BackXviewExpo", "", this.f9521e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t(com.jingdong.app.mall.home.floor.ctrl.t.a aVar, ChannelInfo channelInfo, ChannelInfo channelInfo2, boolean z) {
        if (com.jingdong.app.mall.home.o.a.f.j0()) {
            return;
        }
        s(" \u8bf7\u6c42backXView\u4fe1\u606f");
        n(z);
        com.jingdong.app.mall.home.o.a.f.C0("backXView", e(aVar, channelInfo, channelInfo2), new a(z));
    }
}
