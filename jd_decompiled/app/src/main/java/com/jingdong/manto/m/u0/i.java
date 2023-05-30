package com.jingdong.manto.m.u0;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.q.n;
import com.jingdong.manto.widget.canvas.MantoDrawableView;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class i extends com.jingdong.manto.jsapi.base.a {

    /* loaded from: classes15.dex */
    class a implements n.e0 {
        final /* synthetic */ MantoDrawableView a;

        a(i iVar, MantoDrawableView mantoDrawableView) {
            this.a = mantoDrawableView;
        }

        @Override // com.jingdong.manto.q.n.e0
        public void onForeground() {
            this.a.b();
        }
    }

    @Override // com.jingdong.manto.jsapi.base.d
    public int a(JSONObject jSONObject) {
        return jSONObject.optInt("canvasId");
    }

    @Override // com.jingdong.manto.jsapi.base.a
    public View a(com.jingdong.manto.q.n nVar, JSONObject jSONObject) {
        String str;
        String str2;
        Context context = nVar.f14071i;
        int optInt = jSONObject.optInt("canvasId");
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (optJSONObject == null) {
            String optString = jSONObject.optString("data");
            if (!TextUtils.isEmpty(optString)) {
                try {
                    optJSONObject = new JSONObject(optString);
                } catch (Throwable unused) {
                }
            }
        }
        if (optJSONObject != null) {
            str = optJSONObject.optString("nodeId");
            str2 = optJSONObject.optString("type");
        } else {
            str = "";
            str2 = null;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "canvas";
        }
        MantoDrawableView mantoDrawableView = new MantoDrawableView(context);
        mantoDrawableView.getDrawContext().b = com.jingdong.manto.utils.b.a();
        mantoDrawableView.getDrawContext().f13728c = nVar;
        mantoDrawableView.a(optInt, str2, str);
        mantoDrawableView.setContentDescription("canvas");
        nVar.a(new a(this, mantoDrawableView));
        return new CoverViewContainer(context, mantoDrawableView);
    }

    @Override // com.jingdong.manto.jsapi.base.a
    public void a(boolean z, com.jingdong.manto.q.n nVar, int i2, View view, JSONObject jSONObject) {
        View convertTo = ((CoverViewContainer) view).convertTo(View.class);
        if (convertTo instanceof MantoDrawableView) {
            ((MantoDrawableView) convertTo).setEmbeddedCanvas(z);
        }
    }

    @Override // com.jingdong.manto.jsapi.base.a
    public boolean b() {
        return true;
    }

    @Override // com.jingdong.manto.jsapi.base.a
    public boolean c() {
        return true;
    }

    @Override // com.jingdong.manto.jsapi.base.d, com.jingdong.manto.m.a
    public String getJsApiName() {
        return "insertCanvas";
    }
}
