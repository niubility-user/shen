package com.jingdong.manto.m.d1;

import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.o.n;
import com.jingdong.manto.utils.MantoUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class f extends d {
    public f(i iVar) {
        super(iVar);
    }

    public final String a(e0 e0Var, Bundle bundle, MantoCore mantoCore) {
        StringBuilder sb;
        String str;
        n nVar;
        bundle.putString("appid", e0Var.a());
        if (e0Var.h().f13016h != null) {
            bundle.putString("type", e0Var.h().f13016h.type);
        }
        if (bundle.getBoolean(IMantoBaseModule.ADD_EXTRAS_DATA)) {
            bundle.putString(IMantoBaseModule.EXTRAS_DATA, e0Var.h().r.f13090m);
        }
        bundle.putString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, e0Var.c());
        com.jingdong.manto.q.n pageView = c0.getPageView(e0Var);
        bundle.putBoolean(IMantoBaseModule.SHARE_SUPPORT_KEY, (pageView == null || (nVar = pageView.p().get(5)) == null) ? false : nVar.a.a("user_clicked_share_btn", true));
        bundle.putInt(IMantoBaseModule.COMPONENT_HASHCODE, e0Var.hashCode());
        MantoLifecycleLisener addLifecycleLisener = this.b.c().addLifecycleLisener(getJsApiName(), bundle);
        com.jingdong.manto.jsapi.base.e a = (addLifecycleLisener == null || pageView == null) ? null : d.a(pageView, addLifecycleLisener);
        Bundle handleMethodSync = this.b.c().handleMethodSync(this.b.a(), mantoCore, bundle);
        if (a != null) {
            d.a(pageView, a);
        }
        String str2 = "fail:";
        if (handleMethodSync == null) {
            return putErrMsg("fail:", null);
        }
        Map<String, ? extends Object> formatBundle = MantoUtils.formatBundle(handleMethodSync);
        if (formatBundle == null) {
            formatBundle = new HashMap<>(1);
        }
        String string = handleMethodSync.getString(IMantoBaseModule.ERROR_CODE, "1");
        String string2 = handleMethodSync.getString("message", "error");
        if ("1".equals(string)) {
            formatBundle.remove(IMantoBaseModule.ERROR_CODE);
            str = IMantoBaseModule.SUCCESS;
        } else {
            if ("0".equals(string)) {
                formatBundle.remove("message");
                formatBundle.remove(IMantoBaseModule.ERROR_CODE);
                sb = new StringBuilder();
            } else if ("-1".equals(string)) {
                str = "cancel";
            } else {
                str2 = handleMethodSync.getString("result", "fail");
                sb = new StringBuilder();
            }
            sb.append(str2);
            sb.append(string2);
            str = sb.toString();
        }
        return putErrMsg(str, formatBundle);
    }

    @Override // com.jingdong.manto.m.d1.d
    protected String a(e0 e0Var, JSONObject jSONObject, int i2) {
        MantoCore core = getCore(e0Var);
        if (core == null) {
            return putErrMsg("fail", null);
        }
        Bundle initData = jSONObject != null ? this.b.c().initData(this.b.a(), core, jSONObject) : null;
        if (initData == null) {
            initData = new Bundle();
        }
        return a(e0Var, initData, core);
    }
}
