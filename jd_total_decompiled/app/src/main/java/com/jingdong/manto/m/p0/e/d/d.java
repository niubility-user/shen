package com.jingdong.manto.m.p0.e.d;

import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d extends d0 {

    /* loaded from: classes15.dex */
    class a extends com.jingdong.manto.m.p0.e.c {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ h f13528c;
        final /* synthetic */ int d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f13529e;

        a(String str, String str2, h hVar, int i2, int i3) {
            this.a = str;
            this.b = str2;
            this.f13528c = hVar;
            this.d = i2;
            this.f13529e = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a.equalsIgnoreCase("play")) {
                Pair<Boolean, String> f2 = com.jingdong.manto.m.p0.e.b.f(this.b);
                boolean booleanValue = ((Boolean) f2.first).booleanValue();
                String str = (String) f2.second;
                if (!booleanValue) {
                    this.f13528c.a(this.d, d.this.putErrMsg("fail:" + str));
                    return;
                }
            } else if (this.a.equalsIgnoreCase("pause")) {
                Pair<Boolean, String> d = com.jingdong.manto.m.p0.e.b.d(this.b);
                boolean booleanValue2 = ((Boolean) d.first).booleanValue();
                String str2 = (String) d.second;
                if (!booleanValue2) {
                    this.f13528c.a(this.d, d.this.putErrMsg("fail:" + str2));
                    return;
                }
            } else if (this.a.equalsIgnoreCase(JsApiVideoPlayer.CM_SEEK)) {
                int i2 = this.f13529e;
                if (i2 < 0) {
                    return;
                }
                Pair<Boolean, String> a = com.jingdong.manto.m.p0.e.b.a(this.b, i2);
                boolean booleanValue3 = ((Boolean) a.first).booleanValue();
                String str3 = (String) a.second;
                if (!booleanValue3) {
                    this.f13528c.a(this.d, d.this.putErrMsg("fail:" + str3));
                    return;
                }
            } else if (!this.a.equalsIgnoreCase("stop")) {
                MantoLog.e("Audio.OperateAudio", "operationType is invalid");
                this.f13528c.a(this.d, d.this.putErrMsg("fail:operationType is invalid"));
                return;
            } else {
                Pair<Boolean, String> g2 = com.jingdong.manto.m.p0.e.b.g(this.b);
                boolean booleanValue4 = ((Boolean) g2.first).booleanValue();
                String str4 = (String) g2.second;
                if (!booleanValue4) {
                    this.f13528c.a(this.d, d.this.putErrMsg("fail:" + str4));
                    return;
                }
            }
            this.f13528c.a(this.d, d.this.putErrMsg(IMantoBaseModule.SUCCESS));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        String str3;
        hVar.a();
        if (!isAppForeground(hVar)) {
            MantoLog.d("Audio.OperateAudio", "can't do operateAudio, App is paused or background");
            str3 = "fail:App is paused or background";
        } else if (jSONObject != null) {
            String optString = jSONObject.optString("audioId");
            int optInt = jSONObject.optInt("currentTime", 0);
            String optString2 = jSONObject.optString(CartConstant.KEY_OPERATIONTYPE);
            if (TextUtils.isEmpty(optString)) {
                MantoLog.e("Audio.OperateAudio", "audioId is empty");
                str2 = "fail:audioId is empty";
            } else if (!TextUtils.isEmpty(optString2)) {
                new a(optString2, optString, hVar, i2, optInt).a();
                return;
            } else {
                MantoLog.e("Audio.OperateAudio", "operationType is empty");
                str2 = "fail:operationType is empty";
            }
            hVar.a(i2, putErrMsg(str2));
            return;
        } else {
            MantoLog.d("Audio.OperateAudio", "operateAudio data is null");
            str3 = "fail:data is null";
        }
        hVar.a(i2, putErrMsg(str3));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "operateAudio";
    }
}
