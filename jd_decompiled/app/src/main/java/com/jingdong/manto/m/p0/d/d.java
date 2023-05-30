package com.jingdong.manto.m.p0.d;

import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.m.e0;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d extends d0 {

    /* loaded from: classes15.dex */
    class a extends com.jingdong.manto.m.p0.e.c {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ h b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13523c;

        a(JSONObject jSONObject, h hVar, int i2) {
            this.a = jSONObject;
            this.b = hVar;
            this.f13523c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar;
            int i2;
            d dVar;
            String str;
            String optString = this.a.optString(CartConstant.KEY_OPERATIONTYPE);
            double optDouble = this.a.optDouble("currentTime", -1.0d);
            if (TextUtils.isEmpty(optString)) {
                hVar = this.b;
                i2 = this.f13523c;
                dVar = d.this;
                str = "fail:operationType is null";
            } else {
                if (optString.equalsIgnoreCase("play")) {
                    com.jingdong.manto.m.p0.d.a.c(this.b);
                } else if (optString.equalsIgnoreCase("pause")) {
                    com.jingdong.manto.m.p0.d.a.b(this.b);
                } else if (optString.equalsIgnoreCase("stop")) {
                    com.jingdong.manto.m.p0.d.a.d(this.b);
                } else if (optString.equalsIgnoreCase(JsApiVideoPlayer.CM_SEEK)) {
                    com.jingdong.manto.m.p0.d.a.a(this.b, optDouble);
                }
                hVar = this.b;
                i2 = this.f13523c;
                dVar = d.this;
                str = IMantoBaseModule.SUCCESS;
            }
            hVar.a(i2, dVar.putErrMsg(str));
        }
    }

    /* loaded from: classes15.dex */
    public static final class b extends com.jingdong.manto.m.d {
        private static final b d = new b();

        /* JADX INFO: Access modifiers changed from: package-private */
        public static synchronized void a(h hVar) {
            synchronized (b.class) {
                synchronized (b.class) {
                    d.a((e0) hVar).a();
                }
            }
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onBackgroundAudioNext";
        }
    }

    /* loaded from: classes15.dex */
    public static final class c extends com.jingdong.manto.m.d {
        private static final c d = new c();

        public static synchronized void a(h hVar) {
            synchronized (c.class) {
                synchronized (c.class) {
                    d.a((e0) hVar).a();
                }
            }
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onBackgroundAudioPrev";
        }
    }

    /* renamed from: com.jingdong.manto.m.p0.d.d$d  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public static final class C0593d extends com.jingdong.manto.m.d {
        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onBackgroundAudioStateChange";
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        if (jSONObject == null) {
            hVar.a(i2, putErrMsg("fail:data is null"));
            return;
        }
        hVar.a();
        new a(jSONObject, hVar, i2).a();
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "operateBackgroundAudio";
    }
}
