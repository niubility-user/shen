package com.jingdong.manto.m.p0.e.d;

import android.text.TextUtils;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoLog;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends d0 {

    /* loaded from: classes15.dex */
    class a extends com.jingdong.manto.m.p0.e.c {
        final /* synthetic */ h a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13527c;

        a(h hVar, String str, int i2) {
            this.a = hVar;
            this.b = str;
            this.f13527c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a == null) {
                MantoLog.e("Audio.GetAudioState", "service is null");
                return;
            }
            try {
                JSONObject c2 = com.jingdong.manto.m.p0.e.b.c(this.b);
                if (c2 == null) {
                    this.a.a(this.f13527c, c.this.putErrMsg("fail:return parameter is invalid"));
                    return;
                }
                int optInt = c2.optInt("duration");
                if (optInt < 0) {
                    this.a.a(this.f13527c, c.this.putErrMsg("fail:return parameter is invalid"));
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("duration", Integer.valueOf(optInt));
                hashMap.put("currentTime", c2.opt("currentTime"));
                hashMap.put("paused", c2.opt("paused"));
                hashMap.put("buffered", c2.opt("buffered"));
                hashMap.put("src", c2.opt("src"));
                hashMap.put("startTime", c2.opt("startTime"));
                hashMap.put(JsApiVideoPlayer.CM_PLAY_RATE, c2.opt(JsApiVideoPlayer.CM_PLAY_RATE));
                this.a.a(this.f13527c, c.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap, "getAudioState"));
            } catch (Throwable th) {
                MantoLog.e("Audio.GetAudioState", "getAudioState fail", th);
                this.a.a(this.f13527c, c.this.putErrMsg("fail:internal error"));
            }
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        if (jSONObject == null) {
            MantoLog.e("Audio.GetAudioState", "getAudioState data is null");
            hVar.a(i2, putErrMsg("fail:data is null"));
            return;
        }
        MantoLog.d("Audio.GetAudioState", "getAudioState data:" + jSONObject);
        String optString = jSONObject.optString("audioId");
        if (!TextUtils.isEmpty(optString)) {
            new a(hVar, optString, i2).a();
            return;
        }
        MantoLog.e("Audio.GetAudioState", "getAudioState audioId is empty");
        hVar.a(i2, putErrMsg("fail:audioId is empty"));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getAudioState";
    }
}
