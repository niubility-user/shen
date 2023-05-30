package com.jingdong.manto.m.p0.f;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer;
import com.jingdong.manto.m.p0.f.e.a;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.n;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d {

    /* renamed from: h  reason: collision with root package name */
    private static d f13537h = new d();
    private volatile boolean a;
    private volatile boolean b;

    /* renamed from: c  reason: collision with root package name */
    private List<String> f13538c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f13539e;

    /* renamed from: f  reason: collision with root package name */
    private a.e f13540f;

    /* renamed from: g  reason: collision with root package name */
    private c f13541g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements com.jingdong.manto.m.p0.f.b {
        a() {
        }

        @Override // com.jingdong.manto.m.p0.f.b
        public void a() {
            d.this.d();
        }

        @Override // com.jingdong.manto.m.p0.f.b
        public void a(String str) {
            d.this.a(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements com.jingdong.manto.m.p0.f.b {
        b() {
        }

        @Override // com.jingdong.manto.m.p0.f.b
        public void a() {
            d.this.d();
        }

        @Override // com.jingdong.manto.m.p0.f.b
        public void a(String str) {
            d.this.a(str);
        }
    }

    private d() {
        ArrayList arrayList = new ArrayList();
        this.f13538c = arrayList;
        arrayList.add("aac");
        this.f13538c.add("wav");
    }

    public static d a() {
        return f13537h;
    }

    private void a(int i2, int i3, int i4, String str, int i5) {
        if (this.f13541g == null) {
            return;
        }
        try {
            String str2 = n.b + "record-" + System.currentTimeMillis();
            this.f13539e = OrderISVUtil.MONEY_DECIMAL + str;
            String str3 = str2 + this.f13539e;
            this.d = str3;
            this.f13541g.a(str3, i2, i3, i4, i5);
            this.f13541g.a();
            this.f13541g.start();
            this.a = true;
            this.b = false;
            this.f13540f.b("start");
        } catch (Throwable th) {
            MantoLog.e("Audio.RecorderManager", "start record", th);
            this.f13540f.a("error", "start record error");
        }
    }

    private void c() {
        c cVar = this.f13541g;
        if (cVar == null) {
            return;
        }
        try {
            cVar.resume();
            this.b = false;
            this.a = true;
            this.f13540f.b(JsApiLivePlayer.CM_RESUME);
        } catch (Throwable unused) {
            this.f13540f.a("error", "resume record error");
        }
    }

    public void a(a.e eVar) {
        String str;
        JSONObject jSONObject = eVar.a;
        if (jSONObject == null) {
            str = "data is empty";
        } else {
            this.f13540f = eVar;
            String optString = jSONObject.optString(CartConstant.KEY_OPERATIONTYPE);
            if ("start".equalsIgnoreCase(optString)) {
                if (!this.a) {
                    int optInt = jSONObject.optInt("duration", 60000);
                    int i2 = (optInt > 60000 || optInt <= 0) ? 60000 : optInt;
                    int optInt2 = jSONObject.optInt("sampleRate", 8000);
                    int optInt3 = jSONObject.optInt("encodeBitRate", 48000);
                    int optInt4 = jSONObject.optInt("numberOfChannels", 2);
                    int i3 = (optInt4 > 2 || optInt4 < 1) ? 2 : optInt4;
                    String lowerCase = jSONObject.optString("format", "aac").toLowerCase();
                    String str2 = !this.f13538c.contains(lowerCase) ? "aac" : lowerCase;
                    c cVar = this.f13541g;
                    if (cVar == null) {
                        this.f13541g = "wav".equalsIgnoreCase(str2) ? new com.jingdong.manto.m.p0.f.f.b(new a()) : new com.jingdong.manto.m.p0.f.f.a(new b());
                        this.f13541g.init();
                    } else {
                        cVar.b();
                    }
                    a(i2, optInt2, optInt3, str2, i3);
                    return;
                }
                str = "is recording";
            } else if (!"pause".equalsIgnoreCase(optString)) {
                if (JsApiLivePlayer.CM_RESUME.equalsIgnoreCase(optString)) {
                    c();
                    return;
                } else if ("stop".equalsIgnoreCase(optString)) {
                    d();
                    return;
                } else {
                    return;
                }
            } else if (!this.b) {
                b();
                return;
            } else {
                str = "already paused, can not pause";
            }
        }
        eVar.a(str);
    }

    public void a(String str) {
        a.e eVar = this.f13540f;
        if (eVar != null) {
            eVar.a("error", str);
        }
    }

    public void b() {
        c cVar = this.f13541g;
        if (cVar == null) {
            return;
        }
        try {
            cVar.pause();
            this.b = true;
            this.a = false;
            this.f13540f.b("pause");
        } catch (Throwable unused) {
            this.f13540f.a("error", "pause record error");
        }
    }

    public void d() {
        if (this.f13541g == null) {
            return;
        }
        try {
            this.a = false;
            this.f13541g.stop();
            int duration = this.f13541g.getDuration();
            this.f13541g.release();
            this.f13541g = null;
            this.b = false;
            com.jingdong.manto.t.d a2 = com.jingdong.manto.t.c.a(this.f13540f.f13542c.c(), this.d, this.f13539e, false);
            if (a2 != null) {
                this.f13540f.a("stop", a2.a, duration, new File(this.d).length());
            } else {
                this.f13540f.a("error", "save tmpFile error");
            }
        } catch (Throwable unused) {
            this.f13540f.a("error", "stop record error");
        }
    }
}
