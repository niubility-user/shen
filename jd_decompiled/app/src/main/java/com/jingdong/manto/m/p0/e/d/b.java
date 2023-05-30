package com.jingdong.manto.m.p0.e.d;

import android.text.TextUtils;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends d0 {

    /* loaded from: classes15.dex */
    class a extends com.jingdong.manto.m.p0.e.c {
        final /* synthetic */ String a;
        final /* synthetic */ h b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13526c;

        a(String str, h hVar, int i2) {
            this.a = str;
            this.b = hVar;
            this.f13526c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoLog.e("Audio.DestroyAudio", "destroy audio audioId:" + this.a);
            if (this.b == null) {
                MantoLog.e("Audio.DestroyAudio", "destroy audio service is null");
                return;
            }
            try {
                com.jingdong.manto.m.p0.e.b.a(this.a);
                h hVar = this.b;
                if (hVar != null) {
                    hVar.a(this.f13526c, b.this.putErrMsg(IMantoBaseModule.SUCCESS));
                }
            } catch (Throwable unused) {
                h hVar2 = this.b;
                if (hVar2 != null) {
                    hVar2.a(this.f13526c, b.this.putErrMsg("fail"));
                }
            }
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        if (jSONObject == null) {
            hVar.a(i2, putErrMsg("fail:data is null"));
            return;
        }
        String optString = jSONObject.optString("audioId");
        if (!TextUtils.isEmpty(optString)) {
            new a(optString, hVar, i2).a();
            return;
        }
        MantoLog.e("Audio.DestroyAudio", "audioId is empty");
        hVar.a(i2, putErrMsg("fail:audioId is empty"));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "destroyAudioInstance";
    }
}
