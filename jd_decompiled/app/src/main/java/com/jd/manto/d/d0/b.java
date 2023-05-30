package com.jd.manto.d.d0;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.base.MantoCallback;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class b extends AbstractMantoViewManager {

    /* loaded from: classes17.dex */
    class a implements MantoLifecycleLisener {
        a(b bVar) {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onBackground() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onDestroy() {
            c.o(com.jingdong.a.g());
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onForeground() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onPause() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onReady() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public boolean onRemove() {
            c.o(com.jingdong.a.g());
            return false;
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public MantoLifecycleLisener addLifecycleLisener(Bundle bundle) {
        return new a(this);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore, Bundle bundle) {
        JSONObject jSONObject;
        View e2;
        try {
            jSONObject = new JSONObject(bundle.getString("params"));
        } catch (Exception unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("mode");
        if (TextUtils.equals(optString, "camera")) {
            e2 = c.d(mantoCore.getActivity());
        } else {
            e2 = TextUtils.equals(optString, "video") ? c.e(mantoCore.getActivity()) : null;
        }
        if (e2 != null) {
            if (e2.getParent() != null) {
                ((ViewGroup) e2.getParent()).removeView(e2);
            }
            return new CoverViewContainer(mantoCore.getActivity(), e2);
        }
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "RTCView";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public String getViewName() {
        return "RTCView";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public Bundle handleInsertData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle(1);
        bundle.putString("params", jSONObject.toString());
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("viewId"));
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public void handleMethod(String str, View view, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public Bundle handleRemoveData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle(1);
        bundle.putString("params", jSONObject.toString());
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("viewId"));
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public Bundle handleUpdateData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle(1);
        bundle.putString("params", jSONObject.toString());
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("viewId"));
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
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("params"));
        } catch (Exception unused) {
            jSONObject = null;
        }
        return jSONObject != null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewRemove(Bundle bundle, View view, MantoCore mantoCore) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("params"));
        } catch (Exception unused) {
            jSONObject = null;
        }
        return jSONObject != null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("params"));
        } catch (Exception unused) {
            jSONObject = null;
        }
        return jSONObject != null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle, MantoCallback mantoCallback) {
        return true;
    }
}
