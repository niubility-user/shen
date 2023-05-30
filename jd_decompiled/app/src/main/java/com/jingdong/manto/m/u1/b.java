package com.jingdong.manto.m.u1;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.base.MantoCallback;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends AbstractMantoViewManager {
    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public MantoLifecycleLisener addLifecycleLisener(Bundle bundle) {
        return f.a(bundle.getString("appUniqueId") + bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY)).a();
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore, Bundle bundle) {
        JSONObject jSONObject;
        String str;
        e eVar = new e((com.jingdong.manto.e) mantoCore);
        int i2 = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        String string = bundle.getString("json");
        String string2 = bundle.getString("sourceType");
        bundle.getString("appUniqueId");
        String str2 = bundle.getString("appUniqueId") + i2;
        try {
            jSONObject = new JSONObject(string);
        } catch (JSONException e2) {
            e = e2;
            jSONObject = null;
        }
        try {
            str = new JSONObject(jSONObject.optString("data")).optString("nodeId");
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
            str = "";
            eVar.a(jSONObject.optBoolean("disableScroll", false));
            eVar.b(jSONObject.optBoolean("gesture", false));
            eVar.a(i2);
            eVar.b(jSONObject.optInt("left"), jSONObject.optInt("top"), jSONObject.optInt("width"), jSONObject.optInt("height"));
            f.a(str2, eVar);
            return new CoverViewContainer(com.jingdong.a.g(), eVar.a(true, string2, str));
        }
        eVar.a(jSONObject.optBoolean("disableScroll", false));
        eVar.b(jSONObject.optBoolean("gesture", false));
        eVar.a(i2);
        eVar.b(jSONObject.optInt("left"), jSONObject.optInt("top"), jSONObject.optInt("width"), jSONObject.optInt("height"));
        f.a(str2, eVar);
        return new CoverViewContainer(com.jingdong.a.g(), eVar.a(true, string2, str));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final int getInsertIndex() {
        return R2.attr.drawableTint;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return "Canvas2DView";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final int getRemoveIndex() {
        return R2.attr.drawableTitle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final int getUpdateIndex() {
        return R2.attr.drawableTintMode;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final String getViewName() {
        return "SkiaCanvas";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleInsertData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("canvasId"));
        bundle.putString("json", jSONObject.toString());
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            String optString = optJSONObject != null ? optJSONObject.optString("type") : null;
            if (TextUtils.isEmpty(optString)) {
                optString = "canvas";
            }
            bundle.putString("sourceType", optString);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public void handleMethod(String str, View view, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleRemoveData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("canvasId"));
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleUpdateData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("canvasId"));
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    protected void injectJsApiMethod(List<JsApiMethod> list) {
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewInsert(Bundle bundle, View view, MantoCore mantoCore) {
        return true;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewRemove(Bundle bundle, View view, MantoCore mantoCore) {
        String str = bundle.getString("appUniqueId") + bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        f.a(str).b();
        f.b(str);
        return true;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle) {
        return true;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle, MantoCallback mantoCallback) {
        return true;
    }
}
