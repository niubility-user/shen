package com.jingdong.manto.m.d1;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.m.k0;
import com.jingdong.manto.o.n;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.utils.u;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends c {

    /* loaded from: classes15.dex */
    class a implements MantoResultCallBack {
        final /* synthetic */ e0 a;
        final /* synthetic */ int b;

        /* renamed from: c */
        final /* synthetic */ String f13336c;
        final /* synthetic */ com.jingdong.manto.jsapi.base.e d;

        a(e0 e0Var, int i2, String str, com.jingdong.manto.jsapi.base.e eVar) {
            e.this = r1;
            this.a = e0Var;
            this.b = i2;
            this.f13336c = str;
            this.d = eVar;
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
        public void onCancel(Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Bundle bundle2 = bundle;
            bundle2.putString(IMantoBaseModule.ERROR_CODE, "-1");
            e.this.a(this.a, this.b, bundle2, this.f13336c, this.d);
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
        public void onFailed(Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Bundle bundle2 = bundle;
            bundle2.putString(IMantoBaseModule.ERROR_CODE, "0");
            e.this.a(this.a, this.b, bundle2, this.f13336c, this.d);
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
        public void onSuccess(Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Bundle bundle2 = bundle;
            bundle2.putString(IMantoBaseModule.ERROR_CODE, "1");
            e.this.a(this.a, this.b, bundle2, this.f13336c, this.d);
        }
    }

    public e(i iVar) {
        super(iVar);
    }

    private final String a(String str, Map<String, ? extends Object> map, String str2) {
        HashMap hashMap = new HashMap();
        String jsApiName = getJsApiName();
        if (TextUtils.isEmpty(str2)) {
            str2 = jsApiName;
        }
        hashMap.put("errMsg", str2 + ":" + str);
        if (map != null) {
            if (map.containsKey("errMsg") && com.jingdong.a.a) {
                throw new RuntimeException("api " + str2 + ": Cant put errMsg in res!!!");
            }
            hashMap.putAll(map);
        }
        MantoUtils.mapToJson(hashMap);
        return new JSONObject(hashMap).toString();
    }

    public void a(e0 e0Var, int i2, Bundle bundle, String str, com.jingdong.manto.jsapi.base.e eVar) {
        String str2;
        StringBuilder sb;
        String str3;
        String str4 = str;
        if (bundle == null) {
            e0Var.a(i2, a("fail", (Map<String, ? extends Object>) null, str4));
        }
        String string = bundle.getString(IMantoBaseModule.ERROR_CODE, "0");
        String string2 = bundle.getString("message", "error");
        String string3 = bundle.getString(IMantoBaseModule.REQUEST_JSAPI_KEY, "");
        if (bundle.containsKey(IMantoBaseModule.UPDATE_LOGIN_STATUS_KEY)) {
            e0Var.h().z = Boolean.valueOf(bundle.getBoolean(IMantoBaseModule.UPDATE_LOGIN_STATUS_KEY, false)).booleanValue();
        }
        if (!TextUtils.isEmpty(string3)) {
            k0 d = e0Var.d();
            Map a2 = a(bundle);
            MantoUtils.mapToJson(a2);
            String jsApiName = getJsApiName();
            if (TextUtils.isEmpty(str)) {
                str4 = jsApiName;
            }
            d.a(string3, new JSONObject(a2).toString(), i2, str4);
            return;
        }
        if (bundle.containsKey(IMantoBaseModule.UPDATE_LOGIN_STATUS_KEY)) {
            bundle.remove(IMantoBaseModule.UPDATE_LOGIN_STATUS_KEY);
        }
        Map a3 = a(bundle);
        if (a3 == null) {
            a3 = new HashMap(1);
        }
        a3.remove(IMantoBaseModule.ERROR_CODE);
        if ("1".equals(string)) {
            str2 = IMantoBaseModule.SUCCESS;
        } else {
            if ("0".equals(string)) {
                a3.remove("message");
                sb = new StringBuilder();
                str3 = "fail:";
            } else {
                boolean equals = "-1".equals(string);
                a3.remove("message");
                if (equals) {
                    sb = new StringBuilder();
                    str3 = "cancel:";
                } else {
                    str2 = "" + bundle.getString("result", "fail") + ":" + string2;
                }
            }
            sb.append(str3);
            sb.append(string2);
            str2 = sb.toString();
        }
        e0Var.a(i2, a(str2, a3, str4));
        if (eVar != null) {
            c.a(c0.getPageView(e0Var), eVar);
        }
    }

    @Override // com.jingdong.manto.m.d1.c
    protected void a(e0 e0Var, JSONObject jSONObject, int i2, int i3, String str) {
        n nVar;
        MantoCore core = getCore(e0Var);
        com.jingdong.manto.jsapi.base.e eVar = null;
        if (core == null) {
            e0Var.a(i2, a("fail", (Map<String, ? extends Object>) null, str));
        }
        boolean a2 = u.a(jSONObject);
        if (a2) {
            u.a(e0Var, jSONObject, this);
        }
        Bundle initData = jSONObject != null ? this.a.c().initData(this.a.a(), core, jSONObject) : null;
        if (initData == null) {
            initData = new Bundle();
        }
        initData.putBoolean(IMantoBaseModule.HAS_NATIVE_BUFFER, a2);
        initData.putString("appid", e0Var.a());
        initData.putString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, e0Var.c());
        if (initData.getBoolean(IMantoBaseModule.ADD_EXTRAS_DATA)) {
            initData.putString(IMantoBaseModule.EXTRAS_DATA, e0Var.h().r.f13090m);
        }
        if (e0Var.h().f13016h != null) {
            initData.putString("type", e0Var.h().f13016h.type);
        }
        if (e0Var.h().r != null && !TextUtils.isEmpty(e0Var.h().r.p)) {
            initData.putString(IMantoBaseModule.ACTION_ID, e0Var.h().r.p);
        }
        com.jingdong.manto.q.n pageView = c0.getPageView(e0Var);
        initData.putBoolean(IMantoBaseModule.SHARE_SUPPORT_KEY, (pageView == null || (nVar = pageView.p().get(5)) == null) ? false : nVar.a.a("user_clicked_share_btn", true));
        initData.putInt(IMantoBaseModule.COMPONENT_HASHCODE, e0Var.hashCode());
        MantoLifecycleLisener addLifecycleLisener = this.a.c().addLifecycleLisener(getJsApiName(), initData);
        if (addLifecycleLisener != null && pageView != null) {
            eVar = c.a(pageView, addLifecycleLisener);
        }
        this.a.c().handleMethod(this.a.a(), core, initData, new a(e0Var, i2, str, eVar));
    }
}
