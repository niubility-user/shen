package com.jingdong.manto.m.p1;

import android.os.Bundle;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModuleGen2;
import com.jingdong.manto.jsapi.openmodule.ApiWorker;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public final class a extends AbstractMantoModuleGen2 {
    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModuleGen2
    protected ApiWorker genApiWorker(AbstractMantoModuleGen2 abstractMantoModuleGen2, String str) {
        return new b(abstractMantoModuleGen2, str);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return XView2Constants.XVIEW2_ACTION_TOAST;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("params", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("showToast", 1));
        list.add(new JsApiMethod("hideToast", 1));
    }
}
