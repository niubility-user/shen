package com.jingdong.manto.m.u0;

import android.view.View;
import com.jingdong.manto.jsapi.base.MantoCallback;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.widget.canvas.MantoDrawableView;
import com.jingdong.manto.widget.canvas.a;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class h extends com.jingdong.manto.jsapi.base.c {

    /* loaded from: classes15.dex */
    class a implements a.InterfaceC0691a {
        final /* synthetic */ MantoCallback a;

        a(MantoCallback mantoCallback) {
            this.a = mantoCallback;
        }

        @Override // com.jingdong.manto.widget.canvas.a.InterfaceC0691a
        public final void a(com.jingdong.manto.widget.canvas.b bVar) {
            this.a.invokeCallback(h.this.putErrMsg(IMantoBaseModule.SUCCESS, null));
        }
    }

    /* loaded from: classes15.dex */
    class b implements a.InterfaceC0691a {
        final /* synthetic */ MantoCallback a;

        b(MantoCallback mantoCallback) {
            this.a = mantoCallback;
        }

        @Override // com.jingdong.manto.widget.canvas.a.InterfaceC0691a
        public final void a(com.jingdong.manto.widget.canvas.b bVar) {
            this.a.invokeCallback(h.this.putErrMsg(IMantoBaseModule.SUCCESS, null));
        }
    }

    @Override // com.jingdong.manto.jsapi.base.d
    public final int a(JSONObject jSONObject) {
        return jSONObject.optInt("canvasId");
    }

    @Override // com.jingdong.manto.jsapi.base.c
    public boolean a(com.jingdong.manto.q.n nVar, int i2, View view, JSONObject jSONObject, MantoCallback mantoCallback, String str) {
        if (view instanceof CoverViewContainer) {
            View convertTo = ((CoverViewContainer) view).convertTo(View.class);
            if (convertTo instanceof MantoDrawableView) {
                System.currentTimeMillis();
                JSONArray optJSONArray = jSONObject.optJSONArray("actions");
                MantoDrawableView mantoDrawableView = (MantoDrawableView) convertTo;
                if (jSONObject.optBoolean("reserve")) {
                    mantoDrawableView.a(optJSONArray, new a(mantoCallback));
                } else {
                    mantoDrawableView.b(optJSONArray, new b(mantoCallback));
                }
                mantoDrawableView.a();
                return true;
            }
        }
        mantoCallback.invokeCallback(putErrMsg("fail", null));
        return false;
    }

    @Override // com.jingdong.manto.jsapi.base.c
    public final boolean c() {
        return true;
    }

    @Override // com.jingdong.manto.jsapi.base.d, com.jingdong.manto.m.a
    public String getJsApiName() {
        return "drawCanvas";
    }
}
