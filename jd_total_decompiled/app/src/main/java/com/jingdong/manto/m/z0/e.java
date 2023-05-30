package com.jingdong.manto.m.z0;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends AbstractMantoModule {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ MantoCore a;
        final /* synthetic */ boolean b;

        a(e eVar, MantoCore mantoCore, boolean z) {
            this.a = mantoCore;
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.restoreWebViewFocus(this.b);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "richText";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle handleMethodSync(String str, MantoCore mantoCore, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (TextUtils.equals(str, "restoreWebviewFocus")) {
            try {
                MantoThreadUtils.runOnUIThread(new a(this, mantoCore, new JSONObject(bundle.getString("params")).optBoolean("focus")));
                bundle2.putString(IMantoBaseModule.ERROR_CODE, "1");
            } catch (Exception e2) {
                e2.printStackTrace();
                bundle2.putString(IMantoBaseModule.ERROR_CODE, "0");
            }
        }
        return bundle2;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("params", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("restoreWebviewFocus", 3));
    }
}
