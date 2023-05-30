package com.jingdong.manto.m.j1.d;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.base.MantoCallback;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends AbstractMantoViewManager implements com.jingdong.manto.jsapi.coverview.b, com.jingdong.manto.jsapi.coverview.c {

    /* renamed from: com.jingdong.manto.m.j1.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class ViewOnClickListenerC0578a implements View.OnClickListener {
        final /* synthetic */ Reference a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13414c;
        final /* synthetic */ int d;

        ViewOnClickListenerC0578a(Reference reference, String str, String str2, int i2) {
            this.a = reference;
            this.b = str;
            this.f13414c = str2;
            this.d = i2;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            MantoCore mantoCore = (MantoCore) this.a.get();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("data", this.b);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            ViewOnClickListenerC0578a viewOnClickListenerC0578a = null;
            if ("webview".equals(this.f13414c)) {
                a.this.dispatchEventToPage(mantoCore, new e(viewOnClickListenerC0578a).getJsApiName(), jSONObject, new int[]{this.d});
            } else {
                a.this.dispatchEvent(mantoCore, new e(viewOnClickListenerC0578a).getJsApiName(), jSONObject, this.d);
            }
        }
    }

    /* loaded from: classes15.dex */
    class b implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13416c;
        final /* synthetic */ MantoCore d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f13417e;

        b(String str, int i2, int i3, MantoCore mantoCore, int i4) {
            this.a = str;
            this.b = i2;
            this.f13416c = i3;
            this.d = mantoCore;
            this.f13417e = i4;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("data", this.a);
                jSONObject.put("width", this.b);
                jSONObject.put("height", this.f13416c);
                a.this.dispatchEventToPage(this.d, new d(null).getJsApiName(), jSONObject, new int[]{this.f13417e});
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* loaded from: classes15.dex */
    class c implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ MantoCore b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13419c;

        c(String str, MantoCore mantoCore, int i2) {
            this.a = str;
            this.b = mantoCore;
            this.f13419c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("data", this.a);
                jSONObject.put("width", 0);
                jSONObject.put("height", 0);
                a.this.dispatchEventToPage(this.b, new d(null).getJsApiName(), jSONObject, new int[]{this.f13419c});
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* loaded from: classes15.dex */
    private static final class d extends com.jingdong.manto.m.d {
        private d() {
        }

        /* synthetic */ d(ViewOnClickListenerC0578a viewOnClickListenerC0578a) {
            this();
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onImageViewLoad";
        }
    }

    /* loaded from: classes15.dex */
    private static final class e extends com.jingdong.manto.m.d {
        private e() {
        }

        /* synthetic */ e(ViewOnClickListenerC0578a viewOnClickListenerC0578a) {
            this();
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onImageViewClick";
        }
    }

    @Override // com.jingdong.manto.jsapi.coverview.b
    public final InputStream a(MantoCore mantoCore, String str) {
        return readFile(mantoCore, str);
    }

    @Override // com.jingdong.manto.jsapi.coverview.c
    public void a(MantoCore mantoCore, String str, int i2) {
        MantoThreadUtils.post(new c(str, mantoCore, i2), 1000);
    }

    @Override // com.jingdong.manto.jsapi.coverview.c
    public void a(MantoCore mantoCore, String str, int i2, int i3, int i4) {
        MantoThreadUtils.post(new b(str, i2, i3, mantoCore, i4), 1000);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore) {
        Context g2 = com.jingdong.a.g();
        com.jingdong.manto.widget.b bVar = new com.jingdong.manto.widget.b(g2);
        bVar.setScaleType(ImageView.ScaleType.FIT_XY);
        return new CoverViewContainer(g2, bVar);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return "ImageView";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final String getViewName() {
        return "ImageView";
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
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final boolean onViewInsert(Bundle bundle, View view, MantoCore mantoCore) {
        JSONObject jSONObject;
        bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        String string = bundle.getString("appUniqueId");
        int i2 = bundle.getInt("hashCode");
        int i3 = bundle.getInt("runtimeHashCode");
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        com.jingdong.manto.widget.b bVar = (com.jingdong.manto.widget.b) ((CoverViewContainer) view).convertTo(com.jingdong.manto.widget.b.class);
        boolean optBoolean = jSONObject.optBoolean(JDPureVideoManager.SourceKey.CLICKABLE);
        jSONObject.optBoolean("transEvt");
        String optString = jSONObject.optString("sendTo", "appservice");
        String optString2 = jSONObject.optString("data", "");
        com.jingdong.manto.jsapi.container.b.a(view, jSONObject.optJSONObject(DeeplinkProductDetailHelper.LAYER_STYLE));
        com.jingdong.manto.jsapi.coverview.a.a(mantoCore, i3, string, this, bVar, jSONObject, this, optString2, i2, jSONObject.optString("mode"));
        SoftReference softReference = new SoftReference(mantoCore);
        if (bVar == null || !optBoolean) {
            return true;
        }
        bVar.setOnClickListener(new ViewOnClickListenerC0578a(softReference, optString2, optString, i2));
        bVar.setClickable(optBoolean);
        return true;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final boolean onViewRemove(Bundle bundle, View view, MantoCore mantoCore) {
        return true;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle) {
        JSONObject jSONObject;
        com.jingdong.manto.widget.b bVar;
        bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        String string = bundle.getString("appUniqueId");
        String string2 = bundle.getString("json");
        int i2 = bundle.getInt("hashCode");
        int i3 = bundle.getInt("runtimeHashCode");
        try {
            jSONObject = new JSONObject(string2);
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        JSONObject jSONObject2 = jSONObject;
        if (!(view instanceof CoverViewContainer) || (bVar = (com.jingdong.manto.widget.b) ((CoverViewContainer) view).convertTo(com.jingdong.manto.widget.b.class)) == null) {
            return false;
        }
        com.jingdong.manto.jsapi.container.b.a(view, jSONObject2.optJSONObject(DeeplinkProductDetailHelper.LAYER_STYLE));
        com.jingdong.manto.jsapi.coverview.a.a(mantoCore, i3, string, this, bVar, jSONObject2, this, jSONObject2.optString("data", ""), i2, jSONObject2.optString("mode", ""));
        return true;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle, MantoCallback mantoCallback) {
        return false;
    }
}
