package com.jingdong.manto.m.o1;

import android.animation.ObjectAnimator;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.j;
import com.jingdong.manto.q.q;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends d0 {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ com.jingdong.manto.widget.d a;
        final /* synthetic */ ObjectAnimator b;

        a(e eVar, com.jingdong.manto.widget.d dVar, ObjectAnimator objectAnimator) {
            this.a = dVar;
            this.b = objectAnimator;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.setVisibility(0);
            this.a.a(this.b, (Runnable) null);
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        String putErrMsg;
        j firstPage = hVar.h().f13014f.getFirstPage();
        if (firstPage instanceof q) {
            com.jingdong.manto.widget.d dVar = ((q) firstPage).f14092i;
            if (dVar.getVisibility() != 0) {
                boolean optBoolean = jSONObject.optBoolean("animation", true);
                float[] fArr = new float[2];
                fArr[0] = ("top".equals(dVar.f14345c) ? -1 : 1) * dVar.getHeight();
                fArr[1] = 0.0f;
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(dVar, "translationY", fArr);
                ofFloat.setDuration(optBoolean ? 250L : 0L);
                dVar.post(new a(this, dVar, ofFloat));
            }
            putErrMsg = putErrMsg(IMantoBaseModule.SUCCESS, null, str);
        } else {
            putErrMsg = putErrMsg("fail:page not ready", null, str);
        }
        hVar.a(i2, putErrMsg);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "showTabBar";
    }
}
