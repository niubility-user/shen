package com.jingdong.manto.m.k1;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.e;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.c;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.IMantoReport;
import com.jingdong.manto.utils.t;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends AbstractMantoModule {

    /* loaded from: classes15.dex */
    class a implements IMantoReport.IMantoReportCallback {
        final /* synthetic */ MantoResultCallBack a;

        a(b bVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.sdk.api.IMantoReport.IMantoReportCallback
        public void onError(JSONObject jSONObject, Throwable th) {
            Bundle bundle = new Bundle();
            bundle.putString("errorMessage", th.getMessage());
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.sdk.api.IMantoReport.IMantoReportCallback
        public void onSuccess(JSONObject jSONObject) {
            this.a.onSuccess(null);
        }
    }

    /* renamed from: com.jingdong.manto.m.k1.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0582b extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        C0582b(b bVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            Bundle bundle = new Bundle();
            bundle.putString("errorMessage", th.getMessage());
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            if (jSONObject.optInt("code", -1) == 0) {
                this.a.onSuccess(null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("errorMessage", jSONObject.optString("errorMessage"));
            this.a.onFailed(bundle);
        }
    }

    private JSONArray a(MantoCore mantoCore, int i2, JSONArray jSONArray) {
        if (1 != i2) {
            return jSONArray;
        }
        String c2 = t.c(((e) mantoCore).getLatestRuntime().m());
        JSONArray jSONArray2 = new JSONArray();
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i3);
            if (!TextUtils.equals("firstRenderTime", optJSONObject.optString("type")) || TextUtils.equals(c2, optJSONObject.optString("path"))) {
                jSONArray2.put(optJSONObject);
            }
        }
        return jSONArray2;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "reportError";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        PkgDetailEntity c2;
        String string = bundle.getString("data");
        if (string == null) {
            return;
        }
        String string2 = bundle.getString("appid");
        String string3 = bundle.getString("type");
        if (string2 == null || string3 == null || (c2 = com.jingdong.manto.b.k().c(string2, string3)) == null) {
            return;
        }
        String str2 = c2.versionName;
        str.hashCode();
        str.hashCode();
        char c3 = '\uffff';
        int i2 = 2;
        switch (str.hashCode()) {
            case -1203606180:
                if (str.equals("errorReport")) {
                    c3 = 0;
                    break;
                }
                break;
            case -147317483:
                if (str.equals("networkRequestReport")) {
                    c3 = 1;
                    break;
                }
                break;
            case 1779658116:
                if (str.equals("performanceReport")) {
                    c3 = 2;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
            default:
                i2 = 0;
                break;
            case 1:
                break;
            case 2:
                i2 = 1;
                break;
        }
        try {
            c cVar = new c(string2, str2, a(mantoCore, i2, new JSONObject(string).getJSONArray("dataArray")), i2);
            IMantoReport iMantoReport = (IMantoReport) com.jingdong.a.n(IMantoReport.class);
            if (iMantoReport != null) {
                iMantoReport.reportData(cVar.getFunctionId(), cVar.getPostBody(), new a(this, mantoResultCallBack));
            } else {
                MantoJDHttpHandler.commit(cVar, new C0582b(this, mantoResultCallBack));
            }
        } catch (Throwable th) {
            th.printStackTrace();
            Bundle bundle2 = new Bundle();
            bundle2.putString("errorMessage", th.getMessage());
            mantoResultCallBack.onFailed(bundle2);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        Bundle bundle = new Bundle(1);
        bundle.putString("data", jSONObject2);
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("errorReport", 1));
        list.add(new JsApiMethod("performanceReport", 1));
        list.add(new JsApiMethod("networkRequestReport", 1));
    }
}
