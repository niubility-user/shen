package com.jingdong.manto.m.d1;

import android.os.Bundle;
import android.view.View;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.q.n;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class g extends c {

    /* loaded from: classes15.dex */
    class a implements MantoResultCallBack {
        final /* synthetic */ e0 a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13338c;

        a(e0 e0Var, int i2, String str) {
            this.a = e0Var;
            this.b = i2;
            this.f13338c = str;
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
        public void onCancel(Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(IMantoBaseModule.ERROR_CODE, "-1");
            g.this.a(this.a, this.b, bundle, this.f13338c);
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
        public void onFailed(Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
            g.this.a(this.a, this.b, bundle, this.f13338c);
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
        public void onSuccess(Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(IMantoBaseModule.ERROR_CODE, "1");
            g.this.a(this.a, this.b, bundle, this.f13338c);
        }
    }

    public g(i iVar) {
        super(iVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(e0 e0Var, int i2, Bundle bundle, String str) {
        String str2;
        String string = bundle.getString(IMantoBaseModule.ERROR_CODE, "0");
        String string2 = bundle.getString("message", "error");
        Map a2 = a(bundle);
        if (a2 == null) {
            a2 = new HashMap(1);
        }
        if ("1".equals(string)) {
            a2.remove(IMantoBaseModule.ERROR_CODE);
            str2 = IMantoBaseModule.SUCCESS;
        } else if ("0".equals(string)) {
            str2 = "fail:" + string2;
        } else if ("-1".equals(string)) {
            str2 = "cancel";
        } else {
            str2 = "" + bundle.getString("result", "fail") + ":" + string2;
        }
        e0Var.a(i2, putErrMsg(str2, a2, str));
    }

    @Override // com.jingdong.manto.m.d1.c
    protected void a(e0 e0Var, JSONObject jSONObject, int i2, int i3, String str) {
        n pageView = c0.getPageView(e0Var);
        if (pageView == null) {
            e0Var.a(i2, putErrMsg("fail:page is null", null, str));
            return;
        }
        MantoCore core = getCore(e0Var);
        if (core == null) {
            e0Var.a(i2, putErrMsg("fail", null, str));
            return;
        }
        Bundle initData = jSONObject != null ? this.a.c().initData(this.a.a(), core, jSONObject) : null;
        if (initData == null) {
            initData = new Bundle();
        }
        Bundle bundle = initData;
        bundle.putString("appid", e0Var.a());
        if (e0Var.h().f13016h != null) {
            bundle.putString("type", e0Var.h().f13016h.type);
        }
        bundle.putString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, e0Var.c());
        com.jingdong.manto.o.n nVar = pageView.p().get(5);
        if (nVar == null) {
            bundle.putBoolean(IMantoBaseModule.SHARE_SUPPORT_KEY, false);
        } else {
            bundle.putBoolean(IMantoBaseModule.SHARE_SUPPORT_KEY, nVar.a.a("user_clicked_share_btn", true));
        }
        int i4 = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY, 0);
        if (i4 == 0) {
            e0Var.a(i2, putErrMsg("fail", null, str));
            return;
        }
        String replace = str.replace("operate", "");
        View c2 = (!pageView.i(replace) || pageView.a(replace, i4) == null) ? pageView.n().c(i4) : pageView.a(replace, i4);
        if (c2 == null) {
            e0Var.a(i2, putErrMsg("fail", null, str));
            return;
        }
        bundle.putInt(IMantoBaseModule.COMPONENT_HASHCODE, e0Var.hashCode());
        ((AbstractMantoViewManager) this.a.c()).handleMethod(this.a.a(), c2, core, bundle, new a(e0Var, i2, str));
    }
}
