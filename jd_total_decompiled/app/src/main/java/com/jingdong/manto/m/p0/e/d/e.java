package com.jingdong.manto.m.p0.e.d;

import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.pkg.b.g;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.s;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends d0 {

    /* loaded from: classes15.dex */
    class a extends com.jingdong.manto.m.p0.e.c {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ boolean f13531c;
        final /* synthetic */ float d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f13532e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f13533f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ h f13534g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f13535h;

        a(String str, boolean z, boolean z2, float f2, int i2, String str2, h hVar, int i3) {
            this.a = str;
            this.b = z;
            this.f13531c = z2;
            this.d = f2;
            this.f13532e = i2;
            this.f13533f = str2;
            this.f13534g = hVar;
            this.f13535h = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar;
            int i2;
            e eVar;
            String str;
            boolean booleanValue;
            String str2;
            try {
                Pair<Boolean, String> a = com.jingdong.manto.m.p0.e.b.a(this.a, this.b, this.f13531c, this.d, this.f13532e, this.f13533f);
                booleanValue = ((Boolean) a.first).booleanValue();
                str2 = (String) a.second;
            } catch (Throwable unused) {
                hVar = this.f13534g;
                i2 = this.f13535h;
                eVar = e.this;
                str = "fail:set audio state fail";
            }
            if (booleanValue) {
                hVar = this.f13534g;
                i2 = this.f13535h;
                eVar = e.this;
                str = IMantoBaseModule.SUCCESS;
                hVar.a(i2, eVar.putErrMsg(str));
                return;
            }
            this.f13534g.a(this.f13535h, e.this.putErrMsg("fail:" + str2));
        }
    }

    /* loaded from: classes15.dex */
    public static class b extends com.jingdong.manto.m.d {
        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onAudioStateChange";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c0  */
    @Override // com.jingdong.manto.m.d0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        String e2;
        String str2;
        String str3;
        String str4;
        hVar.a();
        if (!isAppForeground(hVar)) {
            MantoLog.d("Audio.SetAudioState", "can't do setAudioState, App is paused or background");
            str4 = "fail:App is paused or background";
        } else if (jSONObject != null) {
            String optString = jSONObject.optString("audioId");
            int optInt = jSONObject.optInt("startTime", 0) / 1000;
            String optString2 = jSONObject.optString("src");
            if (!TextUtils.isEmpty(optString2)) {
                if (optString2.startsWith("jdfile://")) {
                    String replace = optString2.replace("jdfile://", "");
                    com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(hVar.h().f13019k, optString2);
                    if (g2 != null && !TextUtils.isEmpty(g2.b)) {
                        e2 = com.jingdong.manto.t.c.e(hVar.c(), replace);
                        if (!s.d(e2)) {
                            s.a(g2.b, e2);
                        }
                    }
                } else if (optString2.startsWith("http")) {
                    str2 = optString2;
                    boolean optBoolean = jSONObject.optBoolean("autoplay", false);
                    boolean optBoolean2 = jSONObject.optBoolean(JDPureVideoManager.SourceKey.LOOP, false);
                    float optDouble = (float) jSONObject.optDouble("volume", 1.0d);
                    if (TextUtils.isEmpty(optString)) {
                        str3 = "fail:audioId is empty";
                    } else if (!TextUtils.isEmpty(str2)) {
                        new a(optString, optBoolean, optBoolean2, optDouble, optInt, str2, hVar, i2).a();
                        return;
                    } else {
                        str3 = "fail:src is empty";
                    }
                    hVar.a(i2, putErrMsg(str3));
                    return;
                } else {
                    e2 = com.jingdong.manto.t.c.e(hVar.c(), optString2);
                    if (!s.d(e2)) {
                        s.a(g.d(hVar.h(), optString2), e2);
                    }
                }
                str2 = e2;
                boolean optBoolean3 = jSONObject.optBoolean("autoplay", false);
                boolean optBoolean22 = jSONObject.optBoolean(JDPureVideoManager.SourceKey.LOOP, false);
                float optDouble2 = (float) jSONObject.optDouble("volume", 1.0d);
                if (TextUtils.isEmpty(optString)) {
                }
                hVar.a(i2, putErrMsg(str3));
                return;
            }
            str2 = "";
            boolean optBoolean32 = jSONObject.optBoolean("autoplay", false);
            boolean optBoolean222 = jSONObject.optBoolean(JDPureVideoManager.SourceKey.LOOP, false);
            float optDouble22 = (float) jSONObject.optDouble("volume", 1.0d);
            if (TextUtils.isEmpty(optString)) {
            }
            hVar.a(i2, putErrMsg(str3));
            return;
        } else {
            MantoLog.d("Audio.SetAudioState", "setAudioState data is null");
            str4 = "fail:data is null";
        }
        hVar.a(i2, putErrMsg(str4));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setAudioState";
    }
}
