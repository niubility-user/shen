package com.jingdong.manto.m.o1;

import android.animation.ObjectAnimator;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.j;
import com.jingdong.manto.q.q;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends d0 {

    /* renamed from: com.jingdong.manto.m.o1.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class RunnableC0587a implements Runnable {
        final /* synthetic */ com.jingdong.manto.widget.d a;
        final /* synthetic */ ObjectAnimator b;

        /* renamed from: com.jingdong.manto.m.o1.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0588a implements Runnable {
            RunnableC0588a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                RunnableC0587a.this.a.setVisibility(8);
            }
        }

        RunnableC0587a(a aVar, com.jingdong.manto.widget.d dVar, ObjectAnimator objectAnimator) {
            this.a = dVar;
            this.b = objectAnimator;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b, new RunnableC0588a());
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        String putErrMsg;
        j firstPage = hVar.h().f13014f.getFirstPage();
        if (firstPage instanceof q) {
            com.jingdong.manto.widget.d dVar = ((q) firstPage).f14092i;
            if (dVar.getVisibility() != 8) {
                boolean optBoolean = jSONObject.optBoolean("animation", true);
                float[] fArr = new float[2];
                fArr[0] = 0.0f;
                fArr[1] = ("top".equals(dVar.f14345c) ? -1 : 1) * dVar.getHeight();
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(dVar, "translationY", fArr);
                ofFloat.setDuration(optBoolean ? 250L : 0L);
                dVar.post(new RunnableC0587a(this, dVar, ofFloat));
            }
            putErrMsg = putErrMsg(IMantoBaseModule.SUCCESS, null, str);
        } else {
            putErrMsg = putErrMsg("fail:page not ready", null, str);
        }
        hVar.a(i2, putErrMsg);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "hideTabBar";
    }
}
