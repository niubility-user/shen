package com.jingdong.manto.m.b1;

import com.jingdong.manto.f;
import com.jingdong.manto.h;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends d0 {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ f a;
        final /* synthetic */ JSONObject b;

        a(c cVar, f fVar, JSONObject jSONObject) {
            this.a = fVar;
            this.b = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            f fVar = this.a;
            fVar.a(fVar, this.b);
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        MantoUtils.runOnUiThread(new a(this, hVar.h(), jSONObject.optJSONObject("extraData")));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "navigateBackMiniProgram";
    }
}
