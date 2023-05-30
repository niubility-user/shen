package com.jingdong.manto.m.j1.d;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.base.MantoCallback;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.m.d;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.c;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends AbstractMantoViewManager {

    /* loaded from: classes15.dex */
    class a implements c.b {
        final /* synthetic */ String a;
        final /* synthetic */ Reference b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13420c;

        a(String str, Reference reference, int i2) {
            this.a = str;
            this.b = reference;
            this.f13420c = i2;
        }

        @Override // com.jingdong.manto.widget.c.b
        public void a(com.jingdong.manto.widget.c cVar, int i2, int i3) {
            if (TextUtils.isEmpty(this.a)) {
                return;
            }
            ViewGroup targetView = cVar.getTargetView();
            HashMap hashMap = new HashMap();
            hashMap.put("data", this.a);
            hashMap.put("scrollLeft", Integer.valueOf(MantoDensityUtils.pixel2dip(i2)));
            hashMap.put("scrollTop", Integer.valueOf(MantoDensityUtils.pixel2dip(i3)));
            hashMap.put("scrollWidth", Integer.valueOf(MantoDensityUtils.pixel2dip(targetView.getWidth())));
            hashMap.put("scrollHeight", Integer.valueOf(MantoDensityUtils.pixel2dip(targetView.getHeight())));
            C0579b c0579b = new C0579b(b.this);
            MantoUtils.mapToJson(hashMap);
            JSONObject jSONObject = new JSONObject(hashMap);
            b.this.dispatchEvent((MantoCore) this.b.get(), c0579b.getJsApiName(), jSONObject, this.f13420c);
        }
    }

    /* renamed from: com.jingdong.manto.m.j1.d.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0579b extends d {
        C0579b(b bVar) {
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onScrollViewScroll";
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore) {
        Context g2 = com.jingdong.a.g();
        return new CoverViewContainer(g2, new com.jingdong.manto.widget.c(g2));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return "ScrollView";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final String getViewName() {
        return "ScrollView";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleInsertData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("viewId"));
        bundle.putBoolean("abg", false);
        bundle.putBoolean("enableLongClick", false);
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final void handleMethod(String str, View view, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleRemoveData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("viewId"));
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleUpdateData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("viewId"));
        bundle.putBoolean("abg", false);
        bundle.putBoolean("abi", false);
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final boolean onViewInsert(Bundle bundle, View view, MantoCore mantoCore) {
        JSONObject jSONObject;
        bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        String string = bundle.getString("json");
        int i2 = bundle.getInt("hashCode");
        try {
            jSONObject = new JSONObject(string);
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        com.jingdong.manto.widget.c cVar = (com.jingdong.manto.widget.c) ((CoverViewContainer) view).convertTo(com.jingdong.manto.widget.c.class);
        jSONObject.optBoolean(JDPureVideoManager.SourceKey.CLICKABLE);
        jSONObject.optBoolean("transEvt");
        jSONObject.optBoolean("notCoverRect");
        boolean optBoolean = jSONObject.optBoolean("scrollX");
        boolean optBoolean2 = jSONObject.optBoolean("scrollY");
        boolean optBoolean3 = jSONObject.optBoolean("needScrollEvent");
        jSONObject.optJSONObject("contentSize");
        jSONObject.optString("sendTo", "appservice");
        String optString = jSONObject.optString("data", "");
        com.jingdong.manto.jsapi.container.b.a(view, jSONObject.optJSONObject(DeeplinkProductDetailHelper.LAYER_STYLE));
        SoftReference softReference = new SoftReference(mantoCore);
        if (cVar != null) {
            cVar.setVerticalScrollBarEnabled(optBoolean2);
            cVar.setHorizontalScrollBarEnabled(optBoolean);
            if (optBoolean3) {
                cVar.setOnScrollChangedListener(new a(optString, softReference, i2));
                return true;
            }
            return true;
        }
        return true;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final boolean onViewRemove(Bundle bundle, View view, MantoCore mantoCore) {
        return true;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle) {
        JSONObject jSONObject;
        com.jingdong.manto.widget.c cVar;
        bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        if (!(view instanceof CoverViewContainer) || (cVar = (com.jingdong.manto.widget.c) ((CoverViewContainer) view).convertTo(com.jingdong.manto.widget.c.class)) == null) {
            return false;
        }
        com.jingdong.manto.jsapi.container.b.a(view, jSONObject.optJSONObject(DeeplinkProductDetailHelper.LAYER_STYLE));
        int optInt = jSONObject.optInt("scrollTop");
        if (optInt > 0) {
            cVar.scrollTo(0, (int) MantoDensityUtils.dip2pixel(optInt));
            return true;
        }
        return true;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle, MantoCallback mantoCallback) {
        return false;
    }
}
