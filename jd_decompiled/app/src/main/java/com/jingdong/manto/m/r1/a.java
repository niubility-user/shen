package com.jingdong.manto.m.r1;

import com.jingdong.manto.f;
import com.jingdong.manto.h;
import com.jingdong.manto.i.c;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoThreadUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends d0 {

    /* renamed from: com.jingdong.manto.m.r1.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class RunnableC0612a implements Runnable {
        final /* synthetic */ h a;

        RunnableC0612a(h hVar) {
            this.a = hVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a.this.getCore(this.a).getActivity().finish();
                f h2 = this.a.h();
                LaunchParam launchParam = new LaunchParam();
                c cVar = h2.r;
                launchParam.appId = cVar.a;
                launchParam.debugType = cVar.f13082e;
                launchParam.extrasJson = cVar.f13090m;
                launchParam.launchPath = cVar.f13083f;
                com.jingdong.a.o(launchParam);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        MantoThreadUtils.runOnUIThread(new RunnableC0612a(hVar));
        hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "applyUpdate";
    }
}
